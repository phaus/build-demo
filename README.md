# build-demo

This Demo shows different ways of building software in Docker.

## Building in Docker

```bash
$ docker run -it -v $PWD:/home/gradle/src adoptopenjdk/openjdk11:alpine-slim /bin/sh
# cd /home/gradle/src/
/home/gradle/src # ls
Dockerfile    build.gradle  gradle        gradlew       gradlew.bat   src   gradlew      gradlew.bat
/home/gradle/src # ./gradlew build --no-daemon
…
BUILD SUCCESSFUL in 25s
3 actionable tasks: 3 executed
```

## Building with Docker

```bash
$ docker build -t a19/$USER/build-demo .
…
Successfully built 00de8f500328
Successfully tagged a19/user/build-demo:latest
```


Starting the freshly build container:

```bash
$ docker run a19/$USER/build-demo:latest

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.6.RELEASE)
 …
 2019-10-13 21:40:14.235  INFO 1 --- [           main] com.innoq.product.Application            : Started Application in 3.489 seconds (JVM running for 4.263)
 ```