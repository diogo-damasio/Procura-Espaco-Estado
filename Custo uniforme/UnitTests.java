import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


/**
 * Classe de testes unitarios para as classes Container e Port
 * Classe BestFirst nao necessita testes unitarios pois foi reutilizada
 * @author Diogo Damasio 79826
 * @version 1.0
 */
public class UnitTests {

    /**
     * Testa construtor e getters da classe Container
     */
    @Test
    public void testContainerConstructor() {
        Container c1 = new Container('A', 2);
        assertEquals('A',c1.getNome());
        assertEquals(2,c1.getPeso());

        Container c2 = new Container('B');
        assertEquals('B',c2.getNome());
        assertEquals(0,c2.getPeso());

        Container c3 = new Container('A', 200);
        assertNotEquals('B',c3.getNome());
        assertNotEquals(10,c3.getPeso());
    }

    /**
     * Testa equals da classe Container
     */
    @Test
    public void testContainerEquals() {
        Container c1 = new Container('A', 10);
        Container c2 = new Container('A');
        Container c3 = new Container('A',  5);
        Container c4 = new Container('B', 10);

        assertEquals(c1, c2);
        assertEquals(c1, c3);
        assertNotEquals(c1, c4);
    }

    /**
     * Testa hashCode da classe Container
     */
    @Test
    public void testContainerHashcode() {
        Container c1 = new Container('Z',2);
        Container c2 = new Container('Z', 10);
        Container c3 = new Container('X', 2);

        assertEquals(c1.hashCode(),c2.hashCode());
        assertNotEquals(c1.hashCode(),c3.hashCode());
    }

    /**
     * Testa construtor e getContentores da classe Port
     */
    @Test
    public void testPortConstructor() {
        Container c1 = new Container('A');
        Container c2 = new Container('B');
        Container c3 = new Container('C');

        ArrayList<Container> pilha1 = new ArrayList<>();
        pilha1.add(c1);
        pilha1.add(c2);

        ArrayList<Container> pilha2 = new ArrayList<>();
        pilha2.add(c3);

        ArrayList<ArrayList<Container>> porto = new ArrayList<>();
        porto.add(pilha1);
        porto.add(pilha2);

        Port p = new Port(porto);

        Container c4 = new Container('A');
        Container c5 = new Container('B');
        Container c6 = new Container('C');

        ArrayList<Container> pilha3 = new ArrayList<>();
        pilha3.add(c4);
        pilha3.add(c5);

        ArrayList<Container> pilha4 = new ArrayList<>();
        pilha4.add(c6);

        ArrayList<ArrayList<Container>> portoEsperado = new ArrayList<>();
        portoEsperado.add(pilha3);
        portoEsperado.add(pilha4);

        assertEquals(p.getContentores(),portoEsperado);

        Container c7 = new Container('D');
        Container c8 = new Container('E');
        Container c9 = new Container('F');

        ArrayList<Container> pilha5 = new ArrayList<>();
        pilha5.add(c7);
        pilha5.add(c8);

        ArrayList<Container> pilha6 = new ArrayList<>();
        pilha6.add(c9);

        ArrayList<ArrayList<Container>> portoEsperado2 = new ArrayList<>();
        portoEsperado2.add(pilha5);
        portoEsperado2.add(pilha6);

        assertNotEquals(p.getContentores(),portoEsperado2);
    }


    /**
     * Testa toString da classe Port bem como a ordenacao dos stacks
     */
    @Test
    public void testPortToString() {
        Container c1 = new Container('A');
        Container c2 = new Container('B');
        Container c3 = new Container('C');

        ArrayList<Container> pilha1 = new ArrayList<>();
        pilha1.add(c1);
        pilha1.add(c2);

        ArrayList<Container> pilha2 = new ArrayList<>();
        pilha2.add(c3);

        ArrayList<ArrayList<Container>> porto = new ArrayList<>();
        porto.add(pilha1);
        porto.add(pilha2);

        Port p = new Port(porto);

        String expected = "[A, B]\n[C]\n";

        assertEquals(expected,p.toString());

        String notExpected1 = "[C]\n[A, B]\n";
        String notExpected2 = "[A, B, C]\n";

        assertNotEquals(notExpected1,p.toString());
        assertNotEquals(notExpected2,p.toString());
    }

