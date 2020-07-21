package com.css.cssbase.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description
 */
@Configuration
@EnableConfigurationProperties(DruidProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DataSourceConfig {

    @Autowired
    private DruidProperties druidProperties;

    /**
     * 注册DruidServlet
     *
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean druidServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        // 登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册DruidFilter拦截
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean druidFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<String, String>();
        // 设置忽略请求
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(druidProperties.getUrl());
        datasource.setUsername(druidProperties.getUsername());
        datasource.setPassword(druidProperties.getPassword());
        datasource.setDriverClassName(druidProperties.getDriverClassName());

        //configuration
        datasource.setInitialSize(druidProperties.getInitialSize());
        datasource.setMinIdle(druidProperties.getMinIdle());
        datasource.setMaxActive(druidProperties.getMaxActive());
        datasource.setMaxWait(druidProperties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(druidProperties.getValidationQuery());
        datasource.setTestWhileIdle(druidProperties.isTestWhileIdle());
        datasource.setTestOnBorrow(druidProperties.isTestOnBorrow());
        datasource.setTestOnReturn(druidProperties.isTestOnReturn());
        datasource.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
        datasource.setConnectionProperties(druidProperties.getConnectionProperties());

        try {
            datasource.setFilters(druidProperties.getFilters());
        } catch (SQLException e) {
            //logger.error("druid configuration initialization filter", e);
        }

        return datasource;
    }

}
