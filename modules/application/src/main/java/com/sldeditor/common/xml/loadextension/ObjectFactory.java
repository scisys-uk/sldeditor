//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: [TEXT REMOVED by maven-replacer-plugin]
//


package com.sldeditor.common.xml.loadextension;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sldeditor.common.xml.loadextension package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sldeditor.common.xml.loadextension
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LoadExtension }
     * 
     */
    public LoadExtension createLoadExtension() {
        return new LoadExtension();
    }

    /**
     * Create an instance of {@link XMLExtension }
     * 
     */
    public XMLExtension createXMLExtension() {
        return new XMLExtension();
    }

    /**
     * Create an instance of {@link XMLExtensionClass }
     * 
     */
    public XMLExtensionClass createXMLExtensionClass() {
        return new XMLExtensionClass();
    }

}