package com.example.shardingsphere.demo;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @version 1.0
 * @Description TODO
 * @Author honghonghui
 * @Date
 **/
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() throws IOException, SQLException {
        String fileName = "/Users/apple/calu/work/JAVA-000/Week_08/xademo/src/main/resources/sharding-tables.yaml";
        File yamlFile = new File(fileName);
        return YamlShardingSphereDataSourceFactory.createDataSource(yamlFile);
    }
}
