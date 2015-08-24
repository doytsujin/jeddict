//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package org.netbeans.jpa.modeler.spec;

import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.TypeElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.netbeans.jpa.modeler.spec.extend.Attribute;
import org.netbeans.jpa.modeler.spec.extend.IAttributes;
import org.netbeans.jpa.modeler.spec.extend.JavaClass;
import org.netbeans.jpa.source.JavaSourceParserUtil;
import org.netbeans.modeler.core.NBModelerUtil;

/**
 *
 *
 *         Defines the settings and mappings for embeddable objects. Is 
 *         allowed to be sparsely populated and used in conjunction with 
 *         the annotations. Alternatively, the metadata-complete attribute 
 *         can be used to indicate that no annotations are to be processed 
 *         in the class. If this is the case then the defaulting rules will 
 *         be recursively applied.
 *
 *         @Target({TYPE}) @Retention(RUNTIME)
 *         public @interface Embeddable {}
 *
 *
 *
 * <p>Java class for embeddable complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="embeddable">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributes" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}embeddable-attributes" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="access" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}access-type" />
 *       &lt;attribute name="metadata-complete" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "embeddable", propOrder = {
//    "description",
    "attributes",
//    "interfaces"
})
public class Embeddable extends ManagedClass {

    protected EmbeddableAttributes attributes;
    
    

    @Override
    public void load(EntityMappings entityMappings, TypeElement element, boolean fieldAccess) {
        if (entityMappings.findMappedSuperclass(element.getSimpleName().toString()) == null) { // BUG : https://java.net/bugzilla/show_bug.cgi?id=6192 NullPointerException when reverse engineering from JPA classes revisited
            TypeElement superClassElement = JavaSourceParserUtil.getSuperclassTypeElement(element);
            if (!superClassElement.getQualifiedName().toString().equals("java.lang.Object")) {
                //Not Supported Currently
            }
            super.load(entityMappings, element, fieldAccess);
        }
    }


    /**
     * Gets the value of the attributes property.
     *
     * @return possible object is {@link EmbeddableAttributes }
     *
     */
    public EmbeddableAttributes getAttributes() {
        if (attributes == null) {
            attributes = new EmbeddableAttributes();
        }
        return attributes;
    }

    /**
     * Sets the value of the attributes property.
     *
     * @param value allowed object is {@link EmbeddableAttributes }
     *
     */
    public void setAttributes(EmbeddableAttributes value) {
        this.attributes = value;
    }
    


    @Override
    public String getName() {
        return getClazz();
    }

    @Override
    public void setName(String name) {
        setClazz(name);
    }

    @Override
    public void setAttributes(IAttributes attributes) {
        this.attributes = (EmbeddableAttributes) attributes;
    }

}
