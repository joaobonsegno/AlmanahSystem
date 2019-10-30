package model.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AgendaDespesa {
    private int id, qtdVezes;
    private String descricao, data, frequencia;
    private Double valor;
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getQtdVezes() {
        return qtdVezes;
    }

    public void setQtdVezes(int qtdVezes) {
        this.qtdVezes = qtdVezes;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public String dataAtual(){
        Calendar data = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy   HH:mm:ss");
        String dataFormatada = sdf.format(data.getTime());
        return dataFormatada;
    }
}
