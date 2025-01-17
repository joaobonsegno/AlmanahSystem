package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.LogCaixa;

public class LogCaixaDAO {
    public void create(LogCaixa l){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO logCaixa (descricao, categoria, data, tipo, saldo, status, valor)VALUES(?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, l.getDescricao());
            stmt.setString(2, l.getCategoria());
            stmt.setString(3, l.getData());
            stmt.setString(4, l.getTipo());
            stmt.setDouble(5, l.getSaldo());
            stmt.setInt(6, l.getStatus());
            stmt.setDouble(7, l.getValor());
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
     public ArrayList<LogCaixa> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<LogCaixa> logs = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM logCaixa");
            rs = stmt.executeQuery();
            while (rs.next()){
                LogCaixa l = new LogCaixa();
                
                l.setId(rs.getInt("idLogCaixa"));
                l.setDescricao(rs.getString("descricao"));
                l.setCategoria(rs.getString("categoria"));
                l.setData(rs.getString("data"));
                l.setTipo(rs.getString("tipo"));
                l.setSaldo(rs.getDouble("saldo"));
                l.setStatus(rs.getInt("status"));
                l.setValor(rs.getDouble("valor"));

                logs.add(l);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return logs;
    }
     
     public ArrayList<LogCaixa> readForNome(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<LogCaixa> logs = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM logCaixa WHERE descricao LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            
            rs = stmt.executeQuery();
            while (rs.next()){
                LogCaixa l = new LogCaixa();
                
                l.setId(rs.getInt("idLogCaixa"));
                l.setDescricao(rs.getString("descricao"));
                l.setCategoria(rs.getString("categoria"));
                l.setData(rs.getString("data"));
                l.setTipo(rs.getString("tipo"));
                l.setSaldo(rs.getDouble("saldo"));
                l.setStatus(rs.getInt("status"));
                l.setValor(rs.getDouble("valor"));

                logs.add(l);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return logs;
    }
     
     public LogCaixa readLast(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LogCaixa l = new LogCaixa();
        try{
            stmt = con.prepareStatement("SELECT * FROM logCaixa ORDER BY idLogCaixa DESC LIMIT 1");           
            rs = stmt.executeQuery();
            while (rs.next()){         
                l.setId(rs.getInt("idLogCaixa"));
                l.setDescricao(rs.getString("descricao"));
                l.setCategoria(rs.getString("categoria"));
                l.setData(rs.getString("data"));
                l.setTipo(rs.getString("tipo"));
                l.setSaldo(rs.getDouble("saldo"));
                l.setStatus(rs.getInt("status"));
                l.setValor(rs.getDouble("valor"));
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);           
        }  
        return l;
     }
     
     public void switchStatus(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        LogCaixa l = new LogCaixa();
        try{
            stmt = con.prepareStatement("SELECT * FROM logCaixa WHERE status = 1");           
            rs = stmt.executeQuery();
            while (rs.next()){   
                stmt2 = con.prepareStatement("UPDATE logCaixa SET status=? WHERE idLogCaixa = ?");           
                stmt2.setInt(1, 0);
                stmt2.setInt(2, rs.getInt("idLogCaixa"));
                stmt2.executeUpdate();
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);           
        }  
     }
     
    /*public Log readLast(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Log l = new Log();
        String qtdLogs = null;
        try{
            stmt = con.prepareStatement("SELECT COUNT(*) AS QTD FROM log");   
            rs = stmt.executeQuery();
            while(rs.next()){
                qtdLogs = rs.getString("QTD");
            }
            
            stmt = con.prepareStatement("SELECT * FROM log WHERE idLog = ?"); 
            stmt.setString(1, qtdLogs);
            rs = stmt.executeQuery();
            while(rs.next()){
                l.setId(rs.getInt("idLog"));
                l.setDescricao(rs.getString("descricao"));
                l.setCategoria(rs.getString("categoria"));
                l.setData(rs.getString("data"));
                l.setTipo(rs.getString("tipo"));
                l.setSaldo(rs.getDouble("saldo"));
                l.setStatus(rs.getInt("status"));
                l.setValor(rs.getDouble("valor"));
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);           
        }  
        return l;
     }*/
     
}
