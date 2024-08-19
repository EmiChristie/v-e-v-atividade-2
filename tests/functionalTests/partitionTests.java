package functionalTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sistemaIngressos.*;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;

public class partitionTests {
    static IngressoRepository ingressoRepository;
    static ShowRepository showRepository;
    static Show show;
    static int loteId;
    static LoteRepository loteRepository;
    static Lote lote;

    static ArrayList<Ingresso> ingressos;

    @BeforeClass
    public static void setup() {
        ingressoRepository = new IngressoRepository();
        loteRepository = new LoteRepository();
        showRepository = new ShowRepository();
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        showRepository.addShow(show);
        Ingresso ingressoNormal = new Ingresso("NORMAL",show.getPreco(), showRepository.getLastId(), ingressoRepository.getLastId(), show);
        ingressoRepository.addIngresso(ingressoNormal);
        Ingresso ingressoVIP = new Ingresso("VIP",show.getPreco(), showRepository.getLastId(), ingressoRepository.getLastId(), show);
        ingressoRepository.addIngresso(ingressoVIP);
        Ingresso ingressoMeia = new Ingresso("MEIA-ENTRADA",show.getPreco(), showRepository.getLastId(), ingressoRepository.getLastId(), show);
        ingressoRepository.addIngresso(ingressoMeia);
        ingressos = new ArrayList<>();
        ingressos.add(ingressoNormal);
        ingressos.add(ingressoVIP);
        ingressos.add(ingressoMeia);
        double desconto = 0.25;
        loteId = loteRepository.getLastId();
        lote = new Lote(loteId, ingressos, desconto);
        loteRepository.addLote(lote);
    }

    @Test
    public void testeSemDescontoIngressoVIP() {
        double rendaAnterior = show.getRendaLiquida();
        lote = new Lote(loteRepository.getLastId(), ingressos, 1);
        lote.comprarIngresso(0);
        double valorIngresso = ingressos.get(0).getPreco() * lote.getDesconto();
        Assert.assertEquals(rendaAnterior + valorIngresso, show.getRendaLiquida(), 1);
    }

    @Test
    public void testeDescontoValidoIngressoVIP() {
        double rendaAnterior = show.getRendaLiquida();
        lote = new Lote(loteRepository.getLastId(), ingressos, 0.75);
        lote.comprarIngresso(0);
        double valorIngresso = ingressos.get(0).getPreco() * lote.getDesconto();
        Assert.assertEquals(rendaAnterior + valorIngresso, show.getRendaLiquida(), 1);
    }

    @Test
    public void testeDescontoInvalidoIngressoVIP() {
        double rendaAnterior = show.getRendaLiquida();
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            lote = new Lote(loteRepository.getLastId(), ingressos, 0.74);
        });
    }

    @Test
    public void testeSemDescontoIngressoNormal() {
        double rendaAnterior = show.getRendaLiquida();
        lote = new Lote(loteRepository.getLastId(), ingressos, 1);
        lote.comprarIngresso(1);
        double valorIngresso = ingressos.get(1).getPreco() * lote.getDesconto();
        Assert.assertEquals(rendaAnterior + valorIngresso, show.getRendaLiquida(), 1);
    }

    @Test
    public void testeDescontoValidoIngressoNormal() {
        double rendaAnterior = show.getRendaLiquida();
        lote = new Lote(loteRepository.getLastId(), ingressos, 0.75);
        lote.comprarIngresso(1);
        double valorIngresso = ingressos.get(1).getPreco() * lote.getDesconto();
        Assert.assertEquals(rendaAnterior + valorIngresso, show.getRendaLiquida(), 1);
    }

    @Test
    public void testeDescontoInvalidoIngressoNormal() {
        double rendaAnterior = show.getRendaLiquida();
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            lote = new Lote(loteRepository.getLastId(), ingressos, 0.74);
        });
    }

    @Test
    public void testeSemDescontoIngressoMeia() {
        double rendaAnterior = show.getRendaLiquida();
        lote = new Lote(loteRepository.getLastId(), ingressos, 1);
        lote.comprarIngresso(2);
        double valorIngresso = ingressos.get(2).getPreco();
        Assert.assertEquals(rendaAnterior + valorIngresso, show.getRendaLiquida(), 1);
    }

    @Test
    public void testeDescontoValidoIngressoMeia() {
        double rendaAnterior = show.getRendaLiquida();
        lote = new Lote(loteRepository.getLastId(), ingressos, 1);
        lote.comprarIngresso(2);
        double valorIngresso = ingressos.get(2).getPreco();
        Assert.assertEquals(rendaAnterior + valorIngresso, show.getRendaLiquida(), 1);
    }

    @Test
    public void testeDescontoInvalidoIngressoMeia() {
        double rendaAnterior = show.getRendaLiquida();
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            lote = new Lote(loteRepository.getLastId(), ingressos, 0.74);
        });
    }
}
