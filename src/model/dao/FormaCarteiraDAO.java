package model.dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Cliente;
import model.bean.FormaCarteira;

public class FormaCarteiraDAO {   
    public void create(FormaCarteira f, Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO formaPagamentoCarteira (valor, formaPagamento, idCliente) VALUES(?,?,?)";
            stmt = con.prepareStatement(sql);
            
            stmt.setDouble(1, f.getValor());  
            stmt.setString(2, f.getFormaPagamento());  
            stmt.setInt(3, c.getId());
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<FormaCarteira> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<FormaCarteira> formas = new ArrayList<>();
        ClienteDAO cDao = new ClienteDAO();   
        
        try{
            stmt = con.prepareStatement("SELECT * FROM formaPagamentoCarteira");
            rs = stmt.executeQuery();
            while (rs.next()){
                FormaCarteira f = new FormaCarteira();
                
                f.setValor(rs.getDouble("valor"));
                f.setFormaPagamento(rs.getString("formaPagamento"));
                //Integer idVenda = (rs.getInt("idVenda"));
                Integer idCliente = (rs.getInt("idCliente"));
                
                for (Cliente c:cDao.read()){
                    if (c.getId() == idCliente){
                        f.setCliente(c);
                        break;
                    }
                }               
                formas.add(f);               
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return formas;
    } 
    
    public void delete(FormaCarteira f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM formaPagamentoCarteira WHERE idFormaPagamentoCarteira = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, f.getId());
            
            stmt.executeUpdate();
            //System.out.println("Excluído com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Integer id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM formaPagamentoCarteira WHERE idFormaPagamentoCarteira = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            //System.out.println("Excluído com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<FormaCarteira> readForCliente(Cliente cliente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<FormaCarteira> formas = new ArrayList<>();  
        
        try{
            stmt = con.prepareStatement("SELECT * FROM formaPagamentoCarteira WHERE idCliente = ?");
            stmt.setInt(1, cliente.getId());
            rs = stmt.executeQuery();
            while (rs.next()){     
                FormaCarteira forma = new FormaCarteira();
                
                forma.setId(rs.getInt("idFormaPagamentoCarteira"));
                forma.setValor(rs.getDouble("valor"));
                forma.setFormaPagamento(rs.getString("formaPagamento"));
                forma.setCliente(cliente);
                
                formas.add(forma);
            }         
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return formas;
    }
    
    public FormaCarteira readLast(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null; 
        FormaCarteira forma = new FormaCarteira();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM formaPagamentoCarteira ORDER BY idFormaPagamentoCarteira DESC LIMIT 1");
            rs = stmt.executeQuery();
            while (rs.next()){                     
                forma.setId(rs.getInt("idFormaPagamentoCarteira"));
                forma.setValor(rs.getDouble("valor"));
                forma.setFormaPagamento(rs.getString("formaPagamento"));
            }         
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return forma;
    }
    
    public void updateVenda(FormaCarteira f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        Integer var = null;
        try{
            String sql = "UPDATE formaPagamentoCarteira SET idVenda = ?, idCliente = ? WHERE idFormaPagamentoCarteira = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, f.getVenda().getId());
            stmt.setNull(2, var == null ? 0 : var);
            stmt.setInt(3, f.getId());
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /*public void update(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE produto SET nome=?, ncm=?, ean=?, descricao=?, qtdMinima=?, preco=?, validade=?, qtdEstoque=?, idCategoria=?, unidadeDeMedida=? WHERE idProduto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getNcm());
            stmt.setString(3, p.getEan());
            stmt.setString(4, p.getDescricao());
            stmt.setString(5, p.getQtdMinima());
            stmt.setDouble(6, p.getPreco());  
            stmt.setString(7, p.getValidade());
            stmt.setString(8, p.getQtdEstoque());
            stmt.setInt(9, p.getCategoria().getId());
            stmt.setString(10, p.getUnidadeDeMedida());
            stmt.setInt(11, p.getIdProduto());
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    
    public void updatePromocao(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE produto SET precoComDesconto=? WHERE idProduto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, p.getPrecoComDesconto());
            stmt.setInt(2, p.getIdProduto());
            
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }*/
    
    
}
