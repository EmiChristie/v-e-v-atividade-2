package sistemaIngressos;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

public class ShowTest {

    static IngressoRepository ingressoRepository;
    static ShowRepository showRepository;
    static Show show;

    @BeforeClass
    public static void setup() {
        String data = "02/02/2020";
        Boolean dataEspecial = false;
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
    public void testeCriarShow() {
        Assert.assertEquals("02/02/2020",show.getData());
        Assert.assertEquals(false,show.getDataEspecial());
        Assert.assertEquals(2000.00, show.getDespesas(), 0);
        Assert.assertEquals(1000.00, show.getCache(), 0);
        Assert.assertEquals(showRepository.getLastId(), show.getId());
    }

    @Test
    public void testeGerarRelatorio() {
        String relatorioEsperado = "Ingressos: VIP(0), NORMAL(0), MEIA_ENTRADA(0); Receita Liquida = R$0,00; status financeiro; ESTÁVEL";
        String relatorio = show.gerarRelatorio();
        Assert.assertEquals(relatorioEsperado, relatorio);
    }
}
