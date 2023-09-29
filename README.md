# TODO Application
TODO Application that use GitHub OAuth Authorization for accessing our resource server.

## Instruction to run TODO Application
### Build and Run Application with Docker Compose
1. Run the application with just 1 line:
```
docker-compose up
```
2. The Server will be up on Port 8081
3. Accessible via http://127.0.0.1:8081

### Build Application
1. Build the Application
```
docker-compose build
```

### Test Application
#### Precondition
To run the JUnit test, bring up the Database with ```docker-compose up```.

1. Run the JUnit Test:
```
gradle test
```

## Interfaces
Authorized Bearer OAuth Token is required for invoking the interfaces

Summary | Endpoint | Request Method
--- | --- | --- 
List Out TODO Item | ```/``` | GET
Add TODO Item | ```/``` | POST
Delete TODO Item | ```/{itemId}/delete``` | POST
Mark TODO Item As Completed | ```/{itemId}/markAsComplete``` | POST

#### cURL Patterns
```
curl -H "Authorization: Bearer <OAuth Token>" http://127.0.0.1:8081/todo

curl -X POST -H "Authorization: Bearer <OAuth Token>" -H "Content-Type: application/json" -d "{\"itemTitle\": \"Title 1\", \"itemDesc\": \"Title Description 1\"}" http://127.0.0.1:8081/todo

curl -X POST -H "Authorization: Bearer <OAuth Token>" http://127.0.0.1:8081/todo/{itemId}/markAsComplete

curl -X POST -H "Authorization: Bearer <OAuth Token>" http://127.0.0.1:8081/todo/{itemId}/delete
```
