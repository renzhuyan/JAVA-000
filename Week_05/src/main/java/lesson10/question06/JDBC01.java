package lesson10.question06;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 研究一下JDBC 接口和数据库连接池，掌握它们的设计和用法：
 * 1）使用JDBC 原生接口，实现数据库的增删改查操作。
 */
public class JDBC01 {
    public final static String URL = "jdbc:mysql://localhost:3306/tratest?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private final static String USER = "test";
    private final static String PASSWORD = "test";

    public static void main(String[] args) throws SQLException {
        String insertSql = "insert into student(id,name) values ('2','lisi')";
        insert(insertSql);

        String updateSql = "update student set name ='lisisi' where id ='2'";
        update(updateSql);

        List<Map<String,Object>> list = query("select id,name from student");
        for (Map<String,Object> map : list) {
            System.out.println("id="+map.get("id"));
            System.out.println("name="+map.get("name"));
        }

        String deleteSql = "delete from student where id ='2'";
        delete(deleteSql);
    }

    /**
     * 创建数据库连接
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() {
        Connection conn = null;

        try {
            // 1、加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2、创建连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("创建连接失败:" + e);
        }

        return conn;
    }

    /**
     * @param sql
     * @throws SQLException
     */
    public static void delete(String sql) throws SQLException {
        Connection conn = getConnection();
        Statement stat = null;
        if (conn != null) {
            // 3、获取statement对象
            try {
                stat = conn.createStatement();
                stat.execute(sql);
                System.out.println("删除成功");
            } catch (Exception e) {
                System.out.println("删除失败");
            } finally {
                try {
                    stat.close();
                    conn.close();
                } catch (Exception e) {

                }

            }

        }
    }

    /**
     * @param sql
     * @throws SQLException
     */
    public static void update(String sql) throws SQLException {
        Connection conn = getConnection();
        Statement stat = null;
        if (conn != null) {
            // 3、获取statement对象
            try {
                stat = conn.createStatement();
                stat.execute(sql);
                System.out.println("修改成功");
            } catch (Exception e) {
                System.out.println("修改失败");
            } finally {
                try {
                    stat.close();
                    conn.close();
                } catch (Exception e) {

                }

            }

        }
    }


    /**
     * @param sql
     * @throws SQLException
     */
    public static void insert(String sql) {
        Connection conn = getConnection();
        Statement stat = null;
        if (conn != null) {
            // 3、获取statement对象
            try {
                stat = conn.createStatement();
                stat.execute(sql);
                System.out.println("新增成功");
            } catch (Exception e) {
                System.out.println("新增失败");
            } finally {
                try {
                    stat.close();
                    conn.close();
                } catch (Exception e) {

                }

            }

        }
    }

    /**
     * @param sql
     * @throws SQLException
     */
    public static List<Map<String,Object>> query(String sql)  {
        Connection conn = getConnection();
        boolean flag = false;
        Statement stat = null;
        List<Map<String,Object>> result = new ArrayList<>();
        if (conn != null) {
            // 3、获取statement对象
            try {
                stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);
                while (rs.next()) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("id",rs.getInt("id"));
                    map.put("name",rs.getString("name"));
                    result.add(map);
                }
            } catch (Exception e) {
                System.out.println("查询失败");
            } finally {
                try {
                    stat.close();
                    conn.close();
                } catch (Exception e) {

                }

            }

        }

        return result;
    }
}
