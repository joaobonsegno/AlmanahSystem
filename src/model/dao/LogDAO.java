package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import main.Login;
import model.bean.Categoria;
import model.bean.Log;
import model.bean.Produto;

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
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
     public ArrayList<Log> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Log> logs = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM log");
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
     
     public ArrayList<Log> readForNome(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Log> logs = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM log WHERE descricao LIKE ?");
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
}
