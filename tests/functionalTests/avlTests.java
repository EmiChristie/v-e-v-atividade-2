package functionalTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sistemaIngressos.*;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;

public class avlTests {
    static IngressoRepository ingressoRepository;
    static ShowRepository showRepository;
    static Show show;
    static int loteId;
    static LoteRepository loteRepository;
    static Lote lote;

    @BeforeClass
    public static void setup() {
        ingressoRepository = new IngressoRepository();
        loteRepository = new LoteRepository();
        showRepository = new ShowRepository();
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        showRepository.addShow(show);
        Ingresso ingresso = new Ingresso("NORMAL",show.getPreco(), showRepository.getLastId(), ingressoRepository.getLastId(), show);
        ingressoRepository.addIngresso(ingresso);
        ArrayList<Ingresso> ingressos = new ArrayList<>();
        double desconto = 0.25;
        loteId = loteRepository.getLastId();
        lote = new Lote(loteId, ingressos, desconto);
        loteRepository.addLote(lote);
    }

    @Test
    public void testeQuantiaMenorQueMinimoIngressoVIP() {
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 19; i++) {
            ingressos.add("VIP");
        }
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            show.adicionarIngresso(ingressos);
        });
    }

    @Test
    public void testeQuantiaMinimaIngressoVIP() {
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            ingressos.add("VIP");
        }

        show.adicionarIngresso(ingressos);
        Assert.assertEquals(20, show.getIngressosVip());
    }

    @Test
    public void testeQuantia1MaiorQueMininoIngressoVIP() {
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 21; i++) {
            ingressos.add("VIP");
        }
        show.adicionarIngresso(ingressos);
        Assert.assertEquals(21, show.getIngressosVip());
    }

    @Test
    public void testeQuantiaQualquerIngressoVIP() {
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 25; i++) {
            ingressos.add("VIP");
        }
        show.adicionarIngresso(ingressos);
        Assert.assertEquals(25, show.getIngressosVip());
    }

    @Test
    public void testeQuantia1MenorQueMaximoIngressoVIP() {
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 29; i++) {
            ingressos.add("VIP");
        }
        show.adicionarIngresso(ingressos);
        Assert.assertEquals(29, show.getIngressosVip());
    }

    @Test
    public void testeQuantiaMaximaIngressoVIP() {
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 30; i++) {
            ingressos.add("VIP");
        }
        show.adicionarIngresso(ingressos);
        Assert.assertEquals(30, show.getIngressosVip());
    }

    @Test
    public void testeQuantiaMaiorQueMaximoIngressoVIP() {
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 31; i++) {
            ingressos.add("VIP");
        }
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            show.adicionarIngresso(ingressos);
        });
    }

    @Test
    public void testeQuantiaMenorQueMinimoIngressoNormal() {
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 69; i++) {
            ingressos.add("NORMAL");
        }
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            show.adicionarIngresso(ingressos);
        });
    }

    @Test
    public void testeQuantiaMinimaIngressoNormal() {
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 70; i++) {
            ingressos.add("NORMAL");
        }

        show.adicionarIngresso(ingressos);
        Assert.assertEquals(70, show.getIngressosVip());
    }

    @Test
    public void testeQuantia1MaiorQueMininoIngressoNormal() {
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 71; i++) {
            ingressos.add("NORMAL");
        }
        show.adicionarIngresso(ingressos);
        Assert.assertEquals(71, show.getIngressosVip());
    }

    @Test
    public void testeQuantiaQualquerIngressoNormal() {
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 74; i++) {
            ingressos.add("NORMAL");
        }
        show.adicionarIngresso(ingressos);
        Assert.assertEquals(74, show.getIngressosVip());
    }

    @Test
    public void testeQuantia1MenorQueMaximoIngressoNormal() {
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 79; i++) {
            ingressos.add("NORMAL");
        }
        show.adicionarIngresso(ingressos);
        Assert.assertEquals(79, show.getIngressosVip());
    }

    @Test
    public void testeQuantiaMaximaIngressoNormal() {
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 80; i++) {
            ingressos.add("NORMAL");
        }
        show.adicionarIngresso(ingressos);
        Assert.assertEquals(80, show.getIngressosVip());
    }

    @Test
    public void testeQuantiaMaiorQueMaximoIngressoNormal() {
        show = new Show(100, "23/08/2023", false, 1000, 1500, showRepository.getLastId(), 50);
        ArrayList<String> ingressos = new ArrayList<>();
        for(int i = 0; i < 81; i++) {
            ingressos.add("NORMAL");
        }
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            show.adicionarIngresso(ingressos);
        });
    }
}