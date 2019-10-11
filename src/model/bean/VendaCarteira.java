package model.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class VendaCarteira {
    private Integer id;
    private Double total;
    private String data;
    private Caixa caixa;
    private Cliente cliente;
    
    public String dataAtual(){
        Calendar data = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy   HH:mm:ss");
        String dataFormatada = sdf.format(data.getTime());
        return dataFormatada;
    }
    
    public void setAtributos(String data, Double total){
        this.data = data;
        this.total = total;
    }
    
    // GETTERS E SETTERS PADRÕES
    public Integer getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setId(Integer id) {
        this.id = id;
    }
   
    public VendaCarteira(){}

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }
}
