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
        /*
        String data = "02/02/2020";
        boolean dataEspecial = false;
        double despesas = 2000.00;
        double cache = 1000.00;
        Show show = new Show(data, dataEspecial, despesas, cache);
        return show;
        */
        return lotes.get(loteId);
    }

}
