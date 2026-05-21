package boundary.interfaces;

public interface IControlJeuPirate {
    void jouer();
    void apresLancerDe(int sommeDes);
    void apresDeplacer(int numCase);
    void finDeTour();
    void finAfficherTour();
}