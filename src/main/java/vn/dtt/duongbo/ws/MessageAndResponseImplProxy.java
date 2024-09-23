package vn.dtt.duongbo.ws;

public class MessageAndResponseImplProxy implements vn.dtt.duongbo.ws.MessageAndResponseImpl {
  private String _endpoint = null;
  private vn.dtt.duongbo.ws.MessageAndResponseImpl messageAndResponseImpl = null;
  
  public MessageAndResponseImplProxy() {
    _initMessageAndResponseImplProxy();
  }
  
  public MessageAndResponseImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initMessageAndResponseImplProxy();
  }
  
  private void _initMessageAndResponseImplProxy() {
    try {
      messageAndResponseImpl = (new vn.dtt.duongbo.ws.MessageAndResponseImplServiceLocator()).getMessageAndResponseImpl();
      if (messageAndResponseImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)messageAndResponseImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)messageAndResponseImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (messageAndResponseImpl != null)
      ((javax.xml.rpc.Stub)messageAndResponseImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public vn.dtt.duongbo.ws.MessageAndResponseImpl getMessageAndResponseImpl() {
    if (messageAndResponseImpl == null)
      _initMessageAndResponseImplProxy();
    return messageAndResponseImpl;
  }
  
  public java.lang.String sendAndGetMessage(java.lang.String requestMessage) throws java.rmi.RemoteException{
    if (messageAndResponseImpl == null)
      _initMessageAndResponseImplProxy();
    return messageAndResponseImpl.sendAndGetMessage(requestMessage);
  }
  
  
}