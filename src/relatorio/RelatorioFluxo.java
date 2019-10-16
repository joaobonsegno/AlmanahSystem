package relatorio;

public class RelatorioFluxo {
    private String data, descricao, tipo;
    private Double valor;

    
    //GETTERS E SETTERS PADRÃO
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao.equals("Dinheiro")){
            this.descricao = "Venda em dinheiro";
        }else if (descricao.equals("Voucher")){
            this.descricao = "Venda em voucher";
        }else if (descricao.equals("Crédito")){
            this.descricao = "Venda no cartão de crédito";
        }else if (descricao.equals("Débito")){
            this.descricao = "Venda no cartão de débito";
        }else{
            this.descricao = descricao;
        }
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }  

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
