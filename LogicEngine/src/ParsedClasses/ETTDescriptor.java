
package ParsedClasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{}ETT-TimeTable"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ettTimeTable"
})
@XmlRootElement(name = "ETT-Descriptor")
public class ETTDescriptor {

    @XmlElement(name = "ETT-TimeTable", required = true)
    protected ETTTimeTable ettTimeTable;

    /**
     * Gets the value of the ettTimeTable property.
     * 
     * @return
     *     possible object is
     *     {@link ETTTimeTable }
     *     
     */
    public ETTTimeTable getETTTimeTable() {
        return ettTimeTable;
    }

    /**
     * Sets the value of the ettTimeTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETTTimeTable }
     *     
     */
    public void setETTTimeTable(ETTTimeTable value) {
        this.ettTimeTable = value;
    }

}
