package com.example.repository;

import com.example.data.User;
import com.example.data.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDBRepository implements Repository<String, Payment> {
    private final String url;
    private final String username;
    private final String password;

    public PaymentDBRepository(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Payment> getAll()
    {
        List<Payment> payments = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * from \"Payment\"");
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next())
            {
                String  id = resultSet.getString("id");
                UUID subscription_id = UUID.fromString(resultSet.getString("subscription_id"));
                Date paymentDate = resultSet.getDate("paymentDate");
                Float amount = resultSet.getFloat("amount");
                String status = resultSet.getString("status");
                Payment payment = new Payment(id, paymentDate, amount, status);
                payment.setSubscription_id(subscription_id);

                payments.add(payment);
            }
            return payments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public void add(Payment payment)
    {
        String sql = "insert into \"Payment\" (id, subscription_id, paymentDate, amount, status) values (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, payment.getId());
            ps.setString(2, UUID.toString(payment.getSubscription_id));
            ps.setDate(3, payment.getPaymentDate());
            ps.setFloat(4, payment.getAmount());
            ps.setString(5, payment.getStatus());

            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Payment payment){
        String sql = "DELETE from \"Payment\" where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, payment.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User searchById(String id){
        String sql = "select * from \"Payment\" where id = ? ";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            UUID subscription_id = UUID.fromString(resultSet.getString("subscription_id"));
            Date paymentDate = resultSet.getDate("paymentDate");
            Float amount = resultSet.getFloat("amount");
            String status = resultSet.getString("status");
            Payment payment = new Payment(id, paymentDate, amount, status);
            payment.setSubscription_id(subscription_id);
            return payment;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Payment oldPayment,Payment newPayment){
        String sql = "update \"Payment\" set paymentDate = ?, amount = ?, status = ? where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setDate(1, newPayment.getPaymentDate());
            ps.setFloat(2, newPayment.getAmount());
            ps.setString(3, newPayment.getStatus());
            ps.setString(4, oldPayment.getId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
