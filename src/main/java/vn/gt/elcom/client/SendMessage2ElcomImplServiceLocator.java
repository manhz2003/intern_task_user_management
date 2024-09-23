/**
 * SendMessage2ElcomImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.gt.elcom.client;

public class SendMessage2ElcomImplServiceLocator extends org.apache.axis.client.Service implements vn.gt.elcom.client.SendMessage2ElcomImplService {

    public SendMessage2ElcomImplServiceLocator() {
    }


    public SendMessage2ElcomImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SendMessage2ElcomImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SendMessage2ElcomImpl
    private java.lang.String SendMessage2ElcomImpl_address = "http://kiemthu.mt.gov.vn:6001/TichHopGiaoThong-portlet/services/SendMessage2ElcomImpl";

    public java.lang.String getSendMessage2ElcomImplAddress() {
        return SendMessage2ElcomImpl_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SendMessage2ElcomImplWSDDServiceName = "SendMessage2ElcomImpl";

    public java.lang.String getSendMessage2ElcomImplWSDDServiceName() {
        return SendMessage2ElcomImplWSDDServiceName;
    }

    public void setSendMessage2ElcomImplWSDDServiceName(java.lang.String name) {
        SendMessage2ElcomImplWSDDServiceName = name;
    }

    public vn.gt.elcom.client.SendMessage2ElcomImpl getSendMessage2ElcomImpl() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SendMessage2ElcomImpl_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSendMessage2ElcomImpl(endpoint);
    }

    public vn.gt.elcom.client.SendMessage2ElcomImpl getSendMessage2ElcomImpl(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            vn.gt.elcom.client.SendMessage2ElcomImplSoapBindingStub _stub = new vn.gt.elcom.client.SendMessage2ElcomImplSoapBindingStub(portAddress, this);
            _stub.setPortName(getSendMessage2ElcomImplWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSendMessage2ElcomImplEndpointAddress(java.lang.String address) {
        SendMessage2ElcomImpl_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (vn.gt.elcom.client.SendMessage2ElcomImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                vn.gt.elcom.client.SendMessage2ElcomImplSoapBindingStub _stub = new vn.gt.elcom.client.SendMessage2ElcomImplSoapBindingStub(new java.net.URL(SendMessage2ElcomImpl_address), this);
                _stub.setPortName(getSendMessage2ElcomImplWSDDServiceName());
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
        if ("SendMessage2ElcomImpl".equals(inputPortName)) {
            return getSendMessage2ElcomImpl();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://elcom.gt.vn", "SendMessage2ElcomImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://elcom.gt.vn", "SendMessage2ElcomImpl"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SendMessage2ElcomImpl".equals(portName)) {
            setSendMessage2ElcomImplEndpointAddress(address);
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
