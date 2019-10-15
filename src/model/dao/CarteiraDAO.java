package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Carteira;
import model.bean.Cliente;
import model.bean.Forma;
import model.bean.Funcionario;

public class CarteiraDAO {
    FuncionarioDAO fDao = new FuncionarioDAO();
    
    public void create(Carteira c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO carteira (valor, data, idFuncionario, idCliente) VALUES(?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, c.getValor());
            stmt.setString(2, c.getData());
            stmt.setInt(3, c.getFuncionario().getIdFuncionario());
            stmt.setInt(4, c.getCliente().getId());
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro SQL (CREATE do CarteiraDAO): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Carteira> readForCliente(Cliente cliente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Carteira> carteiras = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM carteira WHERE idCliente = "+cliente.getId());
            rs = stmt.executeQuery();
            while (rs.next()){
                Carteira c = new Carteira();               
                
                c.setId(rs.getInt("idCarteira"));
                c.setValor(rs.getDouble("valor"));
                c.setData(rs.getString("data"));
                c.setCliente(cliente);
                
                for (Funcionario f : fDao.read()){
                    if (f.getIdFuncionario() == rs.getInt("idFuncionario")){
                        c.setFuncionario(f);
                    }
                }           

                carteiras.add(c);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return carteiras;
    }

    public Carteira readLast(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Carteira c = new Carteira();
        try{
            stmt = con.prepareStatement("SELECT * FROM carteira ORDER BY idCarteira DESC LIMIT 1");           
            rs = stmt.executeQuery();
            while (rs.next()){         
                c.setId(rs.getInt("idCarteira"));
                c.setValor(rs.getDouble("valor"));
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);           
        }  
        return c;
     }
    
    public void updateForma(Carteira c,Forma f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            System.out.println("ID Forma: "+f.getId());
            System.out.println("ID Carteira: "+c.getId());
            String sql = "UPDATE carteira SET idFormaPagamento = ? WHERE idCarteira = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, f.getId());
            stmt.setInt(2, c.getId());
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Forma f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM carteira WHERE idFormaPagamento = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, f.getId());
            
            stmt.executeUpdate();
            System.out.println("Excluído com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /*public Cliente readForCpf(String cpf){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente c = new Cliente();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE cpf LIKE '"+cpf+"'");
            rs = stmt.executeQuery();
            while (rs.next()){               
                c.setId(rs.getInt("idCliente"));
                c.setNome(rs.getString("nome")); 
                c.setCpf(rs.getString("cpf")); 
                c.setSaldo(rs.getDouble("saldo"));
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return c;
    }
    
    public void updateSaldo(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE cliente SET saldo=? WHERE idCliente = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, c.getSaldo());
            stmt.setInt(2, c.getId());
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void update(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE cliente SET nome=?, cpf=?, sexo=?, email=?, saldo=?, dataNasc=?, telefone=?, celular=?, logradouro=?, bairro=?, "
                    + "cidade=?, numero=?, complemento=?, cep=?, idEstado=? WHERE idCliente = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getSexo());
            stmt.setString(4, c.getEmail());
            stmt.setDouble(5, c.getSaldo());
            stmt.setString(6, c.getDataNasc());             
            stmt.setString(7, c.getTelefone());
            stmt.setString(8, c.getCelular());           
            stmt.setString(9, c.getLogradouro());
            stmt.setString(10, c.getBairro());
            stmt.setString(11, c.getCidade());
            stmt.setInt(12, c.getNumero());
            stmt.setString(13, c.getComplemento());
            stmt.setString(14, c.getCep());
            stmt.setInt(15, c.getEstado().getId());
            stmt.setInt(16, c.getId());
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public Iterable<Cliente> readForNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
