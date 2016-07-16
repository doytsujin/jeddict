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

import org.netbeans.jpa.modeler.properties.classmember.nodes.ClassMemberChildFactory;
import org.netbeans.jpa.modeler.navigator.nodes.CheckableAttributeNode;
import javax.swing.SwingUtilities;
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
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.OutlineView;

public class ClassMemberPanel extends GenericEmbeddedEditor<ClassMembers> implements ExplorerManager.Provider {

    private ExplorerManager manager;
    private final String title;

    private ClassMembers classMembers;
    private PersistenceClassWidget<? extends ManagedClass> persistenceClassWidget;
    private CMRootNode node;

    public ClassMemberPanel(String title, PersistenceClassWidget<? extends ManagedClass> persistenceClassWidget) {
        this.persistenceClassWidget = persistenceClassWidget;
        this.title=title;
    }

    public ClassMemberPanel(String title) {
        this.title=title;
    }

    @Override
    public void init() {
        manager = new ExplorerManager();
        initComponents();
    }

    @Override
    public void setValue(ClassMembers classMembers) {
        this.classMembers = classMembers;
        SwingUtilities.invokeLater(() -> {
            node = new CMRootNode(persistenceClassWidget, classMembers, new ClassMemberChildFactory(), new CheckableAttributeNode());
            manager.setRootContext(node);
            node.init();
        });
    }

    @Override
    public ClassMembers getValue() {
        classMembers.getAttributes().clear();
        loadClassMember(classMembers, node);
        return classMembers;
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
        outlineView = new OutlineView(getTitle());

        rootLayeredPane.setLayout(new java.awt.GridLayout(1, 0));

        outlineView.setToolTipText(org.openide.util.NbBundle.getMessage(ClassMemberPanel.class, "ClassMemberPanel.outlineView.toolTipText")); // NOI18N
        rootLayeredPane.add(outlineView);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadClassMember(ClassMembers classMembers, TreeNode parentNode) {
        if (parentNode instanceof TreeParentNode) {
            for (TreeNode childNode : ((TreeParentNode<ClassMembers>) parentNode).getChildList()) {
                loadAttributeNode(classMembers, childNode);
            }
//        } else if (parentNode instanceof CMInternalNode) {
//            for (TreeNode childNode : ((CMInternalNode) parentNode).getChildList()) {
//                loadSubGraph(classMembers, childNode);
//            }
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
    private javax.swing.JScrollPane outlineView;
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
