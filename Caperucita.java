
/**
 * Write a description of class Caperucita here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Caperucita
{
    private char[][]mapa;
    public Caperucita(char[][]mapa) {
        this.mapa = mapa;
    }
    
    public int getCantidadFlores() {
        return backtraking(0, 0);
    }
    
    private int backtraking(int fil, int col) {
        int res = 0, sur, nor, est, oes;
        sur = nor = est = oes = 0;
        if(validar(fil, col)) {
            if(mapa[fil][col] != 'C') {
                res = mapa[fil][col] == 'F'? 1 : 0;
                mapa[fil][col] = 'C';
                sur = backtraking(fil+1, col);
                nor = backtraking(fil-1, col);
                est = backtraking(fil, col+1);
                oes = backtraking(fil, col-1);
                res += sur;
                res += nor;
                res += est;
                res += oes;
            }
        }
        return res;
    } 
    
    
    private boolean validar(int fil, int col) {
        return fil > -1 && fil < mapa.length && col > -1 && col < mapa[0].length;
    }
}
