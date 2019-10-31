package model.dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.Forma;

public class FormaDAO {
    public void create(Forma f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO formaPagamento (valor, formaPagamento, idVenda)VALUES(?,?,?)";
            stmt = con.prepareStatement(sql);
            
            stmt.setDouble(1, f.getValor());  
            stmt.setString(2, f.getFormaPagamento());
            stmt.setInt(3, f.getVenda().getIdBanco());  
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
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
}
