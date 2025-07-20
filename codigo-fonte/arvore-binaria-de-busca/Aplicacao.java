/**
 * Classe de aplicação responsável por demonstrar o uso da
 * Árvore Binária de Busca (ABB) com objetos do tipo {@link Aluno}.
 */
public class Aplicacao {

    public static void main(String[] args) {
        ABB<String, Aluno> turma = new ABB<>();
        Aluno alunoNovo;

        if (turma.vazia()) {
            System.out.println("A turma está vazia.");
        } else {
            System.out.println("A turma possui alunos matriculados.");
        }

        try {
            alunoNovo = new Aluno(5, "Amanda", 85.00);
            turma.inserir(alunoNovo.getNome(), alunoNovo);

            alunoNovo = new Aluno(8, "Uriel", 87.00);
            turma.inserir(alunoNovo.getNome(), alunoNovo);

            alunoNovo = new Aluno(6, "Ivo", 75.00);
            turma.inserir(alunoNovo.getNome(), alunoNovo);

            alunoNovo = new Aluno(3, "Olivia", 90.00);
            turma.inserir(alunoNovo.getNome(), alunoNovo);

            alunoNovo = new Aluno(9, "Evandro", 85.00);
            turma.inserir(alunoNovo.getNome(), alunoNovo);

            alunoNovo = new Aluno(4, "Sônia", 82.00);
            turma.inserir(alunoNovo.getNome(), alunoNovo);

            alunoNovo = new Aluno(6, "Ivo", 75.00);
            turma.inserir(alunoNovo.getNome(), alunoNovo);
        } catch (Exception excecao) {
            System.out.println(excecao.getMessage());
        }

        System.out.println("\n===== Caminhamento Em Ordem =====");
        System.out.println(turma.caminhamentoEmOrdem());

        System.out.println("\n===== Pesquisando =====");
        System.out.println(turma.pesquisar("Amanda"));

        System.out.println("\n===== Pesquisando =====");
        System.out.println(turma.pesquisar("Evandro"));

        try {
            turma.remover("Benício");
        } catch (Exception excecao) {
            System.out.println(excecao.getMessage());
        }

        turma.remover("Amanda");
        turma.remover("Olivia");

        System.out.println("\n===== Caminhamento Em Ordem =====");
        System.out.println(turma.caminhamentoEmOrdem());

        turma.remover("Sônia");
        turma.remover("Uriel");

        System.out.println("\n===== Caminhamento Em Ordem =====");
        System.out.println(turma.caminhamentoEmOrdem());

        turma.remover("Ivo");
        turma.remover("Evandro");

        try {
            turma.remover("Evandro");
        } catch (Exception excecao) {
            System.out.println(excecao.getMessage());
        }
    }
}