package processadorContas;

import org.junit.Test;
import processadorContas.*;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ProcessadorContasTests {
    Conta conta1;
    Fatura fatura1;

    ProcessadorContas pc;



    @Test
    public void setup(){
        pc = new ProcessadorContas();

        Date dataConta1 = new Date(124,02,05);
        conta1 = new Conta(1, dataConta1,500.00, "BOLETO");

        Date dataFatura1 = new Date(124,02,20);
        fatura1 = new Fatura(dataFatura1,1500.00,"Cliente 1");
    }
}