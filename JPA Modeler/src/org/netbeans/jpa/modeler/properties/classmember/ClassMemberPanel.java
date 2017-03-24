/**
 * Copyright [2016] Gaurav Gupta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.netbeans.jpa.modeler.properties.classmember;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import static javax.swing.JOptionPane.OK_OPTION;
import org.netbeans.jpa.modeler.properties.classmember.nodes.ClassMemberChildFactory;
import org.netbeans.jpa.modeler.navigator.nodes.CheckableAttributeNode;
import javax.swing.SwingUtilities;
import org.apache.commons.lang.StringUtils;
import org.netbeans.jpa.modeler.core.widget.PersistenceClassWidget;
import org.netbeans.jpa.modeler.properties.classmember.nodes.CMLeafNode;
import org.netbeans.jpa.modeler.properties.classmember.nodes.CMRootNode;
import org.netbeans.jpa.modeler.navigator.nodes.TreeChildNode;
import org.netbeans.jpa.modeler.navigator.nodes.TreeNode;
import org.netbeans.jpa.modeler.navigator.nodes.TreeParentNode;
import org.netbeans.jpa.modeler.spec.ManagedClass;
import org.netbeans.jpa.modeler.spec.extend.Attribute;
import org.netbeans.jpa.modeler.spec.extend.ClassMembers;
import org.netbeans.modeler.properties.embedded.GenericEmbeddedEditor;
import org.netbeans.modeler.properties.window.OptionDialog;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.OutlineView;

public class ClassMemberPanel extends GenericEmbeddedEditor<ClassMembers> implements ExplorerManager.Provider {
    
    private ExplorerManager manager;
    private final String title;
    
    private ClassMembers classMembers;
    private PersistenceClassWidget<? extends ManagedClass> persistenceClassWidget;
    private CMRootNode node;
    private boolean customCode = true;

    public ClassMemberPanel(String title, PersistenceClassWidget<? extends ManagedClass> persistenceClassWidget) {
        this.persistenceClassWidget = persistenceClassWidget;
        this.title = title;
    }
    
    public ClassMemberPanel(String title, PersistenceClassWidget<? extends ManagedClass> persistenceClassWidget,
            boolean customCode) {
        this.persistenceClassWidget = persistenceClassWidget;
        this.title = title;
        this.customCode = customCode;
    }
    
    public ClassMemberPanel(String title) {
        this.title = title;
    }
    
    public void postConstruct(){
        initComponents(); 
    }
    @Override
    public void init() {
        manager = new ExplorerManager();
        displayCustomCode(customCode);
    }
    
    @Override
    public void setValue(ClassMembers classMembers) {
        this.classMembers = classMembers;
        SwingUtilities.invokeLater(() -> {
            node = new CMRootNode(persistenceClassWidget, classMembers, new ClassMemberChildFactory(), new CheckableAttributeNode());
            manager.setRootContext(node);
            node.init();
        });
        updatebutton();
        
    }
    
    @Override
    public ClassMembers getValue() {
        classMembers.getAttributes().clear();
        loadClassMember(classMembers, node);
        return classMembers;
    }
    
    public void displayCustomCode(boolean visible) {
        displayPreCustomCode(visible);
        displayPostCustomCode(visible);
    }
    
    public void displayPreCustomCode(boolean visible) {
        preCodeButton.setVisible(visible);
    }
    
    public void displayPostCustomCode(boolean visible) {
        postCodeButton.setVisible(visible);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootLayeredPane = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        outlineView = new OutlineView(getTitle());
        preCodeButton = new javax.swing.JButton();
        postCodeButton = new javax.swing.JButton();

        rootLayeredPane.setLayout(new java.awt.BorderLayout());

        outlineView.setToolTipText(org.openide.util.NbBundle.getMessage(ClassMemberPanel.class, "ClassMemberPanel.outlineView.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(outlineView, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(outlineView, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE))
        );

        rootLayeredPane.add(jPanel1, java.awt.BorderLayout.CENTER);

        preCodeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/netbeans/jpa/modeler/properties/classmember/resource/add-data.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(preCodeButton, org.openide.util.NbBundle.getMessage(ClassMemberPanel.class, "ClassMemberPanel.preCodeButton.text")); // NOI18N
        preCodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preCodeButtonActionPerformed(evt);
            }
        });
        rootLayeredPane.add(preCodeButton, java.awt.BorderLayout.PAGE_START);

        postCodeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/netbeans/jpa/modeler/properties/classmember/resource/add-data.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(postCodeButton, org.openide.util.NbBundle.getMessage(ClassMemberPanel.class, "ClassMemberPanel.postCodeButton.text")); // NOI18N
        postCodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postCodeButtonActionPerformed(evt);
            }
        });
        rootLayeredPane.add(postCodeButton, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootLayeredPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootLayeredPane)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void preCodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preCodeButtonActionPerformed
        classMembers.setPreCode(getCode(classMembers.getPreCode(), "Pre-Source"));
        updatebutton();
    }//GEN-LAST:event_preCodeButtonActionPerformed

    private void postCodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postCodeButtonActionPerformed
        classMembers.setPostCode(getCode(classMembers.getPostCode(), "Post-Source"));
        updatebutton();
    }//GEN-LAST:event_postCodeButtonActionPerformed
    
    private String getCode(String code, String title) {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/x-java");
        editorPane.setPreferredSize(new java.awt.Dimension(600, 400));
        editorPane.setText(code);
        OptionDialog dialog = new OptionDialog(editorPane, title);
        dialog.setVisible(true);
        if (OK_OPTION == dialog.getDialogResult()) {
            return editorPane.getText();
        } else {
            return code;
        }
    }
    
    private void updatebutton(){
        String DEAFULT_TOOLTIP = "add custom code snippet";
        ImageIcon addDataIcon = new ImageIcon(getClass().getResource("/org/netbeans/jpa/modeler/properties/classmember/resource/add-data.png")); 
        ImageIcon dataIcon = new ImageIcon(getClass().getResource("/org/netbeans/jpa/modeler/properties/classmember/resource/data.png")); 
        preCodeButton.setIcon(StringUtils.isNotBlank(classMembers.getPreCode())?dataIcon:addDataIcon);
        preCodeButton.setToolTipText(StringUtils.isNotBlank(classMembers.getPreCode())?classMembers.getPreCode():DEAFULT_TOOLTIP);
        postCodeButton.setIcon(StringUtils.isNotBlank(classMembers.getPostCode())?dataIcon:addDataIcon);
        postCodeButton.setToolTipText(StringUtils.isNotBlank(classMembers.getPostCode())?classMembers.getPostCode():DEAFULT_TOOLTIP);
    }
    
    private void loadClassMember(ClassMembers classMembers, TreeNode parentNode) {
        if (parentNode instanceof TreeParentNode) {
            for (TreeNode childNode : ((TreeParentNode<ClassMembers>) parentNode).getChildList()) {
                loadAttributeNode(classMembers, childNode);
            }
        }
    }
    
    private void loadAttributeNode(ClassMembers classMembers, TreeNode childNode) {
        if (childNode.getCheckableNode() == null || !childNode.getCheckableNode().isSelected() || !childNode.getCheckableNode().isCheckEnabled()) {
            return;
        }
        if (childNode instanceof TreeChildNode) {
            Attribute attribute = ((Attribute) (((CMLeafNode) childNode).getLeafAttributeWidget().getBaseElementSpec()));
            classMembers.addAttribute(attribute);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane outlineView;
    private javax.swing.JButton postCodeButton;
    private javax.swing.JButton preCodeButton;
    private javax.swing.JLayeredPane rootLayeredPane;
    // End of variables declaration//GEN-END:variables

    @Override
    public ExplorerManager getExplorerManager() {
        return manager;
    }

    /**
     * @param persistenceClassWidget the persistenceClassWidget to set
     */
    public void setPersistenceClassWidget(PersistenceClassWidget<? extends ManagedClass> persistenceClassWidget) {
        this.persistenceClassWidget = persistenceClassWidget;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
}