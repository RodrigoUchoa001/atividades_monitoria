package trabalho1.CadastroDeAlunos;

import java.util.ArrayList;

class Aluno {
    private String nome;
    private int matricula;
    private ArrayList<Double> notas;

    public Aluno(String nome, int matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.notas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void adicionarNota(double nota) {
        notas.add(nota);
    }

    public double calcularMedia() {
        if (notas.isEmpty())
            return 0;

        double soma = 0;
        for (double n : notas) {
            soma += n;
        }
        return soma / notas.size();
    }

    public String estaAprovado() {
        if (calcularMedia() >= 7.0) {
            return "APROVADO";
        } else {
            return "REPROVADO";
        }
    }

    public void exibirInfo() {
        System.out.printf("Aluno: " + nome + " | Matrícula: " + matricula + " | Média: " + calcularMedia() + " | "
                + estaAprovado() + "\n");
    }
}

class Turma {
    private ArrayList<Aluno> alunos;

    public Turma() {
        alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void adicionarNotaAluno(int matricula, double nota) {
        Aluno aluno = buscarAluno(matricula);
        if (aluno != null) {
            aluno.adicionarNota(nota);
        } else {
            System.out.println("Aluno com matrícula " + matricula + " não encontrado.");
        }
    }

    public void mostrarAlunosAprovados() {
        System.out.println("\nAlunos Aprovados:");
        for (Aluno a : alunos) {
            if (a.estaAprovado() == "APROVADO") {
                a.exibirInfo();
            }
        }
    }

    public void mostrarTodosAlunos() {
        System.out.println("\nTodos os Alunos da Turma:");
        for (Aluno a : alunos) {
            a.exibirInfo();
        }
    }

    private Aluno buscarAluno(int matricula) {
        for (Aluno a : alunos) {
            if (a.getMatricula() == matricula) {
                return a;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Turma turma = new Turma();

        // Criando alunos
        Aluno a1 = new Aluno("João", 101);
        Aluno a2 = new Aluno("Maria", 102);
        Aluno a3 = new Aluno("Lucas", 103);

        // Adicionando a turma
        turma.adicionarAluno(a1);
        turma.adicionarAluno(a2);
        turma.adicionarAluno(a3);

        // Adicionando notas
        turma.adicionarNotaAluno(101, 7.5);
        turma.adicionarNotaAluno(101, 8.0);

        turma.adicionarNotaAluno(102, 6.0);
        turma.adicionarNotaAluno(102, 5.5);

        turma.adicionarNotaAluno(103, 9.0);
        turma.adicionarNotaAluno(103, 8.5);

        // Mostrar todos
        turma.mostrarTodosAlunos();

        // Mostrar apenas aprovados
        turma.mostrarAlunosAprovados();
    }
}
