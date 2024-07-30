package processadorContas;

import java.util.ArrayList;
import java.util.List;

public class Fatura {

    String data;
    double valor;
    String nomeCliente;
    List<Pagamento> pagamentos = new ArrayList<>();

    public Fatura(String data, double valor, String nomeCliente){
        this.data = data; this.valor = valor; this.nomeCliente = nomeCliente;
    }

    public double getValor(){return valor;}

    public String getData(){return data;}

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void addPagamento(Pagamento p){
        pagamentos.add(p);
    }
}
