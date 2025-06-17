package dev.arturbomtempo;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Classe para testar as implementações dos métodos das questões 1 a 10
 * da Árvore Binária de Busca.
 */
public class Aplicacao {

    /**
     * Cria e povoa uma árvore de alunos para testes
     */
    private static ABB<String, Aluno> criarArvoreDeAlunos() {
        ABB<String, Aluno> arvore = new ABB<>();

        inserirAluno(arvore, 101, "Amanda", 85.5);
        inserirAluno(arvore, 205, "Carlos", 92.0);
        inserirAluno(arvore, 310, "Eduardo", 78.5);
        inserirAluno(arvore, 407, "Gabriel", 81.0);
        inserirAluno(arvore, 503, "João", 76.0);
        inserirAluno(arvore, 612, "Luciana", 89.0);
        inserirAluno(arvore, 709, "Maria", 95.5);
        inserirAluno(arvore, 804, "Pedro", 79.0);
        inserirAluno(arvore, 908, "Renata", 88.0);

        return arvore;
    }

    /**
     * Método auxiliar para inserir alunos na árvore
     */
    private static void inserirAluno(ABB<String, Aluno> arvore, int matricula, String nome, double nota) {
        try {
            Aluno aluno = new Aluno(matricula, nome, nota);
            arvore.inserir(aluno.getNome(), aluno);
        } catch (Exception e) {
            System.out.println("Erro ao inserir aluno " + nome + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        ABB<String, Aluno> arvoreAlunos = criarArvoreDeAlunos();

        System.out.println("===== Árvore de Alunos Inicial =====");
        System.out.println("Tamanho da árvore: " + arvoreAlunos.tamanho());
        System.out.println("\nCaminhamento Padrão (Em Ordem):");
        System.out.println(arvoreAlunos.caminhamentoEmOrdem());

        System.out.println("\n===== QUESTÃO 1: Caminhamento em Pré-Ordem =====");
        System.out.println("Visita primeiro a raiz, depois esquerda, depois direita");
        System.out.println(arvoreAlunos.caminhamentoPreOrdem());

        System.out.println("\n===== QUESTÃO 2: Caminhamento em Pós-Ordem =====");
        System.out.println("Visita primeiro a esquerda, depois direita, por fim a raiz");
        System.out.println(arvoreAlunos.caminhamentoPosOrdem());

        System.out.println("\n===== QUESTÃO 3: Caminhamento em Ordem Decrescente =====");
        System.out.println("Visita direita, raiz, esquerda (ordem Z->A)");
        System.out.println(arvoreAlunos.caminhamentoDecrescente());

        System.out.println("\n===== QUESTÃO 4: Obter Menor Elemento =====");
        try {
            Aluno menorAluno = arvoreAlunos.obterMenor();
            System.out.println("Aluno com menor chave (primeiro alfabeticamente):");
            System.out.println(menorAluno);
        } catch (NoSuchElementException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n===== QUESTÃO 5: Clonagem da Árvore =====");
        ABB<String, Aluno> arvoreClonada = arvoreAlunos.clone();
        System.out.println("Verificando se a árvore clonada tem o mesmo tamanho: " +
                (arvoreClonada.tamanho() == arvoreAlunos.tamanho()));

        System.out.println("Removendo 'Maria' da árvore original...");
        try {
            arvoreAlunos.remover("Maria");
            System.out.println("Tamanho da árvore original após remoção: " + arvoreAlunos.tamanho());
            System.out.println("Tamanho da árvore clonada (deve permanecer igual): " + arvoreClonada.tamanho());
        } catch (Exception e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }

        System.out.println("\n===== QUESTÃO 6: Obter Subconjunto de Maiores =====");
        try {
            System.out.println("Subconjunto de alunos com nome >= 'K':");
            ABB<String, Aluno> maioresQueK = arvoreClonada.obterSubconjuntoMaiores("K");
            System.out.println("Tamanho do subconjunto: " + maioresQueK.tamanho());
            System.out.println(maioresQueK.caminhamentoEmOrdem());

            System.out.println("\nTentando obter subconjunto >= 'Z' (deve gerar exceção):");
            ABB<String, Aluno> maioresQueZ = arvoreClonada.obterSubconjuntoMaiores("Z");
        } catch (NoSuchElementException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        System.out.println("\n===== QUESTÃO 7: Verificar se é Raiz =====");
        String nomeRaiz = arvoreClonada.caminhamentoPreOrdem().split("\n")[0].split("Nome: ")[1].split("\n")[0];
        System.out.println("Nome da raiz (pelo pré-ordem): " + nomeRaiz);
        System.out.println("Verificando se '" + nomeRaiz + "' é raiz: " + arvoreClonada.ehRaiz(nomeRaiz));
        System.out.println("Verificando se 'Pedro' é raiz: " + arvoreClonada.ehRaiz("Pedro"));

        System.out.println("\n===== QUESTÃO 8: Obter Antecessor =====");
        try {
            String nomeMeio = "Maria";
            Aluno antecessor = arvoreClonada.obterAntecessor(nomeMeio);
            System.out.println("Antecessor de '" + nomeMeio + "':");
            System.out.println(antecessor);

            System.out.println("\nTentando obter antecessor do primeiro elemento (deve gerar exceção):");
            Aluno menorAluno = arvoreClonada.obterMenor();
            Aluno antecessorMenor = arvoreClonada.obterAntecessor(menorAluno.getNome());
        } catch (NoSuchElementException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        System.out.println("\n===== QUESTÃO 9: Calcular Valor Médio =====");
        Function<Aluno, Double> extrairNota = aluno -> aluno.getNota();

        try {
            double mediaTurma = arvoreClonada.calcularValorMedio(extrairNota);
            System.out.println("Nota média da turma: " + String.format("%.2f", mediaTurma));

            Function<Aluno, Double> extrairMatricula = aluno -> (double) aluno.getNumMatricula();
            double mediaMatriculas = arvoreClonada.calcularValorMedio(extrairMatricula);
            System.out.println("Média das matrículas: " + String.format("%.1f", mediaMatriculas));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n===== QUESTÃO 10: Contar Se =====");

        Predicate<Aluno> notaAcimaDe80 = aluno -> aluno.getNota() > 80.0;

        try {
            int alunosAcimaDe80 = arvoreClonada.contarSe(notaAcimaDe80);
            System.out.println("Quantidade de alunos com nota > 80: " + alunosAcimaDe80);

            Predicate<Aluno> nomeComecaComM = aluno -> aluno.getNome().startsWith("M");
            int alunosComNomeM = arvoreClonada.contarSe(nomeComecaComM);
            System.out.println("Quantidade de alunos cujo nome começa com 'M': " + alunosComNomeM);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
