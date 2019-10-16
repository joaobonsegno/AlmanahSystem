package model.bean;

import java.util.ArrayList;
import model.dao.ComandaDAO;
import model.dao.FormaDAO;
import model.dao.ItemComandaDAO;
import model.dao.ProdutoDAO;
import model.dao.PromocaoUmDAO;

public class Comanda {

    private Integer id, idBanco, status;
    private Double valor, valorPendente;
    private ArrayList<Produto> itens = new ArrayList<>();
    private ArrayList<String> qnt = new ArrayList<>();
    private ArrayList<Double> pratos = new ArrayList<>();
    private ArrayList<Forma> formasDePagamento = new ArrayList<>();
    private FormaDAO fDao;
    private ItemComandaDAO itemDao;
    private ComandaDAO cDao;
    private Cliente cliente;

    public Comanda(Integer id) {
        this.id = id;
        this.valor = 0.00;
        this.valorPendente = 0.00;
        this.status = 1;
    }

    public Comanda() {
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public boolean setItensComVerificacao(Produto item, String qtd) {
        PromocaoUmDAO promoUmDao = new PromocaoUmDAO();
        PromocaoUm promoUm = promoUmDao.read();
        ComandaDAO comandaDao = new ComandaDAO();
        boolean flagExiste = false;
        int contador = 0;
        for (Produto p : this.itens) {
            if (p.getIdProduto() == item.getIdProduto()) {
                flagExiste = true;
                break;
            }
            contador += 1;
        }
        Double total;
        Integer qtdInt = Integer.parseInt(qtd);
        
        if (flagExiste) {
            Integer quantidade = Integer.parseInt(this.qnt.get(contador));
            quantidade += Integer.parseInt(qtd);
            this.qnt.remove(contador);
            this.itens.remove(contador);
            this.qnt.add(Integer.toString(quantidade));
            this.itens.add(item);
            
            // Soma ao total da comanda unificada somente a multiplicação da quantidade de itens que já havia na comanda velha
            if (promoUm.getStatus() == 1) {
                if (item.getCategoria().getNome().equals("Suco")) {
                    total = item.getPrecoComDesconto() * qtdInt;
                } else {
                    total = item.getPreco() * qtdInt;
                }
            } else {
                total = item.getPreco() * qtdInt;
            }
            
            
        } else {
            this.itens.add(item);
            this.qnt.add(qtd);

            if (promoUm.getStatus() == 1) {
                if (item.getCategoria().getNome().equals("Suco")) {
                    total = item.getPrecoComDesconto() * qtdInt;
                } else {
                    total = item.getPreco() * qtdInt;
                }
            } else {
                total = item.getPreco() * qtdInt;
            }
        }
        atualizarValor(total);
        comandaDao.update(this);
        return flagExiste;
    }

    public void updateForma(Forma f) {
        formasDePagamento.get(formasDePagamento.size() - 1).setId(f.getId());
    }

    public void setForma(Forma f) {
        this.valorPendente -= f.getValor();
        formasDePagamento.add(f);
        ComandaDAO cDao = new ComandaDAO();
        cDao.updatePendente(this);
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
        PromocaoUmDAO promoUmDao = new PromocaoUmDAO();
        PromocaoUm promoUm = promoUmDao.read();
        ComandaDAO comandaDao = new ComandaDAO();
        this.itens.add(item);
        this.qnt.add(qtd);
        Integer qtdInt = Integer.parseInt(qtd);
        Double total;

        if (promoUm.getStatus() == 1) {
            if (item.getCategoria().getNome().equals("Suco")) {
                total = item.getPrecoComDesconto() * qtdInt;
            } else {
                total = item.getPreco() * qtdInt;
            }
        } else {
            total = item.getPreco() * qtdInt;
        }
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

    public void atualizarValor(Double novoValor) {
        this.valor += novoValor;
        this.valorPendente += novoValor;
    }

    public void setPratosBanco(Double prato) {
        this.pratos.add(prato);
    }

    public void setItensBanco(Produto item, String qtd) {
        this.itens.add(item);
        this.qnt.add(qtd);
    }

    public void setListaItens(ArrayList<Produto> lista) {
        this.itens = lista;
    }

    public void setListaQtd(ArrayList<String> lista) {
        this.qnt = lista;
    }

    public void setListaPratos(ArrayList<Double> lista) {
        this.pratos = lista;
    }

    public void removerProduto(int i){
        cDao = new ComandaDAO();
        Produto p = itens.get(i);
        int quant = Integer.parseInt(qnt.get(i));
        Double totalLinha = p.getPreco()*quant;
        valor -= totalLinha;
        valorPendente -= totalLinha;
        itens.remove(i);
        qnt.remove(i);
        cDao.update(this);
    }
    
    public String getQntEspecifica(int i) {
        return qnt.get(i);
    }

    public void removerPrato(int i) {
        cDao = new ComandaDAO();
        itemDao = new ItemComandaDAO();
        Double d = pratos.get(i);
        pratos.remove(i);
        valor -= d;
        valorPendente -= d;
        itemDao.deletePrato(this, d);
        cDao.update(this);
    }

    public void removerItem(int i) {
        cDao = new ComandaDAO();
        itemDao = new ItemComandaDAO();
        ProdutoDAO pDao = new ProdutoDAO();

        Produto prod = itens.get(i);
        Integer q = Integer.parseInt(qnt.get(i));
        if (!prod.getQtdEstoque().equals("X")) {
            Integer qtdProdEstoque = Integer.parseInt(prod.getQtdEstoque());
            qtdProdEstoque += q;
            prod.setQtdEstoque(Integer.toString(qtdProdEstoque));
        }

        Double v = prod.getPrecoComDesconto() * q;
        valor -= v;
        valorPendente -= v;
        itens.remove(i);
        qnt.remove(i);
        itemDao.deleteProduto(this, prod.getIdProduto());
        pDao.updateEstoque(prod);
        cDao.update(this);
    }

    public void removerItemSemAddEstoque(int i) {
        cDao = new ComandaDAO();
        itemDao = new ItemComandaDAO();
        Integer q = Integer.parseInt(qnt.get(i));
        Double v = itens.get(i).getPrecoComDesconto() * q;
        valor -= v;
        valorPendente -= v;
        itemDao.deleteProduto(this, itens.get(i).getIdProduto());
        itens.remove(i);
        qnt.remove(i);        
        cDao.update(this);
    }
    
    public void removerForma(int indice) {
        formasDePagamento.remove(indice);
    }

    public Comanda clonarComanda(Comanda c) {
        c.setId(this.id);
        c.setIdBanco(this.idBanco);
        c.setListaItens(this.itens);
        c.setListaPratos(this.pratos);
        c.setListaQtd(this.qnt);
        c.setFormasDePagamento(this.formasDePagamento);
        c.setStatus(this.status);
        c.setValor(this.valor);
        c.setValorPendente(this.valorPendente);
        return c;
    }

    /*public void reduzirValorPendente(){
        this.valorPendente = this.valor;
        for (Forma f:formasDePagamento){
            this.valorPendente -= f.getValor();
        }
        ComandaDAO cDao = new ComandaDAO();
        cDao.updatePendente(this);
    }*/
    public void aumentarValorPendente(Double v) {
        this.valorPendente += v;

        ComandaDAO cDao = new ComandaDAO();
        cDao.updatePendente(this);
    }

    public ArrayList<Forma> getFormasDePagamento() {
        return formasDePagamento;
    }

    public void setFormasDePagamento(ArrayList<Forma> formasDePagamento) {
        this.formasDePagamento = formasDePagamento;
    }

    public Double getValorPendente() {
        return valorPendente;
    }

    public void setValorPendente(Double valorPendente) {
        this.valorPendente = valorPendente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
