# Project Plan

An Android application to monitor book reading using an SQLite database. The app features:
- A menu with 'Add New Book' and 'Book List'.
- 'Add New Book' form: Book name, category, author, number of chapters, number of pages, and an optional cover image.
- 'Book List': Shows book titles and authors, with a search bar at the top for filtering by name.
- 'Book Details' (on item click): Shows title, author, cover image, category, and reading progress (pages read).
- UI includes a circular progress chart for reading progress.
- Features to update progress and delete books from the database.
- Modern UI using Material Design 3, vibrant colors, edge-to-edge display.
- Language: Java.
- UI: XML/View system.
- Application Language: Romanian.
Note: The app is already partially implemented in English (Tasks 1-4 completed). This plan should account for localizing everything into Romanian and completing the final verification.

## Project Brief

# Rezumatul
 Proiectului: ReadTrack MVP

O aplicație Android modernă, dezvoltată în Java, concepută pentru monitorizarea lect
urii. Aplicația oferă o experiență intuitivă pentru gestionarea colecției personale de cărți
 și vizualizarea progresului prin grafice moderne, respectând principiile Material Design 3 și utilizând o bază de date
 locală robustă.

## Funcționalități
- **Înregistrarea Cărților**: Formular complet pentru ad
ăugarea numelui cărții, categoriei, autorului, numărului de pagini/capitole și
 a unei imagini opționale pentru copertă.
- **Bibliotecă Căutabilă**: Listă din
amică a tuturor cărților cu o bară de căutare integrată în partea de sus pentru filtrarea
 rapidă după titlu.
- **Monitorizarea Progresului Vizual**: Ecran de detalii pentru fiecare carte care include
 un grafic circular de progres pentru a vizualiza procentul paginilor citite.
- **Gestiunea
 Datelor**: Funcții dedicate pentru actualizarea paginilor parcurse sau ștergerea definitivă a căr
ților din baza de date SQLite.

## Stiva Tehnologică (Nivel Înalt)
-
 **Limbaj**: Java
- **Interfață Utilizator (UI)**: XML Layouts cu Material Design
 3 (M3) și suport Edge-to-Edge
- **Persistență**: Room Persistence Library (SQLite
) pentru stocarea locală a datelor
- **Navigație și Strategie Adaptivă**: **Jetpack
 Navigation 3** (bazată pe stări) și **Compose Material Adaptive Library** pentru gestionarea layout-urilor
 adaptive
- **Concurență**: Java Executors pentru operațiuni asincrone cu baza de date
- **Înc
ărcare Imagini**: Glide (pentru procesarea și afișarea coperților)

## Implementation Steps
**Total Duration:** 17m 13s

### Task_1_DataLayer: Setup the Room database architecture using Java. Define the Book entity (title, author, category, chapters, pages, current page, image path), DAO for CRUD operations, and a Repository class.
- **Status:** COMPLETED
- **Updates:** Creating Room data layer in Java: Created Book entity, DAO, Database, and Repository. Fixed build error by updating compileSdk to 37. Project builds successfully.
- **Acceptance Criteria:**
  - Room entity, DAO, and Database classes created in Java
  - Repository class implemented for data operations
  - Database builds successfully

### Task_2_ListAndAddUI: Implement the Navigation graph and the two primary screens: Book List and Add Book. Use XML layouts and Java Fragments. The list must include a RecyclerView and a SearchView for filtering. The Add Book form must handle all required fields.
- **Status:** COMPLETED
- **Updates:** Implemented Navigation graph, BookListFragment (RecyclerView + SearchBar), AddBookFragment (Form), and BookViewModel in Java. Converted MainActivity to Java. Integrated Material 3.
- **Acceptance Criteria:**
  - Navigation graph with Fragment destinations established
  - Book List displays books and filters correctly via SearchBar
  - Add Book form saves data to Room database via ViewModel
- **Duration:** 3m 7s

### Task_3_DetailsAndProgress: Create the Book Details screen. Implement a circular progress chart (using a custom Drawable or View) to visualize reading progress. Add functionality to update the 'pages read' and delete the book.
- **Status:** COMPLETED
- **Updates:** Implemented BookDetailFragment with circular progress chart, update progress dialog, and delete functionality. Updated ViewModel and Repository for CRUD operations on single books. Integrated Glide.
- **Acceptance Criteria:**
  - Details screen shows all book info and cover image
  - Circular progress chart accurately reflects (pages read / total pages)
  - Update and Delete operations work correctly and reflect in the UI
- **Duration:** 2m 5s

### Task_4_ThemeAndPolish: Apply Material Design 3 theme with a vibrant color scheme. Implement edge-to-edge display support and create an adaptive app icon matching the book tracking theme.
- **Status:** COMPLETED
- **Updates:** Fixed Edge-to-Edge toolbar overlap by applying WindowInsets padding to the Toolbar in MainActivity. Verified build success.
- **Acceptance Criteria:**
  - Material 3 theme applied with vibrant colors
  - App supports Edge-to-Edge display
  - Adaptive icon created and functional
- **Duration:** 3m 25s

### Task_5_RomanianLocalization: Localize the entire application interface into Romanian. Translate all UI labels, hints, and messages in the 'Add New Book', 'Book List', and 'Book Details' fragments to align with the Romanian project brief.
- **Status:** COMPLETED
- **Updates:** Localizing the entire application interface into Romanian. Translated all UI labels, hints, and messages in the 'Add New Book', 'Book List', and 'Book Details' fragments. Updated nav_graph.xml titles and java code for toast messages and dialogs. Verified build success.
- **Acceptance Criteria:**
  - All UI text and resource strings are in Romanian
  - Project builds successfully after string replacement
  - Navigation and form labels are correctly localized
- **Duration:** 1m 36s

### Task_6_RunAndVerify: Final verification of the application. Ensure stability, confirm alignment with Romanian user requirements, and report critical UI issues. Instruct critic_agent to verify application stability (no crashes).
- **Status:** COMPLETED
- **Updates:** Final verification complete. The application is stable, fully localized in Romanian, and all features (Add, Search, Details, Update, Delete) are functional. The Edge-to-Edge toolbar issue is fixed, and Material Design 3 guidelines are followed. No crashes or critical issues found.
- **Acceptance Criteria:**
  - App runs without crashes
  - All features functional (Add, Search, Details, Update, Delete)
  - build pass
  - make sure all existing tests pass
- **Duration:** 7m

