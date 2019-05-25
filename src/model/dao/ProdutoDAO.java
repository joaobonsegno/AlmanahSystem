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

public class ProdutoDAO {
    public void create(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO produto (idSistema, nome, ncm, ean, descricao, qtdMinima, preco, validade, qtdEstoque, idCategoria)VALUES(?,?,?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getIdSistema());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getNcm());
            stmt.setString(4, p.getEan());
            stmt.setString(5, p.getDescricao());
            stmt.setString(6, p.getQtdMinima());
            stmt.setDouble(7, p.getPreco());  
            stmt.setString(8, p.getValidade());
            stmt.setString(9, p.getQtdEstoque());
            stmt.setInt(10, p.getCategoria().getId());
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Produto> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> prods = new ArrayList<>();
        CategoriaDAO cDao = new CategoriaDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();
            while (rs.next()){
                Produto p = new Produto();
                
                p.setIdBanco(rs.getInt("idProduto"));
                p.setIdSistema(rs.getInt("idSistema"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setNcm(rs.getString("ncm"));
                p.setEan(rs.getString("ean"));
                p.setPreco(rs.getDouble("preco"));
                p.setQtdMinima(rs.getString("qtdMinima"));
                p.setQtdEstoque(rs.getString("qtdEstoque"));
                p.setValidade(rs.getString("validade"));
                Integer categoriaProduto = (rs.getInt("idCategoria"));
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
                
                p.setIdBanco(rs.getInt("idProduto"));
                p.setIdSistema(rs.getInt("idSistema"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setNcm(rs.getString("ncm"));
                p.setEan(rs.getString("ean"));
                p.setPreco(rs.getDouble("preco"));
                p.setQtdMinima(rs.getString("qtdMinima"));
                p.setQtdEstoque(rs.getString("qtdEstoque"));
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
    
    public void update(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE produto SET nome=?, ncm=?, ean=?, descricao=?, qtdMinima=?, preco=?, validade=?, qtdEstoque=?, idSistema=?, idCategoria=? WHERE idProduto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getNcm());
            stmt.setString(3, p.getEan());
            stmt.setString(4, p.getDescricao());
            stmt.setString(5, p.getQtdMinima());
            stmt.setDouble(6, p.getPreco());  
            stmt.setString(7, p.getValidade());
            stmt.setString(8, p.getQtdEstoque());
            stmt.setInt(9, p.getIdSistema());
            stmt.setInt(10, p.getCategoria().getId());
            stmt.setInt(11, p.getIdBanco());
            
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
            stmt.setInt(2, p.getIdBanco());
            
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
            stmt.setInt(1, p.getIdBanco());
            
            stmt.executeUpdate();
            System.out.println("Excluído com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
