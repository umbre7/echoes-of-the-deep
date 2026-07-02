# Game Design Document - Echoes of the Deep

> Statut : document vivant. Les sections marquées **TBD** seront précisées au fur et à mesure des prototypes.

## 1. Vision

Echoes of the Deep est un RPG tactique en tour par tour, dans un cadre medieval fantasy à l'ambiance sombre et dure, centré sur l'exploration de donjons. Le joueur dirige un héros principal et une petite équipe d'alliés IA, dans une boucle roguelite : chaque expédition peut se terminer par la mort, mais la progression globale du joueur persiste via un hub village.

Piliers de gameplay :
- **Tactique** : le positionnement et le timing (initiative) comptent autant que la puissance brute.
- **Lisibilité** : peu d'entités à gérer en même temps (1 héros + 2-3 alliés), pour garder chaque combat clair.
- **Persistance douce** : la mort punit (perte partielle d'or/loot) sans effacer la progression du joueur.

**Ton et univers** : cadre medieval fantasy (royaumes, chevaliers, magie intégrée au monde) avec une ambiance sombre et dure : danger réel, enjeux moraux gris, pas de médiéval fantasy "conte de fées". **TBD** : direction artistique précise, lore détaillé (factions, histoire du monde, nature des donjons/créatures).

## 2. Boucle de jeu

Village (hub) → Préparation (équipement, équipe) → Expédition en donjon → Combats + exploration → Retour (butin) ou Mort (perte partielle) → Village

## 3. Système de combat

### 3.1 Grille

- La grille de combat correspond à la zone de la carte où le combat se déclenche (pas une grille séparée de taille fixe) : le combat "hérite" de l'espace exploré.
- Cette approche est motivée par les évolutions prévues : zones d'effet (AOE), sorts à portée, obstacles de terrain, etc. Il faut donc que la grille de combat soit cohérente avec la grille d'exploration dès le départ (même système de coordonnées).
- **TBD** : taille de cellule, gestion de la ligne de vue / obstacles, contraintes de forme de zone (salle fermée vs couloir).

### 3.2 Initiative et tour

- Chaque entité (héros, alliés, ennemis) a une vitesse qui détermine sa place dans une file d'initiative **globale et unique**.
- Une entité agit une fois par tour, une seule action parmi :
    - se déplacer
    - attaquer
    - utiliser une compétence
    - utiliser un objet
    - passer son tour

### 3.3 Zones d'effet (AOE) : prévu, pas dans la v1

- Les compétences pourront cibler plusieurs cases (ligne, cercle, cône, etc.).
- Implique dès la v1 : les entités doivent occuper des coordonnées de grille explicites (pas juste un ordre de liste), pour que les calculs de zone soient possibles plus tard sans réécrire le système de ciblage.

## 4. Personnages

### 4.1 Héros et alliés

- 1 héros principal, contrôlé directement par le joueur.
- 2 à 3 alliés, contrôlés par une IA simple : suivent le héros en exploration, attaquent automatiquement en combat (cible la plus proche ou la plus faible, **TBD**).
- Tous partagent la même file d'initiative que les ennemis.

### 4.2 Classes (v1)

- 3 à 4 classes simples au lancement.
- **TBD** : identité de chaque classe (rôle tank/dégâts/soutien), stats de départ, compétences signature. À définir lors du design de l'Entity System.

## 5. Donjons

- Génération **procédurale** visée pour la version cible.
- Pendant la phase de prototypage des mécaniques (combat, IA, grille), les niveaux seront **faits à la main** : ça permet de tester les systèmes sans dépendre d'un générateur pas encore écrit, et de garder des cas de test reproductibles.
- La génération procédurale sera introduite une fois les mécaniques de base validées (cf. Roadmap).

## 6. Mort et progression

- Mort en donjon → retour au village, perte partielle d'or et de loot (pourcentage exact **TBD**).
- Progression globale conservée (roguelite) : **TBD** ce qui est persistant exactement (niveaux des personnages ? déblocages au village ? objets stockés ?).
- Le village est le hub entre les expéditions : achat/vente, préparation d'équipe, **TBD** autres fonctions (upgrades, quêtes annexes...).

## 7. Hors scope pour l'instant

- Multijoueur
- Customisation avancée de personnage
- Systèmes de craft complexes

(à déplacer vers `Ideas.md` si abandonnés définitivement, ou vers la Roadmap si planifiés plus tard)
