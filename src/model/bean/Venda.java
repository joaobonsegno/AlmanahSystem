package model.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import main.EncerrarComanda;
import model.dao.ProdutoDAO;

public class Venda {
    private Integer idBanco;
    private Double total, totalPendente;
    private String data;
    private Caixa caixa;
    private Cliente cliente;
    private ArrayList<Produto> itens = new ArrayList<>();
    private ArrayList<String> qnt = new ArrayList<>();
    private ArrayList<Double> pratos = new ArrayList<>();
    private ArrayList<Forma> formasPagamento = new ArrayList<>();

    public Venda(){}
    
    public Venda(Double valor){
        this.total = valor;
        this.totalPendente = valor;
    }
    
    // MÉTODOS IMPORTANTES PARA FECHAMENTO DE COMANDAS
    public void setItem(Produto p, String qtd){
        Integer qtdInt = Integer.parseInt(qtd);
        itens.add(p);
        qnt.add(qtd);
        this.total += p.getPrecoComDesconto()*qtdInt;
        this.totalPendente += p.getPrecoComDesconto()*qtdInt;
    }
    
    public void setPrato(Double d){
        pratos.add(d);
        this.total += d;
        this.totalPendente += d;
    }
    
    public void removerItem(int indice){
        int qtd = Integer.parseInt(this.qnt.get(indice));
        Double valor = this.itens.get(indice).getPrecoComDesconto()*qtd;
        Produto p = this.itens.get(indice);
        this.total -= valor;
        this.totalPendente -= valor;
        this.itens.remove(indice);
        this.qnt.remove(indice);   
    }
    
    public void removerPrato(int indice){
        this.total -= this.pratos.get(indice);
        this.totalPendente -= this.pratos.get(indice);
        this.pratos.remove(indice);  
    }
    
    public void setForma(Forma f){
        this.formasPagamento.add(f);
        this.totalPendente -= f.getValor();
    }
    
    public void removerForma(int indice){
        Double d = this.formasPagamento.get(indice).getValor();
        this.total += d;
        this.totalPendente += d;
        this.formasPagamento.remove(indice);      
    }
    
    // -----------------------------------------------
    public String dataAtual(){
        Calendar data = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy   HH:mm:ss");
        String dataFormatada = sdf.format(data.getTime());
        return dataFormatada;
    }

    public Double getTotalPendente() {
        return totalPendente;
    }

    public ArrayList<Forma> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(ArrayList<Forma> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public void setFormaPagamento(Forma f){
        this.formasPagamento.add(f);
    }
    
    public Forma getFormaPagamento(int indice){
        return this.formasPagamento.get(indice);
    }
    
    public void setTotalPendente(Double totalPendente) {
        this.totalPendente = totalPendente;
    }
    
    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ArrayList<Produto> getItens() {
        return itens;
    }

    public ArrayList<Double> getPratos() {
        return pratos;
    }

    public ArrayList<String> getQnt() {
        return qnt;
    }

    public void setQnt(String qnt) {
        this.qnt.add(qnt);
    } 
    
    public void setPratosBanco(Double prato) {
        this.pratos.add(prato);
    }
    
    public void setItensBanco(Produto item, String qtd) {
        this.itens.add(item);
        this.qnt.add(qtd);
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
