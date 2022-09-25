package project.repository;

import project.connection.DBConnection;
import project.entiry.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Muzhzukhin
 * ¯\_(ツ)_/¯
 */
public class OrderRepository {

    private final Connection connection = DBConnection.getConnection();

    public boolean create(String clientName, String clientPhone, String orderList) {
        boolean result = false;
        try {
            String sql = String.format("insert into orders (client_name, client_phone, order_list) values ('%s', '%s', '%s')",
                    clientName, clientPhone, orderList);
            executeQuery(sql, true);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean update(Order order) {
        boolean result = false;
        try {
            String sql = String.format("update orders set client_name='%s', client_phone='%s' , order_list='%s' where id=%d",
                    order.getClientName(), order.getClientPhone(), order.getOrder(), order.getId());
            executeQuery(sql, true);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try {
            String sql = String.format("delete from orders where id=%d", id);
            executeQuery(sql, true);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Order getById(int id) {
        Order result = null;
        try {
            String sql = String.format("SELECT id, client_name, client_phone, order_list FROM orders WHERE id=%d", id);
            ResultSet rs = executeQuery(sql, false);
            if (rs != null) {
                while (rs.next()) {
                    String clientName = rs.getString("client_name");
                    String phone = rs.getString("client_phone");
                    String order = rs.getString("order_list");
                    result = new Order(id, clientName, phone, order);
                }
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Order> getAll() {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT id, client_name, client_phone, order_list FROM orders";
        try {
            ResultSet rs = executeQuery(sql, false);
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String clientName = rs.getString("client_name");
                    String phone = rs.getString("client_phone");
                    String order = rs.getString("order_list");
                    orderList.add(new Order(id, clientName, phone, order));
                }
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    private ResultSet executeQuery(String sql, boolean update) throws SQLException {
        ResultSet rs = null;
        Statement stmt = connection.createStatement();
        if (!update) {
            rs = stmt.executeQuery(sql);
        } else {
            stmt.execute(sql);
        }
        return rs;
    }
}
