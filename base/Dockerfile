FROM maven:3.8.4-openjdk-17
MAINTAINER abanoubiskander
RUN ["git", "clone", "https://github.com/Limitless0/MapGeneratorJava/"]
WORKDIR MapGeneratorJava/base
RUN ["mvn", "clean", "validate", "compile", "package", "verify", "install"]
WORKDIR map-generator
RUN ["mvn", "org.openjfx:javafx-maven-plugin:0.0.8:jlink", "-X"]
ENTRYPOINT target/image/bin/MapGenLauncher


