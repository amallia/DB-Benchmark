DB Benchmarks
--------------

This is a toy project used to benchmark the performance of a table of a PostgreSQL database.

Three different benchmarks have been realized:
- Insert with autocommit
- Insert with deferred commit
- Select with a WHERE condition on a primary key

When execution starts whole benchmarks are attempted, 1000 queries per benchmarks.

Results are presented as output of the command line. For each benchmark is printed number of query executed, total execution time (in milliseconds) and mean, maximum, minimum execution time per single query.

### Prerequisites

The DB can be started in a Docker container using the followind commands:
```
cd db
docker build -t postgres_test .
docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres_test
```
N.B. In case you prefer to use an existing database you can use the init.sql file present inside db folder.

### Configuration
The only parameters to configure are inside db.properties file. You need to specify DB ip, port, user and password. If use docker leave default user 'postgres'.

### Packaging and Run 
```
mvn clean install
java -jar target/db-benchmark.jar
```

Tests
-------
Automatic tests are executed on query builder and prepared statement creation.
We don't provide full tests since this is an example project.
