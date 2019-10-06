# Getting Started

Description how to setup project with spring boot and postgresql [link](https://dzone.com/articles/bounty-spring-boot-and-postgresql-database).<br/>
Description how to deploy to aws [link](https://www.callicoder.com/deploy-host-spring-boot-apps-on-aws-using-elastic-beanstalk/).<br/>
Description how to connect pgAdmin to remote db [link](https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/USER_ConnectToPostgreSQLInstance.html).<br/>
For test the application use this [endpoint](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com).<br/>
Returning files with spring [link](https://www.baeldung.com/spring-controller-return-image-file)
Actuator endpoints<br/>
[auditevents](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/auditevents) – lists security audit-related events such as user login/logout. Also, we can filter by principal or type among others fields<br/>
[beans](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/beans) – returns all available beans in our BeanFactory. Unlike /auditevents, it doesn't support filtering<br/>
[conditions](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/conditions) – formerly known as /autoconfig, builds a report of conditions around auto-configuration<br/>
[configprops](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/configprops) – allows us to fetch all @ConfigurationProperties beans<br/>
[env](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/env) – returns the current environment properties. Additionally, we can retrieve single properties<br/>
[flayway](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/flyway) – provides details about our Flyway database migrations<br/>
[health](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/health) – summarises the health status of our application<br/>
[heapdump](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/heapdump) – builds and returns a heap dump from the JVM used by our application<br/>
[info](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/info) – returns general information. It might be custom data, build information or details about the latest commit<br/>
[liquibase](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/liquibase) – behaves like /flyway but for Liquibase<br/>
[logfile](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/logfile) – returns ordinary application logs<br/>
[loggers](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/loggers) – enables us to query and modify the logging level of our application<br/>
[metrics](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/metrics) – details metrics of our application. This might include generic metrics as well as custom ones<br/>
[prometheus](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/prometheus) – returns metrics like the previous one, but formatted to work with a Prometheus server<br/>
[scheduledtasks](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/scheduledtasks) – provides details about every scheduled task within our application<br/>
[sessions](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/sessions) – lists HTTP sessions given we are using Spring Session<br/>
[shutdown](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/shutdown) – performs a graceful shutdown of the application<br/>
[threaddump](http://pwsecondtry-env.wrujijgsjr.eu-central-1.elasticbeanstalk.com/actuator/threaddump) – dumps the thread information of the underlying JVM<br/>

#### Compilation class versions
49 = Java 5<br/>
50 = Java 6<br/>
51 = Java 7<br/>
52 = Java 8<br/>
53 = Java 9<br/>
54 = Java 10<br/>
55 = Java 11<br/>
56 = Java 12<br/>
57 = Java 13<br/>


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-developing-web-applications)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-endpoint)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#production-ready)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

