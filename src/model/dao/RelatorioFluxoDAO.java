package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Despesa;
import model.bean.Forma;
import model.bean.FormaCarteira;
import relatorio.RelatorioFluxo;
import relatorio.RelatorioForma;
import relatorio.RelatorioFormaCarteira;


public class RelatorioFluxoDAO {    
    //Métodos para relatórios
    public ArrayList<RelatorioFluxo> relatorioFormas(String dataMaior, String dataMenor){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<RelatorioFluxo> formas = new ArrayList<>();
        
        try{
            String sql = "SELECT f.valor   AS 'valor', "
            +            "v.data    AS 'data'," 
            +            "f.formaPagamento 'forma' "
            +  " FROM formaPagamento f INNER JOIN venda v ON (f.idVenda = v.idVenda) " 
            +  " WHERE formaPagamento NOT LIKE \"Carteira\" " 
            +  " AND str_to_date(v.data, '%d/%m/%Y') BETWEEN str_to_date('"+dataMenor+"','%d/%m/%Y') AND str_to_date('"+dataMaior+"','%d/%m/%Y') "
            +  " ORDER BY str_to_date(v.data, '%d/%m/%Y')";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                RelatorioFluxo f = new RelatorioFluxo();
                
                f.setTipo("Crédito");
                f.setData(rs.getString("data"));
                f.setValor(rs.getDouble("valor"));
                f.setDescricao(rs.getString("forma"));

                formas.add(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return formas;
    }
    
    public ArrayList<RelatorioFluxo> relatorioFormasCarteira(String dataMaior, String dataMenor){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<RelatorioFluxo> formas = new ArrayList<>();
        
        try{
            String sql = "SELECT f.valor   AS 'valor', "
            +            "v.data    AS 'data', " 
            +            "f.formaPagamento 'forma' "
            +  " FROM formaPagamentoCarteira f INNER JOIN vendaCarteira v ON (f.idVenda = v.idVendaCarteira) " 
            +  " WHERE str_to_date(v.data, '%d/%m/%Y') BETWEEN str_to_date('"+dataMenor+"','%d/%m/%Y') AND str_to_date('"+dataMaior+"','%d/%m/%Y') "
            +  " ORDER BY str_to_date(v.data, '%d/%m/%Y')";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                RelatorioFluxo f = new RelatorioFluxo();
                
                f.setTipo("Crédito");
                f.setData(rs.getString("data"));
                f.setValor(rs.getDouble("valor"));
                f.setDescricao(rs.getString("forma"));

                formas.add(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return formas;
    }
    
    public ArrayList<RelatorioFluxo> relatorioDespesas(String dataMaior, String dataMenor){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<RelatorioFluxo> despesas = new ArrayList<>();
        
        try{
            String sql = "SELECT valor, "
            +            "data, " 
            +            "descricao "
            +  " FROM despesa " 
            +  " WHERE str_to_date(data, '%d/%m/%Y') BETWEEN str_to_date('"+dataMenor+"','%d/%m/%Y') AND str_to_date('"+dataMaior+"','%d/%m/%Y') "
            +  " ORDER BY str_to_date(data, '%d/%m/%Y')";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                RelatorioFluxo f = new RelatorioFluxo();
                
                f.setTipo("Débito");
                f.setData(rs.getString("data"));
                f.setValor(rs.getDouble("valor"));
                f.setDescricao(rs.getString("descricao"));

                despesas.add(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return despesas;
    }
}
