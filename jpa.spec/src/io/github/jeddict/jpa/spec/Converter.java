//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.18 at 01:46:14 PM IST 
//
package io.github.jeddict.jpa.spec;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.persistence.internal.jpa.metadata.converters.MixedConverterMetadata;

/**
 *
 *
 * @Target({TYPE, METHOD, FIELD}) @Retention(RUNTIME) public @interface
 * Converter { boolean autoApply() default false; }
 *
 *
 *
 * <p>
 * Java class for converter complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="converter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="auto-apply" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "converter", propOrder = {
    "description"
})
public class Converter {

    protected String description;
    @XmlAttribute(name = "c", required = true)
    protected String clazz;
    @XmlAttribute(name = "a", required = true)
    private String attributeType;
    @XmlAttribute(name = "f", required = true)
    private String fieldType;//db
    @XmlAttribute(name = "au")
    protected Boolean autoApply;

    /**
     * Gets the value of the description property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the clazz property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the autoApply property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean isAutoApply() {
        if(autoApply == null) {
            return false;
        }
        return autoApply;
    }

    /**
     * Sets the value of the autoApply property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setAutoApply(Boolean value) {
        this.autoApply = value;
    }

    /**
     * @return the attributeType
     */
    public String getAttributeType() {
        return attributeType;
    }

    /**
     * @param attributeType the attributeType to set
     */
    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    /**
     * @return the dbFieldType
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * @param dbFieldType the dbFieldType to set
     */
    public void setFieldType(String dbFieldType) {
        this.fieldType = dbFieldType;
    }

    public MixedConverterMetadata getAccessor() {
        MixedConverterMetadata accessr = new MixedConverterMetadata();
        accessr.setClassName(getClazz());
        accessr.setAutoApply(isAutoApply());
        return accessr;
    }
}