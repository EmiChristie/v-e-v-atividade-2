package sistemaIngressos;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class IngressoTest {

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
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        showRepository.addShow(show);
        Ingresso ingresso = new Ingresso("NORMAL",show.getPreco(), showRepository.getLastId(), ingressoRepository.getLastId(), show);
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
    public void testeCriarIngressoNormal(){
        String tipo = "NORMAL";
        double preco;
        int showId;

        showId = showRepository.getLastId();

        preco = show.getPreco();
        Ingresso ingresso = new Ingresso(tipo, preco, showId, ingressoRepository.getLastId(), show);
        ingressoRepository.addIngresso(ingresso);

        Assert.assertEquals(ingressoRepository.getLastId(), ingresso.getId());
        Assert.assertEquals(tipo, ingresso.getType());
        Assert.assertEquals("nao vendido", ingresso.getStatus());
        Assert.assertEquals(preco, ingresso.getPreco(), 0.1);
        Assert.assertEquals(showId, ingresso.getShowId());
    }

    @Test
    public void testeCriarIngressoVIP(){
        String tipo = "VIP";
        double preco;
        int showId;

        showId = show.getId();
        preco = show.getPreco() * 2;
        Ingresso ingresso = new Ingresso(tipo, preco, showId, ingressoRepository.getLastId(), show);
        ingressoRepository.addIngresso(ingresso);

        Assert.assertEquals(ingressoRepository.getLastId(), ingresso.getId());
        Assert.assertEquals(tipo, ingresso.getType());
        Assert.assertEquals("nao vendido", ingresso.getStatus());
        Assert.assertEquals(preco, ingresso.getPreco(), 0.1);
        Assert.assertEquals(showId, ingresso.getShowId());
    }

    @Test
    public void testeCriarIngressoMeiaEntrada() {
        String tipo = "MEIA_ENTRADA";
        double preco;
        int showId;

        showId = show.getId();
        preco = show.getPreco() / 2;
        Ingresso ingresso = new Ingresso(tipo, preco, showId, ingressoRepository.getLastId(), show);
        ingressoRepository.addIngresso(ingresso);

        Assert.assertEquals(ingressoRepository.getLastId(), ingresso.getId());
        Assert.assertEquals(tipo, ingresso.getType());
        Assert.assertEquals("nao vendido", ingresso.getStatus());
        Assert.assertEquals(preco, ingresso.getPreco(), 0.1);
        Assert.assertEquals(showId, ingresso.getShowId());
    }

    @Test
    public void testComprarIngresso() {
        String tipo = "MEIA_ENTRADA";
        double preco;
        int showId = showRepository.getLastId();
        int ingressosVendidos = show.getIngressosVendidos();

        preco = showRepository.getShow(showId).getPreco() / 2;
        Ingresso ingresso = new Ingresso(tipo, preco, showId, ingressoRepository.getLastId(), show);
        ingresso.comprarIngresso(0.25);

        Assert.assertEquals(ingressosVendidos+1, show.getIngressosVendidos());
        Assert.assertEquals(ingresso.getStatus(), "vendido");
    }

    @Test
    public void testCancelarCompra() {
        String tipo = "MEIA_ENTRADA";
        double preco;
        int showId = showRepository.getLastId();
        int ingressosVendidos = show.getIngressosVendidos();

        preco = showRepository.getShow(showId).getPreco() / 2;
        Ingresso ingresso = new Ingresso(tipo, preco, showId, ingressoRepository.lastId, show);
        ingresso.comprarIngresso(0.25);
        ingresso.cancelarCompra(0.25);

        Assert.assertEquals(ingressosVendidos, show.getIngressosVendidos());
        Assert.assertEquals(ingresso.getStatus(), "nao vendido");
    }
}
