import java.util.*;

/**
 * A interface Ilayout representa o layout de um porto maritimo.
 * Define métodos que devem ser implementados para manipular o layout e calcular
 * as suas propriedades.
 * @author Diogo Damásio, Gonçalo Rodrigues
 * @version 1.0
 */
interface Ilayout
{
    /**
     * Retorna os filhos (ou estados sucessores) do layout atual.
     *
     * @return Uma lista de objetos Ilayout que representam os estados filhos do layout atual.
     */
    List<Ilayout> children();

    /**
     * Verifica se o layout atual corresponde ao estado objetivo fornecido.
     *
     * @param l O layout que representa o estado objetivo.
     * @return true se o layout atual for igual ao layout objetivo l; false caso contrário.
     */
    boolean isGoal(Ilayout l);

    /**
     * Retorna o custo de movimento do layout atual em relação a um layout de entrada.
     *
     * @return O custo de movimentação do estado de entrada para o layout atual.
     */
    double getG();
}
