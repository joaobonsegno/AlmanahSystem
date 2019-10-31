package model.bean;

public class Forma {
    private int id;
    private Double valor;
    private String formaPagamento;
    private Venda venda;
    private Cliente cliente;

    public Forma(Double v, String f, Venda venda){
        this.valor = v;
        this.formaPagamento = f;
        this.venda = venda;       
    }
    
    public Forma(Double v, String f, Cliente c){
        this.valor = v;
        this.formaPagamento = f;
        this.cliente = c;       
    }
    
    // GETTERS E SETTERS PADRÃO
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
}
