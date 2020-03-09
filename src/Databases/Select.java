package Databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Select {

    public static void main(String[] args) {
        List<Store> list = new Select().select();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));

        }

    }


    public List<Store> select() {
        // register
        Statement stmn = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Store> list = null;
        String url = "jdbc:mysql://172.104.163.123:3306/test";
        String user = "test";
        String password = "test123";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select * from house";
            stmn = conn.createStatement();
            rs = stmn.executeQuery(sql);

            list = new ArrayList<Store>();

            while (rs.next()) {
                int id = rs.getInt(1);
                String space = rs.getString(2);
                String group = rs.getString(3);
                String action = rs.getString(4);
                String buy = rs.getString(5);
                int cost = rs.getInt(6);
                int rent0 = rs.getInt(7);
                int rent1 = rs.getInt(8);
                int rent2 = rs.getInt(9);
                int rent3 = rs.getInt(10);
                int rent4 = rs.getInt(11);
                int hotel = rs.getInt(12);


                Store s1 = new Store();
                s1.setPosition(id);
                s1.setSpace(space);
                s1.setGroup(group);
                s1.setAction(action);
                s1.setBuy(buy);
                s1.setCost(cost);
                s1.setRent_unimproved(rent0);
                s1.setRent_1house(rent1);
                s1.setRent_2house(rent2);
                s1.setRent_3house(rent3);
                s1.setRent_4house(rent4);
                s1.setRent_hotel(hotel);
                list.add(s1);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmn != null) {
                try {
                    stmn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return list;
    }
}
