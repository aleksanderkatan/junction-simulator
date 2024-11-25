#!/usr/bin/env bash

echo "The application uses Java 21."
echo "Building the application."

if ./gradlew build; then
    echo "Build succeeded."
    echo "Running the application."
    ./gradlew run --args="$*"
else
    echo "Build failed."
    exit 1
fi
