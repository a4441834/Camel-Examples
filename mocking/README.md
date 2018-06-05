# Mocking Services in Camel

## Goals

In this example, we will mock the an API ([api.github.com](https://api.github.com/)).
This is very useful if the API is billed per usage or not available.

## The mocked service

We will mock the following API calls (you can try them in a terminal):
```
curl -D - https://api.github.com/
curl -D - https://api.github.com/users/nmasse-itix
curl -D - https://api.github.com/users/lbroudoux
curl -D - https://api.github.com/users/xxx-dummy-missing-xxx
curl -D - https://api.github.com/orgs/microcks
curl -D - https://api.github.com/orgs/xxx-dummy-missing-xxx
```

## How this project has been created

Generate a new Maven project using the `camel-archetype-java` archetype:
```
mvn archetype:generate -DarchetypeGroupId=org.apache.camel.archetypes -DarchetypeArtifactId=camel-archetype-spring -DarchetypeVersion=2.21.1 -DinteractiveMode=false -DgroupId=fr.itix.camel-examples -DartifactId=mocking -Dversion=1.0-SNAPSHOT -Dpackage=fr.itix.camel.mocking
```

As usual, enter the created directory and remove the provided examples:
```
cd mocking
rm -rf src/data
```

You should have such repository structure:
```
$ find . -type f
pom.xml
src/main/resources/META-INF/spring/camel-context.xml
src/main/resources/log4j2.properties
```

Since we will use the `jetty` Camel component to listen for HTTP requests,
according to [the documentation](http://camel.apache.org/jetty.html), you will
have to add the specified dependency to your `pom.xml`. In the `<dependencies>`
section, add:
```xml
<dependency>
  <groupId>org.apache.camel</groupId>
  <artifactId>camel-jetty</artifactId>
</dependency>
```

Replace the content of `camel-context.xml` with [this file](src/main/resources/META-INF/spring/camel-context.xml).

## How to test it

Each time you changed something in your `camel-context.xml`, you will have
to re-run Camel with:
```
mvn install camel:run
```

If you followed the instructions, you should have something like this:
```
[INFO] --- camel-maven-plugin:2.21.1:run (default-cli) @ mocking ---
[INFO] You can skip tests from the command line using: mvn camel:run -Dmaven.test.skip=true
[INFO] Using org.apache.camel.spring.Main to initiate a CamelContext
[INFO] Starting Camel ...
Jun 05, 2018 11:08:24 AM org.springframework.context.support.ClassPathXmlApplicationContext prepareRefresh
INFO: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@2311c071: startup date [Tue Jun 05 11:08:24 CEST 2018]; root of context hierarchy
Jun 05, 2018 11:08:24 AM org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
INFO: Loading XML bean definitions from file [/Users/nmasse/git/perso/Camel-Examples/mocking/target/classes/META-INF/spring/camel-context.xml]
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Apache Camel 2.21.1 (CamelContext: camel-1) is starting
[pache.camel.spring.Main.main()] ManagedManagementStrategy      INFO  JMX is enabled
[pache.camel.spring.Main.main()] DefaultTypeConverter           INFO  Type converters loaded (core: 194, classpath: 8)
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
[pache.camel.spring.Main.main()] log                            INFO  Logging initialized @4192ms to org.eclipse.jetty.util.log.Slf4jLog
[pache.camel.spring.Main.main()] Server                         INFO  jetty-9.4.6.v20170531
[pache.camel.spring.Main.main()] ContextHandler                 INFO  Started o.e.j.s.ServletContextHandler@24af2e09{/,null,AVAILABLE}
[pache.camel.spring.Main.main()] AbstractConnector              INFO  Started ServerConnector@3ac5035f{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
[pache.camel.spring.Main.main()] Server                         INFO  Started @4290ms
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Route: route1 started and consuming from: jetty:http://0.0.0.0:8080?matchOnUriPrefix=true
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Route: route2 started and consuming from: direct://get_orgs_microcks
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Route: route3 started and consuming from: direct://get_users_nmasse_itix
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Route: route4 started and consuming from: direct://get_users_lbroudoux
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Route: route5 started and consuming from: direct://not_found
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Route: route6 started and consuming from: direct://get_metadata
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Total 6 routes, of which 6 are started
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Apache Camel 2.21.1 (CamelContext: camel-1) started in 0.397 seconds
Jun 05, 2018 11:08:25 AM org.springframework.context.support.DefaultLifecycleProcessor start
INFO: Starting beans in phase 2147483646
```

From another terminal you can verify that the mock is working and matches the mocked API:
```
$ curl -D - http://localhost:8080/
HTTP/1.1 200 OK
Content-Type: application/json
Accept: */*
breadcrumbId: ID-nmasse-OSX-local-1528189705427-0-9
User-Agent: curl/7.54.0
Transfer-Encoding: chunked
Server: Jetty(9.4.6.v20170531)

{
  "organization_url": "https://api.github.com/orgs/{org}",
  "user_url": "https://api.github.com/users/{user}"
}
```

You can try with the other URLs:
```
curl -D - http://localhost:8080/users/nmasse-itix
curl -D - http://localhost:8080/users/lbroudoux
curl -D - http://localhost:8080/users/xxx-dummy-missing-xxx
curl -D - http://localhost:8080/orgs/microcks
curl -D - http://localhost:8080/orgs/xxx-dummy-missing-xxx
```

On the other console, you should see the requests coming in the log:
```
[               qtp183376222-18] route1                         INFO  GET /
[               qtp183376222-19] route1                         INFO  GET /users/nmasse-itix
[               qtp183376222-20] route1                         INFO  GET /users/lbroudoux
[               qtp183376222-21] route1                         INFO  GET /users/xxx-dummy-missing-xxx
[               qtp183376222-22] route1                         INFO  GET /orgs/microcks
[               qtp183376222-23] route1                         INFO  GET /orgs/xxx-dummy-missing-xxx
```

Note: you will have to press Ctrl-C to exit this example!

## References

- [Jetty](http://camel.apache.org/jetty.html)
- [HTTP Component](http://camel.apache.org/http.html) (message header names)
- [Header Names for Spring DSL](https://camel.apache.org/maven/current/camel-core/apidocs/org/apache/camel/Exchange.html#HTTP_PATH)
- [Match on URI prefix](http://camel.apache.org/how-do-i-let-jetty-match-wildcards.html)
- [Simple Expressions Syntax](http://camel.apache.org/simple.html)
- [Constant Syntax](http://camel.apache.org/constant.html)
- [The Log EIP](http://camel.apache.org/logeip.html)
