package functionalTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sistemaIngressos.*;

import java.util.ArrayList;

public class decisionTableTests {
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
        show = new Show(100, "23/08/2023", false, 500, 500, showRepository.getLastId(), 500);
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
        double desconto = 1;
        loteId = loteRepository.getLastId();
        lote = new Lote(loteId, ingressos, desconto);
        loteRepository.addLote(lote);
    }

    @Test
    public void testeCustosMaiorQueReceita() {
        lote.comprarIngresso(0);
        double valorIngresso = ingressos.get(0).getPreco() * lote.getDesconto();
        Assert.assertEquals("PREJUIZO", show.getStatus());
    }

    @Test
    public void testeCustosIgualAReceita() {
        lote.comprarIngresso(1);
        Assert.assertEquals("ESTAVEL", show.getStatus());
    }

    @Test
    public void testeCustosMenorQueReceita() {
        lote.comprarIngresso(2);
        Assert.assertEquals("LUCRO", show.getStatus());
    }
}
