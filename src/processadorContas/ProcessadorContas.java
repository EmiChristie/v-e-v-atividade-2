package processadorContas;

import processadorContas.exceptions.illegalArgumentsException;

import java.util.List;

public class ProcessadorContas {

    public String processar(Conta[] contas, Fatura fatura) throws illegalArgumentsException {
        String status = "PENDENTE";
        if(somarValores(fatura.getPagamentos()) >= fatura.getValor()){
            status = "PAGA";
        }
        return status;
    }

    double somarValores(List<Pagamento> pags) throws illegalArgumentsException {
        double soma = 0;
        for(Pagamento i:pags){
            soma+=i.getValor();
        }
        return soma;
    }

    void adicionarPagamentoAFatura(Fatura fatura, Pagamento pag){
        fatura.addPagamento(pag);
    }
}
