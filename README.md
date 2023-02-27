# save-my-petrol

Find the cheapest petrol stations in Spain.

*Data obtained from: https://sedeaplicaciones.minetur.gob.es*

## Features

- Find a petrol station by id [X]
- Find petrol stations by simple filter [X]
- Find products on petrol stations by simple filter [X]
- Add subscription to a target product [X]
- Generate matches based on subscriptions [X]
- Add authentication and authorization []
- Add different sorting filters []
- Find petrol stations in a route defined by two points []

## Improvements

- Add useful testing [X]
- Add validations [X]
- Use query bus/command bus to decouple modules
  (e.g. currently ```PetrolStationRepository``` is being used in products module) []
- Use visitor pattern to avoid exposing internal object structure
  (see: [printers-instead-of-getters](https://www.yegor256.com/2016/04/05/printers-instead-of-getters.html)) []

## How to contribute

Contributes are welcome!

Make a PR resolving one feature/improvement or create new feature/improvement task

## Deploy in a DEV environment

Define these environment variables:

```bash
APP_ENV=dev
APP_PORT

MONGO_DB_PORT
MONGO_ROOT_USER
MONGO_ROOT_PASSWORD
MONGO_DATABASE
MONGO_EXPRESS_PORT
```

### Without docker

Execute:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### With mongo in docker

Execute:

```bash 
docker-compose --env-file ${env_file} up -d mongodb && mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## With mongo and app in docker

Execute:

```bash
docker-compose --env-file ${env_file} up -d
```