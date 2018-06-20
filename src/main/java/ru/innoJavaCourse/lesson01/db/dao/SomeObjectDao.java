package ru.innoJavaCourse.lesson01.db.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.innoJavaCourse.lesson01.db.connection.ConnectionManager;
import ru.innoJavaCourse.lesson01.db.connection.ConnectionManagerImpl;
import ru.innoJavaCourse.lesson01.db.pojo.SomeObject;
import ru.innoJavaCourse.lesson01.util.RandomWordGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SomeObjectDao {

    private static ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
    private static final Logger logger = Logger.getLogger("defaultLog");

    public void addAndDeleteSomeObjects(List<SomeObject> listOfObjects) {
        try (Connection connection = connectionManager.getConnection()) {
            for (SomeObject someObject : listOfObjects) {
                long id = addSomeObject(someObject, connection);
                delSomeObjectById(id, connection);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private long addSomeObject(SomeObject someObject, Connection connection) {
        long result = -1;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO some_object (name, value) VALUES (?, ?) RETURNING id"
        )) {
            statement.setString(1, someObject.getName());
            statement.setString(2, someObject.getValue());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getLong("id");
                }
            }
            logger.info("Object successfully added.");
            return result;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return result;
        }
    }

    private void delSomeObjectById(long id, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM some_object WHERE id = ?"
        )) {
            statement.setLong(1, id);
            statement.execute();
            logger.info("Object successfully deleted");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public List<SomeObject> getSomeObjects() {
        List<SomeObject> result = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM some_object"
        )) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String value = resultSet.getString("value");
                    SomeObject someObject = new SomeObject(id, name, value);
                    result.add(someObject);
                }
            }
            connection.close();
            return result;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return result;
        }
    }

    /*public void delSomeObjectById(long id) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM some_object WHERE id = ?"
        )) {
            statement.setLong(1, id);
            statement.execute();
            logger.info("Object successfully deleted");
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }*/

}
