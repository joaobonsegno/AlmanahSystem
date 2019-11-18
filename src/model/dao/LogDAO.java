package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Log;

public class LogDAO {
    public void create(Log l){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO log (descricao, categoria, data)VALUES(?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, l.getDescricao());
            stmt.setString(2, l.getCategoria());
            stmt.setString(3, l.getData());

            stmt.executeUpdate();
            //System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
     public ArrayList<Log> read(String data){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Log> logs = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM log WHERE str_to_date(data, '%d/%m/%Y') = str_to_date('"+data+"','%d/%m/%Y') ";
            //System.out.println("SQL: "+sql);
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                Log l = new Log();
                
                l.setId(rs.getInt("idLog"));
                l.setDescricao(rs.getString("descricao"));
                l.setCategoria(rs.getString("categoria"));
                l.setData(rs.getString("data"));

                logs.add(l);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return logs;
    }
     
     public ArrayList<Log> readForNome(String nome, String data){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Log> logs = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM log WHERE descricao LIKE ? AND str_to_date(data, '%d/%m/%Y') = str_to_date('"+data+"','%d/%m/%Y') ");
            stmt.setString(1, "%"+nome+"%");
            
            rs = stmt.executeQuery();
            while (rs.next()){
                Log l = new Log();
                
                l.setId(rs.getInt("idLog"));
                l.setDescricao(rs.getString("descricao"));
                l.setCategoria(rs.getString("categoria"));
                l.setData(rs.getString("data"));

                logs.add(l);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return logs;
    }
     
     public Log readLast(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Log l = new Log();
        try{
            stmt = con.prepareStatement("SELECT * FROM log ORDER BY idLog DESC LIMIT 1");           
            rs = stmt.executeQuery();
            while (rs.next()){         
                l.setId(rs.getInt("idLog"));
                l.setDescricao(rs.getString("descricao"));
                l.setCategoria(rs.getString("categoria"));
                l.setData(rs.getString("data"));
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);           
        }  
        return l;
     }    
}
