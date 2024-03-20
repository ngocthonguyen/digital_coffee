# Digital Coffee

Repository for Digital Coffee platform:
- Eureka discovery service
- API Gateway
- Auth/Users service
- Customer service
- Shop service
- Menu service
- Order service
- Workflow engine
- Notification service
- Commons-lib library

## Solution design
The solution design and other details are outlined in this document: [Solution design](solution-design/README.md)

## Clone from Github
```commandline
git clone https://github.com/ngocthonguyen/digital_coffee.git
```

## Build project
```commandline
cd digital_coffee
mvn clean install
```

## Run PostgresSQL and Solace docker
```commandline

```

## Run microservices

// TODO: Use single command
```commandline
mvn spring-boot:run -pl dc-eureka
mvn spring-boot:run -pl dc-api-gateway
mvn spring-boot:run -pl dc-user
mvn spring-boot:run -pl dc-customer
mvn spring-boot:run -pl dc-shop
mvn spring-boot:run -pl dc-order
mvn spring-boot:run -pl dc-menu
```

## Test with Postman
The application can be tested via this Postman collection: [DC_collection](digital_coffee.postman_collection.json) 