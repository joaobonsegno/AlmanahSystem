package model.bean;

public class Produto {
    Integer idBanco, idSistema;
    String nome, ncm, ean, descricao, validade, qtdMinima, qtdEstoque;
    Double preco;
    Categoria categoria;
    
    public Integer getIdBanco() {
        return idBanco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Integer getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Integer id) {
        this.idSistema = id;
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

    public void setValidade(String validade) {
        this.validade = validade;
    }
}
