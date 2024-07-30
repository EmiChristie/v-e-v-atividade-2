package processadorContas;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Pagamento {

    double valor;
    String data;
    String dataConta;
    String tipo;
    double valorASomar;

    public Pagamento(double valor, String data, String dataConta,String tipo) {
        this.valor = valor;
        this.data = data;
        this.dataConta = dataConta;
        this.tipo = tipo;
        this.valorASomar = valor;
    }

    public String getTipo(){return tipo;}

    public double getValor() {
        return valor;
    }

    public double getValorASomar(){
        return valorASomar;
    }

    public void setValorASomar(double novoValor){
        this.valorASomar = novoValor;
    }

    public String getData(){return data;}

}
