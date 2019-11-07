package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Fornecedor;
import model.bean.Produto;

public class FornecedorDAO {
    public void create(Fornecedor f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO fornecedor (nome, cnpj, telefone, celular, email, logradouro, bairro, cidade, numero, "
                    + "complemento, cep, status, idEstado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCnpj());
            stmt.setString(3, f.getTelefone());
            stmt.setString(4, f.getCelular());
            stmt.setString(5, f.getEmail());
            stmt.setString(6, f.getLogradouro());
            stmt.setString(7, f.getBairro());
            stmt.setString(8, f.getCidade());
            stmt.setInt(9, f.getNumero());
            stmt.setString(10, f.getComplemento());
            stmt.setString(11, f.getCep());
            stmt.setInt(12, 1);
            stmt.setInt(13, f.getEstado().getId());
 
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="READ / READ FOR NOME / UPDATE">
    public ArrayList<Fornecedor> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Fornecedor> prods = new ArrayList<>();
        EstadoDAO eDao = new EstadoDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM fornecedor WHERE status = 1 ORDER BY nome");
            rs = stmt.executeQuery();
            while (rs.next()){
                Fornecedor f = new Fornecedor();
                
                f.setId(rs.getInt("idFornecedor"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setCelular(rs.getString("celular"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));
                f.setLogradouro(rs.getString("logradouro"));
                f.setBairro(rs.getString("bairro"));
                f.setCidade(rs.getString("cidade"));
                f.setNumero(rs.getInt("numero"));
                f.setComplemento(rs.getString("complemento"));
                f.setCidade(rs.getString("cidade"));
                f.setEstado(eDao.readForId(rs.getInt("idEstado")));
                
                // Seta dentro do Fornecedor todos os itens que ele fornece
                readItens(f);
                prods.add(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return prods;
    }
    
    public ArrayList<Fornecedor> readForNome(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Fornecedor> prods = new ArrayList<>();
        EstadoDAO eDao = new EstadoDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM fornecedor WHERE status = 1 AND nome LIKE ? ORDER BY nome");
            stmt.setString(1, "%"+nome+"%");
            
            rs = stmt.executeQuery();
             while (rs.next()){
                Fornecedor f = new Fornecedor();
                
                f.setId(rs.getInt("idFornecedor"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setCelular(rs.getString("celular"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));
                f.setLogradouro(rs.getString("logradouro"));
                f.setBairro(rs.getString("bairro"));
                f.setCidade(rs.getString("cidade"));
                f.setNumero(rs.getInt("numero"));
                f.setComplemento(rs.getString("complemento"));
                f.setCidade(rs.getString("cidade"));
                f.setEstado(eDao.readForId(rs.getInt("idEstado")));

                // Seta dentro do Fornecedor todos os itens que ele fornece
                readItens(f);
                prods.add(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return prods;
    }   
    
    public Fornecedor readForId(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EstadoDAO eDao = new EstadoDAO();
        Fornecedor f = new Fornecedor();
        try{
            stmt = con.prepareStatement("SELECT * FROM fornecedor WHERE status = 1 AND idFornecedor = "+id+" ORDER BY nome");
            
            rs = stmt.executeQuery();
             while (rs.next()){            
                f.setId(rs.getInt("idFornecedor"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setCelular(rs.getString("celular"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));
                f.setLogradouro(rs.getString("logradouro"));
                f.setBairro(rs.getString("bairro"));
                f.setCidade(rs.getString("cidade"));
                f.setNumero(rs.getInt("numero"));
                f.setComplemento(rs.getString("complemento"));
                f.setCidade(rs.getString("cidade"));
                f.setEstado(eDao.readForId(rs.getInt("idEstado")));
                // Seta dentro do Fornecedor todos os itens que ele fornece
                readItens(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return f;
    }
    
    public void update(Fornecedor f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE fornecedor SET nome=?, cnpj=?, telefone=?, celular=?, email=?, logradouro=?, bairro=?, numero=?, "
                    + "complemento=?, cidade=?, cep=?, idEstado=? WHERE idFornecedor = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCnpj());
            stmt.setString(3, f.getTelefone());
            stmt.setString(4, f.getCelular());
            stmt.setString(5, f.getEmail());
            stmt.setString(6, f.getLogradouro());
            stmt.setString(7, f.getBairro());           
            stmt.setInt(8, f.getNumero());         
            stmt.setString(9, f.getComplemento());
            stmt.setString(10, f.getCidade());
            stmt.setString(11, f.getCep());         
            stmt.setInt(12, f.getEstado().getId());
            stmt.setInt(13, f.getId());
            
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void readItens(Fornecedor f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProdutoDAO pDao = new ProdutoDAO();
    
        try{
            stmt = con.prepareStatement("SELECT * FROM produto_fornecedor WHERE idFornecedor = "+f.getId());
            rs = stmt.executeQuery();
            while (rs.next()){ 
                Produto p = pDao.readForId(rs.getInt("idProduto"));
                f.setProduto(p);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    // </editor-fold>
    
    public ArrayList<Produto> readProdutosFornecidos(Fornecedor f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProdutoDAO pDao = new ProdutoDAO();
        ArrayList<Produto> prods = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM produto_fornecedor WHERE idFornecedor = "+f.getId());
            rs = stmt.executeQuery();
            while (rs.next()){ 
                Produto p = pDao.readForId(rs.getInt("idProduto"));
                prods.add(p);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return prods;
    }
    
    public void addItem(Fornecedor f, Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO produto_fornecedor (idProduto, idFornecedor) VALUES (?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getIdProduto());
            stmt.setInt(2, f.getId());
 
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void deleteItem(Produto p){
        ItemComandaDAO iDao = new ItemComandaDAO();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM produto_fornecedor WHERE idProduto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getIdProduto());
            
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="ATIVAR / INATIVAR">  
    public void setAtivo(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE fornecedor SET status=? WHERE idFornecedor = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, 1);
            stmt.setInt(2, id);
            
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void setInativo(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE fornecedor SET status=? WHERE idFornecedor = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, 0);
            stmt.setInt(2, id);
            
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Fornecedor> readInativos(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Fornecedor> prods = new ArrayList<>();
        EstadoDAO eDao = new EstadoDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM fornecedor WHERE status = 0 ORDER BY nome");
            rs = stmt.executeQuery();
            while (rs.next()){
                Fornecedor f = new Fornecedor();
                
                f.setId(rs.getInt("idFornecedor"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setCelular(rs.getString("celular"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));
                f.setLogradouro(rs.getString("logradouro"));
                f.setBairro(rs.getString("bairro"));
                f.setCidade(rs.getString("cidade"));
                f.setNumero(rs.getInt("numero"));
                f.setComplemento(rs.getString("complemento"));
                f.setCidade(rs.getString("cidade"));
                f.setEstado(eDao.readForId(rs.getInt("idEstado")));

                prods.add(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return prods;
    }
    // </editor-fold> 
}
