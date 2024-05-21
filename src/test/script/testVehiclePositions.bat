@echo off
setlocal enabledelayedexpansion

REM Przyjmowanie argumentów z linii poleceń
set "filename=%~1"
set "vehicleID=%~2"
set "delay=%~3"

REM Sprawdzenie, czy plik istnieje
if not exist "%filename%" (
    echo Plik %filename% nie istnieje.
    exit /b
)

REM Czytanie pliku linia po linia
for /f "delims=" %%a in (%filename%) do (
    REM Dodanie ID pojazdu na początek każdej linii
    set "line=%vehicleID%;%%a"
    echo "!line!"
    REM Wysłanie zapytania POST na endpoint
    curl --header "Content-Type: text/plain" --request POST --data "!line!" http://localhost:8080/vehiclepositions 
    REM Odczekanie określonej liczby sekund
    timeout /t %delay% /nobreak >nul
)
