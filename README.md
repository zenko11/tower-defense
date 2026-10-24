# Tower Defense

Projet de jeu *Tower Defense* développé en groupe dans le cadre universitaire.

Le projet contient plusieurs versions du jeu, des tests unitaires, des fichiers de documentation UML ainsi qu'un `Makefile` permettant de compiler, tester et générer les exécutables `.jar`.

## Structure du projet

```text
.
├── images/     # Schémas UML et images des livrables
├── src/        # Code source Java
├── tests/      # Tests unitaires JUnit
├── Makefile    # Commandes de compilation, tests et exécution
└── LICENSE
```

## Prérequis

- Java JDK installé avec `javac`, `java`, `jar` et `javadoc` disponibles dans le `PATH` ;
- GNU Make ;
- `junit-console.jar` placé à la racine du projet pour exécuter les tests.

## Compilation et tests

Pour compiler le projet, générer la documentation, créer les fichiers `.jar` et lancer les tests :

```bash
make
```

Les commandes peuvent également être exécutées séparément :

```bash
make classes   # Compile les fichiers Java dans classes/
make docs      # Génère la documentation Javadoc dans docs/
make jar       # Génère les fichiers .jar dans jar/
make tests     # Compile les tests
make runtests  # Exécute les tests JUnit
make clean     # Supprime les fichiers générés
```

## Exécuter le jeu

Le projet fournit quatre modes d'exécution :

```bash
make towerdefense_a_random
make towerdefense_b_random
make towerdefense_a_interactive
make towerdefense_b_interactive
```

Les commandes `make` utilisent des paramètres par défaut définis dans le `Makefile`. Après avoir exécuté `make jar`, il est également possible de lancer directement un fichier `.jar`, par exemple :

```bash
java -jar jar/towerdefense_a_random.jar 10 10 5
```

Les fichiers générés sont placés dans les dossiers suivants :

- `classes/` : fichiers compilés ;
- `docs/` : documentation Javadoc ;
- `jar/` : exécutables du jeu au format `.jar`.

## Documentation

Les images et schémas UML associés aux différents livrables sont disponibles dans le dossier [`images/`](images/).
