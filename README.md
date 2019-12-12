
# About
Test | Standard CRUD operations on company shares

# Stack
- Java
- Maven
- Spring (Boot, MVC, Data)
- MySQL
- QueryDSL
- Hibernate Envirs
- MapStruct

# Execute application
Put your MySQL properties in <i><b>application.yml</b></i>
```
username: YOUR_USERNAME
password: YOUR_PASSWORD
```
For insreting test data in database change the value to <i><b>true</b></i>
```
sharedb: false
```
Run application
```
$ mvn spring-boot:run
```



# How to use
```
http://localhost:9080/api/v1/
```

| Method | Url | Decription |
| ------ | --- | ---------- |
| POST    |/api/v1/login | Get authentication token |
| POST    |/api/v1/registration | Registrate new user (ADMIN) |
| GET    |/api/v1/public/share  | Get all company shares with predicates, sorting and pagination |
| GET    |/api/v1/public/share/company/{usreou}  | Get all company shares by company usreou (ЄДРПОУ) |
| GET    |/api/v1/public/share/{id}  | Get company share by its id (without history) |
| GET    |/api/v1/private/share | Get all company shares with predicates, sorting and pagination |
| POST    |/api/v1/private/share | Create new share |
| GET    |/api/v1/private/share/company/{usreou} | Get shares with history by company usreou |
| GET    |/api/v1/private/share/{id} | Get share with its history changing |
| PUT    |/api/v1/private/share/{id} | Update existing share |



# Examples

- **Getting token**
```
http://localhost:9080/api/v1/login
```
Request
```
{
	"login":"admin",
	"password":"admin"
}
```
Response
```
{
    "login": "admin",
    "token": "Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzGm0..."
}
```

- **Create new share**
```
http://localhost:9080/api/v1/private/share
```
Request
```
{
	"comment":"new share",
	"capitalSize":"7000",
	"usreou":"546654",
	"amount":"7",
	"faceValue":"3400.00",
	"stateDutyPaid":"14000.00"
}
```
Response
```
{
    "id": 5,
    "comment": "new share",
    "capitalSize": 7000,
    "usreou": 546654,
    "amount": 7,
    "totalFaceValue": 23800.00,
    "faceValue": 3400.00,
    "stateDutyPaid": 14000.00,
    "releaseDate": "2019-12-12T21:04:10.771+0000"
}
```

 - **Get all share (with public data) with pagination (also it can be predicate e.g. "?amount=4&faceValue=300.00")**
```
http://localhost:9080/api/v1/public/share?size=2&page=0
```
Response
```
{
    "content": [
        {
            "id": 1,
            "usreou": 5101,
            "amount": 6,
            "totalFaceValue": 6951.3000,
            "faceValue": 462.4200,
            "releaseDate": "2019-12-12T18:20:18.000+0000"
        },
        {
            "id": 2,
            "usreou": 4350,
            "amount": 9,
            "totalFaceValue": 6906.8400,
            "faceValue": 570.5700,
            "releaseDate": "2019-12-12T18:20:18.000+0000"
        }
    ],
    ... other information about pagination
```
