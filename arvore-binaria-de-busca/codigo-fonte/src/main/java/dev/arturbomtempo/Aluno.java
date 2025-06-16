package dev.arturbomtempo;

/**
 * Classe que representa um aluno com nome, número de matrícula e nota.
 * Implementa {@link Comparable} para permitir comparação com base no nome.
 */
public class Aluno implements Comparable<Aluno> {

    private int numMatricula;
    private String nome;
    private double nota;

    public Aluno(int matricula, String nome, double nota) {
        this.setNumMatricula(matricula);
        this.setNome(nome);
        this.setNota(nota);
    }

    public int getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(int numMatricula) {
        this.numMatricula = numMatricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    /**
     * Compara este aluno com outro, com base no nome.
     *
     * @param outroAluno Aluno a ser comparado.
     * @return Valor negativo se este nome for "menor", positivo se for "maior", 0
     *         se forem iguais.
     */
    @Override
    public int compareTo(Aluno outroAluno) {
        return (this.nome.compareTo(outroAluno.getNome()));
    }

    /**
     * Verifica se dois objetos Aluno são iguais com base no nome.
     *
     * @param outroObjeto Objeto a ser comparado.
     * @return {@code true} se os nomes forem iguais.
     */
    @Override
    public boolean equals(Object outroObjeto) {
        Aluno outroAluno = (Aluno) outroObjeto;
        return (this.nome.equals(outroAluno.getNome()));
    }

    /**
     * Retorna o código hash do aluno, com base na matrícula.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return (this.numMatricula);
    }

    /**
     * Retorna uma representação textual do aluno,
     * incluindo nome, matrícula e nota.
     *
     * @return String formatada com as informações do aluno.
     */
    @Override
    public String toString() {
        return ("Nome: " + this.nome + "\nNúmero de matrícula: " + this.numMatricula + "\nNota: " + this.nota);
    }
}
