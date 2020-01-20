# Social Network Project

* Spring and mongodb development of a Social Network project.
* [Project Specification](https://docs.google.com/document/d/1avKEbAbKpNTLZYVMpKu-oeMAN-CedXtj6D-W9G0pGbE/edit?usp=sharing)
* [Diagram model](https://www.lucidchart.com/documents/edit/3f65ed69-753c-462c-9e2a-ebe09afb4012/0_0?beaconFlowId=C0D59E289C0037D6)

## Installation

* Install Java and MongoDB

```bash
sudo apt-get update && apt-get upgrade
sudo apt-get install default-jdk
sudo apt-get install -y mongodb-org
```

* On terminal run

```bash
mongod
```

## Downloads

* Edit source code with [STS](https://spring.io/tools) IDE.
* Test application Endpoints with [Postman](https://www.getpostman.com/downloads/).

## Running App
```
   ./mvnw clean package
   ./mvn spring-boot:run
```
* On STS IDE, open this maven project and execute as a Spring Boot App.
* To easily explore and manipulate MongoDB data, install [Mongo Compass](https://www.mongodb.com/products/compass).
   
# Security
## Authentication Strategy:

```
 Based on JWT. Each req. needs a valid token to access the endpoints. All endpoints are blocked, except for login and user creation.
```

The system security was made using Spring Security, for all reqs. where it was created an interceptor that verifies the token and the user currently using the api.
  
  
## Authentication:

 ```
1) access the url base_url/user/login with a Json containing the Password and Username, with a post method.
2) Access the Response headers, and copy the token, on "Authorization:".
3) The token will be responsible to access the api.
4) Add the token to the Authorize on the application with the following pattern:
        Bearer <Token>
```
    
 # Performance
 
 ## Cache System Optimization
 The system offers a cache system, using the spring cache tool, **spring-boot-starter-cache**, that optimizes all the api requisitions.
  
 ## Performance Tests:
 All the tests where made with **JMeter**.

# Kubernetes
It was built a single image container on a **VM**, where it was able to deploy the app and test the api running on other machine.
### Install on your machine:
```
1. minikube
2. kubectl
3. dockertools
4. Oracle VM Virtualbox
```

## Running with Kubernetes
### 1. Build the app and the Docker image
```
eval $(minikube docker-env)
./mvnw clean package
docker build -t simple-crud:0.0.1 .

```
### 2. Run a Kubernetes deployment on the running Minikube cluster

```
kubectl run simple-crud --image simple-crud:0.0.1 --port 8080
kubectl expose deployment simple-crud --type=NodePort
```
### 3. Test the app
```
curl $(minikube service simple-crud --url)/users/kubernetes
```
### 4. Create Deployment and Service YAML files for future repeatable deployments
```
kubectl run simple-crud --image simple-crud:0.0.1 --port 8080 -o yaml --dry-run \
    > simple-crud-deployment.yaml
kubectl expose deployment simple-crud --type=NodePort -o yaml --dry-run > simple-crud-svc.yaml
```   
### 5. Delete resources created for simple-crud
```
kubectl delete all -l run=simple-crud
```


## License
[MIT](https://choosealicense.com/licenses/mit/)
