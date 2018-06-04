package fr.itix.camel.hello_world_java;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {
    public void configure() {
        from("timer:hello?period=1000").log("Hello, world !");
    }
}
