
package ParsedClasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{}ETT-Name"/>
 *         &lt;element ref="{}ETT-Requirements"/>
 *       &lt;/all>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "ETT-Class")
public class ETTClass {

    @XmlElement(name = "ETT-Name", required = true)
    protected String ettName;
    @XmlElement(name = "ETT-Requirements", required = true)
    protected ETTRequirements ettRequirements;
    @XmlAttribute(name = "id", required = true)
    protected int id;

    /**
     * Gets the value of the ettName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getETTName() {
        return ettName;
    }

    /**
     * Sets the value of the ettName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setETTName(String value) {
        this.ettName = value;
    }

    /**
     * Gets the value of the ettRequirements property.
     * 
     * @return
     *     possible object is
     *     {@link ETTRequirements }
     *     
     */
    public ETTRequirements getETTRequirements() {
        return ettRequirements;
    }

    /**
     * Sets the value of the ettRequirements property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETTRequirements }
     *     
     */
    public void setETTRequirements(ETTRequirements value) {
        this.ettRequirements = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

}
