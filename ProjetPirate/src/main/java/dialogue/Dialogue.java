package dialogue;

import interface_noyau_fonctionnel.INoyauFonctionnel;
import interface_noyau_fonctionnel.IPirates;
import presentation.MainFrame;

public class Dialogue implements IPirates {

    private MainFrame mainFrame;
    private INoyauFonctionnel noyau;

    private String nomJoueur1;
    private int joueurActifIndex = 0;

    // ═══ ANCIENNES VARIABLES TEMPORAIRES (à supprimer quand le noyau sera
    // connecté) ═══
    private int desTermines = 0;
    private int r1, r2;
    private int positionJoueur = 0;

    public Dialogue(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setNoyau(INoyauFonctionnel noyau) {
        this.noyau = noyau;
    }

    // ═══════════════════════════════════════════════════════════════
    // ANCIENNES MÉTHODES TEMPORAIRES (à supprimer quand le noyau sera connecté)
    // ═══════════════════════════════════════════════════════════════

    public void demarrerJeu() {
        positionJoueur = 1;
        mainFrame.deplacerPion(0, 1);
        mainFrame.activerBouton(true);
    }

    public void lancerDes() {
        mainFrame.activerBouton(false);
        if (noyau != null) {
            noyau.onBoutonLancerDesClique();
        } else { // en attendant que l'adaptateur soit fait
            r1 = (int) (Math.random() * 6) + 1;
            r2 = (int) (Math.random() * 6) + 1;
            mainFrame.afficherDes(r1, r2);
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // PARTIE 1 : IPirates (Appels du Noyau -> Vers MainFrame)
    // ═══════════════════════════════════════════════════════════════

    @Override
    public void afficherSaisieNoms() {
        mainFrame.afficherEcranDemarrage(); // Nom plus descriptif du composant UI
    }

    @Override
    public void afficherQuiCommence(String nomPremier) {
        mainFrame.afficherPopupQuiCommence(nomPremier);
    }

    @Override
    public void afficherTourJoueur(String nomJoueur) {
        joueurActifIndex = nomJoueur.equals(nomJoueur1) ? 0 : 1;
        mainFrame.mettreEnSurbrillanceJoueur(joueurActifIndex);
        mainFrame.log("C'est au tour de " + nomJoueur);
        mainFrame.activerBouton(true);
    }

    @Override
    public void afficherResultatDes(int de1, int de2) {
        mainFrame.afficherDes(de1, de2);
    }

    @Override
    public void afficherDeplacement(String nomPirate, int caseNumero) {
        mainFrame.activerDrag(joueurActifIndex, caseNumero);
    }

    @Override
    public void afficherCaseSpeciale(String type, String message) {
        if (type.equals("Bombe")) {
            mainFrame.afficherBombe(message);
        } else if (type.equals("Coco")) {
            mainFrame.afficherCoco(message);
        } else if (type.equals("Mystere")) {
            mainFrame.afficherMystere(message);
        }
    }

    @Override
    public void afficherChoixCoco() {
        String reponse = mainFrame.afficherPopupChoixCoco();
        onReponseCoco(reponse);
    }

    @Override
    public void afficherPV(String nomPirate, int pv) {
        int indexJoueur = nomPirate.equals(nomJoueur1) ? 0 : 1;
        mainFrame.mettreAJourPV(indexJoueur, pv);
        mainFrame.log(nomPirate + " a maintenant " + pv + " PV");
        onAffichagePVTermine();
    }

    @Override
    public void afficherFinPartie(String nomGagnant) {
        mainFrame.afficherEcranFinPartie(nomGagnant);
    }

    @Override
    public void afficherMessage(String message) {
        mainFrame.log(message);
    }

    // ═══════════════════════════════════════════════════════════════
    // PARTIE 2 : Callbacks / EventHandlers (Appels de MainFrame -> Vers Noyau)
    // ═══════════════════════════════════════════════════════════════

    public void onNomsSaisis(String nomJ1, String nomJ2) {
        this.nomJoueur1 = nomJ1;
        if (noyau != null)
            noyau.soumettreNoms(nomJ1, nomJ2);
    }

    public void onPopupQuiCommenceFermee() {
        if (noyau != null)
            noyau.onPopupQuiCommenceFermee();
    }

    public void onBoutonLancerDesClique() {
        mainFrame.activerBouton(false);
        if (noyau != null) {
            noyau.onBoutonLancerDesClique();
        } else {
            lancerDes();
        }
    }

    public void onAnimationDesTerminee() {
        desTermines++;
        if (desTermines == 2) {
            desTermines = 0;
        } else {
            // Logique temporaire quand le noyau n'est pas branché
            int cible = positionJoueur + r1 + r2;
            if (cible > 30)
                cible = 30;
            mainFrame.activerDrag(joueurActifIndex, cible);
        }
    }

    }

    // Callback venant de PanelPlateau (via MainFrame)
    public void onAnimationDeplacementTerminee(int caseNumero) {
        mainFrame.deplacerPion(joueurActifIndex, caseNumero);

        if (noyau != null) {
            noyau.onAnimationDeplacementTerminee();
        } else {
            positionJoueur = caseNumero;
            mainFrame.activerBouton(true);
        }
    }

    public void onPopupCaseSpecialeFermee() {
        if (noyau != null)
            noyau.onPopupCaseSpecialeFermee();
    }

    public void onReponseCoco(String reponse) {
        if (noyau != null)
            noyau.onReponseCoco(reponse);
    }

    public void onAffichagePVTermine() {
        if (noyau != null)
            noyau.onAffichagePVTermine();
    }

    public void onPopupFinPartieFermee() {
        if (noyau != null)
            noyau.onPopupFinPartieFermee();
    }
}
