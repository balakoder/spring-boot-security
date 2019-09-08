# spring-boot-security

#### ** Before running the appication **

create database springbootsec;

and when application is up and running  i.e when tables gets created in database,execute these quries

insert into role values(uuid(),"ROLE_ADMIN");
insert into role values(uuid(),"ROLE_USER");

**  Access application on url ** 

http://localhost:8080/spring-boot-security/register

for creating first user

if you are accessing url like 

http://localhost:8080/spring-boot-security/abc

spring security always redirect you back to login url

http://localhost:8080/spring-boot-security/login

after entering usernme and password

**  Well done !!!! you are running spring boot with spring security !!! ** 
