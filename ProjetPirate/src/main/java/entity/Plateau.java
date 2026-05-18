package entity;

public class Plateau {
	private Case[] cases = new Case[30];
	
	public Plateau() {
        for (int i = 0; i < 30; i++) {
            cases[i] = new Case(i);
        }
        
        
        // En choisit en avance les cases speciales
        // En coherance avec les cases speciales de PanelPlateau(Presentation)
        cases[4]  = new CaseBombe(4);    // case 5
        cases[7]  = new CaseCoco(7);     // case 8
        cases[9]  = new CaseMystere(9);  // case 10
        cases[11] = new CaseBombe(11);
        cases[16] = new CaseCoco(16);
        cases[19] = new CaseBombe(19);
        cases[21] = new CaseMystere(21);
        cases[24] = new CaseCoco(24);
        cases[27] = new CaseMystere(27);
    }

	
	
	public Case getCase(int indice) {
		return cases[indice];
	}
}