    /**
     * Testa equals da classe Port
     */
    @Test
    public void testPortEquals() {
        Container c1 = new Container('A');
        Container c2 = new Container('B');
        Container c3 = new Container('C');

        ArrayList<Container> pilha1 = new ArrayList<>();
        pilha1.add(c1);
        pilha1.add(c2);

        ArrayList<Container> pilha2 = new ArrayList<>();
        pilha2.add(c3);

        ArrayList<ArrayList<Container>> porto1 = new ArrayList<>();
        porto1.add(pilha1);
        porto1.add(pilha2);

        Port p1 = new Port(porto1);

        Container c4 = new Container('A');
        Container c5 = new Container('B');
        Container c6 = new Container('C');

        ArrayList<Container> pilha3 = new ArrayList<>();
        pilha3.add(c4);
        pilha3.add(c5);

        ArrayList<Container> pilha4 = new ArrayList<>();
        pilha4.add(c6);

        ArrayList<ArrayList<Container>> porto2 = new ArrayList<>();
        porto2.add(pilha3);
        porto2.add(pilha4);

        Port p2 = new Port(porto2);

        assertEquals(p1, p2);

        Container c7 = new Container('A');
        Container c8 = new Container('B');
        Container c9 = new Container('D');

        ArrayList<Container> pilha5 = new ArrayList<>();
        pilha5.add(c7);
        pilha5.add(c8);

        ArrayList<Container> pilha6 = new ArrayList<>();
        pilha6.add(c9);

        ArrayList<ArrayList<Container>> porto3 = new ArrayList<>();
        porto3.add(pilha5);
        porto3.add(pilha6);

        Port p3 = new Port(porto3);

        assertNotEquals(p1, p3);
    }

    /**
     * Testa hashCode da classe Port
     */
    @Test
    public void testPortHashcode() {
        Container c1 = new Container('A');
        Container c2 = new Container('B');
        Container c3 = new Container('C');

        ArrayList<Container> pilha1 = new ArrayList<>();
        pilha1.add(c1);
        pilha1.add(c2);

        ArrayList<Container> pilha2 = new ArrayList<>();
        pilha2.add(c3);

        ArrayList<ArrayList<Container>> porto1 = new ArrayList<>();
        porto1.add(pilha1);
        porto1.add(pilha2);

        Port p1 = new Port(porto1);

        Container c4 = new Container('A');
        Container c5 = new Container('B');
        Container c6 = new Container('C');

        ArrayList<Container> pilha3 = new ArrayList<>();
        pilha3.add(c4);
        pilha3.add(c5);

        ArrayList<Container> pilha4 = new ArrayList<>();
        pilha4.add(c6);

        ArrayList<ArrayList<Container>> porto2 = new ArrayList<>();
        porto2.add(pilha3);
        porto2.add(pilha4);

        Port p2 = new Port(porto2);

        assertEquals(p1.hashCode(),p2.hashCode());

        Container c7 = new Container('A');
        Container c8 = new Container('B');
        Container c9 = new Container('D');

        ArrayList<Container> pilha5 = new ArrayList<>();
        pilha5.add(c7);
        pilha5.add(c8);

        ArrayList<Container> pilha6 = new ArrayList<>();
        pilha6.add(c9);

        ArrayList<ArrayList<Container>> porto3 = new ArrayList<>();
        porto3.add(pilha5);
        porto3.add(pilha6);

        Port p3 = new Port(porto3);

        assertNotEquals(p1.hashCode(),p3.hashCode());
    }

    /**
     * Testa clone da classe Port
     * @throws CloneNotSupportedException se nao for possivel clonar
     */
    @Test
    public void testPortClone() throws CloneNotSupportedException {

        Container c1 = new Container('A');
        Container c2 = new Container('B');
        Container c3 = new Container('C');

        ArrayList<Container> pilha1 = new ArrayList<>();
        pilha1.add(c1);
        pilha1.add(c2);

        ArrayList<Container> pilha2 = new ArrayList<>();
        pilha2.add(c3);

        ArrayList<ArrayList<Container>> porto = new ArrayList<>();
        porto.add(pilha1);
        porto.add(pilha2);

        Port p = new Port(porto);

        Port pClone = (Port) p.clone();

        assertEquals(p,pClone);


        Container c4 = new Container('D',1);
        Container c5 = new Container('E',2);
        Container c6 = new Container('F',3);

        ArrayList<Container> pilha3 = new ArrayList<>();
        pilha3.add(c4);
        pilha3.add(c5);

        ArrayList<Container> pilha4 = new ArrayList<>();
        pilha4.add(c6);

        ArrayList<ArrayList<Container>> porto2 = new ArrayList<>();
        porto2.add(pilha3);
        porto2.add(pilha4);

        Port p2 = new Port(porto2);
        Port p2Clone = (Port) p2.clone();

        assertEquals(p2,p2Clone);
    }

