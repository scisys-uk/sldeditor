//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, vhudson-jaxb-ri-2.1-833
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: [TEXT REMOVED by maven-replacer-plugin]
//

package com.sldeditor.common.xml.ui;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Configuration to compare the integer value of a field
 *
 * <p>Java class for XMLFieldLiteralInt complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="XMLFieldLiteralInt">
 *   &lt;complexContent>
 *     &lt;extension base="{}XMLFieldLiteralBase">
 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLFieldLiteralInt")
public class XMLFieldLiteralInt extends XMLFieldLiteralBase {

    @XmlAttribute protected Integer value;

    /**
     * Gets the value of the value property.
     *
     * @return possible object is {@link Integer }
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is {@link Integer }
     */
    public void setValue(Integer value) {
        this.value = value;
    }
}
