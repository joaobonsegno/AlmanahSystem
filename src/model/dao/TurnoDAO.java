package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Categoria;
import model.bean.Turno;

public class TurnoDAO {
    public void create(Turno t){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO turno (dataAbertura,status) VALUES(?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, t.getDataAbertura());
            stmt.setInt(2, t.getStatus());
            
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Turno> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Turno> turnos = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM turno");
            rs = stmt.executeQuery();
            while (rs.next()){
                Turno t = new Turno();
                
                t.setIdTurno(rs.getInt("idTurno"));
                t.setDataAbertura(rs.getString("dataAbertura"));
                t.setDataFechamento(rs.getString("dataFechamento"));
                t.setStatus(rs.getInt("status"));
                
                turnos.add(t);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return turnos;
    }
    
    public void update(Turno t){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE turno SET dataAbertura=?, dataFechamento=?, status=? WHERE idTurno = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, t.getDataAbertura());
            stmt.setString(2, t.getDataFechamento());
            stmt.setInt(3, t.getStatus());
            stmt.setInt(4, t.getIdTurno());
            
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
   /*public Turno readAtual(){
        Turno turnoAberto = new Turno();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Turno> turnos = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM turno");
            rs = stmt.executeQuery();
            while (rs.next()){
                Turno t = new Turno();
                
                t.setIdTurno(rs.getInt("idTurno"));
                t.setDataAbertura(rs.getString("dataAbertura"));
                t.setStatus(rs.getInt("status"));
                
                turnos.add(t);
            }
            for(Turno t:turnos){
                if(t.getStatus()==1){
                    turnoAberto = t;
                }
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return turnoAberto;
    }*/
}
