package model.bean;

import java.util.ArrayList;

public class Produto implements Comparable<Produto>{
    private Integer idProduto;
    private String nome, ncm, ean, descricao, validade, qtdMinima, qtdEstoque, unidadeDeMedida;
    private Double preco, precoComDesconto;
    private Categoria categoria;
    private ArrayList<Produto> materiasPrimas = new ArrayList<>();
    
    @Override
    public int compareTo(Produto outro){
        int compareInt = this.nome.compareTo(outro.getNome());
        if (compareInt < 0) return -1; //this.nome é maior 
        if (compareInt > 0) return 1; //outro.nome é maior
        return 0; //os dois são iguais
    }
    
    public Integer getIdProduto() {
        return idProduto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(String quantidadeMinima) {
        this.qtdMinima = quantidadeMinima;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(String qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getValidade() {
        return validade;
    }

    public String getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(String unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public Double getPrecoComDesconto() {
        return precoComDesconto;
    }

    public void setPrecoComDesconto(Double precoComPromocao) {
        this.precoComDesconto = precoComPromocao;
    }
    
    public void setValidade(String validade) {
        this.validade = validade;
    }
}
