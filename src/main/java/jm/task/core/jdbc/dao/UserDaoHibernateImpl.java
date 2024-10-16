package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Session session = Util.getSession();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        createDropCleanTable(UserDao.SQL_CREATE, UserDao.PRINT_CREATE);
    }

    @Override
    public void dropUsersTable() {
        createDropCleanTable(UserDao.SQL_DROP, UserDao.PRINT_DROP);

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session.beginTransaction();
        List<User> users = session.createQuery("FROM User").getResultList();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        createDropCleanTable(UserDao.SQL_TRUNCATE, UserDao.PRINT_TRUNCATE);
    }

    private void createDropCleanTable(String sql, String print) {
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        System.out.println(print);
    }
}
