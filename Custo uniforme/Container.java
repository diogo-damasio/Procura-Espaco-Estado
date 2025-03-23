/**
 * A classe Container implementa a representacao de um contentor.
 * nome - caracter identificativo do contentor
 * peso - peso do contentor
 * @author Diogo Damasio 79826
 * @version 1.0
 * @inv peso >= 0
 */
public class Container {

    char nome;
    int peso;

    /**
     * Construtor, cria um contentor com o nome e pesos fornecidos
     * @param nome char
     * @param peso int
     */
    public Container(char nome, int peso) {

        this.nome = nome;
        this.peso = Math.abs(peso);
    }

    /**
     * Construtor, cria um contentor com o nome fornecido e peso = 0
     * @param nome char
     */
    public Container(char nome) {

        this.nome = nome;
        this.peso = 0;

    }

    /**
     * Getter para o nome
     * @return nome (char)
     */
    public char getNome() {

        return nome;

    }

    /**
     * Getter para peso
     * @return peso (int)
     */
    public int getPeso() {

        return peso;

    }

    /**
     * Verifica a igualdade entre dois contentores. Sao iguais se tiverem o mesmo nome
     * @param obj Outro contentor
     * @return true se forem iguais e false cc
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Container outro = (Container) obj;
        return this.nome == outro.nome;

    }

    /**
     * Gera codigo hash para contentor
     * @return Hashcode (int)
     */
    @Override
    public int hashCode() {

        return Character.hashCode(nome);

    }
}
