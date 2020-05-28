FROM openjdk:6
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
# CMD ["echo", "Hello from Dockerfile"]
CMD ["java", "-jar", "./target/HelloWorld-0.1.jar"]