package myApp.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "created_at")
    private Date created_at;

    public User() {
        this.created_at = new java.util.Date();
    }

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.created_at = new java.util.Date();
    }

    public void setUser_id(int id) {
        this.user_id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    @Override
    public String toString() {
        return "Пользователь: {" +
                "ID = " + user_id +
                ", Имя = '" + name + '\'' +
                ", Почта = '" + email + '\'' +
                ", Возраст = '" + age + '\'' +
                ", Создан = '" + created_at +
                '}';
    }
}
