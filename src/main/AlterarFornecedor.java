package main;

import ArrumarString.SoNumeros;
import javax.swing.JOptionPane;
import model.bean.Estado;
import model.bean.Fornecedor;
import model.dao.EstadoDAO;
import model.dao.FornecedorDAO;

public class AlterarFornecedor extends javax.swing.JFrame {

    public AlterarFornecedor() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtNumero.setDocument(new SoNumeros());
        criarCb();
        getRootPane().setDefaultButton(btnConfirmar);
        
        // Setar informações nos TXTs
        Fornecedor f = GerenciadorFornecedores.fornecedorSelecionado;
        txtNome.setText(f.getNome());
        txtCnpj.setText(f.getCnpj());
        txtTelefone.setText(f.getTelefone());
        txtEmail.setText(f.getEmail());
        txtCelular.setText(f.getCelular());
        txtLogradouro.setText(f.getLogradouro());
        txtBairro.setText(f.getBairro());
        txtComplemento.setText(f.getComplemento());
        txtCidade.setText(f.getCidade());
        txtNumero.setText(Integer.toString(f.getNumero()));
        txtCep.setText(f.getCep());
        
        EstadoDAO eDao = new EstadoDAO();
        int contador = 0;
        for (Estado e : eDao.read()){
            if (e.getNome().equals(f.getEstado().getNome())){
                contador += 1;
                break;
            }
            contador += 1;
        }
        cbUf.setSelectedIndex(contador);
        
    }

    public void criarCb() {
        cbUf.removeAllItems();
        cbUf.addItem("");
        EstadoDAO eDao = new EstadoDAO();
        for (Estado e : eDao.read()) {
            cbUf.addItem(e.getNome());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStringNovoFunc = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        jPanel1 = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        lblStringNome = new javax.swing.JLabel();
        lblStringEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblStringContatoUm = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        lblStringContatoDois = new javax.swing.JLabel();
        txtCelular = new javax.swing.JFormattedTextField();
        lblStringCpf = new javax.swing.JLabel();
        txtCnpj = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        lblStringLogradouro = new javax.swing.JLabel();
        txtLogradouro = new javax.swing.JTextField();
        lblStringBairro = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        lblStringCidade = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        lblStringComplemento = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        lblStringNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblStringCep = new javax.swing.JLabel();
        txtCep = new javax.swing.JFormattedTextField();
        lblStringUf = new javax.swing.JLabel();
        cbUf = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Alterar Fornecedor");
        setMaximizedBounds(new java.awt.Rectangle(1039, 536, 536, 536));
        setMaximumSize(new java.awt.Dimension(1039, 589));
        setMinimumSize(new java.awt.Dimension(1039, 589));
        setResizable(false);

        lblStringNovoFunc.setBackground(new java.awt.Color(0, 102, 204));
        lblStringNovoFunc.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblStringNovoFunc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringNovoFunc.setText("Alterar Fornecedor");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        btnConfirmar.setBackground(new java.awt.Color(0, 153, 0));
        btnConfirmar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnConfirmar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\confirm.png")); // NOI18N
        btnConfirmar.setText("  Confirmar");
        btnConfirmar.setBorder(new javax.swing.border.MatteBorder(null));
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(204, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\cancel.png")); // NOI18N
        btnCancelar.setText("  Cancelar");
        btnCancelar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        txtNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNome.setText("*Nome:");

        lblStringEmail.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringEmail.setText("*Email:");

        txtEmail.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringContatoUm.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringContatoUm.setText("Telefone:");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringContatoDois.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringContatoDois.setText("*Celular:");

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelular.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringCpf.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringCpf.setText("*CNPJ:");

        try {
            txtCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCnpj.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lblStringEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblStringContatoUm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(lblStringContatoDois)
                        .addGap(18, 18, 18)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblStringNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblStringCpf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCnpj))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCnpj, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStringNome)
                        .addComponent(lblStringCpf)))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringEmail))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringContatoDois)
                    .addComponent(lblStringContatoUm))
                .addGap(14, 14, 14))
        );

        lblStringLogradouro.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringLogradouro.setText("*Logradouro:");

        txtLogradouro.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringBairro.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringBairro.setText("*Bairro:");

        txtBairro.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringCidade.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringCidade.setText("*Cidade:");

        txtComplemento.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringComplemento.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringComplemento.setText("Complemento:");

        txtCidade.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringNumero.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNumero.setText("*Número:");

        txtNumero.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        lblStringCep.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringCep.setText("*CEP:");

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCep.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringUf.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringUf.setText("*UF:");

        cbUf.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbUf.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbUfItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblStringCep)
                    .addComponent(lblStringBairro)
                    .addComponent(lblStringLogradouro)
                    .addComponent(lblStringComplemento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblStringUf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblStringNumero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero))
                    .addComponent(txtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblStringCidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringLogradouro)
                    .addComponent(txtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringBairro)
                    .addComponent(lblStringCidade)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringComplemento)
                    .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringNumero)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringCep)
                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringUf)
                    .addComponent(cbUf, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1039, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 1039, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(279, 279, 279)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(397, 397, 397)
                .addComponent(lblStringNovoFunc))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(lblStringNovoFunc)
                .addGap(6, 6, 6)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        boolean camposObrigatorios = true;        
        FornecedorDAO fDao = new FornecedorDAO();
        EstadoDAO eDao = new EstadoDAO();
        
        // Variáveis que são de preenchimento obrigatório
        String nome = txtNome.getText();
        String cnpj = txtCnpj.getText();
        String email = txtEmail.getText();
        String celular = txtCelular.getText();
        String logradouro = txtLogradouro.getText();
        String bairro = txtBairro.getText();
        String cidade = txtCidade.getText();
        String numero = txtNumero.getText();
        String cep = txtCep.getText();
        String uf = (String) cbUf.getSelectedItem();

        // Verifica se as variáveis de preenchimento obrigatória estão ok
        if (nome.equals("") || cnpj.equals("") || email.equals("") || celular.equals("") || logradouro.equals("") | 
        bairro.equals("") || cidade.equals("") || numero.equals("") || cep.equals("") || uf.equals("")) {            
            camposObrigatorios = false;
        }
        
        // Se os campos obrigatórios foram preenchidos, o cadastro pode seguir
        if (camposObrigatorios){
                // Instancia o objeto ESTADO, pertencente ao Fornecedor
                Estado estado = new Estado();
                for (Estado e : eDao.read()) {
                    if (e.getNome().equals(uf)) {
                        estado.setId(e.getId());
                        estado.setNome(uf);
                    }
                }

                // Instancia o Fornecedor e seta todos os seus atributos
                Fornecedor f = new Fornecedor();
                f.setNome(nome);
                f.setCnpj(cnpj);
                f.setEmail(email);
                f.setTelefone(txtTelefone.getText());
                f.setCelular(celular);
                f.setLogradouro(logradouro);
                f.setBairro(bairro);
                f.setNumero(Integer.parseInt(numero));
                f.setCidade(cidade);
                f.setCep(cep);
                f.setComplemento(txtCidade.getText());
                f.setEstado(estado);

                // Cria o fornecedor no banco de dados
                fDao.create(f);
                new GerenciadorFornecedores().setVisible(true);
                dispose();
        }else {            
            JOptionPane.showMessageDialog(null, "Erro no cadastro: campos obrigatórios não foram preenchidos.\nPreencha todos os campos que possuem asterisco (*).");
        }


    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        new GerenciadorFornecedores().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void cbUfItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbUfItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbUfItemStateChanged

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
                if ("WebLaF".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AlterarFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlterarFornecedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private static javax.swing.JComboBox<String> cbUf;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblStringBairro;
    private javax.swing.JLabel lblStringCep;
    private javax.swing.JLabel lblStringCidade;
    private javax.swing.JLabel lblStringComplemento;
    private javax.swing.JLabel lblStringContatoDois;
    private javax.swing.JLabel lblStringContatoUm;
    private javax.swing.JLabel lblStringCpf;
    private javax.swing.JLabel lblStringEmail;
    private javax.swing.JLabel lblStringLogradouro;
    private javax.swing.JLabel lblStringNome;
    private javax.swing.JLabel lblStringNovoFunc;
    private javax.swing.JLabel lblStringNumero;
    private javax.swing.JLabel lblStringUf;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLogradouro;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
