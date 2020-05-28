
# Anagrams
This is a spring boot application which has following 2 endpoints.   
GET /anagrams/{s1} : Returns all possible anagrams of the string.   
GET /anagrams/{s1}/{s2} : Returns true if s1 and s2 is anagram, else return false.   

## Docker Repository
Docker Repo is [Here](https://hub.docker.com/r/hiralbest/anagrams).

## Run application using docker image
Install docker from [here](https://www.docker.com/). 
```
docker pull hiralbest/anagrams:production
docker run -p 8080:8080 hiralbest/anagrams:production
```

## Build and run locally
1. Install [Java](https://www.java.com/en/download/) and [Maven](https://maven.apache.org/).   
2. Make sure java and maven is in your classpath using following commands
```
java -version
mvn -version
```
3. Build jar file using maven. Following command will run all unit and integration tests, and build jar file in target folder.
```
mvn clean install
```
4. Run application
```
java -jar target/*.jar
```

## UI for testing API
You can access API endpoints using swagger from [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

