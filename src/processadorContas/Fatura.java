package processadorContas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fatura {

    Date data;
    double valor;
    String nomeCliente;
    List<Pagamento> pagamentos = new ArrayList<Pagamento>();

    public Fatura(Date data, double valor, String nomeCliente){
        this.data = data; this.valor = valor; this.nomeCliente = nomeCliente;
    }

    public double getValor(){return valor;}

    public Date getData(){return data;}

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void addPagamento(Pagamento p){
        pagamentos.add(p);
    }
}
