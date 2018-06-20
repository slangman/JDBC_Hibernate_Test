package ru.innoJavaCourse.lesson01.db.connection;

import java.sql.Connection;

public interface ConnectionManager {
    Connection getConnection();
}
