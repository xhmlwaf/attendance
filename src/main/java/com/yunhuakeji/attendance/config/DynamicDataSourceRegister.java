package com.yunhuakeji.attendance.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.yunhuakeji.attendance.enums.DataBaseType;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * 动态数据源注册<br/> 启动动态数据源请在启动类中（如SpringBootSampleApplication） 添加 @Import(DynamicDataSourceRegister.class)
 */
public class DynamicDataSourceRegister implements EnvironmentAware {

  private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceRegister.class);
  private Environment env;

  @Override
  public void setEnvironment(final Environment environment) {
    this.env = environment;
  }

  @Bean
  public DataSource defaultDataBase() throws Exception {
    Properties props = new Properties();
    props.put("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
    props.put("url", env.getProperty("spring.datasource.url"));
    props.put("username", env.getProperty("spring.datasource.username"));
    props.put("password", env.getProperty("spring.datasource.password"));
    this.setCommonJDBCProperties(props);
    DruidDataSource ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(props);
    // List<Filter> filters = new ArrayList<>();
    // filters.add(wallFilter());
    // ds.setProxyFilters(filters);
    return ds;
  }

  /**
   * private WallConfig wallConfig() { WallConfig wconfig = new WallConfig();
   * wconfig.setMultiStatementAllow(true); return wconfig; }
   *
   * private WallFilter wallFilter() { WallFilter wfilter = new WallFilter();
   * wfilter.setConfig(wallConfig()); return wfilter; }
   **/

  @Bean
  public DataSource baseDataSource() throws Exception {
    Properties props = new Properties();
    props.put("driverClassName", env.getProperty("baseData.datasource.driver-class-name"));
    props.put("url", env.getProperty("baseData.datasource.url"));
    props.put("username", env.getProperty("baseData.datasource.username"));
    props.put("password", env.getProperty("baseData.datasource.password"));
    this.setCommonJDBCProperties(props);
    DruidDataSource ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(props);
    // List<Filter> filters = new ArrayList<>();
    // filters.add(wallFilter());
    // ds.setProxyFilters(filters);
    return ds;
  }

  @Bean
  @Primary
  public DynamicDataSource dataSource(@Qualifier("defaultDataBase") DataSource defaultDataBase,
                                      @Qualifier("baseDataSource") DataSource baseDataSource) {
    Map<Object, Object> targetDataSources = new HashMap<>();
    targetDataSources.put(DataBaseType.DEFAULT, defaultDataBase);
    targetDataSources.put(DataBaseType.BASEDATA, baseDataSource);

    DynamicDataSource dataSource = new DynamicDataSource();
    dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
    dataSource.setDefaultTargetDataSource(defaultDataBase);// 默认的datasource设置为defaultDataBase

    return dataSource;
  }

  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactoryBean(DynamicDataSource ds) {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
    bean.setDataSource(ds);
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    try {
      bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
      return bean.getObject();
    } catch (Exception e) {
      log.error("sqlSessionFactory创建失败！");
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Bean
  public DataSourceTransactionManager transationManager(DynamicDataSource ds) throws Exception {
    return new DataSourceTransactionManager(ds);
  }

  private void setCommonJDBCProperties(Properties props) {
    props.put("initialSize", env.getProperty("spring.datasource.initialSize"));
    props.put("minIdle", env.getProperty("spring.datasource.minIdle"));
    props.put("maxActive", env.getProperty("spring.datasource.maxActive"));
    props.put("maxWait", env.getProperty("spring.datasource.maxWait"));
    props.put("validationQuery", env.getProperty("spring.datasource.validationQuery"));
    props.put("testOnBorrow", env.getProperty("spring.datasource.testOnBorrow"));
    props.put("testOnReturn", env.getProperty("spring.datasource.testOnReturn"));
    props.put("testWhileIdle", env.getProperty("spring.datasource.testWhileIdle"));
    props.put("timeBetweenEvictionRunsMillis", env.getProperty("spring.datasource.timeBetweenEvictionRunsMillis"));
    props.put("minEvictableIdleTimeMillis", env.getProperty("spring.datasource.minEvictableIdleTimeMillis"));
    props.put("removeAbandoned", env.getProperty("spring.datasource.removeAbandoned"));
    //props.put("removeAbandonedTimeOut", env.getProperty("spring.datasource.removeAbandonedTimeOut"));
    props.put("logAbandoned", env.getProperty("spring.datasource.logAbandoned"));
    props.put("poolPreparedStatements", env.getProperty("spring.datasource.poolPreparedStatements"));
    props.put("maxPoolPreparedStatementPerConnectionSize",
        env.getProperty("spring.datasource.maxPoolPreparedStatementPerConnectionSize"));
    props.put("filters", env.getProperty("spring.datasource.filters"));


  }
}