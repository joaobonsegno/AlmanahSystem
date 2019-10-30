package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import main.GerenciadorComandas;
import model.bean.AgendaDespesa;


public class AgendaDespesaDAO {
    public void create(AgendaDespesa d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO agendaDespesa (descricao, frequencia, valor, data, qtdVezes)VALUES(?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, d.getDescricao());
            stmt.setString(2, d.getFrequencia());
            stmt.setDouble(3, d.getValor());
            stmt.setString(4, d.getData());
            stmt.setInt(5, d.getQtdVezes());
            
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
            stmt = con.prepareStatement("SELECT * FROM agendaDespesa ORDER BY str_to_date(data, '%d/%m/%Y'), valor, descricao");
            rs = stmt.executeQuery();
            while (rs.next()){
                AgendaDespesa d = new AgendaDespesa();
                
                d.setId(rs.getInt("idAgendaDespesa"));
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getDouble("valor"));
                d.setFrequencia(rs.getString("frequencia"));
                d.setQtdVezes(rs.getInt("qtdVezes"));
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
                d.setFrequencia(rs.getString("frequencia"));
                d.setQtdVezes(rs.getInt("qtdVezes"));
                d.setData(rs.getString("data"));
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return d;
    }
    
    public  ArrayList<AgendaDespesa> readForDataAtual(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;       
        ArrayList<AgendaDespesa> a = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM agendaDespesa WHERE str_to_date(data, '%d/%m/%Y') <= str_to_date('"+GerenciadorComandas.getDataAtualSemHoraFormatoBr()+"', '%d/%m/%Y') "
                    + "ORDER BY str_to_date(data, '%d/%m/%Y'), valor");
            rs = stmt.executeQuery();
            while (rs.next()){  
                AgendaDespesa d = new AgendaDespesa();
                d.setId(rs.getInt("idAgendaDespesa"));
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getDouble("valor"));
                d.setFrequencia(rs.getString("frequencia"));
                d.setQtdVezes(rs.getInt("qtdVezes"));
                d.setData(rs.getString("data"));
                a.add(d);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return a;
    }
    
    public ArrayList<AgendaDespesa> readForDescricao(String desc){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<AgendaDespesa> despesas = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM agendaDespesa WHERE descricao LIKE '%"+desc+"%' ORDER BY str_to_date(data, '%d/%m/%Y'), valor, descricao";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                AgendaDespesa d = new AgendaDespesa();
                
                d.setId(rs.getInt("idAgendaDespesa"));
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getDouble("valor"));
                d.setFrequencia(rs.getString("frequencia"));
                d.setQtdVezes(rs.getInt("qtdVezes"));
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
            String sql = "UPDATE agendaDespesa SET descricao=?, valor=?, frequencia=?, data=?, qtdVezes=? WHERE idAgendaDespesa = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, d.getDescricao());
            stmt.setDouble(2, d.getValor());
            stmt.setString(3, d.getFrequencia());
            stmt.setString(4, d.getData());
            stmt.setInt(5, d.getQtdVezes());
            stmt.setInt(6, d.getId());            
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updateData(AgendaDespesa d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE agendaDespesa SET data=? WHERE idAgendaDespesa = ?";
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, d.getData());
            stmt.setInt(2, d.getId());            
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updateParcela(AgendaDespesa d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE agendaDespesa SET qtdVezes=? WHERE idAgendaDespesa = ?";
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, d.getQtdVezes());
            stmt.setInt(2, d.getId());            
            
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
