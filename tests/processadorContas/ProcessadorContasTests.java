package processadorContas;

import org.junit.Test;
import processadorContas.*;

import static org.junit.Assert.assertEquals;

public class ProcessadorContasTests {

    ProcessadorContas pc = new ProcessadorContas();

    @Test
    public void processadorContasTeste1(){
        double[] contas = new double[0];
        double fatura = 0;
        String processamento = pc.processar(contas,fatura);
        String processamentoEsperado = "Fatura de 1.500,00 (20/02/2023) com 3 contas no valor de 500,00, " +
                "400,00 e 600,00. As três contas foram pagas por boleto no dia 20/02/2023 (todas em dia), assim a fatura é marcada como PAGA.";

        assertEquals(processamentoEsperado, processamento);
    }
}