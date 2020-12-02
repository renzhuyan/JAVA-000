package lesson13.question02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 2、（必做）按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率。
 * 方式一：Statement方式单条的方式插入1万次(100万次太久。。) 数据耗时：131194ms
 */
public class JDBC01 {

    public final static String URL = "jdbc:mysql://localhost:3306/tratest?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private final static String USER = "test";
    private final static String PASSWORD = "test";

    public static void main(String[] args) {
        long startMillis = System.currentTimeMillis();

        System.out.println("开始执行插入操作，插入中。。。");

        for (int i = 0 ; i < 10000 ; i++ ){
            String insertSql = "insert into order_info(order_id,user_id) values (" + i + ",100)";
            JDBC01.insert(insertSql);
        }

        long endMillis = System.currentTimeMillis();
        long takeMillis = endMillis - startMillis;

        System.out.println("结束执行插入操作");
        System.out.println("插入1万条数据耗时：" + takeMillis +"ms");

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

}


