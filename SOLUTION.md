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

