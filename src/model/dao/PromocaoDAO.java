package model.dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Promocao;

public class PromocaoDAO {
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
}
