/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dict.gui.swing;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dict.obj.DictionaryInterface;

/**
 *
 * @author Sabari
 */
public class LoginPreface extends javax.swing.JFrame {

    /**
     * Creates new form LoginPreface
     */
    public LoginPreface() {
        initComponents();
        admin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        DictionaryInterface di = new DictionaryInterface();
                        try{
                          di.login("admin", "password");  
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        new SearchWord(true,di);
                        dispose();                        
                    }
                }
        );
        user.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        DictionaryInterface di = new DictionaryInterface();
                        try{
                          di.login("admin", "password");  
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        new SearchWord(false,di);
                        dispose();
                    }
                }
        );
        
        setVisible(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        admin = new javax.swing.JButton();
        user = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User type");

        admin.setText("Admin");

        user.setText("User");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(admin, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(admin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(user)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton admin;
    private javax.swing.JButton user;
    // End of variables declaration//GEN-END:variables
}