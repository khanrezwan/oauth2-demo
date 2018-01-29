# Oauth2-demo

#### Using  Spring boot and mongo db for oauth2

## Following these tutorials
 - [Tut1] : started with this. Partial implementation
 - [Tut2] : not so useful
 - [Documentation1] : Read Spring frame work official documentation
 - [Documentation2] : Mongo custom models for token stores
 - [Library1] : Spring security Mongo Library. Need to test it ou.
    
[Tut1]: <https://gigsterous.github.io/engineering/2017/03/01/spring-boot-4.html>
[Tut2]: <https://jugbd.org/2017/09/19/implementing-oauth2-spring-boot-spring-security/>
[Documentation1]: <http://projects.spring.io/spring-security-oauth/docs/oauth2.html>
[Documentation2]:<https://github.com/jeebb/oauth-demo/tree/master/sec-api/src/main/java/com/jeenguyen/demo/oauth/api/entities>
[Library1]: <https://github.com/caelwinner/spring-security-mongo>
## curl commands
```sh
$ curl -X POST --user 'cli1:secret' -d 'grant_type=password&username=rik&password=password' http://localhost:8080/oauth/token
```
### Todos
  - Check Bcrypt Encryption
  - Implement Mongo store or check JWT
  - Cache using Redis
 
