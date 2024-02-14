# Tech Challenge [Soft Design]

This application implements an API REST to:

- Create, Edit and Remove Books
- Rent Books
- Search Books by name and author

Validations are applied to ensure that a rented Book will not be rented again, modified or deleted.

There are Unit Tests implemented for the controller and business layers.

The controller tests ensure that: 
- Serializaton/deserialization is working properly
- Business services are being used correctly given correct and incorrect input

## Generating Backend artifact

cd application-dir/softdesign-interview-backend
mvn clean install

The artifact will be generated on target/soft-design.war

## Running Backend

- Run WildFly:
cd wildfly-installation-dir
./bin/standalone.sh

- Deploy application:
cd application-dir/softdesign-interview-backend
mvn clean install wildfly:deploy

## Running Frontend

cd application-dir/softdesign-interview-frontend
ng serve --open