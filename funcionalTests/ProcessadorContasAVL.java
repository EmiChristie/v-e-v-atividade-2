import org.junit.Before;
import org.junit.Test;
import processadorContas.Conta;
import processadorContas.Fatura;
import processadorContas.Pagamento;
import processadorContas.ProcessadorContas;
import processadorContas.exceptions.illegalArgumentsException;

import static org.junit.Assert.assertEquals;

public class ProcessadorContasAVL {
    Conta conta;
    Fatura fatura;
    ProcessadorContas pc;
    Pagamento pagamento;

    @Before
    public void setup(){
        pc = new ProcessadorContas();
    }

    @Test
    public void testAVL1(){
        fatura = new Fatura("2024-05-02",0,"Cliente 1");
        Conta conta = new Conta(4, "2024-05-02",0);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        pagamento = new Pagamento(0,"2024-05-02","2024-05-02","BOLETO");
        try{
            fatura.addPagamento(pagamento);
            String processamento = pc.processar(contas,fatura);
            String msg = "PAGA";
            assertEquals(msg, processamento);
        }catch(illegalArgumentsException e){
            System.out.println("Boleto não aceito, pois possui valor abaixo do permitido.");
        }
    }

    @Test
    public void testAVL2() throws illegalArgumentsException {
        fatura = new Fatura("2024-05-02",0.01,"Cliente 1");
        Conta conta = new Conta(4, "2024-05-02",0.01);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        pagamento = new Pagamento(0.01,"2024-05-02","2024-05-02","BOLETO");
        fatura.addPagamento(pagamento);
        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
    }

    @Test
    public void testAVL3() throws illegalArgumentsException {
        fatura = new Fatura("2024-05-02",0.02,"Cliente 1");
        Conta conta = new Conta(4, "2024-05-02",0.02);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        pagamento = new Pagamento(0.02,"2024-05-02","2024-05-02","BOLETO");
        fatura.addPagamento(pagamento);
        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
    }

    @Test
    public void testAVL4() throws illegalArgumentsException {
        fatura = new Fatura("2024-05-02",150,"Cliente 1");
        Conta conta = new Conta(4, "2024-05-02",50);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        pagamento = new Pagamento(50,"2024-05-02","2024-05-02","BOLETO");
        fatura.addPagamento(pagamento);
        String processamento = pc.processar(contas,fatura);
        String msg = "PENDENTE";
        assertEquals(msg, processamento);
    }

    @Test
    public void testAVL5() throws illegalArgumentsException {
        fatura = new Fatura("2024-05-02",4999.99,"Cliente 1");
        Conta conta = new Conta(4, "2024-05-02",4999.99);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        pagamento = new Pagamento(4999.99,"2024-05-02","2024-05-02","BOLETO");
        fatura.addPagamento(pagamento);
        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
    }

    @Test
    public void testAVL6() throws illegalArgumentsException {
        fatura = new Fatura("2024-05-02",5000,"Cliente 1");
        Conta conta = new Conta(4, "2024-05-02",5000);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        pagamento = new Pagamento(5000,"2024-05-02","2024-05-02","BOLETO");
        fatura.addPagamento(pagamento);
        String processamento = pc.processar(contas,fatura);
        String msg = "PAGA";
        assertEquals(msg, processamento);
    }

    @Test
    public void testAVL7(){
        fatura = new Fatura("2024-05-02",5000.01,"Cliente 1");
        Conta conta = new Conta(4, "2024-05-02",5000.01);
        Conta[] contas = new Conta[1];
        contas[0] = conta;

        pagamento = new Pagamento(5000.01,"2024-05-02","2024-05-02","BOLETO");
        try{
            fatura.addPagamento(pagamento);
            String processamento = pc.processar(contas,fatura);
            String msg = "PAGA";
            assertEquals(msg, processamento);
        }catch(illegalArgumentsException e){
            System.out.println("Boleto não aceito, pois possui valor acima do permitido.");
        }
    }

}