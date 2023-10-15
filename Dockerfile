FROM maven:3.9-sapmachine-21 AS builder
WORKDIR /usr/src/nzuguem-github-bot
COPY . ./
RUN mvn package -DskipTests -Dquarkus.package.type=uber-jar

FROM sapmachine:21-jre-ubuntu
COPY --from=public.ecr.aws/awsguru/aws-lambda-adapter:0.7.1 /lambda-adapter /opt/extensions/lambda-adapter
WORKDIR app/
COPY --from=builder /usr/src/nzuguem-github-bot/target/nzuguem-github-bot-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]