# Praise System Backend

Steps for running backend locally: 

### 1. Add Ethereum address for admin access
Edit `/Backend/src/main/resources/application-local.yaml`

### 2. Terminal 1, run PostgreSQL
`docker compose -f docker-compose-local.yml up`

### 3. Terminal 2, run backend
`./gradlew bootRun --args='--spring.profiles.active=local'`


- Backend runs at port 8088
- Swagger API documentation at http://localhost:8088/swagger-ui.html


## CI/CD
`source .env`		To load env variables
`DATABASE_HOST=localhost:5432 ./gradlew clean build -x test -PspringProfiles=local`  			to build the packages (skipping tests for now) NOTE: database must be running on localhost or this will fail
`docker build --build-arg JAR_FILE=Backend-0.0.1-SNAPSHOT.jar  -t praise/backend Backend`		to build the backend (substitute jar file with correct name if you change the version in gradle.build)
`docker compose -f docker-compose-local.yml up`