package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Cliente;
import model.bean.Estado;

public class ClienteDAO {
    EstadoDAO eDao = new EstadoDAO();
    
    public void create(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO cliente (nome, cpf, sexo, dataNasc, telefone, celular, email, logradouro, bairro, "+
                         "cidade, numero, complemento, cep, idEstado, saldo, saldoPendente) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getSexo());
            stmt.setString(4, c.getDataNasc());
            stmt.setString(5, c.getTelefone());
            stmt.setString(6, c.getCelular());
            stmt.setString(7, c.getEmail());  
            stmt.setString(8, c.getLogradouro());
            stmt.setString(9, c.getBairro());
            stmt.setString(10, c.getCidade());
            stmt.setInt(11, c.getNumero());
            stmt.setString(12, c.getComplemento());
            stmt.setString(13, c.getCep());
            stmt.setInt(14, c.getEstado().getId());
            stmt.setDouble(15, 0.0);
            stmt.setDouble(16, 0.0);
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Cliente> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();
            while (rs.next()){
                Cliente c = new Cliente();               
                
                c.setId(rs.getInt("idCliente"));
                c.setNome(rs.getString("nome")); 
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setSexo(rs.getString("sexo"));
                c.setTelefone(rs.getString("telefone"));
                c.setCelular(rs.getString("celular"));
                c.setLogradouro(rs.getString("logradouro"));
                c.setBairro(rs.getString("bairro"));
                c.setNumero(rs.getInt("numero"));
                c.setCidade(rs.getString("cidade"));
                c.setCep(rs.getString("cep"));
                c.setComplemento(rs.getString("complemento"));
                c.setDataNasc(rs.getString("dataNasc"));
                c.setSaldo(rs.getDouble("saldo"));
                c.setSaldoPendente(rs.getDouble("saldoPendente"));
                
                for (Estado e:eDao.read()){
                    if (e.getId() == rs.getInt("idEstado")){
                        c.setEstado(e);
                    }
                }               

                clientes.add(c);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }

    public Cliente readForId(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente c = new Cliente();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE idCliente = "+id);
            rs = stmt.executeQuery();
            while (rs.next()){
                               
                
                c.setId(rs.getInt("idCliente"));
                c.setNome(rs.getString("nome")); 
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setSexo(rs.getString("sexo"));
                c.setTelefone(rs.getString("telefone"));
                c.setCelular(rs.getString("celular"));
                c.setLogradouro(rs.getString("logradouro"));
                c.setBairro(rs.getString("bairro"));
                c.setNumero(rs.getInt("numero"));
                c.setCidade(rs.getString("cidade"));
                c.setCep(rs.getString("cep"));
                c.setComplemento(rs.getString("complemento"));
                c.setDataNasc(rs.getString("dataNasc"));
                c.setSaldo(rs.getDouble("saldo"));
                c.setSaldoPendente(rs.getDouble("saldoPendente"));
                for (Estado e:eDao.read()){
                    if (e.getId() == rs.getInt("idEstado")){
                        c.setEstado(e);
                    }
                }          
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return c;
    }
    
    public Cliente readForCpfExato(String cpf){
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
                c.setSaldoPendente(rs.getDouble("saldoPendente"));
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return c;
    }
    
    public ArrayList<Cliente> readForCpf(String cpf){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;       
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE cpf LIKE '"+cpf+"%'");
            rs = stmt.executeQuery();
            while (rs.next()){   
                Cliente c = new Cliente();
                c.setId(rs.getInt("idCliente"));
                c.setNome(rs.getString("nome")); 
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setSexo(rs.getString("sexo"));
                c.setTelefone(rs.getString("telefone"));
                c.setCelular(rs.getString("celular"));
                c.setLogradouro(rs.getString("logradouro"));
                c.setBairro(rs.getString("bairro"));
                c.setNumero(rs.getInt("numero"));
                c.setCidade(rs.getString("cidade"));
                c.setCep(rs.getString("cep"));
                c.setComplemento(rs.getString("complemento"));
                c.setDataNasc(rs.getString("dataNasc"));
                c.setSaldo(rs.getDouble("saldo"));
                c.setSaldoPendente(rs.getDouble("saldoPendente"));
                clientes.add(c);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
    
    public ArrayList<Cliente> readForSaldo(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;       
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE saldo > 0");
            rs = stmt.executeQuery();
            while (rs.next()){   
                Cliente c = new Cliente();
                c.setId(rs.getInt("idCliente"));
                c.setNome(rs.getString("nome")); 
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setSexo(rs.getString("sexo"));
                c.setTelefone(rs.getString("telefone"));
                c.setCelular(rs.getString("celular"));
                c.setLogradouro(rs.getString("logradouro"));
                c.setBairro(rs.getString("bairro"));
                c.setNumero(rs.getInt("numero"));
                c.setCidade(rs.getString("cidade"));
                c.setCep(rs.getString("cep"));
                c.setComplemento(rs.getString("complemento"));
                c.setDataNasc(rs.getString("dataNasc"));
                c.setSaldo(rs.getDouble("saldo"));
                c.setSaldoPendente(rs.getDouble("saldoPendente"));
                clientes.add(c);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
    
    public void updateSaldo(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE cliente SET saldo=?, saldoPendente=? WHERE idCliente = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, c.getSaldo());
            stmt.setDouble(2, c.getSaldoPendente());
            stmt.setInt(3, c.getId());
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updatePendente(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE cliente SET saldoPendente = ? WHERE idCliente = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, c.getSaldoPendente());
            stmt.setInt(2, c.getId());
            
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
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
                    + "cidade=?, numero=?, complemento=?, cep=?, idEstado=?, saldoPendente=? WHERE idCliente = ?";
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
            stmt.setDouble(16, c.getSaldoPendente());
            stmt.setInt(17, c.getId());
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
