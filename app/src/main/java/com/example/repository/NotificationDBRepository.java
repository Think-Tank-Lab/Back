package com.example.repository;

import com.example.data.User;
import com.example.data.Notification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDBRepository implements Repository<String, Notification> {
    private final String url;
    private final String username;
    private final String password;

    public NotificationDBRepository(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Notification> getAll()
    {
        List<Notification> notifications = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * from \"Notification\"");
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next())
            {
                String id = resultSet.getString("id");
                String message = resultSet.getString("message");
                Date sendDate = resultSet.getDate("sendDate");
                Notification notification = new Notification(id, message, sendDate);

                notifications.add(notification);
            }
            return notifications;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    @Override
    public void add(Notification notification)
    {
        String sql = "insert into \"Notification\" (id, message, sendDate) values (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, notification.getId());
            ps.setString(2, notification.getMessage());
            ps.setDate(3, (Date) notification.getSendDate());

            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Notification notification){
        String sql = "DELETE from \"Notification\" where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, notification.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Notification searchById(String id){
        String sql = "select * from \"Notification\" where id = ? ";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            String message = resultSet.getString("message");
            Date sendDate = resultSet.getDate("sendDate");
            return new Notification(id, message, sendDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Notification oldNotification,Notification newNotification){
        String sql = "update \"Notification\" set message = ?, sendDate = ? where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, newNotification.getMessage());
            ps.setDate(2, (Date) newNotification.getSendDate());
            ps.setString(3, newNotification.getId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
