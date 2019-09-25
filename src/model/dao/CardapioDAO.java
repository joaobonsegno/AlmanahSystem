package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Cardapio;

public class CardapioDAO {
    public void create(Cardapio c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO cardapio (status, data)VALUES(?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getStatus());
            stmt.setString(2, c.getData());
//          stmt.setInt(3, c.getDiaSemana());

            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Cardapio> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        CardapioDAO cDao = new CardapioDAO();
        ItemComandaDAO iDao = new ItemComandaDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM cardapio");
            rs = stmt.executeQuery();                        
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return setCardapios(rs);
    }
    
    public void updateStatus(Cardapio c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE comanda SET status=? WHERE idCardapio = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getStatus());
            stmt.setInt(2, c.getId());
                       
            stmt.executeUpdate();
           // System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public Cardapio readForData(String data){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CategoriaDAO cDao = new CategoriaDAO();
        Cardapio c = new Cardapio();
        try{
            stmt = con.prepareStatement("SELECT * FROM cardapio WHERE data LIKE ?");
            stmt.setString(1, data);
            
            rs = stmt.executeQuery();
            while (rs.next()){     
                c.setId(rs.getInt("idCardapio"));
                c.setData(rs.getString("data"));
                c.setStatus(rs.getInt("status"));
                /*for (CategoriaPrato c:Login.categoriasPratos){
                    if (c.getId() == rs.getInt("idCategoriaPrato")){
                        p.setCategoria(c);
                    }
                }*/
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL (CardapioDAO - readForData()): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return c;
    }
    
    private ArrayList<Cardapio> setCardapios(ResultSet rs){
        ArrayList<Cardapio> cardapios = new ArrayList<>();
        try{
        while (rs.next()){
                Cardapio c = new Cardapio();

                c.setId(rs.getInt("idCardapio"));
                c.setData(rs.getString("data"));
                c.setStatus(rs.getInt("status"));
                //c.setDiaSemana(rs.getInt("diaSemana"));
                cardapios.add(c);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }
        return cardapios;
    }
    
    
    /*
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
    */    
}
