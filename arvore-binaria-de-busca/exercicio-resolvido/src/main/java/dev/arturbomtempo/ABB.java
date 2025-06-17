package dev.arturbomtempo;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Implementação de uma Árvore Binária de Busca (ABB) genérica, que permite
 * armazenar pares chave-valor de forma ordenada conforme um comparador.
 * 
 * Esta estrutura não permite chaves duplicadas e oferece operações eficientes
 * de inserção, pesquisa, remoção e percurso em ordem.
 * 
 * @param <K> Tipo da chave (deve ser comparável ou ter um Comparator definido).
 * @param <V> Tipo do valor associado à chave.
 */
public class ABB<K, V> implements IMapeamento<K, V> {

    private No<K, V> raiz;
    private Comparator<K> comparador;
    private int tamanho;

    /**
     * Inicializa a árvore binária de busca, definindo a raiz como nula e o tamanho
     * como zero.
     * Se nenhum comparador for fornecido, utiliza o comparador natural das chaves.
     *
     * @param comparador Comparador a ser usado para ordenar as chaves, ou null para
     *                   ordem natural.
     */
    @SuppressWarnings("unchecked")
    private void init(Comparator<K> comparador) {
        raiz = null;
        tamanho = 0;

        if (comparador == null) {
            comparador = (Comparator<K>) Comparator.naturalOrder();
        }

        this.comparador = comparador;
    }

    /**
     * Cria uma árvore binária de busca utilizando o comparador padrão (ordem
     * natural das chaves).
     */
    public ABB() {
        init(null);
    }

    /**
     * Cria uma árvore binária de busca utilizando um comparador personalizado para
     * ordenar as chaves.
     *
     * @param comparador Comparador a ser utilizado para ordenar as chaves.
     */
    public ABB(Comparator<K> comparador) {
        init(comparador);
    }

    /**
     * Verifica se a árvore está vazia, ou seja, se não possui nenhum elemento
     * inserido.
     *
     * @return true se a árvore não possui elementos, false caso contrário.
     */
    public Boolean vazia() {
        return this.raiz == null;
    }

    /**
     * Pesquisa recursivamente por uma chave a partir de um nó específico da árvore.
     * Compara a chave procurada com a chave do nó atual e segue para a subárvore
     * esquerda ou direita conforme necessário.
     *
     * @param raizArvore Nó atual da recursão.
     * @param procurado  Chave a ser buscada na árvore.
     * @return Valor associado à chave, se encontrado.
     * @throws NoSuchElementException se a chave não for encontrada na árvore.
     */
    private V pesquisar(No<K, V> raizArvore, K procurado) {
        int comparacao;

        if (raizArvore == null) {
            throw new NoSuchElementException("O item não foi localizado na árvore.");
        }

        comparacao = comparador.compare(procurado, raizArvore.getChave());

        if (comparacao == 0) {
            return raizArvore.getItem();
        } else if (comparacao < 0) {
            return pesquisar(raizArvore.getEsquerda(), procurado);
        } else {
            return pesquisar(raizArvore.getDireita(), procurado);
        }
    }

    /**
     * Pesquisa por uma chave na árvore a partir da raiz.
     * 
     * @param chave Chave a ser buscada.
     * @return Valor associado à chave, se encontrado.
     * @throws NoSuchElementException se a chave não for encontrada.
     */
    @Override
    public V pesquisar(K chave) {
        return pesquisar(raiz, chave);
    }

    /**
     * Insere recursivamente um novo par (chave, valor) na árvore, mantendo a
     * ordenação.
     * Se a chave já existir, lança uma exceção para evitar duplicidade.
     *
     * @param raizArvore Nó atual da recursão.
     * @param chave      Chave do novo item a ser inserido.
     * @param item       Valor associado à chave.
     * @return A nova subárvore resultante após a inserção.
     * @throws RuntimeException se a chave já existir na árvore.
     */
    private No<K, V> inserir(No<K, V> raizArvore, K chave, V item) {
        int comparacao;

        if (raizArvore == null) {
            raizArvore = new No<>(chave, item);
        } else {
            comparacao = comparador.compare(chave, raizArvore.getChave());

            if (comparacao < 0) {
                raizArvore.setEsquerda(inserir(raizArvore.getEsquerda(), chave, item));
            } else if (comparacao > 0) {
                raizArvore.setDireita(inserir(raizArvore.getDireita(), chave, item));
            } else {
                throw new RuntimeException("O item já foi inserido anteriormente na árvore.");
            }
        }

        return raizArvore;
    }

