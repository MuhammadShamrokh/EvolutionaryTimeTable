
package ParsedClasses;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{}ETT-Rule" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="hard-rules-weight" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ettRule"
})
@XmlRootElement(name = "ETT-Rules")
public class ETTRules {

    @XmlElement(name = "ETT-Rule", required = true)
    protected List<ETTRule> ettRule;
    @XmlAttribute(name = "hard-rules-weight", required = true)
    protected int hardRulesWeight;

    /**
     * Gets the value of the ettRule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ettRule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getETTRule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ETTRule }
     * 
     * 
     */
    public List<ETTRule> getETTRule() {
        if (ettRule == null) {
            ettRule = new ArrayList<ETTRule>();
        }
        return this.ettRule;
    }

    /**
     * Gets the value of the hardRulesWeight property.
     * 
     */
    public int getHardRulesWeight() {
        return hardRulesWeight;
    }

    /**
     * Sets the value of the hardRulesWeight property.
     * 
     */
    public void setHardRulesWeight(int value) {
        this.hardRulesWeight = value;
    }

}
