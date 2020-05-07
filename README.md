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

### Usage

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


---

### Examples

For the endpoint `/1.0/greeting` the project will return `Greeting from v1.0`

For the endpoint `/2.0/greeting` the project will return `Greeting from v2.0`

For the endpoint `/1.0/greeting/Hi from` the project will return `Hi from v1.0`

For the endpoint `/2.0/greeting/Hello from` the project will return `Hello from v2.0`

---

