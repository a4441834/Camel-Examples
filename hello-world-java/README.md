# Camel Hello World

## Goal


## How this project has been created

Generate a new Maven project using the `camel-archetype-java` archetype:
```
mvn archetype:generate -DarchetypeGroupId=org.apache.camel.archetypes -DarchetypeArtifactId=camel-archetype-java -DarchetypeVersion=2.21.1 -DinteractiveMode=false -DgroupId=fr.itix.camel-examples -DartifactId=hello-world-java -Dversion=1.0-SNAPSHOT -Dpackage=fr.itix.camel.hello_world_java
```

Enter the created directory:
```
cd hello-world-java
```

List the created project structure:
```
$ find . -type f
pom.xml
src/main/resources/log4j2.properties
src/main/java/fr/itix/camel/hello_world_java/MainApp.java
src/main/java/fr/itix/camel/hello_world_java/MyRouteBuilder.java
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
src
src/main/resources/log4j2.properties
src/main/java/fr/itix/camel/hello_world_java/MainApp.java
src/main/java/fr/itix/camel/hello_world_java/MyRouteBuilder.java
```

For a first "hello world" example, you can replace the content of `MyRouteBuilder.java` with:
```
package fr.itix.camel.hello_world_java;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {
    public void configure() {
        from("timer:hello?period=1000").log("Hello, world !");
    }
}
```

## How to build it

First, make sure you are in the `hello-world-java` directory and run:
```
mvn install
```

## How to use it

Once built, you can run it with:
```
mvn exec:java
```

If you followed the instructions, you should have something like this:
```
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building A Camel Route 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- exec-maven-plugin:1.6.0:java (default-cli) @ hello-world-java ---
[ello_world_java.MainApp.main()] DefaultCamelContext            INFO  Apache Camel 2.21.1 (CamelContext: camel-1) is starting
[ello_world_java.MainApp.main()] ManagedManagementStrategy      INFO  JMX is enabled
[ello_world_java.MainApp.main()] DefaultTypeConverter           INFO  Type converters loaded (core: 194, classpath: 0)
[ello_world_java.MainApp.main()] DefaultCamelContext            INFO  StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
[ello_world_java.MainApp.main()] DefaultCamelContext            INFO  Route: route1 started and consuming from: timer://hello?period=1000
[ello_world_java.MainApp.main()] DefaultCamelContext            INFO  Total 1 routes, of which 1 are started
[ello_world_java.MainApp.main()] DefaultCamelContext            INFO  Apache Camel 2.21.1 (CamelContext: camel-1) started in 0.211 seconds
[l-1) thread #2 - timer://hello] route1                         INFO  Hello, world !
[l-1) thread #2 - timer://hello] route1                         INFO  Hello, world !
[l-1) thread #2 - timer://hello] route1                         INFO  Hello, world !
[l-1) thread #2 - timer://hello] route1                         INFO  Hello, world !
[l-1) thread #2 - timer://hello] route1                         INFO  Hello, world !
^C[ad #0 - CamelHangupInterceptor] MainSupport$HangupInterceptor  INFO  Received hang up - stopping the main instance.
[ad #0 - CamelHangupInterceptor] DefaultCamelContext            INFO  Apache Camel 2.21.1 (CamelContext: camel-1) is shutting down
[ello_world_java.MainApp.main()] MainSupport                    INFO  MainSupport exiting code: 0
[ad #0 - CamelHangupInterceptor] DefaultShutdownStrategy        INFO  Starting to graceful shutdown 1 routes (timeout 300 seconds)
```

Note: you will have to press Ctrl-C to exit this Hello World example!

## References

- [Camel Archetypes](http://camel.apache.org/camel-maven-archetypes.html)
- [Timer Component](http://camel.apache.org/timer.html)
