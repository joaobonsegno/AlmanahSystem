package model.bean;

public class Forma {
    private int id;
    private Double valor;
    private String formaPagamento;
    private Venda venda;
    private Comanda comanda;

    public Forma(Double v, String f, Comanda c){
        this.valor = v;
        this.formaPagamento = f;
        this.comanda = c;       
    }
    
    public Forma(){}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }
    
    
}
