package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Cliente;
import model.bean.Comanda;

public class ComandaDAO {
    public void create(Comanda c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO comanda (valor, valorPendente, idSistema, status)VALUES(?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, c.getValor());
            stmt.setDouble(2, c.getValorPendente());
            stmt.setInt(3, c.getId());
            stmt.setInt(4, c.getStatus());
            
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public int codComanda(String codigo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM codComanda WHERE codigo LIKE '"+codigo+"'");
            rs = stmt.executeQuery();
            while (rs.next()){
                id = (rs.getInt("idComanda"));                
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return id;
    }
    
    public ArrayList<Comanda> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Comanda> comandas = new ArrayList<>();
        ComandaDAO cDao = new ComandaDAO();
        ItemComandaDAO iDao = new ItemComandaDAO();
        ClienteDAO clienteDao = new ClienteDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM comanda");
            rs = stmt.executeQuery();
            while (rs.next()){
                Comanda c = new Comanda();

                c.setIdBanco(rs.getInt("idComanda"));
                c.setValor(rs.getDouble("valor"));
                c.setValorPendente(rs.getDouble("valorPendente"));
                c.setStatus(rs.getInt("status"));
                c.setId(rs.getInt("idSistema"));

                for (Cliente cliente : clienteDao.read()){
                    if (cliente.getId() == rs.getInt("idCliente")){
                        c.setCliente(cliente);
                    }
                }
                
                comandas.add(c);
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
            String sql = "UPDATE comanda SET valor=?, valorPendente=?, idSistema=?, status=? WHERE idComanda = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, c.getValor());
            stmt.setDouble(2, c.getValorPendente());
            stmt.setInt(3, c.getId());
            stmt.setInt(4, c.getStatus());
            stmt.setInt(5, c.getIdBanco());
            
            stmt.executeUpdate();
           // System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updatePendente(Comanda c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE comanda SET valorPendente = ? WHERE idComanda = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, c.getValorPendente());
            stmt.setInt(2, c.getIdBanco());
            
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
            deletarItens(c);
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
    
    public void deletarItens(Integer c){
        ItemComandaDAO iDao = new ItemComandaDAO();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM item_comanda WHERE idComanda = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c);
            
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updateCliente(Comanda c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE comanda SET idCliente = ? WHERE idComanda = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, c.getCliente().getId());
            stmt.setInt(2, c.getIdBanco());
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
