package sistemaIngressos;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IngressoRepositoryTest {
    static IngressoRepository ingressoRepository;
    static ShowRepository showRepository;
    static Show show;


    @BeforeClass
    public static void setup() {
        ingressoRepository = new IngressoRepository();
        showRepository = new ShowRepository();
        String data = "02/02/2020";
        Boolean dataEspecial = false;
        Double despesas = 2000.00;
        double cache = 1000.00;
        show = new Show(data, dataEspecial, despesas, cache);
        showRepository.addShow(show);
    }

    @Test
    public void testeAddIngresso() {
        Ingresso ingresso = new Ingresso("VIP", 10, show.getId());
        ingressoRepository.addIngresso(ingresso);
        Assert.assertEquals(1, ingressoRepository.getLastId());
    }

    @Test
    public void testeGetLastId() {
        Assert.assertEquals(0, ingressoRepository.getLastId());
    }
}