    /**
     * Insere um novo par (chave, valor) na árvore a partir da raiz.
     * 
     * @param chave Chave do novo item.
     * @param item  Valor associado à chave.
     * @return O novo tamanho da árvore após a inserção.
     * @throws RuntimeException se a chave já existir.
     */
    @Override
    public int inserir(K chave, V item) {
        this.raiz = inserir(this.raiz, chave, item);
        tamanho++;
        return tamanho;
    }

    /**
     * Realiza o caminhamento em ordem (esquerda -> raiz -> direita) recursivamente
     * a partir de um nó.
     * Retorna uma String contendo todos os valores visitados em ordem crescente das
     * chaves.
     *
     * @param raizArvore Nó atual da recursão.
     * @return String com os itens percorridos em ordem, separados por quebras de
     *         linha.
     */
    private String caminhamentoEmOrdem(No<K, V> raizArvore) {
        if (raizArvore != null) {
            String resposta = caminhamentoEmOrdem(raizArvore.getEsquerda());

            resposta += raizArvore.getItem() + "\n";
            resposta += caminhamentoEmOrdem(raizArvore.getDireita());

            return resposta;
        } else {
            return "";
        }
    }

    /**
     * Inicia o caminhamento em ordem a partir da raiz da árvore.
     * 
     * @return String com os itens da árvore em ordem crescente das chaves.
     * @throws IllegalStateException se a árvore estiver vazia.
     */
    public String caminhamentoEmOrdem() {
        if (vazia()) {
            throw new IllegalStateException("A árvore está vazia.");
        }

        return caminhamentoEmOrdem(raiz);
    }

    /**
     * Percorre a árvore em ordem e retorna uma String com todos os valores, um por
     * linha.
     * 
     * @return String com os itens da árvore em ordem.
     */
    @Override
    public String percorrer() {
        return caminhamentoEmOrdem();
    }

    /**
     * Remove recursivamente o nó com a chave informada da árvore, ajustando os
     * ponteiros para manter a estrutura da ABB.
     * Se o nó a ser removido possuir dois filhos, substitui seu valor pelo
     * antecessor imediato (maior valor da subárvore esquerda).
     *
     * @param raizArvore   Nó atual da recursão.
     * @param chaveRemover Chave do nó a ser removido.
     * @return Nova subárvore resultante após a remoção.
     * @throws NoSuchElementException se a chave não for encontrada.
     */
    private No<K, V> remover(No<K, V> raizArvore, K chaveRemover) {
        if (raizArvore == null) {
            throw new NoSuchElementException("O item a ser removido não foi localizado na árvore.");
        }

        int comparacao = comparador.compare(chaveRemover, raizArvore.getChave());

        if (comparacao == 0) {
            if (raizArvore.getDireita() == null) {
                raizArvore = raizArvore.getEsquerda();
            } else if (raizArvore.getEsquerda() == null) {
                raizArvore = raizArvore.getDireita();
            } else {
                raizArvore.setEsquerda(removerNoAntecessor(raizArvore, raizArvore.getEsquerda()));
            }
        } else if (comparacao < 0) {
            raizArvore.setEsquerda(remover(raizArvore.getEsquerda(), chaveRemover));
        } else {
            raizArvore.setDireita(remover(raizArvore.getDireita(), chaveRemover));
        }

        return raizArvore;
    }

    /**
     * Remove um elemento da árvore com base na chave informada.
     * 
     * @param chave Chave do elemento a ser removido.
     * @return Valor associado à chave removida.
     * @throws NoSuchElementException se a chave não for encontrada.
     */
    public V remover(K chave) {
        V removido = pesquisar(chave);

        raiz = remover(raiz, chave);
        tamanho--;

        return removido;
    }

