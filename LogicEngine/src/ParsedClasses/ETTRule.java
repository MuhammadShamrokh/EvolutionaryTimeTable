
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
 *       &lt;sequence>
 *         &lt;element ref="{}ETT-RuleId"/>
 *         &lt;element ref="{}ETT-Configuration" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Hard"/>
 *             &lt;enumeration value="Soft"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ettRuleId",
    "ettConfiguration"
})
@XmlRootElement(name = "ETT-Rule")
public class ETTRule {

    @XmlElement(name = "ETT-RuleId", required = true)
    protected String ettRuleId;
    @XmlElement(name = "ETT-Configuration")
    protected String ettConfiguration;
    @XmlAttribute(name = "type", required = true)
    protected String type;

    /**
     * Gets the value of the ettRuleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getETTRuleId() {
        return ettRuleId;
    }

    /**
     * Sets the value of the ettRuleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setETTRuleId(String value) {
        this.ettRuleId = value;
    }

    /**
     * Gets the value of the ettConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getETTConfiguration() {
        return ettConfiguration;
    }

    /**
     * Sets the value of the ettConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setETTConfiguration(String value) {
        this.ettConfiguration = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
