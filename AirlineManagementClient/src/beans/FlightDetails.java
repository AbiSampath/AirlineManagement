/**
 * FlightDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package beans;

public class FlightDetails  implements java.io.Serializable {
    private java.lang.String airlineName;

    private int crewId;

    private java.lang.String destination;

    private int flightNumber;

    private java.lang.String flightTime;

    private int numberOfSeats;

    private java.lang.String source;

    public FlightDetails() {
    }

    public FlightDetails(
           java.lang.String airlineName,
           int crewId,
           java.lang.String destination,
           int flightNumber,
           java.lang.String flightTime,
           int numberOfSeats,
           java.lang.String source) {
           this.airlineName = airlineName;
           this.crewId = crewId;
           this.destination = destination;
           this.flightNumber = flightNumber;
           this.flightTime = flightTime;
           this.numberOfSeats = numberOfSeats;
           this.source = source;
    }


    /**
     * Gets the airlineName value for this FlightDetails.
     * 
     * @return airlineName
     */
    public java.lang.String getAirlineName() {
        return airlineName;
    }


    /**
     * Sets the airlineName value for this FlightDetails.
     * 
     * @param airlineName
     */
    public void setAirlineName(java.lang.String airlineName) {
        this.airlineName = airlineName;
    }


    /**
     * Gets the crewId value for this FlightDetails.
     * 
     * @return crewId
     */
    public int getCrewId() {
        return crewId;
    }


    /**
     * Sets the crewId value for this FlightDetails.
     * 
     * @param crewId
     */
    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }


    /**
     * Gets the destination value for this FlightDetails.
     * 
     * @return destination
     */
    public java.lang.String getDestination() {
        return destination;
    }


    /**
     * Sets the destination value for this FlightDetails.
     * 
     * @param destination
     */
    public void setDestination(java.lang.String destination) {
        this.destination = destination;
    }


    /**
     * Gets the flightNumber value for this FlightDetails.
     * 
     * @return flightNumber
     */
    public int getFlightNumber() {
        return flightNumber;
    }


    /**
     * Sets the flightNumber value for this FlightDetails.
     * 
     * @param flightNumber
     */
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }


    /**
     * Gets the flightTime value for this FlightDetails.
     * 
     * @return flightTime
     */
    public java.lang.String getFlightTime() {
        return flightTime;
    }


    /**
     * Sets the flightTime value for this FlightDetails.
     * 
     * @param flightTime
     */
    public void setFlightTime(java.lang.String flightTime) {
        this.flightTime = flightTime;
    }


    /**
     * Gets the numberOfSeats value for this FlightDetails.
     * 
     * @return numberOfSeats
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }


    /**
     * Sets the numberOfSeats value for this FlightDetails.
     * 
     * @param numberOfSeats
     */
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }


    /**
     * Gets the source value for this FlightDetails.
     * 
     * @return source
     */
    public java.lang.String getSource() {
        return source;
    }


    /**
     * Sets the source value for this FlightDetails.
     * 
     * @param source
     */
    public void setSource(java.lang.String source) {
        this.source = source;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FlightDetails)) return false;
        FlightDetails other = (FlightDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.airlineName==null && other.getAirlineName()==null) || 
             (this.airlineName!=null &&
              this.airlineName.equals(other.getAirlineName()))) &&
            this.crewId == other.getCrewId() &&
            ((this.destination==null && other.getDestination()==null) || 
             (this.destination!=null &&
              this.destination.equals(other.getDestination()))) &&
            this.flightNumber == other.getFlightNumber() &&
            ((this.flightTime==null && other.getFlightTime()==null) || 
             (this.flightTime!=null &&
              this.flightTime.equals(other.getFlightTime()))) &&
            this.numberOfSeats == other.getNumberOfSeats() &&
            ((this.source==null && other.getSource()==null) || 
             (this.source!=null &&
              this.source.equals(other.getSource())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAirlineName() != null) {
            _hashCode += getAirlineName().hashCode();
        }
        _hashCode += getCrewId();
        if (getDestination() != null) {
            _hashCode += getDestination().hashCode();
        }
        _hashCode += getFlightNumber();
        if (getFlightTime() != null) {
            _hashCode += getFlightTime().hashCode();
        }
        _hashCode += getNumberOfSeats();
        if (getSource() != null) {
            _hashCode += getSource().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FlightDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://beans", "FlightDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("airlineName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "airlineName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crewId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "crewId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destination");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "destination"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flightNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "flightNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flightTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "flightTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfSeats");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "numberOfSeats"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("source");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "source"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
