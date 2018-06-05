# Camel Hello World (Spring Version)

## Goals

- Discover the difference between the Java and the [Spring DSL](http://camel.apache.org/spring.html).

## How this project has been created

Generate a new Maven project using the `camel-archetype-spring` archetype:
```
mvn archetype:generate -DarchetypeGroupId=org.apache.camel.archetypes -DarchetypeArtifactId=camel-archetype-spring -DarchetypeVersion=2.21.1 -DinteractiveMode=false -DgroupId=fr.itix.camel-examples -DartifactId=hello-world-spring -Dversion=1.0-SNAPSHOT -Dpackage=fr.itix.camel.hello_world_spring
```

Enter the created directory:
```
cd hello-world-spring
```

List the created project structure:
```
$ find . -type f
pom.xml
src/main/resources/META-INF/spring/camel-context.xml
src/main/resources/log4j2.properties
src/data/message2.xml
src/data/message1.xml
```

For a simple hello world, we can do a bit of cleanup:
```
rm -rf src/data
```

Now, you should have a much smaller repository structure:
```
$ find . -type f
pom.xml
src/main/resources/META-INF/spring/camel-context.xml
src/main/resources/log4j2.properties
```

For a first "hello world" example, you can replace the content of `camel-context.xml` with:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://camel.apache.org/schema/spring http://camel.apache.org/schema/spring/camel-spring.xsd">
  <camelContext xmlns="http://camel.apache.org/schema/spring">
    <route>
      <from uri="timer:hello?period=1000"/>
      <log message="Hello, world !" />
    </route>
  </camelContext>
</beans>
```

## How to build it

First, make sure you are in the `hello-world-spring` directory and run:
```
mvn install
```

## How to use it

Once built, you can run it with:
```
mvn camel:run
```

If you followed the instructions, you should have something like this:
```
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building A Camel Spring Route 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO]
[INFO] --- camel-maven-plugin:2.21.1:run (default-cli) @ hello-world-spring ---
[INFO] You can skip tests from the command line using: mvn camel:run -Dmaven.test.skip=true
[INFO] Using org.apache.camel.spring.Main to initiate a CamelContext
[INFO] Starting Camel ...
Jun 04, 2018 8:23:12 PM org.springframework.context.support.ClassPathXmlApplicationContext prepareRefresh
INFO: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@488d7a8f: startup date [Mon Jun 04 20:23:12 CEST 2018]; root of context hierarchy
Jun 04, 2018 8:23:12 PM org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
INFO: Loading XML bean definitions from file [/Users/nmasse/git/perso/Camel-Examples/hello-world-spring/target/classes/META-INF/spring/camel-context.xml]
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Apache Camel 2.21.1 (CamelContext: camel-1) is starting
[pache.camel.spring.Main.main()] ManagedManagementStrategy      INFO  JMX is enabled
[pache.camel.spring.Main.main()] DefaultTypeConverter           INFO  Type converters loaded (core: 194, classpath: 1)
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Route: route1 started and consuming from: timer://hello?period=1000
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Total 1 routes, of which 1 are started
[pache.camel.spring.Main.main()] SpringCamelContext             INFO  Apache Camel 2.21.1 (CamelContext: camel-1) started in 0.224 seconds
Jun 04, 2018 8:23:13 PM org.springframework.context.support.DefaultLifecycleProcessor start
INFO: Starting beans in phase 2147483646
[l-1) thread #2 - timer://hello] route1                         INFO  Hello, world !
[l-1) thread #2 - timer://hello] route1                         INFO  Hello, world !
[ad #0 - CamelHangupInterceptor] MainSupport$HangupInterceptor  INFO  Received hang up - stopping the main instance.
[pache.camel.spring.Main.main()] MainSupport                    INFO  MainSupport exiting code: 0
[ad #0 - CamelHangupInterceptor] SpringCamelContext             INFO  Apache Camel 2.21.1 (CamelContext: camel-1) is shutting down
```

Note: you will have to press Ctrl-C to exit this Hello World example!

## References

- [Timer Component](http://camel.apache.org/timer.html)
- [The Log EIP](http://camel.apache.org/logeip.html)
