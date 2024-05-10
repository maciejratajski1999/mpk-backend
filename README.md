# Mapa utrudnień MPK - backend

Ten projekt jest oparty na poradniku dostępnym na stronie [Quarkus][1].

## Wymagania

### Windows

- System operacyjny: Windows
- [Quarkus CLI][2]
- [Chocolatey Software Manager][3] (do instalacji Quarkus CLI)
- [JDK 22][4] (dodane do PATH w systemie Windows)
- [Docker Desktop dla Windows][5]

### Ubuntu

- System operacyjny: Ubuntu
- [SDKMAN CLI][6]
  ```curl -s "https://get.sdkman.io" | bash```
- [JDK 22][4] (dodane do PATH w systemie Ubuntu)
  ```sdk install java 22-graal```
- Zainstaluj Quarkus przez sdkman
  ```sdk install quarkus```
- [Docker CLI na Ubuntu][7]


## Uruchomienie kontenera PostgreSQL

Uruchom kontener PostgreSQL za pomocą poniższej komendy:

```bash
docker run --hostname=147010e54885 --mac-address=02:42:ac:11:00:02 --env=POSTGRES_DB=quarkus_test --env=POSTGRES_USER=quarkus_test --env=POSTGRES_PASSWORD=quarkus_test --env=PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/lib/postgresql/14/bin --env=GOSU_VERSION=1.14 --env=LANG=en_US.utf8 --env=PG_MAJOR=14 --env=PG_VERSION=14.1-1.pgdg110+1 --env=PGDATA=/var/lib/postgresql/data --volume=/var/lib/postgresql/data -p 5432:5432 --restart=no --runtime=runc -d postgres:14.1
```

Docker powinien automatycznie pobrać potrzebne dependencies z internetu.

### Tworzenie tabeli w PostgreSQL
Uruchom terminal kontenera i wprowadź poniższe komendy:
```bash
psql -h localhost -U quarkus_test -d quarkus_test
create table routes
(
    route_id   int  not null
        primary key,
    route_type int  null,
    valid_from date null
);
  
\dt
```
Jeżeli nie stworzysz potrzebnych tablic, Quarkus spróbuje je stworzyć.

## Uruchomienie aplikacji Quarkus

1. **Windows**: Uruchom klienta Docker na Windows na domyślnym porcie.
   **Ubuntu**: Uruchom klienta Docker na Ubuntu na domyślnym porcie.
2. Następnie użyj komendy `quarkus dev` / `sudo quarkus dev` w folderze z projektem.
-  jeśli otrzymujesz `sudo: quarkus: command not found`, spróbuj:
   `sudo -E env "PATH=$PATH" quarkus dev`

### Endpoints API

- `GET http://localhost:8080/routes`
- `GET http://localhost:8080/routes/{routeId}`
- `POST http://localhost:8080/routes`
- 
### Testy
Po uruchomieniu kontenera PostgreSQL, uruchom aplikację w trybie deweloperskim. Test powinien dodać trasę “Testowa”. Możesz to sprawdzić na endpointcie http://localhost:8080/routes.

### Dodawanie trasy przez endpoint POST
Spróbuj dodać trasę za pomocą poniższej komendy:

```bash
curl -X POST http://localhost:8080/routes -H 'Content-Type: application/json' -d '{"routeId": "exampleRouteId", "routeType": 1, "validFrom": "2024-05-10"}'
```
### Weryfikacja dodanej trasy
Zweryfikuj, czy trasa została poprawnie dodana, odwiedzając http://localhost:8080/routes/exampleRouteId.


[1]: https://quarkus.io/guides/getting-started-dev-services
[2]: https://quarkus.io/guides/cli-tooling
[3]: https://chocolatey.org/
[4]: https://www.oracle.com/java/technologies/downloads/
[5]: https://docs.docker.com/desktop/install/windows-install/
[6]: https://sdkman.io/install
[7]: https://docs.docker.com/engine/install/ubuntu/#install-using-the-repository`