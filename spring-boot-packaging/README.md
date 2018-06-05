# Spring Boot packaging for Camel

## Goals

Spring Boot enables you to package your Camel routes along with any required
dependency. A "fat jar" is produced and can be run with just a `java -jar /path/to/app.jar`.

In this example, we will package the Camel routes as a Spring Boot application.

## How this project has been created

Generate a new Maven project using the `camel-archetype-spring-boot` archetype:
```
mvn archetype:generate -DarchetypeGroupId=org.apache.camel.archetypes -DarchetypeArtifactId=camel-archetype-spring-boot -DarchetypeVersion=2.21.1 -DinteractiveMode=false -DgroupId=fr.itix.camel-examples -DartifactId=spring-boot-packaging -Dversion=1.0-SNAPSHOT -Dpackage=fr.itix.camel.spring_boot_packaging
```

By default, the `camel-archetype-spring-boot` archetype creates a sample route
using the Java DSL. In this example, we will use the Spring DSL, so you can remove
the two following classes:
```
rm src/main/java/fr/itix/camel/spring_boot_packaging/MySpringBean.java
rm src/main/java/fr/itix/camel/spring_boot_packaging/MySpringBootRouter.java
```

Create a `camel` directory in `src/main/resources`:
```
mkdir src/main/resources/camel/
```

Create an `hello-world.xml` file in the newly created `camel` directory with
this content:
```
<routes xmlns="http://camel.apache.org/schema/spring">
  <route id="hello-world">
    <from uri="timer:hello?period=1000"/>
    <log message="Hello, world !" />
  </route>
</routes>
```

## Try it !

Each time you changed something in your `camel-context.xml`, you will have
to re-run Camel with:
```
mvn package spring-boot:run
```

If you followed the instructions, you should have something like this:
```
[INFO] --- spring-boot-maven-plugin:1.5.12.RELEASE:run (default-cli) @ spring-boot-packaging ---

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::       (v1.5.12.RELEASE)

2018-06-05 12:40:51.475  INFO 24580 --- [           main] f.i.c.s.MySpringBootApplication          : Starting MySpringBootApplication on nmasse-OSX.local with PID 24580 (/Users/nmasse/git/perso/Camel-Examples/spring-boot-packaging/target/classes started by nmasse in /Users/nmasse/git/perso/Camel-Examples/spring-boot-packaging)
2018-06-05 12:40:51.477  INFO 24580 --- [           main] f.i.c.s.MySpringBootApplication          : No active profile set, falling back to default profiles: default

[...]

2018-06-05 12:40:54.527  INFO 24580 --- [           main] o.a.camel.spring.boot.RoutesCollector    : Loading additional Camel XML routes from: classpath:camel/*.xml
2018-06-05 12:40:55.006  INFO 24580 --- [           main] o.a.camel.spring.boot.RoutesCollector    : Loading additional Camel XML rests from: classpath:camel-rest/*.xml
2018-06-05 12:40:55.020  INFO 24580 --- [           main] o.a.camel.spring.SpringCamelContext      : Apache Camel 2.21.1 (CamelContext: MyCamel) is starting
2018-06-05 12:40:55.021  INFO 24580 --- [           main] o.a.c.m.ManagedManagementStrategy        : JMX is enabled
2018-06-05 12:40:55.190  INFO 24580 --- [           main] o.a.camel.spring.SpringCamelContext      : StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
2018-06-05 12:40:55.205  INFO 24580 --- [           main] o.a.camel.spring.SpringCamelContext      : Route: hello-world started and consuming from: timer://hello?period=1000
2018-06-05 12:40:55.207  INFO 24580 --- [           main] o.a.camel.spring.SpringCamelContext      : Total 1 routes, of which 1 are started
2018-06-05 12:40:55.207  INFO 24580 --- [           main] o.a.camel.spring.SpringCamelContext      : Apache Camel 2.21.1 (CamelContext: MyCamel) started in 0.187 seconds
2018-06-05 12:40:55.263  INFO 24580 --- [           main] b.c.e.u.UndertowEmbeddedServletContainer : Undertow started on port(s) 8080 (http)
2018-06-05 12:40:55.271  INFO 24580 --- [           main] f.i.c.s.MySpringBootApplication          : Started MySpringBootApplication in 4.173 seconds (JVM running for 7.038)
2018-06-05 12:40:56.222  INFO 24580 --- [- timer://hello] hello-world                              : Hello, world !
2018-06-05 12:40:57.216  INFO 24580 --- [- timer://hello] hello-world                              : Hello, world !
2018-06-05 12:40:58.216  INFO 24580 --- [- timer://hello] hello-world                              : Hello, world !
2018-06-05 12:40:59.217  INFO 24580 --- [- timer://hello] hello-world                              : Hello, world !
```

Note: you will have to press Ctrl-C to exit this Hello World example!

## Packaging

As you might have noticed, by default this archetype generates a fat jar:
```
$ ls -lh target/*.jar
-rw-r--r--  1 nmasse  staff    21M Jun  5 12:40 target/spring-boot-packaging-1.0-SNAPSHOT.jar
```

When ready, you can copy the fat jar on another server and run it:
```
java -jar target/spring-boot-packaging-1.0-SNAPSHOT.jar
```

## References

- [Camel Archetypes](http://camel.apache.org/camel-maven-archetypes.html)
- [Spring Boot support for Camel](http://camel.apache.org/spring-boot.html)
