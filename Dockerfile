FROM openjdk:8
ADD target/blog.jar blog.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","blog.jar"]