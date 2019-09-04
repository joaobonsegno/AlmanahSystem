package model.dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import main.Login;
import model.bean.Categoria;
import model.bean.Produto;
import model.bean.Promocao;

public class PromocaoDAO {
    public void create(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO produto (nome, ncm, ean, descricao, qtdMinima, preco, validade, qtdEstoque, unidadeDeMedida, idCategoria)VALUES(?,?,?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getNcm());
            stmt.setString(3, p.getEan());
            stmt.setString(4, p.getDescricao());
            stmt.setString(5, p.getQtdMinima());
            stmt.setDouble(6, p.getPreco());  
            stmt.setString(7, p.getValidade());
            stmt.setString(8, p.getQtdEstoque());
            stmt.setString(9, p.getUnidadeDeMedida());
            stmt.setInt(10, p.getCategoria().getId());
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Promocao> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Promocao> promos = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM promocao");
            rs = stmt.executeQuery();
            while (rs.next()){
                Promocao p = new Promocao();
                
                p.setId(rs.getInt("idPromocao"));
                p.setDescricao(rs.getString("descricao"));
                p.setStatus(rs.getInt("status"));

                promos.add(p);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return promos;
    }
    
    public ArrayList<Produto> readForNome(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> prods = new ArrayList<>();
        CategoriaDAO cDao = new CategoriaDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM produto WHERE nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            
            rs = stmt.executeQuery();
            while (rs.next()){
                Produto p = new Produto();
                
                p.setIdProduto(rs.getInt("idProduto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setNcm(rs.getString("ncm"));
                p.setEan(rs.getString("ean"));
                p.setPreco(rs.getDouble("preco"));
                p.setQtdMinima(rs.getString("qtdMinima"));
                p.setQtdEstoque(rs.getString("qtdEstoque"));
                p.setUnidadeDeMedida("unidadeDeMedida");
                Integer categoriaProduto = (rs.getInt("idCategoria"));
                p.setValidade(rs.getString("validade"));
                for (Categoria c:Login.categorias){
                    if (c.getId() == categoriaProduto){
                        p.setCategoria(c);
                    }
                }
                prods.add(p);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return prods;
    }
    
    public ArrayList<Produto> readForCategoria(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> prods = new ArrayList<>();
        CategoriaDAO cDao = new CategoriaDAO();
        Categoria cat = new Categoria();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM categoria WHERE nome LIKE ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            while (rs.next()){               
                cat.setId(rs.getInt("idCategoria"));
                cat.setNome(rs.getString("nome"));
                cat.setDescricao(rs.getString("descricao"));
            }
            
            stmt = con.prepareStatement("SELECT * FROM produto WHERE idCategoria LIKE ?");
            stmt.setInt(1, cat.getId());
            rs = stmt.executeQuery();
            while (rs.next()){
                
                Produto p = new Produto();
                
                p.setIdProduto(rs.getInt("idProduto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setNcm(rs.getString("ncm"));
                p.setEan(rs.getString("ean"));
                p.setPreco(rs.getDouble("preco"));
                p.setQtdMinima(rs.getString("qtdMinima"));
                p.setQtdEstoque(rs.getString("qtdEstoque"));
                Integer categoriaProduto = (rs.getInt("idCategoria"));
                p.setValidade(rs.getString("validade"));
                p.setUnidadeDeMedida("unidadeDeMedida");
                p.setCategoria(cat);
                prods.add(p);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return prods;
    }
    
    public void update(Promocao p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE promocao SET descricao=?, status=? WHERE idPromocao = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getDescricao());
            stmt.setInt(3, p.getStatus());
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updateEstoque(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE produto SET qtdEstoque=? WHERE idProduto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getQtdEstoque());
            stmt.setInt(2, p.getIdProduto());
            
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM produto WHERE idProduto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getIdProduto());
            
            stmt.executeUpdate();
            System.out.println("Excluído com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
