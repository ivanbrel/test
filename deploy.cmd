call mvn liquibase:dropAll
call mvn liquibase:update
call mvn clean
call mvn compile
call mvn deploy