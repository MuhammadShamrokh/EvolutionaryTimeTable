package DataClasses.AlgorithmData;

import javafx.collections.transformation.SortedList;

import java.util.*;

public class Generation{
    List<Parent> m_ParentsList;

    public Generation()
    {
        m_ParentsList =new ArrayList<>();
    }

    public List<Parent> getParentsList() {
        return m_ParentsList;
    }
    public void addParentToGeneration(Parent i_Parent)
    {
        m_ParentsList.add(i_Parent);
    }
    public Integer getGenerationSize() { return m_ParentsList.size(); }
    public Parent getParentByIndex(Integer index) { return m_ParentsList.get(index); }
    public Integer getSumOfFitness(){
        return m_ParentsList.stream().mapToInt(parent-> parent.getFitness()).sum();
    }
    public void sortGenerationByFitness()
    {
        Comparator<Parent> cmp= (p1,p2)->p2.getFitness() - p1.getFitness();
        m_ParentsList.sort(cmp);
    }
    //**
    @Override
    public String toString() {
        return "Generation{"
                + m_ParentsList +
                '}';
    }

}
