package entity;

public class Plateau {
	private Case[] cases = new Case[30];
	
	public Plateau() {
        for (int i = 0; i < 30; i++) {
            cases[i] = new CaseBombe(i);
        }
        
        
        // En choisit en avance les cases speciales
        // En coherance avec les cases speciales de PanelPlateau(Presentation)
        
        cases[2]  = new CaseMystere(2);    // case 3
        cases[3]  = new CaseCoco(3);
        cases[10]  = new CaseCoco(10);
        cases[13] = new CaseMystere(13);
        cases[17] = new CaseCoco(17);
        cases[21] = new CaseMystere(21);
        cases[25] = new CaseBombe(25);
        cases[27] = new CaseMystere(27);
    }

	
	
	public Case getCase(int indice) {
		return cases[indice];
	}
}
