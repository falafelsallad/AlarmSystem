# This is the docker-compose file to bind the images pulled from docker hub. Images are pushed to docker hub repository.

version: '3.8'
services:
  db:
    image: falafelsallad/postgres:17
    restart: always
    environment:
      POSTGRES_USER: alarmsystem_admin
      POSTGRES_PASSWORD: alarmsystem_password
      POSTGRES_DB: AlarmSystemDB
    ports:
      - "5234:5432"
  AlarmSystem:
    image: falafelsallad/alarmsystemimg:v1
    ports:
      - "8080:8081"
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/AlarmSystemDB
