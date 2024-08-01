package sistemaIngressos;

import java.util.ArrayList;

public class Lote {
    int loteId;

    double desconto;
    ArrayList<Ingresso> ingressos = new ArrayList<>();
    public Lote(int loteId, ArrayList<Ingresso> ingressos, double desconto) {
        this.ingressos = ingressos;
        this.loteId = loteId;
        this.desconto = desconto;
    }

    public void addIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }

    public double getDesconto() {
        return desconto;
    }

    public void comprarIngresso(int id) {
        Ingresso ingresso = ingressos.get(id);
        ingresso.comprarIngresso(desconto);
    }

    public void cancelarCompra(int id) {
        Ingresso ingresso = ingressos.get(id);
        ingresso.cancelarCompra(desconto);
    }

    public int getId() {
        return loteId;
    }
}
