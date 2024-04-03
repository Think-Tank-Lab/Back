package com.example.repository;

import com.example.data.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBRepository implements Repository<String, User> {
    private final String url;
    private final String username;
    private final String password;

    public UserDBRepository(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<User> getAll()
    {
        List<User> users = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * from \"User\"");
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next())
            {
                String id = resultSet.getString("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User user = new User(id, firstName, lastName, email, password);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void add(User user)
    {
        String sql = "insert into \"User\" (id, firstName, lastName, email, password) values (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, user.getId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());

            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(User user){
        String sql = "DELETE from \"User\" where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User searchById(String id){
        String sql = "select * from \"User\" where id = ? ";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            return new User(id, firstName, lastName, email, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(User oldUser,User newUser){
        String sql = "update \"User\" set firstName = ?, lastName = ?, email = ?,password = ? where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, newUser.getFirstName());
            ps.setString(2, newUser.getLastName());
            ps.setString(3, newUser.getEmail());
            ps.setString(4, newUser.getPassword());
            ps.setString(5, newUser.getId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
