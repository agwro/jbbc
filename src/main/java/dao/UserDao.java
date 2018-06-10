package dao;


import com.sun.xml.internal.ws.api.ha.StickyFeature;
import config.DataBase;
import model.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    // save
    //update
    //delete
    //findById
    //findAll
    private DataBase database = new DataBase();

    public User save(User user) throws SQLException {
        //insert
        //1.pobirz połączenie
        Connection connection = database.getConnetion();

        //2. napisz zapytania
        //      String sql = "INSERT INTO user(first_name, last_name,email)"+
        //              "VALUES(user.getFirstMame()....)"; // bniebezpieczne

        String sql = "INSERT INTO user(first_name, last_name,email)" +
                "VALUES(?,?,?)";
        // 3. utwórz obiect prepateStatement
        PreparedStatement statement = connection.prepareStatement(sql);
        //4 uzupełnij parametry zapytania
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());

        //5 wykonaj zapytanie w bazie
        statement.executeUpdate();

        return user;

    }

    public void update(User user) throws SQLException {
        Connection connection = database.getConnetion();
        String sql = "UPDATE user SET(first_name=?, last_name=?, email=?)"+
                "WHERE(id=?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        //4 uzupełnij parametry zapytania
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setInt(4,user.getId());
        statement.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        Connection connection = database.getConnetion();
        String sql = "DELETE FROM user WHERE id=?";
        PreparedStatement statement = null;
        statement.setInt(1, id);
        statement = connection.prepareStatement(sql);
        statement.executeUpdate();

    }

    public User findById(int id) throws SQLException {
        Connection connection = database.getConnetion();
        String sql=("SELECT id, first_name, last_name, email FROM user Where id=?");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        User user = null;
        while (result.next()){
            id=result.getInt("id");
            String firstName=result.getString("first_name");
            String lastName=result.getNString("last_name");
            String email=result.getNString("email");
            user=new User(id, firstName, lastName,email);

        }
        return user;
    }

    public List<User> findAll() throws SQLException {
        Connection connection = database.getConnetion();
        String sql=("SELECT id, first_name, last_name, email FROM user Where id=?");
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        List<User> users = new ArrayList<>();

        while (result.next()){
            int id = result.getInt("id");
            String firstName = result.getString("first_name");
            String lastName = result.getNString("last_name");
            String email = result.getNString("email");
            users.add(new User(id, firstName, lastName, email));
        }


        return users;
    }

}
