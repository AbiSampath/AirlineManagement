/**
 * Employee.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package beans;

public class Employee  extends beans.Person  implements java.io.Serializable {
    private int employeeID;

    private java.lang.String hireDate;

    private java.lang.String position;

    private int uniqueID;

    private java.lang.String workDescription;

    public Employee() {
    }

    public Employee(
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
           int employeeID,
           java.lang.String hireDate,
           java.lang.String position,
           int uniqueID,
           java.lang.String workDescription) {
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
        this.employeeID = employeeID;
        this.hireDate = hireDate;
        this.position = position;
        this.uniqueID = uniqueID;
        this.workDescription = workDescription;
    }


    /**
     * Gets the employeeID value for this Employee.
     * 
     * @return employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }


    /**
     * Sets the employeeID value for this Employee.
     * 
     * @param employeeID
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }


    /**
     * Gets the hireDate value for this Employee.
     * 
     * @return hireDate
     */
    public java.lang.String getHireDate() {
        return hireDate;
    }


    /**
     * Sets the hireDate value for this Employee.
     * 
     * @param hireDate
     */
    public void setHireDate(java.lang.String hireDate) {
        this.hireDate = hireDate;
    }


    /**
     * Gets the position value for this Employee.
     * 
     * @return position
     */
    public java.lang.String getPosition() {
        return position;
    }


    /**
     * Sets the position value for this Employee.
     * 
     * @param position
     */
    public void setPosition(java.lang.String position) {
        this.position = position;
    }


    /**
     * Gets the uniqueID value for this Employee.
     * 
     * @return uniqueID
     */
    public int getUniqueID() {
        return uniqueID;
    }


    /**
     * Sets the uniqueID value for this Employee.
     * 
     * @param uniqueID
     */
    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }


    /**
     * Gets the workDescription value for this Employee.
     * 
     * @return workDescription
     */
    public java.lang.String getWorkDescription() {
        return workDescription;
    }


    /**
     * Sets the workDescription value for this Employee.
     * 
     * @param workDescription
     */
    public void setWorkDescription(java.lang.String workDescription) {
        this.workDescription = workDescription;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Employee)) return false;
        Employee other = (Employee) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.employeeID == other.getEmployeeID() &&
            ((this.hireDate==null && other.getHireDate()==null) || 
             (this.hireDate!=null &&
              this.hireDate.equals(other.getHireDate()))) &&
            ((this.position==null && other.getPosition()==null) || 
             (this.position!=null &&
              this.position.equals(other.getPosition()))) &&
            this.uniqueID == other.getUniqueID() &&
            ((this.workDescription==null && other.getWorkDescription()==null) || 
             (this.workDescription!=null &&
              this.workDescription.equals(other.getWorkDescription())));
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
        _hashCode += getEmployeeID();
        if (getHireDate() != null) {
            _hashCode += getHireDate().hashCode();
        }
        if (getPosition() != null) {
            _hashCode += getPosition().hashCode();
        }
        _hashCode += getUniqueID();
        if (getWorkDescription() != null) {
            _hashCode += getWorkDescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Employee.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://beans", "Employee"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("employeeID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "employeeID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hireDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "hireDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("position");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "position"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uniqueID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "uniqueID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "workDescription"));
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
