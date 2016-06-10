//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package org.netbeans.jpa.modeler.spec;

import java.util.ArrayList;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.persistence.internal.jpa.metadata.accessors.classes.XMLAttributes;
import org.netbeans.jpa.modeler.spec.extend.BaseAttributes;
import org.netbeans.jpa.source.JavaSourceParserUtil;

/**
 * <p>
 * Java class for embeddable-attributes complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="embeddable-attributes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="basic" type="{http://java.sun.com/xml/ns/persistence/orm}basic" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="many-to-one" type="{http://java.sun.com/xml/ns/persistence/orm}many-to-one" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="one-to-many" type="{http://java.sun.com/xml/ns/persistence/orm}one-to-many" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="one-to-one" type="{http://java.sun.com/xml/ns/persistence/orm}one-to-one" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="many-to-many" type="{http://java.sun.com/xml/ns/persistence/orm}many-to-many" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="element-collection" type="{http://java.sun.com/xml/ns/persistence/orm}element-collection" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="embedded" type="{http://java.sun.com/xml/ns/persistence/orm}embedded" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="transient" type="{http://java.sun.com/xml/ns/persistence/orm}transient" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "embeddable-attributes", propOrder = {})
public class EmbeddableAttributes extends BaseAttributes {

    @Override
    public void load(EntityMappings entityMappings, TypeElement typeElement, boolean fieldAccess) {

        for (ExecutableElement method : JavaSourceParserUtil.getMethods(typeElement)) {
            String methodName = method.getSimpleName().toString();
            if (methodName.startsWith("get")) {
                Element element;
                VariableElement variableElement = JavaSourceParserUtil.guessField(method);
                // Issue Fix #5976 Start
                /**
                 * #5976 FIX fixed NPE when method is not attached to field
                 * Transient or in
                 *
                 * @author Juraj Balaz <georgeeb@java.net>
                 * @since Thu, 17 Apr 2014 14:07:11 +0000
                 */
                // skip processing if the method is not joined with field
                // might be transient method or method implementation from some interface
                if (variableElement == null) {
                    continue;
                }
                // Issue Fix #5976 End
                if (fieldAccess) {
                    element = variableElement;
                } else {
                    element = method;
                }

                if (JavaSourceParserUtil.isAnnotatedWith(element, "javax.persistence.Basic")) {
                    this.addBasic(Basic.load(element, variableElement));
                } else if (JavaSourceParserUtil.isAnnotatedWith(element, "javax.persistence.Transient")) {
                    this.addTransient(Transient.load(element, variableElement));
                } else if (JavaSourceParserUtil.isAnnotatedWith(element, "javax.persistence.ElementCollection")) {
                    this.addElementCollection(ElementCollection.load(entityMappings, element, variableElement));
                } else if (JavaSourceParserUtil.isAnnotatedWith(element, "javax.persistence.OneToOne")) {
                    OneToOne oneToOneObj = new OneToOne();
                    this.addOneToOne(oneToOneObj);
                    oneToOneObj.load(element, variableElement);
                } else if (JavaSourceParserUtil.isAnnotatedWith(element, "javax.persistence.ManyToOne")) {
                    ManyToOne manyToOneObj = new ManyToOne();
                    this.addManyToOne(manyToOneObj);
                    manyToOneObj.load(element, variableElement);
                } else if (JavaSourceParserUtil.isAnnotatedWith(element, "javax.persistence.OneToMany")) {
                    OneToMany oneToManyObj = new OneToMany();
                    this.addOneToMany(oneToManyObj);
                    oneToManyObj.load(element, variableElement);
                } else if (JavaSourceParserUtil.isAnnotatedWith(element, "javax.persistence.ManyToMany")) {
                    ManyToMany manyToManyObj = new ManyToMany();
                    this.addManyToMany(manyToManyObj);
                    manyToManyObj.load(element, variableElement);
                } else if (JavaSourceParserUtil.isAnnotatedWith(element, "javax.persistence.Embedded")) {
                    this.addEmbedded(Embedded.load(entityMappings, element, variableElement));
                } else {
                    this.addBasic(Basic.load(element, variableElement)); //Default Annotation
                }

            }
        }

    }

    @Override
    public XMLAttributes getAccessor() {
        XMLAttributes attr = super.getAccessor();
        attr.setIds(new ArrayList<>());
        attr.setVersions(new ArrayList<>());
        return updateAccessor(attr);
    }

    public XMLAttributes updateAccessor(XMLAttributes attr) {
        super.updateAccessor(attr, false);
        return attr;
    }

}
