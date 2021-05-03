package models;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "users1")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private Integer age;

    @Column
    private String email;

    public User() {
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(String name, String password, Integer age, String email) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    public User(Long id, String name, String password, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", password = '" + password + '\'' +
                ", age = " + age +
                ", email = '" + email + '\'' +
                '}' + '\'';
    }
}