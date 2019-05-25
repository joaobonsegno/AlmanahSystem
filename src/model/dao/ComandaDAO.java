package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Comanda;

public class ComandaDAO {
    public void create(Comanda c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO comanda (valor, idSistema, status)VALUES(?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, c.getValor());
            stmt.setInt(2, c.getId());
            stmt.setInt(3, c.getStatus());
            
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Comanda> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Comanda> comandas = new ArrayList<>();
        ComandaDAO cDao = new ComandaDAO();
        ItemComandaDAO iDao = new ItemComandaDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM comanda");
            rs = stmt.executeQuery();
            while (rs.next()){
                if (rs.getInt("status") == 0){
                    //cDao.delete(rs.getInt("idComanda"));
                    //iDao.delete(rs.getInt("idComanda"));
                }else{
                    Comanda c = new Comanda();

                    c.setIdBanco(rs.getInt("idComanda"));
                    c.setValor(rs.getDouble("valor"));
                    c.setStatus(rs.getInt("status"));
                    c.setId(rs.getInt("idSistema"));

                    comandas.add(c);
                }
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return comandas;
    }
    
    public void update(Comanda c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE comanda SET valor=?, idSistema=?, status=? WHERE idComanda = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, c.getValor());
            stmt.setInt(2, c.getId());
            stmt.setInt(3, c.getStatus());
            stmt.setInt(4, c.getIdBanco());
            
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Integer c){
        ItemComandaDAO iDao = new ItemComandaDAO();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM comanda WHERE idComanda = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c);
            
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
