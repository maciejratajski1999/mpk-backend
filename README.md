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
Uruchom terminal kontenera i zaloguj się do kontenera (opcjonalne):
```bash
psql -h localhost -U quarkus_test -d quarkus_test
\dt
```
Jeżeli nie stworzysz potrzebnych tablic, Quarkus spróbuje je stworzyć sam.

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
curl --header "Content-Type: application/json" --request POST --data "{\"routeId\": \"CurlTest\", \"routeType\": 2, \"validFrom\": \"2024-04-28\"}" localhost:8080/routes
```
### Weryfikacja dodanej trasy
Zweryfikuj, czy trasa została poprawnie dodana, odwiedzając http://localhost:8080/routes/exampleRouteId.

```bash
curl -X POST -H "Content-Type: application/json" -d '{"tripId":1,"route":{"routeId":"Testowa"},"tripHeadsign":"Test Head Sign","directionId":1,"shapeId":1,"variantId":1}' http://localhost:8080/trips
```
```bash
curl -X POST -H "Content-Type: application/json" -d '{"posId":1,"vehicle":{"vehicleID":1},"posLat":51.107883,"posLon":17.038538,"timestamp":"2024-05-06T15:01:51Z"}' http://localhost:8080/vehiclepositions
```
```bash
curl -X POST -H "Content-Type: application/json" -d '{"vehicleID":1,"trip":{"tripId":1}}' http://localhost:8080/vehicles
```
## Testowa trasa:
w folderze src/test/script wyślij paczkę z pojazdu o ID 1001 co sekundę, ze źródła trasa.txt:
```batch
.\testVehiclePositions.bat .\trasa.txt 1001 1
```

## Struktura Projektu

```text
├───.idea
├───.mvn
│   └───wrapper
├───.quarkus
│   └───cli
│       └───plugins
├───config
│       keycloak-keystore.jks - plik konfiguracyjny kontenera Keycloak
├───src
│   ├───data
│   ├───main
│   │   ├───docker
│   │   ├───java
│   │   │   └───org
│   │   │       └───mpk
│   │   │           ├───data
│   │   │           │       LoadDataFromGTFS.java - Klasa do ładowania danych z GTFS.
│   │   │           │
│   │   │           ├───entity
│   │   │           │       EntityBase.java - Klasa bazowa dla wszystkich encji.
│   │   │           │       Route.java - Pojedyncza linia, np. K, 11, 931
│   │   │           │       Stop.java - Przystanek
│   │   │           │       Trip.java - Pojedynczy kurs, np. tramwaj 8 startujący o 16:00 z pętli Karłowice
│   │   │           │       Vehicle.java - pojedynczy tramwaj bądź autobus na trasie
│   │   │           │       VehiclePosition.java - zarejestrowane ramki z pozycjami pojazdów
│   │   │           │
│   │   │           ├───lifecycle
│   │   │           │       LoadOpenDataBean.java - Na startupie serwera ładuje dane z OpenData Urzędu Miejskiego
│   │   │           │
│   │   │           └───resource
│   │   └───resources
│   │       │   application.properties - plik konfiguracyjny aplikacji
│   │       │   import.sql - przykładowe i testowe uzupełnienie bazy danych
│   │       │
│   │       └───META-INF
│   │           └───resources
│   │                   index.html - strona główna aplikacji backendowej
│   └───test
│       ├───data
│       ├───java
│       │   └───org
│       │       └───mpk
│       │               ParseDataFromTxtTest.java - przetestuj rozpakowywanie OpenData GTFS do JavaMap
│       │               RouteTest.java - przetestuj translację z JavaMap do persystentnej encji Route
│       │
│       └───script
│               testVehiclePositions.bat -skrypt symulujący wysyłane ramki przez urządzenie
│               trasa.txt - przykładowa trasa
│               trasa2.txt - 2 przykładowa trasa
└───target
    ├───classes
    │   ├───META-INF
    │   │   └───resources
    │   └───org
    └───test-classes
        └───org
            └───mpk
```






[1]: https://quarkus.io/guides/getting-started-dev-services
[2]: https://quarkus.io/guides/cli-tooling
[3]: https://chocolatey.org/
[4]: https://www.oracle.com/java/technologies/downloads/
[5]: https://docs.docker.com/desktop/install/windows-install/
[6]: https://sdkman.io/install
[7]: https://docs.docker.com/engine/install/ubuntu/#install-using-the-repository`