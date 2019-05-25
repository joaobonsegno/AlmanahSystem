package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import main.Login;
import model.bean.Caixa;
import model.bean.Categoria;
import model.bean.Turno;

public class CaixaDAO {
    public void create(Caixa c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO caixa (dinheiro, dataAbertura, status, idTurno)VALUES(?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, c.getDinheiro());
            stmt.setString(2, c.getDataAbertura());
            stmt.setInt(3, c.getStatus());
            stmt.setInt(4, c.getTurno().getIdTurno());
            
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Caixa> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Caixa> caixas = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM caixa");
            rs = stmt.executeQuery();
            while (rs.next()){
                Caixa c = new Caixa();
                
                c.setIdCaixa(rs.getInt("idCaixa"));
                c.setDataAbertura(rs.getString("dataAbertura"));
                c.setDataFechamento(rs.getString("dataFechamento"));
                c.setDinheiro(rs.getDouble("dinheiro"));
                c.setStatus(rs.getInt("status"));
                c.setTurno(Login.turnoAtual);
                
                caixas.add(c);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return caixas;
    }
    
    public void update(Caixa c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE caixa SET dataAbertura=?, dataFechamento=?, status=?, dinheiro=?, idTurno=? WHERE idCaixa = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getDataAbertura());
            stmt.setString(2, c.getDataFechamento());
            stmt.setInt(3, c.getStatus());
            stmt.setDouble(4, c.getDinheiro());
            stmt.setInt(5, c.getTurno().getIdTurno());
            stmt.setInt(6, c.getIdCaixa());
            
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
