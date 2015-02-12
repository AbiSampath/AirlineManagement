/**
 * AirlineServerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package airlineSystem;

public interface AirlineServerService extends javax.xml.rpc.Service {
    public java.lang.String getAirlineServerAddress();

    public airlineSystem.AirlineServer getAirlineServer() throws javax.xml.rpc.ServiceException;

    public airlineSystem.AirlineServer getAirlineServer(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
