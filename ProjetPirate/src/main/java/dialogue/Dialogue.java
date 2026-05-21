package dialogue;

import interface_noyau_fonctionnel.INoyauFonctionnel;
import interface_noyau_fonctionnel.IPirates;
import presentation.MainFrame;

public class Dialogue implements IPirates {

    private MainFrame mainFrame;
    private INoyauFonctionnel noyau;
    private int desTermines = 0;
    private int r1, r2;
    private int positionJoueur = 0;

    public Dialogue(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setNoyau(INoyauFonctionnel noyau) {
        this.noyau = noyau;
    }
    
    public void lancerDes() {
        mainFrame.activerBouton(false);
        if (noyau != null) {
           // noyau.lancerDes();
        } else { // on attendant que l'adaptateur soit fait
            r1 = (int)(Math.random() * 6) + 1;
            r2 = (int)(Math.random() * 6) + 1;
            mainFrame.afficherDes(r1, r2);
        }
    }
    
    
    public void animationDeTerminee() {
        desTermines++;
        if (desTermines == 2) {
            desTermines = 0;
            int cible = positionJoueur + r1 + r2;
            if (cible > 30) cible = 30;
            mainFrame.activerDrag(cible);
        }
    }

    public void pionPlaceCorrectement(int caseNumero) {
        positionJoueur = caseNumero;
        mainFrame.deplacerPion(0, caseNumero, java.awt.Color.RED);
        mainFrame.activerBouton(true);
    }

    // --------------------------------------------------------
    // IPirates — appelé par l'Adaptateur pour afficher des choses
    // --------------------------------------------------------

    @Override
    public void afficherSaisieNoms() {
        // Afficher le panneau de saisie des noms des joueurs
    }

    @Override
    public void afficherQuiCommence(String nomPremier) {
        // Afficher une popup indiquant quel joueur commence
    }

    @Override
    public void afficherTourJoueur(String nomJoueur) {
        // Mettre en surbrillance le joueur actif, afficher son nom
    }

    @Override
    public void afficherResultatDes(int de1, int de2) {
        // Lancer l'animation des dés avec les valeurs de1 et de2
    }

    @Override
    public void afficherDeplacement(String nomPirate, int caseNumero) {
        // Animer le déplacement du pion vers la case caseNumero
    }

    @Override
    public void afficherCaseSpeciale(String type, String message) {
        // Afficher une popup avec le type de case et le message d'effet
    }

    @Override
    public void afficherPV(String nomPirate, int pv) {
        // Mettre à jour la barre / le compteur de PV du joueur nomPirate
    }

    @Override
    public void afficherFinPartie(String nomGagnant) {
        // Afficher l'écran de fin de partie avec le nom du gagnant
    }

    @Override
    public void afficherMessage(String message) {
        // Afficher un message informatif dans la zone de texte
    }

    // --------------------------------------------------------
    // EventHandlers — appelé par MainFrame en réponse aux événements GUI
    // (ActionEvent bouton, événement fin d'animation, fermeture popup, etc.)
    // MainFrame reçoit l'événement de ses panels internes et délègue ici.
    // Chaque handler notifie le noyau que l'action utilisateur est terminée.
    // --------------------------------------------------------

    public void onNomsSaisis(String nomJ1, String nomJ2) {
        // ActionEvent : bouton "Jouer" cliqué dans le panel de saisie des noms
        noyau.soumettreNoms(nomJ1, nomJ2);
    }

    public void onPopupQuiCommenceFermee() {
        // ActionEvent : bouton OK de la popup "X commence" cliqué
        noyau.onPopupQuiCommenceFermee();
    }

    public void onBoutonLancerDesClique() {
        // ActionEvent : bouton "Lancer les dés" cliqué
        noyau.onBoutonLancerDesClique();
    }

    public void onAnimationDesTerminee() {
        // Événement fin d'animation : les dés ont fini de tourner
        noyau.onAnimationDesTerminee();
    }

    public void onAnimationDeplacementTerminee() {
        // Événement fin d'animation : le pion est arrivé sur sa case cible
        noyau.onAnimationDeplacementTerminee();
    }

    public void onPopupCaseSpecialeFermee() {
        // ActionEvent : bouton OK de la popup de case spéciale cliqué
        noyau.onPopupCaseSpecialeFermee();
    }

    public void onAffichagePVTermine() {
        // Événement fin d'affichage : la mise à jour des PV est terminée
        noyau.onAffichagePVTermine();
    }

    public void onPopupFinPartieFermee() {
        // ActionEvent : bouton OK de l'écran de fin de partie cliqué
        noyau.onPopupFinPartieFermee();
    }
}
