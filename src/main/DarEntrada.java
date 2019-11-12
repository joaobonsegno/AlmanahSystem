package main;

import ArrumarString.SoNumeros;
import javax.swing.JOptionPane;
import model.bean.Alerta;
import model.bean.Log;
import model.bean.Produto;
import model.dao.AlertaDAO;
import model.dao.LogDAO;
import model.dao.ProdutoDAO;

public class DarEntrada extends javax.swing.JDialog {
    private static Integer idSelecionado;
    ProdutoDAO pDao = new ProdutoDAO();
    //ArrayList<Produto> prods = new ArrayList<>();
    private Produto prod;
    
    public DarEntrada(java.awt.Frame parent, boolean modal, Integer id) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnConfirmar);
        idSelecionado = id;
        encontraProd();
        if (!prod.getUnidadeDeMedida().equals("Kg")){
            txtEntrada.setDocument(new SoNumeros());
        } 
    }
    
    private void encontraProd(){
        for(Produto p:pDao.read()){
            if(p.getIdProduto().equals(idSelecionado)){
                prod = p;
            }
        }
        arrumarLabels();
    }
    
    public void arrumarLabels(){
        lblId.setText(" "+Integer.toString(prod.getIdProduto()));
        lblNome.setText(" "+prod.getNome());
        if (!prod.getUnidadeDeMedida().equals("Unidade")){
            lblUnidade.setText(prod.getUnidadeDeMedida());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo1 = new javax.swing.ButtonGroup();
        lblStringNovoPrato = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblUnidade = new javax.swing.JLabel();
        txtEntrada = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        lblStringId = new javax.swing.JLabel();
        lblStringNome = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblStringQuantidade1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Entrada no Estoque");
        setMaximumSize(new java.awt.Dimension(487, 285));
        setMinimumSize(new java.awt.Dimension(487, 285));
        setResizable(false);

        lblStringNovoPrato.setBackground(new java.awt.Color(0, 102, 204));
        lblStringNovoPrato.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lblStringNovoPrato.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringNovoPrato.setText("Dar Entrada");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        lblUnidade.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblUnidade.setText("unid.");

        txtEntrada.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntradaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEntradaKeyTyped(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(204, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\cancel.png")); // NOI18N
        btnCancelar.setText(" Cancelar");
        btnCancelar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnConfirmar.setBackground(new java.awt.Color(0, 153, 0));
        btnConfirmar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnConfirmar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\confirm.png")); // NOI18N
        btnConfirmar.setText(" Confirmar");
        btnConfirmar.setBorder(new javax.swing.border.MatteBorder(null));
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        lblStringId.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblStringId.setText("ID:");

        lblStringNome.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblStringNome.setText("Nome:");

        lblId.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblId.setText("ID:");
        lblId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNome.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblNome.setText("ID:");
        lblNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        lblStringQuantidade1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblStringQuantidade1.setText("Quantidade a ser adicionada:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblStringQuantidade1)
                .addGap(10, 10, 10)
                .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUnidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(lblStringNovoPrato, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblStringId)
                        .addGap(6, 6, 6)
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblStringNome)
                        .addGap(10, 10, 10)
                        .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblStringNovoPrato)
                .addGap(7, 7, 7)
                .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblId)
                    .addComponent(lblNome)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStringId)
                            .addComponent(lblStringNome))))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblStringQuantidade1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblUnidade)))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        new GerenciadorEstoque().setVisible(true);
        dispose();   
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        try{
            boolean excluirAlerta = false;
            LogDAO logDao = new LogDAO();
            Log l = new Log();
            l.setCategoria("Estoque");
            l.setData(l.dataAtual());

            String qtdAtualS = prod.getQtdEstoque();
            String qtdAdicionadaS = txtEntrada.getText();
            l.setDescricao(Login.funcAtual.getNome()+" entrou "+qtdAdicionadaS+" de \""+prod.getNome()+"\" no estoque");

            if (prod.getUnidadeDeMedida().equals("Kg")){
                qtdAdicionadaS = qtdAdicionadaS.replace(",", ".");
                Double qtdAtual = Double.parseDouble(qtdAtualS);
                Double qtdAdicionada = Double.parseDouble(qtdAdicionadaS);
                qtdAtual += qtdAdicionada;
                if (qtdAtual > Double.parseDouble(prod.getQtdMinima())){
                    excluirAlerta = true;
                }
                qtdAtualS = Double.toString(qtdAtual);
            }else{
                Integer qtdAtual = Integer.parseInt(qtdAtualS);
                Integer qtdAdicionada = Integer.parseInt(qtdAdicionadaS);
                qtdAtual += qtdAdicionada;
                if (qtdAtual > Integer.parseInt(prod.getQtdMinima())){
                    excluirAlerta = true;
                }
                qtdAtualS = Integer.toString(qtdAtual);
            }
            
            if (excluirAlerta){
                AlertaDAO aDao = new AlertaDAO();
                aDao.delete(prod.getIdProduto());
            }
            prod.setQtdEstoque(qtdAtualS);
            pDao.updateEstoque(prod);

            logDao.create(l);
            new GerenciadorEstoque().setVisible(true);
            dispose();
        }catch(java.lang.NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Insira um valor válido");
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void txtEntradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaKeyReleased
        
        
    }//GEN-LAST:event_txtEntradaKeyReleased

    private void txtEntradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaKeyTyped
        Character c = evt.getKeyChar();
        if (c.isSpaceChar(c) || c.isAlphabetic(c)){
            evt.consume();
        }
    }//GEN-LAST:event_txtEntradaKeyTyped


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
            java.util.logging.Logger.getLogger(DarEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DarEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DarEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DarEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DarEntrada dialog = new DarEntrada(new javax.swing.JFrame(), true, idSelecionado);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.ButtonGroup grupo1;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblStringId;
    private javax.swing.JLabel lblStringNome;
    private javax.swing.JLabel lblStringNovoPrato;
    private javax.swing.JLabel lblStringQuantidade1;
    private javax.swing.JLabel lblUnidade;
    private javax.swing.Box.Filler linha;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JTextField txtEntrada;
    // End of variables declaration//GEN-END:variables
}
