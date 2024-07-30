package processadorContas;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Teste com apenas uma conta, de valor que não atinge a fatura. Status da fatura é PENDENTE")
    public void test1() throws illegalArgumentsException {
        Conta[] contas = new Conta[1];
        contas[0] = conta1;
        String processamento = pc.processar(contas,fatura1);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
    }

    @Test
    @DisplayName("Teste com múltiplas contas, de valor que atinge a fatura. Status da fatura é PAGA")
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
    @DisplayName("Teste da multa de 10% no boleto quando o pagamento é atrasado. Um boleto de 400 reais passaria a valer 440 devido ao atraso.")
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
    @DisplayName("Teste do limite superior do boleto (R$ 5000,00). A especificação não diz o que deve acontecer neste caso, " +
            "apenas que o boleto não pode assumir estes valores, então assumi que uma exceção seria lançada.")
    public void test4() {
        Fatura faturaBoleto2 = new Fatura("2024-05-20",6500.00,"Cliente 1");

        Conta contaBoleto2 = new Conta(4, "2024-05-02",5500.00);
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
    @DisplayName("Teste do limite superior do boleto (R$ 5000,00).")
    public void test4_5() {
        Fatura faturaBoleto25 = new Fatura("2024-05-20",5000.00,"Cliente 1");

        Conta contaBoleto25 = new Conta(4, "2024-05-02",5000.00);
        Conta[] contasBoleto25 = new Conta[1];
        contasBoleto25[0] = contaBoleto25;

        Pagamento pagamentoBoleto25 = new Pagamento(5000.00,"2024-05-02","2024-05-02","BOLETO");
        try{
            faturaBoleto25.addPagamento(pagamentoBoleto25);
            String processamento = pc.processar(contasBoleto25,faturaBoleto25);
            String msg = "PAGA";
            assertEquals(msg, processamento);
        }catch(illegalArgumentsException e){
            System.out.println("Boleto não aceito, pois possui valor acima do permitido.");
        }
    }

    @Test
    @DisplayName("Teste do limite inferior do boleto (R$ 0,01). A especificação não diz o que deve acontecer neste caso, " +
            "apenas que o boleto não pode assumir estes valores, então assumi que uma exceção seria lançada.")
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
    @DisplayName("Teste do limite inferior do boleto (R$ 0,01).")
    public void test5_5() {
        Fatura faturaBoleto35 = new Fatura("2024-05-20",0.01,"Cliente 1");

        Conta contaBoleto35 = new Conta(4, "2024-05-02",0.01);
        Conta[] contasBoleto35 = new Conta[1];
        contasBoleto35[0] = contaBoleto35;

        Pagamento pagamentoBoleto35 = new Pagamento(0.01,"2024-05-02","2024-05-02","BOLETO");
        try{
            faturaBoleto35.addPagamento(pagamentoBoleto35);
            String processamento = pc.processar(contasBoleto35,faturaBoleto35);
            String msg = "PAGA";
            assertEquals(msg, processamento);
        }catch(illegalArgumentsException e){
            System.out.println("Boleto não aceito, pois possui valor abaixo do permitido.");
        }
    }

    @Test
    @DisplayName("Teste de pagamento com Cartão de Crédito em limite de tempo válido.")
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

    @Test
    @DisplayName("Teste de pagamento com Cartão de Crédito em limite de tempo inválido.")
    public void test7() throws illegalArgumentsException {
        Fatura faturaCartao2 = new Fatura("2024-05-20",6500.00,"Cliente 1");

        Conta contaCartao2 = new Conta(5, "2024-05-06",6600); //limite dos 14 dias que não entra na fatura (a partir de 15 seria aceito)
        Conta[] contasCartao2 = new Conta[1];
        contasCartao2[0] = contaCartao2;

        Pagamento pagamentoCartao = new Pagamento(6600,"2024-05-06","2024-05-06","CARTAO_CREDITO");
        //por causa do prazo, o pagamento não entrará na fatura

        faturaCartao2.addPagamento(pagamentoCartao);
        String processamento = pc.processar(contasCartao2,faturaCartao2);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
    }

    @Test
    @DisplayName("Teste de pagamento com Cartão de Crédito em limite de tempo válido (último dia antes de se tornar inválido).")
    public void test8() throws illegalArgumentsException {
        Fatura faturaCartao3 = new Fatura("2024-05-20",6500.00,"Cliente 1");

        Conta contaCartao3 = new Conta(5, "2024-05-05",6600);//limite para entrar na fatura (a partir do próximo dia não seria aceito)
        Conta[] contasCartao3 = new Conta[1];
        contasCartao3[0] = contaCartao3;

        Pagamento pagamentoCartao = new Pagamento(6600,"2024-05-05","2024-05-05","CARTAO_CREDITO");
        //por causa do prazo, o pagamento não entrará na fatura

        faturaCartao3.addPagamento(pagamentoCartao);
        String processamento = pc.processar(contasCartao3,faturaCartao3);
        String msg = "PAGA";
        assertEquals(msg, processamento);
    }

    @Test
    @DisplayName("Teste com os três tipos de pagamento e boleto atrasado.")
    public void test9() throws illegalArgumentsException {
        //teste geral que envolve todas as três modalidades de pagamento
        Fatura faturaGeral = new Fatura("2024-05-20",6500.00,"Cliente 1");

        Conta contaGeralBoleto = new Conta(6, "2024-05-18", 2000);
        Conta contaGeralCartao = new Conta(7, "2024-05-01", 1000);
        Conta contaGeralTransferencia = new Conta(8, "2024-05-01", 3500);

        Pagamento pagamentoGeralCartao = new Pagamento(1000,"2024-05-02","2024-05-01","CARTAO_CREDITO");
        Pagamento pagamentoGeralBoleto = new Pagamento(2000,"2024-05-19","2024-05-18","BOLETO"); //é pra aparecer 2200 no final (2000*1.1)
        Pagamento pagamentoGeralTransferencia = new Pagamento(3500,"2024-05-01","2024-05-01","TRANSFERENCIA_BANCARIA");

        Conta[] contasGerais = new Conta[3];
        contasGerais[0] = contaGeralBoleto;
        contasGerais[1] = contaGeralCartao;
        contasGerais[2] = contaGeralTransferencia;

        faturaGeral.addPagamento(pagamentoGeralBoleto);
        faturaGeral.addPagamento(pagamentoGeralCartao);
        faturaGeral.addPagamento(pagamentoGeralTransferencia);

        String processamento = pc.processar(contasGerais,faturaGeral);
        String msg = "PAGA";
        assertEquals(msg, processamento);
    }

    @Test
    @DisplayName("Teste com os três tipos de pagamento e cartão fora do prazo.")
    public void test10() throws illegalArgumentsException {
        //teste geral que envolve todas as três modalidades de pagamento
        Fatura faturaGeral2 = new Fatura("2024-05-20",6500.00,"Cliente 1");

        Conta contaGeralBoleto2 = new Conta(6, "2024-05-18", 2000);
        Conta contaGeralCartao2 = new Conta(7, "2024-05-16", 1000);
        Conta contaGeralTransferencia2 = new Conta(8, "2024-05-01", 3500);

        Pagamento pagamentoGeralCartao2 = new Pagamento(1000,"2024-05-16","2024-05-16","CARTAO_CREDITO"); //fora do prazo, é pra aparecer 0
        Pagamento pagamentoGeralBoleto2 = new Pagamento(2000,"2024-05-19","2024-05-18","BOLETO");
        Pagamento pagamentoGeralTransferencia2 = new Pagamento(3500,"2024-05-01","2024-05-01","TRANSFERENCIA_BANCARIA");

        Conta[] contasGerais2 = new Conta[3];
        contasGerais2[0] = contaGeralBoleto2;
        contasGerais2[1] = contaGeralCartao2;
        contasGerais2[2] = contaGeralTransferencia2;

        faturaGeral2.addPagamento(pagamentoGeralBoleto2);
        faturaGeral2.addPagamento(pagamentoGeralCartao2);
        faturaGeral2.addPagamento(pagamentoGeralTransferencia2);

        String processamento = pc.processar(contasGerais2,faturaGeral2);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
    }

    @Test
    @DisplayName("Teste com os três tipos de pagamento e todos os pagamentos estão fora do prazo.")
    public void test11() throws illegalArgumentsException {
        //teste geral que envolve todas as três modalidades de pagamento
        Fatura faturaGeral3 = new Fatura("2023-05-20",6500.00,"Cliente 1");

        Conta contaGeralBoleto3 = new Conta(6, "2024-06-01", 2000);
        Conta contaGeralCartao3 = new Conta(7, "2024-06-01", 1000);
        Conta contaGeralTransferencia3 = new Conta(8, "2024-06-01", 3500);

        Pagamento pagamentoGeralCartao3 = new Pagamento(1000,"2024-06-01","2024-06-01","CARTAO_CREDITO");
        Pagamento pagamentoGeralBoleto3 = new Pagamento(2000,"2024-06-02","2024-06-01","BOLETO");
        Pagamento pagamentoGeralTransferencia3 = new Pagamento(3500,"2024-06-01","2024-06-01","TRANSFERENCIA_BANCARIA");

        Conta[] contasGerais = new Conta[3];
        contasGerais[0] = contaGeralBoleto3;
        contasGerais[1] = contaGeralCartao3;
        contasGerais[2] = contaGeralTransferencia3;

        faturaGeral3.addPagamento(pagamentoGeralBoleto3);
        faturaGeral3.addPagamento(pagamentoGeralCartao3);
        faturaGeral3.addPagamento(pagamentoGeralTransferencia3);

        String processamento = pc.processar(contasGerais,faturaGeral3);
        String msg = "PENDENTE"; //nenhum dos pagamentos está no prazo, a soma deve ser 0
        assertEquals(msg, processamento);
    }

}