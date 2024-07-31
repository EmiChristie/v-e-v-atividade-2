package sistemaIngressos;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

public class ShowRepositoryTest {

    static IngressoRepository ingressoRepository;
    static ShowRepository showRepository;
    static Show show;

    @BeforeClass
    public static void setup() {
        String data = "02/02/2020";
        boolean dataEspecial = false;
        double despesas = 2000.00;
        double cache = 1000.00;
        show = new Show(data, dataEspecial, despesas, cache);
        ingressoRepository = new IngressoRepository();
        showRepository = new ShowRepository();
        showRepository.addShow(show);
        Ingresso ingresso = new Ingresso("VIP", 10, show.getId());
        ingressoRepository.addIngresso(ingresso);
    }

    @Test
    public void testeAddShow() {
        Assert.assertEquals(show.getId(), showRepository.getLastId()-1);
    }

    @Test
    public void testeGetShow() {
        Assert.assertEquals(show.getId(), showRepository.getShow(show.getId()).getId());
    }
}