    /**
     * Testa children da classe Port
     */
    @Test
    public void testPortChildren() {
        Container a = new Container('A');
        Container b = new Container('B');
        Container c = new Container('C');

        ArrayList<Container> pilha1 = new ArrayList<>();
        pilha1.add(a);
        pilha1.add(b);

        ArrayList<Container> pilha2 = new ArrayList<>();
        pilha2.add(c);

        ArrayList<ArrayList<Container>> porto = new ArrayList<>();
        porto.add(pilha1);
        porto.add(pilha2);

        Port p = new Port(porto);


        //1o filho
        Container a1 = new Container('A');
        Container b1 = new Container('B');
        Container c1 = new Container('C');
        ArrayList<Container> pilha3 = new ArrayList<>();
        pilha3.add(a1);

        ArrayList<Container> pilha4 = new ArrayList<>();
        pilha4.add(c1);
        pilha4.add(b1);


        ArrayList<ArrayList<Container>> porto1 = new ArrayList<>();
        porto1.add(pilha3);
        porto1.add(pilha4);
        Port p1 = new Port(porto1);



        //2o filho
        Container a2 = new Container('A');
        Container b2 = new Container('B');
        Container c2 = new Container('C');
        ArrayList<Container> pilha5 = new ArrayList<>();
        pilha5.add(a2);

        ArrayList<Container> pilha6 = new ArrayList<>();
        pilha6.add(c2);

        ArrayList<Container> pilha7 = new ArrayList<>();
        pilha7.add(b2);


        ArrayList<ArrayList<Container>> porto2 = new ArrayList<>();
        porto2.add(pilha5);
        porto2.add(pilha6);
        porto2.add(pilha7);

        Port p2 = new Port(porto2);

        //3o filho
        Container a3 = new Container('A');
        Container b3 = new Container('B');
        Container c3 = new Container('C');
        ArrayList<Container> pilha8 = new ArrayList<>();
        pilha8.add(a3);
        pilha8.add(b3);
        pilha8.add(c3);

        ArrayList<ArrayList<Container>> porto3 = new ArrayList<>();
        porto3.add(pilha8);

        Port p3 = new Port(porto3);



        List<Ilayout> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);

