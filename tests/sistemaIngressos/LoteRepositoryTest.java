package sistemaIngressos;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class LoteRepositoryTest {
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
        ingressoRepository = new IngressoRepository();
        ingressoRepository.addIngresso(ingresso);
        ArrayList<Ingresso> ingressos = new ArrayList<>();
        loteRepository = new LoteRepository();
        double desconto = 0.25;
        loteId = loteRepository.getLastId();
        lote = new Lote(loteId, ingressos, desconto);
        loteRepository.addLote(lote);

    }

    @Test
    public void testeAddLote() {
        Assert.assertEquals(lote.getId(), loteRepository.getLastId()-1);
    }

    @Test
    public void testeGetLote() {
        Assert.assertEquals(show.getId(), loteRepository.getLote(lote.getId()).getId());
    }
}
