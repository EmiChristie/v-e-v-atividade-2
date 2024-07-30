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
        show = new Show();
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
