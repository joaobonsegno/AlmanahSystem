package model.bean;

public class Carteira {
    private Double valor;
    private String data;
    private int id;
    private Funcionario funcionario;
    private Cliente cliente;
    private Forma forma;
    
    public Carteira(){}
    
    public Carteira(String d, Double v, Cliente c, Funcionario f){
        this.data = d;
        this.valor = v;
        this.cliente = c;
        this.funcionario = f;
    }
    
    //GETTERS E SETTERS PADRÃO
    public Double getValor() {
        return valor;
    }

    public Forma getForma() {
        return forma;
    }

    public void setForma(Forma forma) {
        this.forma = forma;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }   

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
