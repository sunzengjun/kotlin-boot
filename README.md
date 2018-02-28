## 项目说明 ##   
项目主要由spring boot+kotlin搭建而成，集成了mybatis，可以用java和kotlin这2种语言进行服务器端的业务开发。适合java和kotlin结合的项目或者从java到kotlin的逐步切换。
本项目是一种新的尝试，搭建过程遇到的2个主要问题：    
1、kotlin数据类与mybatis的对应问题      
2、kotlin对象序列化问题        
本项目给了对应解决方案。   

## 启动说明 ##   
1、创建数据库，数据表见others目录下begin.sql    
2、修改base\src\main\resources下application.yml文件中的数据库配置，包括ip、端口、database名、用户名、密码    
3、启动base\src\main\src\main\java\com\szj\hello下Application文件（或者maven install后运行对应jar包）    
4、访问接口。（端口也配置在application.yml文件中）    

## 项目介绍 ##   
### 版本介绍 ###     
spring-boot使用1.5.8版本，kotlin使用1.1.1版本     
### 模块说明 ###     
conf:项目配置      

dao：数据实体    

exception:异常类    

service：服务层    

utils：工具   

web：控制器层   

## 作者信息  
孙曾军 链家网web开发工程师

