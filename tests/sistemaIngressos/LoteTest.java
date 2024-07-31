package sistemaIngressos;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LoteTest {
    static IngressoRepository ingressoRepository;
    static ShowRepository showRepository;
    static Show show;

    static int loteId;

    Ingresso ingresso;
    static LoteRepository loteRepository;
    static Lote lote;

    @BeforeClass
    public static void setup() {
        showRepository = new ShowRepository();
        show = new Show("02/02/2020", false,2000.00, 1000.00);
        showRepository.addShow(show);
        Ingresso ingresso = new Ingresso("NORMAL",show.getPreco(), showRepository.getLastId());
        ingressoRepository.addIngresso(ingresso);
        ArrayList<Ingresso> ingressos = new ArrayList<>();
        ingressoRepository = new IngressoRepository();
        loteRepository = new LoteRepository();
        double desconto = 0.25;
        loteId = loteRepository.getLastId();
        lote = new Lote(loteId, ingressos, desconto);
        loteRepository.addLote(lote);

    }

    @Test
    public void adicionarIngresso() {
        lote.addIngresso(ingresso);
        ArrayList<Ingresso> ingressos = lote.getIngressos();
        Ingresso ultimoIngresso = ingressos.get(ingressos.size()-1);
        Assert.assertEquals(ingressoRepository.getLastId(), ingresso.getId());
        Assert.assertEquals("NORMAL", ingresso.getType());
        Assert.assertEquals("nao vendido", ingresso.getStatus());
        Assert.assertEquals(show.getPreco(), ingresso.getPreco());
        Assert.assertEquals(showRepository.getLastId(), ingresso.getShowId());
    }

    @Test
    public void testComprarIngresso() {
        double showRendaAnterior = show.getRendaLiquida();
        lote.comprarIngresso(ingresso.getId());
        double desconto = lote.getDesconto();
        Assert.assertEquals(showRendaAnterior, show.getRendaLiquida() - ingresso.getPreco() * desconto);
    }

    @Test
    public void testCancelarCompra() {
        double showRendaAnterior = show.getRendaLiquida();
        lote.comprarIngresso(ingresso.getId());
        lote.cancelarCompra(ingresso.getId());
        Assert.assertEquals(showRendaAnterior, show.getRendaLiquida());
    }
}
