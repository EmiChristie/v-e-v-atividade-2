package processadorContas;

import org.junit.Before;
import org.junit.Test;
import processadorContas.exceptions.illegalArgumentsException;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ProcessadorContasTests {
    Conta conta1;
    Fatura fatura1;

    ProcessadorContas pc;
    Pagamento pagamento1;

    @Before
    public void setup(){
        pc = new ProcessadorContas();

        Date dataConta1 = new Date(124,02,20);
        conta1 = new Conta(1, dataConta1,500.00);

        Date dataFatura1 = new Date(124,02,20);
        fatura1 = new Fatura(dataFatura1,1500.00,"Cliente 1");

        Date dataPag1 = new Date(124,02,05);
        pagamento1 = new Pagamento(500,dataPag1,dataConta1,"BOLETO");

        fatura1.addPagamento(pagamento1);
    }

    @Test
    public void test1() throws illegalArgumentsException {
        Conta[] contas = new Conta[1];
        contas[0] = conta1;
        String processamento = pc.processar(contas,fatura1);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
    }

    @Test
    public void test2() throws illegalArgumentsException {
        fatura1.addPagamento(pagamento1);
        fatura1.addPagamento(pagamento1);

        Conta[] contas = new Conta[3];
        contas[0] = conta1;
        contas[1] = conta1;
        contas[2] = conta1;
        String processamento = pc.processar(contas,fatura1);
        String msg = "PAGA";
        assertEquals(msg, processamento);
    }

}