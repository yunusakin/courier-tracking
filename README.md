# Courier Tracking 

This is a RESTful web application built with Spring Boot that tracks the geolocation of couriers and logs their activities when they enter the vicinity of specific stores. 
The application allows querying the total travel distance of couriers.


## Features

- Log courier and store when any courier enters a radius of 100 meters from stores.
- Reentries to the same store's circumference within 1 minute are not counted as "entrance".
- Query the total travel distance of couriers.

## Technologies Used

- Java 11
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5

## Getting Started

To get started with the project, follow these steps:

1. Clone the repository:
   ```shell
   git clone https://github.com/yunusakin/courier-tracking.git

2. Navigate to the project directory:
   ```shell
   cd courier-tracking
   
3. Build the project using Maven:
   ```shell 
   mvn clean install
4. Run the application:
   ```shell 
   mvn spring-boot:run
5. Run unit tests:
   ```shell 
   mvn test
6. The application will be accessible at http://localhost:8182

# API Endpoints
## Courier Endpoints
1. GET  http://localhost:8182/api/courier/list
   * Get All Couriers
   * Example Response
   ```json 
   {
    "success": true,
    "response": {
        "courierList": [
            {
                "name": "Courier A",
                "token": "52adc33a-0260-11ee-be56-0242ac120002"
            },
            {
                "name": "Courier B",
                "token": "611f52bc-0260-11ee-be56-0242ac120002"
            }
        ]
    }
2. POST  http://localhost:8182/api/courier/create
   * Create Courier
   * Example Request
      ```json 
     {
        "name": "COURIER Z"
     }
     
   * Example Response
     ```json
     {
       "success": true,
       "response":{
          "name": "COURIER Z",
          "token": "5b1d964e-99e4-46d3-93a0-0e69a9e7bb73"
      }
     }

## Store Endpoints
1. GET  http://localhost:8182/api/store/list
   * Get All Stores
   * Example Response
    ``` json 
    {
    "success": true,
    "response": {
        "stores": [
            {
                "name": "Ataşehir Store",
                "longitude": 29.1244229,
                "latitude": 40.9923307
            },
            {
                "name": "Novada Store",
                "longitude": 29.1161293,
                "latitude": 40.986106
            },
            {
                "name": "Beylikdüzü Store",
                "longitude": 28.6552262,
                "latitude": 41.0066851
            },
            {
                "name": "Ortaköy Store",
                "longitude": 29.0210292,
                "latitude": 41.055783
            },
            {
                "name": "Caddebostan Store",
                "longitude": 29.0630908,
                "latitude": 40.9632463
            }
        ]
     }
2. POST  http://localhost:8182/api/store/create
   * Create Store
   * Example Request
      ```json 
     {
     "name": "Test Store",
     "longitude": 29.1244229,
     "latitude": 40.9923307
     }

   * Example Response
     ```json
     {"success": true,"status": 200 }

## Courier Tracking Endpoints
* GET  http://localhost:8182/api/tracking/courier/{courierToken}/distance
   * Get the total travel distance for a specific courier.
   * Example Response
     ```json 
     {
      "success": true,
       "response": {
        "distance": 1849.6467940433715
       }
     }
* POST  http://localhost:8182/api/tracking/courier/log
   * Log courier position when entering a store radius.
   * Example Request
      ```json 
     {
     "time": "2023-06-01T17:09:42.411",
     "courier": {
          "name": "COURIER B",
          "courierToken": "611f52bc-0260-11ee-be56-0242ac120002"
     },
     "latitude": 40.9921200,
     "longitude": 29.1244229
     }


   * Example Response
     ```json
     {"success": true,"status": 200}

# Data Model
## Courier
 * id: Long (auto-generated)
 * name: String
 * token: String
## Store 
   * id: Long (auto-generated)
   * name: String
   * latitude: double
   * longitude: double
## CourierLog
  * id: Long (auto-generated)
  * time: LocalDateTime
  * latitude: double
  * longitude: double
  * courier: Courier
  * store: Store
# Data Access
  * For accessing stored data in H2 Database http://localhost:8182/ct/h2-panel 

