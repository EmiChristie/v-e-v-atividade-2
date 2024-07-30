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
    public void setup() throws illegalArgumentsException {
        pc = new ProcessadorContas();

        conta1 = new Conta(1, "2024-05-02",500.00);

        fatura1 = new Fatura("2024-05-02",1500.00,"Cliente 1");

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

    @Test
    public void test3() throws illegalArgumentsException {
        Fatura faturaBoleto = new Fatura("2024-05-20",1500.00,"Cliente 1");

        Conta contaBoleto = new Conta(4, "2024-05-02",400.00);
        Pagamento pagamentoBoleto = new Pagamento(400,"2024-05-03","2024-05-02","BOLETO");
        faturaBoleto.addPagamento(pagamentoBoleto);
        Conta[] contasBoleto = new Conta[1];
        contasBoleto[0] = contaBoleto;

        String processamento = pc.processar(contasBoleto,faturaBoleto);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
        assertEquals(440,pagamentoBoleto.getValorASomar(),1);
    }

    @Test
    public void test4() {
        Fatura faturaBoleto2 = new Fatura("2024-05-20",6500.00,"Cliente 1");

        Conta contaBoleto2 = new Conta(4, "2024-05-02",5500.00); //é pra quebrar por ser acima de 5000
        Conta[] contasBoleto2 = new Conta[1];
        contasBoleto2[0] = contaBoleto2;

        Pagamento pagamentoBoleto2 = new Pagamento(5500.00,"2024-05-02","2024-05-02","BOLETO");
        try{
            faturaBoleto2.addPagamento(pagamentoBoleto2);
            String processamento = pc.processar(contasBoleto2,faturaBoleto2);
            String msg = "PENDENTE";
            assertEquals(msg, processamento);
        }catch(illegalArgumentsException e){
            System.out.println("Boleto não aceito, pois possui valor acima do permitido.");
        }
    }

    @Test
    public void test5() {
        Fatura faturaBoleto3 = new Fatura("2024-05-20",6500.00,"Cliente 1");

        Conta contaBoleto3 = new Conta(4, "2024-05-02",0);
        Conta[] contasBoleto3 = new Conta[1];
        contasBoleto3[0] = contaBoleto3;

        Pagamento pagamentoBoleto3 = new Pagamento(0,"2024-05-02","2024-05-02","BOLETO");
        try{
            faturaBoleto3.addPagamento(pagamentoBoleto3);
            String processamento = pc.processar(contasBoleto3,faturaBoleto3);
            String msg = "PENDENTE";
            assertEquals(msg, processamento);
        }catch(illegalArgumentsException e){
            System.out.println("Boleto não aceito, pois possui valor abaixo do permitido.");
        }
    }

    @Test
    public void test6() throws illegalArgumentsException {
        Fatura faturaCartao = new Fatura("2024-05-20",6500.00,"Cliente 1");

        Conta contaCartao = new Conta(5, "2024-05-02",6600);
        Conta[] contasCartao = new Conta[1];
        contasCartao[0] = contaCartao;

        Pagamento pagamentoCartao = new Pagamento(6600,"2024-05-02","2024-05-02","CARTAO_CREDITO");
        faturaCartao.addPagamento(pagamentoCartao);
        String processamento = pc.processar(contasCartao,faturaCartao);
        String msg = "PAGA";
        assertEquals(msg, processamento);
    }

}