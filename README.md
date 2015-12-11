DB Benchmarks
--------------

This is a toy project used to benchmark the performance of a table of a PostgreSQL database.

Three different benchmarks have been realized:
- Insert with autocommit
- Insert with deferred commit
- Select with a WHERE condition on a primary key

Results are presented as output of the command line. For each benchmark is printed number of query executed, total execution time and mean, maximum, minimum execution time per single query.

### Prerequisites

The DB can be started in a Docker container using the followind commands:
```
cd db
docker build -t postgres_test .
docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres_test
```
N.B. In case you prefer to use an existing database you can use the init.sql file present inside db folder.

### Configuration
The only parameters to configure are inside db.properties file. You need to specify DB ip, port, name, user and password 

### Packaging and Run 
```
mvn clean install
java -jar target/db-benchmark.jar
```