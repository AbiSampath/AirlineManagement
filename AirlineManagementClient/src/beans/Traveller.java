/**
 * Traveller.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package beans;

public class Traveller  extends beans.Person  implements java.io.Serializable {
    private java.lang.String customerId;

    private java.lang.String nationality;

    private java.lang.String passportNo;

    private int travellerId;

    public Traveller() {
    }

    public Traveller(
           java.lang.String address,
           java.lang.String city,
           java.lang.String dateOfBirth,
           java.lang.String emailID,
           java.lang.String firstName,
           java.lang.String lastName,
           java.lang.String password,
           int roleID,
           java.lang.String state,
           java.lang.String zipCode,
           java.lang.String customerId,
           java.lang.String nationality,
           java.lang.String passportNo,
           int travellerId) {
        super(
            address,
            city,
            dateOfBirth,
            emailID,
            firstName,
            lastName,
            password,
            roleID,
            state,
            zipCode);
        this.customerId = customerId;
        this.nationality = nationality;
        this.passportNo = passportNo;
        this.travellerId = travellerId;
    }


    /**
     * Gets the customerId value for this Traveller.
     * 
     * @return customerId
     */
    public java.lang.String getCustomerId() {
        return customerId;
    }


    /**
     * Sets the customerId value for this Traveller.
     * 
     * @param customerId
     */
    public void setCustomerId(java.lang.String customerId) {
        this.customerId = customerId;
    }


    /**
     * Gets the nationality value for this Traveller.
     * 
     * @return nationality
     */
    public java.lang.String getNationality() {
        return nationality;
    }


    /**
     * Sets the nationality value for this Traveller.
     * 
     * @param nationality
     */
    public void setNationality(java.lang.String nationality) {
        this.nationality = nationality;
    }


    /**
     * Gets the passportNo value for this Traveller.
     * 
     * @return passportNo
     */
    public java.lang.String getPassportNo() {
        return passportNo;
    }


    /**
     * Sets the passportNo value for this Traveller.
     * 
     * @param passportNo
     */
    public void setPassportNo(java.lang.String passportNo) {
        this.passportNo = passportNo;
    }


    /**
     * Gets the travellerId value for this Traveller.
     * 
     * @return travellerId
     */
    public int getTravellerId() {
        return travellerId;
    }


    /**
     * Sets the travellerId value for this Traveller.
     * 
     * @param travellerId
     */
    public void setTravellerId(int travellerId) {
        this.travellerId = travellerId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Traveller)) return false;
        Traveller other = (Traveller) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.customerId==null && other.getCustomerId()==null) || 
             (this.customerId!=null &&
              this.customerId.equals(other.getCustomerId()))) &&
            ((this.nationality==null && other.getNationality()==null) || 
             (this.nationality!=null &&
              this.nationality.equals(other.getNationality()))) &&
            ((this.passportNo==null && other.getPassportNo()==null) || 
             (this.passportNo!=null &&
              this.passportNo.equals(other.getPassportNo()))) &&
            this.travellerId == other.getTravellerId();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getCustomerId() != null) {
            _hashCode += getCustomerId().hashCode();
        }
        if (getNationality() != null) {
            _hashCode += getNationality().hashCode();
        }
        if (getPassportNo() != null) {
            _hashCode += getPassportNo().hashCode();
        }
        _hashCode += getTravellerId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Traveller.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://beans", "Traveller"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "customerId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nationality");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "nationality"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passportNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "passportNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("travellerId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "travellerId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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
