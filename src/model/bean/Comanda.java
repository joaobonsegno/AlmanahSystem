package model.bean;

import java.util.ArrayList;
import main.GerenciadorComandas;
import model.dao.ComandaDAO;

public class Comanda {
    private Integer id, idBanco, status;
    private Double valor;
    private ArrayList<Produto> itens = new ArrayList<>();
    private ArrayList<String> qnt = new ArrayList<>();
    private ArrayList<Double> pratos = new ArrayList<>();
    
    public Comanda(Integer id){
        this.id = id;
        this.valor = 0.00;
        this.status = 1;
    }
    
    public Comanda(){}

    public Integer getIdBanco() {
        return idBanco;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }
    
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

    public ArrayList<Produto> getItens() {
        return itens;
    }
    
    public void setItens(Produto item, String qtd) {
        ComandaDAO comandaDao = new ComandaDAO();
        this.itens.add(item);
        this.qnt.add(qtd);
        Integer qtdInt = Integer.parseInt(qtd);
        Double total = item.getPreco()*qtdInt;
        atualizarValor(total);
        comandaDao.update(this);
    }

    public ArrayList<Double> getPratos() {
        return pratos;
    }

    public void setPratos(Double prato) {
        ComandaDAO comandaDao = new ComandaDAO();
        this.pratos.add(prato);
        atualizarValor(prato);
        comandaDao.update(this);
    }
    
    public ArrayList<String> getQnt() {
        return qnt;
    }

    public void setQnt(String qnt) {
        this.qnt.add(qnt);
    } 
    
    public void atualizarValor(Double novoValor){
        this.valor += novoValor;
    }
    
    public void setPratosBanco(Double prato) {
        this.pratos.add(prato);
    }
    
    public void setItensBanco(Produto item, String qtd) {
        this.itens.add(item);
        this.qnt.add(qtd);
    }
    
    public void setListaItens(ArrayList<Produto> lista){
        this.itens = lista;
    }
    
    public void setListaQtd(ArrayList<String> lista){
        this.qnt = lista;
    }
    
    public void setListaPratos(ArrayList<Double> lista){
        this.pratos = lista;
    }
    
    public Comanda clonarComanda(Comanda c) {
        c.setId(this.id);
        c.setIdBanco(this.idBanco);
        c.setListaItens(this.itens);
        c.setListaPratos(this.pratos);
        c.setListaQtd(this.qnt);
        c.setStatus(this.status);
        c.setValor(this.valor);
        return c;
    }
}
