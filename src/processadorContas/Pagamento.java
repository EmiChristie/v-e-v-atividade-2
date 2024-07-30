package processadorContas;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Pagamento {

    double valor;
    String data;
    String dataConta;
    String tipo;

    public Pagamento(double valor, String data, String dataConta,String tipo) {
        this.valor = valor;
        this.data = data;
        this.dataConta = dataConta;
        this.tipo = tipo;
    }

    public String getTipo(){return tipo;}

    public double getValor() {
        if(dataAtrasada()){
            return valor*1.1;
        }
        return valor;
    }

    public String getData(){return data;}

    public boolean dataAtrasada(){
        LocalDate data1 = LocalDate.parse(data);
        LocalDate data2 = LocalDate.parse(dataConta);
        return data1.isAfter(data2);
    }
}
