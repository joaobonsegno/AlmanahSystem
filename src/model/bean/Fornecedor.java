package model.bean;

import java.util.ArrayList;

public class Fornecedor {
    private int id, status;
    private String nome, descricao, telefone, celular, cnpj, email;
    private ArrayList<Produto> produtosFornecidos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public ArrayList<Produto> getProdutosFornecidos() {
        return produtosFornecidos;
    }

    public void setProdutosFornecidos(ArrayList<Produto> produtosFornecidos) {
        this.produtosFornecidos = produtosFornecidos;
    }
    
    public void setProdutoFornecido(Produto p){
        this.produtosFornecidos.add(p);
    }
    
    public void removerProdutoFornecido(int indice){
        this.produtosFornecidos.remove(indice);
    }
}
