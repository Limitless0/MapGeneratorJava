FROM centos
MAINTAINER abanoubiskander
RUN yum -y install libX11
FROM maven:3.8.4-openjdk-17
RUN ["git", "clone", "https://github.com/Limitless0/MapGeneratorJava/"]
WORKDIR MapGeneratorJava/base
RUN ["mvn", "clean", "validate", "compile", "package", "verify", "install"]
WORKDIR map-generator
ENTRYPOINT ["mvn", "org.openjfx:javafx-maven-plugin:0.0.8:run", "-X"]

