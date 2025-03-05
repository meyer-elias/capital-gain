FROM gradle:8-jdk-21-and-22 AS temp_build_image
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build || return 0
COPY . .
RUN gradle clean build

# actual container
FROM openjdk:21-jdk-slim
ENV ARTIFACT_NAME=capital-gain-1.0-SNAPSHOT.jar
ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME
COPY --from=temp_build_image $APP_HOME/build/libs/$ARTIFACT_NAME .

ENTRYPOINT sh -c 'exec java -jar $ARTIFACT_NAME'