package sistemaIngressos;

public class IngressoRepository {
    int lastId = 0;
    public void addIngresso(Ingresso ingresso) {
        lastId++;
    }

    public int getLastId() {
        return lastId;
    }
}
