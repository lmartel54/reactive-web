

Spring Boot WebFlux + Thymeleaf + AngularJS + Server-Sent Events (SSE) + R2DBC
 

https://www.infoq.com/news/2017/12/servlet-reactive-stack/

https://docs.spring.io/spring/docs/5.1.9.RELEASE/spring-framework-reference/web-reactive.html#webflux

### Retrying Requests using Apache HttpClient 4 ###

http://www.javabyexamples.com/retrying-requests-using-apache-httpclient-4/


Hands-On Reactive Programming in Spring 5 ===> pack it


###############################################

docker pull postgres:latest
docker run --rm -d --name atrium-db-test -d -p 5432:5432 -e POSTGRES_USER="admin" -e POSTGRES_PASSWORD="password" -e POSTGRES_DB="atrium-test" -v $HOME/eclipse-postgres/volumes/postgres/test1:/var/lib/postgresql/data postgres
docker exec -it atrium-db-test psql -U admin atrium-test
\dt