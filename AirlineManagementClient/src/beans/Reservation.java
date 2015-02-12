/**
 * Reservation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package beans;

public class Reservation  implements java.io.Serializable {
    private java.lang.String airlineName;

    private java.lang.String destination;

    private java.lang.String email;

    private int flightNumber;

    private int numberOfSeats;

    private int reservationId;

    private java.lang.String source;

    public Reservation() {
    }

    public Reservation(
           java.lang.String airlineName,
           java.lang.String destination,
           java.lang.String email,
           int flightNumber,
           int numberOfSeats,
           int reservationId,
           java.lang.String source) {
           this.airlineName = airlineName;
           this.destination = destination;
           this.email = email;
           this.flightNumber = flightNumber;
           this.numberOfSeats = numberOfSeats;
           this.reservationId = reservationId;
           this.source = source;
    }


    /**
     * Gets the airlineName value for this Reservation.
     * 
     * @return airlineName
     */
    public java.lang.String getAirlineName() {
        return airlineName;
    }


    /**
     * Sets the airlineName value for this Reservation.
     * 
     * @param airlineName
     */
    public void setAirlineName(java.lang.String airlineName) {
        this.airlineName = airlineName;
    }


    /**
     * Gets the destination value for this Reservation.
     * 
     * @return destination
     */
    public java.lang.String getDestination() {
        return destination;
    }


    /**
     * Sets the destination value for this Reservation.
     * 
     * @param destination
     */
    public void setDestination(java.lang.String destination) {
        this.destination = destination;
    }


    /**
     * Gets the email value for this Reservation.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this Reservation.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the flightNumber value for this Reservation.
     * 
     * @return flightNumber
     */
    public int getFlightNumber() {
        return flightNumber;
    }


    /**
     * Sets the flightNumber value for this Reservation.
     * 
     * @param flightNumber
     */
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }


    /**
     * Gets the numberOfSeats value for this Reservation.
     * 
     * @return numberOfSeats
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }


    /**
     * Sets the numberOfSeats value for this Reservation.
     * 
     * @param numberOfSeats
     */
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }


    /**
     * Gets the reservationId value for this Reservation.
     * 
     * @return reservationId
     */
    public int getReservationId() {
        return reservationId;
    }


    /**
     * Sets the reservationId value for this Reservation.
     * 
     * @param reservationId
     */
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }


    /**
     * Gets the source value for this Reservation.
     * 
     * @return source
     */
    public java.lang.String getSource() {
        return source;
    }


    /**
     * Sets the source value for this Reservation.
     * 
     * @param source
     */
    public void setSource(java.lang.String source) {
        this.source = source;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Reservation)) return false;
        Reservation other = (Reservation) obj;
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
            ((this.destination==null && other.getDestination()==null) || 
             (this.destination!=null &&
              this.destination.equals(other.getDestination()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            this.flightNumber == other.getFlightNumber() &&
            this.numberOfSeats == other.getNumberOfSeats() &&
            this.reservationId == other.getReservationId() &&
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
        if (getDestination() != null) {
            _hashCode += getDestination().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        _hashCode += getFlightNumber();
        _hashCode += getNumberOfSeats();
        _hashCode += getReservationId();
        if (getSource() != null) {
            _hashCode += getSource().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Reservation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://beans", "Reservation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("airlineName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "airlineName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destination");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "destination"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "email"));
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
        elemField.setFieldName("numberOfSeats");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "numberOfSeats"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reservationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "reservationId"));
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
