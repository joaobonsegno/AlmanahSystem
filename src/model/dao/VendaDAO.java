package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import main.Login;
import model.bean.Caixa;
import model.bean.Forma;
import model.bean.Venda;

public class VendaDAO {
    public void create(Venda v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO venda (data, total, idCaixa)VALUES(?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, v.getData());
            stmt.setDouble(2, v.getTotal());
            stmt.setInt(3, Login.caixaAtual.getIdCaixa());
            
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro no CREATE da Venda: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void createComCliente(Venda v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO venda (data, total, idCaixa, idCliente)VALUES(?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, v.getData());
            stmt.setDouble(2, v.getTotal());
            stmt.setInt(3, Login.caixaAtual.getIdCaixa());
            stmt.setInt(4, v.getCliente().getId());
            
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro no CREATE da Venda: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updateCliente(Venda v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE venda SET idCliente = ? WHERE idVenda = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, v.getCliente().getId());
            stmt.setInt(2, v.getIdBanco());
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Venda> read(){
        CaixaDAO caixaDao = new CaixaDAO();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Venda> vendas = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM venda");
            rs = stmt.executeQuery();
            while (rs.next()){
                Venda v = new Venda();
                
                v.setIdBanco(rs.getInt("idVenda"));
                v.setData(rs.getString("data"));
                v.setTotal(rs.getDouble("total"));
                for(Caixa c:caixaDao.read()){
                    if(rs.getInt("idCaixa") == c.getIdCaixa()){
                        v.setCaixa(c);
                    }
                }
                vendas.add(v);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return vendas;
    }
    
    public Venda readLast(){
        CaixaDAO caixaDao = new CaixaDAO();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Venda v = new Venda();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM venda ORDER BY idVenda DESC LIMIT 1");
            rs = stmt.executeQuery();
            while (rs.next()){
                v.setIdBanco(rs.getInt("idVenda"));
                v.setData(rs.getString("data"));
                v.setTotal(rs.getDouble("total"));
                for(Caixa c:caixaDao.read()){
                    if(rs.getInt("idCaixa") == c.getIdCaixa()){
                        v.setCaixa(c);
                    }
                }
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return v;
    }
    
    public ArrayList<Venda> relatorio(String dataMaior, String dataMenor){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Venda> vendas = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM venda "
                    +    "WHERE str_to_date(data, '%d/%m/%Y') >= str_to_date('"+dataMenor+"','%d/%m/%Y') "
                    +    "AND   str_to_date(data, '%d/%m/%Y') <= str_to_date('"+dataMaior+"','%d/%m/%Y') "
                    +    "ORDER BY str_to_date(data, '%d/%m/%Y') ASC";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                Venda v = new Venda();
                
                v.setIdBanco(rs.getInt("idVenda"));
                v.setData(rs.getString("data"));
                v.setTotal(rs.getDouble("total"));

                vendas.add(v);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return vendas;
    }
    
    public ArrayList<Forma> getFormas(Venda venda){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Forma> formas = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM formaPagamento WHERE idVenda = "+venda.getIdBanco();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                Forma f = new Forma();
                
                f.setId(rs.getInt("idFormaPagamento"));
                f.setValor(rs.getDouble("valor"));
                f.setFormaPagamento(rs.getString("formaPagamento"));

                formas.add(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return formas;
    }
    
    public static int diferencaDatas(String dataMaior,String dataMenor){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int diferenca = 0;
        
        try{
            String sql = "SELECT datediff(str_to_date('"+dataMaior+"','%d/%m/%Y'),"
            +                            "str_to_date('"+dataMenor+"','%d/%m/%Y')) AS \"dif\"";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                diferenca = rs.getInt("dif");
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return diferenca;
    }
    
    /*public void update(Comanda c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE comanda SET valor=?, idSistema=?, status=? WHERE idCategoria = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, c.getValor());
            stmt.setInt(2, c.getId());
            stmt.setInt(3, c.getStatus());
            stmt.setInt(4, c.getIdBanco());
            
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar (ItemComandaDAO): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }*/
}
