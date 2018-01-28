# features-api
This api allows to get, create, update and delete features. 
Features can be globally enabled/disabled and can be additionally enabled/disabled per user.

## Build and deploy

In project root folder run gradle clean build deploy

## Run

### Using Docker (recommended)

In project root folder run docker-compose up </br>
After start up the api will be accessible at http://localhost:8000/task/api/v1/features

### Using local servlet container and MySql instance

1. Create user 'task' with password 'task' to be owner of a new schema called 'task'.
2. Execute sql script at sql/schema.sql
3. Deploy the artifact located in webapps folder in your running servlet container.

