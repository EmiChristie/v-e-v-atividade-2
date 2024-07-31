package sistemaIngressos;

public class Show {
    int capacidade;
    int ingressosVendidos;

    double receitaLiquida;
    double despesas;
    double cache;

    public Show(String data, boolean dataEspecial, double despesas, double cache) {
    }

    public double getPreco() {
        return 0;
    }

    public int getId() {
        return 0;
    }

    public int getIngressosVendidos() {
        return ingressosVendidos;
    }

    public String getData() {
        return "02/02/2020";
    }

    public boolean getDataEspecial() {
        return false;
    }

    public double getDespesas() {
        return 2000.00;
    }

    public double getCache() {
        return 1000.00;
    }

    public String gerarRelatorio() {
        return "Ingressos: VIP(0), NORMAL(0), MEIA_ENTRADA(0); Receita Liquida = R$0.00; status financeiro; ESTÁVEL";
    }

    public double getRendaLiquida() {
        return receitaLiquida;
    }
}