    /**
     * Substitui o conteúdo do nó a ser removido pelo antecessor mais à direita da
     * subárvore esquerda.
     * Utilizado durante a remoção de um nó com dois filhos para manter a
     * propriedade da ABB.
     *
     * @param itemRetirar Nó cujo valor será substituído pelo antecessor.
     * @param raizArvore  Raiz da subárvore esquerda.
     * @return Subárvore ajustada após remoção do antecessor.
     */
    private No<K, V> removerNoAntecessor(No<K, V> itemRetirar, No<K, V> raizArvore) {
        if (raizArvore.getDireita() != null) {
            raizArvore.setDireita(removerNoAntecessor(itemRetirar, raizArvore.getDireita()));
        } else {
            itemRetirar.setChave(raizArvore.getChave());
            itemRetirar.setItem(raizArvore.getItem());
            raizArvore = raizArvore.getEsquerda();
        }

        return raizArvore;
    }

    /**
     * Retorna a quantidade de elementos atualmente armazenados na árvore.
     * 
     * @return Número de elementos na árvore.
     */
    @Override
    public int tamanho() {
        return tamanho;
    }

    /**
     * Retorna uma representação em String da árvore, mostrando todos os valores em
     * ordem.
     * 
     * @return String com os itens da árvore em ordem.
     */
    @Override
    public String toString() {
        return percorrer();
    }

    /**
     * Questão 1:
     * Realiza o caminhamento em pré-ordem (raiz -> esquerda -> direita)
     * recursivamente
     * a partir de um nó da árvore. Essa varredura visita primeiro o nó atual,
     * depois
     * a subárvore à esquerda, e por fim a subárvore à direita.
     *
     * @param raizArvore Nó atual da recursão.
     * @return String com os itens da árvore em pré-ordem, separados por quebras de
     *         linha.
     */
    private String caminhamentoPreOrdem(No<K, V> raizArvore) {
        if (raizArvore != null) {
            String resposta = raizArvore.getItem() + "\n";

            resposta += caminhamentoPreOrdem(raizArvore.getEsquerda());
            resposta += caminhamentoPreOrdem(raizArvore.getDireita());

            return resposta;
        } else {
            return "";
        }
    }

    /**
     * Questão 1:
     * Inicia o caminhamento em pré-ordem a partir da raiz da árvore.
     * Lança uma exceção se a árvore estiver vazia.
     *
     * @return String com os itens da árvore em pré-ordem.
     * @throws IllegalStateException se a árvore estiver vazia.
     */
    public String caminhamentoPreOrdem() {
        if (vazia()) {
            throw new IllegalStateException("A árvore está vazia.");
        }

        return caminhamentoPreOrdem(raiz);
    }

    /**
     * Questão 2:
     * Realiza o caminhamento em pós-ordem (esquerda -> direita -> raiz)
     * recursivamente
     * a partir de um nó da árvore. Nessa varredura, primeiro são visitados os
     * filhos,
     * e por último o nó atual.
     *
     * @param raizArvore Nó atual da recursão.
     * @return String com os itens da árvore em pós-ordem, separados por quebras de
     *         linha.
     */
    private String caminhamentoPosOrdem(No<K, V> raizArvore) {
        if (raizArvore != null) {
            String resposta = caminhamentoPosOrdem(raizArvore.getEsquerda());
            resposta += caminhamentoPosOrdem(raizArvore.getDireita());
            resposta += raizArvore.getItem() + "\n";
            return resposta;
        } else {
            return "";
        }
    }

    /**
     * Questão 2:
     * Inicia o caminhamento em pós-ordem a partir da raiz da árvore.
     * Lança uma exceção se a árvore estiver vazia.
     *
     * @return String com os itens da árvore em pós-ordem.
     * @throws IllegalStateException se a árvore estiver vazia.
     */
    public String caminhamentoPosOrdem() {
        if (vazia()) {
            throw new IllegalStateException("A árvore está vazia.");
        }

        return caminhamentoPosOrdem(raiz);
    }
}
