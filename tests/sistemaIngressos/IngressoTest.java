package sistemaIngressos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
public class IngressoTest {

    IngressoRepository ingressoRepository;
    ShowRepository showRepository;

    @BeforeClass
    public static void setup() {
        ingressoRepository = new IngressoRepository();
        showRepository = new ShowRepository();
        Show show = new Show();
        showRepository.addShow(show);
    }

    @Test
    public void testeCriarIngressoNormal(){
        String tipo = "NORMAL";
        double preco;
        int showId;

        showId = showRepository.getLastId();
        preco = showRepository.getShow(showId).getPreco();
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

        showId = showRepository.getLastId();
        preco = showRepository.getShow(showId).getPreco() * 2;
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

        showId = showRepository.getLastId();
        preco = showRepository.getShow(showId).getPreco() / 2;
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
        int ingressosVendidos = showRepository.getShow(showId).getIngressosVendidos()[1].leght;

        preco = showRepository.getShow(showId).getPreco() / 2;
        Ingresso ingresso = new Ingresso(tipo, preco, showId);
        ingresso.comprarIngresso();

        Assert.assertEquals(ingressosVendidos+1, showRepository.getShow(showId).getIngressosVendidos()[1].leght);
        Assert.assertEquals(igresso.getStatus(), "vendido");
    }

    @Test
    public void testCancelarCompra() {
        String tipo = "MEIA_ENTRADA";
        double preco;
        int showId = showRepository.getLastId();
        int ingressosVendidos = showRepository.getShow(showId).getIngressosVendidos()[1].leght;

        preco = showRepository.getShow(showId).getPreco() / 2;
        Ingresso ingresso = new Ingresso(tipo, preco, showId);
        ingresso.comprarIngresso();
        ingresso.cancelarCompra();

        Assert.assertEquals(ingressosVendidos, showRepository.getShow(showId).getIngressosVendidos()[1].leght);
        Assert.assertEquals(igresso.getStatus(), "nao vendido");
    }
}
