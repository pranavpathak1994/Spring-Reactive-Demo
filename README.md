# Spring-Reactive-Demo

You can run this project with external tomcat and embedded tomcat of spring boot.

For the API

If you are running with embedded tomcat of spring boot then API as are below.

GET localhost:8080/users/ -  Get all the users from the database.

GET localhost:8080/users/userId/${userId} - Get user by user id. pass user id to the path variable.

GET localhost:8080/users/emailId/${emailId} -  Get user by user id. pass email id to the path variable.

POST localhost:8080/users/ - Create and update the user. Pass user object as data for the API call.

For the external Tomcat
 
add rootContext in API.
