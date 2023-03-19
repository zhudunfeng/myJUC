package com.adun;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ADun
 * @date 2022/12/1 18:05
 */

@SpringBootTest
public class SqlFileTest {

    @Test
    public void testCreateDB() throws ClassNotFoundException, SQLException, IOException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
        String userName = "root";
        String password = "root";
        String driverClass = "com.mysql.cj.jdbc.Driver";

        String schema = "dbTest";

        String sqlCreateSchema = "CREATE DATABASE `#{schema}` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';";
        String sql2 = "use `#{schema}`";

        //加载驱动
        Class.forName(driverClass);
        //获取连接
        Connection connection = DriverManager.getConnection(jdbcUrl, userName, password);

        //获取通道
        Statement statement = connection.createStatement();
        statement.execute(sqlCreateSchema.replace("#{schema}", schema));
        statement.execute(sql2.replace("#{schema}", schema));

        //使用mybatis脚本执行器
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        //出现错误停止
        scriptRunner.setStopOnError(true);

        ClassPathResource classPathResource = new ClassPathResource("sql/users.sql");
        InputStream inputStream = classPathResource.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        //执行脚本
        scriptRunner.runScript(reader);

        //提交sql语句
        connection.commit();

        //关闭连接 回收资源
        connection.close();

    }


    @Test
    public void tryConn(){
        Connection connection = null;
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/dbTest?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
            String userName = "root";
            String password = "root";
            String driverClass = "com.mysql.cj.jdbc.Driver";
            //加载驱动
            Class.forName(driverClass);
            //获取连接
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("连接成功");
        } catch (Exception e) {
            System.out.println("连接失败");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
//                    connection.commit();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
