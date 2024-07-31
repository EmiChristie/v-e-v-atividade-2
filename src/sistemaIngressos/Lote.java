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
    }

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }

    public double getDesconto() {
        return desconto;
    }

    public void comprarIngresso(int id) {
    }

    public void cancelarCompra(int id) {
    }
}
