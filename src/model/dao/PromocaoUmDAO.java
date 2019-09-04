package model.dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.PromocaoUm;

public class PromocaoUmDAO {
    /*public void create(Promocao1 p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO promocao1 (id, )VALUES(?,?,?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getId());
            stmt.setInt(2, p.getSeg());
            stmt.setInt(3, p.getTer());
            stmt.setInt(4, p.getQua());
            stmt.setInt(5, p.getQui());
            stmt.setInt(6, p.getSex());
            stmt.setInt(7, p.getSab());
            stmt.setInt(8, p.getDom());
            stmt.setInt(9, p.getStatus());
            stmt.setString(10, p.getDescricao());
            stmt.setDouble(11, p.getPorcentagem());
            
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }*/
    
    public PromocaoUm read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PromocaoUm p = new PromocaoUm();
        try{
            stmt = con.prepareStatement("SELECT * FROM promocao1");
            rs = stmt.executeQuery();
            while (rs.next()){  
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setStatus(rs.getInt("status"));
                p.setSeg(rs.getInt("seg"));
                p.setTer(rs.getInt("ter"));
                p.setQua(rs.getInt("qua"));
                p.setQui(rs.getInt("qui"));
                p.setSex(rs.getInt("sex"));
                p.setSab(rs.getInt("sab"));
                p.setDom(rs.getInt("dom"));
                p.setPorcentagem(rs.getDouble("porcentagem"));
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return p;
    }
    
    public void update(PromocaoUm p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE promocao1 SET descricao=?, status=?, seg=?, ter=?, qua=?, qui=?, sex=?, sab=?, dom=?, porcentagem=? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, p.getDescricao());
            stmt.setInt(2, p.getStatus());
            stmt.setInt(3, p.getSeg());
            stmt.setInt(4, p.getTer());
            stmt.setInt(5, p.getQua());
            stmt.setInt(6, p.getQui());
            stmt.setInt(7, p.getSex());
            stmt.setInt(8, p.getSab());
            stmt.setInt(9, p.getDom());
            stmt.setDouble(10, p.getPorcentagem());
            stmt.setInt(11, p.getId());
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
