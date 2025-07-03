package myApp.services;

import myApp.actions.UserAction;

import java.util.Scanner;

public class UserService {
    private final UserAction userAction = new UserAction();

    public UserService() {
    }

    public void start() {
        showMenu();
        controlMenu(runScanner());
    }

    public void showMenu() {
        System.out.println("Панель управления:" +
        "\n[1] Найти пользователя" +
        "\n[2] Показать всех пользователей" +
        "\n[3] Создать пользователя" +
        "\n[4] Изменить пользователя" +
        "\n[5] Удалить пользователя" +
        "\n[0] Выход" +
        "\nВыберите действие: ");
    }

    public String runScanner() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    public void controlMenu(String command) {
        switch (command) {
            case "1":
                getById();
                start();
                break;
            case "2":
                getAll();
                start();
                break;
            case "3":
                saveUser();
                start();
                break;
            case "4":
                updateUser();
                start();
                break;
            case "5":
                deleteUser();
                start();
                break;
            default:
                break;
        }
    }

    public void getById() {
        userAction.getById();
    }

    public void getAll() {
        userAction.getAll();
    }

    public void saveUser() {
        userAction.saveUser();
    }

    public void updateUser() {
        userAction.updateUser();
    }

    public void deleteUser() {
        userAction.deleteUser();
    }
}
