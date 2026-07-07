# Ideas - Echoes of the Deep

> Backlog d'idées non planifiées. Rien ici n'est engagé, c'est une liste vivante à trier au fil du développement. Une idée qui devient concrète migre vers `Roadmap.md` (ou `GDD.md` si c'est une décision de design plutôt qu'une étape de dev).

## Gameplay

- Obstacles de terrain influençant le déplacement / la ligne de vue
- Effets de statut (poison, étourdissement, buff/debuff temporaire)
- Objets/équipements avec effets passifs
- Ennemis avec IA plus élaborée (patterns, phases de boss)
- Système de craft léger au village

## Contenu

- Plusieurs biomes de donjon (thèmes visuels + mécaniques propres)
- Boss uniques par type de donjon
- Événements aléatoires en exploration (pièges, trésors cachés, PNJ)

## Progression / Meta

- Arbre de compétences par classe
- Déblocables permanents au village (nouvelles classes, upgrades de départ)
- Difficulté croissante / mode "New Game+"

## Technique / Outils

- Éditeur de donjon fait main (pour accélérer la création de contenu manuel en Phase 5)
- Système de sauvegarde/chargement
- Support manette (en plus clavier/souris) une fois la migration LibGDX faite
- Question ouverte : le tour par tour s'applique-t-il uniquement au combat (TurnManager scopé aux participants d'un combat, ce qui est l'hypothèse actuelle), ou est-ce que d'autres entités de l'étage (qui ramassent du loot, se déplacent, etc.) doivent aussi être prises en compte pendant qu'un combat se déroule ailleurs ? Si oui, il faudra une couche d'orchestration au-dessus de TurnManager (probablement dans engine), sans changer TurnManager lui-même. À trancher quand on abordera le système de donjon (Phase 5) — pas avant.

## Hors scope confirmé (rappel du GDD)

- Multijoueur
- Customisation avancée de personnage
- Systèmes de craft complexes

---

*Pas de pression à tout faire ici, c'est un réservoir d'idées, pas un engagement.*
