package com.css.cssbase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description
 */
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DruidProperties {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private int initialSize;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;

    private String validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean poolPreparedStatements;

    private int maxPoolPreparedStatementPerConnectionSize;

    private String filters;

    private String connectionProperties;
}
