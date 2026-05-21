package interface_noyau_fonctionnel;

// Implémentée par AdaptateurNoyauFonctionel
public interface INoyauFonctionnel {
    void jouer();
    void soumettreNoms(String nomJ1, String nomJ2);
    void onPopupQuiCommenceFermee();
    void onBoutonLancerDesClique();
    void onAnimationDesTerminee();
    void onAnimationDeplacementTerminee();
    void onPopupCaseSpecialeFermee();
    void onAffichagePVTermine();
    void onPopupFinPartieFermee();
}
