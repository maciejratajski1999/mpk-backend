# Mapa utrudnień MPK - backend

Ten projekt jest oparty na poradniku dostępnym na stronie [Quarkus][1].

## Wymagania

- System operacyjny: Windows
- [Quarkus CLI][2]
- [Chocolatey Software Manager][3] (do instalacji Quarkus CLI)
- [JDK 22][4] (dodane do PATH w systemie Windows)
- [Docker Desktop dla Windows][5]

## Uruchomienie serwera bazy danych

1. Uruchom klienta Docker na Windows na domyślnym porcie.
2. Następnie użyj komendy `quarkus dev` w folderze z projektem.

## Endpoints API

- `GET http://localhost:8080/vehicle?id=9100` - zwraca pojazd o podanym ID.
- `GET http://localhost:8080/vehicle/ids` - wyświetla wszystkie pojazdy w bazie danych.

[1]: https://quarkus.io/guides/getting-started-dev-services
[2]: https://quarkus.io/guides/cli-tooling
[3]: https://chocolatey.org/
[4]: https://www.oracle.com/java/technologies/downloads/
[5]: https://docs.docker.com/desktop/install/windows-install/
