package com.css.cssbase.config;

import com.css.cssbase.base.shiro.JwtFilter;
import com.css.cssbase.base.shiro.ShiroRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description
 */
@Slf4j
@Configuration
public class ShiroConfig {

    @Value("${cssbase.shiro.excludeUrls}")
    private String excludeUrls;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String redisPassword;


    @Bean
    public Realm shiroRealm(){
        return new ShiroRealm();
    }

    /**
     * Filter Chain定义说明
     *
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 过滤器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        if(!StringUtils.isEmpty(excludeUrls)){
            String[] permissionUrl = excludeUrls.split(",");
            for(String url : permissionUrl){
                filterChainDefinitionMap.put(url,"anon");
            }
        }


        // 配置不会被拦截的链接 顺序判断 authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/login", "anon"); //登录接口排除
        filterChainDefinitionMap.put("/user", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/common/unauthorized/**", "anon");
        // 除了以上的请求外，其它请求都需要登录
        filterChainDefinitionMap.put("/**", "authc");
        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        filterChainDefinitionMap.put("/**", "jwt");
        // 未授权界面返回JSON
        shiroFilterFactoryBean.setUnauthorizedUrl("/sys/common/403");
        shiroFilterFactoryBean.setLoginUrl("/sys/common/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        //关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }

    /**
     * 下面的代码是添加注解支持
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        defaultAdvisorAutoProxyCreator.setAdvisorBeanNamePrefix("_no_advisor");
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    public RedisCacheManager redisCacheManager() {
        log.info("===============(1)创建缓存管理器RedisCacheManager");
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //redis中针对不同用户缓存(此处的id需要对应user实体中的id字段,用于唯一标识)
        redisCacheManager.setPrincipalIdFieldName("id");
        //用户权限信息缓存时间
        redisCacheManager.setExpire(200000);
        return redisCacheManager;
    }

    @Bean
    public RedisManager redisManager() {
        log.info("===============(2)创建RedisManager,连接Redis..URL= " + host + ":" + port);
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(getInt(port));
        redisManager.setTimeout(0);
        if (!StringUtils.isEmpty(redisPassword)) {
            redisManager.setPassword(redisPassword);
        }
        return redisManager;
    }

    private static int getInt(String s) {
        if (s == null || s == "") {
            return 0;
        }
        try {
            return (Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
