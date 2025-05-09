package trabalho1.SistemaDeEstoque;

import java.util.ArrayList;
import java.util.List;

// Produto
class Produto {
    private String nome;
    private int codigo;
    private double preco;
    private int quantidadeEmEstoque;

    public Produto(String nome, int codigo, double preco, int quantidadeEmEstoque) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void removerEstoque(int quantidade) {
        this.quantidadeEmEstoque -= quantidade;
    }

    public void adicionarEstoque(int quantidade) {
        this.quantidadeEmEstoque += quantidade;
    }
}

// Item do carrinho (Produto + Quantidade desejada)
class ItemCarrinho {
    Produto produto;
    int quantidade;

    public ItemCarrinho(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }
}

// Carrinho de Compras
class CarrinhoDeCompras {
    private List<ItemCarrinho> itens = new ArrayList<>();

    public void adicionarProduto(Produto produto, int quantidade) {
        itens.add(new ItemCarrinho(produto, quantidade));
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCarrinho item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void limpar() {
        itens.clear();
    }
}

// Loja
class Loja {
    private List<Produto> estoque = new ArrayList<>();
    private CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

    public void adicionarProdutoAoEstoque(Produto produto) {
        estoque.add(produto);
    }

    public Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto p : estoque) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public void adicionarProdutoAoCarrinho(int codigo, int quantidade) {
        Produto p = buscarProdutoPorCodigo(codigo);
        if (p != null && p.getQuantidadeEmEstoque() >= quantidade) {
            carrinho.adicionarProduto(p, quantidade);
        } else {
            System.out.println("Produto não encontrado ou quantidade indisponível.");
        }
    }

    public void finalizarCompra() {
        for (ItemCarrinho item : carrinho.getItens()) {
            item.produto.removerEstoque(item.quantidade);
        }
        System.out.println("Compra finalizada. Total: R$ " + carrinho.calcularTotal());
        carrinho.limpar();
    }

    public void mostrarTotalCarrinho() {
        System.out.println("Total do carrinho: R$ " + carrinho.calcularTotal());
    }
}

// Main para simular o funcionamento
public class Main {
    public static void main(String[] args) {
        Loja loja = new Loja();

        Produto p1 = new Produto("Mouse", 101, 50.0, 10);
        Produto p2 = new Produto("Teclado", 102, 100.0, 5);

        loja.adicionarProdutoAoEstoque(p1);
        loja.adicionarProdutoAoEstoque(p2);

        loja.adicionarProdutoAoCarrinho(101, 2);
        loja.adicionarProdutoAoCarrinho(102, 1);

        loja.mostrarTotalCarrinho();

        loja.finalizarCompra();

        // Tentativa de nova compra para ver atualização de estoque
        loja.adicionarProdutoAoCarrinho(101, 10); // Deve falhar
    }
}
