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
import dict.obj.PartOfSpeech;
import dict.obj.ExplanationUnit;
import dict.obj.CtxOfUse;
import dict.obj.Phrase;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import java.util.List;

public class AddExplanationUnit extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form AddExplanationUnit
     */
    
    private AddWord parent;
    private ExplanationUnit eu;
    private DefaultListModel<String> contextListModel;
    private DefaultListModel<String> idiomListModel;
    private DefaultListModel<String> pverbListModel;
    private boolean phraseFlag; // true - Idiom, false - Phrasal Verb
    private boolean editFlag;
    private final String emptyContext;
    
    
    private AddExplanationUnit(AddWord parent){
        this.parent = parent;
        setTitle("Add Explanation Unit");
        this.eu = new ExplanationUnit();
    }
    
    private AddExplanationUnit(AddWord parent, ExplanationUnit eu) {
        this.parent = parent;
        setTitle("Edit Explanation Unit");        
        this.eu = eu;
        psComboBox.setSelectedItem(eu.getPartOfSpeech().getName());
        List<CtxOfUse> cList = eu.getContextList();        
        for(CtxOfUse cu : cList){            
            contextListModel.addElement(checkContext(cu));
        }
        List<Phrase> pList = eu.getIdiomList();
        for(Phrase ph : pList){
            idiomListModel.addElement(ph.getForm());
        }
        pList = eu.getPhrasalVerbList();        
        for(Phrase ph : pList){
            pverbListModel.addElement(ph.getForm());
        }        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent ae){
        warningLabel.setText("  ");
        switch(ae.getActionCommand()){
            case "AddContext":
                addContextButtonActionPerformed(ae);
                break;
            case "EditContext":
                editContextButtonActionPerformed(ae);
                break;
            case "DeleteContext":
                deleteContextButtonActionPerformed(ae);
                break;
            case "AddIdiom":
                addIdiomButtonActionPerformed(ae);
                break;
            case "EditIdiom":
                editIdiomButtonActionPerformed(ae);
                break;
            case "DeleteIdiom":
                deleteIdiomButtonActionPerformed(ae);
                break;
            case "AddPVerb":
                addPVerbButtonActionPerformed(ae);
                break;
            case "EditPVerb":
                editPVerbButtonActionPerformed(ae);
                break;
            case "DeletePverb":
                deletePVerbButtonActionPerformed(ae);
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
            case "comboBoxChanged":
                psComboBoxActionPerformed(ae);
                break;
            default:
                warningLabel.setText("Something went wrong");
        }
        
    }    
      
    
    private void addContextButtonActionPerformed(ActionEvent ae){
        editFlag = false;
        setVisible(false);
        AddCtxOfUse.init(this);
    }
    
    private void editContextButtonActionPerformed(ActionEvent ae){
        editFlag = true;
        int index;
        index = contextList.getSelectedIndex();
        try{
            if(index == -1){
                throw new Exception("Select context to edit");
            }
            AddCtxOfUse.init(this, new CtxOfUse(eu.getContextList().get(index)));
            setVisible(false);
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void deleteContextButtonActionPerformed(ActionEvent ae){
        int index;
        index = contextList.getSelectedIndex();
        try{
            if(index == -1){
                throw new Exception("Select context to delete");
            }
            eu.getContextList().remove(index);
            contextListModel.remove(index);
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void addIdiomButtonActionPerformed(ActionEvent ae){
        editFlag = false;
        phraseFlag = true;
        setVisible(false);
        AddPhrase.init(this, "Idiom");
    }
    
    private void editIdiomButtonActionPerformed(ActionEvent ae){
        editFlag = true;
        phraseFlag = true;
        int index;
        index = idiomList.getSelectedIndex();
        try{
            if(index == -1){
                throw new Exception("Select Idiom to edit");
            }
            AddPhrase.init(this, "Idiom", new Phrase(eu.getIdiomList().get(index)));
            setVisible(false);
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void deleteIdiomButtonActionPerformed(ActionEvent ae){
        int index;
        index = idiomList.getSelectedIndex();
        try{
            if(index == -1){
                throw new Exception("Select Idiom to delete");
            }
            eu.getIdiomList().remove(index);
            idiomListModel.remove(index);
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void addPVerbButtonActionPerformed(ActionEvent ae){
        editFlag = false;
        phraseFlag = false;
        setVisible(false);
        AddPhrase.init(this, "Phrasal verb");
    }
    
    private void editPVerbButtonActionPerformed(ActionEvent ae){
        editFlag = true;
        phraseFlag = false;
        int index;
        index = pverbList.getSelectedIndex();
        try{
            if(index == -1){
                throw new Exception("Select Phrasal verb to edit");
            }
            AddPhrase.init(this, "Phrasal verb", new Phrase(eu.getPhrasalVerbList().get(index)));
            setVisible(false);
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void deletePVerbButtonActionPerformed(ActionEvent ae){
        int index;
        index = pverbList.getSelectedIndex();
        try{
            if(index == -1){
                throw new Exception("Select Phrasal verb to delete");
            }
            eu.getPhrasalVerbList().remove(index);
            pverbListModel.remove(index);
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void previewButtonActionPerformed(ActionEvent ae){
        try{
            String str = (String)psComboBox.getSelectedItem();
            PartOfSpeech ps = DictionaryManager.getPartOfSpeech(str);
            eu.setPartOfSpeech(ps);
            Preview.init("Explanation Unit", eu.toString());            
        }
        catch(Exception e){
            warningLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void psComboBoxActionPerformed(ActionEvent ae){
        String str = (String)psComboBox.getSelectedItem();
        if(str.equals("verb")){
            setPhrasalVerbEnable(true);
        }
        else{
            setPhrasalVerbEnable(false);
        }
    }
    
    private void saveButtonActionPerformed(ActionEvent ae){
        try{
            String str = (String)psComboBox.getSelectedItem();
            PartOfSpeech ps = DictionaryManager.getPartOfSpeech(str);
            eu.setPartOfSpeech(ps);
            parent.addExplanationUnit(eu);
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
    
    private void setPhrasalVerbEnable(boolean flag){
        pverbList.setEnabled(flag);
        pAddButton.setEnabled(flag);
        pEditButton.setEnabled(flag);
        pDeleteButton.setEnabled(flag);
    }        
    
    private void psComboBoxInitialization(){
        for(PartOfSpeech ps : DictionaryManager.getPartOfSpeechList()){
            psComboBox.addItem(ps.getName());
        }
    }
    
    public void addCtxOfUse(CtxOfUse context) throws Exception{
        int index;
        if(editFlag){
            index = contextList.getSelectedIndex();
            if(index == -1)
                throw new Exception("Something went wrong");
            eu.getContextList().remove(index);
            contextListModel.remove(index);
            eu.getContextList().add(index, context);            
            contextListModel.add(index, checkContext(context));
        }
        else{
            eu.addContext(context);            
            contextListModel.addElement(checkContext(context));
            index = contextListModel.size();
            index--;
        }
        contextList.setSelectedIndex(index);
        contextList.ensureIndexIsVisible(index);
    }
    
    public void addPhrase(Phrase phrase) throws Exception{
        javax.swing.DefaultListModel<String> tempModel;
        javax.swing.JList<String> tempList;
        if(phraseFlag){
            tempModel = idiomListModel;
            tempList = idiomList;
        }
        else{
            tempModel = pverbListModel;
            tempList = pverbList;
        }
        int index;
        if(editFlag){
            index = tempList.getSelectedIndex();
            if(index == -1)
                throw new Exception("Something went wrong");
            if(phraseFlag){
                eu.getIdiomList().remove(index);            
                eu.addIdiom(index, phrase);
            }
            else{
                eu.getPhrasalVerbList().remove(index);            
                eu.addPhrasalVerb(index, phrase);
            }            
            tempModel.remove(index);
            tempModel.add(index, phrase.getForm());
        }
        else{
            if(phraseFlag){
                eu.addIdiom(phrase);
            }
            else{
                eu.addPhrasalVerb(phrase);
            }
            tempModel.addElement(phrase.getForm());
            index = tempModel.size();
            index--;
        }
        tempList.setSelectedIndex(index);
        tempList.ensureIndexIsVisible(index);
    }
    
    private String checkContext(CtxOfUse context){
        String str = context.getContext();
        if(str == null || str.length() == 0)
            return emptyContext;
        else
            return str;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        psLabel = new javax.swing.JLabel();
        psComboBox = new javax.swing.JComboBox<String>();
        contextLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contextListModel = new javax.swing.DefaultListModel<String>();
        contextList = new javax.swing.JList<String>(contextListModel);
        cAddButton = new javax.swing.JButton();
        cEditButton = new javax.swing.JButton();
        cDeleteButton = new javax.swing.JButton();
        iAddButton = new javax.swing.JButton();
        iEditButton = new javax.swing.JButton();
        iDeleteButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        idiomListModel = new javax.swing.DefaultListModel<String>();
        idiomList = new javax.swing.JList<String>(idiomListModel);
        jScrollPane3 = new javax.swing.JScrollPane();
        pverbListModel = new javax.swing.DefaultListModel<String>();
        pverbList = new javax.swing.JList<String>(pverbListModel);
        pAddButton = new javax.swing.JButton();
        pEditButton = new javax.swing.JButton();
        pDeleteButton = new javax.swing.JButton();
        idiomLabel = new javax.swing.JLabel();
        pverbLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        previewButton = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        psLabel.setText("Part of speech");

        contextLabel.setText("Context Of Use");

        jScrollPane1.setViewportView(contextList);

        cAddButton.setText("Add");
        cAddButton.setActionCommand("AddContext");

        cEditButton.setText("Edit");
        cEditButton.setActionCommand("EditContext");

        cDeleteButton.setText("Delete");
        cDeleteButton.setActionCommand("DeleteContext");

        iAddButton.setText("Add");
        iAddButton.setActionCommand("AddIdiom");

        iEditButton.setText("Edit");
        iEditButton.setActionCommand("EditIdiom");

        iDeleteButton.setText("Delete");
        iDeleteButton.setActionCommand("DeleteIdiom");

        jScrollPane2.setViewportView(idiomList);

        pverbList.setEnabled(false);
        jScrollPane3.setViewportView(pverbList);

        pAddButton.setText("Add");
        pAddButton.setActionCommand("AddPVerb");
        pAddButton.setEnabled(false);

        pEditButton.setText("Edit");
        pEditButton.setActionCommand("EditPVerb");
        pEditButton.setEnabled(false);

        pDeleteButton.setText("Delete");
        pDeleteButton.setActionCommand("DeletePVerb");
        pDeleteButton.setEnabled(false);

        idiomLabel.setText("Idiom");

        pverbLabel.setText("Phrasal verb");

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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pverbLabel)
                        .addComponent(idiomLabel)
                        .addComponent(contextLabel)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(psLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(psComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(iDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(iEditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(iAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cEditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cAddButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pEditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pAddButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(previewButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cancelButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(psLabel)
                    .addComponent(psComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contextLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cAddButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cEditButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cDeleteButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(idiomLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(iAddButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iEditButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iDeleteButton))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pverbLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pAddButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pEditButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pDeleteButton))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warningLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            java.util.logging.Logger.getLogger(AddExplanationUnit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddExplanationUnit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddExplanationUnit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddExplanationUnit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }

    public static void  init(AddWord parent){
        setLookAndFeel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddExplanationUnit(parent).setVisible(true);
            }
        });
    }
    
    public static void  init(AddWord parent, ExplanationUnit eu){
        setLookAndFeel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddExplanationUnit(parent, eu).setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cAddButton;
    private javax.swing.JButton cDeleteButton;
    private javax.swing.JButton cEditButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel contextLabel;
    private javax.swing.JList<String> contextList;
    private javax.swing.JButton iAddButton;
    private javax.swing.JButton iDeleteButton;
    private javax.swing.JButton iEditButton;
    private javax.swing.JLabel idiomLabel;
    private javax.swing.JList<String> idiomList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton pAddButton;
    private javax.swing.JButton pDeleteButton;
    private javax.swing.JButton pEditButton;
    private javax.swing.JButton previewButton;
    private javax.swing.JComboBox<String> psComboBox;
    private javax.swing.JLabel psLabel;
    private javax.swing.JLabel pverbLabel;
    private javax.swing.JList<String> pverbList;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables

    {
        emptyContext = "---";        
        initComponents();
        psComboBoxInitialization();
        
        addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosed(WindowEvent we){
                        parent.setVisible(true);
                    }
                }
        );
        
        cAddButton.addActionListener(this);
        cEditButton.addActionListener(this);
        cDeleteButton.addActionListener(this);
        
        iAddButton.addActionListener(this);
        iEditButton.addActionListener(this);
        iDeleteButton.addActionListener(this);
        
        pAddButton.addActionListener(this);
        pEditButton.addActionListener(this);
        pDeleteButton.addActionListener(this);
        
        previewButton.addActionListener(this);
        cancelButton.addActionListener(this);
        saveButton.addActionListener(this);
        
        psComboBox.addActionListener(this);
        
    }
    
}
