# Projet Pirate — Groupe 2

Jeu de plateau inspiré de l'univers pirate, développé en Java dans le cadre de l'ue "ilu4" en licence Informatique de paul sabatier. Deux joueurs s'affrontent sur un plateau de 30 cases en lançant des dés, en subissant les effets de cases spéciales, et en tentant de vider les points de vie de leur adversaire.

---

## Ressources du projet

🔗 [Accéder au Drive partagé](https://drive.google.com/drive/folders/1bVZj_1JwVaW4w3XeHWh-M7CR-RiWe8M3) — UML, spécifications, test

---

## Équipe

| Rôle | Membre |
|---|---|
| Chef de projet | RABARISON Tiana Mélanie |
| Responsable des tests fonctionnels | BE MANANTSOA Lauriana |
| Responsable du modèle | HAILSELASSIEA Yoakin Samson |
| Responsable du développement du noyau | BELKHEIR Emin |
| Responsable du développement de l'IHM | GEBREKIRSTOS Nolawi |
| Responsable technique | PUENTE GONZALEZ Oscar |

---

## Répartition des tâches

| Partie | Contributeurs |
|---|---|
| POO (modèle objet) | Tout le groupe |
| IHM (package `presentation`) | Auteur indiqué dans chaque fichier source |
| Spécification | Tout le groupe |
| Tests | Mélanie & Lauriana |
| UML | Yoakin, Nolawi & Oscar |
| Expressions lambda | Emin |

---

## Architecture du projet

Le projet suit une architecture **MVC (Modèle – Vue – Contrôleur)**. Voici la structure des packages Java :

```
ProjetPirate/src/main/java/
│
├── entity/                         
│   ├── Jeu.java                     
│   ├── Joueur.java                 
│   ├── Pion.java                    
│   ├── De.java                      
│   ├── Plateau.java                 
│   ├── Case.java                    
│   ├── CaseBombe.java               
│   ├── CaseCoco.java                
│   ├── CaseMystere.java             
│   └── Couleur.java               
│
├── interface_noyau_fonctionnel/    
│   ├── INoyauFonctionnel.java
│   └── IPirates.java
│
├── noyau_fonctionnel/             
│   └── AdaptateurNoyauFonctionel.java 
│
├── controleur/                      
│   ├── ControlJeuPirate.java       
│   ├── ControlCommencerPartie.java  
│   ├── ControlDeplacer.java         
│   ├── ControleurDe.java           
│   ├── ControlActiverCase.java      
│   ├── ControlActiverCaseSpecial.java   
│   ├── ControlActiverCaseBombe.java     # Effet bombe
│   ├── ControlActiverCaseCoco.java      # Effet noix de coco
│   ├── ControlActiverCaseMystere.java   # Effet mystère
│   ├── ControlCacherDe.java         # Utilisation de l'accessoire coco (cacher dé)
│   ├── ControlPointDeVie.java       
│   └── ControlVerifierFinPartie.java   
│
├── boundary/interfaces/        
│   ├── IBoundary.java
│   ├── IActiverCase.java
│   ├── ICaseBombe.java
│   ├── ICaseCoco.java
│   ├── ICaseMystere.java
│   ├── ICommencerPartie.java
│   ├── IControlCacherDe.java
│   ├── IControlJeuPirate.java
│   ├── IDeplacerPirate.java
│   ├── IFinDePartie.java
│   ├── ILancerDe.java
│   └── IPointsDeVie.java
│
├── dialogue/                      
│   └── Dialogue.java
│
├── console/                         
│   ├── BoundaryConsole.java
│   ├── MainBoundaryConsole.java
│   └── MainIHM.java
│
└── presentation/                    # IHM graphique Swing (auteur précisé dans chaque fichier)
    ├── MainFrame.java               
    ├── PanelDemarrage.java          
    ├── PanelPlateau.java            
    ├── PanelCase.java               
    ├── PanelJoueur.java             
    ├── PanelCoeurs.java             
    ├── PanelDe.java                 
    ├── PanelCarte.java             
    ├── PanelBateau.java             
    ├── PanelCoco.java               
    ├── PanelDemiCocoDroit.java      
    ├── PanelDemiCocoGauche.java    
    ├── PanelEffetBombe.java         
    ├── PanelMystere.java            
    ├── PanelFinDePartie.java        
    └── TestFrame.java / TestPanelBombe.java  

ProjetPirate/test/
├── controleur/                      # Tests fonctionnels des contrôleurs
│   ├── BoundaryTest.java
│   ├── TestControlActiverCase.java
│   ├── TestControlActiverCaseBombe.java
│   ├── TestControlActiverCaseCoco.java
│   ├── TestControlActiverCaseMystere.java
│   ├── TestControlCacherDe.java
│   ├── TestControlCommencerPartie.java
│   ├── TestControlDeplacer.java
│   ├── TestControleurDe.java
│   ├── TestControlJeuPirate.java
│   └── TestControlPointDeVie.java
└── entity/                          # Tests unitaires des entités
    ├── TestCase.java
    ├── TestJoueur.java
    └── TestPlateau.java
```

---

## Règles du jeu

- **2 joueurs** s'affrontent sur un plateau de **30 cases**.
- Chaque joueur commence avec **5 points de vie (PV)**.
- À son tour, un joueur lance **2 dés** (valeurs 1–6) et avance son pion de la somme obtenue.
- Le premier joueur à atteindre ou dépasser la **case 30** gagne la partie, ou le dernier joueur encore en vie.

### Cases spéciales

| Case | Effet |
|---|---|
| **Case Mystère** (cases 3, 14, 22, 28) | Aléatoirement : avance ou recule le pion d'un nombre défini par un lancer de dé, **ou** fait gagner un nombre prédéfini de PV |
| **Chute de Noix de Coco** (cases 4, 11, 18) | Perte de PV aléatoire + donne un **accessoire** permettant de cacher l'un des dés de son adversaire (celui-ci ne lancera qu'un seul dé à son prochain tour) |
| **Case Bombe** (case 26) | Perte aléatoire d'un nombre de points de vie |

---

## 📐 Patron de conception

Le projet applique le patron **Boundary-Control-Entity (BCE)** :

- **Entity** — les données métier (plateau, joueurs, cases, dés)
- **Control** — la logique applicative et les règles du jeu
- **Boundary** — les interfaces entre l'IHM et les contrôleurs

Un **Adaptateur** (`AdaptateurNoyauFonctionel`) découple le noyau fonctionnel de la couche de présentation, permettant de faire fonctionner le jeu aussi bien en mode console qu'en mode graphique Swing.
