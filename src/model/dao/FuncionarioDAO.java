package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Cargo;
import model.bean.Estado;
import model.bean.Funcionario;

public class FuncionarioDAO {
    private CargoDAO cargoDao = new CargoDAO();
    EstadoDAO eDao = new EstadoDAO();
    
    public void create(Funcionario f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "INSERT INTO funcionario (nome, cpf, sexo, dataNasc, telefone, celular, email, salario, usuario, senha, logradouro, bairro, "+
                         "cidade, numero, complemento, cep, idEstado, idCargo, status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCpf());
            stmt.setString(3, f.getSexo());
            stmt.setString(4, f.getDataNasc());
            stmt.setString(5, f.getTelefone());
            stmt.setString(6, f.getCelular());
            stmt.setString(7, f.getEmail());  
            stmt.setDouble(8, f.getSalario());
            stmt.setString(9, f.getUsuario());
            stmt.setString(10, f.getSenha());
            stmt.setString(11, f.getLogradouro());
            stmt.setString(12, f.getBairro());
            stmt.setString(13, f.getCidade());
            stmt.setInt(14, f.getNumero());
            stmt.setString(15, f.getComplemento());
            stmt.setString(16, f.getCep());
            stmt.setInt(17, f.getEstado().getId());
            stmt.setInt(18, f.getCargo().getId());
            stmt.setInt(19, 1);
            stmt.executeUpdate();
            //System.out.println("Salvo com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro SQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="READS DOS ATIVOS">  
    public ArrayList<Funcionario> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE status = 1");
            rs = stmt.executeQuery();
            while (rs.next()){
                Funcionario f = new Funcionario();               
                
                f.setIdFuncionario(rs.getInt("idFuncionario"));
                f.setUsuario(rs.getString("usuario"));
                f.setSenha(rs.getString("senha"));
                f.setNome(rs.getString("nome")); 
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setSexo(rs.getString("sexo"));
                f.setSalario(rs.getDouble("salario"));
                f.setTelefone(rs.getString("telefone"));
                f.setCelular(rs.getString("celular"));
                f.setLogradouro(rs.getString("logradouro"));
                f.setBairro(rs.getString("bairro"));
                f.setNumero(rs.getInt("numero"));
                f.setCidade(rs.getString("cidade"));
                f.setCep(rs.getString("cep"));
                f.setComplemento(rs.getString("complemento"));
                f.setDataNasc(rs.getString("dataNasc"));
                
                for (Cargo c:cargoDao.read()){
                    if (c.getId() == rs.getInt("idCargo")){
                        f.setCargo(c);
                    }
                }
                
                for (Estado e:eDao.read()){
                    if (e.getId() == rs.getInt("idEstado")){
                        f.setEstado(e);
                    }
                }               

                funcionarios.add(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return funcionarios;
    }
    
    public ArrayList<Funcionario> readLogin(String user){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        Funcionario f = new Funcionario();
        
        try{
            String sql = "SELECT * FROM funcionario WHERE status = 1 AND usuario = \""+user+"\"";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                f.setIdFuncionario(rs.getInt("idFuncionario"));
                f.setUsuario(rs.getString("usuario"));
                f.setSenha(rs.getString("senha"));
                f.setNome(rs.getString("nome")); 
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setSexo(rs.getString("sexo"));
                f.setSalario(rs.getDouble("salario"));
                f.setTelefone(rs.getString("telefone"));
                f.setCelular(rs.getString("celular"));
                f.setLogradouro(rs.getString("logradouro"));
                f.setBairro(rs.getString("bairro"));
                f.setNumero(rs.getInt("numero"));
                f.setCidade(rs.getString("cidade"));
                f.setCep(rs.getString("cep"));
                f.setComplemento(rs.getString("complemento"));
                f.setDataNasc(rs.getString("dataNasc"));
                
                for (Cargo c:cargoDao.read()){
                    if (c.getId() == rs.getInt("idCargo")){
                        f.setCargo(c);
                    }
                }
                
                for (Estado e:eDao.read()){
                    if (e.getId() == rs.getInt("idEstado")){
                        f.setEstado(e);
                    }
                } 
                
                funcionarios.add(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return funcionarios;
    }

    public Funcionario readForId(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario f = new Funcionario();
        EstadoDAO eDao = new EstadoDAO();
        
        try{
            String sql = "SELECT * FROM funcionario WHERE status = 1 AND idFuncionario = "+id;
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){                        
                f.setIdFuncionario(rs.getInt("idFuncionario"));
                f.setUsuario(rs.getString("usuario"));
                f.setSenha(rs.getString("senha"));
                f.setNome(rs.getString("nome")); 
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setSexo(rs.getString("sexo"));
                f.setSalario(rs.getDouble("salario"));
                f.setTelefone(rs.getString("telefone"));
                f.setCelular(rs.getString("celular"));
                f.setLogradouro(rs.getString("logradouro"));
                f.setBairro(rs.getString("bairro"));
                f.setNumero(rs.getInt("numero"));
                f.setCidade(rs.getString("cidade"));
                f.setCep(rs.getString("cep"));
                f.setComplemento(rs.getString("complemento"));
                f.setDataNasc(rs.getString("dataNasc"));
                
                for (Cargo c:cargoDao.read()){
                    if (c.getId() == rs.getInt("idCargo")){
                        f.setCargo(c);
                    }
                }
                
                for (Estado e:eDao.read()){
                    if (e.getId() == rs.getInt("idEstado")){
                        f.setEstado(e);
                    }
                } 

            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return f;
    }
    
    public ArrayList<Funcionario> readForCargo(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionario> funcs = new ArrayList<>();
        CategoriaDAO cDao = new CategoriaDAO();
        Cargo cargo = new Cargo();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM cargo WHERE nome LIKE ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            while (rs.next()){               
                cargo.setId(rs.getInt("idCargo"));
                cargo.setNome(rs.getString("nome"));
                cargo.setDescricao(rs.getString("descricao"));
            }
            
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE status = 1 AND idCargo LIKE ?");
            stmt.setInt(1, cargo.getId());
            rs = stmt.executeQuery();
            while (rs.next()){
                
                Funcionario f = new Funcionario();
                
                f.setIdFuncionario(rs.getInt("idFuncionario"));
                f.setUsuario(rs.getString("usuario"));
                f.setSenha(rs.getString("senha"));
                f.setNome(rs.getString("nome")); 
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setSexo(rs.getString("sexo"));
                f.setSalario(rs.getDouble("salario"));
                f.setTelefone(rs.getString("telefone"));
                f.setCelular(rs.getString("celular"));
                f.setLogradouro(rs.getString("logradouro"));
                f.setBairro(rs.getString("bairro"));
                f.setNumero(rs.getInt("numero"));
                f.setCidade(rs.getString("cidade"));
                f.setCep(rs.getString("cep"));
                f.setComplemento(rs.getString("complemento"));
                f.setDataNasc(rs.getString("dataNasc"));
                f.setCargo(cargo); 
                for (Estado e:eDao.read()){
                    if (e.getId() == rs.getInt("idEstado")){
                        f.setEstado(e);
                    }
                }
                funcs.add(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return funcs;
    }
    
    public ArrayList<Funcionario> readForNome(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionario> funcs = new ArrayList<>();
        CategoriaDAO cDao = new CategoriaDAO();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE status = 1 AND nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            
            rs = stmt.executeQuery();
            while (rs.next()){
                Funcionario f = new Funcionario();
                
                f.setIdFuncionario(rs.getInt("idFuncionario"));
                f.setUsuario(rs.getString("usuario"));
                f.setSenha(rs.getString("senha"));
                f.setNome(rs.getString("nome")); 
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setSexo(rs.getString("sexo"));
                f.setSalario(rs.getDouble("salario"));
                f.setTelefone(rs.getString("telefone"));
                f.setCelular(rs.getString("celular"));
                f.setLogradouro(rs.getString("logradouro"));
                f.setBairro(rs.getString("bairro"));
                f.setNumero(rs.getInt("numero"));
                f.setCidade(rs.getString("cidade"));
                f.setCep(rs.getString("cep"));
                f.setComplemento(rs.getString("complemento"));
                f.setDataNasc(rs.getString("dataNasc"));
                for (Cargo c:cargoDao.read()){
                    if (c.getId() == rs.getInt("idCargo")){
                        f.setCargo(c);
                    }
                } 
                for (Estado e:eDao.read()){
                    if (e.getId() == rs.getInt("idEstado")){
                        f.setEstado(e);
                    }
                }
                funcs.add(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return funcs;
    }
    // </editor-fold> 
    
    public void update(Funcionario f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE funcionario SET nome=?, cpf=?, sexo=?, email=?, salario=?, dataNasc=?, telefone=?, celular=?, logradouro=?, bairro=?, "
                    + "cidade=?, numero=?, complemento=?, cep=?, idEstado=?, usuario=?, senha=?, idCargo=?  WHERE idFuncionario = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCpf());
            stmt.setString(3, f.getSexo());
            stmt.setString(4, f.getEmail());
            stmt.setDouble(5, f.getSalario());
            stmt.setString(6, f.getDataNasc());             
            stmt.setString(7, f.getTelefone());
            stmt.setString(8, f.getCelular());           
            stmt.setString(9, f.getLogradouro());
            stmt.setString(10, f.getBairro());
            stmt.setString(11, f.getCidade());
            stmt.setInt(12, f.getNumero());
            stmt.setString(13, f.getComplemento());
            stmt.setString(14, f.getCep());
            stmt.setInt(15, f.getEstado().getId());
            stmt.setString(16, f.getUsuario());
            stmt.setString(17, f.getSenha());
            stmt.setInt(18, f.getCargo().getId());
            stmt.setInt(19, f.getIdFuncionario());
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    // PRATO INATIVO
    public ArrayList<Funcionario> readInativos(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE status = 0");
            rs = stmt.executeQuery();
            while (rs.next()){
                Funcionario f = new Funcionario();               
                
                f.setIdFuncionario(rs.getInt("idFuncionario"));
                f.setUsuario(rs.getString("usuario"));
                f.setSenha(rs.getString("senha"));
                f.setNome(rs.getString("nome")); 
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setSexo(rs.getString("sexo"));
                f.setSalario(rs.getDouble("salario"));
                f.setTelefone(rs.getString("telefone"));
                f.setCelular(rs.getString("celular"));
                f.setLogradouro(rs.getString("logradouro"));
                f.setBairro(rs.getString("bairro"));
                f.setNumero(rs.getInt("numero"));
                f.setCidade(rs.getString("cidade"));
                f.setCep(rs.getString("cep"));
                f.setComplemento(rs.getString("complemento"));
                f.setDataNasc(rs.getString("dataNasc"));
                
                for (Cargo c:cargoDao.read()){
                    System.out.println("Cargo "+c.getId());
                    if (c.getId() == rs.getInt("idCargo")){
                        f.setCargo(c);
                    }
                }
                
                for (Estado e:eDao.read()){
                    if (e.getId() == rs.getInt("idEstado")){
                        f.setEstado(e);
                    }
                }               

                funcionarios.add(f);
            }
        }catch(SQLException ex){
            System.err.println("Erro no READ MySQL: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return funcionarios;
    }
    
    public void setInativo(int c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE funcionario SET status=? WHERE idFuncionario = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, 0);
            stmt.setInt(2, c);
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void setAtivo(int c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE funcionario SET status=? WHERE idFuncionario = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, 1);
            stmt.setInt(2, c);
            
            stmt.executeUpdate();
            //System.out.println("Atualizado com sucesso!");
        }catch(SQLException ex){
            System.err.println("Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
