package main;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import static main.GerenciadorProdutos.listaProdutos;
import model.bean.Caixa;
import model.bean.Categoria;
import model.bean.CategoriaPrato;
import model.bean.Comanda;
import model.bean.Funcionario;
import model.bean.Produto;
import model.dao.CaixaDAO;
import model.dao.CategoriaDAO;
import model.dao.CategoriaPratoDAO;
import model.dao.ComandaDAO;
import model.dao.FuncionarioDAO;
import model.dao.ItemComandaDAO;
import model.dao.LogDAO;
import model.dao.ProdutoDAO;


public class Login extends javax.swing.JFrame {
    public static Boolean flagNovaComanda = false;
    public static Caixa caixaAtual;
    public static Funcionario funcAtual;
    public static String user, password;
    public static String cargo = "Gerente";
    public static ArrayList<Categoria> categorias = new ArrayList<>();
    public static ArrayList<CategoriaPrato> categoriasPratos = new ArrayList<>();
    
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        CategoriaDAO cDao = new CategoriaDAO();
        CategoriaPratoDAO cpDao = new CategoriaPratoDAO();
        CaixaDAO caixaDao = new CaixaDAO();
        ComandaDAO comandaDao = new ComandaDAO();
        ItemComandaDAO itemDao = new ItemComandaDAO();
        LogDAO logDao = new LogDAO();
        ProdutoDAO pDao = new ProdutoDAO();
        
        for (Produto p: pDao.read()){
            listaProdutos.add(p);
        }
        
        for (Categoria c:cDao.read()){
            categorias.add(c);
        }
        
        for (CategoriaPrato c:cpDao.read()){
            categoriasPratos.add(c);
        }
        
        for (Caixa c:caixaDao.read()){
            if(c.getStatus() == 1){
                caixaAtual = c;
                break;
            }
        }

        for (Comanda c:comandaDao.read()){
            if(c.getStatus() == 1){
                GerenciadorComandas.comandasAbertas.add(c);
                GerenciadorComandas.numeroNovaComanda = c.getId();
                itemDao.read(c);
            }else{
                comandaDao.delete(c.getIdBanco());
            }
        }
        getRootPane().setDefaultButton(btnLogin);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblStringAlmanah = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Almanah System");
        setMinimumSize(new java.awt.Dimension(425, 226));
        setResizable(false);

        txtUsuario.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setText("Usuário");
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        txtSenha.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSenha.setText("testesenha");
        txtSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSenhaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSenhaFocusLost(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblStringAlmanah.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblStringAlmanah.setText("Almanah System");

        linha1.setBackground(new java.awt.Color(153, 153, 0));
        linha1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuario)
                            .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(lblStringAlmanah)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblStringAlmanah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed

    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusGained
        String nome;
        nome = txtUsuario.getText();
        if (nome.equals("Usuário")){
            txtUsuario.setText("");
        }
    }//GEN-LAST:event_txtUsuarioFocusGained

    private void txtSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenhaFocusGained
        String senha;
        senha = txtSenha.getText();
        if (senha.equals("testesenha")){
            txtSenha.setText("");
        }
    }//GEN-LAST:event_txtSenhaFocusGained

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        user = txtUsuario.getText();
        password = txtSenha.getText();
        funcAtual = new Funcionario();
        FuncionarioDAO fDao = new FuncionarioDAO();
        boolean logado = false;
 
        for (Funcionario f:fDao.readLogin(user)){
            if (f.getSenha().equals(password)){       
                funcAtual = f.clonarFuncionario(funcAtual); 
                
                new Menu().setVisible(true);
                dispose();
                logado = true;
                break;
            }
        }
        if (!logado){
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusLost
        String nome;
        nome = txtUsuario.getText();
        if (nome.equals("")){
            txtUsuario.setText("Usuário");
        }
    }//GEN-LAST:event_txtUsuarioFocusLost

    private void txtSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenhaFocusLost
        String senha;
        senha = txtSenha.getText();
        if (senha.equals("")){
            txtSenha.setText("testesenha");
        }
    }//GEN-LAST:event_txtSenhaFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblStringAlmanah;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
