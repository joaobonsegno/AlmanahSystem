package main;

import ArrumarString.Monetarios;
import ArrumarString.SoNumeros;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import model.bean.AgendaDespesa;
import model.dao.LogDAO;
import model.bean.Despesa;
import model.dao.AgendaDespesaDAO;
import model.dao.DespesaDAO;

public class AlterarDespesa extends javax.swing.JDialog {
    private AgendaDespesa despesa;
    
    public AlterarDespesa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        txtValor.setDocument(new Monetarios(7,2));
        txtRepeticao.setDocument(new SoNumeros());
        getRootPane().setDefaultButton(btnConfirmar);
        LogDAO logDao = new LogDAO();
        txtDescricao.setWrapStyleWord(true);
        CriarDespesa.flagNovaDespesa = false;       
        
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            AgendaDespesaDAO a = new AgendaDespesaDAO();
            despesa = a.readForId(GerenciadorDespesa.idEscolhido);
            txtDescricao.setText(despesa.getDescricao());
            txtValor.setText(GerenciadorComandas.valorMonetario(despesa.getValor()));
            calendar.setDate(sdf.parse(despesa.getData()));
            this.setarRadios(despesa.getFrequencia());
        }catch(ParseException ex){}
    }

    public void setarRadios(String frequencia){
        if (frequencia.equals("Mensal")){
            radioMensal.setSelected(true);
            radioSemanal.setEnabled(false);
            radioParcela.setEnabled(false);
            txtRepeticao.setVisible(false);
            lblVezes.setVisible(false);
        }else if (frequencia.equals("Semanal")){
            radioSemanal.setSelected(true);
            radioMensal.setEnabled(false);
            radioParcela.setEnabled(false);
            txtRepeticao.setVisible(false);
            lblVezes.setVisible(false);
        }else{
            radioParcela.setSelected(true);
            radioSemanal.setEnabled(false);
            radioMensal.setEnabled(false);
            txtRepeticao.setText(Integer.toString(despesa.getQtdVezes()));
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo1 = new javax.swing.ButtonGroup();
        txtStringSuprimento = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblInsiraSuprimento = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        lblInsiraSuprimento1 = new javax.swing.JLabel();
        lblInsiraSuprimento4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        lblStringDia = new javax.swing.JLabel();
        calendar = new com.toedter.calendar.JDateChooser();
        lblStringDia1 = new javax.swing.JLabel();
        radioMensal = new javax.swing.JRadioButton();
        radioSemanal = new javax.swing.JRadioButton();
        radioParcela = new javax.swing.JRadioButton();
        txtRepeticao = new javax.swing.JTextField();
        lblVezes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Suprimento do Caixa");
        setMaximumSize(new java.awt.Dimension(496, 467));
        setMinimumSize(new java.awt.Dimension(496, 467));
        setResizable(false);

        txtStringSuprimento.setBackground(new java.awt.Color(0, 102, 204));
        txtStringSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        txtStringSuprimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtStringSuprimento.setText("Alterar Despesa");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        lblInsiraSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblInsiraSuprimento.setText("Valor da despesa:");

        txtValor.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        btnCancelar.setBackground(new java.awt.Color(255, 0, 51));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnConfirmar.setBackground(new java.awt.Color(0, 153, 51));
        btnConfirmar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setBorder(new javax.swing.border.MatteBorder(null));
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        lblInsiraSuprimento1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblInsiraSuprimento1.setText("R$");

        lblInsiraSuprimento4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblInsiraSuprimento4.setText("Descrição:");

        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        txtDescricao.setLineWrap(true);
        txtDescricao.setRows(3);
        txtDescricao.setTabSize(1);
        jScrollPane2.setViewportView(txtDescricao);

        lblStringDia.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStringDia.setText("Data:");

        calendar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        lblStringDia1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStringDia1.setText("Repetições:");

        grupo1.add(radioMensal);
        radioMensal.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        radioMensal.setText("Mensal");
        radioMensal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                radioMensalFocusGained(evt);
            }
        });

        grupo1.add(radioSemanal);
        radioSemanal.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        radioSemanal.setText("Semanal");
        radioSemanal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                radioSemanalFocusGained(evt);
            }
        });

        grupo1.add(radioParcela);
        radioParcela.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        radioParcela.setText("Parcela");
        radioParcela.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                radioParcelaFocusGained(evt);
            }
        });

        txtRepeticao.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        txtRepeticao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblVezes.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        lblVezes.setText("vezes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblInsiraSuprimento)
                    .addComponent(lblStringDia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInsiraSuprimento1))
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtStringSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblInsiraSuprimento4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringDia1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioSemanal)
                                    .addComponent(radioMensal)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(radioParcela)
                                        .addGap(24, 24, 24)
                                        .addComponent(txtRepeticao, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblVezes)))
                                .addGap(4, 4, 4)))
                        .addGap(45, 45, 45))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtStringSuprimento)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInsiraSuprimento1)
                    .addComponent(lblInsiraSuprimento))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblStringDia)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStringDia1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioSemanal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioMensal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtRepeticao)
                                    .addComponent(lblVezes, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInsiraSuprimento4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(423, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        boolean flagValorOuRepeticao = false;
        try{   
            boolean flagParcelas = false;
            if (txtRepeticao.getText().equals("0")){
                flagParcelas = true;
                JOptionPane.showMessageDialog(null, "A quantidade de parcelas deve ser maior do que zero (0)\n\nDICA: caso queira excluir esta despesa, faça-o na aba do Gerenciador de Despesas");
            }
            
            if (!flagParcelas){
                //Instancia os objetos de manipulação de datas
                SimpleDateFormat formatoBr = new SimpleDateFormat("dd/MM/yyyy");
                Calendar atual = new GregorianCalendar();
                Calendar calendario = Calendar.getInstance();
                Date dataSelecionada, dataAtual;
                boolean dataSelecionadaMenor = false;

                //Instancia a data atual e a data selecionada, para compará-las
                String data = formatoBr.format(calendar.getDate());
                dataSelecionada = formatoBr.parse(data);
                dataAtual = formatoBr.parse(GerenciadorComandas.getDataAtualSemHoraFormatoBr());
                int resultado = dataSelecionada.compareTo(dataAtual);
                if (resultado == -1){ // DATA SELECIONADA é menor do que a DATA ATUAL
                    dataSelecionadaMenor = true;              
                    JOptionPane.showMessageDialog(null, "A data selecionada deve ser maior ou igual à data atual");
                }
                
                if (!dataSelecionadaMenor){
                    //Seta os atributos da agenda
                    AgendaDespesaDAO dDao = new AgendaDespesaDAO();
                    String valor = txtValor.getText();
                    valor = valor.replace("." , "");
                    valor = valor.replace("," , ".");
 
                    despesa.setDescricao(txtDescricao.getText());
                    despesa.setValor(Double.parseDouble(valor));           

                    if (despesa.getQtdVezes() > 0){                       
                        flagValorOuRepeticao = true;
                        despesa.setQtdVezes(Integer.parseInt(txtRepeticao.getText()));                       
                    }                                  

                    despesa.setData(formatoBr.format(dataSelecionada));
                    
                    
                    despesa.setId(GerenciadorDespesa.idEscolhido);
                    dDao.update(despesa);                      
                    CriarDespesa.flagNovaDespesa = true;
                    dispose();                
                }
            }
            
        }catch(java.lang.NumberFormatException ex){
            if (flagValorOuRepeticao)
                JOptionPane.showMessageDialog(null, "Insira a quantidade de vezes que a despesa deverá ser cobrada");
            else
                JOptionPane.showMessageDialog(null, "Insira o valor da despesa");
        }catch(java.lang.NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Informe a data da despesa");
        }catch(ParseException ex){
            System.err.println("Erro: "+ex);
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void radioSemanalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_radioSemanalFocusGained
        txtRepeticao.setVisible(false);
        lblVezes.setVisible(false);
                
    }//GEN-LAST:event_radioSemanalFocusGained

    private void radioMensalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_radioMensalFocusGained
        txtRepeticao.setVisible(false);
        lblVezes.setVisible(false);
    }//GEN-LAST:event_radioMensalFocusGained

    private void radioParcelaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_radioParcelaFocusGained
        txtRepeticao.setVisible(true);
        lblVezes.setVisible(true);
    }//GEN-LAST:event_radioParcelaFocusGained

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Despesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Despesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Despesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Despesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AlterarDespesa dialog = new AlterarDespesa(new javax.swing.JFrame(), true);
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
    private com.toedter.calendar.JDateChooser calendar;
    private javax.swing.ButtonGroup grupo1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblInsiraSuprimento;
    private javax.swing.JLabel lblInsiraSuprimento1;
    private javax.swing.JLabel lblInsiraSuprimento4;
    private javax.swing.JLabel lblStringDia;
    private javax.swing.JLabel lblStringDia1;
    private javax.swing.JLabel lblVezes;
    private javax.swing.Box.Filler linha;
    private javax.swing.JRadioButton radioMensal;
    private javax.swing.JRadioButton radioParcela;
    private javax.swing.JRadioButton radioSemanal;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtRepeticao;
    private javax.swing.JLabel txtStringSuprimento;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
