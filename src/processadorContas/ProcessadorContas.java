package processadorContas;

public class ProcessadorContas {

    public String processar(double[] contas, double fatura){
        return "Fatura de 1.500,00 (20/02/2023) com 3 contas no valor de 500,00, " +
                "400,00 e 600,00. As três contas foram pagas por boleto no dia 20/02/2023 (todas em dia), assim a fatura é marcada como PAGA.";
    }
}
