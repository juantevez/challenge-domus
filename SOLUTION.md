SOLUTION


I designed the challenge as a hexagonal architecture, separating by adapters, ports, and domain.


Adapter package
* in: with the subpackages DTO and Controller. There are the endpoint call to GET all the movies 
* out: with the subpackages DTO and Controller. There are the endpoint which calls the method, that executes the funcionalitu with the filters of the requirement. 

Port package
Interfaces, for the input/output processing

Config package 
* Rest template configuration

Domain
* Models package, for data information classes.
* port packages - 

Service 
* Concret classes, that implements the method declared in the port interfaces. 





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