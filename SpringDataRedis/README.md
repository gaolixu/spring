Request 1
http://localhost:8080/save
The browser returns Done.

Request 2
http://localhost:8080/findall
spring-data-redis-crud-result-findall

Request 3
http://localhost:8080/find?id=2
spring-data-redis-crud-result-findbyid
 
Request 4.1
http://localhost:8080/uppercase?id=2
The browser returns Done.

Request 4.2: check update
http://localhost:8080/find?id=2
spring-data-redis-crud-result-update

Request 5.1
http://localhost:8080/delete?id=3
The browser returns Done.

Request 5.2: check delete
http://localhost:8080/findall