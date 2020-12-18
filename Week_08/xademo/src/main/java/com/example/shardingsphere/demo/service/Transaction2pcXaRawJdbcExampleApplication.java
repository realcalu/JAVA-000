package com.example.shardingsphere.demo.service;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Transaction2pcXaRawJdbcExampleApplication {

    /**
     * 第一次插入数据正常运行成功
     * 第二次插入数据由于主键冲突，导致回滚
     */
    public static void main(String[] args) throws IOException, SQLException {
        DataSource dataSource = getShardingDatasource();
//        cleanupData(dataSource);

        TransactionTypeHolder.set(TransactionType.XA);

        Connection conn = dataSource.getConnection();


        String sql = "insert into t_order (buyerid, orderid,snapshotid,createtime,updatetime,state) VALUES (?, ?,?,?,?,?);";

        System.out.println("First XA Start insert data");
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (int i = 1; i < 11; i++) {
                statement.setLong(1, i);
                statement.setLong(2, i);
                statement.setLong(3, new Date().getTime());
                statement.setLong(4, new Date().getTime());
                statement.setLong(5, new Date().getTime());
                statement.setLong(6, 1);
                statement.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            System.out.println("Second XA insert failed");
            conn.rollback();
        } finally {
            conn.close();
        }

        System.out.println("First XA insert successful");

        // 设置id+5，如果设置XA事务成功，则所有的数据都不会插入
        // 设置id+5，如果设置XA事务不成功，则id大于10的数据就会插入到数据库
        // 程序运行完毕后，查看数据库，没有id大于10的数据，所以XA设置成功
        System.out.println("Second XA Start insert data");
        sql = "update t_order set snapshotid=?,createtime=?,updatetime=?,state=? where orderid=? and buyerid=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (int i = 1; i < 5; i++) {
                statement.setLong(1, new Date().getTime());
                statement.setLong(2, new Date().getTime());
                statement.setLong(3, new Date().getTime());
                statement.setLong(4, 2);
                statement.setLong(5, i);
                statement.setLong(6, i);
                statement.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            System.out.println("Second XA insert failed");
            conn.rollback();
        } finally {
            conn.close();
        }
        System.out.println("Second XA insert successful");
    }

    private static void cleanupData(DataSource dataSource) {
        System.out.println("Delete all Data");
        try (Connection conn = dataSource.getConnection(); Statement statement = conn.createStatement()) {
            statement.execute("delete from t_order;");
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Delete all Data successful");
    }

    /**
     * 生成DataSource，文件路径自行替换
     *
     * @return
     * @throws IOException
     * @throws SQLException
     */
    static private DataSource getShardingDatasource() throws IOException, SQLException {
        String fileName = "/Users/apple/calu/work/JAVA-000/Week_08/xademo/src/main/resources/sharding-tables.yaml";
        File yamlFile = new File(fileName);
        return YamlShardingSphereDataSourceFactory.createDataSource(yamlFile);
    }

}