package model.bean;

import java.util.ArrayList;

public class Prato implements Comparable<Prato>{
    private String nome, descricao;
    private int id, status;
    private CategoriaPrato categoria;
    private ArrayList<Produto> subprodutos = new ArrayList<>();

    @Override
    public int compareTo(Prato outro){
        int compareInt = this.nome.compareTo(outro.getNome());
        if (compareInt < 0) return -1; //this.nome é maior 
        if (compareInt > 0) return 1; //outro.nome é maior
        return 0; //os dois são iguais
    }
    
    public Prato(){}
    
    public void limparSubprodutos(){
        this.subprodutos.removeAll(this.subprodutos);
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public CategoriaPrato getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaPrato categoria) {
        this.categoria = categoria;
    }
}
