package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import main.GerenciadorComandas;
import model.bean.Alerta;

public class AlertaDAO {
    
    public void create(Alerta a){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO alerta (mensagem, idProduto, data)VALUES(?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, a.getMensagem());
            stmt.setInt(2, a.getProduto().getIdProduto());
            stmt.setString(3, GerenciadorComandas.getDataAtualComHoraFormatoBr());
            
            stmt.executeUpdate();

        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Alerta> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Alerta> alertas = new ArrayList<>();
        ProdutoDAO pDao = new ProdutoDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM alerta");
            rs = stmt.executeQuery();
            while (rs.next()){
                Alerta a = new Alerta();
                
                a.setId(rs.getInt("idAlerta"));
                a.setMensagem(rs.getString("mensagem"));
                a.setData(rs.getString("data"));
                a.setProduto(pDao.readForId(rs.getInt("idProduto")));
                
                alertas.add(a);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return alertas;
    }
    
    public boolean existeNoBanco(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM alerta LIMIT 1");
            rs = stmt.executeQuery();
            while (rs.next()){
                return true;
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return false;
    }
    
    public Alerta readForProduto(int idProd){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProdutoDAO pDao = new ProdutoDAO();
        Alerta a = new Alerta();
        try{
            stmt = con.prepareStatement("SELECT * FROM alerta WHERE idProduto = "+idProd);
            rs = stmt.executeQuery();
            while (rs.next()){
                a.setId(rs.getInt("idAlerta"));
                a.setMensagem(rs.getString("mensagem"));
                a.setData(rs.getString("data"));
                a.setProduto(pDao.readForId(rs.getInt("idProduto")));               
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return a;
    }

    public void delete(int id){
        ItemComandaDAO iDao = new ItemComandaDAO();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM alerta WHERE idProduto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        }catch(SQLException ex){
            //System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
