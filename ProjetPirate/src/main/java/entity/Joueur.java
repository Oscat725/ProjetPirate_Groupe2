package entity;

public class Joueur {

	public static final Integer PV_MAX = 5;

	private final String nom;
	private int pointDeVie;
	private Pion pion;
	private boolean affecteCoco;
    private final De deUn;
    private final De deDeux;
	

	public Joueur(String nom, Pion pion) {
		this.nom = nom;
		this.pointDeVie = PV_MAX;
		this.pion = pion;
		this.deUn = new De(); 
        this.deDeux = new De(); 
    }

	public int getPointDeVie() {
		return pointDeVie;
	}
    
    public void setPointDeVie(int pointDeVie) {
		this.pointDeVie = pointDeVie;
	}

	public String getNom() {
		return nom;
	}
	
	public int getPosition() {
		return pion.getPosition();
	}
	
	public int setPosition(int pos) {
		return pion.setPosition(pos);
	}
	
	public void setAffecteCoco() {
		affecteCoco = true;
	}
    
    public int[] lancerDe() {
	    	int[] tabValDe =new int[] {0, 0};
	    	tabValDe[0] = deUn.getValeur();
	
	    	
	    	if (affecteCoco) {
	    		affecteCoco = false;
	    		return tabValDe;
	    	}
	    	
	    	tabValDe[1] = deDeux.getValeur();
	    	return tabValDe;
    }
    
    public int lancerDesGeneral(int nbDes, int ValeurMax, int ValeurMin) {
    	return deUn.lancerDes(nbDes, ValeurMax, ValeurMin);
    }
    
    public int getValeurCoco() {
    	return deUn.getValeur();
    }
    
    public Pion getPion() {
    	return pion;
    }

}
