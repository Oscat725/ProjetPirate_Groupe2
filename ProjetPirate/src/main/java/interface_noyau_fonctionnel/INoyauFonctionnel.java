package interface_noyau_fonctionnel;

// Dialogue → Adaptateur
// Le Dialogue appelle ces méthodes quand l'utilisateur fait une action / animation terminée
// Implémentée par AdaptateurNoyauFonctionel
public interface INoyauFonctionnel {
    void jouer();

    void soumettreNoms(String nomJ1, String nomJ2);

    // Noms proposés à valider avec l'équipe :
    void confirmationCommencer(); // Proposition: void onPopupQuiCommenceFermee();

    void confirmationTourVu(); // Proposition: void onBoutonLancerDesClique();

    void confirmationDes(); // Proposition: void onAnimationDesTerminee();

    void confirmationDeplacement(); // Proposition: void onAnimationDeplacementTerminee();

    void confirmationCaseSpeciale(); // Proposition: void onPopupCaseSpecialeFermee();

    void confirmationPV(); // Proposition: void onAffichagePVTermine();

    void confirmationFinPartie(); // Proposition: void onPopupFinPartieFermee();
}
