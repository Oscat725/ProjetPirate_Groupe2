//package controleur;
//
//public class ControlActiverCaseCoco extends ControlActiverCaseSpecial {
//    
//    private static final int DEGATS_MIN = 1;
//    private static final int DEGATS_MAX = 2;
//    
//    private final ControlPointsDeVie controlVie;
//    private final ControlCacherDe controlDe;
//    private final Random random;
//    private int derniersDegats;
//    
//    public ControlActiverCaseCoco(ControlPointsDeVie controlVie, ControlCacherDe controlDe) {
//        this.controlVie = controlVie;
//        this.controlDe = controlDe;
//        this.random = new Random();
//        this.derniersDegats = 0;
//    }
//    
//    @Override
//    public void activerCase(Joueur joueur) {
//        // 1) 1 ou 2
//        derniersDegats = DEGATS_MIN + random.nextInt(DEGATS_MAX - DEGATS_MIN + 1);
//        
//        // 2) //On met les dégats sur le joueurs
//        controlVie.perdrePointsDeVie(derniersDegats, joueur);
//        
//        // 3) True car effecté par la coco
//        controlDe.setAffecteCoco(joueur, true);
//    }
//    
//    public int getDerniersDegats() {
//        return derniersDegats;
//    }	
//}