package processadorContas;

import org.junit.Before;
import org.junit.Test;
import processadorContas.exceptions.illegalArgumentsException;

import static org.junit.Assert.assertEquals;

public class ProcessadorContasTests {
    Conta conta1;
    Fatura fatura1;

    ProcessadorContas pc;
    Pagamento pagamento1;

    @Before
    public void setup(){
        pc = new ProcessadorContas();

        conta1 = new Conta(1, "2024-05-02",500.00);

        fatura1 = new Fatura("2024-05-20",1500.00,"Cliente 1");

        pagamento1 = new Pagamento(500,"2024-05-02","2024-05-02","BOLETO");

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