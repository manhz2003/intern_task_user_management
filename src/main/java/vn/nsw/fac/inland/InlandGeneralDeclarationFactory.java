//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.22 at 05:48:27 AM ICT 
//


package vn.nsw.fac.inland;

import javax.xml.bind.annotation.XmlRegistry;
import vn.nsw.model.inland.InlandGeneralDeclaration;

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
public class InlandGeneralDeclarationFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: vn
     * 
     */
    public InlandGeneralDeclarationFactory() {
    }

    /**
     * Create an instance of {@link InlandGeneralDeclaration }
     * 
     */
    public InlandGeneralDeclaration createInlandGeneralDeclaration() {
        return new InlandGeneralDeclaration();
    }

    /**
     * Create an instance of {@link InlandGeneralDeclaration.CargoList }
     * 
     */
    public InlandGeneralDeclaration.CargoList createInlandGeneralDeclarationCargoList() {
        return new InlandGeneralDeclaration.CargoList();
    }

}
