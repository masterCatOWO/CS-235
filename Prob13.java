import java.util.Stack;

public class Prob13
{
    class Vac {
        public String restaurar(String cifrado) {
            Stack<Character> pila = new Stack<>();
            for(char letra: cifrado.toCharArray()) {
                if(pila.isEmpty() || pila.peek() != letra) {
                    pila.push(letra);
                } else {
                    pila.pop();
                }
            }
            return depilar(pila);
        }
        
        public String depilar(Stack<Character> pila) {
            String msgRestaurado = "";
            while(!pila.isEmpty()) {
                msgRestaurado = pila.pop() + msgRestaurado;
            }
            return msgRestaurado;
        }
    }
    
    public void  run() {
        Vac v = new Vac();
        System.out.println(v.restaurar("eeexafaafmccessndd"));
    }
    
    public static void main() {
        new Prob13().run();
    }
}
