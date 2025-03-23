import java.util.*;

/**
 * A classe Port implementa um porto maritimo, onde os contentores irao estar inseridos em varias pilhas.
 * Implementa duas interfaces: Ilayout para definir comportamentos comuns a outras classes e Cloneable para criacao de clones.
 * contentores - conjunto das pilhas de contentores
 * custo - peso do contentor movido em cada passo (inicialmente 0)
 * @author Diogo Damasio 79826
 * @version 1.0
 */
public class Port implements Ilayout, Cloneable {

    private ArrayList<ArrayList<Container>> contentores;
    private int custo = 0;


    /**
     * Construtor, cria um porto com as pilhas fornecidas
     * @param contentores Lista de listas de contentores
     */
    public Port(ArrayList<ArrayList<Container>> contentores) {

        this.contentores = new ArrayList<>();

        for (ArrayList<Container> pilha : contentores) {

            this.contentores.add( new ArrayList<>(pilha) );

        }
    }

    /**
     * Getter para a lista de pilhas de contentores
     * @return lista de pilhas de contentores (ArrayList<ArrayList<Container>>)
     */
    public ArrayList<ArrayList<Container>> getContentores() {
        return this.contentores;
    }


    /**
     * Setter para o custo
     * @param custo Novo custo (peso do contentor movido)
     */
    public void setCusto(int custo) {

         this.custo = custo;

    }

    //fazer funcao order




    /**
     * Fornece uma representacao do porto em forma de string, ordenando as pilhas por ordem lexicografica do elemento da base
     * @return representacao em string
     */
    @Override
    public String toString() {
        this.contentores.sort((ArrayList<Container> a1, ArrayList<Container> a2) -> {                                   //ordena as pilhas
            if (!a1.isEmpty() && !a2.isEmpty()) {
                return Character.compare(a1.get(0).getNome(), a2.get(0).getNome());                                     //compara os elementos da base (chars)
            }
            return 0;
        });

        StringBuilder result = new StringBuilder();
        for (ArrayList<Container> pilha : this.contentores) {                                                           //percorre todas as pilhas
            if(!pilha.isEmpty()) {
                result.append("[");
                for (int i = 0; i < pilha.size(); i++) {
                    result.append(pilha.get(i).getNome());
                    if (i < pilha.size() - 1) {                                                                         //ate a penultima pq ultima nao leva virgula
                        result.append(", ");
                    }
                }
                result.append("]\n");
            }
        }
        return result.toString();
    }

    /**
     * Verifica a igualdade entre dois portos. Sao iguais se as suas representacoes em string forem iguais
     * @param obj Outro porto
     * @return true se forem iguais e false cc
     */
    @Override
    public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Port port1 = (Port) obj;
            return Objects.equals(toString(), port1.toString());

    }

    /**
     * Gera codigo hash para porto
     * @return Hashcode (int)
     */
    @Override
    public int hashCode() {

        return Objects.hash(toString());

    }


    /**
     * Permite criar clones de portos (copias profundas)
     * @return Clone do porto
     * @throws CloneNotSupportedException se nao for possivel clonar
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {

        Port copia = (Port) super.clone();

        copia.contentores = new ArrayList<>();

        for (ArrayList<Container> pilha : this.contentores) {
            copia.contentores.add(new ArrayList<>(pilha));
        }

        return copia;
    }




    /**
     * Gera todos os "filhos" do porto atual, ou seja, todas as possiveis sucessoes do porto atual movendo apenas um contentor
     * @return Lista de sucessores
     */
    @Override
    public List<Ilayout> children() {
        List<Ilayout> children = new ArrayList<>();                                                                     //cria lista de filhos

        for (int i = 0; i < contentores.size(); i++) {
            ArrayList<Container> origem = contentores.get(i);                                                           //em cada iteracao seleciona uma origem diferente

            if (!origem.isEmpty()) {
                Container mover = origem.get(origem.size() - 1);                                                        //contentor que vai ser movido (topo de origem)

                for (int j = 0; j < contentores.size(); j++) {
                    if (i != j) {                                                                                       //itera sobre todas as OUTRAS pilhas (i!=j)

                        Port newPort;

                        try {
                            newPort = (Port) this.clone();                                                              //cria clone
                        } catch (CloneNotSupportedException e) {
                            throw new RuntimeException(e);
                        }

                        move(newPort.contentores.get(i),newPort.contentores.get(j));                                    //move de origem p destino

                        newPort.setCusto(mover.getPeso());                                                              //atualiza custo

                        if (newPort.contentores.get(i).isEmpty()) {                                                     //remove listas vazias
                            newPort.contentores.remove(i);
                        }

                        children.add(newPort);                                                                          //adiciona clone a lista de filhos
                    }
                }


                if (origem.size() > 1) {                                                                                //se a pilha de origem tiver pelo menos 2 elementos
                    Port newPort;                                                                                       //pode mover o topo p uma nova pilha
                    //se so tiver um elemento nao faz sentido mover
                    try {                                                                                               //fica a mesma coisa
                        newPort = (Port) this.clone();
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }

                    newPort.contentores.add(new ArrayList<Container>());                                                //mesmo raciocinio de antes

                    move(newPort.contentores.get(i), newPort.contentores.get(newPort.contentores.size() - 1));

                    newPort.setCusto(mover.getPeso());

                    children.add(newPort);
                }
            }
        }

        return children;
    }



    /**
     * Funcao auxiliar para mover um contentor de uma pilha para outra
     * @param origem Pilha de origem
     * @param destino Pilha de destino
     */
    private void move(ArrayList<Container> origem, ArrayList<Container> destino) {

        Container c = origem.remove(origem.size()-1);
        destino.add(c);

    }

    /**
     * Verifica se o porto atual e a solucao
     * @param l O layout que representa o estado objetivo.
     * @return true se for e false cc
     */
    @Override
    public boolean isGoal(Ilayout l) {                                                                                  //basicamente e um equals comparando com objetivo
        return this.equals(l);                                                                                          //kinda useless :(
    }


    /**
     * Getter para o atributo custo (acumulado)
     * @return custo
     */
    @Override
    public double getG() {
        return custo;
    }
}
