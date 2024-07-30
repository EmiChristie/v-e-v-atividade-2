package sistemaIngressos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
public class IngressoTest {

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
    public void testeCriarIngressoNormal(){
        String tipo = "NORMAL";
        double preco;
        int showId;

        showId = showRepository.getLastId();

        preco = show.getPreco();
        Ingresso ingresso = new Ingresso(tipo, preco, showId);
        ingressoRepository.addIngresso();

        Assert.assertEquals(ingressoRepository.getLastId(), ingresso.getId());
        Assert.assertEquals(tipo, ingresso.getType());
        Assert.assertEquals("nao vendido", ingresso.getStatus());
        Assert.assertEquals(preco, ingresso.getPreco());
        Assert.assertEquals(showId, ingresso.getShowId());
    }

    @Test
    public void testeCriarIngressoVIP(){
        String tipo = "VIP";
        double preco;
        int showId;

        showId = show.getId();
        preco = show.getPreco() * 2;
        Ingresso ingresso = new Ingresso(tipo, preco, showId);
        ingressoRepository.addIngresso();

        Assert.assertEquals(ingressoRepository.getLastId(), ingresso.getId());
        Assert.assertEquals(tipo, ingresso.getType());
        Assert.assertEquals("nao vendido", ingresso.getStatus());
        Assert.assertEquals(preco, ingresso.getPreco());
        Assert.assertEquals(showId, ingresso.getShowId());
    }

    @Test
    public void testeCriarIngressoMeiaEntrada() {
        String tipo = "MEIA_ENTRADA";
        double preco;
        int showId;

        showId = show.getId();
        preco = show.getPreco() / 2;
        Ingresso ingresso = new Ingresso(tipo, preco, showId);
        ingressoRepository.addIngresso();

        Assert.assertEquals(ingressoRepository.getLastId(), ingresso.getId());
        Assert.assertEquals(tipo, ingresso.getType());
        Assert.assertEquals("nao vendido", ingresso.getStatus());
        Assert.assertEquals(preco, ingresso.getPreco());
        Assert.assertEquals(showId, ingresso.getShowId());
    }

    @Test
    public void testComprarIngresso() {
        String tipo = "MEIA_ENTRADA";
        double preco;
        int showId = showRepository.getLastId();
        int ingressosVendidos = show.getIngressosVendidos();

        preco = showRepository.getShow(showId).getPreco() / 2;
        Ingresso ingresso = new Ingresso(tipo, preco, showId);
        ingresso.comprarIngresso();

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
        Ingresso ingresso = new Ingresso(tipo, preco, showId);
        ingresso.comprarIngresso();
        ingresso.cancelarCompra();

        Assert.assertEquals(ingressosVendidos, show.getIngressosVendidos());
        Assert.assertEquals(ingresso.getStatus(), "nao vendido");
    }
}
