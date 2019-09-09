package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.Produto;

public class MateriaPrimaDAO {
    Integer idItem = null;
    public void create(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            for (Produto materia : p.getMateriasPrimas()){
                String sql = "INSERT INTO materiaPrima (idProduto, idMateria)VALUES(?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, p.getIdProduto());
                stmt.setInt(2, materia.getIdProduto());
                
                stmt.executeUpdate();
                System.out.println("Salvo com sucesso!");
            }            
        }catch(SQLException ex){
            System.err.println("Erro SQL (ItemComandaDAO): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void read(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProdutoDAO pDao = new ProdutoDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM materiaPrima WHERE idProduto = "+p.getIdProduto());
            rs = stmt.executeQuery();
            while (rs.next()){
                for (Produto materia : pDao.read()){ //Percorre todos os produtos do banco, para achar qual deles é a MATÉRIA desejada
                    if (rs.getInt("idMateria") == materia.getIdProduto()){
                        p.setMateriaPrima(materia); //Seta a matéria no produto passado por parâmetro
                    }
                }                   
            }                
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL (MateriaPrimaDAO): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public void deleteAll(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = "DELETE FROM materiaPrima WHERE idProduto = "+p.getIdProduto();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /*
    public void deleteProduto(Comanda c, Integer id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM item_comanda WHERE idComanda = "+c.getIdBanco();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                int idProd = rs.getInt("idProduto");
                if(idProd >= 1){
                    if(idProd == id){
                        idItem = rs.getInt("idItemComanda");
                        delete(idItem);
                    }
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public void deletePrato(Comanda c, Double d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM item_comanda WHERE idComanda = "+c.getIdBanco();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                if(rs.getInt("idProduto") < 1){
                    if(rs.getDouble("valor") == d){
                        idItem = rs.getInt("idItemComanda");
                        delete(idItem);
                    }
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
                
    public void delete(Integer idItem){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM item_comanda WHERE idItemComanda = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idItem);
            
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void update(Comanda c, Produto p, Integer qtd){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE item_comanda SET idComanda=?, idProduto=?, qnt=?, valor=? WHERE idItemComanda = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getIdBanco());
            stmt.setInt(2, p.getIdProduto());
            stmt.setInt(3, qtd);
            stmt.setDouble(4, p.getPreco()*qtd);
            readItem(c, p.getIdProduto());
            stmt.setInt(5, idItem);
            
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar (ItemComandaDAO): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void readItem(Comanda c, int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM item_comanda WHERE idComanda = "+c.getIdBanco();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                int idProd = rs.getInt("idProduto");
                if (idProd == id){
                    idItem = rs.getInt("idItemComanda");
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }*/
}
