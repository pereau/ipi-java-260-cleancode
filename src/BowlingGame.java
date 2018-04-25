import com.sun.corba.se.impl.orbutil.closure.Constant;

public class BowlingGame {

    private int score;
    private int rollIndex = 0;
    private boolean strike = false;
    private boolean spare = false;
    private int frameScore = 0;
    int bonus = 0;
    
    
    

    public int getScore() {
        return score;
    }
    
//    public int getRollIndex() {
//        return rollIndex;
//    }
//    
//    public boolean isStrike() {
//        return strike;
//    }
//    
//    public boolean isSpare() {
//        return spare;
//    }

    public void roll(int numberPins) {
	rollIndex += 1;  
	
        if (spare) {
        // S'il y a eu spare au précédent roll
            bonus += numberPins;
            score += bonus;
            spare = false;
            bonus = 0;
        }
        
      
        frameScore += numberPins;
        if (rollIndex % 2 == 0) {
        // S'il s'agit du second roll dans une frame
            if (strike) {
            // S'il y a eu un strike sur la précédente frame
        	bonus += frameScore;
        	score += bonus;
        	strike = false;
        	bonus = 0;
            }
            if (frameScore == 10 ) {
        	spare = true; //// "annonce" l'ajout d'un bonus au prochain roll
            }  
            // On réinitialise frameScore pour le prochain roll (premier de la prochaine frame)
            score += frameScore ; 
            frameScore = 0;
        }
        
        if (numberPins == 10) {
            
                strike = true; // "annonce" l'ajout d'un bonus à la prochain frame
                rollIndex += 1; // on ne joue pas le second roll de la frame dont le score sera fixé à 10
                score += 10; 
                frameScore = 0; // on réinitialise frameScore étant donnée qu'on passe directement à la prochaine frame
            } 
     
     }
 }

