import java.util.*;

/**
 * A classe Main é a classe principal que resolve o problema dos contentores usando o algoritmo A*.
 * @author Diogo Damásio, Goncalo Rodrigues
 */
public class Main {
    /**
     * Responsavel por interagir com o utilizador
     * @param args argumentos da linha de comandos (nao utilizado)
     */
    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);

        AStar s = new AStar();

        String inicial = sc.nextLine();                                                                                 //le a configuracao inicial
        String objetivo = sc.nextLine();                                                                                //le a configuracao final

        Port pI = new Port('i', inicial);
        Port pF = new Port('o', objetivo);

        Iterator<AStar.State> it = s.solve(pI, pF);                                                                     //resolver problema

        if (it==null) System.out.println("no solution found");
        else
        {
                AStar.State i = it.next();
                System.out.println(i);                                                                                  //imprime a solucao
                System.out.println((int) i.getG());                                                                     //imprime custo acumulado
        }


        sc.close();
    }
}
