FROM openjdk:17
ADD build/libs/kampus-0.0.1.jar kampus-0.0.1.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","kampus-0.0.1.jar"]