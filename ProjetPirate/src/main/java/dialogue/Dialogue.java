package dialogue;

import interface_noyau_fonctionnel.INoyauFonctionnel;
import interface_noyau_fonctionnel.IPirates;
import presentation.MainFrame;

public class Dialogue implements IPirates {

    private MainFrame mainFrame;
    private INoyauFonctionnel adaptateur;

    private String nomJoueur1;
    private int joueurActifIndex = 0;

    private int desTermines = 0;

    public Dialogue(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setAdaptateur(INoyauFonctionnel adaptateur) {
        this.adaptateur = adaptateur;
    }

    // ÉTAPE 1 : Démarrage et Saisie des noms

    @Override
    public void afficherSaisieNoms() {
        mainFrame.afficherEcranDemarrage();
        // Afficher le panneau de saisie des noms des joueurs
    }

    public void onNomsSaisis(String nomJ1, String nomJ2) {
        // ActionEvent : bouton "Jouer" cliqué dans le panel de saisie des noms
        this.nomJoueur1 = nomJ1;
        mainFrame.deplacerPion(0, 1);
        mainFrame.deplacerPion(1, 1);
        if (adaptateur != null)
            adaptateur.soumettreNoms(nomJ1, nomJ2);
    }

    // ÉTAPE 2 : Déterminer qui commence

    @Override
    public void afficherQuiCommence(String nomPremier) {
        mainFrame.afficherPopupQuiCommence(nomPremier);
        // Afficher une popup indiquant quel joueur commence
        if (adaptateur != null)
            adaptateur.onPopupQuiCommenceFermee();
    }

    public void onPopupQuiCommenceFermee() {
        // ActionEvent : bouton OK de la popup "X commence" cliqué
        if (adaptateur != null)
            adaptateur.onPopupQuiCommenceFermee();
    }

    // ÉTAPE 3 : Début du tour d'un joueur

    @Override
    public void afficherTourJoueur(String nomJoueur) {
        joueurActifIndex = nomJoueur.equals(nomJoueur1) ? 0 : 1;
        mainFrame.mettreEnSurbrillanceJoueur(joueurActifIndex);
        mainFrame.log("C'est au tour de " + nomJoueur);
        mainFrame.activerBouton(true);
        // Mettre en surbrillance le joueur actif, afficher son nom
    }

    public void onBoutonLancerDesClique() {
        // ActionEvent : bouton "Lancer les dés" cliqué
        mainFrame.activerBouton(false);
        if (adaptateur != null) {
            adaptateur.onBoutonLancerDesClique();
        }
    }

    // ÉTAPE 4 : Lancer et Animation des dés

    @Override
    public void afficherResultatDes(int de1, int de2) {
        mainFrame.afficherDes(de1, de2);
        // Lancer l'animation des dés avec les valeurs de1 et de2
    }

    public void onAnimationDesTerminee() {
        // Événement fin d'animation : les dés ont fini de tourner
        desTermines++;
        if (desTermines == 2) {
            desTermines = 0;
            if (adaptateur != null) {
                adaptateur.onAnimationDesTerminee();
            }
        }
    }

    // ÉTAPE 5 : Déplacement (Drag & Drop) du pion

    @Override
    public void afficherDeplacement(String nomPirate, int caseNumero) {
        mainFrame.activerDrag(joueurActifIndex, caseNumero);
        // Animer le déplacement du pion vers la case caseNumero + 1 (pour l'UI)
    }

    public void onAnimationDeplacementTerminee(int caseNumero) {
        // Événement fin d'animation : le pion est arrivé sur sa case cible
        mainFrame.deplacerPion(joueurActifIndex, caseNumero);
        if (adaptateur != null) {
            adaptateur.onAnimationDeplacementTerminee();
        }
    }

    // ÉTAPE 6 : Résolution de la case (Spéciale / Coco)

    public void afficherCaseSpeciale(String type, String message,int effect, int value) {
        switch (type) {
            case "BOMBE":
                afficherMessage("Case special bombe");
                mainFrame.afficherBombe(value,message);
                break;
            case "CHUTE DE NOIX DE COCO":
                afficherMessage("Case special coco");
                mainFrame.afficherCoco(value);
                break;
            case "MYSTERE":
                afficherMessage("Case special mystere");
                mainFrame.afficherMystere(effect,value);
                break;
            default:
                break;
        }
    }

    public void onPopupCaseSpecialeFermee() {
        // ActionEvent : bouton OK de la popup de case spéciale cliqué
        if (adaptateur != null)
            adaptateur.onPopupCaseSpecialeFermee();
    }

    @Override
    public void afficherChoixCoco(int joueurCourant) {
        String reponse = mainFrame.afficherPopupChoixCoco(JoueurCourant);
        onReponseCoco(reponse);
    }

    public void onReponseCoco(String reponse) {
        if (adaptateur != null)
            adaptateur.onReponseCoco(reponse);
    }

    // ÉTAPE 7 : Mise à jour des Points de Vie

    @Override
    public void afficherPV(String nomPirate, int pv) {
        int indexJoueur = nomPirate.equals(nomJoueur1) ? 0 : 1;
        mainFrame.mettreAJourPV(indexJoueur, pv);
        mainFrame.log(nomPirate + " a maintenant " + pv + " PV");
        onAffichagePVTermine();
        // Mettre à jour la barre / le compteur de PV du joueur nomPirate
    }

    public void onAffichagePVTermine() {
        // Événement fin d'affichage : la mise à jour des PV est terminée
        if (adaptateur != null)
            adaptateur.onAffichagePVTermine();
    }

    // ÉTAPE 8 : Fin de partie

    @Override
    public void afficherFinPartie(String nomGagnant) {
        mainFrame.afficherEcranFinPartie(nomGagnant);
        // Afficher l'écran de fin de partie avec le nom du gagnant
    }

    public void onPopupFinPartieFermee() {
        // ActionEvent : bouton OK de l'écran de fin de partie cliqué
        if (adaptateur != null)
            adaptateur.onPopupFinPartieFermee();
    }

    // MÉTHODES UTILITAIRES

    @Override
    public void afficherMessage(String message) {
        mainFrame.log(message);
        // Afficher un message informatif dans la zone de texte
    }
}
