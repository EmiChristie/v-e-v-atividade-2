package processadorContas;

import processadorContas.exceptions.illegalArgumentsException;

import java.util.List;
import java.util.Objects;
import java.time.LocalDate;

public class ProcessadorContas {

    public String processar(Conta[] contas, Fatura fatura) throws illegalArgumentsException {
        String status = "PENDENTE";
        verificarDataCartao(contas, fatura.getPagamentos(), fatura.getData());
        verificarDataBoletos(contas, fatura.getPagamentos());
        if(somarValores(fatura.getPagamentos(),fatura.getData()) >= fatura.getValor()){
            status = "PAGA";
        }
        return status;
    }

    private void verificarDataBoletos(Conta[] contas, List<Pagamento> pags) {
        for(int i = 0; i < contas.length;i++){
            Pagamento p = pags.get(i);
            if(Objects.equals(p.getTipo(), "BOLETO")){
                LocalDate data1 = LocalDate.parse(p.getData());
                LocalDate data2 = LocalDate.parse(contas[i].getData());
                if(data1.isAfter(data2)){
                    double novoValor = p.getValor()*1.1;
                    p.setValorASomar(novoValor);
                }
            }
        }
    }

    double somarValores(List<Pagamento> pags, String data) {
        double soma = 0;
        for(Pagamento i:pags){
            if(dataDentroDoLimite(i.getData(),data)){
                soma+=i.getValorASomar();
                System.out.println(i.getValorASomar());
            }
        }
        return soma;
    }

    private boolean dataDentroDoLimite(String data, String dataFatura) {
        LocalDate data1 = LocalDate.parse(data);
        LocalDate data2 = LocalDate.parse(dataFatura);
        LocalDate data3 = data2.plusDays(1);
        return data1.isBefore(data3);
    }


    void verificarDataCartao(Conta[] contas, List<Pagamento> pags, String dataFatura){
        String dataFatura15DiasAtras = get15DiasAtras(dataFatura);
        for(int i = 0; i < contas.length;i++){
            Pagamento p = pags.get(i);
            if(p.getTipo().equals("CARTAO_CREDITO")){
                if(LocalDate.parse(contas[i].getData()).isAfter(LocalDate.parse(dataFatura15DiasAtras))){
                    p.setValorASomar(0);
                }
            }
        }
    }

    private String get15DiasAtras(String data) {
        return LocalDate.parse(data).minusDays(15).toString();
    }
}
