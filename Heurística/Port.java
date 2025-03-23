import java.util.*;


/**
 * A classe Port implementa um porto maritimo, onde os contentores irao estar inseridos em varias pilhas.
 * Implementa duas interfaces: Ilayout para definir comportamentos comuns a outras classes e Cloneable para criacao de clones.
 *
 * @author Diogo Damasio 79826, Goncalo Rodrigues 79833
 * @version 2.0
 */
public class Port implements Ilayout, Cloneable {

    private ArrayList<ArrayList<Character>> contentores;
    private Map<Character, Integer> custoMap; // Mapa para pesos dos contentores
    private int custo = 0;    //custo de transicao
    private Integer cachedHashCode = null;
    private String cachedToString = null;


    /**
     * Construtor para a classe Port que cria segundo a entrada do utilizador
     *
     * @param opcao  Define se e a configuracao inicial ou objetivo ('i' - inicial, 'o' - objetivo)
     * @param pilhas Entrada do utilizador
     */
    public Port(char opcao, String pilhas) {

        this.custoMap = new HashMap<>(); // Inicializa o mapa de custos
        this.contentores = new ArrayList<>(52);

        for (int i = 0; i < 52; i++) {
            contentores.add(new ArrayList<>());
        }

        String[] params = pilhas.split(" ");

        if (opcao == 'i') {
            for (String string : params) {
                ArrayList<Character> pilha = new ArrayList<>();
                for (int i = 0; i < string.length(); i += 2) {
                    char nome = string.charAt(i); // Nome do contentor
                    int custo = Character.getNumericValue(string.charAt(i + 1)); // Custo do contentor
                    pilha.add(nome);
                    custoMap.put(nome, custo); // Adiciona o custo ao mapa
                }
                char nome = pilha.get(0);
                int pos = calculaPosicao(nome);
                contentores.set(pos, pilha);
            }
        } else if (opcao == 'o') {
            for (String string : params) {
                ArrayList<Character> pilha = new ArrayList<>();
                for (int i = 0; i < string.length(); i++) {
                    char nome = string.charAt(i); // Apenas adiciona os nomes
                    pilha.add(nome);
                }
                char nome = pilha.get(0);
                int pos = calculaPosicao(nome);
                contentores.set(pos, pilha);
            }
        } else {
            System.err.println("Opcao invalida");
            System.exit(0);
        }

    }


    /**
     * Permite calcular a posicao de uma pilha no Port com base no nome do contentor da base,
     * organizando-os na ordem A-Z(0 - 25) a-z(26 - 51)
     *
     * @param nome nome do contentor
     * @return indice onde o contentor devera ser colocado
     */
    private int calculaPosicao(char nome) {
        return Character.isUpperCase(nome) ? nome - 'A' : 26 + (nome - 'a');
    }


    /**
     * Setter para o custo
     *
     * @param custo Novo custo (peso do contentor movido)
     */
    public void setCusto(int custo) {

        this.custo = custo;

    }


    /**
     * Fornece uma representacao do porto em forma de string
     *
     * @return representacao em string
     */
    @Override
    public String toString() {
        if (cachedToString == null) {
            StringBuilder result = new StringBuilder();
            for (ArrayList<Character> pilha : this.contentores) {                                                           //percorre todas as pilhas
                if (!pilha.isEmpty()) {
                    result.append("[");
                    for (int i = 0; i < pilha.size(); i++) {
                        result.append(pilha.get(i));
                        if (i < pilha.size() - 1) {                                                                         //ate a penultima pq ultima nao leva virgula
                            result.append(", ");
                        }
                    }
                    result.append("]\n");
                }
            }
            cachedToString = result.toString();
        }

        return cachedToString;
    }

    /**
     * Verifica a igualdade entre dois portos. Sao iguais se as suas representacoes em string forem iguais
     *
     * @param obj Outro porto
     * @return true se forem iguais e false cc
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Port that = (Port) obj;

        // Compara o conteúdo de cada pilha
        for (int i = 0; i < 52; i++) {
            ArrayList<Character> thisPilha = this.contentores.get(i);
            ArrayList<Character> thatPilha = that.contentores.get(i);

            if (!thisPilha.equals(thatPilha)) {
                return false; // Retorna false se qualquer pilha não é igual
            }
        }

        return true; // Retorna true se todas as pilhas são iguais
    }


    /**
     * Gera codigo hash para porto
     *
     * @return Hashcode (int)
     */
    @Override
    public int hashCode() {

        if (cachedHashCode == null) {

            cachedHashCode = contentores.hashCode();

        }

        return cachedHashCode;

    }


