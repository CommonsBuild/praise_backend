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
./gradlew build -PspringProfiles=local
