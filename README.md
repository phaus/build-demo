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
BUILD SUCCESSFUL in 22s
…
Successfully built 00de8f500328
Successfully tagged a19/user/build-demo:latest
```

You can now see the images:

```bash
$ docker images | grep $USER/build-demo
a19/user/build-demo        latest              10292cd1035d        2 minutes ago       512MB
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

## Using Multi-Staged Builds

```bash
$ docker build -f Dockerfile-multi -t a19/$USER/build-demo-multi  .
…
BUILD SUCCESSFUL in 20s
…
Successfully built adad7203c481
Successfully tagged a19/root/build-demo-multi:latest
```

Compare the size of both images:

```bash
$ docker images | grep $USER/build-demo
a19/user/build-demo-multi   latest              adad7203c481        57 seconds ago      158MB
a19/user/build-demo         latest              10292cd1035d        11 minutes ago      512MB
```

Test if the new Image does work:

```bash
$ docker run a19/$USER/build-demo-multi:latest

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.6.RELEASE)

2019-10-13 21:52:50.126  INFO 1 --- [           main] com.innoq.product.Application            : Starting Application on 73468d32d42c with PID 1 (/app/spring-boot-application.jar started by root in /)
2019-10-13 21:52:50.132  INFO 1 --- [           main] com.innoq.product.Application            : No active profile set, falling back to default profiles: default
2019-10-13 21:52:51.820  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 9001 (http)
…
2019-10-13 21:52:52.810  INFO 1 --- [           main] com.innoq.product.Application            : Started Application in 3.381 seconds (JVM running for 4.139)
```

## Using a specific Build Image

```bash
$ docker build -f Dockerfile-builder -t a19/build-demo/builder:latest .
…
Successfully built ad62d12483db
Successfully tagged a19/build-demo/builder:latest
```

Check if the images was build:

```bash
$ docker images | grep a19/build-demo/builder
a19/build-demo/builder        latest              e9460a98e29b        11 seconds ago      402MB
```

Building the Application with the newly created Build-Image:

```bash
$ docker build -f Dockerfile-with-builder -t a19/$USER/build-demo-with-builder  .
…
BUILD SUCCESSFUL in 9s
…
Successfully built adad7203c481
Successfully tagged a19/user/build-demo-with-builder:latest
```

Compare the image sizes:

```bash
$ docker images | grep a19/$USER/build-demo-
a19/root/build-demo-with-builder   latest              adad7203c481        18 minutes ago      158MB
a19/root/build-demo-multi          latest              adad7203c481        18 minutes ago      158MB
```
