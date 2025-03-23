import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Classe de testes para a classe Port
 * @author Diogo Damasio, Goncalo Rodrigues
 */
class PortTest {

    private Port portInicial1;
    private Port portInicial2;
    private Port portObjetivo1;
    private Port portObjetivo2;

    /**
     * Forma cada Port
     */
    @BeforeEach
    void setUp() {
        // Configurações iniciais e objetivos para cada caso de teste
        portInicial1 = new Port('i', "A1B2C3 D4E5F6 G7H8I9");
        portObjetivo1 = new Port('o', "IFC BEH AG D");

        portInicial2 = new Port('i', "X1Y2Z3 P4Q5R6 S7T8U9");
        portObjetivo2 = new Port('o', "S P Q R Z");
    }

    /**
     * Testa metodo isGoal
     */
    @Test
    void testIsGoal() {
        // Verificar se o estado inicial é igual ao objetivo
        assertFalse(portInicial1.isGoal(portObjetivo1));
        assertTrue(portObjetivo1.isGoal(portObjetivo1));

        // Segundo conjunto de teste
        assertFalse(portInicial2.isGoal(portObjetivo2));
        assertTrue(portObjetivo2.isGoal(portObjetivo2));
    }

    /**
     * Testa metodo children
     */
    @Test
    void testChildren() {
        // Gera sucessores do layout inicial e verifica se são válidos
        List<Ilayout> children1 = portInicial1.children();
        List<Ilayout> children2 = portInicial2.children();

        assertNotNull(children1);
        assertTrue(children1.size() > 0);

        assertNotNull(children2);
        assertTrue(children2.size() > 0);
    }


    /**
     * Testa metodo heuristica
     */
    @Test
    void testHeuristica() {
        // Calcula a heurística entre estado inicial e objetivo
        double heuristica1 = portInicial1.heuristica(portObjetivo1);
        double heuristica2 = portInicial2.heuristica(portObjetivo2);

        assertTrue(heuristica1 >= 0);
        assertTrue(heuristica2 >= 0);
    }

    /**
     * Testa metodo equals
     */
    @Test
    void testEquals() {
        // Verifica a igualdade entre estados do porto
        Port cloneObjetivo1 = new Port('o', "IFC BEH AG D");
        Port cloneObjetivo2 = new Port('o', "S P Q R Z");

        assertEquals(portObjetivo1, cloneObjetivo1);
        assertEquals(portObjetivo2, cloneObjetivo2);

        assertNotEquals(portInicial1, portObjetivo1);
        assertNotEquals(portInicial2, portObjetivo2);
    }

    /**
     * Testa metodo hashCode
     */
    @Test
    void testHashCode() {
        // Verifica se o hashCode é consistente para estados equivalentes
        Port cloneObjetivo1 = new Port('o', "IFC BEH AG D");
        Port cloneObjetivo2 = new Port('o', "S P Q R Z");

        assertEquals(portObjetivo1.hashCode(), cloneObjetivo1.hashCode());
        assertEquals(portObjetivo2.hashCode(), cloneObjetivo2.hashCode());
    }
}
