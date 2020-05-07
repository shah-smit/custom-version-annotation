# Spring Version Handler Annotation

![Java CI with Maven](https://github.com/shah-smit/custom-version-annotation/workflows/Java%20CI%20with%20Maven/badge.svg)

### Features
There are two end-point
- GET /{version}/greeting
- GET /{version}/greeting/{message}
- POST /{version/greeting
request body sample:
```json5
{
  "message": "Hello World"
}
```

There are two versions `1.0` and `2.0`

---

### Documentation

Step 1, create the class that will be versioned, then ensure to put annotation of `@VersionHandler` this interface accepts a String as such you can put value such as `1.0` and `2.0`

Step 2, create sub method that are needs to do the processing, ensure to annotate them with`@VersionGetHandler` this tells that this method is for All HTTP Get methods.

Step 3, In your rest controller, you will have use VersionHandlerController that is a class with one method `findVersionHandler` which has the following signature:

```
public ResponseEntity findVersionHandler(String endpoint, String version, HttpMethod httpMethod, Object... args)
```

Parameters Description:

`version` This field denotes the use of version and matches `@VersionHandler` version value. 

`httpMethod` This field denotes the use of HTTP Methods such as GET, POST, PUT, and it will be mapped to equivalent annotation such as `@VersionGetHandler`

`endpoint` This field is the last check to drill down to only one method, as such if there are more than one method, the annotation logic will not work. 

#### Sample Code

File: Controller.java

```java
@RestController
public class HomeController {

    @Autowired
    VersionHandlerController versionController;

    @GetMapping("${bean.get.endpoint}")
    public ResponseEntity getGreeting(@PathVariable String version){
        return versionController.findVersionHandler("${bean.get.endpoint}", version, HttpMethod.GET);
    }
}
```
As you can see there is a `versionController` that is designed to do the processing for you. You should always call `findVersionHandler` method in the `versionController` with the needed parameters.

In this example, I am passing the endpoint (its always good to put the enpoint in properties to ensure all the places where this endpoint is used - its consistent), followed by version that you will be either getting from path, query, header, or body. It should be following the format that is stated in the documentation above.

Finally, the `HttpMethod` again it should be also correct, as the method will be mappted to its equivalent Handler annotation.

*handler.java

```java
@Component
@VersionHandler(version = "1.0")
public class HomeHandler {

    @VersionGetHandler(endpoint = "${bean.get.endpoint}")
    public String getGreeting(){
        return "Greeting from v1.0";
    }
}
```

First its needs to have `@Component` or else the logic would not be able to locate the bean. Secondly, it needs to have `@VersionHandler` annotation 
as it is the another important annotation that will help with narrowing the search for the particular method to execute. 

After which, in the method, should be annotated with `@VersionGetHandler` or `@VersionPostHandler` based on HttpMethod and the endpoint should match the one stated in the controller abvoe.
 
---

### Examples

For the endpoint `/1.0/greeting` the project will return `Greeting from v1.0`

For the endpoint `/2.0/greeting` the project will return `Greeting from v2.0`

For the endpoint `/1.0/greeting/Hi from` the project will return `Hi from v1.0`

For the endpoint `/2.0/greeting/Hello from` the project will return `Hello from v2.0`

---

### Others

#### Pre-Commit Scripts

```bash
#!/bin/sh

echo "Precommiting script started"

mvn clean test

nohup mvn spring-boot:run &

sleep 10

cd karate/

mvn test -Dtest=testRunner

cd ..

kill $(lsof -ti:8081)

echo "Successfully Completed"
```
