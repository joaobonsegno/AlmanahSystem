package model.dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.CategoriaPrato;
import model.bean.Prato;
import model.bean.Produto;

public class PratoDAO {
    public void create(Prato prato){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            //Criar registro do prato
            String sql = "INSERT INTO prato (nome, descricao, idCategoriaPrato)VALUES(?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, prato.getNome());
            stmt.setString(2, prato.getDescricao());
            stmt.setInt(3, prato.getCategoria().getId());
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
            
            //Pegar do banco o ID gerado para o prato
            prato.setId(readForNomeExato(prato.getNome()).getId());
            
            //Criar registros dos subprodutos do prato
            for (Produto p:prato.getSubprodutos()){
                sql = "INSERT INTO subproduto (idProduto, idPrato)VALUES(?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, p.getIdProduto());
                stmt.setInt(2, prato.getId());
                stmt.executeUpdate();
            } 
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    } 
    
    public ArrayList<Prato> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Prato> pratos = new ArrayList<>();
        CategoriaPratoDAO cDao = new CategoriaPratoDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM prato");
            rs = stmt.executeQuery();
            while (rs.next()){
                Prato p = new Prato();
                
                p.setId(rs.getInt("idPrato"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                for (CategoriaPrato c:cDao.read()){
                    if (c.getId() == rs.getInt("idCategoriaPrato")){
                        p.setCategoria(c);
                    }
                }
                this.setSubprodutos(p);
                
                pratos.add(p);
            }
            
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pratos;
    }
    
    public Prato readForNomeExato(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Prato p = new Prato();
        CategoriaPratoDAO cDao = new CategoriaPratoDAO();
        try{
            stmt = con.prepareStatement("SELECT * FROM prato WHERE nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            
            rs = stmt.executeQuery();
            while (rs.next()){     
                p.setId(rs.getInt("idPrato"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                for (CategoriaPrato c:cDao.read()){
                    if (c.getId() == rs.getInt("idCategoriaPrato")){
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
    
    public ArrayList<Prato> readForNome(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Prato> pratos = new ArrayList<>();
        CategoriaPratoDAO cDao = new CategoriaPratoDAO();     
        try{
            stmt = con.prepareStatement("SELECT * FROM prato WHERE nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            rs = stmt.executeQuery();
            while (rs.next()){   
                Prato p = new Prato();
                p.setId(rs.getInt("idPrato"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                for (CategoriaPrato c:cDao.read()){
                    if (c.getId() == rs.getInt("idCategoriaPrato")){
                        p.setCategoria(c);
                    }
                }
                pratos.add(p);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pratos;
    }
    
    public ArrayList<Prato> readForCategoria(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Prato> pratos = new ArrayList<>();
        CategoriaPratoDAO cDao = new CategoriaPratoDAO();
        CategoriaPrato cat = new CategoriaPrato();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM categoriaPrato WHERE nome LIKE ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            while (rs.next()){               
                cat.setId(rs.getInt("idCategoriaPrato"));
                cat.setNome(rs.getString("nome"));
                cat.setDescricao(rs.getString("descricao"));
            }
            
            stmt = con.prepareStatement("SELECT * FROM prato WHERE idCategoriaPrato LIKE ?");
            stmt.setInt(1, cat.getId());
            rs = stmt.executeQuery();
            while (rs.next()){               
                Prato p = new Prato();               
                p.setId(rs.getInt("idPrato"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                for (CategoriaPrato c:cDao.read()){
                    if (c.getId() == rs.getInt("idCategoriaPrato")){
                        p.setCategoria(c);
                    }
                }
                pratos.add(p);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pratos;
    }
    
    public void setSubprodutos(Prato prato){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProdutoDAO pDao = new ProdutoDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM subproduto WHERE idPrato="+prato.getId());
            rs = stmt.executeQuery();
            while (rs.next()){
                for (Produto p:pDao.read()){
                    if (p.getIdProduto() == rs.getInt("idProduto")){
                        prato.setSubproduto(p);
                    }
                }
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
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
    
    /*public void criarSubproduto(Prato prato){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            //Criar registros dos subprodutos do prato
            for (Produto p:prato.getSubprodutos()){
                String sql = "INSERT INTO subproduto (idProduto, idPrato)VALUES(?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, p.getIdProduto());
                stmt.setInt(2, prato.getId());
            } 
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }*/
}
