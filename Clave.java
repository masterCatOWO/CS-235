
public class Clave
{
    public String transformar(String cad, int rots) {
        return transformar(cad, rots, 0);
    }
    
    private String transformar(String cad, int rots, int indiceCad) {
        if(indiceCad == cad.length()) {
            return "";
        } else {
            int posLetra = cad.charAt(indiceCad) - 'a';
            int nuevaPosLetra = (posLetra + rots)%26;
            nuevaPosLetra = nuevaPosLetra != 0 ? nuevaPosLetra - 1 : nuevaPosLetra;
            char nuevaLetra = (char)(nuevaPosLetra + 'a');
            return nuevaLetra + transformar(cad, rots, indiceCad + 1);
        }
    }
    
    
    
    
    
    
    
    
    
    
}
