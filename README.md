# Echoes of the Deep

**Echoes of the Deep** est un RPG tactique en tour par tour orienté exploration de donjons, dans un univers medieval fantasy à l'ambiance sombre et dure.

Le joueur incarne un héros principal accompagné d'une petite équipe d'aventuriers. Ensemble, ils explorent des donjons, affrontent des créatures et récupèrent du butin, dans un système de combat basé sur une initiative liée à la vitesse.

Projet généré avec [libGDX](https://libgdx.com/) via [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

---

## Concept

- RPG tactique, combat au tour par tour sur grille
- Exploration de donjons générés (procédural à terme, fait main pendant le prototypage)
- Univers medieval fantasy à l'ambiance sombre et dure
- Boucle roguelite : progression persistante malgré la mort en donjon

##  Gameplay

- Système d'initiative basé sur la vitesse (chaque entité agit une fois par tour)
- Grille de combat = zone de la carte où le combat se déclenche (prévu pour supporter les AOE)
- Une action par tour parmi :
    - se déplacer
    - attaquer
    - utiliser une compétence
    - utiliser un objet
    - passer son tour
- Héros principal contrôlé directement par le joueur
- 2 à 3 alliés contrôlés par une IA simple (suivi + attaque automatique)
- Tous les personnages (héros, alliés, ennemis) partagent une seule file d'initiative globale
- 3 à 4 classes de personnages au lancement

## Mort et progression

- Mort du joueur → retour au village, perte partielle d'or et de loot
- Progression globale conservée (roguelite)
- Le village sert de hub entre les expéditions

## Architecture technique (vision actuelle)

Le moteur de jeu est pensé indépendamment du rendu graphique :

- Core Game Engine
- Combat Engine
- Entity System
- Action System
- Turn / Initiative System
- World Grid System
- AI System

Détails complets → [`Architecture.md`](./docs/Architecture.md)

## Roadmap (vue d'ensemble)

1. Prototype du moteur de combat
2. Système de grille et déplacement
3. IA de base
4. Compétences et objets
5. Système de donjon
6. Hub de village et progression persistante

Détails complets → [`Roadmap.md`](./docs/Roadmap.md)

## Documentation du projet

| Fichier           | Contenu                               |
|-------------------|---------------------------------------|
| `README.md`       | Présentation générale (ce fichier)    |
| `GDD.md`          | Design du gameplay                    |
| `Architecture.md` | Structure technique du moteur         |
| `Roadmap.md`      | Suivi de progression du développement |
| `Ideas.md`        | Idées futures / backlog non planifié  |

## Organisation Git

- `main` → version stable
- `dev` → intégration des fonctionnalités
- `feature/*` → développement isolé par fonctionnalité

---

## Développement (LibGDX / Gradle)

### Modules

- `core` : module principal, logique applicative partagée par toutes les plateformes.
- `lwjgl3` : plateforme desktop principale (LWJGL3 ; s'appelait `desktop` dans les anciennes docs).

### Gradle

Ce projet utilise [Gradle](https://gradle.org/) pour la gestion des dépendances. Le wrapper Gradle est inclus : utilise `gradlew.bat` ou `./gradlew`.

Tâches et flags utiles :

- `--continue` : les erreurs n'interrompent pas l'exécution des autres tâches.
- `--daemon` : utilise le daemon Gradle pour accélérer les exécutions.
- `--offline` : utilise les dépendances déjà en cache.
- `--refresh-dependencies` : force la revalidation de toutes les dépendances (utile pour les versions snapshot).
- `build` : build les sources et archives de tous les projets.
- `cleanEclipse` / `cleanIdea` : supprime les données de projet Eclipse / IntelliJ.
- `clean` : supprime les dossiers `build` (classes compilées, archives).
- `eclipse` / `idea` : génère les données de projet Eclipse / IntelliJ.
- `lwjgl3:jar` : build le jar exécutable (`lwjgl3/build/libs`).
- `lwjgl3:run` : lance l'application.
- `test` : lance les tests unitaires (si présents).

Les tâches non spécifiques à un module peuvent être préfixées par `nom:` pour ne s'appliquer qu'à un projet donné (ex. `core:clean`).

---

*Projet personnel de développement de jeu, en Java / LibGDX.*
