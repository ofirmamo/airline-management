FROM alpine:latest as source
WORKDIR /usr/code/app
COPY ./src ./src
COPY ./pom.xml ./

FROM maven:3.6.3-openjdk-8 as builder
WORKDIR /usr/compiled/app

COPY --from=source /usr/code/app/src ./src
COPY --from=source /usr/code/app/pom.xml ./
RUN mvn clean verify

FROM openjdk:8-alpine
COPY --from=builder /usr/compiled/app/target/airlines-management.jar ./

ENTRYPOINT ["java"]
CMD ["-jar", "/airlines-management.jar"]