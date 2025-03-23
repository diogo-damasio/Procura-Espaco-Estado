import java.util.*;

/**
 * A classe Main é a classe principal que resolve o problema dos contentores usando o algoritmo Best-First.
 * @author Diogo Damásio
 */
public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        BestFirst s = new BestFirst();

        String inicial = sc.nextLine();                                                                                 //le a configuracao inicial
        String objetivo = sc.nextLine();                                                                                //le a configuracao final

        String[] params_i = inicial.split(" ");                                                                   //divide em pilhas
        String[] params_o = objetivo.split(" ");

        ArrayList<ArrayList<Container>> portoI= new ArrayList<>();                                                      //listas p inserir contentores
        ArrayList<ArrayList<Container>> portoF= new ArrayList<>();


        for (String string : params_i) {                                                                                //cria e adiciona contentores as diferentes pilhas
            ArrayList<Container> pilha = new ArrayList<>();                                                             //por cada string cria uma pilha e no fim adiciona
            for ( int i = 0; i < string.length(); i+=2) {
                pilha.add(new Container(string.charAt(i), (Character.getNumericValue(string.charAt(i + 1)))));
            }
            portoI.add(pilha);
        }

        Port pI = new Port(portoI);                                                                                     //criacao da configuracao inicial


        for (String string : params_o) {                                                                                //mesmo para final
            ArrayList<Container> pilha = new ArrayList<>();
            for ( int i = 0; i < string.length(); i++) {
                pilha.add(new Container(string.charAt(i)));
            }
            portoF.add(pilha);

        }

        Port pF = new Port(portoF);



        Iterator<BestFirst.State> it = s.solve(pI, pF);                                                                 //resolver problema

        if (it==null) System.out.println("no solution found");
        else
        {
            while(it.hasNext()) {
                BestFirst.State i = it.next();
                System.out.println(i);                                                                                  //imprime a solucao
                if (!it.hasNext()) System.out.println((int) i.getG());                                                  //imprime custo acumulado
            }
        }




        sc.close();
    }
}
