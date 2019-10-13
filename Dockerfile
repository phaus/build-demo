FROM adoptopenjdk/openjdk11:alpine-slim

RUN mkdir /app

EXPOSE 9001

RUN apk --no-cache update && apk upgrade 

COPY . /home/gradle/src

WORKDIR /home/gradle/src

RUN ./gradlew build --no-daemon 

RUN cp /home/gradle/src/build/libs/productservice-0.1.0.jar /app/spring-boot-application.jar \
    && rm -Rf /home/gradle/src

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]
