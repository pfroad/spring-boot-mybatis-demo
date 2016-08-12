package sample.web.ui.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages = DruidDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class DruidDataSourceConfig {
	public static final String PACKAGE = "sample.web.ui.**.mapper";

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
	}
	
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource ds) {
		return new DataSourceTransactionManager(ds);
	} 
	
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource ds) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(ds);
		return sqlSessionFactory.getObject();
	}
}
