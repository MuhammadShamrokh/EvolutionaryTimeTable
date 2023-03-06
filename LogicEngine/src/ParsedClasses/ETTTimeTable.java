
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
 *         &lt;element ref="{}ETT-Teachers"/>
 *         &lt;element ref="{}ETT-Subjects"/>
 *         &lt;element ref="{}ETT-Classes"/>
 *         &lt;element ref="{}ETT-Rules"/>
 *       &lt;/sequence>
 *       &lt;attribute name="hours" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="days" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ettTeachers",
    "ettSubjects",
    "ettClasses",
    "ettRules"
})
@XmlRootElement(name = "ETT-TimeTable")
public class ETTTimeTable {

    @XmlElement(name = "ETT-Teachers", required = true)
    protected ETTTeachers ettTeachers;
    @XmlElement(name = "ETT-Subjects", required = true)
    protected ETTSubjects ettSubjects;
    @XmlElement(name = "ETT-Classes", required = true)
    protected ETTClasses ettClasses;
    @XmlElement(name = "ETT-Rules", required = true)
    protected ETTRules ettRules;
    @XmlAttribute(name = "hours", required = true)
    protected int hours;
    @XmlAttribute(name = "days", required = true)
    protected int days;

    /**
     * Gets the value of the ettTeachers property.
     * 
     * @return
     *     possible object is
     *     {@link ETTTeachers }
     *     
     */
    public ETTTeachers getETTTeachers() {
        return ettTeachers;
    }

    /**
     * Sets the value of the ettTeachers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETTTeachers }
     *     
     */
    public void setETTTeachers(ETTTeachers value) {
        this.ettTeachers = value;
    }

    /**
     * Gets the value of the ettSubjects property.
     * 
     * @return
     *     possible object is
     *     {@link ETTSubjects }
     *     
     */
    public ETTSubjects getETTSubjects() {
        return ettSubjects;
    }

    /**
     * Sets the value of the ettSubjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETTSubjects }
     *     
     */
    public void setETTSubjects(ETTSubjects value) {
        this.ettSubjects = value;
    }

    /**
     * Gets the value of the ettClasses property.
     * 
     * @return
     *     possible object is
     *     {@link ETTClasses }
     *     
     */
    public ETTClasses getETTClasses() {
        return ettClasses;
    }

    /**
     * Sets the value of the ettClasses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETTClasses }
     *     
     */
    public void setETTClasses(ETTClasses value) {
        this.ettClasses = value;
    }

    /**
     * Gets the value of the ettRules property.
     * 
     * @return
     *     possible object is
     *     {@link ETTRules }
     *     
     */
    public ETTRules getETTRules() {
        return ettRules;
    }

    /**
     * Sets the value of the ettRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETTRules }
     *     
     */
    public void setETTRules(ETTRules value) {
        this.ettRules = value;
    }

    /**
     * Gets the value of the hours property.
     * 
     */
    public int getHours() {
        return hours;
    }

    /**
     * Sets the value of the hours property.
     * 
     */
    public void setHours(int value) {
        this.hours = value;
    }

    /**
     * Gets the value of the days property.
     * 
     */
    public int getDays() {
        return days;
    }

    /**
     * Sets the value of the days property.
     * 
     */
    public void setDays(int value) {
        this.days = value;
    }

}
