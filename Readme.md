# Argonauts

## Stack
- Gradle
- Java 11
- Mysql
- Spring Boot

## Port
- default port 8080

## Endpoints
- POST 		 /argonauts
- GET			/argonauts
- GET			/argonauts/{id}
- PUT			/argonauts/{id}
- DELETE		/argonauts/{id}

## Model

```json
student {
  "id": "Long",
  "name": "String",
  "identityNumber": "String",
  "studentSince": "LocalDate",
  "birthDate": "LocalDate",
  "cellphone": "Long",
  "email": "String",
  "postalCode": "String",
  "addressLine": "String",
  "monthlyBill": "Double",
  "lastPayDate": "LocalDate"
}
```
