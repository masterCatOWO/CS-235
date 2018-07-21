
/**
 * Write a description of class Prob85 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Prob85
{
    public String letrasEliminadas(String textVirus, String textBackup) {
        return letrasEliminadas(textVirus, textBackup, 0, 0);
    }
    
    private String letrasEliminadas(String textVirus, String textBackup,
        int indiceTV, int indiceTB) {
        if(indiceTB == textBackup.length()) {
            return "";
        } else {
            if(indiceTV == textVirus.length()) {
                return textBackup.substring(indiceTB);
            } else if(textVirus.charAt(indiceTV) == textBackup.charAt(indiceTB)) {
                return letrasEliminadas(textVirus, textBackup, indiceTV+1, indiceTB+1);
            } else {
                return textBackup.charAt(indiceTB) + letrasEliminadas(textVirus, textBackup,
                indiceTV, indiceTB+1);
            }
        }
    }
    
    
    
    
    
    
}
