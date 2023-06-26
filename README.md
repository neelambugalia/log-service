# Log Service

This service takes any kind of log and saves it in database.
There are 3 components:
- Server
- Database
- Tester

## Setting up server
- Install docker on your machine.
- Compile and generate jar for log-service by running
    ```
    mvn clean install
    ```
- Create `log-network` to run server, db, tester to be run in same network.
  ```
    docker network create --driver=bridge --subnet=192.168.11.0/24 --gateway=192.168.11.1 log-network
  ```
- CD to log-service project base directory
- Build docker image
  ```
    docker compose build
  ```
- Run server and database
  ```
    docker compose up
  ```

### Running tester
- CD to tester script directory 
  ```
  cd \src\main\resources\scripts
  ```
- Build tester docker image
  ```
  docker compose build
  ```
- Run tester
  ```
  docker compose up
  ```

### APIs

- GET `baseurl/api/logs`
  - returns all the logs saved
  - Response schema:

    ```json
    [
      {
        "id": 1,
        "logId": 34,
        "userId": "Neelam",
        "eventName": "LOGIN",
        "unixTs": 187353784
      }
    ]
    ```

- GET `baseurl/api/logs/{logId}`    
  - returns the log with given logId
  - Response schema:

    ```json
    {
    "id": 1,
    "logId": 34,
    "userId": "Neelam",
    "eventName": "LOGIN",
    "unixTs": 187353784
    }
    ```

- POST `baseurl/api/logs`    
  - creates a log in the database    
  - Request schema:

      ```json
      {
        "logId": 1,
        "userId": "Neelam",
        "eventName": "LOGIN",
        "unixTs": 187353784
      }
      ```

  - Response schema:

      ```json
      {
          "id": 1,
          "logId": 34,
          "userId": "Neelam",
          "eventName": "LOGIN",
          "unixTs": 187353784
      }
      ```

- DELETE `baseurl/api/logs/{logId}`    
  - deletes the log with given logId 
