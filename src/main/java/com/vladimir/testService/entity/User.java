package com.vladimir.testService.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", length = 6, nullable = false)
    private long _id;

    @Column(name = "name")
    private String _name;

    @Column(name = "surname")
    private String _surname;

    public User() {
    }

    public User(String name) {
        this._name = name;
    }

    public User(String name, String surname) {

        _name = name;
        _surname = surname;
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getSurname() {
        return _surname;
    }

    public void setSurname(String surname) {
        _surname = surname;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(User.class)) {
            return (_name == ((User) obj).getName() &&
                    _surname == ((User) obj).getSurname());
        }
        else {
            return super.equals(obj);
        }
    }
}
