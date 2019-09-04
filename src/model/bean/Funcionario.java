package model.bean;

public class Funcionario implements Comparable<Funcionario>{
    Integer idFuncionario, numero;
    String nome, cpf, dataNasc, telefone, celular, sexo, usuario;
    String senha, logradouro, bairro, cidade, email, complemento, cep;
    Double salario;
    Cargo cargo;
    Estado estado;
    
    @Override
    public int compareTo(Funcionario outro){
        int compareInt = this.nome.compareTo(outro.getNome());
        if (compareInt < 0) return -1; //this.nome é maior 
        if (compareInt > 0) return 1; //outro.nome é maior
        return 0; //os dois são iguais
    }
    
    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Cargo getCargo() {
        return this.cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    public Funcionario clonarFuncionario(Funcionario funcAtual) {
        funcAtual.setIdFuncionario(this.getIdFuncionario());
        funcAtual.setSenha(this.getSenha());
        funcAtual.setUsuario(this.getUsuario());
        funcAtual.setNome(this.getNome());
        funcAtual.setCpf(this.getCpf());
        funcAtual.setEmail(this.getEmail());
        funcAtual.setSexo(this.getSexo());
        funcAtual.setSalario(this.getSalario());
        funcAtual.setCargo(this.getCargo());
        funcAtual.setTelefone(this.getTelefone());
        funcAtual.setCelular(this.getCelular());
        funcAtual.setLogradouro(this.getLogradouro());
        funcAtual.setBairro(this.getBairro());
        funcAtual.setNumero(this.getNumero());
        funcAtual.setCidade(this.getCidade());
        funcAtual.setCep(this.getCep());
        funcAtual.setComplemento(this.getComplemento());
        funcAtual.setEstado(this.getEstado());
 
        return funcAtual;
    }
}

