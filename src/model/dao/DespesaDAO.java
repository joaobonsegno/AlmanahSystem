package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Despesa;


public class DespesaDAO {
    public void create(Despesa d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO despesa (descricao, data, valor)VALUES(?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, d.getDescricao());
            stmt.setString(2, d.getData());
            stmt.setDouble(3, d.getValor());
            
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Despesa> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Despesa> despesas = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM promocao");
            rs = stmt.executeQuery();
            while (rs.next()){
                Despesa d = new Despesa();
                
                d.setId(rs.getInt("idPromocao"));
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getDouble("valor"));
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
    
    public void update(Despesa d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE despesa SET descricao=?, valor=?, data=? WHERE idDespesa = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, d.getDescricao());
            stmt.setDouble(2, d.getValor());
            stmt.setString(3, d.getData());
            stmt.setInt(4, d.getId());
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Despesa d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM despesa WHERE idDespesa = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, d.getId());
            
            stmt.executeUpdate();
            System.out.println("Excluído com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
