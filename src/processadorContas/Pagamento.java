package processadorContas;

import processadorContas.exceptions.illegalArgumentsException;

import java.util.Date;

public class Pagamento {

    double valor;
    Date data;
    Date dataConta;
    String tipo;

    public Pagamento(double valor, Date data, Date dataConta,String tipo) {
        this.valor = valor;
        this.data = data;
        this.dataConta = dataConta;
        this.tipo = tipo;
    }

    public double getValor() {
        if(dataAtrasada(data,dataConta)){
            return valor*1.1;
        }
        return valor;
    }

    public Date getData(){return data;}

    public boolean dataAtrasada(Date data, Date dataConta){
        return data.after(dataConta);
    }
}
