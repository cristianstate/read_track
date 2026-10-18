# ReadTrack - Aplicație Android pentru Gestionarea Lecturii

**ReadTrack** este o aplicație mobilă Android modernă, concepută pentru a ajuta utilizatorii să își organizeze colecția de cărți și să își urmărească progresul de lectură.

---

## Caracteristici Principale

-  **Managementul Cărților**: Adăugarea de noi cărți în bibliotecă cu detalii precum titlu, autor, categorie, număr de capitole și pagini.
- **Urmărirea Progresului**: Afișarea grafică și procentuală a progresului de lectură pentru fiecare carte prin intermediul unui grafic circular personalizat (*Custom View*).
-  **Căutare în Timp Real**: Filtrarea rapidă a cărților din bibliotecă după titlu sau autor direct din bara de căutare.
-  **Actualizare Progres**: Posibilitatea de a actualiza rapid numărul de pagini citite.
-  **Ștergere Cărți**: Eliminarea cărților din bibliotecă cu dialog de confirmare.

---

## Arhitectură și Design Patterns

Aplicația respectă recomandările oficiale Google privind arhitectura Android și folosește pattern-ul **MVVM (Model-View-ViewModel)**:

- **Model**: Entitatea `Book` și baza de date `AppDatabase` (Room).
- **View**: Fragmentele (`BookListFragment`, `BookDetailFragment`, `AddBookFragment`) și `MainActivity`.
- **ViewModel**: `BookViewModel`, responsabil pentru stocarea și gestionarea datelor supraviețuind modificărilor de configurație (ex: rotirea ecranului).
- **Repository Pattern**: `BookRepository` servește drept sursă unică de adevăr (*Single Source of Truth*), abstractizând accesul la baza de date.

---

## Tehnologii și Biblioteci Utilizate

- **Limbaj**: Java
- **Android Architecture Components**:
  - **Room Database**: Pentru stocarea locală persistentă a datelor.
  - **LiveData & ViewModel**: Pentru gestionarea reactivă a stării interfeței grafice.
  - **Navigation Component**: Pentru gestionarea fiabilă a navigării între fragmente.
  - **View Binding**: Pentru accesul sigur și performant la elementele de layout.
- **Interfață Grafică & UI Custom**:
  - **Material Design 3**: Componente vizuale moderne (SearchView, FloatingActionButton, Cards).
  - **CircularProgressChart**: *Custom View* personalizat dezvoltat folosind API-ul `Canvas` din Android.
- **Utilitare**:
  - **Glide**: Încărcare și stocare eficientă a imaginilor/copertelor.

---

## Structura Proiectului

```text
com.fitness.myapplication6
├── MainActivity.java                  # Activitatea principală (găzduiește NavHostFragment)
├── data
│   ├── dao
│   │   └── BookDao.java               # Interfața DAO pentru interogări Room
│   ├── database
│   │   └── AppDatabase.java           # Instanța bazei de date Room (Singleton)
│   ├── model
│   │   └── Book.java                  # Entitatea ce reprezintă un tabel în baza de date
│   └── repository
│       └── BookRepository.java        # Clasa Repository pentru manipularea datelor
└── ui
    ├── adapter
    │   └── BookAdapter.java           # RecyclerView Adapter cu ListAdapter & DiffUtil
    ├── fragment
    │   ├── AddBookFragment.java       # Ecran adăugare carte nouă
    │   ├── BookDetailFragment.java    # Ecran detalii și actualizare progres carte
    │   └── BookListFragment.java      # Ecran listă cărți și căutare
    ├── view
    │   └── CircularProgressChart.java # Custom View pentru graficul circular de progres
    └── viewmodel
        └── BookViewModel.java         # ViewModel-ul principal
```

---

##  Cerințe de Sistem și Instalare

- **Android Studio**: Jellyfish / Ladybug sau o versiune mai nouă.
- **JDK**: Java 17 sau mai nou.
- **SDK Minim**: API 24 (Android 7.0 Nougat).
- **SDK Țintă**: API 34 sau mai nou.

### Pasul de compilare și rulare:
1. Deschideți proiectul în Android Studio.
2. Efectuați o sincronizare Gradle (`Gradle Sync`).
3. Rulați aplicația pe un emulator sau dispozitiv fizic Android (`Run 'app'`).
