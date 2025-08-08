@echo off
echo Building Docker image...
docker build -t falafelsallad/alarmsystemimg:v1 .
echo Docker image built successfully!

::just to make sure a fresh build: docker build --no-cache -t falafelsallad/alarmsystemimg:v1 .
