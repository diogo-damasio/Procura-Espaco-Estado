import java.util.*;


/**
 * A classe AStar implementa o algoritmo de busca A* para resolver o problema dos contentores.
 * Utiliza uma fila de prioridade para explorar os estados e encontra uma solução, se existir,
 * a partir de um estado inicial até atingir um estado objetivo.
 * @author Diogo Damásio 79826, Gonçalo Rodrigues 79833
 * @version 1.0
 */
class AStar
{
    protected Queue<State> abertos;
    private Map<Ilayout, State> fechados;

    private State actual;
    private static Ilayout objective;

    /**
     * A classe interna State representa um estado de um porto, armazenando o layout, o pai do estado e o custo para
     * chegar a esse estado.
     */
    static class State
    {
        private Ilayout layout;
        private State father;
        private double g; // custo acumulado
        private double h; //heuristica

        /**
         * Construtor que inicializa um novo estado com base num layout e no estado pai.
         *
         * @param l O layout do estado atual.
         * @param n O estado pai, que gerou o estado atual.
         */
        public State(Ilayout l, State n)
        {
            layout = l;
            father = n;
            if (father != null)
            {
                g = father.g + l.getG();
            }
            else

            {
                g = 0.0;
            }

            h = l.heuristica(objective);
        }

        /**
         * Retorna o custo do estado atual.
         *
         * @return O valor de g, que representa o custo acumulado.
         */
        public double getG() {
            return g;
        }


        /**
         * Retorna o resultado da funcao f = custo acumulado + heuristica
         * @return o valor de f = g + h
         */
        public double getF(){
            return g+h;
        }

        /**
         * Retorna uma representação textual do layout do estado.
         *
         * @return Uma string que representa o estado atual.
         */
        @Override
        public String toString() {
            return layout.toString();
        }

        /**
         * Compara se dois estados são iguais com base no layout.
         *
         * @param o O objeto a ser comparado.
         * @return true se os layouts forem iguais, false caso contrário.
         */
        @Override
        public boolean equals(Object o)
        {
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            State n = (State) o;
            return this.layout.equals(n.layout);
        }

        /**
         * Gera um código hash para o estado com base no layout.
         *
         * @return O código hash gerado.
         */
        @Override
        public int hashCode() {
            return toString().hashCode();
        }
    }


    /**
     * Gera os estados sucessores para um determinado estado.
     *
     * @param n O estado para o qual serão gerados sucessores.
     * @return Uma lista de estados sucessores.
     */
    private List<State> sucessores(State n)
    {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for (Ilayout e : children)
        {
            if (n.father == null || !e.equals(n.father.layout))
            {
                State nn = new State(e, n);
                sucs.add(nn);
            }
        }
        return sucs;
    }

    /**
     * Resolve o problema utilizando o algoritmo A*.
     *
     * @param s O layout inicial.
     * @param goal O layout objetivo.
     * @return Um iterador sobre os estados que formam a solução, ou null se não houver solução.
     */
    public Iterator<State> solve(Ilayout s, Ilayout goal)
    {
        objective = goal;

        abertos = new PriorityQueue<>(10, (s1, s2) -> {
            int comp = Double.compare(s1.getF(),s2.getF());
            if (comp == 0) return Double.compare(s1.h,s2.h);
            return comp;                                                                                                //priority queue ordenada por oc de f e depois h
        });


        fechados = new HashMap<>();
        abertos.add(new State(s, null));                                                                             //adiciona o estado inicial
        List<State> sucs;
        Map<Ilayout,State> mAbertos = new HashMap<>();

        while (!abertos.isEmpty())                                                                                      //equanto houver estados a ser explorados
        {
            actual = abertos.poll();                                                                                    //remove o primeiro elemento

            if (actual.layout.isGoal(objective))                                                                        //se o atual for objetivo
            {
                List<State> solution = new ArrayList<>();
                while (actual != null)
                {
                    solution.add(actual);                                                                               //adiciona o a solucao, atual passa a ser
                    actual = actual.father;                                                                             //o pai deste e repete ate ao inicial (pai
                }                                                                                                       //do inicial e nulo)

                return solution.iterator();                                                                             //retorna iterador sobre a solucao
            }

            fechados.put(actual.layout, actual);                                                                        //adiciona os estados explorados a fechados
            mAbertos.remove(actual.layout, actual);                                                                     //remove do mapa de abertos

            sucs = sucessores(actual);                                                                                  //gera os sucessores do estado atual
            for (State suc : sucs)
            {
                if (!fechados.containsKey(suc.layout) && !mAbertos.containsKey(suc.layout))                             //se nao estiver em fechados (ja foi testado)
                {                                                                                                       //nem em abertos (ja foi gerado) adicionar a
                    abertos.add(suc);                                                                                   //fila e ao mapa
                    mAbertos.put(suc.layout,suc);
                }

            }
        }
        return null;
    }
}