package sistemaIngressos;

import java.util.ArrayList;

public class IngressoRepository {
    ArrayList<Ingresso> ingressos = new ArrayList<>();
    int lastId = 0;
    public void addIngresso(Ingresso ingresso) {
        lastId++;
        ingressos.add(ingresso);
    }

    public int getLastId() {
        return lastId;
    }
}
