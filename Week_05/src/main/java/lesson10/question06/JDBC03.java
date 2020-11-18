package lesson10.question06;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

/**
 * 研究一下JDBC 接口和数据库连接池，掌握它们的设计和用法：
 * 3）配置Hikari 连接池。
 */
public class JDBC03 {
    public final static String URL = "jdbc:mysql://localhost:3306/tratest?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private final static String USER = "test";
    private final static String PASSWORD = "test";

    public static void main(String[] args) throws SQLException {
        String insertSql1 = "insert into student(id,name) values ('2','lisi2')";
        String insertSql2 = "insert into student(id,name) values ('3','lisi3')";
        //batchInsert(insertSql1,insertSql2);

        String insertSqlPre = "insert into student(id,name) values (?,?)";
        batchInsertByPre(insertSqlPre);
    }

    /**
     * 创建数据库连接池
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() {
        HikariDataSource  ds = null;
        Connection conn = null;
        try {
            HikariConfig conf = new HikariConfig();
            conf.setUsername(USER);
            conf.setPassword(PASSWORD);
            conf.setJdbcUrl(URL);
            ds = new HikariDataSource(conf);
            conn = ds.getConnection();
        } catch (Exception e) {
            System.out.println("Hikari连接池创建连接失败:" + e);
        }

        return conn;
    }

    public static void batchInsert(String sql1,String sql2) {
        Connection conn = getConnection();
        Statement ps = null;
        try{
            conn.setAutoCommit(false);
            ps = conn.createStatement();
            ps.addBatch(sql1);
            ps.addBatch(sql2);
            ps.executeBatch();
            conn.commit();
            System.out.println("批量操作执行成功");
        } catch (Exception e){
            try {
                conn.rollback();
            } catch (Exception e1){
                System.out.println("批量操作回滚失败："+ e1);
            }
        } finally {
            if(conn != null){
                try{
                    conn.close();
                } catch (Exception e2){

                }

            }
            if (ps != null){
                try{
                    ps.close();
                } catch (Exception e2){

                }
            }
        }
    }

    public static void batchInsertByPre(String sql) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try{
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);


            ps.setInt(1,10);
            ps.setString(2,"test1");
            ps.addBatch();

            ps.setInt(1,20);
            ps.setString(2,"test2");
            ps.addBatch();

            ps.executeBatch();
            conn.commit();
            System.out.println("批量操作执行成功");
        } catch (Exception e){
            try {
                conn.rollback();
            } catch (Exception e1){
                System.out.println("批量操作回滚失败："+ e1);
            }
        } finally {
            if(conn != null){
                try{
                    conn.close();
                } catch (Exception e2){

                }

            }
            if (ps != null){
                try{
                    ps.close();
                } catch (Exception e2){

                }
            }
        }
    }



}
