mvn clean install

java -jar eureka-server/target/eureka-server.jar  &
java -jar api-gateway/target/api-gateway.jar &
java -jar auth-service/target/user-service.jar &
java -jar order-service/target/order-service.jar &
java -jar customer-service/target/customer-service.jar &
