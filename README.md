# architecture-course-project

Javadoc - förhoppningsvis klart
Logging - Logga ohanterade exceptions, och logga säkerhet, typ om någon brute forcar. Loggfiler läggs i resources/logs/
Transactions - Vi ska ha transactions på alla CRUD-operationer
Management of relations - Entity classes ändras så att de är cascading
Error Handling - Databas och service lagren kastar upp undantag till viewcontrollers som hanterar dem med try/catch, presentera informativa felmeddelanden. Hanterade undantag loggas i separat fil.
Internationalization and localization - Datum i vyn ska formateras. Språk ska läggas till i databasen.
Packaging of final product - Vi utvecklar och deployar applikationen och meddelar när den är klar.
Testing - Vi ska göra In container tests med Spring
Acceptance testing - Ska göra med Selenium
Validation - Validering av HTML formulär ska göras
Template - klart
Standalone client - Ska göras
PDF documents - Ska göras
Other requirements
  - Availability kan vi lösa med AWS, eller med replication
  - Responstid kan lösas
  - Non-repudiation, kanske logga allt användare gör?
