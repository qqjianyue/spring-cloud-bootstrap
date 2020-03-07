# spring-cloud-bootstrap

Intend to demo spring cloud usage by some demo code and instruction. There'll be one branch for each demo

Base on [spring cloud bootstrap guide](https://www.baeldung.com/spring-cloud-bootstrapping "spring cloud bootstrap guide") and [code](https://github.com/eugenp/tutorials/tree/master/spring-cloud/spring-cloud-bootstrap "code")  
Upgrade spring cloud to Hoxton.SR1 and spring boot to 2.2.1  

------------


## Demo 1 Bootstrap - Config server + Eureka + Zull + 2 servies
Demonstrate basic configuration for those services  
Demonstrate ribbon client-side load balancer embedded in spring Zuul component 

![Image](http://i2.tiimg.com/710524/f1da4ac7f3227c1b.jpg)<br/>


### Steps
[codebase](https://github.com/qqjianyue/spring-cloud-bootstrap/tree/bootstrap "codebase")
1. Import project into IDE such as Intellij or Eclipse  
2. Start ConfigApp.java  
Please note default configuration repository path is application-config in project root folder  
Intellij will replace ${user.dir} to spring-cloud-bootstrapping path, while in Eclipse it should be spring-cloud-bootstrapping/cloud-config folder, please replace ${user.dir} to correct path to load configuration properly  

>You should start Eureka(below step) as soon as possible to make config server registered  
 
3. Start EurekaApp.java  
4. Start GatewayApp.java  
5. Start RatingserviceApp.java  
6. Start BookserviceApp.java twice, one with 'peer1' profile, the other with 'peer2' profile  

------------

Alternately we can run mvn command to startup all service, navigate to project root folder:  
mvn spring-boot:run -f cloud-config/pom.xml  
timeout /t 10 /nobreak  
mvn spring-boot:run -f cloud-eureka/pom.xml  
mvn spring-boot:run -f cloud-gateway/pom.xml  
mvn spring-boot:run -f cloud-bookservice/pom.xml -Dspring.profiles.include=peer1  
mvn spring-boot:run -f cloud-bookservice/pom.xml -Dspring.profiles.include=peer2  

------------

7. Verification  
If all services start correctly, you should have services run in Intellij as below  
If any error, please check service log, such as port confliction, configuration path wrongness should make the problem  
![Image](http://i1.fuimg.com/710524/3ce3f9c33a2f8d19.png)  

Access below url several times, it should show you two port number of BookserviceApp in turn   
http://localhost:18080/bookservice/books/port  

------------


### Review flexible configuration in config server
Refer to [quick start](https://cloud.spring.io/spring-cloud-config/reference/html/#_quick_start "quick start")  

/{application}/{profile}[/{label}]  
/{application}-{profile}.yml  
/{label}/{application}-{profile}.yml  
/{application}-{profile}.properties  
/{label}/{application}-{profile}.properties  

The 1st endpoint is more powerful, it's default endpoints for spring cloud component to retrieve config of several config name and profile combination, extended usage as below:  

/{appliction}[,{application}...]/{profile}[,{profile}...][/{label}]

But it cannot support several labels at one call
#### File based repository config
Base on above config server characteristics, I prefer below file based repository config:  

Native:  
file:${user.dir}/application-config,file:${user.dir}/application-config/{profile},file:${user.dir}/application-config/{profile}/{application},file:${user.dir}/application-config/{profile}/{application}/{label},file:${user.dir}/application-config/{label},file:${user.dir}/application-config/{label}/{application},file:${user.dir}/application-config/{label}/{application}/{profile}  

Git:  
application-config,application-config/{profile},application-config/{profile}/{application},application-config/{profile}/{application}/{label},application-config/{label},application-config/{label}/{application},application-config/{label}/{application}/{profile}  

##### Config folder purpose by design:  
/: Global level configuration, refer to [how to share configuration for all application](https://cloud.spring.io/spring-cloud-config/reference/html/#_sharing_configuration_with_all_applications "how to share configuration for all application")  
  
/{profile}: Common profiled configuration shared across different environment  

/{profile}/{application}: Common profiled configuration of particular application shared across different environment 
 
/{label}:  Use label as environment identifier, such as prod for production, env1 for UAT environment 1, env2 for UAT 
environment 2 etc, so under this folder to define environment level common configuration  
/{label}/{application}: Application level setting of particular environment  

/{label}/{appication}/{profile}: Application leve setting of particular profile under specific environment