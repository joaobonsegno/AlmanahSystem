package model.bean;

import model.dao.ClienteDAO;

public class Cliente implements Comparable<Cliente>{
    Integer id, numero, status;
    String nome, cpf, dataNasc, telefone, celular, sexo;
    String logradouro, bairro, cidade, email, complemento, cep;
    Double saldo, saldoPendente;
    Estado estado;
    
    
    
    public void aumentarSaldoPendente(Double valor){
        ClienteDAO cDao = new ClienteDAO();
        this.saldoPendente += valor;
        cDao.updatePendente(this);
    }
    
    public void reduzirSaldoPendente(Double valor){
        ClienteDAO cDao = new ClienteDAO();
        this.saldoPendente -= valor;
        cDao.updatePendente(this);
    }
    
    public void reduzirSaldo(Double valor){
        ClienteDAO cDao = new ClienteDAO();
        this.saldo -= valor;
        cDao.updateSaldo(this);
    }
    
    public void aumentarSaldo(Double valor){
        ClienteDAO cDao = new ClienteDAO();
        this.saldo += valor;
        cDao.updateSaldo(this);
    }
    
    // GETTERS E SETTERS PADRÃO
    public Integer getId() {
        return id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSaldoPendente() {
        return saldoPendente;
    }

    public void setSaldoPendente(Double saldoPendente) {
        this.saldoPendente = saldoPendente;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public Cliente clonarCliente(Cliente clienteAtual) {
        clienteAtual.setId(this.getId());
        clienteAtual.setNome(this.getNome());
        clienteAtual.setCpf(this.getCpf());
        clienteAtual.setEmail(this.getEmail());
        clienteAtual.setSexo(this.getSexo());
        clienteAtual.setTelefone(this.getTelefone());
        clienteAtual.setCelular(this.getCelular());
        clienteAtual.setLogradouro(this.getLogradouro());
        clienteAtual.setBairro(this.getBairro());
        clienteAtual.setNumero(this.getNumero());
        clienteAtual.setCidade(this.getCidade());
        clienteAtual.setCep(this.getCep());
        clienteAtual.setComplemento(this.getComplemento());
        clienteAtual.setEstado(this.getEstado());
 
        return clienteAtual;
    }
    
    // Implementa o método do Collections
    @Override
    public int compareTo(Cliente outro){
        int compareInt = this.nome.compareTo(outro.getNome());
        if (compareInt < 0) return -1; //this.nome é maior 
        if (compareInt > 0) return 1; //outro.nome é maior
        return 0; //os dois são iguais
    }
}

