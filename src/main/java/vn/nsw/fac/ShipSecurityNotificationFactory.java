//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.17 at 12:30:44 PM ICT 
//


package vn.nsw.fac;

import javax.xml.bind.annotation.XmlRegistry;

import vn.nsw.model.ShipSecurityNotification;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the vn.muoinam package. 
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
public class ShipSecurityNotificationFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: vn.muoinam
     * 
     */
    public ShipSecurityNotificationFactory() {
    }

    /**
     * Create an instance of {@link ShipSecurityNotification.Last10PortsOfCall.PortOfCall }
     * 
     */
    public ShipSecurityNotification.Last10PortsOfCall.PortOfCall createShipSecurityNotificationLast10PortsOfCallPortOfCall() {
        return new ShipSecurityNotification.Last10PortsOfCall.PortOfCall();
    }

    /**
     * Create an instance of {@link ShipSecurityNotification.Last10PortsOfCall }
     * 
     */
    public ShipSecurityNotification.Last10PortsOfCall createShipSecurityNotificationLast10PortsOfCall() {
        return new ShipSecurityNotification.Last10PortsOfCall();
    }

    /**
     * Create an instance of {@link ShipSecurityNotification }
     * 
     */
    public ShipSecurityNotification createShipSecurityNotification() {
        return new ShipSecurityNotification();
    }

}