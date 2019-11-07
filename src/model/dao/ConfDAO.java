package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfDAO {
    public int readCod(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        try{
            stmt = con.prepareStatement("SELECT * FROM confCod WHERE id = 1");
            rs = stmt.executeQuery();
            while (rs.next()){
                return rs.getInt("status");
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return 2;
    }
    
    public Double readPreco(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        try{
            stmt = con.prepareStatement("SELECT * FROM confCod WHERE id = 2 AND status = 1");
            rs = stmt.executeQuery();
            while (rs.next()){
                return rs.getDouble("preco");
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return 0.0;
    }
    
    public void updateCod(int status){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE confCod SET status=? WHERE id = 1";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, status);
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updatePreco(int status, Double preco){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE confCod SET status=?, preco=? WHERE id = 2";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, status);
            stmt.setDouble(2, preco);
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updatePreco(int status){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE confCod SET status=? WHERE id = 2";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, status);
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
