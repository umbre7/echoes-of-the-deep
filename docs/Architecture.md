# Architecture - Echoes of the Deep

> Statut : document vivant, à ajuster au fil des prototypes.

## 1. Principe directeur

La logique de jeu doit rester **totalement indépendante du rendu**. Concrètement :

- Le module `core` contient la logique de jeu (moteur, combat, entités, IA, grille...).
- **Pendant la phase de prototypage**, aucune classe de logique ne doit importer de package `com.badlogic.gdx.*`. Le rendu LibGDX (Screen, sprites, input...) n'est pas encore branché.
- Les tests et l'itération se font via une `main()` console (affichage texte, logs), pas via une fenêtre LibGDX.
- Le module `lwjgl3` ne sera utilisé que plus tard, quand on branchera un vrai rendu graphique par-dessus la logique déjà validée.

Objectif : pouvoir remplacer intégralement la couche de présentation (console → LibGDX, voire un autre moteur) sans toucher au moteur de jeu.

## 2. Modules

| Module   | Rôle                                        | Statut                                 |
|----------|---------------------------------------------|----------------------------------------|
| `core`   | Logique de jeu (moteur, combat, entités...) | Actif dès maintenant                   |
| `lwjgl3` | Rendu / plateforme desktop (LibGDX)         | Pas touché avant la fin du prototypage |

## 3. Découpage interne du `core` (proposition de packages)

```
core/
└─ src/main/java/com/echoesofthedeep/
   ├─ engine/       → boucle de jeu générale, orchestration
   ├─ entity/        → Entity, Stats, Character, Enemy...
   ├─ combat/        → CombatEngine, résolution des actions
   ├─ action/        → Action, Move, Attack, UseSkill, UseItem, Pass...
   ├─ turn/           → gestion de l'initiative et de l'ordre des tours
   ├─ grid/           → WorldGrid, coordonnées, calcul de portée/AOE
   ├─ ai/             → comportements IA des alliés/ennemis
   └─ debug/          → affichage console pour les tests manuels
```

Ce découpage n'est pas figé, il évoluera au fur et à mesure qu'on écrira les premières classes. L'idée est surtout de garder chaque système dans son propre package, avec des dépendances qui vont plutôt dans un sens (`combat` dépend de `entity`, pas l'inverse, etc.).

## 4. Systèmes principaux (rappel du découpage fonctionnel)

- **Entity System** : représentation des personnages/monstres (stats, position sur la grille, état).
- **Action System** : ce qu'une entité peut faire à son tour (se déplacer, attaquer, compétence, objet, passer).
- **Turn / Initiative System** : ordre de passage basé sur la vitesse, file d'initiative globale (héros + alliés + ennemis).
- **Combat Engine** : orchestre un combat, démarre la file d'initiative, distribue les tours, applique les actions, détecte fin de combat.
- **World Grid System** : grille de coordonnées, calcul de distance/portée, base pour les AOE futures.
- **AI System** : logique de décision pour les alliés (et plus tard les ennemis), suivi, ciblage, choix d'action.

## 5. Stratégie de test

- Phase 1 (Java pur) : tests via `main()` console + tests unitaires (JUnit) sur les systèmes isolés (ex. : file d'initiative, résolution d'une attaque).
- **TBD** : mise en place de JUnit dès le début ou après les premiers prototypes fonctionnels ? (à trancher avec le mentor avant la première feature).

## 6. Migration vers LibGDX (plus tard)

Quand la logique sera stabilisée :

- `lwjgl3` (et à terme d'autres launchers) consommera `core` comme une librairie.
- Une couche d'adaptation traduira les états du moteur (`core`) en rendu (sprites, grille visuelle, UI), cette couche ne contiendra **aucune règle de jeu**, uniquement de l'affichage et de la capture d'input.
- **TBD** : structure exacte de cette couche d'adaptation (Screen dédié, pattern MVC/MVP...), à concevoir le moment venu, pas maintenant.

## 7. Organisation Git (rappel)

- `main` → version stable
- `dev` → intégration des fonctionnalités
- `feature/*` → développement isolé par fonctionnalité (ex. `feature/entity-system`, `feature/turn-order`)
