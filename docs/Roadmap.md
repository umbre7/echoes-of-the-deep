# Roadmap - Echoes of the Deep

> Statut : document vivant. On coche au fur et à mesure, on ajuste si un jalon s'avère mal découpé.

## Phase 0 : Setup

- [x] Squelette LibGDX généré (gdx-liftoff)
- [x] Structure de packages `core` mise en place (`entity`, `combat`, `action`, `turn`, `grid`, `ai`, `debug`, `engine`)
- [x] Docs de base (README, GDD, Architecture, Roadmap, Ideas)

## Phase 1 : Prototype du moteur de combat

- [x] Classe `Entity` de base (identité, stats minimales, vitesse)
- [x] Système d'initiative (tri par vitesse, file de tour)
- [ ] Boucle de combat minimale : deux entités, une attaque simple, résolution des dégâts
- [ ] Affichage console du déroulement d'un combat (debug)
- [ ] Tests unitaires sur l'ordre d'initiative et la résolution d'attaque

**Critère de sortie** : un combat 1v1 simulé en console, avec initiative correcte et dégâts appliqués.

## Phase 2 : Système de grille et déplacement

- [ ] `WorldGrid` : coordonnées, cases, occupation
- [ ] Action "se déplacer" (validation de portée/collision)
- [ ] Calcul de distance / portée (base pour ciblage et futures AOE)
- [ ] Intégration de la position dans `Entity`

**Critère de sortie** : les entités ont une position sur une grille, peuvent se déplacer, et le combat peut vérifier la portée d'une attaque.

## Phase 3 : IA de base

- [ ] Comportement "suivre le héros" en exploration
- [ ] Comportement "attaquer automatiquement" en combat (cible la plus proche, à affiner)
- [ ] Intégration des alliés IA dans la file d'initiative globale

**Critère de sortie** : un combat à plusieurs entités (héros + alliés IA + ennemis) se déroule sans intervention manuelle sur les alliés.

## Phase 4 : Compétences et objets

- [ ] Système d'action générique (`Action` interface/abstraction commune)
- [ ] Compétences (au moins 1 par classe, cf. GDD)
- [ ] Objets utilisables en combat
- [ ] Première ébauche de ciblage en zone (AOE), cf. GDD §3.3

**Critère de sortie** : un combat utilisant les 4 types d'actions (déplacement, attaque, compétence, objet).

## Phase 5 : Système de donjon

- [ ] Donjons faits à la main (contenu de test)
- [ ] Enchaînement de plusieurs salles/combats
- [ ] Génération procédurale (première itération)

**Critère de sortie** : le joueur peut explorer un donjon (même simple) composé de plusieurs combats/salles.

## Phase 6 : Hub de village et progression persistante

- [ ] Écran/état "village" (même en version console/texte au début)
- [ ] Persistance de la progression entre les expéditions
- [ ] Perte partielle d'or/loot à la mort

**Critère de sortie** : boucle complète village → donjon → mort ou retour → village, avec progression conservée.

## Phase 7 : Migration LibGDX

- [ ] Couche d'adaptation rendu (cf. Architecture.md §6)
- [ ] Rendu graphique de la grille et des entités
- [ ] Input joueur via LibGDX
- [ ] UI (stats, inventaire, actions disponibles)

**Critère de sortie** : le jeu tourne visuellement via `lwjgl3`, logique inchangée par rapport à la version console.

---

*Chaque phase peut donner lieu à une ou plusieurs branches `feature/*`. On ne passe à la phase suivante que quand le critère de sortie de la phase en cours est validé.*
