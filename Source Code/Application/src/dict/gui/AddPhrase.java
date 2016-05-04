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
import dict.obj.Phrase;
import dict.obj.Meaning;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddPhrase extends javax.swing.JFrame implements MeaningInterface, ActionListener{

    /**
     * Creates new form AddPhrase
     */
    private AddExplanationUnit parent;
    private Phrase ph;
    private String type;
    private boolean editFlag;
    private javax.swing.DefaultListModel<String> meaningListModel;
    
    private AddPhrase(AddExplanationUnit parent, String type){
        this.parent = parent;
        this.type = type;
        ph = new Phrase();
        setTitle("Add "+type);
    }
          
    private AddPhrase(AddExplanationUnit parent, String type, Phrase ph){
        this.parent = parent;
        this.type = type;
        this.ph = ph;     
        phraseField.setText(ph.getForm());
        setTitle("Edit "+type);
        List<Meaning> mList = ph.getMeaningList();
        for(Meaning meaning : mList){
            meaningListModel.addElement(meaning.getMeaning());
        }
    }
            
    @Override
    public void actionPerformed(ActionEvent ae){
        warningLabel.setText("  ");
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
            default:
                warningLabel.setText("Somthing went wrong");
        }
    }
    
    private void addButtonActionPerformed(ActionEvent ae){
        editFlag = false;
        setVisible(false);
        AddMeaning.init(this);        
    }
    
    private void editButtonActionPerformed(ActionEvent ae){
        editFlag = true;
        int index = meaningList.getSelectedIndex();
        try{
            if(index == -1){
                throw new Exception("Select meaning to edit");
            }
            AddMeaning.init(this,new Meaning(ph.getMeaningList().get(index)));
            setVisible(false);
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void deleteButtonActionPerformed(ActionEvent ae){
        int index = meaningList.getSelectedIndex();
        try{
            if(index == -1)
                throw new Exception("Select meaning to delete");
            ph.getMeaningList().remove(index);
            meaningListModel.remove(index);
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void saveButtonActionPerformed(ActionEvent ae){
        String phrase = phraseField.getText();
        try{
            if(phrase == null){
                throw new Exception(type+" form cannot be empty");            
            }                        
            phrase = phrase.trim();
            if(phrase.length() == 0 || phrase.equals("")){
                throw new Exception(type+" form cannot be empty");            
            }                        
            ph.setForm(phrase);    
            parent.addPhrase(ph);           
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    private void cancelButtonActionPerformed(ActionEvent ae){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    
    private void previewButtonActionPerformed(ActionEvent ae){
        try{
            ph.setForm(phraseField.getText().trim());
            Preview.init(type,ph.toString());
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }        
    
    @Override
    public void addMeaning(Meaning meaning) throws Exception{
        int index;
        if(editFlag){
            index = meaningList.getSelectedIndex();
            if(index == -1){
                throw new Exception("Somthing went wrong");
            }
            ph.getMeaningList().remove(index);
            ph.addMeaning(index, meaning);
            meaningListModel.remove(index);
            meaningListModel.add(index, meaning.getMeaning());
        }
        else{
            ph.addMeaning(meaning);
            meaningListModel.addElement(meaning.getMeaning());
            index = meaningListModel.getSize();
            index--;
        }
        meaningList.setSelectedIndex(index);
        meaningList.ensureIndexIsVisible(index);
    }        
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        phraseLabel = new javax.swing.JLabel();
        phraseField = new javax.swing.JTextField();
        meaningLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        meaningListModel = new javax.swing.DefaultListModel<String>();
        meaningList = new javax.swing.JList<String>(meaningListModel);
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        previewButton = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        phraseLabel.setText("Form");

        meaningLabel.setText("Meaning");

        meaningList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(meaningList);

        addButton.setText("Add");

        editButton.setText("Edit");

        deleteButton.setText("Delete");

        saveButton.setText("Save");

        cancelButton.setText("Cancel");

        previewButton.setText("Preview");

        warningLabel.setForeground(new java.awt.Color(240, 0, 0));
        warningLabel.setText("  ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warningLabel)
                    .addComponent(meaningLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(phraseLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(phraseField))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(previewButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phraseLabel)
                    .addComponent(phraseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meaningLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
    private static void setLookAndFeel() {
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
            java.util.logging.Logger.getLogger(AddPhrase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPhrase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPhrase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPhrase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

                
    }
    
    public static void init(AddExplanationUnit parent, String type){
        setLookAndFeel();
        /* Create and display the form */        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddPhrase(parent,type).setVisible(true);
            }
        });
    }
    
    public static void init(AddExplanationUnit parent, String type, Phrase ph){
        setLookAndFeel();
        /* Create and display the form */        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddPhrase(parent, type, ph).setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel meaningLabel;
    private javax.swing.JList<String> meaningList;
    private javax.swing.JTextField phraseField;
    private javax.swing.JLabel phraseLabel;
    private javax.swing.JButton previewButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables
    
    {
        //this.parent = parent;
        initComponents();
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
        
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);        
        previewButton.addActionListener(this);
    }

}
