package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Estado;

public class EstadoDAO {
    public ArrayList<Estado> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Estado> estados = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM estado");
            rs = stmt.executeQuery();
            while (rs.next()){
                Estado e = new Estado();
                
                e.setId(rs.getInt("idEstado"));
                e.setNome(rs.getString("nome"));
                
                estados.add(e);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return estados;
    }
    
    public Estado readForUf(String uf){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Estado e = new Estado();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM estado WHERE nome LIKE '"+uf+"'");
            rs = stmt.executeQuery();
            while (rs.next()){
                e.setId(rs.getInt("idEstado"));
                e.setNome(rs.getString("nome"));
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return e;
    }
    
    public Estado readForId(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Estado e = new Estado();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM estado WHERE idEstado = "+id);
            rs = stmt.executeQuery();
            while (rs.next()){
                
                e.setId(rs.getInt("idEstado"));
                e.setNome(rs.getString("nome"));

            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return e;
    }
}
