# Library App

## Opis
Projekt backendowy aplikacji do zarządzania biblioteką, oparty o Spring Boot, Hibernate i MySQL. System obsługuje użytkowników, logowanie, zarządzanie książkami oraz wypożyczenia.

## Wymagania funkcjonalne

### Rejestracja i logowanie użytkownika
- Użytkownik może się zarejestrować, podając:
    - unikalny login,
    - adres e-mail,
    - hasło.
- Hasło musi mieć minimum 8 znaków (walidacja po stronie backendu).
- Hasła są przechowywane w bazie w postaci zaszyfrowanej (np. hash z użyciem bcrypt).
- Logowanie wymaga poprawnych danych uwierzytelniających (login + hasło).

### Zarządzanie książkami
- Administrator może dodawać, aktualizować i usuwać książki.
- Każda książka zawiera:
    - tytuł,
    - autora,
    - ISBN (unikalny),
    - liczbę stron,
    - datę wydania.
- ISBN musi być unikalny.
- Liczba stron musi być większa niż 0 (walidacja z wykorzystaniem CHECK).
- Każda książka należy do jednej kategorii (relacja przez FOREIGN KEY do tabeli kategorii).

### Zarządzanie użytkownikami
- Użytkownicy mogą wypożyczać książki.
- Jeden użytkownik może mieć maksymalnie 5 aktywnych wypożyczeń (ograniczenie walidowane po stronie aplikacji).
- Informacje przechowywane o użytkowniku:
    - imię,
    - nazwisko,
    - login (unikalny),
    - e-mail (unikalny),
    - data urodzenia,
    - hasło (pole wymagane, NOT NULL).

### Wypożyczanie i zwroty
- Użytkownik może wypożyczyć książkę, jeśli jest dostępna (czyli nie jest obecnie wypożyczona przez innego użytkownika).
- Każde wypożyczenie zawiera:
    - datę rozpoczęcia,
    - opcjonalną datę zwrotu.
- Nie można wypożyczyć książki, która już została wypożyczona i nie została jeszcze zwrócona.
