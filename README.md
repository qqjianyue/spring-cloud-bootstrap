# spring-cloud-bootstrap
Base on below spring cloud bootstrap guide and code  
https://www.baeldung.com/spring-cloud-bootstrapping  
https://github.com/eugenp/tutorials/tree/master/spring-cloud/spring-cloud-bootstrap  
Upgrade spring cloud to Hoxton.SR1 and spring boot to 2.2.1  

# Demo 1 Config server + Eureka + Zull + 2 servies
Demonstrate basic configuration for those services  
Demonstrate ribbon client-side load balancer embedded in spring Zuul component 

![Image](http://i2.tiimg.com/710524/f1da4ac7f3227c1b.jpg)


## Steps
1. Import project into IDE such as Intellij or Eclipse  
2. Start ConfigApp.java  
Please note default configuration repository path is application-config in project root folder  
Intellij will replace ${user.dir} to spring-cloud-bootstrapping path, while in Eclipse it should be spring-cloud-bootstrapping/cloud-config folder, please replace ${user.dir} to correct path to load configuration properly  

>You should start Eureka(below step) as soon as possible to make config server registered  
 
3. Start EurekaApp.java  
4. Start GatewayApp.java  
5. Start RatingserviceApp.java  
6. Start BookserviceApp.java twice, one with 'peer1' profile, the other with 'peer2' profile  
7. Verification  
If all services start correctly, you should have services run in Intellij as below  
If any error, please check service log, such as port confliction, configuration path wrongness should make the problem  
![Image](http://i1.fuimg.com/710524/3ce3f9c33a2f8d19.png)  

Access below url several times, it should show you two port number of BookserviceApp in turn   
http://localhost:18080/bookservice/books/port  
