/**
 * MessageAndResponseImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.dtt.duongbo.ws;

import vn.gt.ws.util.WebserviceUtil;

public class MessageAndResponseImplServiceLocator extends org.apache.axis.client.Service implements vn.dtt.duongbo.ws.MessageAndResponseImplService {

    public MessageAndResponseImplServiceLocator() {
    }


    public MessageAndResponseImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MessageAndResponseImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MessageAndResponseImpl
    //TODO: for test
    //private java.lang.String MessageAndResponseImpl_address = "http://192.168.68.28:8080/DuongBoDoanhNghiepApp-portlet/services/MessageAndResponseImpl?wsdl";
    
    //TODO: for prod
    private java.lang.String MessageAndResponseImpl_address = WebserviceUtil.getWebserviceCongBGTVTURL();//"http://20.21.2.52/DuongBoDoanhNghiepApp-portlet/services/MessageAndResponseImpl?wsdl";

    public java.lang.String getMessageAndResponseImplAddress() {
        return MessageAndResponseImpl_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MessageAndResponseImplWSDDServiceName = "MessageAndResponseImpl";

    public java.lang.String getMessageAndResponseImplWSDDServiceName() {
        return MessageAndResponseImplWSDDServiceName;
    }

    public void setMessageAndResponseImplWSDDServiceName(java.lang.String name) {
        MessageAndResponseImplWSDDServiceName = name;
    }

    public vn.dtt.duongbo.ws.MessageAndResponseImpl getMessageAndResponseImpl() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MessageAndResponseImpl_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMessageAndResponseImpl(endpoint);
    }

    public vn.dtt.duongbo.ws.MessageAndResponseImpl getMessageAndResponseImpl(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            vn.dtt.duongbo.ws.MessageAndResponseImplSoapBindingStub _stub = new vn.dtt.duongbo.ws.MessageAndResponseImplSoapBindingStub(portAddress, this);
            _stub.setPortName(getMessageAndResponseImplWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMessageAndResponseImplEndpointAddress(java.lang.String address) {
        MessageAndResponseImpl_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (vn.dtt.duongbo.ws.MessageAndResponseImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                vn.dtt.duongbo.ws.MessageAndResponseImplSoapBindingStub _stub = new vn.dtt.duongbo.ws.MessageAndResponseImplSoapBindingStub(new java.net.URL(MessageAndResponseImpl_address), this);
                _stub.setPortName(getMessageAndResponseImplWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MessageAndResponseImpl".equals(inputPortName)) {
            return getMessageAndResponseImpl();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.duongbo.dtt.vn", "MessageAndResponseImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.duongbo.dtt.vn", "MessageAndResponseImpl"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MessageAndResponseImpl".equals(portName)) {
            setMessageAndResponseImplEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
