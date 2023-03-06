package DataTransferClasses;



public class SolverData {
    private Integer m_SolverID;
    private String m_SolverName;
    private Integer m_GenerationsMade;
    private Integer m_BestFitness;

    public SolverData(Integer i_SolverID,String i_SolverName,Integer i_Generations,Integer i_BestFitness)
    {
        m_SolverID=i_SolverID;
        m_SolverName=i_SolverName;
        m_GenerationsMade=i_Generations;
        m_BestFitness=i_BestFitness;
    }

    public String getSolverName() {
        return m_SolverName;
    }

    public Integer getGenerationsMade() {
        return m_GenerationsMade;
    }

    public Integer getBestFitness() {
        return m_BestFitness;
    }

}
