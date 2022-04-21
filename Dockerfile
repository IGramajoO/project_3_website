
FROM --platform=linux/amd64 openjdk:17
VOLUME /tmp
COPY target/project_3_website-0.0.1-SNAPSHOT.jar  project_3_website-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/project_3_website-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080:8080
