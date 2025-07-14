package myApp.services;


import myApp.dao.UserDao;
import myApp.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private User userOne = new User("Alex", "mymail@yandex.ru", 22);

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDao = new UserDao();
    }

    @Test
    public void getById() {
        User savedUser = userDao.getById(1);
        assertEquals(userOne.getName(), savedUser.getName());
        assertEquals(userOne.getEmail(), savedUser.getEmail());
        assertEquals(userOne.getAge(), savedUser.getAge());
    }

    @Test
    public void getAll() {
        List<User> findAllUsers = userDao.getAll();

        assertEquals(findAllUsers.size(), userService.getAll().size());
    }

    @Test
    public void saveUser() {
        User newUser = new User("Ivan", "ivan@gmail.com", 28);
        UserService userService = mock(UserService.class);
        userService.saveUser(newUser);
        verify(userService).saveUser(newUser);
    }

    @Test
    public void updateUser() {
        List<User> allUsers = userDao.getAll();
        User updateUser = allUsers.get(allUsers.size() - 1);
        UserService userService = mock(UserService.class);
        userService.saveUser(updateUser);
        verify(userService).saveUser(updateUser);
    }

    @Test
    public void deleteUser() {
        List<User> allUsers = userDao.getAll();
        User deleteUser = allUsers.get(allUsers.size() - 1);
        UserService userService = mock(UserService.class);
        userService.deleteUser(deleteUser);
        verify(userService).deleteUser(deleteUser);
    }
}
