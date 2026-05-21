package interface_noyau_fonctionnel;

// Implémentée par Dialogue
public interface IPirates {
    void afficherSaisieNoms();
    void afficherQuiCommence(String nomPremier);
    void afficherResultatDes(int de1, int de2);
    void afficherDeplacement(String nomPirate, int caseNumero);
    void afficherCaseSpeciale(String type, String message);
    void afficherPV(String nomPirate, int pv);
    void afficherFinPartie(String nomGagnant);
    void afficherMessage(String message);
    void afficherTourJoueur(String nomJoueur);
}
