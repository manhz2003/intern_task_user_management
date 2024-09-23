package vn.gt.elcom.client;

public class SendMessage2ElcomImplProxy implements vn.gt.elcom.client.SendMessage2ElcomImpl {
  private String _endpoint = null;
  private vn.gt.elcom.client.SendMessage2ElcomImpl sendMessage2ElcomImpl = null;
  
  public SendMessage2ElcomImplProxy() {
    _initSendMessage2ElcomImplProxy();
  }
  
  public SendMessage2ElcomImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initSendMessage2ElcomImplProxy();
  }
  
  private void _initSendMessage2ElcomImplProxy() {
    try {
      sendMessage2ElcomImpl = (new vn.gt.elcom.client.SendMessage2ElcomImplServiceLocator()).getSendMessage2ElcomImpl();
      if (sendMessage2ElcomImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sendMessage2ElcomImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sendMessage2ElcomImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sendMessage2ElcomImpl != null)
      ((javax.xml.rpc.Stub)sendMessage2ElcomImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public vn.gt.elcom.client.SendMessage2ElcomImpl getSendMessage2ElcomImpl() {
    if (sendMessage2ElcomImpl == null)
      _initSendMessage2ElcomImplProxy();
    return sendMessage2ElcomImpl;
  }
  
  public java.lang.String sendAndGetMessage(java.lang.String requestMessage) throws java.rmi.RemoteException{
    if (sendMessage2ElcomImpl == null)
      _initSendMessage2ElcomImplProxy();
    return sendMessage2ElcomImpl.sendAndGetMessage(requestMessage);
  }
  
  
}