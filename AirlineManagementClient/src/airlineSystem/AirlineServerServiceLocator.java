/**
 * AirlineServerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package airlineSystem;

public class AirlineServerServiceLocator extends org.apache.axis.client.Service implements airlineSystem.AirlineServerService {

    public AirlineServerServiceLocator() {
    }


    public AirlineServerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AirlineServerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AirlineServer
    private java.lang.String AirlineServer_address = "http://localhost:8080/AirlineManagement/services/AirlineServer";

    public java.lang.String getAirlineServerAddress() {
        return AirlineServer_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AirlineServerWSDDServiceName = "AirlineServer";

    public java.lang.String getAirlineServerWSDDServiceName() {
        return AirlineServerWSDDServiceName;
    }

    public void setAirlineServerWSDDServiceName(java.lang.String name) {
        AirlineServerWSDDServiceName = name;
    }

    public airlineSystem.AirlineServer getAirlineServer() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AirlineServer_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAirlineServer(endpoint);
    }

    public airlineSystem.AirlineServer getAirlineServer(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            airlineSystem.AirlineServerSoapBindingStub _stub = new airlineSystem.AirlineServerSoapBindingStub(portAddress, this);
            _stub.setPortName(getAirlineServerWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAirlineServerEndpointAddress(java.lang.String address) {
        AirlineServer_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (airlineSystem.AirlineServer.class.isAssignableFrom(serviceEndpointInterface)) {
                airlineSystem.AirlineServerSoapBindingStub _stub = new airlineSystem.AirlineServerSoapBindingStub(new java.net.URL(AirlineServer_address), this);
                _stub.setPortName(getAirlineServerWSDDServiceName());
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
        if ("AirlineServer".equals(inputPortName)) {
            return getAirlineServer();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://airlineSystem", "AirlineServerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://airlineSystem", "AirlineServer"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AirlineServer".equals(portName)) {
            setAirlineServerEndpointAddress(address);
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
