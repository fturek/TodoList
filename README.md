# hej !

### Dagger:
- W tym temacie jestem samoukiem, więc brakuje mi tutaj komercjalnego doświadczenia i praktyk.

### Testy
- Nie mam doświadczenia z testowaniem z daggerem, dlatego nie pisałem testów. Niestety brakło mi czasu na "douczenie się tego". 
To co bym testował to głównie byłoby poprawne/niepoprawne pobieranie danych - w przypadku braku internetu, co się stanie w przypadku błędu, co gdy dostanę nulla w response

### Ogólne
- Podczas tego projektu uczylem sie Firebase Firestore, gdyz nie miałem wcześniej żadnego doświadczenia z tym. Bardzo przyjemne narzędzie, chociaż nie podoba mi sposob "szukania" odpowiedniego elementu w kolekcji. 
Wydaje mi sie ze są lepsze rozwiązania, jak np trzymanie unikalnego id objektu w bazie, ale pewnie z jakiegos powodu tutaj nie zostało to zastosowane
- W początkowych commitach możecie zauważyć ze probowałem użyc reopsitory pattern, ale odszedłem od tego pomysłu gdyż doszedłem do wniosku ze Firebase ma tak dobre narzędzia do pobierania danych ze Repository było zbędne. 
- W początkowych commitach możecie zauważyć ze probowałem użyc Paginacji zaproponowoanej przez googla, ale odszedłem od tego pomysłu, bo Firestore ma swoja paginacje (zauważyłem to w trakcie nauki).
Próbowałem ja napisać, ale coś mi nie pobierało danych podczas scrollowania po RecyclerView.
Wiem że główny zamysł to "złapanie" ostatniego elementu, i potem  pobrania danych od ".startAfter" ostatniego elementu wraz z opcja ".limit".
Ale coś mi nie działało, nie wiem co, wiec wolałem zostawić w pełni działajacą apkę
- przemyślałbym strukturę projektu, według mnie nie jest zła, ale widze też plusy innych sposobów "ułożenia" projektu.
Np nie jestem pewny czy dzielenie klas w package 'di' osobno na komponenty, moduły itd jest dobrym rozwiazaniem
- Design jest średni, ale IMO wystarczający jak na testową apkę
- Dodałem splashView
- Użyłem architektury MVVM 
- użyłem NavigationComponent do przechodzenia miedzy fragmentami

## Dużo się nauczyłem dzięki temu zadaniu.
## Dzięki z góry za poświęcony czas na sprawdzenie kodu :)

## Pozdrawiam! Filip