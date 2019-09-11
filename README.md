# SpotHero Challenge Problem

### Api Endpoints:
 - PUT `/rates`
 -- Set the rate configuration object as the json payload that is sent as the request body
 - GET `/rates/current`
 -- Get the current rate configuration object as json
 - GET `/rates/lookup`
 -- Takes a `startDate` and `endDate` datetime query parameter, each formatted as ISO-8601.
 -- Returns a rate as an integer or "unavailable" if no rate is specified in the configuration or the input dates are invalid.
 - POST `/rates/lookup`
 -- Takes a json payload with `startDate` and `endDate` formatted as ISO-8601, otherwise same as the GET request with the same path.

### Build, Test, and Run
You need git, java 8, a kotlin compiler, and maven. To build and test: 

    $ git clone <clone url>
    $ cd sh-challenge
    $ mvn clean package

And finally, to run the application:

    mvn spring-boot:run
    
#### Docker run
First build the docker image, this can be done with maven using the `dockerfile-maven-plugin`.

    $ mvn install dockerfile:build

Then run the image:

    $ docker run --name kh-sh-challenge spothero-challenge/sh-challenge

In another terminal you can then curl the application in the container

    $ # First get the ip
    $ IMAGE_IP=$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' kh-sh-challenge)
    $ curl http://${IMAGE_IP}:8080/rates/current | jq .

### Usage
By default the application runs on port 8080. The swagger api documentation is available at: [/v2/api-docs](http://localhost:8080/v2/api-docs),
and if you prefer a swagger ui, that's available at [swagger-ui.html](http://localhost:8080/swagger-ui.html). Change
`localhost` to `${IMAGE_IP}` if using docker.

To get the current rates set on the application:

    $ curl localhost:8080/rates/current | jq .

To set a different rate configuration:

    $ curl -X PUT -H "Content-Type: application/json" -d @configuration.json localhost:8080/rates/

To lookup a rate for a specified date and time:

    $ curl "http://localhost:8080/rates/lookup?endDate=2015-07-01T12%3A00%3A00-05%3A00&startDate=2015-07-01T07%3A00%3A00-05%3A00"

Or To lookup a rate using a json payload:

    $ echo '{"startDate":"2015-07-01T07:00:00-05:00","endDate":"2015-07-01T12:00:00-05:00"}' > payload.json
    $ curl -X POST -H "Content-Type: application/json" -d @payload.json localhost:8080/rates/lookup