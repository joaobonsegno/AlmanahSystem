package relatorio;

import java.util.Date;
import model.bean.Forma;

public class RelatorioForma {
    private String data, descricao, tipo;
    private Forma forma;

    public void teste(){
        
    }
    
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

    public Forma getForma() {
        return forma;
    }

    public void setForma(Forma forma) {
        this.forma = forma;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
