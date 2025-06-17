package dev.arturbomtempo;

/**
 * Interface genérica que define as operações básicas de uma estrutura de árvore binária.
 * 
 * Essa interface estabelece o contrato para classes que implementam árvores binárias,
 * permitindo armazenar pares chave-valor. As operações incluem inserção, pesquisa, remoção,
 * cálculo de tamanho e percurso dos elementos da árvore.
 *
 * @param <K> Tipo da chave usada para indexação, normalmente comparável.
 * @param <V> Tipo do valor associado à chave.
 */
public interface IMapeamento<K, V> {

    public int inserir(K chave, V item);
    public V pesquisar(K chave);
    public V remover(K chave);
    public int tamanho();
    public String percorrer();
}
