//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.06 at 02:31:21 PM ICT 
//


package vn.nsw.fac.inland;

import javax.xml.bind.annotation.XmlRegistry;
import vn.nsw.model.inland.Attachment;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the vn package. 
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
public class AttachmentFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: vn
     * 
     */
    public AttachmentFactory() {
    }

    /**
     * Create an instance of {@link Attachment }
     * 
     */
    public Attachment createAttachment() {
        return new Attachment();
    }

    /**
     * Create an instance of {@link Attachment.AttachmentList }
     * 
     */
    public Attachment.AttachmentList createAttachmentAttachmentList() {
        return new Attachment.AttachmentList();
    }

}
