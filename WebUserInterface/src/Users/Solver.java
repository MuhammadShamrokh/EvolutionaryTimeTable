package Users;

public class Solver {
    private Integer m_SolverID;
    private String m_SolverName;
    private Integer m_GenerationsMade;
    private Integer m_BestFitness;

    public Solver(Integer i_SolverID, String i_SolverName)
    {
        m_SolverID=i_SolverID;
        m_SolverName=i_SolverName;
        m_GenerationsMade=0;
        m_BestFitness=0;
    }

    public Integer getSolverID() {
        return m_SolverID;
    }

    public String getSolverName() {
        return m_SolverName;
    }

    public void setSolverName(String i_SolverName) {
        this.m_SolverName = i_SolverName;
    }

    public Integer getGenerationsMade() {
        return m_GenerationsMade;
    }

    public Integer getBestFitness() {
        return m_BestFitness;
    }

    public void setBestFitness(Integer i_BestFitness) {
        this.m_BestFitness = i_BestFitness;
    }

    public void setSolverID(Integer i_SolverID) {
        this.m_SolverID = i_SolverID;
    }

    public void setGenerationsMade(Integer i_GenerationsMade) {
        this.m_GenerationsMade = i_GenerationsMade;
    }
}
