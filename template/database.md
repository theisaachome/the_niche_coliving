## Access PostgreSQL as superuser

```sh
sudo -u postgres psql
```



If you don’t know the password, you can reset it:
```sh 
ALTER USER postgres PASSWORD 'newpassword';
```

## Create a new user (role)

Inside psql:

```sh 
CREATE USER the_niche_app WITH PASSWORD 'the_niche_11223344556677';
```
## Create a new database

```sh
CREATE DATABASE THENICHEDB_PRD OWNER the_niche_app;
```

## Grant privileges

To ensure the user has full access:
```sh
GRANT ALL PRIVILEGES ON DATABASE THENICHEDB_PRD TO the_niche_app;
```

## Connect to the new database

Exit psql and reconnect:
```sh 
psql -U myapp_user -d myapp_db -h localhost
```
