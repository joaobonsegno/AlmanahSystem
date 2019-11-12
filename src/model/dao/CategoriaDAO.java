package model.dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Categoria;

public class CategoriaDAO {
    public void create(Categoria c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO categoria (nome, descricao)VALUES(?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getDescricao());
            
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Categoria> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Categoria> categorias = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM categoria ORDER BY nome");
            rs = stmt.executeQuery();
            while (rs.next()){
                Categoria c = new Categoria();
                
                c.setId(rs.getInt("idCategoria"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                
                categorias.add(c);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return categorias;
    }
    
    public Categoria readForId(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Categoria c = new Categoria();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM categoria WHERE idCategoria = "+id);
            rs = stmt.executeQuery();
            while (rs.next()){   
                c.setId(rs.getInt("idCategoria"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));

            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return c;
    }
    
    public void update(Categoria c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE categoria SET nome=?, descricao=? WHERE idCategoria = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getDescricao());
            stmt.setInt(3, c.getId());
            
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
