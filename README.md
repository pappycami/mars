# Mars

Application web **Spring Boot** de démonstration, développée par Aina Papy. Elle expose un service REST simple et est packagée en **WAR**, déployable aussi bien avec le Tomcat embarqué que sur un serveur Tomcat externe.

## Stack technique

| Composant | Détail |
|---|---|
| Langage | Java 17 |
| Framework | Spring Boot 3.4.3 |
| Build | Maven 3.9.9 (wrapper `mvnw` inclus) |
| Web | Spring Web (Spring MVC / REST) |
| Persistance | Spring Data JPA (Hibernate) |
| Base de données | H2 en mémoire |
| Packaging | WAR (Tomcat embarqué en scope `provided`) |
| Tests | Spring Boot Test (JUnit 5) |

## Structure du projet

```
src/main/java/com/ainapapy/mars/
├── MarsApplication.java          # Point d'entrée Spring Boot
├── ServletInitializer.java       # Support du déploiement WAR sur Tomcat externe
└── controller/
    └── WelcomeController.java    # API REST
src/main/resources/
└── application.properties        # Configuration (port, datasource, JPA)
src/main/webapp/META-INF/
└── context.xml                   # Context path /mars (Tomcat externe)
src/test/java/com/ainapapy/mars/
└── MarsApplicationTests.java     # Test de chargement du contexte
```

## API

| Méthode | Endpoint    | Description                          |
|---------|-------------|--------------------------------------|
| GET     | `/api/hello` | Retourne le message `Hello, Spring Boot!` |

Exemple :

```bash
curl http://localhost:8080/api/hello
# Hello, Spring Boot!
```

## Prérequis

- JDK 17 ou supérieur
- Apache Tomcat 10.1+ (uniquement pour le déploiement externe)

Maven n'est pas requis : le wrapper (`mvnw` / `mvnw.cmd`) télécharge automatiquement Maven 3.9.9.

## Lancement

### 1. Tomcat embarqué (développement)

Linux / macOS :

```bash
./mvnw spring-boot:run
```

Windows :

```powershell
.\mvnw.cmd spring-boot:run
```

L'application démarre sur le port **8080** : http://localhost:8080/api/hello

### 2. Déploiement sur Tomcat externe

```bash
./mvnw clean package
```

Copier ensuite `target/mars-0.0.1-SNAPSHOT.war` dans le dossier `webapps/` de Tomcat. Le fichier `context.xml` fixe le context path à `/mars` :

- http://localhost:8080/mars/api/hello

## Base de données

La persistance est configurée sur **H2 en mémoire** (`jdbc:h2:mem:testdb`, utilisateur `sa`, mot de passe vide) via `application.properties`. Aucune entité ni repository n'est encore défini — la couche JPA est prête à l'emploi pour les prochains développements.

Pour explorer la base pendant le développement, activer la console H2 :

```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

puis ouvrir http://localhost:8080/h2-console.

## Tests

```bash
./mvnw test
```

## Configuration

`src/main/resources/application.properties` :

| Propriété                     | Valeur                 | Rôle                              |
|-------------------------------|------------------------|-----------------------------------|
| `server.port`                 | 8080                   | Port HTTP du serveur              |
| `server.tomcat.threads.max`   | 30                     | Nombre max de threads Tomcat      |
| `spring.application.name`     | mars                   | Nom de l'application              |
| `spring.datasource.url`       | `jdbc:h2:mem:testdb`   | Base H2 en mémoire                |
| `spring.datasource.username`  | sa                     | Utilisateur de la base            |
| `spring.jpa.database-platform`| H2Dialect              | Dialecte Hibernate                |
