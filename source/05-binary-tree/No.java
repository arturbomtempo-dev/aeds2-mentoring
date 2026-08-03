/**
 * Representa um nó genérico de uma árvore binária, armazenando uma chave,
 * um valor associado e referências para os filhos esquerdo e direito.
 * 
 * Além disso, mantém a altura do nó na árvore, permitindo seu uso
 * em estruturas balanceadas como AVL ou Red-Black Tree.
 *
 * @param <K> Tipo da chave usada para comparação ou identificação.
 * @param <V> Tipo do valor armazenado no nó.
 */
public class No<K, V> {

    private K chave;
    private V item;
    private No<K, V> direita;
    private No<K, V> esquerda;
    private int altura;

    public No(K chave, V item) {
        this.setChave(chave);
        this.setItem(item);
        this.setDireita(null);
        this.setEsquerda(null);
        this.altura = 0;
    }

    public K getChave() {
        return chave;
    }

    public void setChave(K chave) {
        this.chave = chave;
    }

    public V getItem() {
        return item;
    }

    public void setItem(V item) {
        this.item = item;
    }

    public No<K, V> getDireita() {
        return direita;
    }

    public void setDireita(No<K, V> direita) {
        this.direita = direita;
    }

    public No<K, V> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No<K, V> esquerda) {
        this.esquerda = esquerda;
    }

    private int getAltura(No<K, V> no) {
        if (no != null) {
            return no.getAltura();
        } else {
            return -1;
        }
    }

    public int getAltura() {
        return this.altura;
    }

    /**
     * Atualiza a altura do nó com base na maior altura entre os filhos
     * esquerdo e direito, somando 1.
     */
    public void setAltura() {
        int alturaEsquerda, alturaDireita;

        alturaEsquerda = getAltura(esquerda);
        alturaDireita = getAltura(direita);

        altura = Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    /**
     * Calcula e retorna o fator de balanceamento do nó,
     * definido como a diferença entre as alturas da subárvore esquerda
     * e da subárvore direita.
     *
     * @return O fator de balanceamento do nó.
     */
    public int getFatorBalanceamento() {
        int alturaEsquerda, alturaDireita;

        alturaEsquerda = getAltura(esquerda);
        alturaDireita = getAltura(direita);

        return (alturaEsquerda - alturaDireita);
    }
}