package com.example.repository;

import com.example.data.User;
import com.example.data.Subscription;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDBRepository implements Repository<String, Subscription> {
    private final String url;
    private final String username;
    private final String password;

    public SubscriptionDBRepository(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Subscription> getAll()
    {
        List<Subscription> subscriptions = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * from \"Subscription\"");
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next())
            {
                String id = resultSet.getString("id");
                String u_id = resultSet.getString("user_id");
                UUID user_id = UUID.fromString(u_id);
                String subscriptionName = resultSet.getString("subscriptionName");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                String subscriptionType = resultSet.getString("subscriptionType");
                Float price = resultSet.getFloat("price");

                Subscription subscription = new Subscription(id, subscriptionName, startDate, endDate, subscriptionType, price);
                subscription.setUserID(user_id);

                subscriptions.add(subscription);
            }
            return subscriptions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriptions;
    }

    @Override
    public void add(SUbscription subscription)
    {
        String sql = "insert into \"Subscription\" (id, user_id, subscriptionName, startDate, endDate, subscriptionType, price) values (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, subscription.getId());
            ps.setString(2, UUID.toString(subscription.getUserID()));
            ps.setString(3, subscription.getSubscriptionName());
            ps.setDate(4, subscription.getStartDate());
            ps.setDate(5, subscription.getEndDate());
            ps.setString(6, subscription.getSubscriptionType());
            ps.setFloat(7, subscription.getPrice());

            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Subscription subscription){
        String sql = "DELETE from \"Subscription\" where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, subscription.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User searchById(String id){
        String sql = "select * from \"Subscription\" where id = ? ";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            UUID user_id = UUID.fromString(resultSet.getString("user_id"));
            String subscriptionName = resultSet.getString("subscriptionName");
            Date startDate = resultSet.getDate("startDate");
            Date endDate = resultSet.getDate("endDate");
            String subscriptionType = resultSet.getString("subscriptionType");
            Folat price = resultSet.getFloat("price");
            Subscription subscription = new Subscription(id, subscriptionName, startDate, endDate, subscriptionType, price);
            subscription.setUserID(user_id);
            return subscription;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Subscription oldSubscription,Subscription newSubscription){
        String sql = "update \"Subscription\" set subscriptionName = ?, startDate = ?, endDate = ?, subscriptionType = ?, price = ? where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, newSubscription.getSubscriptionName());
            ps.setString(2, newSubscription.getStartDate());
            ps.setString(3, newSubscription.getEndDate());
            ps.setString(4, newSubscription.getSubscriptionType());
            ps.setString(5, newSubscription.getPrice());
            ps.setString(6, oldSubscription.getID());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
