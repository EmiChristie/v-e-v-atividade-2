package sistemaIngressos;

public class Show {
    int id;
    int capacidade;
    int ingressosVendidos;
    int ingressosNormais;
    int ingressosMeia;
    int ingressosVip;
    double receitaLiquida;
    double despesas;
    double cache;
    boolean dataEspecial;
    double preco;
    String data;
    String status;

    public Show(int capacidade, String data, boolean dataEspecial, double despesas, double cache, int id, double preco) {
        this.capacidade = capacidade;
        receitaLiquida = 0 - cache - despesas;
        this.dataEspecial = dataEspecial;
        this.despesas = despesas;
        this.cache = cache;
        ingressosNormais = 0;
        ingressosMeia = 0;
        ingressosVip = 0;
        this.data = data;
        status = "PREJUIZO";
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void mudarReceita(double v) {
        receitaLiquida += v;
        if (receitaLiquida < 0) {
            status = "PREJUIZO";
        } else if (receitaLiquida > 0) {
            status = "LUCRO";
        } else {
            status = "ESTAVEL";
        }
    }

    public void adicionarIngresso(String tipo) {
        if(tipo.equals("NORMAL")) {
            ingressosNormais++;
        } else if (tipo.equals("VIP")) {
            ingressosVip++;
        } else {
            ingressosMeia++;
        }
    }

    public void removerIngresso(String tipo) {
        if(tipo.equals("NORMAL")) {
            ingressosNormais--;
        } else if (tipo.equals("VIP")) {
            ingressosVip--;
        } else {
            ingressosMeia--;
        }
    }

    public String gerarRelatorio() {
        return "Ingressos: VIP("+ingressosVip+"), NORMAL("+ingressosNormais+"), MEIA_ENTRADA("+ingressosMeia+"); " +
                "Receita Liquida = R$"+receitaLiquida+"; status financeiro = "+status;
    }

    public int getId() {
        return id;
    }

    public double getRendaLiquida() {
        return receitaLiquida;
    }

    public int getIngressosVendidos() {
        return ingressosVendidos;
    }

    public String getData() {
        return data;
    }

    public boolean getDataEspecial() {
        return dataEspecial;
    }

    public double getDespesas() {
        return despesas;
    }

    public double getCache() {
        return cache;
    }
}