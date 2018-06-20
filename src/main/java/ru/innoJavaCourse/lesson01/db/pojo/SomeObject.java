package ru.innoJavaCourse.lesson01.db.pojo;

import ru.innoJavaCourse.lesson01.util.RandomWordGenerator;

public class SomeObject {
    private long id;
    private String name;
    private String value;

    public SomeObject() {
    }

    public SomeObject(long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public SomeObject(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
