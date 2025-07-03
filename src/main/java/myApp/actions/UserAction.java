package myApp.actions;

import myApp.dao.UserDao;
import myApp.models.User;
import myApp.validation.Validation;

import java.util.List;
import java.util.Scanner;

public class UserAction {
    private final UserDao usersDao = new UserDao();
    private final Validation validation = new Validation();

    public String runScanner() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    public User findId(int id) {
        User user = usersDao.getById(id);
        if(user == null) System.out.println("Пользователь с ID: " + id + " не найден");
        return user;
    }

    public void getById() {
        System.out.print("Введите ID пользователя: ");
        String str = runScanner();

        if(!validation.checkId(str)) {
            System.out.println("Вы ввели неправильный ID");
            return;
        }

        User user = findId(Integer.parseInt(str));
        if (user != null) System.out.println(user);
    }

    public void getAll() {
        List<User> users = usersDao.getAll();
        if (users.isEmpty()) {
            System.out.println("Список пользователей пуст");
            return;
        }

        System.out.println("Список пользователей: ");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void saveUser() {
        User user = new User();

        System.out.println("Введите данные для пользователя: ");
        user.setName(enterUserName());
        user.setEmail(enterUserEmail());
        user.setAge(enterUserAge());

        usersDao.save(user);
        System.out.println("Пользователь сохранен");
    }

    public void updateUser() {
        System.out.print("Введите ID пользователя: ");
        String str = runScanner();

        if(!validation.checkId(str)) {
            System.out.println("Вы ввели неправильный ID");
            return;
        }

        User user = findId(Integer.parseInt(str));
        if (user != null) {
            System.out.println("Введите новые данные для пользователя: ");
            user.setName(enterUserName());
            user.setEmail(enterUserEmail());
            user.setAge(enterUserAge());

            usersDao.update(user);
            System.out.println("Пользователь с ID: " + str + " изменен");
        }
    }

    public void deleteUser() {
        System.out.print("Введите ID пользователя: ");
        String str = runScanner();

        if(!validation.checkId(str)) {
            System.out.println("Вы ввели неправильный ID");
            return;
        }

        User user = findId(Integer.parseInt(str));
        if (user != null) {
            usersDao.delete(user);
            System.out.println("Пользователь с ID: " + str + " удален");
        }
    }

    public String enterUserName() {
        do {
            System.out.println("Введите имя: ");
            String str = runScanner();
            if(validation.checkName(str)) return str;
            System.out.println("Вы ввели неправильное имя. Повторите попытку: ");
        } while (true);
    }

    public String enterUserEmail() {
        do {
            System.out.println("Введите почту: ");
            String str = runScanner();
            if(validation.checkEmail(str)) return str;
            System.out.println("Вы ввели неправильную почту. Повторите попытку: ");
        } while (true);
    }

    public int enterUserAge() {
        do {
            System.out.println("Введите возраст: ");
            String str = runScanner();
            if(validation.checkAge(str)) return Integer.parseInt(str);
            System.out.println("Вы ввели неправильный возраст. Повторите попытку: ");
        } while (true);
    }
}
