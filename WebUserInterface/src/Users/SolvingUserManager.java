package Users;

import DataTransferClasses.SolverData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolvingUserManager {
    private List<Solver> m_SolversList;

    public SolvingUserManager() {
        m_SolversList = new ArrayList<>();
    }

    public synchronized void addSolver(Solver i_Solver) {
        m_SolversList.add(i_Solver);
    }

    public Solver getSolver(String i_SolverName)
    {
        Solver retSolver=null;
        for(Solver solver:m_SolversList)
        {
            if(solver.getSolverName().equals(i_SolverName))
            {
                retSolver=solver;
                break;
            }
        }
        return retSolver;
    }

    public Integer getBestFitnessFromAllSolvers()
    {
        Integer retBestFitness =0;
        for(Solver solver:m_SolversList)
        {
            if(solver.getBestFitness()> retBestFitness)
                retBestFitness=solver.getBestFitness();
        }
        return retBestFitness;
    }

    public synchronized void removeSolver(String i_SolverName) {
        Solver wantedSolver=null;
        for(Solver solver:m_SolversList)
        {
            if(solver.getSolverName().equals(i_SolverName));
            wantedSolver=solver;
        }
        m_SolversList.remove(wantedSolver);
    }

    public synchronized List<Solver> getSolversList() {
        return Collections.unmodifiableList(m_SolversList);
    }

    public boolean isUserExists(String i_SolverName) {
        boolean retVal=false;
        for(Solver solver:m_SolversList)
        {
            if(solver.getSolverName().equals(i_SolverName))
            {
                retVal=true;
                break;
            }
        }
        return retVal;
    }

    public Integer getSolversAmount() { return m_SolversList.size(); }

    public List<SolverData> getSolversDataList()
    {
        List<SolverData> retList=new ArrayList<>();
        m_SolversList.forEach(solver-> retList.add(new SolverData(solver.getSolverID(),solver.getSolverName(),solver.getGenerationsMade(),solver.getBestFitness())));
        return retList;
    }

}
