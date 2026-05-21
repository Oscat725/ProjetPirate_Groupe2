package controleur;

import java.util.Arrays;
import entity.Joueur;

public class ControlVerifierFinPartie {

	private static final int CASE_FINALE = 29; // case 30 ( indice 29 )

	private final Joueur[] joueurs;

	public ControlVerifierFinPartie(Joueur[] joueurs) {
		if (joueurs == null || joueurs.length != 2) {
			throw new IllegalArgumentException("On doit avoir 2 joueurs dans le tableau");
		}
		this.joueurs = joueurs;
	}

	// La partie est fini si : Joueur sur case 30( indice 29) ou un joueur n'a plus
	// de vie

	public boolean verifierFinPartie() {
//        for (Joueur joueur : joueurs) {
//            if ( joueur.getPion().getPosition()==CASE_FINALE || joueur.getPointDeVie()<=0) {
//                return true;
//            }
//        }
//        return false;
//    }
		
		//utilisation lambdsa + stream
		return Arrays.stream(joueurs)
				.anyMatch(joueur -> joueur.getPion().getPosition() == CASE_FINALE || joueur.getPointDeVie() <= 0);
	}
}