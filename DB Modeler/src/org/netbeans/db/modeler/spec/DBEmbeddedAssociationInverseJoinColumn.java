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
package org.netbeans.db.modeler.spec;

import org.netbeans.jpa.modeler.spec.AssociationOverride;
import org.netbeans.jpa.modeler.spec.Embedded;
import org.netbeans.jpa.modeler.spec.extend.Attribute;

public class DBEmbeddedAssociationInverseJoinColumn extends DBEmbeddedAssociationColumn {

    private AssociationOverride associationOverride;

    public DBEmbeddedAssociationInverseJoinColumn(String name, Embedded embedded, Attribute managedAttribute, boolean relationTableExist) {
        super(name, embedded, managedAttribute,relationTableExist);
        associationOverride = embedded.findAssociationOverride(managedAttribute.getName());
        if (associationOverride == null) {
            associationOverride = new AssociationOverride();
            associationOverride.setName(managedAttribute.getName());
//            associationOverride.setColumn(new Column());
            embedded.addAssociationOverride(associationOverride);
        }
    }

    /**
     * @return the associationOverride
     */
    public AssociationOverride getAssociationOverride() {
        return associationOverride;
    }

}
