package model.dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Cliente;
import model.bean.Comanda;
import model.bean.Forma;

public class FormaDAO {
    public void create(Forma f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO formaPagamento (valor, formaPagamento, idComanda)VALUES(?,?,?)";
            stmt = con.prepareStatement(sql);
            
            stmt.setDouble(1, f.getValor());  
            stmt.setString(2, f.getFormaPagamento());
            stmt.setInt(3, f.getComanda().getIdBanco());  
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void create(Forma f, Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO formaPagamento (valor, formaPagamento, idComanda, idCliente)VALUES(?,?,?,?)";
            stmt = con.prepareStatement(sql);
            
            stmt.setDouble(1, f.getValor());  
            stmt.setString(2, f.getFormaPagamento());
            stmt.setInt(3, f.getComanda().getIdBanco());  
            stmt.setInt(4, c.getId());
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Forma> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Forma> formas = new ArrayList<>();
        //VendaDAO vDao = new VendaDAO();
        ComandaDAO cDao = new ComandaDAO();   
        ClienteDAO cliDao = new ClienteDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM formaPagamento");
            rs = stmt.executeQuery();
            while (rs.next()){
                Forma f = new Forma();
                
                f.setValor(rs.getDouble("valor"));
                f.setFormaPagamento(rs.getString("formaPagamento"));
                Integer idCliente = (rs.getInt("idCliente"));
                Integer idComanda = (rs.getInt("idComanda"));
                
                for (Comanda c:cDao.read()){
                    if (c.getId() == idComanda){
                        f.setComanda(c);
                        break;
                    }
                }
                
                for (Cliente c:cliDao.read()){
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
    
    public void delete(Forma f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM formaPagamento WHERE idFormaPagamento = ?";
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
    
    public ArrayList<Forma> readForComanda(Comanda comanda){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Forma> formas = new ArrayList<>();  
        
        try{
            stmt = con.prepareStatement("SELECT * FROM formaPagamento WHERE idComanda LIKE ?");
            stmt.setInt(1, comanda.getIdBanco());
            rs = stmt.executeQuery();
            while (rs.next()){     
                Forma forma = new Forma();
                forma.setId(rs.getInt("idFormaPagamento"));
                forma.setValor(rs.getDouble("valor"));
                forma.setFormaPagamento(rs.getString("formaPagamento"));
                forma.setComanda(comanda);
                
                ClienteDAO cDao = new ClienteDAO();
                forma.setCliente(cDao.readForId(rs.getInt("idCliente")));
                formas.add(forma);
            }         
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return formas;
    }
    
    public ArrayList<Forma> readForCliente(Cliente cliente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Forma> formas = new ArrayList<>();  
        
        try{
            stmt = con.prepareStatement("SELECT * FROM formaPagamento WHERE idCliente = ?");
            stmt.setInt(1, cliente.getId());
            rs = stmt.executeQuery();
            while (rs.next()){     
                Forma forma = new Forma();
                forma.setId(rs.getInt("idFormaPagamento"));
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
    
    public Forma readLast(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null; 
        Forma forma = new Forma();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM formaPagamento ORDER BY idFormaPagamento DESC LIMIT 1");
            rs = stmt.executeQuery();
            while (rs.next()){                     
                forma.setId(rs.getInt("idFormaPagamento"));
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
    
    public void updateVenda(Forma f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        Integer var = null;
        try{
            String sql = "UPDATE formaPagamento SET idVenda = ?, idComanda=? WHERE idFormaPagamento = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, f.getVenda().getIdBanco());
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
    
    public void updateComanda(Forma f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        Integer var = null;
        try{
            String sql = "UPDATE formaPagamento SET idComanda = ? WHERE idFormaPagamento = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, f.getComanda().getIdBanco());
            stmt.setInt(2, f.getId());
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
