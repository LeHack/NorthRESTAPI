# Konfiguracja PostgreSQL
Instalacja bazy PostgreSQL na Linuxie (Ubuntu 16.04):
```sh
sudo apt-get update
sudo apt-get install postgresql postgresql-contrib
```

Logowanie na domyślne konto postgres:
```sh
sudo -i -u postgres
```
Dostęp do linii poleceń:
```sh
psql
```
Tworzenie użytkownika:
```sh
createuser -P -s -e admin
sudo adduser admin
```
Tworzenie bazy:
```sh
createdb northrest
```

Tworzenie schema:
```sh
create schema northrest
```

Nadawanie uprawnień:
```sh
grant all on schema northrest to admin
GRANT ALL ON ALL TABLES IN SCHEMA northrest TO admin
```
Wyświetlanie wszystkich tabel w bazie:
```sql
select * from information_schema.tables
```
```sql
select * from information_schema.tables
where table_schema = 'information_schema'
```