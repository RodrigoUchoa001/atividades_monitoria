package trabalho1.ContaBancaria;

import java.util.ArrayList;

class ContaBancaria {
    private String titular;
    private int numeroDaConta;
    private double saldo;

    public ContaBancaria(String titular, int numeroDaConta, double saldoInicial) {
        this.titular = titular;
        this.numeroDaConta = numeroDaConta;
        this.saldo = saldoInicial;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public void mostrarExtrato() {
        System.out.println("Titular: " + titular);
        System.out.println("Conta: " + numeroDaConta);
        System.out.println("Saldo: R$ " + saldo);
        System.out.println("----------------------------");
    }
}

class SistemaBancario {
    private ArrayList<ContaBancaria> contas;

    public SistemaBancario() {
        contas = new ArrayList<>();
    }

    public void adicionarConta(ContaBancaria conta) {
        contas.add(conta);
    }

    public ContaBancaria buscarConta(int numero) {
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroDaConta() == numero) {
                return conta;
            }
        }
        return null;
    }

    public void transferir(double valor, int contaOrigem, int contaDestino) {
        ContaBancaria origem = buscarConta(contaOrigem);
        ContaBancaria destino = buscarConta(contaDestino);

        if (origem != null && destino != null) {
            if (origem.sacar(valor)) {
                destino.depositar(valor);
                System.out.println("Transferência de R$" + valor + " realizada com sucesso!");
            } else {
                System.out.println("Saldo insuficiente na conta de origem.");
            }
        } else {
            System.out.println("Conta de origem ou destino não encontrada.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SistemaBancario banco = new SistemaBancario();

        ContaBancaria c1 = new ContaBancaria("Alice", 101, 1000.0);
        ContaBancaria c2 = new ContaBancaria("Bob", 102, 500.0);
        ContaBancaria c3 = new ContaBancaria("Carol", 103, 200.0);

        banco.adicionarConta(c1);
        banco.adicionarConta(c2);
        banco.adicionarConta(c3);

        // Extrato inicial
        c1.mostrarExtrato();
        c2.mostrarExtrato();
        c3.mostrarExtrato();

        // Transferências
        banco.transferir(300.0, 101, 102); // Alice para Bob
        banco.transferir(100.0, 102, 103); // Bob para Carol

        // Extrato final
        c1.mostrarExtrato();
        c2.mostrarExtrato();
        c3.mostrarExtrato();
    }
}
