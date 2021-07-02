#!/bin/sh

./gradlew clean build

# Build the docker image and tag as latest
docker --tag aakulanarendra/myretail:latest .

# Run the platform image
docker run -it --rm \
    --publish 8080:8080 \
    aakulanarendra/myretail:latest
