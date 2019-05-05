package org.aron.mybatisTest.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class HikariCPDataSourceFactory implements DataSourceFactory {

    private Properties props;

    @Override
    public void setProperties(Properties properties) {
        this.props = properties;
    }

    @Override
    public DataSource getDataSource() {
        // 初始化HikariDataSource
        HikariDataSource dataSource = new HikariDataSource();
        // url/username/password/driver
        // 对应 mybatis.xml中的 <property name="driver" value=".."> name属性
        dataSource.setJdbcUrl(this.props.getProperty("url"));
        dataSource.setUsername(this.props.getProperty("username"));
        dataSource.setPassword(this.props.getProperty("password"));
        dataSource.setDriverClassName(this.props.getProperty("driver"));
        return dataSource;
    }
}
