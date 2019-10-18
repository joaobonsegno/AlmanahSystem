package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.AgendaDespesa;


public class AgendaDespesaDAO {
    public void create(AgendaDespesa d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO agendaDespesa (descricao, dia, valor, status, data)VALUES(?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, d.getDescricao());
            stmt.setInt(2, d.getDia());
            stmt.setDouble(3, d.getValor());
            stmt.setInt(4, d.getStatus());
            stmt.setString(5, d.getData());
            
            stmt.executeUpdate();
            //System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<AgendaDespesa> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<AgendaDespesa> despesas = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM agendaDespesa ORDER BY dia, valor");
            rs = stmt.executeQuery();
            while (rs.next()){
                AgendaDespesa d = new AgendaDespesa();
                
                d.setId(rs.getInt("idAgendaDespesa"));
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getDouble("valor"));
                d.setDia(rs.getInt("dia"));
                d.setStatus(rs.getInt("status"));
                d.setData(rs.getString("data"));
                despesas.add(d);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return despesas;
    }
    
    public ArrayList<AgendaDespesa> readForStatus(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<AgendaDespesa> despesas = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM agendaDespesa WHERE status = 1 ORDER BY dia");
            rs = stmt.executeQuery();
            while (rs.next()){
                AgendaDespesa d = new AgendaDespesa();
                
                d.setId(rs.getInt("idAgendaDespesa"));
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getDouble("valor"));
                d.setDia(rs.getInt("dia"));
                d.setStatus(rs.getInt("status"));
                d.setData(rs.getString("data"));
                despesas.add(d);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return despesas;
    }
    
    public AgendaDespesa readForId(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AgendaDespesa d = new AgendaDespesa();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM agendaDespesa WHERE idAgendaDespesa = "+id);
            rs = stmt.executeQuery();
            while (rs.next()){               
                d.setId(rs.getInt("idAgendaDespesa"));
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getDouble("valor"));
                d.setDia(rs.getInt("dia"));
                d.setStatus(rs.getInt("status"));
                d.setData(rs.getString("data"));
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return d;
    }
    
    public ArrayList<AgendaDespesa> readForDescricao(String desc){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<AgendaDespesa> despesas = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM agendaDespesa WHERE descricao LIKE '%"+desc+"%' ORDER BY dia";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                AgendaDespesa d = new AgendaDespesa();
                
                d.setId(rs.getInt("idAgendaDespesa"));
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getDouble("valor"));
                d.setDia(rs.getInt("dia"));
                d.setStatus(rs.getInt("status"));
                d.setData(rs.getString("data"));
                despesas.add(d);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return despesas;
    }
    
    public void update(AgendaDespesa d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE agendaDespesa SET descricao=?, valor=?, dia=?, status=?, data=? WHERE idAgendaDespesa = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, d.getDescricao());
            stmt.setDouble(2, d.getValor());
            stmt.setInt(3, d.getDia());
            stmt.setInt(4, d.getStatus());
            stmt.setString(5, d.getData());
            stmt.setInt(6, d.getId());            
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updateStatusEData(AgendaDespesa d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE agendaDespesa SET status=?, data=? WHERE idAgendaDespesa = ?";
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, d.getStatus());
            stmt.setString(2, d.getData());
            stmt.setInt(3, d.getId());            
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updateStatusMassivo(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE agendaDespesa SET status=1";
            stmt = con.prepareStatement(sql);                   
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Integer d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM agendaDespesa WHERE idAgendaDespesa = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, d);
            
            stmt.executeUpdate();
            //System.out.println("Excluído com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
