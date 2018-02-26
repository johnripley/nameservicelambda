# nameservicelambda

### Instructions on moving our Spring Boot based nameservice into an Lambda function on AWS  

#### Creating the functions  

 1. Navigate to AWS Lambda
 2. Create Function
  - name = NameServiceGender
  - runtime = Java 8
  - role = create new custom role, name = name-service-lambda-role
 3. Create Function
  - NameService/Configuration/Function code/
  - Upload ZIP or jar - upload nameservicelabmmda/target/nameservicelambda-2016.0.1.jar
  - Handler = org.johnripley.nameservicelambda.NameServiceGenderHandler::handleRequest
  - Basic Settings = increase timeout to 15s, increase size to 512MB
 4. Save
 5. Test
 6. Create test Event
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

 7. Repeat for NameServiceStats
 8. Create Function
  - name = NameServiceStats
  - runtime = Java 8
  - role = use existing role = name-service-lambda-role
 9. Create Function
  - NameService/Configuration/Function code/
  - Upload ZIP or jar - upload nameservicelabmmda/target/nameservicelambda-2016.0.1.jar
  - Handler = org.johnripley.nameservicelambda.NameServiceStatsHandler::handleRequest
  - Basic Settings = increase timeout to 15s, increase size to 512MB
 10. Save
 11. Test
 12. Create test Event
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

#### Creating the API  
    
 1. Navigate to API Gateway
 2. Create new API
  - name-service			
  - create resource (gender)
  - create method (GET) on gender
    - integration type = lambda function
    - region = US-EAST1
    - Lambda Function = NameServiceGender
    - Method Request
    - Settings - RequestValidator = Validate query string parameters and headers
    - URL Query String Parameters
      - name
      - year
    - Integration Request
      - Body Mapping Templates
      - When there are no templates defined (recommended)
      - Add mapping template (appliction/json)
      - click application/json (template box will appear below)
      - use 
      - { "name": "$input.params('name')", "year": "$input.params('year')"}
	
 3. Go back to GET method on left side
  - test (name = Madison, year = 2004)
  - Request: /gender?name=Madison&year=2004
   Status: 200
      Latency: 81 ms
      Response Body

      {
	"name": "Madison",
	"year": 2004,
	"gender": "F",
	"confidence": 0.99
      }

   
      

      
      
      
      
      
      
      
    
  
  
  

      

      