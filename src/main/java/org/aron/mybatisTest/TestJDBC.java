package org.aron.mybatisTest;

import java.sql.*;

public class TestJDBC {

    public static void main(String[] args) {
        // 数据库连接
        Connection connection = null;
        // 预编译的Statement
        PreparedStatement preparedStatement = null;
        // 结果集
        ResultSet resultSet = null;

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 通过驱动管理类获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC", "root", "123456");
            String sql = "select * from tb_test where `name`=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "name");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("nickname"));
                System.out.println(resultSet.getString("desc"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
