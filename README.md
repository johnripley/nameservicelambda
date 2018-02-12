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


  
  
  

      

      