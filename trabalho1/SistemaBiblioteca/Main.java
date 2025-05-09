package trabalho1.SistemaBiblioteca;

import java.util.ArrayList;

class Livro {
    private String titulo;
    private String autor;
    private boolean estaEmprestado;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.estaEmprestado = false;
    }

    public boolean estaEmprestado() {
        return estaEmprestado;
    }

    public void emprestar() {
        estaEmprestado = true;
    }

    public void devolver() {
        estaEmprestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public void exibirInfo() {
        String status = estaEmprestado ? "Emprestado" : "Disponível";
        System.out.println("Livro: " + titulo + " | Autor: " + autor + " | Status: " + status);
    }
}

class Usuario {
    private String nome;
    private int id;

    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
}

class Biblioteca {
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;

    public Biblioteca() {
        livros = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void emprestarLivro(String tituloLivro, int idUsuario) {
        Livro livro = buscarLivro(tituloLivro);
        Usuario usuario = buscarUsuario(idUsuario);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
        } else if (usuario == null) {
            System.out.println("Usuário não encontrado.");
        } else if (livro.estaEmprestado()) {
            System.out.println("Livro já está emprestado.");
        } else {
            livro.emprestar();
            System.out.println("Livro \"" + tituloLivro + "\" emprestado para " + usuario.getNome());
        }
    }

    public void devolverLivro(String tituloLivro) {
        Livro livro = buscarLivro(tituloLivro);

        if (livro != null && livro.estaEmprestado()) {
            livro.devolver();
            System.out.println("Livro \"" + tituloLivro + "\" devolvido com sucesso.");
        } else {
            System.out.println("Livro não está emprestado ou não foi encontrado.");
        }
    }

    public void listarLivros() {
        System.out.println("\nLista de Livros na Biblioteca:");
        for (Livro l : livros) {
            l.exibirInfo();
        }
    }

    private Livro buscarLivro(String titulo) {
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }

    private Usuario buscarUsuario(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Adicionando livros
        biblioteca.adicionarLivro(new Livro("Dom Casmurro", "Machado de Assis"));
        biblioteca.adicionarLivro(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry"));

        // Adicionando usuários
        biblioteca.adicionarUsuario(new Usuario("Ana", 1));
        biblioteca.adicionarUsuario(new Usuario("Carlos", 2));

        // Listar todos os livros, enquanto estão todos disponíveis
        biblioteca.listarLivros();

        // Empréstimos dos livros
        biblioteca.emprestarLivro("Dom Casmurro", 1);
        biblioteca.emprestarLivro("O Pequeno Príncipe", 2);

        // Tentativa de emprestar livro já emprestado
        biblioteca.emprestarLivro("Dom Casmurro", 2);

        // Listar livros após empréstimos
        biblioteca.listarLivros();

        // Devolução dos livros emprestados
        biblioteca.devolverLivro("Dom Casmurro");

        // Listar livros após devolução
        biblioteca.listarLivros();
    }
}
