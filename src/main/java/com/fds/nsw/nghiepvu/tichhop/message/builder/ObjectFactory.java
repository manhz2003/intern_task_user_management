//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.25 at 10:22:09 AM ICT 
//


package com.fds.nsw.nghiepvu.tichhop.message.builder;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the vn.nsw package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: vn.nsw
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Header.To }
     * 
     */
    public Header.To createHeaderTo() {
        return new Header.To();
    }

    /**
     * Create an instance of {@link Header.Subject }
     * 
     */
    public Header.Subject createHeaderSubject() {
        return new Header.Subject();
    }

    /**
     * Create an instance of {@link Header.Reference }
     * 
     */
    public Header.Reference createHeaderReference() {
        return new Header.Reference();
    }

    /**
     * Create an instance of {@link Header }
     * 
     */
    public Header createHeader() {
        return new Header();
    }

    /**
     * Create an instance of {@link Header.From }
     * 
     */
    public Header.From createHeaderFrom() {
        return new Header.From();
    }

}
