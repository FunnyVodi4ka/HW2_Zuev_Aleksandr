package myApp.dao;

import myApp.models.User;
import myApp.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.Assert.*;

@Testcontainers
public class UserDaoTest {
    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("hw2")
            .withUsername("postgres")
            .withPassword("admin");

    private UserDao userDao;

    @BeforeAll
    public static void setUpClass() {
        System.setProperty("hibernate.connection.url", postgresContainer.getJdbcUrl());
        System.setProperty("hibernate.connection.username", postgresContainer.getUsername());
        System.setProperty("hibernate.connection.password", postgresContainer.getPassword());
    }

    @BeforeEach
    public void setUp() {
        userDao = new UserDao();
        createSchema();
    }

    private void createSchema() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS t_users (" +
                    "user_id SERIAL NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(150) NOT NULL," +
                    "age INT NOT NULL CHECK (age > 0)," +
                    "created_at DATE NOT NULL);").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @AfterEach
    public void tearDown() {
        clearData();
    }

    private void clearData() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM t_users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    public void testSaveAndGetById() {
        User user = new User("testUser", "test@example.com", 27);

        userDao.save(user);
        User retrievedUser = userDao.getById(user.getUser_id());
        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals("testUser", retrievedUser.getName());
        Assertions.assertEquals(27, retrievedUser.getAge());
    }

    @Test
    public void testFindByUsername() {
        User user = new User("uniqueUser", "unique@example.com", 28);

        userDao.save(user);
        User retrievedUser = user;
        Assertions.assertNotNull(retrievedUser);
        assertEquals("uniqueUser", retrievedUser.getName());
    }

    @Test
    public void testGetById_UserNotFound() {
        User retrievedUser = userDao.getById(99999);
        Assertions.assertNull(retrievedUser);
    }

    @Test
    public void testUpdate() {
        User user = new User("oldUsername", "old@example.com", 43);
        userDao.save(user);

        user.setName("newUsername");
        user.setEmail("new@example.com");

        userDao.update(user);

        User updatedUser = userDao.getById(user.getUser_id());
        assertNotNull(updatedUser);
        assertEquals("newUsername", updatedUser.getName());
        assertEquals("new@example.com", updatedUser.getEmail());
    }

    @Test
    public void testDelete() {
        User user = new User("userToDelete", "delete@example.com", 52);
        userDao.save(user);
        userDao.delete(user);
        User deletedUser = userDao.getById(user.getUser_id());
        assertNull(deletedUser);
    }

    @Test
    public void testGetAll() {
        User userA = new User("userA", "userA@example.com", 19);
        User userB = new User("userB", "userB@example.com", 24);

        userDao.save(userA);
        userDao.save(userB);

        List<User> users = userDao.getAll();

        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(u -> u.getName().equals("userA")));
        assertTrue(users.stream().anyMatch(u -> u.getName().equals("userB")));
    }
}
