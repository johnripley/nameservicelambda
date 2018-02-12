# nameservicelambda

Go to AWS Lambda
Create Function
  name = NameServiceGender
  runtime = Java 8
  role = create new custom role, name = name-service-lambda-role
  Create Function
NameService/Configuration/Function code/
  Upload ZIP or jar - upload nameservicelabmmda/target/nameservicelambda-2016.0.1.jar
  Handler = org.johnripley.nameservicelambda.NameServiceGenderHandler::handleRequest
  Basic Settings = increase timeout to 15s, increase size to 512MB
Save
Test
  Create test Event
    Gender - input =
      {
	"name": "John",
	"year": "1969"
      }

Get ready from some trial and error

      {
	  "name": "John",
	  "year": 1969,
	  "gender": "M",
	  "confidence": 0.995
      }

Repeat for NameServiceStats

Create Function
  name = NameServiceStats
  runtime = Java 8
  role = use existing role = name-service-lambda-role
  Create Function
NameService/Configuration/Function code/
  Upload ZIP or jar - upload nameservicelabmmda/target/nameservicelambda-2016.0.1.jar
  Handler = org.johnripley.nameservicelambda.NameServiceStatsHandler::handleRequest
  Basic Settings = increase timeout to 15s, increase size to 512MB

Save
Test
  Create test Event
    StatsMadison1950 - input =
    {
      "name": "Madison",
      "year": "1950"
    }

    {
      "name": "Madison",
      "year": 1950,
      "countMale": 34,
      "countFemale": 0,
      "totalMale": 1790871,
      "totalFemale": 1713259
    }

API Gateway

Create new API
  name-service			
Create resource (gender)
  create method (GET) on gender
    integration type = lambda function
    region = US-EAST1
    Lambda Function = NameServiceGender
    Method Request
      Settings - RequestValidator = Validate query string parameters and headers
      URL Query String Parameters
	name
	year
    Integration Request
      Body Mapping Templates
	When there are no templates defined (recommended)
	Add mapping template (appliction/json)
	click application/json (template box will appear below)
	use 
	  { "name": "$input.params('name')", "year": "$input.params('year')"}
	
    Go back to GET method on left side
      test (name = Madison, year = 2004)

      Request: /gender?name=Madison&year=2004
      Status: 200
      Latency: 81 ms
      Response Body

      {
	"name": "Madison",
	"year": 2004,
	"gender": "F",
	"confidence": 0.99
      }

   
      

      
      
      
      
      
      
      
    
  
  
  

      

      