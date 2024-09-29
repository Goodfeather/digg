

Start the container

./mvnw package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/user-service-jvm .
docker run -i --rm -p 8080:8080 quarkus/user-service-jvm  