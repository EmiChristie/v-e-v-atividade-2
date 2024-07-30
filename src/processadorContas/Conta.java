package processadorContas;

import java.util.Date;

public class Conta {

    Date data;
    double valor;

    public Conta(int codigoConta, Date data, double valor){
        this.data = data; this.valor = valor;
    }

    public double getValor(){return valor;}

    public Date getData(){return data;}

}
