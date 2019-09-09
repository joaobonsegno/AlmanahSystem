package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import main.Login;
import model.bean.Caixa;
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
