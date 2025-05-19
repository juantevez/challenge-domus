SOLUTION


I designed the challenge as a hexagonal architecture, separating the adapters, ports, and controller, domain.


Adapter package
* in: with the subpackages DTO and Controller. There are the endpoint call to GET all the movies 
* out: with the subpackages DTO and Controller. There are the endpoint which calls the method, that executes the funcionalitu with the filters of the requirement. 

Config package 
* Rest template configuration

Domain


Service 
* There is a method with the funcionality that retrieves all the movies. 





Curl OK (tested locally)
curl --location 'http://localhost:8080/api/movies/directors?threshold=4'

Response
{"directors":["Martin Scorsese","Woody Allen"]}


Curl with threshold parameter incorrect (Value = 3.6)  (tested locally) 

curl --location 'http://localhost:8080/api/movies/directors?threshold=3.6'

Reponse Validation

{
"error": "The threshold parameter must be an integer"
}