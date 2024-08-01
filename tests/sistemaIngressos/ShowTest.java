package sistemaIngressos;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

public class ShowTest {

    static IngressoRepository ingressoRepository;
    static ShowRepository showRepository;
    static Show show;

    static int loteId;

    static Ingresso ingresso;
    static LoteRepository loteRepository;
    static Lote lote;

    @BeforeClass
    public static void setup() {
        ingressoRepository = new IngressoRepository();
        showRepository = new ShowRepository();
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        showRepository.addShow(show);
        ingresso = new Ingresso("NORMAL", show.getPreco(), showRepository.getLastId(), ingressoRepository.getLastId(), show);
        ingressoRepository.addIngresso(ingresso);
        ArrayList<Ingresso> ingressos = new ArrayList<>();
        loteRepository = new LoteRepository();
        double desconto = 0.25;
        loteId = loteRepository.getLastId();
        lote = new Lote(loteId, ingressos, desconto);
        loteRepository.addLote(lote);
    }

    @Test
    public void testeCriarShow() {
        Assert.assertEquals("23/08/2023",show.getData());
        Assert.assertFalse(show.getDataEspecial());
        Assert.assertEquals(1000.00, show.getDespesas(), 0);
        Assert.assertEquals(1500.00, show.getCache(), 0);
        Assert.assertEquals(showRepository.getLastId() - 1, show.getId());
    }

    @Test
    public void testeGerarRelatorio() {
        String relatorioEsperado = "Ingressos: VIP(0), NORMAL(0), MEIA_ENTRADA(0); Receita Liquida = R$-2500.0; status financeiro = PREJUIZO";
        String relatorio = show.gerarRelatorio();

        Assert.assertEquals(relatorioEsperado, relatorio);
    }
}
