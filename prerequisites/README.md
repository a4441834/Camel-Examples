# Pre-requisites for Camel

To run the provided examples, you would need:
- Java 8
- Maven

## Java

Camel requires Java 8 ([Java 9 is currently being tested and Java 10 is out-of-scope](http://camel.apache.org/camel-2200-release.html)).
Make sure your java version is correct by running:
```
$ java -version
java version "1.8.0_131"
Java(TM) SE Runtime Environment (build 1.8.0_131-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.131-b11, mixed mode)
```

### On MacOS

If you do not have Java installed, on MacOS you can use [Brew](https://brew.sh/) to install Java:
```
brew tap caskroom/versions
brew cask install java8
```

If you also have Java 10 installed, you can remove it using:
```
brew cask remove java
```

### On Fedora/CentOS/RHEL

If you have a Fedora/CentOS/RHEL, you can install OpenJDK:
```
sudo yum install java-1.8.0-openjdk-devel
```

## Maven

Make sure [Maven](https://maven.apache.org/) is installed:
```
$ mvn -version
Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-03T21:39:06+02:00)
Maven home: /usr/local/Cellar/maven/3.5.0/libexec
Java version: 1.8.0_131, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.13.4", arch: "x86_64", family: "mac"
```

If you do not have Maven installed, on MacOS you can install it with Brew:
```
brew install maven
```

On Fedora/CentOS/RHEL, you can install it from the optional repo:
```
sudo yum install maven
```
