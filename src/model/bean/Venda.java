package model.bean;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    private ArrayList<Integer> numComandaPratos = new ArrayList<>();
    private ArrayList<Integer> numComandaItens = new ArrayList<>();
    
    public Venda(){}
    
    public Venda(Double valor){
        this.total = valor;
        this.totalPendente = valor;
    }
    
    // MÉTODOS IMPORTANTES PARA FECHAMENTO DE COMANDAS
    public void setItem(Produto p, String qtd, int idComanda){
        numComandaItens.add(idComanda);
        Integer qtdInt = Integer.parseInt(qtd);
        itens.add(p);
        qnt.add(qtd);
        this.total += p.getPrecoComDesconto()*qtdInt;
        this.totalPendente += p.getPrecoComDesconto()*qtdInt;
    }
    
    public void setPrato(Double d, int id){
        numComandaPratos.add(id);
        pratos.add(d);
        this.total += d;
        this.totalPendente += d;
    }
    
    public void removerItem(int indice){
        // Declaração das variáveis
        int qtd = Integer.parseInt(this.qnt.get(indice));
        BigDecimal tirar = new BigDecimal(this.itens.get(indice).getPrecoComDesconto()*qtd);
        BigDecimal valorPendente = new BigDecimal(this.totalPendente);
        BigDecimal valor = new BigDecimal(this.total);
  
        // Setar valores dos BigDecimal nas variáveis da venda
        this.total = valor.subtract(tirar, MathContext.DECIMAL32).doubleValue();
        this.totalPendente = valorPendente.subtract(tirar, MathContext.DECIMAL32).doubleValue();
        
        this.numComandaItens.remove(indice);
        this.itens.remove(indice);
        this.qnt.remove(indice);   
    }
    
    public void removerPrato(int indice){
        BigDecimal total = new BigDecimal(this.total);
        BigDecimal totalPendente = new BigDecimal(this.totalPendente);
        BigDecimal adicionada = new BigDecimal(this.pratos.get(indice));
        total = total.subtract(adicionada, MathContext.DECIMAL32);
        this.total = total.doubleValue();
        totalPendente = totalPendente.subtract(adicionada, MathContext.DECIMAL32);
        this.totalPendente = totalPendente.doubleValue();
        this.numComandaPratos.remove(indice);
        this.pratos.remove(indice);  
    }
    
    public void setForma(Forma f){
        BigDecimal atual = new BigDecimal(this.getTotalPendente());
        BigDecimal adicionada = new BigDecimal(f.getValor());
        BigDecimal fim = atual.subtract(adicionada, MathContext.DECIMAL32);
        this.totalPendente = fim.doubleValue();
        this.formasPagamento.add(f);
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
        // Subtrair 1 hora por causa do horário de verão
        /*Date teste = new Date();
        data.setTime(teste);
        data.set(Calendar.HOUR, data.get(Calendar.HOUR)-1);*/
        // --------------------------------------------
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

    public ArrayList<Integer> getNumComandaItens() {
        return numComandaItens;
    }

    public void setNumComandaItens(ArrayList<Integer> numComandaItens) {
        this.numComandaItens = numComandaItens;
    }

    public void setFormaPagamento(Forma f){
        this.formasPagamento.add(f);
    }
    
    public Forma getFormaPagamento(int indice){
        return this.formasPagamento.get(indice);
    }

    public ArrayList<Integer> getNumComandaPratos() {
        return numComandaPratos;
    }

    public void setNumComandaPratos(ArrayList<Integer> numComandaPratos) {
        this.numComandaPratos = numComandaPratos;
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
