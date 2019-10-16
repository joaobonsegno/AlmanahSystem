package relatorio;

import model.bean.FormaCarteira;

public class RelatorioFormaCarteira {
    private String data, descricao, tipo;
    private FormaCarteira formaCarteira;

    
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
        this.descricao = descricao;
    }

    public FormaCarteira getFormaCarteira() {
        return formaCarteira;
    }

    public void setFormaCarteira(FormaCarteira formaCarteira) {
        this.formaCarteira = formaCarteira;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
