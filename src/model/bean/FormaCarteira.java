package model.bean;

public class FormaCarteira {
    private int id;
    private Double valor;
    private String formaPagamento;
    private VendaCarteira venda;
    private Cliente cliente;
    
    public FormaCarteira(Double v, String f, Cliente c){
        this.valor = v;
        this.formaPagamento = f;
        this.cliente = c;       
    }
    
    public FormaCarteira(){}
    
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

    public VendaCarteira getVenda() {
        return venda;
    }

    public void setVenda(VendaCarteira venda) {
        this.venda = venda;
    }
}
