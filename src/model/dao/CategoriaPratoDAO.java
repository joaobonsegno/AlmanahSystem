package model.dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.CategoriaPrato;

public class CategoriaPratoDAO {
    public void create(CategoriaPrato c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO categoriaPrato (nome, descricao)VALUES(?,?)";
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
    
    public ArrayList<CategoriaPrato> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<CategoriaPrato> categorias = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM categoriaPrato");
            rs = stmt.executeQuery();
            while (rs.next()){
                CategoriaPrato c = new CategoriaPrato();
                
                c.setId(rs.getInt("idCategoriaPrato"));
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
    
    public void update(CategoriaPrato c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE categoriaPrato SET nome=?, descricao=? WHERE idCategoriaPrato = ?";
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
