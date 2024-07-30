package processadorContas;


public class Conta {

    String data;
    double valor;

    public Conta(int codigoConta, String data, double valor){
        this.data = data; this.valor = valor;
    }

    public double getValor(){return valor;}

    public String getData(){return data;}

}
