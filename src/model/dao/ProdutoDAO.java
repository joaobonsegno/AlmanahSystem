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
import relatorio.RelatorioProduto;

public class ProdutoDAO {
    public void create(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO produto (nome, ncm, ean, descricao, qtdMinima, preco, validade, qtdEstoque, unidadeDeMedida, idCategoria, precoComDesconto)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
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
            stmt.setDouble(11, p.getPreco());
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
                
                p.setIdProduto(rs.getInt("idProduto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setNcm(rs.getString("ncm"));
                p.setEan(rs.getString("ean"));
                p.setPreco(rs.getDouble("preco"));
                p.setQtdMinima(rs.getString("qtdMinima"));
                p.setUnidadeDeMedida(rs.getString("unidadeDeMedida"));
                p.setPrecoComDesconto(rs.getDouble("precoComDesconto"));
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
                
                p.setIdProduto(rs.getInt("idProduto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setNcm(rs.getString("ncm"));
                p.setEan(rs.getString("ean"));
                p.setPreco(rs.getDouble("preco"));
                p.setQtdMinima(rs.getString("qtdMinima"));
                p.setQtdEstoque(rs.getString("qtdEstoque"));
                p.setUnidadeDeMedida(rs.getString("unidadeDeMedida"));
                p.setPrecoComDesconto(rs.getDouble("precoComDesconto"));
                
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
                p.setUnidadeDeMedida(rs.getString("unidadeDeMedida"));
                p.setPrecoComDesconto(rs.getDouble("precoComDesconto"));
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
    
    public void update(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE produto SET nome=?, ncm=?, ean=?, descricao=?, qtdMinima=?, preco=?, validade=?, qtdEstoque=?, idCategoria=?, unidadeDeMedida=? WHERE idProduto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getNcm());
            stmt.setString(3, p.getEan());
            stmt.setString(4, p.getDescricao());
            stmt.setString(5, p.getQtdMinima());
            stmt.setDouble(6, p.getPreco());  
            stmt.setString(7, p.getValidade());
            stmt.setString(8, p.getQtdEstoque());
            stmt.setInt(9, p.getCategoria().getId());
            stmt.setString(10, p.getUnidadeDeMedida());
            stmt.setInt(11, p.getIdProduto());
            
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
    
    public void updatePromocao(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE produto SET precoComDesconto=? WHERE idProduto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, p.getPrecoComDesconto());
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
    
    //Métodos para relatórios
    public ArrayList<RelatorioProduto> relatorio(String dataMaior, String dataMenor, Integer limite){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<RelatorioProduto> produtos = new ArrayList<>();
        
        try{
            String sql = "SELECT p.idProduto AS 'id', "
                    +    "p.nome AS 'nome', "
                    +    "p.preco AS 'preco', "
                    +    "SUM(i.quantidade) AS 'qtdVendas', "
                    +    "SUM(i.quantidade)*p.preco AS 'total' "
                    +    "FROM produto p "
                    +    "INNER JOIN item_venda i ON 	p.idProduto = i.idProduto "
                    +    "INNER JOIN venda v 	  ON  v.idVenda = i.idVenda "
                    +    "WHERE str_to_date(v.data, '%d/%m/%Y') >= str_to_date('"+dataMenor+"','%d/%m/%Y') "
                    +    "AND   str_to_date(v.data, '%d/%m/%Y') <= str_to_date('"+dataMaior+"','%d/%m/%Y') "
                    +    "GROUP BY p.idProduto "
                    +    "ORDER BY qtdVendas DESC LIMIT "+limite;
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                RelatorioProduto p = new RelatorioProduto();
                
                p.setId(rs.getInt("id"));
                p.setPreco(rs.getDouble("preco"));
                p.setTotal(rs.getDouble("total"));
                p.setQtdVendas(rs.getInt("qtdVendas"));
                p.setNome(rs.getString("nome"));

                produtos.add(p);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
    }
     
}
