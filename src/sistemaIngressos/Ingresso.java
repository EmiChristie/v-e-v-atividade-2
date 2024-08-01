package sistemaIngressos;

public class Ingresso {

    int id;

    Show show;

    String tipo;

    double preco;

    int showId;

    String status;

    public Ingresso(String tipo, double preco, int showId, int id, Show show) {
        this.tipo = tipo;
        this.preco = preco;
        this.showId = showId;
        this.id = id;
        this.show = show;
        this.status = "nao vendido";
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return tipo;
    }

    public String getStatus() {
        return status;
    }

    public double getPreco() {
        return preco;
    }

    public int getShowId() {
        return showId;
    }

    public void comprarIngresso(double desconto) {
        if(tipo.equals("NORMAL") || tipo.equals("VIP")) {
            show.mudarReceita(preco * desconto);
        } else {
            show.mudarReceita(preco);
        }
        show.adicionarIngresso(tipo);
        status = "vendido";
    }

    public void cancelarCompra(double desconto) {
        if(tipo.equals("NORMAL") || tipo.equals("VIP")) {
            show.mudarReceita(preco * desconto * -1);
        } else {
            show.mudarReceita(preco * -1);
        }
        show.removerIngresso(tipo);
        status = "nao vendido";
    }
}