        assertEquals(p.children(),lista);
    }

    /**
     * Testa isGoal da classe Port
     */
    @Test
    public void testContainerIsGoal() {
        Container a = new Container('A');
        Container b = new Container('B');
        Container c = new Container('C');

        ArrayList<Container> pilha1 = new ArrayList<>();
        pilha1.add(a);
        pilha1.add(b);

        ArrayList<Container> pilha2 = new ArrayList<>();
        pilha2.add(c);

        ArrayList<ArrayList<Container>> porto = new ArrayList<>();
        porto.add(pilha1);
        porto.add(pilha2);

        Port p = new Port(porto);


        //objetivo
        Container a1 = new Container('A');
        Container b1 = new Container('B');
        Container c1 = new Container('C');
        ArrayList<Container> pilha3 = new ArrayList<>();
        pilha3.add(a1);
        pilha3.add(b1);

        ArrayList<Container> pilha4 = new ArrayList<>();
        pilha4.add(c1);

        ArrayList<ArrayList<Container>> porto1 = new ArrayList<>();
        porto1.add(pilha3);
        porto1.add(pilha4);
        Port p1 = new Port(porto1);

        assertTrue(p.isGoal(p1));


        //nao objetivo
        Container a2 = new Container('A');
        Container b2 = new Container('B');
        Container c2 = new Container('C');
        ArrayList<Container> pilha5 = new ArrayList<>();
        pilha5.add(a2);

        ArrayList<Container> pilha6 = new ArrayList<>();
        pilha6.add(c2);

        ArrayList<Container> pilha7 = new ArrayList<>();
        pilha7.add(b2);


        ArrayList<ArrayList<Container>> porto2 = new ArrayList<>();
        porto2.add(pilha5);
        porto2.add(pilha6);
        porto2.add(pilha7);

        Port p2 = new Port(porto2);

        assertFalse(p.isGoal(p2));
    }

    /**
     * Testa getG e setCusto da classe Port
     */
    @Test
    public void testPortGetG() {
        Container c1 = new Container('A');
        Container c2 = new Container('B');
        Container c3 = new Container('C');

        ArrayList<Container> pilha1 = new ArrayList<>();
        pilha1.add(c1);
        pilha1.add(c2);

        ArrayList<Container> pilha2 = new ArrayList<>();
        pilha2.add(c3);

        ArrayList<ArrayList<Container>> porto1 = new ArrayList<>();
        porto1.add(pilha1);
        porto1.add(pilha2);

        Port p1 = new Port(porto1);

        p1.setCusto(10);
        assertEquals(p1.getG(),10);

        p1.setCusto(100);
        assertNotEquals(p1.getG(),5);

        p1.setCusto(10);
        assertNotEquals(p1.getG(),5);
    }

    /**
     * Testa a eficiência de solve da classe BestFirst, nomeadamente se a descoberta da solucao demora menos que 1 segundo
     * String inicial: A1B2 C1D3
     * String final: DB CA
     */
    @Test
    public void testEfficiencyBestFirstSolve1(){
        long tI = System.currentTimeMillis();

        BestFirst s = new BestFirst();

        String inicial = "A1B2 C1D3";
        String objetivo = "DB CA";

        String[] params_i = inicial.split(" ");
        String[] params_o = objetivo.split(" ");

        ArrayList<ArrayList<Container>> portoI= new ArrayList<>();
        ArrayList<ArrayList<Container>> portoF= new ArrayList<>();


        for (String string : params_i) {
            ArrayList<Container> pilha = new ArrayList<>();
            for ( int i = 0; i < string.length(); i+=2) {
                pilha.add(new Container(string.charAt(i), (Character.getNumericValue(string.charAt(i + 1)))));
            }
            portoI.add(pilha);
        }

        Port pI = new Port(portoI);


        for (String string : params_o) {
            ArrayList<Container> pilha = new ArrayList<>();
            for ( int i = 0; i < string.length(); i++) {
                pilha.add(new Container(string.charAt(i)));
            }
            portoF.add(pilha);

        }

        Port pF = new Port(portoF);



        Iterator<BestFirst.State> it = s.solve(pI, pF);

        long tF = System.currentTimeMillis();

        long decorrido = (long) ((tF - tI) / 1000.0);

        assertTrue(Math.round(decorrido) <= 1);
    }

    /**
     * Testa a eficiência de solve da classe BestFirst, nomeadamente se a descoberta da solucao demora menos que 1 segundo
     * String inicial:
     * String final:
     */
    @Test
    public void testEfficiencyBestFirstSolve2(){
        long tI = System.currentTimeMillis();

        BestFirst s = new BestFirst();

        String inicial = "A1B1 C1 D1 E1 F1 G1 H1";
        String objetivo = "BA C D E F G H";

        String[] params_i = inicial.split(" ");
        String[] params_o = objetivo.split(" ");

        ArrayList<ArrayList<Container>> portoI= new ArrayList<>();
        ArrayList<ArrayList<Container>> portoF= new ArrayList<>();


        for (String string : params_i) {
            ArrayList<Container> pilha = new ArrayList<>();
            for ( int i = 0; i < string.length(); i+=2) {
                pilha.add(new Container(string.charAt(i), (Character.getNumericValue(string.charAt(i + 1)))));
            }
            portoI.add(pilha);
        }

        Port pI = new Port(portoI);


        for (String string : params_o) {
            ArrayList<Container> pilha = new ArrayList<>();
            for ( int i = 0; i < string.length(); i++) {
                pilha.add(new Container(string.charAt(i)));
            }
            portoF.add(pilha);

        }

        Port pF = new Port(portoF);



        Iterator<BestFirst.State> it = s.solve(pI, pF);

        long tF = System.currentTimeMillis();

        long decorrido = (long) ((tF - tI) / 1000.0);

        assertTrue(Math.round(decorrido) <= 1);
    }
}