//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.25 at 11:31:34 PM ALMT 
//


package com.baeldung.springsoap.gen;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employee" type="{http://www.baeldung.com/springsoap/gen}employee"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "employee"
})
@XmlRootElement(name = "setEmployeeRequest")
public class SetEmployeeRequest {

    @XmlElement(required = true)
    protected Employee employee;

    /**
     * Gets the value of the employee property.
     *
     * @return possible object is
     * {@link Employee }
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the value of the employee property.
     *
     * @param value allowed object is
     *              {@link Employee }
     */
    public void setEmployee(Employee value) {
        this.employee = value;
    }

}
