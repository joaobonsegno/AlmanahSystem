package model.bean;

import java.util.ArrayList;

public class Prato {
    private String nome, descricao;
    private int id;
    private ArrayList<Produto> subprodutos = new ArrayList<>();

    public Prato() {
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   

    public ArrayList<Produto> getSubprodutos() {
        return subprodutos;
    }

    public void setSubprodutos(ArrayList<Produto> subprodutos) {
        this.subprodutos = subprodutos;
    }
    
    public void setSubproduto(Produto produto){
        this.subprodutos.add(produto);
    }
}
