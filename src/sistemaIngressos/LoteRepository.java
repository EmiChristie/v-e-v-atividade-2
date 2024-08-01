package sistemaIngressos;

import java.util.ArrayList;

public class LoteRepository {
    ArrayList<Lote> lotes = new ArrayList<>();
    int lastId = 0;
    public int getLastId() {
        return lastId;
    }

    public void addLote(Lote lote) {
        lastId++;
        lotes.add(lote);
    }

    public Lote getLote(int loteId) {
        return lotes.get(loteId);
    }

}
