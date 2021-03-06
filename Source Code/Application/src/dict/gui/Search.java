/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dict.gui;

/**
 *
 * @author Sabari
 */

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dict.obj.DictionaryManager;
import dict.obj.Word;

public class Search extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form Search
     */
    
    private DictionaryManager dm;
    private final String login = "LoginDialog";
    private final String logout = "Logout";
    
    private Search() {
        initComponents();
        addWordButton.setVisible(false);
        try{
            dm = new DictionaryManager();
            dm.start();
            addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent we){
                        try{
                            dm.logout();
                            dm.stop();
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            );
        }
        catch(Exception e){
            e.printStackTrace();
            warningLabel.setText(e.getMessage());
        }
        
        searchButton.addActionListener(this);
        accButton.addActionListener(this);
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        addWordButton.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        String cmd = ae.getActionCommand();
        warningLabel.setText("  ");
        switch(cmd){
            case "Search":
                searchButtonActionPerformed(ae);
                break;
            case "LoginDialog":
            case "Logout":
                accButtonActionPerformed(ae);
                break;
            case "Login":
                loginButtonActionPerformed(ae);
                break;
            case "Cancel":
                cancelButtonActionPerformed(ae);
                break;
            case "Add Word":
                addWordButtonActionPerformed(ae);
                break;
            default:
                warningLabel.setText("Something went wrong");
        }
    }
    
    private void searchButtonActionPerformed(ActionEvent ae){
        String searchWord = searchField.getText();
        resultArea.setText("");
        try{
            if(searchWord == null || searchWord.length() == 0){
                throw new Exception("Search text is empty");
            }
            Word word = dm.searchWord(searchWord);
            resultArea.setText(word.toString());
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void accButtonActionPerformed(ActionEvent ae){
        switch(accButton.getActionCommand()){
            case login:
                userField.setText("");
                passwordField.setText("");
                loginDialog.setVisible(true);
                break;
            case logout:
                try{
                    dm.logout();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                accButton.setText("Login");
                accButton.setActionCommand(login);
                addWordButton.setEnabled(false);
                addWordButton.setVisible(false);
                setTitle("Dictionary");
                break;
        }
    }

    private void loginButtonActionPerformed(ActionEvent ae){
        try{
            dm.setUser(userField.getText());
            String password = new String(passwordField.getPassword());
            dm.setPassword(password);
            dm.login();
            accButton.setText("Logout");
            accButton.setActionCommand(logout);
            addWordButton.setEnabled(true);
            addWordButton.setVisible(true);
            loginDialog.dispose();
            setTitle("Dictionary-Admin");
        }
        catch(Exception e){
            dialogWarningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void cancelButtonActionPerformed(ActionEvent ae){
        loginDialog.dispose();
    }
    
    private void addWordButtonActionPerformed(ActionEvent ae){
        setVisible(false);
        AddWord.init(this,dm);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginDialog = new javax.swing.JDialog(this,true);
        userLabel = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        dialogWarningLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultArea = new javax.swing.JTextArea();
        accButton = new javax.swing.JButton();
        addWordButton = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();

        loginDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        loginDialog.setTitle("Login");
        loginDialog.setResizable(false);

        userLabel.setText("Username");

        passwordLabel.setText("Password");

        dialogWarningLabel.setForeground(new java.awt.Color(240, 0, 0));
        dialogWarningLabel.setText("  ");

        loginButton.setText("Login");

        cancelButton.setText("Cancel");

        javax.swing.GroupLayout loginDialogLayout = new javax.swing.GroupLayout(loginDialog.getContentPane());
        loginDialog.getContentPane().setLayout(loginDialogLayout);
        loginDialogLayout.setHorizontalGroup(
            loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginDialogLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dialogWarningLabel)
                    .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(loginDialogLayout.createSequentialGroup()
                            .addComponent(cancelButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(loginButton))
                        .addGroup(loginDialogLayout.createSequentialGroup()
                            .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(passwordLabel)
                                .addComponent(userLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(userField)
                                .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        loginDialogLayout.setVerticalGroup(
            loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dialogWarningLabel)
                .addGap(5, 5, 5)
                .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel)
                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(cancelButton))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        loginDialog.pack();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dictionary");

        searchButton.setText("Search");

        resultArea.setEditable(false);
        resultArea.setColumns(20);
        resultArea.setRows(5);
        jScrollPane1.setViewportView(resultArea);

        accButton.setText("Login");
        accButton.setActionCommand("LoginDialog");

        addWordButton.setText("Add Word");
        addWordButton.setEnabled(false);

        warningLabel.setForeground(new java.awt.Color(240, 0, 0));
        warningLabel.setText("  ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(warningLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addWordButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(accButton))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchButton)))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(warningLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accButton)
                    .addComponent(addWordButton))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void init() {
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
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Search().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accButton;
    private javax.swing.JButton addWordButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel dialogWarningLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loginButton;
    private javax.swing.JDialog loginDialog;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextArea resultArea;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField userField;
    private javax.swing.JLabel userLabel;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables
}
