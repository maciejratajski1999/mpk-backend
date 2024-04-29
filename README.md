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


## Uruchomienie serwera bazy danych

1. **Windows**: Uruchom klienta Docker na Windows na domyślnym porcie.
   **Ubuntu**: Uruchom klienta Docker na Ubuntu na domyślnym porcie.
2. Następnie użyj komendy `quarkus dev` / `sudo quarkus dev` w folderze z projektem.
-  jeśli otrzymujesz `sudo: quarkus: command not found`, spróbuj:
`sudo -E env "PATH=$PATH" quarkus dev`

## Endpoints API

- `GET http://localhost:8080/vehicles`
- `GET http://localhost:8080/routes`
- `GET http://localhost:8080/trips`

[1]: https://quarkus.io/guides/getting-started-dev-services
[2]: https://quarkus.io/guides/cli-tooling
[3]: https://chocolatey.org/
[4]: https://www.oracle.com/java/technologies/downloads/
[5]: https://docs.docker.com/desktop/install/windows-install/
[6]: https://sdkman.io/install
[7]: https://docs.docker.com/engine/install/ubuntu/#install-using-the-repository`