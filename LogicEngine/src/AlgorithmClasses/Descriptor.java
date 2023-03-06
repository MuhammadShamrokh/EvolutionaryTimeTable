
package AlgorithmClasses;

import DataClasses.FileInputDataClasses.TimeTable;
import ParsedClasses.ETTDescriptor;

public class Descriptor {

    private TimeTable m_TimeTable;
    private EvolutionEngine m_EvolutionEngine;

    public Descriptor(){}
    public Descriptor(ETTDescriptor i_ETTDescriptor)
    {
        m_TimeTable=new TimeTable(i_ETTDescriptor.getETTTimeTable());
        m_EvolutionEngine=new EvolutionEngine();
    }
    public Descriptor(Descriptor i_Descriptor){
        m_TimeTable=new TimeTable(i_Descriptor.getTimeTable());
        m_EvolutionEngine=new EvolutionEngine();
    }

    public TimeTable getTimeTable() {
        return m_TimeTable;
    }

    public EvolutionEngine getEvolutionEngine() {
        return m_EvolutionEngine;
    }
}
