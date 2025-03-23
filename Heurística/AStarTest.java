import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Classe de testes para o algoritmo A*
 * @author Diogo Damasio, Goncalo Rodrigues
 */
class AStarTest {

    private AStar aStar;
    private Port portInicial;
    private Port portObjetivo;

    /**
     * Teste H2 do Mooshak
     */
    @Test
    public void testeH2(){
        aStar = new AStar();
        // Configuração inicial e objetivo usando o input especificado
        portInicial = new Port('i', "A1B2C3 D4E5F6 G7H8I9");
        portObjetivo = new Port('o', "IFC BEH AG D");

        // Testa se o algoritmo encontra uma solução para o input especificado
        Iterator<AStar.State> it = aStar.solve(portInicial, portObjetivo);

        AStar.State i = it.next();

        assertEquals(portObjetivo.toString(), i.toString());

        // Verificar o custo total
        assertEquals(40, (int) i.getG());
    }

    /**
     * Teste H3 do Mooshak
     */
    @Test
    public void testeH3(){
        aStar = new AStar();
        // Configuração inicial e objetivo usando o input especificado
        portInicial = new Port('i', "A1 B2 C3 D4 E5 F6 G7 H8 I9");
        portObjetivo = new Port('o', "IHGFEDCBA");

        // Testa se o algoritmo encontra uma solução para o input especificado
        Iterator<AStar.State> it = aStar.solve(portInicial, portObjetivo);

        AStar.State i = it.next();

        assertEquals(portObjetivo.toString(), i.toString());

        // Verificar o custo total
        assertEquals(36, (int) i.getG());
    }

    /**
     * Teste H4 do Mooshak
     */
    @Test
    public void testeH4(){
        aStar = new AStar();
        // Configuração inicial e objetivo usando o input especificado
        portInicial = new Port('i', "A9B9C9D9E9F9 G1H1I6 J1 K2L2 M1 N1O1P1Q1");
        portObjetivo = new Port('o', "FILMJKEPN GHDCBAQO");

        // Testa se o algoritmo encontra uma solução para o input especificado
        Iterator<AStar.State> it = aStar.solve(portInicial, portObjetivo);

        AStar.State i = it.next();

        assertEquals(portObjetivo.toString(), i.toString());

        // Verificar o custo total
        assertEquals(70, (int) i.getG());
    }


    /**
     * Teste H5 do Mooshak
     */
    @Test
    public void testeH5(){
        aStar = new AStar();
        // Configuração inicial e objetivo usando o input especificado
        portInicial = new Port('i', "A9I8Q7Y6 B5J4R3Z2 C1K2S3 D4L5T6 E7M8U9 F1N2V3 G1O2W3 H3P2X1");
        portObjetivo = new Port('o', "YZXW OQSTUV NMLKRI AJCDEF G B HP");

        // Testa se o algoritmo encontra uma solução para o input especificado
        Iterator<AStar.State> it = aStar.solve(portInicial, portObjetivo);

        AStar.State i = it.next();

        assertEquals(portObjetivo.toString(), i.toString());

        // Verificar o custo total
        assertEquals(87, (int) i.getG());
    }

    /**
     * Teste H6 do Mooshak
     */
    @Test
    public void testeH6(){
        aStar = new AStar();
        // Configuração inicial e objetivo usando o input especificado
        portInicial = new Port('i', "B9b5A2a7 E3 e3 G1H5L9 M8m4N3O7o5 S3s1T5 R1 V9C4c5");
        portObjetivo = new Port('o', "BOM TRAbaLH VCS coNsEGem");

        // Testa se o algoritmo encontra uma solução para o input especificado
        Iterator<AStar.State> it = aStar.solve(portInicial, portObjetivo);

        AStar.State i = it.next();

        assertEquals(portObjetivo.toString(), i.toString());

        // Verificar o custo total
        assertEquals(84, (int) i.getG());
    }

