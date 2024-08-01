package sistemaIngressos;

import java.sql.Array;
import java.util.ArrayList;

public class ShowRepository {

    ArrayList<Show> shows = new ArrayList<Show>();
    int lastId = 0;

    public void addShow(Show show) {
        shows.add(show);
        lastId++;
    }

    public int getLastId() {
        return lastId;
    }

    public Show getShow(int showId) {
        /*
        String data = "02/02/2020";
        boolean dataEspecial = false;
        double despesas = 2000.00;
        double cache = 1000.00;
        Show show = new Show(capacidade, data, dataEspecial,  despesas, cache);
        return show;
        */
        return shows.get(showId);
    }
}
