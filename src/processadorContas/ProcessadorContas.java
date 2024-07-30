package processadorContas;

import processadorContas.exceptions.illegalArgumentsException;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;

public class ProcessadorContas {

    public String processar(Conta[] contas, Fatura fatura) throws illegalArgumentsException {
        String status = "PENDENTE";
        verificarDataCartao(contas, fatura.getPagamentos());
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

    void verificarDataCartao(Conta[] contas, List<Pagamento> pags){
        for(int i = 0; i < contas.length;i++){
            Pagamento p = pags.get(i);
            String dataConta15DiasAtras = get15DiasAtras(contas[i].getData());
            if(Objects.equals(p.getTipo(), "CARTAO_CREDITO")){
                if(LocalDate.parse(p.getData()).isAfter(LocalDate.parse(dataConta15DiasAtras))){
                    p.valor = 0;
                }
            }
        }
    }

    private String get15DiasAtras(String data) {
        return LocalDate.parse(data).minusDays(15).toString();
    }
}
