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

import dict.obj.Meaning;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddMeaning extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form AddMeaning
     */
    
    private MeaningInterface parent;
    private Meaning meaning;
    private javax.swing.DefaultListModel<String> exampleListModel;
    private boolean editFlag;
    
    private AddMeaning(MeaningInterface parent){
        this.parent = parent;
        meaning = new Meaning();
        setTitle("Add Meaning");
    }
    
    private AddMeaning(MeaningInterface parent, Meaning meaning) {
        this.parent = parent;
        this.meaning = meaning;
        meaningField.setText(meaning.getMeaning());
        setTitle("Edit Meaning");
        for(String str : meaning.getExampleList()){
            exampleListModel.addElement(str);
        }
    }
        
    
    @Override
    public void actionPerformed(ActionEvent ae){
        warningLabel.setText(" ");
        switch(ae.getActionCommand()){
            case "Add":
                addButtonActionPerformed(ae);
                break;
            case "Edit":
                editButtonActionPerformed(ae);
                break;
            case "Delete":
                deleteButtonActionPerformed(ae);
                break;
            case "Preview":
                previewButtonActionPerformed(ae);
                break;
            case "Cancel":
                cancelButtonActionPerformed(ae);
                break;
            case "Save":
                saveButtonActionPerformed(ae);
                break;
            case "ExampleSave":
                dSaveButtonActionPerformed(ae);
                break;
            case "ExampleCancel":
                dCancelButtonActionPerformed(ae);
                break;
            default:
                warningLabel.setText("Somthing went wrong");
        }
    }
    
    private void addButtonActionPerformed(ActionEvent ae){
        editFlag = false;
        exampleField.setText("");
        exampleDialog.setTitle("Add Example");
        exampleDialog.setVisible(true);
    }
    
    private void editButtonActionPerformed(ActionEvent ae){
        editFlag = true;
        int index = exampleList.getSelectedIndex();
        try{
            if(index == -1){
                throw new Exception("Select example to edit");
            }                
            exampleField.setText(meaning.getExampleList().get(index));
            exampleDialog.setTitle("Edit Example");
            exampleDialog.setVisible(true);
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
        
        
    }
    
    private void deleteButtonActionPerformed(ActionEvent ae){
        try{
            int index = exampleList.getSelectedIndex();
            if(index == -1)
                throw new Exception("Select example to delete");
            meaning.getExampleList().remove(index);
            exampleListModel.removeElementAt(index);
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void previewButtonActionPerformed(ActionEvent ae){
        try{
            meaning.setMeaning(meaningField.getText().trim());
            Preview.init("Meaning", meaning.toString());            
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void cancelButtonActionPerformed(ActionEvent ae){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    
    private void saveButtonActionPerformed(ActionEvent ae){
        try{
            String str = meaningField.getText();
            if(str == null){
                throw new Exception("Meaning cannot be empty");
            }
            str = str.trim();
            if(str.length() == 0 || str.equals("")){
                throw new Exception("Meaning cannot be empty");
            }
            meaning.setMeaning(str);
            parent.addMeaning(meaning); 
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));                       
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void dSaveButtonActionPerformed(ActionEvent ae){
        String example = exampleField.getText();        
        try{
            if(example == null){
                throw new Exception("Example cannot be empty");
            }
            example = example.trim();
            if(example.length() == 0 || example.equals("")){
                throw new Exception("Example cannot be empty");
            }
            addExample(example);
            exampleDialog.dispatchEvent(new WindowEvent(exampleDialog, WindowEvent.WINDOW_CLOSING));
        }
        catch(Exception e){
            dWarningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void dCancelButtonActionPerformed(ActionEvent ae){
        dispatchEvent(new WindowEvent(exampleDialog, WindowEvent.WINDOW_CLOSING));
    }
    
    private void addExample(String example) throws Exception{
        int index;
        if(editFlag){
            index = exampleList.getSelectedIndex();
            if(index == -1)
                throw new Exception("Somthing went wrong");
            meaning.getExampleList().remove(index);
            meaning.addExample(index, example);
            exampleListModel.remove(index);
            exampleListModel.add(index, example);
        }
        else{
            meaning.addExample(example);
            exampleListModel.addElement(example);
            index = exampleListModel.getSize();
            index--;
        }
        exampleList.setSelectedIndex(index);
        exampleList.ensureIndexIsVisible(index);        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exampleDialog = new javax.swing.JDialog(this,true);
        dExampleLabel = new javax.swing.JLabel();
        exampleField = new javax.swing.JTextField();
        dSaveButton = new javax.swing.JButton();
        dCancelButton = new javax.swing.JButton();
        dWarningLabel = new javax.swing.JLabel();
        meaningLabel = new javax.swing.JLabel();
        meaningField = new javax.swing.JTextField();
        exampleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        exampleListModel = new javax.swing.DefaultListModel<String>();
        exampleList = new javax.swing.JList(exampleListModel);
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        previewButton = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();

        exampleDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        exampleDialog.setResizable(false);

        dExampleLabel.setText("Example");

        dSaveButton.setText("Save");
        dSaveButton.setActionCommand("ExampleSave");

        dCancelButton.setText("Cancel");
        dCancelButton.setActionCommand("ExampleCancel");

        dWarningLabel.setForeground(new java.awt.Color(240, 0, 0));
        dWarningLabel.setText("  ");

        javax.swing.GroupLayout exampleDialogLayout = new javax.swing.GroupLayout(exampleDialog.getContentPane());
        exampleDialog.getContentPane().setLayout(exampleDialogLayout);
        exampleDialogLayout.setHorizontalGroup(
            exampleDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exampleDialogLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(exampleDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(exampleDialogLayout.createSequentialGroup()
                        .addComponent(dWarningLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(exampleDialogLayout.createSequentialGroup()
                        .addGroup(exampleDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(exampleDialogLayout.createSequentialGroup()
                                .addComponent(dCancelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(exampleDialogLayout.createSequentialGroup()
                                .addComponent(dExampleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exampleField, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))))
        );
        exampleDialogLayout.setVerticalGroup(
            exampleDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exampleDialogLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(exampleDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dExampleLabel)
                    .addComponent(exampleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dWarningLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(exampleDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dSaveButton)
                    .addComponent(dCancelButton))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        exampleDialog.pack();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        meaningLabel.setText("Meaning");

        exampleLabel.setText("Example");

        exampleList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(exampleList);

        addButton.setText("Add");

        editButton.setText("Edit");

        deleteButton.setText("Delete");

        saveButton.setText("Save");

        cancelButton.setText("Cancel");

        previewButton.setText("Preview");

        warningLabel.setForeground(new java.awt.Color(240, 0, 0));
        warningLabel.setText("  ");
        warningLabel.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warningLabel)
                    .addComponent(exampleLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(meaningLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(meaningField, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(previewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meaningLabel)
                    .addComponent(meaningField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exampleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warningLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton)
                    .addComponent(previewButton))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    private static void setLookandFeel() {
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
            java.util.logging.Logger.getLogger(AddMeaning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddMeaning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddMeaning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddMeaning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>       
        
    }
    
    public static void init(MeaningInterface parent){
        setLookandFeel();
         /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddMeaning(parent).setVisible(true);
            }
        });
    }
    
    public static void init(MeaningInterface parent, Meaning meaning){
        setLookandFeel();
         /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddMeaning(parent,meaning).setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton dCancelButton;
    private javax.swing.JLabel dExampleLabel;
    private javax.swing.JButton dSaveButton;
    private javax.swing.JLabel dWarningLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JDialog exampleDialog;
    private javax.swing.JTextField exampleField;
    private javax.swing.JLabel exampleLabel;
    private javax.swing.JList exampleList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField meaningField;
    private javax.swing.JLabel meaningLabel;
    private javax.swing.JButton previewButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables

    {
        //this.parent = parent;
        initComponents();
        addButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        
        saveButton.addActionListener(this);        
        cancelButton.addActionListener(this);        
        previewButton.addActionListener(this);               
       
        dSaveButton.addActionListener(this);        
        dCancelButton.addActionListener(this);
        
        addWindowListener(
            new WindowAdapter(){
                @Override
                public void windowClosed(WindowEvent we){
                    ((javax.swing.JFrame)parent).setVisible(true);
                }
            }
        ); 
        
    }
}
