package model.bean;

import java.util.ArrayList;
import model.dao.FornecedorDAO;

public class Fornecedor {
    private int id, status, numero;
    private String nome, telefone, celular, cnpj, email, logradouro, bairro, cidade, cep, complemento;
    private Estado estado;
    private ArrayList<Produto> produtosFornecidos;

    public Fornecedor(){
        produtosFornecidos = new ArrayList<>();
    }
    
    public void setProdutoSalvando(Produto p){
        FornecedorDAO fDao = new FornecedorDAO();
        produtosFornecidos.add(p);
        fDao.addItem(this, p);
    }
    
    public void setProduto(Produto p){
        produtosFornecidos.add(p);
    }
    
    public void removerProduto(int indice){
        FornecedorDAO fDao = new FornecedorDAO();
        Produto p = produtosFornecidos.get(indice);
        produtosFornecidos.remove(indice);
        fDao.deleteItem(p);
    }
    
    public Produto getProduto(int i){
        return produtosFornecidos.get(i);
    }
    
    public boolean possuiProduto(Produto prod){
        for (Produto p : produtosFornecidos){
            if (p.getIdProduto() == prod.getIdProduto()){
                //System.out.println("RETORNANDO TRUE");
                return true;
            }
        }
        //System.out.println("RETORNANDO FALSE");
        return false;
    }
    
    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS PADRÃO">  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setProdutosFornecidos(ArrayList<Produto> produtosFornecidos) {
        this.produtosFornecidos = produtosFornecidos;
    }
    
    public ArrayList<Produto> getProdutosFornecidos() {
        return produtosFornecidos;
    }
    // </editor-fold> 

}
