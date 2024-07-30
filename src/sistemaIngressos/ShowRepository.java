package sistemaIngressos;

public class ShowRepository {
    public void addShow(Show show) {
    }

    public int getLastId() {
        return 0;
    }

    public Show getShow(int showId) {
        String data = "02/02/2020";
        Boolean dataEspecial = false;
        Double despesas = 2000.00;
        double cache = 1000.00;
        Show show = new Show(data, dataEspecial, despesas, cache);
        return show;
    }
}
