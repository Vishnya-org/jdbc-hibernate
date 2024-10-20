package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection conn = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        createDropCleanTable(UserDao.SQL_CREATE, UserDao.PRINT_CREATE);
    }

    public void dropUsersTable() {
        createDropCleanTable(UserDao.SQL_DROP, UserDao.PRINT_DROP);
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users(name, last_name, age) VALUES (?,?,?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id=?");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        createDropCleanTable(UserDao.SQL_TRUNCATE, UserDao.PRINT_TRUNCATE);
    }

    private static void createDropCleanTable(String sql, String print) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            System.out.println(print);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
