package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.Cardapio;
import model.bean.Prato;

public class ItemCardapioDAO {
    Integer idItem = null;
    public void create(Cardapio c, Prato p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO itemCardapio (idCardapio, idPrato)VALUES(?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            stmt.setInt(2, p.getId());           
            
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL (ItemCardapioDAO): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void read(Cardapio c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PratoDAO pratoDao = new PratoDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM itemCardapio");
            rs = stmt.executeQuery();
            while (rs.next()){
                Integer idCar = rs.getInt("idCardapio");
                if(idCar == c.getId()){
                    int idPrato = rs.getInt("idPrato");
                    for(Prato p:pratoDao.read()){
                        if(p.getId() == idPrato){
                            c.setPrato(p);
                        }
                    }
                }       
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL (ItemCardapioDAO): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public void readForCardapio(Cardapio c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PratoDAO pratoDao = new PratoDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM itemCardapio WHERE idCardapio = "+c.getId());
            rs = stmt.executeQuery();
            while (rs.next()){
                Integer idCar = rs.getInt("idCardapio");
                if(idCar == c.getId()){
                    int idPrato = rs.getInt("idPrato");
                    for(Prato p:pratoDao.read()){
                        if(p.getId() == idPrato){
                            c.setPrato(p);
                        }
                    }
                }       
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL (ItemCardapioDAO): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public void deleteItemCardapio(Cardapio c, Integer id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM itemCardapio WHERE idCardapio = "+c.getId();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                if(rs.getInt("idItemCardapio") == id){
                    idItem = rs.getInt("idItemCardapio");
                    delete(idItem);
                }                
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
                
    public void delete(Integer idItem){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM itemCardapio WHERE idItemCardapio = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idItem);
            
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void update(Cardapio c, Prato p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE itemCardapio SET idCardapio=?, idPrato=? WHERE idItemCardapio = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            stmt.setInt(2, p.getId());

            readItem(c, p.getId());
            stmt.setInt(5, idItem);
            
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar (ItemCardapioDAO): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void readItem(Cardapio c, int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM itemCardapio WHERE idCardapio = "+c.getId();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                int idPrato = rs.getInt("idPrato");
                if (idPrato == id){
                    idItem = rs.getInt("idItemCardapio");
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
