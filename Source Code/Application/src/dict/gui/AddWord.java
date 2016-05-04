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

import dict.obj.DictionaryManager;
import dict.obj.Word;
import dict.obj.ExplanationUnit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;

public class AddWord extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form AddWord
     */
    
    private Search parent;
    private DictionaryManager dm;
    private Word word;
    private DefaultListModel euListModel;
    private boolean editFlag;
    
    private AddWord(Search parent, DictionaryManager dm) {
        initComponents();
        this.parent = parent;
        this.dm = dm;
        word = new Word();
        addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosed(WindowEvent we){                        
                        parent.setVisible(true);
                    }
                }
        );
        
        addButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);
        previewButton.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        String cmd = ae.getActionCommand();
        warningLabel.setText("  ");
        switch(cmd){
            case "Add":
                addButtonActionPerformed(ae);
                break;
            case "Edit":
                editButtonActionPerformed(ae);
                break;
            case "Delete":
                deleteButtonActionPerformed(ae);
                break;
            case "Validate":
                validateButtonActionPerformed(ae);
                break;
            case "Submit":
                submitButtonActionPerformed(ae);
                break;
            case "Cancel":
                cancelButtonActionPerformed(ae);
                break;
            case "Preview":
                previewButtonActionPerformed(ae);
                break;
            default:
                warningLabel.setText("Somthing Went wrong :)");
        }
            
    }
    
    private void addButtonActionPerformed(ActionEvent ae){
        editFlag = false;
        setVisible(false);
        AddExplanationUnit.init(this);
        
    }
    
    private void editButtonActionPerformed(ActionEvent ae){
        try{
            editFlag = true;
            int index = explanationList.getSelectedIndex();
            if(index == -1){
                throw new Exception("Select item to edit");
            }            
            setVisible(false);
            AddExplanationUnit.init(this,new ExplanationUnit(word.getExplanationUnitList().get(index)));
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void deleteButtonActionPerformed(ActionEvent ae){
        try{
            int index = explanationList.getSelectedIndex();
            if(index == -1){
                throw new Exception("Select item to delete");
            }
            word.getExplanationUnitList().remove(index);       
            euListModel.removeElementAt(index);
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void validateButtonActionPerformed(ActionEvent ae){
        try{
            word.setWord(wordField.getText().trim());
            word.setSyllable(syllableField.getText().trim());
            dm.validateWord(word);
            submitButton.setText("Submit");
            submitButton.setActionCommand("Submit");            
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void submitButtonActionPerformed(ActionEvent ae){
        try{
            dm.submitWordToDictionary();
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            Preview.init("Word",word.toString());            
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void previewButtonActionPerformed(ActionEvent ae){        
        try{
            word.setWord(wordField.getText().trim());
            word.setSyllable(syllableField.getText().trim());
            Preview.init("Word",word.toString());
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void cancelButtonActionPerformed(ActionEvent ae){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    
    public void addExplanationUnit(ExplanationUnit eu) throws Exception{
        int index;
        if(editFlag){
            index = explanationList.getSelectedIndex();
            if(index == -1)
                throw new Exception("Something went wrong");
            euListModel.remove(index);
            word.getExplanationUnitList().remove(index);
            word.addExplanationUnit(index, eu);
            euListModel.add(index, eu.getPartOfSpeech().getName());
        }
        else{
            word.addExplanationUnit(eu);
            euListModel.addElement(eu.getPartOfSpeech().getName());
            index = euListModel.size();
            index--;
        }
        
        explanationList.setSelectedIndex(index);
        explanationList.ensureIndexIsVisible(index);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wordLabel = new javax.swing.JLabel();
        syllableLabel = new javax.swing.JLabel();
        wordField = new javax.swing.JTextField();
        syllableField = new javax.swing.JTextField();
        explanationLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        euListModel = new DefaultListModel<String>();
        explanationList = new javax.swing.JList<String>(euListModel);
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();
        previewButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Word");
        setResizable(false);

        wordLabel.setText("Word");

        syllableLabel.setText("Syllable");

        explanationLabel.setText("Explanation");

        jScrollPane1.setViewportView(explanationList);

        addButton.setText("Add");

        editButton.setText("Edit");

        deleteButton.setText("Delete");

        submitButton.setText("Validate");

        cancelButton.setText("Cancel");

        warningLabel.setForeground(new java.awt.Color(240, 0, 0));
        warningLabel.setText("  ");

        previewButton.setText("Preview");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wordLabel)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(explanationLabel)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(syllableLabel)
                                    .addGap(284, 284, 284))
                                .addComponent(syllableField)
                                .addComponent(wordField)
                                .addComponent(warningLabel)))
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(previewButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(submitButton)
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(wordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(syllableLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(syllableField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(explanationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warningLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previewButton)
                    .addComponent(cancelButton)
                    .addComponent(submitButton))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void init(Search parent, DictionaryManager dm) {
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
            java.util.logging.Logger.getLogger(AddWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddWord(parent, dm).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel explanationLabel;
    private javax.swing.JList<String> explanationList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton previewButton;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField syllableField;
    private javax.swing.JLabel syllableLabel;
    private javax.swing.JLabel warningLabel;
    private javax.swing.JTextField wordField;
    private javax.swing.JLabel wordLabel;
    // End of variables declaration//GEN-END:variables
}
