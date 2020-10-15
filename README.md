<h1 align="center"> User microservice </h1> <br>

<p align="center">
  User microservice description.
</p>


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [Testing](#testing)
- [API](#requirements)
- [Acknowledgements](#acknowledgements)




## Introduction

In short, the microservice architectural style is an approach to developing a single application as a suite of small services, 
each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API or event-driven.

This sample user service is adopting the microservice architecture style.

## Features
Description of features

* Include a list of users
* Get a particular user based on the id
* Save an user
* Delete an user based on the id


## Requirements
The application can be run locally or in a docker container, the requirements for each setup are listed below.

### Local
* [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/download.cgi)


### Docker
* [Docker](https://www.docker.com/get-docker)


### H2 inmemory database
* [H2 DB](https://www.h2database.com/html/main.html)

## Quick Start
Once you have the required environment setup, you can run the server in a docker container or on your local machine.

### Run Local
```bash
$ mvn spring-boot:run
```

Application will run by default on port `8080`

Configure the port by changing `server.port` in __application.yml__


### Run Docker

First build the image:
```bash
$ docker build -t user-service-spring-boot-docker .
```

When ready, run it:
```bash
$ docker run -p 8080:8080 user-service-spring-boot-docker
```

Application will run by default on port `8080`


## Testing
Integration tests will execute while building the application.


## API
API Reference with examples, or a link to a wiki or other documentation source.
In local, the endpoints can be access via below links.

* Get all users: GET (http://localhost:8080/api/userdetails) 
* Get single user: GET (http://localhost:8080/api/userdetails/10)
* Create new user: POST (http://localhost:8080/api/userdetails/restricted)
* Update existing user: PUT (http://localhost:8080/api/userdetails/restricted/1)
* Delete an existing user: DELETE (http://localhost:8080/api/userdetails/restricted/1)