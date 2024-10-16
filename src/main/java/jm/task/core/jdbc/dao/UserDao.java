package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    String SQL_CREATE = "CREATE TABLE IF NOT EXISTS users (" +
            "id BIGSERIAL PRIMARY KEY, " +
            "name VARCHAR(20) NOT NULL, " +
            "last_name VARCHAR(30) NOT NULL," +
            "age SMALLINT);";
    String PRINT_CREATE = "Table created";
    String SQL_DROP = "DROP TABLE IF EXISTS users;";
    String PRINT_DROP = "Table dropped";
    String SQL_TRUNCATE = "TRUNCATE TABLE users RESTART IDENTITY";
    String PRINT_TRUNCATE = "Table cleaned";

    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
