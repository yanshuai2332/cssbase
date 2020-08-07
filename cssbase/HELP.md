# cssbase

## 基本框架
基础框架：Spring Boot 2.1.3.RELEASE

持久层框架：Mybatis-plus_3.1.2

安全框架：Apache Shiro 1.4.0，Jwt_3.7.0

数据库连接池：阿里巴巴Druid 1.1.10

缓存框架：redis

日志打印：logback

### 开发历史记录
2020/7/7： 已完成mybatis-plus的集成

2020/7/8： 已完成logback的集成

2020/7/9： 已完成shiro+jwt的集成，token在线刷新不掉线机制

2020/7/10： 已完成统一返回数据，统一处理异常，UserContext管理

2020/7/22： 完成枚举类和数据库自动转换，EnumTypeHandler

2020/7/24： 解决过滤器抛出异常ExceptionHandler无法捕获的问题

### TODO
1.把shiro中魔法值移到配置文件管理，aop记录日志

2.人员组织管理业务模块开发

3.权限管理业务模块开发。