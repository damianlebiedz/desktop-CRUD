# Finance Manager

Aplikacja desktopowa CRUD (create-read-update-delete) do zarządzania wydatkami budżetu domowego.

## Spis treści
- [Technologie](#technologie)
- [Instalacja](#instalacja)
- [Problemy](#problemy)
- [Zrzuty ekranu](#zrzuty-ekranu)
- [Kontakt](#kontakt)

## Technologie
- Java
- JavaFX
- H2 Database
- Maven
- CSS

Do stworzenia graficznego interfejsu użytkownika (GUI) aplikacji użyłem JavaFX. Wybrałem bazę danych H2 ze względu na jej szybkość oraz fakt, iż zależało mi na stworzeniu w pełni desktopowej aplikacji, której dane użytkownika będą przechowywane bezpośrednio w folderze projektu. Program zbudowałem za pomocą Maven, który pomógł sprawnie zastosować potrzebne zależności. Moim celem było stworzenie prostej, szybkiej w obsłudze i czytelnej aplikacji desktopowej typu CRUD.


## Instalacja

1. Java Development Kit (JDK):
Upewnij się, że masz zainstalowane JDK (najlepiej w wersji 23.0.1 lub wyższej).
Możesz go pobrać z https://www.oracle.com/java/technologies/downloads/

2. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/damianlebiedz/java-desktop-app.git

4. Przejdź do katalogu projektu z poziomu cmd:
   ```bash
   cd java-desktop-app/build

5. Uruchom aplikację:
   ```bash
   java -jar FinanceManager-1.0-SNAPSHOT-jar-with-dependencies.jar

Alternatywnie można zbudować aplikację samodzielnie za pomocą Mavena. W tym celu należy mieć zainstalowane JDK, SDK i Maven. W celu łatwiejszego uruchamiania aplikacji zbudowałem plik .jar z zależnościami i zamieściłem go w folderze build. To on jest uruchamiany powyżej.
   
## Problemy
- Po pobraniu aplikacja nie łączy się automatycznie z bazą danych - wymagane jest ręczne połączenie w IDE za pomocą loginu i hasła do bazy podanych w klasie odpowiedzialnej za obsługe DB.
W TRAKCIE NAPRAWY

## Zrzuty ekranu

***
Główny widok:
***
![main view](https://github.com/damianlebiedz/Finance-Manager-CRUD-/assets/109239676/3ed67c90-f38d-4e89-84e4-b9bf3fa5ac0a)
***
Przykład użycia paska wyszukiwania:
***
![search bar](https://github.com/damianlebiedz/Finance-Manager-CRUD-/assets/109239676/2d4ee471-396d-4519-8891-c11270c66856)
***
Przykład wypełnienia pól po wybraniu elementu z tabeli (przygotowanie do zaktualizowania danych):
***
![on mouse clicked](https://github.com/damianlebiedz/Finance-Manager-CRUD-/assets/109239676/36d219a3-2704-490f-be66-c4ee589e244e)
***
Przykład wprowadzenia niepoprawnych danych:
***
![incorrect data error](https://github.com/damianlebiedz/Finance-Manager-CRUD-/assets/109239676/25cad134-90b9-4c17-8674-26cd7429e21f)
***

## Kontakt
Damian Lebiedź | 
https://damianlebiedz.github.io
