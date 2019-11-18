package model.dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Categoria;
import model.bean.Produto;
import relatorio.RelatorioProduto;

public class ProdutoDAO {
    CategoriaDAO cDao;
    public void create(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO produto (nome, ncm, ean, descricao, qtdMinima, preco, qtdEstoque, unidadeDeMedida, idCategoria, precoComDesconto, status)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getNcm());
            stmt.setString(3, p.getEan());
            stmt.setString(4, p.getDescricao());
            stmt.setString(5, p.getQtdMinima());
            stmt.setDouble(6, p.getPreco());  
            stmt.setString(7, p.getQtdEstoque());
            stmt.setString(8, p.getUnidadeDeMedida());
            stmt.setInt(9, p.getCategoria().getId());
            stmt.setDouble(10, p.getPreco());
            stmt.setInt(11, 1);
            stmt.executeUpdate();
            //System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    // READS
    public ArrayList<Produto> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> prods = new ArrayList<>();
        cDao = new CategoriaDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM produto WHERE status = 1 ORDER BY nome");
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
                Integer categoriaProduto = (rs.getInt("idCategoria"));
                for (Categoria c:cDao.read()){
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
        cDao = new CategoriaDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM produto WHERE status = 1 AND nome LIKE ? ORDER BY nome");
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
                for (Categoria c:cDao.read()){
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
        cDao = new CategoriaDAO();
        Categoria cat = new Categoria();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM categoria WHERE nome LIKE ? ORDER BY nome");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            while (rs.next()){               
                cat.setId(rs.getInt("idCategoria"));
                cat.setNome(rs.getString("nome"));
                cat.setDescricao(rs.getString("descricao"));
            }
            
            stmt = con.prepareStatement("SELECT * FROM produto WHERE status = 1 AND idCategoria LIKE ? ORDER BY nome");
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
    
    public Produto readForId(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        cDao = new CategoriaDAO();
        Produto p = new Produto();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM produto WHERE status = 1 AND idProduto = "+id);
            
            rs = stmt.executeQuery();
            while (rs.next()){
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
                for (Categoria c:cDao.read()){
                    if (c.getId() == categoriaProduto){
                        p.setCategoria(c);
                    }
                }
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return p;
    }
    
    // UPDATES 
    public void update(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE produto SET nome=?, ncm=?, ean=?, descricao=?, qtdMinima=?, preco=?, qtdEstoque=?, idCategoria=?, unidadeDeMedida=? WHERE idProduto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getNcm());
            stmt.setString(3, p.getEan());
            stmt.setString(4, p.getDescricao());
            stmt.setString(5, p.getQtdMinima());
            stmt.setDouble(6, p.getPreco());  
            stmt.setString(7, p.getQtdEstoque());
            stmt.setInt(8, p.getCategoria().getId());
            stmt.setString(9, p.getUnidadeDeMedida());
            stmt.setInt(10, p.getIdProduto());
            
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
            //System.out.println("Atualizado com sucesso!");
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
            //System.out.println("Atualizado com sucesso!");
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
    
    // PRODUTO INATIVO
    public ArrayList<Produto> readInativos(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> prods = new ArrayList<>();
        cDao = new CategoriaDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM produto WHERE status = 0");
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
                Integer categoriaProduto = (rs.getInt("idCategoria"));
                for (Categoria c:cDao.read()){
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
    
    public void setInativo(int c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE produto SET status=? WHERE idProduto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, 0);
            stmt.setInt(2, c);
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void setAtivo(int c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE produto SET status=? WHERE idProduto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, 1);
            stmt.setInt(2, c);
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //Métodos para relatórios
    public ArrayList<RelatorioProduto> relatorio(String dataMaior, String dataMenor, Integer limite, int ordem){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<RelatorioProduto> produtos = new ArrayList<>();
        
        try{
            String sql = "";
            if (ordem == 0){
                sql = "SELECT p.idProduto AS 'id', "
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
            }else{
                sql = "SELECT p.idProduto AS 'id', "
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
                    +    "ORDER BY qtdVendas ASC LIMIT "+limite;
            }
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
     
    public ArrayList<Produto> relatorioEstoque(String exibirSomente, Integer ascOrDesc, int ordem){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> produtos = new ArrayList<>();
        
        try{
            String sql = "SELECT p.nome AS 'nome', "
                    +    "p.qtdEstoque AS 'qtd', "
                    +    "c.idCategoria AS 'categoria', "
                    +    "p.idProduto AS 'id', "
                    +    "p.unidadeDeMedida AS 'unidade' "
                    +    "FROM produto p "
                    +    "INNER JOIN categoria c ON c.idCategoria = p.idCategoria "
                    +    "WHERE p.qtdEstoque NOT LIKE 'X' ";
            
            // IF QUE VERIFICA O "EXIBIR SOMENTE"
            if (!exibirSomente.equals("<Todos>")){
                sql += "AND c.nome LIKE '"+exibirSomente+"' ";
            }
            
            
            // IFs QUE VERIFICAM A ORDENAÇÃO DA QUERY
            if (ordem == 0){ // Ordenar por categoria  
                if (ascOrDesc == 0){ // Ordenar DESCRESCENTE
                    sql += "ORDER BY c.nome DESC, p.nome ";
                }else{
                    sql += "ORDER BY c.nome ASC, p.nome ";
                }
            }else if (ordem == 1){ // Ordenar por nome
                if (ascOrDesc == 0){ // Ordenar DESCRESCENTE
                    sql += "ORDER BY p.nome DESC, c.nome ";
                }else{
                    sql += "ORDER BY p.nome ASC, c.nome ";
                }               
            }else{ // Ordenar por Qtd em estoque
                if (ascOrDesc == 0){ // Ordenar DESCRESCENTE
                    sql += "ORDER BY CAST(qtdEstoque AS UNSIGNED) DESC, c.nome ";
                }else{
                    sql += "ORDER BY CAST(qtdEstoque AS UNSIGNED) ASC, c.nome ";
                }
                
            } 
            //System.out.println("SQL: "+sql);
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
                
            while (rs.next()){
                Produto p = new Produto();
                CategoriaDAO cDao = new CategoriaDAO();
                p.setCategoria(cDao.readForId(rs.getInt("categoria")));
                p.setIdProduto(rs.getInt("id"));
                p.setQtdEstoque(rs.getString("qtd"));
                p.setNome(rs.getString("nome"));
                p.setUnidadeDeMedida(rs.getString("unidade"));

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
