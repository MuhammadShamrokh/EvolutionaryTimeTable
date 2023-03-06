
package ParsedClasses;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ParsedClasses package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ETTName_QNAME = new QName("", "ETT-Name");
    private final static QName _ETTRuleId_QNAME = new QName("", "ETT-RuleId");
    private final static QName _ETTConfiguration_QNAME = new QName("", "ETT-Configuration");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ParsedClasses
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ETTRequirements }
     * 
     */
    public ETTRequirements createETTRequirements() {
        return new ETTRequirements();
    }

    /**
     * Create an instance of {@link ETTStudy }
     * 
     */
    public ETTStudy createETTStudy() {
        return new ETTStudy();
    }

    /**
     * Create an instance of {@link ETTSubjects }
     * 
     */
    public ETTSubjects createETTSubjects() {
        return new ETTSubjects();
    }

    /**
     * Create an instance of {@link ETTSubject }
     * 
     */
    public ETTSubject createETTSubject() {
        return new ETTSubject();
    }

    /**
     * Create an instance of {@link ETTTeacher }
     * 
     */
    public ETTTeacher createETTTeacher() {
        return new ETTTeacher();
    }

    /**
     * Create an instance of {@link ETTTeaching }
     * 
     */
    public ETTTeaching createETTTeaching() {
        return new ETTTeaching();
    }

    /**
     * Create an instance of {@link ETTTeaches }
     * 
     */
    public ETTTeaches createETTTeaches() {
        return new ETTTeaches();
    }

    /**
     * Create an instance of {@link ETTRules }
     * 
     */
    public ETTRules createETTRules() {
        return new ETTRules();
    }

    /**
     * Create an instance of {@link ETTRule }
     * 
     */
    public ETTRule createETTRule() {
        return new ETTRule();
    }

    /**
     * Create an instance of {@link ETTDescriptor }
     * 
     */
    public ETTDescriptor createETTDescriptor() {
        return new ETTDescriptor();
    }

    /**
     * Create an instance of {@link ETTTimeTable }
     * 
     */
    public ETTTimeTable createETTTimeTable() {
        return new ETTTimeTable();
    }

    /**
     * Create an instance of {@link ETTTeachers }
     * 
     */
    public ETTTeachers createETTTeachers() {
        return new ETTTeachers();
    }

    /**
     * Create an instance of {@link ETTClasses }
     * 
     */
    public ETTClasses createETTClasses() {
        return new ETTClasses();
    }

    /**
     * Create an instance of {@link ETTClass }
     * 
     */
    public ETTClass createETTClass() {
        return new ETTClass();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ETT-Name")
    public JAXBElement<String> createETTName(String value) {
        return new JAXBElement<String>(_ETTName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ETT-RuleId")
    public JAXBElement<String> createETTRuleId(String value) {
        return new JAXBElement<String>(_ETTRuleId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ETT-Configuration")
    public JAXBElement<String> createETTConfiguration(String value) {
        return new JAXBElement<String>(_ETTConfiguration_QNAME, String.class, null, value);
    }

}
