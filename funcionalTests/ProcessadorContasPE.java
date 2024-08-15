import org.junit.Before;
import org.junit.Test;
import processadorContas.Conta;
import processadorContas.Fatura;
import processadorContas.Pagamento;
import processadorContas.ProcessadorContas;
import processadorContas.exceptions.illegalArgumentsException;

import static org.junit.Assert.assertEquals;

public class ProcessadorContasPE {
    Conta conta;
    Fatura fatura;
    ProcessadorContas pc;
    Pagamento pagamento;

    @Before
    public void setup(){
        pc = new ProcessadorContas();
    }

    @Test
    public void testPE1() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",3000.00,"Cliente 1");

        conta = new Conta(1, "2024-06-22",3000.00);
        pagamento = new Pagamento(3000,"2024-06-21","2024-06-22","BOLETO");
        fatura.addPagamento(pagamento);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
        assertEquals(3000,pagamento.getValorASomar(),1);
    }

    @Test
    public void testPE2() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",3000.00,"Cliente 1");

        conta = new Conta(1, "2024-06-22",3000.00);
        pagamento = new Pagamento(3000,"2024-06-21","2024-06-22","BOLETO");
        fatura.addPagamento(pagamento);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
        assertEquals(3000,pagamento.getValorASomar(),1);
    }

    @Test
    public void testPE3() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",3000,"Cliente 1");

        conta = new Conta(1, "2024-06-22",3000);
        pagamento = new Pagamento(3000,"2024-06-23","2024-06-22","BOLETO");
        fatura.addPagamento(pagamento);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
        assertEquals(3300,pagamento.getValorASomar(),1);
    }

    @Test
    public void testPE4() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",3000,"Cliente 1");

        conta = new Conta(1, "2024-06-22",3000);
        pagamento = new Pagamento(3000,"2024-06-24","2024-06-22","BOLETO");
        fatura.addPagamento(pagamento);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        String processamento = pc.processar(contas,fatura);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
        assertEquals(0,pagamento.getValorASomar(),1);
    }

    @Test
    public void testPE5() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",3000,"Cliente 1");

        conta = new Conta(1, "2024-06-07",3000);
        pagamento = new Pagamento(3000,"2024-06-06","2024-06-07","CARTAO_CREDITO");
        fatura.addPagamento(pagamento);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
        assertEquals(3000,pagamento.getValorASomar(),1);
    }

    @Test
    public void testPE6() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",3000,"Cliente 1");

        conta = new Conta(1, "2024-06-08",3000);
        pagamento = new Pagamento(3000,"2024-06-08","2024-06-08","CARTAO_CREDITO");
        fatura.addPagamento(pagamento);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
        assertEquals(3000,pagamento.getValorASomar(),1);
    }

    @Test
    public void testPE7() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",3000,"Cliente 1");

        conta = new Conta(1, "2024-06-09",3000);
        pagamento = new Pagamento(3000,"2024-06-08","2024-06-09","CARTAO_CREDITO");
        fatura.addPagamento(pagamento);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        String processamento = pc.processar(contas,fatura);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
        assertEquals(0,pagamento.getValorASomar(),1);
    }

    @Test
    public void testPE8() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",3000,"Cliente 1");

        conta = new Conta(1, "2024-06-22",3000);
        pagamento = new Pagamento(3000,"2024-06-21","2024-06-22","TRANSFERENCIA_BANCARIA");
        fatura.addPagamento(pagamento);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
        assertEquals(3000,pagamento.getValorASomar(),1);
    }

    @Test
    public void testPE9() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",3000,"Cliente 1");

        conta = new Conta(1, "2024-06-23",3000);
        pagamento = new Pagamento(3000,"2024-06-23","2024-06-23","TRANSFERENCIA_BANCARIA");
        fatura.addPagamento(pagamento);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
        assertEquals(3000,pagamento.getValorASomar(),1);
    }

    @Test
    public void testPE10() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",3000,"Cliente 1");

        conta = new Conta(1, "2024-06-24",3000);
        pagamento = new Pagamento(3000,"2024-06-23","2024-06-24","TRANSFERENCIA_BANCARIA");
        fatura.addPagamento(pagamento);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        String processamento = pc.processar(contas,fatura);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
        assertEquals(0,pagamento.getValorASomar(),1);
    }

}