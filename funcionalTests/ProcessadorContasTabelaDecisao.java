import org.junit.Before;
import org.junit.Test;
import processadorContas.Conta;
import processadorContas.Fatura;
import processadorContas.Pagamento;
import processadorContas.ProcessadorContas;
import processadorContas.exceptions.illegalArgumentsException;

import static org.junit.Assert.assertEquals;

public class ProcessadorContasTabelaDecisao {
    Conta conta;
    Fatura fatura;
    ProcessadorContas pc;
    Pagamento pagamento;

    @Before
    public void setup(){
        pc = new ProcessadorContas();
    }

    @Test
    public void testTabelaDecisao1() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",3000.00,"Cliente 1");

        conta = new Conta(1, "2024-06-23",3000.00);
        pagamento = new Pagamento(3000,"2024-06-23","2024-06-23","BOLETO");
        fatura.addPagamento(pagamento);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
        assertEquals(3000,pagamento.getValorASomar(),1);
    }

    @Test
    public void testTabelaDecisao2() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",3000.00,"Cliente 1");

        conta = new Conta(1, "2024-06-23",0);
        pagamento = new Pagamento(0,"2024-06-23","2024-06-23","BOLETO");
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        try{
            fatura.addPagamento(pagamento);
            String processamento = pc.processar(contas,fatura);
            String msg = "PENDENTE";
            assertEquals(msg, processamento);
        }catch(illegalArgumentsException e){
            System.out.println("Boleto não aceito, pois possui valor abaixo do permitido.");
        }
    }

    @Test
    public void testTabelaDecisao3() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",6000.00,"Cliente 1");

        conta = new Conta(1, "2024-06-23",6000);
        pagamento = new Pagamento(6000,"2024-06-23","2024-06-23","BOLETO");
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        try{
            fatura.addPagamento(pagamento);
            String processamento = pc.processar(contas,fatura);
            String msg = "PENDENTE";
            assertEquals(msg, processamento);
        }catch(illegalArgumentsException e){
            System.out.println("Boleto não aceito, pois possui valor acima do permitido.");
        }
    }

    @Test
    public void testTabelaDecisao4() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",200.00,"Cliente 1");

        conta = new Conta(1, "2024-06-22",200.00);
        pagamento = new Pagamento(200.00,"2024-06-23","2024-06-22","BOLETO");
        Conta[] contas = new Conta[1];
        contas[0] = conta;
        fatura.addPagamento(pagamento);

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
        assertEquals(220,pagamento.getValorASomar(),1);
    }

    @Test
    public void testTabelaDecisao5() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",200.00,"Cliente 1");

        conta = new Conta(1, "2024-06-25",200.00);
        pagamento = new Pagamento(200.00,"2024-06-25","2024-06-25","BOLETO");
        Conta[] contas = new Conta[1];
        contas[0] = conta;
        fatura.addPagamento(pagamento);

        String processamento = pc.processar(contas,fatura);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
        assertEquals(0,pagamento.getValorASomar(),1);
    }

    @Test
    public void testTabelaDecisao6() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",200.00,"Cliente 1");

        conta = new Conta(1, "2024-06-07",200.00);
        pagamento = new Pagamento(200.00,"2024-06-07","2024-06-07","CARTAO_CREDITO");
        Conta[] contas = new Conta[1];
        contas[0] = conta;
        fatura.addPagamento(pagamento);

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
        assertEquals(200,pagamento.getValorASomar(),1);
    }

    @Test
    public void testTabelaDecisao7() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",200.00,"Cliente 1");

        conta = new Conta(1, "2024-06-10",200.00);
        pagamento = new Pagamento(200.00,"2024-06-07","2024-06-10","CARTAO_CREDITO");
        Conta[] contas = new Conta[1];
        contas[0] = conta;
        fatura.addPagamento(pagamento);

        String processamento = pc.processar(contas,fatura);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
        assertEquals(0,pagamento.getValorASomar(),1);
    }

    @Test
    public void testTabelaDecisao8() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",200.00,"Cliente 1");

        conta = new Conta(1, "2024-06-25",200.00);
        pagamento = new Pagamento(200.00,"2024-06-24","2024-06-25","CARTAO_CREDITO");
        Conta[] contas = new Conta[1];
        contas[0] = conta;
        fatura.addPagamento(pagamento);

        String processamento = pc.processar(contas,fatura);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
        assertEquals(0,pagamento.getValorASomar(),1);
    }

    @Test
    public void testTabelaDecisao9() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",200.00,"Cliente 1");

        conta = new Conta(1, "2024-06-23",200.00);
        pagamento = new Pagamento(200.00,"2024-06-23","2024-06-23","TRANSFERENCIA_BANCARIA");
        Conta[] contas = new Conta[1];
        contas[0] = conta;
        fatura.addPagamento(pagamento);

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
        assertEquals(200,pagamento.getValorASomar(),1);
    }

    @Test
    public void testTabelaDecisao10() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",200.00,"Cliente 1");

        conta = new Conta(1, "2024-06-25",200.00);
        pagamento = new Pagamento(200.00,"2024-06-24","2024-06-25","TRANSFERENCIA_BANCARIA");
        Conta[] contas = new Conta[1];
        contas[0] = conta;
        fatura.addPagamento(pagamento);

        String processamento = pc.processar(contas,fatura);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
        assertEquals(0,pagamento.getValorASomar(),1);
    }

    @Test
    public void testTabelaDecisao11() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",600.00,"Cliente 1");

        conta = new Conta(1, "2024-06-21",200.00);
        Conta conta2 = new Conta(2, "2024-06-20",50.00);
        Conta conta3 = new Conta(3, "2024-06-19",300.00);
        Conta conta4 = new Conta(4, "2024-06-01",50.00);
        pagamento = new Pagamento(200.00,"2024-06-21","2024-06-21","TRANSFERENCIA_BANCARIA");
        Pagamento pagamento2 = new Pagamento(50.00,"2024-06-20","2024-06-20","BOLETO");
        Pagamento pagamento3 = new Pagamento(300.00,"2024-06-19","2024-06-19","BOLETO");
        Pagamento pagamento4 = new Pagamento(50.00,"2024-06-01","2024-06-01","CARTAO_CREDITO");
        Conta[] contas = new Conta[4];
        contas[0] = conta;
        contas[1] = conta2;
        contas[2] = conta3;
        contas[3] = conta4;
        fatura.addPagamento(pagamento);
        fatura.addPagamento(pagamento2);
        fatura.addPagamento(pagamento3);
        fatura.addPagamento(pagamento4);

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
    }

    @Test
    public void testTabelaDecisao12() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",600.00,"Cliente 1");

        conta = new Conta(1, "2024-06-21",200.00);
        Conta conta2 = new Conta(2, "2024-06-20",50.00);
        Conta conta3 = new Conta(3, "2024-06-19",300.00);
        Conta conta4 = new Conta(4, "2024-06-01",50.00);
        pagamento = new Pagamento(200.00,"2024-06-21","2024-06-21","TRANSFERENCIA_BANCARIA");
        Pagamento pagamento2 = new Pagamento(50.00,"2024-06-21","2024-06-20","BOLETO");
        Pagamento pagamento3 = new Pagamento(300.00,"2024-06-19","2024-06-19","BOLETO");
        Pagamento pagamento4 = new Pagamento(50.00,"2024-06-01","2024-06-01","CARTAO_CREDITO");
        Conta[] contas = new Conta[4];
        contas[0] = conta;
        contas[1] = conta2;
        contas[2] = conta3;
        contas[3] = conta4;
        fatura.addPagamento(pagamento);
        fatura.addPagamento(pagamento2);
        fatura.addPagamento(pagamento3);
        fatura.addPagamento(pagamento4);

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
        assertEquals(55,pagamento2.getValorASomar(),1);
    }

    @Test
    public void testTabelaDecisao13() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",600.00,"Cliente 1");

        conta = new Conta(1, "2024-06-21",200.00);
        Conta conta2 = new Conta(2, "2024-06-20",50.00);
        Conta conta3 = new Conta(3, "2024-06-19",300.00);
        Conta conta4 = new Conta(4, "2024-06-01",50.00);
        pagamento = new Pagamento(200.00,"2024-06-25","2024-06-21","TRANSFERENCIA_BANCARIA"); //não entra
        Pagamento pagamento2 = new Pagamento(50.00,"2024-06-21","2024-06-20","BOLETO");
        Pagamento pagamento3 = new Pagamento(300.00,"2024-06-19","2024-06-19","BOLETO");
        Pagamento pagamento4 = new Pagamento(50.00,"2024-06-01","2024-06-01","CARTAO_CREDITO");
        Conta[] contas = new Conta[4];
        contas[0] = conta;
        contas[1] = conta2;
        contas[2] = conta3;
        contas[3] = conta4;
        fatura.addPagamento(pagamento);
        fatura.addPagamento(pagamento2);
        fatura.addPagamento(pagamento3);
        fatura.addPagamento(pagamento4);

        String processamento = pc.processar(contas,fatura);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
        assertEquals(55,pagamento2.getValorASomar(),1);
    }

    @Test
    public void testTabelaDecisao14() throws illegalArgumentsException {
        fatura = new Fatura("2024-06-23",600.00,"Cliente 1");

        conta = new Conta(1, "2024-06-21",200.00);
        Conta conta2 = new Conta(2, "2024-06-20",50.00);
        Conta conta3 = new Conta(3, "2024-06-19",300.00);
        Conta conta4 = new Conta(4, "2024-06-01",50.00);
        pagamento = new Pagamento(100.00,"2024-06-21","2024-06-21","TRANSFERENCIA_BANCARIA");
        Pagamento pagamento2 = new Pagamento(50.00,"2024-06-24","2024-06-25","BOLETO"); //não entra
        Pagamento pagamento3 = new Pagamento(300.00,"2024-06-25","2024-06-15","BOLETO"); //não entra
        Pagamento pagamento4 = new Pagamento(500.00,"2024-06-01","2024-06-01","CARTAO_CREDITO");
        Conta[] contas = new Conta[4];
        contas[0] = conta;
        contas[1] = conta2;
        contas[2] = conta3;
        contas[3] = conta4;
        fatura.addPagamento(pagamento);
        fatura.addPagamento(pagamento2);
        fatura.addPagamento(pagamento3);
        fatura.addPagamento(pagamento4);

        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
    }
}