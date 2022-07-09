
# Elevator-Management


## Introduction:

A hotel chain requires the development of a new central elevator management platform running in the cloud. This platform will operate the button pressed on the inside and outside of the elevator of a given hotel. All hotels will have multiple floors and elevators; some are restricted to hotel personnel only.

## Task Details:

1. Operate the elevator of a given hotel (both inside and outside the elevator)
2. The elevator management platform needs to obtain the history (number of floors travelled)
in a given period for every elevator and Hotel.
3. Obtain a list of all elevators and all hotels managed by this platform
4. In case of any outages, the management platform must inform maintenance staff.
    
### Building from Source

The project is a spring boot maven project with modules:-

 - Spring web
 - H2
 - MySQl
 - Java 11

#### Build and deploy steps

 - $ mvn clean package -Pdev 
	 - Three spring profiles are configured
		 - dev
		 - acc
		 - production
			 - `spring.profiles.active=@spring.profiles.active@`
			 - `spring.config.activate.on-profile=dev`
			 - `spring.config.activate.on-profile=acc`
			 - 	`spring.config.activate.on-profile=production`

 - java -jar target/elevator-v1.jar 
 - docker build -t ajeetkrd1509/elevator . 
 - docker-compose up


# Endpoints

### Get Elevator list

##### Request

`GET /elevator/`

    curl -i -H 'Accept: application/json' http://localhost:8080/elevator

##### Response

    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sat, 09 Jul 2022 13:38:45 GMT
    [
	{
		"elevatorId": 1,
		"hotelId": 111,
		"currentFloor": 1,
		"numberOfFloors": 10,
		"destinationFloors": [],
		"pickupLocations": []
	},
	{
		"elevatorId": 4,
		"hotelId": 666,
		"currentFloor": 0,
		"numberOfFloors": 10,
		"destinationFloors": [],
		"pickupLocations": [
			5
		]
	}
	]
### Get Destination list

##### Request

`GET /destination`

    curl -i -H 'Accept: application/json' http://localhost:8080/destination

##### Response

    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sat, 09 Jul 2022 13:38:45 GMT
    [
	{
		"hotelId": 666,
		"elevatorId": 1,
		"elevatorFloor": 4,
		"id": 1
	},
	{
		"hotelId": 666,
		"elevatorId": 1,
		"elevatorFloor": 5,
		"id": 2
	},
	{
		"hotelId": 666,
		"elevatorId": 1,
		"elevatorFloor": 9,
		"id": 3
	}
	]
	
### Elevator Pickup Request
##### Request
    `GET /elevator/pickup`

    curl -i -H 'Accept: application/json' http://localhost:8080/elevator/pickup

##### Response

    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sat, 09 Jul 2022 13:38:45 GMT

	{
		"elevatorId": 1,
	}
### Elevator Destination Request
##### Request
`GET /elevator/destination`

    curl -i -H 'Accept: application/json' http://localhost:8080/elevator/destination

##### Response

    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sat, 09 Jul 2022 13:38:45 GMT

	{
		"elevatorId": 1,
	}

