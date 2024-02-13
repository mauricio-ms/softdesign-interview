# Tech Challenge [Soft Design]

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