# JDBC

Compile: `javac -cp "lib/connector.jar" -d bin src/jdbc.java`

Maybe Windows Run: `java -cp "lib/connector.jar;bin" jdbc`

Mac: `java -cp "bin:lib/connector.jar" jdbc`

---

## NOTE If using docker

---
Run:

```bash
docker run --name 'my_sql' -d -p 3306:3306 mysql/mysql-server
```

Check logs for password and change root user password by

```sql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'tanay28';
```

Now Whitelist all IP Addresses for Root user by:

```sql
GRANT ALL PRIVILEGES ON your_database.* TO 'root'@'192.168.65.1' IDENTIFIED BY 'tanay28';
FLUSH PRIVILEGES;
```

```sql
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
```


Refer git logs for JDBC to Hibernete Migration
connectors for jdbc and hibernate are located or were located in /lib
