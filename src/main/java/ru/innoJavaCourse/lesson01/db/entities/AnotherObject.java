package ru.innoJavaCourse.lesson01.db.entities;

import javax.persistence.*;

@Entity
public class AnotherObject {
    private long id;
    private String name;
    private String value;

    public AnotherObject() {
    }

    public AnotherObject(long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public AnotherObject(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Id
    @SequenceGenerator(name = "sellerSeq", sequenceName = "SELLER_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sellerSeq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