    /**
     * Teste H7 do Mooshak
     */
    @Test
    public void testeH7(){
        aStar = new AStar();
        // Configuração inicial e objetivo usando o input especificado
        portInicial = new Port('i', "A1B1C1D1E1F1G1H1I1J1K1L1M1N1O1P1Q1R1S1T1U1V1W1X1Y1Z1a1b1c1d1e1f1g1h1i1j1k1l1m1n1o1p1q1r1s1t1u1v1w1x1y1z1");
        portObjetivo = new Port('o', "zyxwvutsrqEDCBA cab edQPONMLKJI ZYXWVUTSRH gfGF kjih ponml");

        // Testa se o algoritmo encontra uma solução para o input especificado
        Iterator<AStar.State> it = aStar.solve(portInicial, portObjetivo);

        AStar.State i = it.next();

        assertEquals(portObjetivo.toString(), i.toString());

        // Verificar o custo total
        assertEquals(53, (int) i.getG());
    }

    /**
     * Teste H9 do Mooshak
     */
    @Test
    public void testeH9(){
        aStar = new AStar();
        // Configuração inicial e objetivo usando o input especificado
        portInicial = new Port('i', "A8B7C6D5E4F3G2H1I9J8K7L6M5N4O3P2Q1R2S3T4U5V6W7X8Y9Z1");
        portObjetivo = new Port('o', "ZYXWVU MNOPQRS LKJIHGF ABCDET");

        // Testa se o algoritmo encontra uma solução para o input especificado
        Iterator<AStar.State> it = aStar.solve(portInicial, portObjetivo);

        AStar.State i = it.next();

        assertEquals(portObjetivo.toString(), i.toString());

        // Verificar o custo total
        assertEquals(115, (int) i.getG());
    }

    /**
     * Teste H10 do Mooshak
     */
    @Test
    public void testeH10(){
        aStar = new AStar();
        // Configuração inicial e objetivo usando o input especificado
        portInicial = new Port('i', "A9B2C3D4 E7 F3 G2H5I8J1K6 L1 M2N5O6 P9 Q2R5 S7 T5U4V7W1 X1Y3Z4");
        portObjetivo = new Port('o', "AKSQ E TRJI DOUH MN BFGPL VC W XYZ");

        // Testa se o algoritmo encontra uma solução para o input especificado
        Iterator<AStar.State> it = aStar.solve(portInicial, portObjetivo);

        AStar.State i = it.next();

        assertEquals(portObjetivo.toString(), i.toString());

        // Verificar o custo total
        assertEquals(76, (int) i.getG());
    }



    /**
     * Testa a eficiência de solve da classe A*, nomeadamente se a descoberta da solucao demora menos que 1 segundo
     * String inicial: A1B2 C1D3
     * String final: DB CA
     */
    @Test
    public void testEfficiencyAStarSolve1(){
        long tI = System.currentTimeMillis();

        AStar s = new AStar();

        String inicial = "A1B2 C1D3";
        String objetivo = "DB CA";

        Port pI = new Port('i',inicial);

        Port pF = new Port('o',objetivo);



        Iterator<AStar.State> it = s.solve(pI, pF);

        long tF = System.currentTimeMillis();

        long decorrido = (long) ((tF - tI) / 1000.0);

        assertTrue(Math.round(decorrido) <= 1);
    }

    /**
     * Testa a eficiência de solve da classe AStar, nomeadamente se a descoberta da solucao demora menos que 1 segundo
     * String inicial: "A1B1 C1 D1 E1 F1 G1 H1"
     * String final: "BA C D E F G H"
     */
    @Test
    public void testEfficiencyAStarSolve2(){
        long tI = System.currentTimeMillis();

        AStar s = new AStar();

        String inicial = "A1B1 C1 D1 E1 F1 G1 H1";
        String objetivo = "BA C D E F G H";

        Port pI = new Port('i',inicial);

        Port pF = new Port('o', objetivo);



        Iterator<AStar.State> it = s.solve(pI, pF);

        long tF = System.currentTimeMillis();

        long decorrido = (long) ((tF - tI) / 1000.0);

        assertTrue(Math.round(decorrido) <= 1);
    }

}