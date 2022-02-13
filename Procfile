release: chmod u+x release.sh && ./release.sh
web: java $JAVA_OPTS -jar ./applications/app-service/build/libs/app-service.jar --spring.config.location=deployment/$stage --server.port=$PORT $JAR_OPTS