    /**
     * Permite criar clones de portos (copias profundas)
     *
     * @return Clone do porto
     * @throws CloneNotSupportedException se nao for possivel clonar
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {

        Port copia = (Port) super.clone();

        copia.contentores = new ArrayList<>(52);

        for (ArrayList<Character> pilha : this.contentores) {
            copia.contentores.add(new ArrayList<>(pilha));
        }
        copia.invalidarCache();

        return copia;
    }


    /**
     * Apaga dados em cache nomeadamente a representacao em String e codigo hash
     */
    private void invalidarCache() {
        this.cachedToString = null;
        this.cachedHashCode = null;
    }


    /**
     * Gera todos os "filhos" do porto atual, ou seja, todas as possiveis sucessoes do porto atual movendo apenas um contentor
     *
     * @return Lista de sucessores
     */
    @Override
    public List<Ilayout> children() {
        List<Ilayout> children = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            ArrayList<Character> origem = contentores.get(i);

            if (!origem.isEmpty()) {
                char mover = origem.get(origem.size() - 1); // Contentor do topo a ser movido
                int custoMover = custoMap.get(mover);

                for (int j = 0; j < 52; j++) {
                    if (i != j && isMoveValid(i, j)) { // Validar se o movimento é necessário
                        Port newPort = cloneWithMove(i, j, custoMover);
                        children.add(newPort);
                    }
                }
            }
        }
        return children;
    }

    /**
     * Cria um Port filho movendo o contentor para um determinado sitio e define o seu custo para o peso do contentor movido
     *
     * @param origemIdx  indice da pilha de origem
     * @param destinoIdx indice da pilha de destino
     * @param custoMover peso do contentor
     * @return novo port filho
     */
    private Port cloneWithMove(int origemIdx, int destinoIdx, int custoMover) {
        try {
            Port newPort = (Port) this.clone();
            newPort.move(newPort.contentores.get(origemIdx), newPort.contentores.get(destinoIdx));
            newPort.setCusto(custoMover);
            return newPort;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica se o movimento e valido: se a pilha de destino estiver vazia, verifica se e a correspondente aquele contentor
     *
     * @param origemIdx  indice da pilha de origem
     * @param destinoIdx indice da pilha de destino
     * @return true se for valido e false caso nao seja
     */
    private boolean isMoveValid(int origemIdx, int destinoIdx) {
        ArrayList<Character> origem = contentores.get(origemIdx);
        ArrayList<Character> destino = contentores.get(destinoIdx);
        char mover = origem.get(origem.size() - 1);

        if (destino.isEmpty())
            return calculaPosicao(mover) == destinoIdx;
        return true;
    }


    /**
     * Funcao auxiliar para mover um contentor de uma pilha para outra
     *
     * @param origem  Pilha de origem
     * @param destino Pilha de destino
     */
    private void move(ArrayList<Character> origem, ArrayList<Character> destino) {

        char container = origem.remove(origem.size() - 1);
        destino.add(container);

    }

    /**
     * Verifica se o porto atual e a solucao
     *
     * @param l O layout que representa o estado objetivo.
     * @return true se for e false cc
     */
    @Override
    public boolean isGoal(Ilayout l) {                                                                                  //basicamente e um equals comparando com objetivo
        return this.equals(l);
    }


    /**
     * Getter para o atributo custo (acumulado)
     *
     * @return custo
     */
    @Override
    public double getG() {
        return custo;
    }


    /**
     * Calcula a heurística para estimar o custo de mover o layout atual do porto em direção ao layout objetivo,
     * baseando-se na soma dos pesos dos contentores que estao fora do sitio
     *
     * @param goal Layout final
     * @return valor da heuristica
     */
    @Override
    public double heuristica(Ilayout goal) {
        Port objetivo = (Port) goal;

        double heuristica = 0;

        // Itera sobre todas as 52 pilhas
        for (int i = 0; i < 52; i++) {
            ArrayList<Character> pilhaAtual = contentores.get(i);
            ArrayList<Character> pilhaObjetivo = objetivo.contentores.get(i);

            // Verifica se a pilha não está vazia
            if (!pilhaAtual.isEmpty()) {

                for (int j = 0; j < pilhaAtual.size(); j++) {
                    char atual = pilhaAtual.get(j);

                    // Adiciona o peso de todos os contentores fora de posição
                    if (j >= pilhaObjetivo.size() || atual != pilhaObjetivo.get(j)) {
                        int peso = custoMap.get(atual);
                        heuristica += peso;

                        if (peso >= 4) {
                            heuristica += (double) 1 / peso;
                            if (j <= 3)
                                heuristica += (j + 1) * 2;
                            else
                                heuristica += j + 1;
                        }
                    }
                }
            }
        }

        return heuristica;
    }




}