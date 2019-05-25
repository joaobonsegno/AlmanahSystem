package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import main.GerenciadorProdutos;
import model.bean.Comanda;
import model.bean.Produto;
import model.bean.Venda;

public class ItemVendaDAO {
    public void create(Produto p, Integer qtd, Venda v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO item_venda (idVenda, idProduto, quantidade, valor)VALUES(?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, v.getIdBanco());
            stmt.setInt(2, p.getIdBanco());
            stmt.setInt(3, qtd);
            stmt.setDouble(4, p.getPreco()*qtd);  
            
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL (ItemComandaDAO): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void create(Double valor, Venda v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO item_venda (idVenda, valor)VALUES(?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, v.getIdBanco());
            stmt.setDouble(2, valor);
            
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL (ItemComandaDAO): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void read(Comanda c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM item_comanda");
            rs = stmt.executeQuery();
            while (rs.next()){
                if(rs.getInt("idComanda")==c.getIdBanco()){
                    int idProd = rs.getInt("idProduto");
                    if(idProd >= 1){
                        for(Produto p:GerenciadorProdutos.listaProdutos){
                            if(p.getIdBanco() == idProd){
                                String qtdString = Integer.toString(rs.getInt("qnt"));
                                c.setItensBanco(p, qtdString);
                            }
                        }
                    }else{
                        c.setPratosBanco(rs.getDouble("valor"));
                    }
                }
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL (ItemComandaDAO): "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
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
