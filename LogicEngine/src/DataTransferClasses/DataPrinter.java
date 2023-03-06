package DataTransferClasses;

import java.util.*;

public class DataPrinter {
    private Set<SubjectData> m_Subjects;
    private Map<Integer, Set<SubjectData>> m_TeacherID2SubjectsSMap;
    private Map<Integer, Set<StudyData>> m_ClassesID2SubjMap;
    private Map<String, String> m_RulesNames2TypeMap;
    private Integer m_InitialPopulation;
    private SelectionData m_SelectionData;
    private CrossoverData m_CrossoverData;
    private List<MutationData> m_MutationsDataList;

    private List<String> m_CrossoverNamesList;
    private List<String> m_SelectionNamesList;

    public DataPrinter()
    {
        m_CrossoverNamesList=new ArrayList<>();
        for(AlgorithmClasses.eCrossover cross:AlgorithmClasses.eCrossover.values())
        {
            m_CrossoverNamesList.add(cross.toString());
        }

        m_SelectionNamesList=new ArrayList<>();
        for(AlgorithmClasses.eSelection selection:AlgorithmClasses.eSelection.values())
        {
            m_SelectionNamesList.add(selection.toString());
        }
    }

    public Set<SubjectData> getSubjectsSet() {
        return m_Subjects;
    }
    public void SetSubjectsSet(Set<SubjectData> i_SubjectSet) {
        this.m_Subjects = i_SubjectSet;
    }

    public Map<Integer, Set<SubjectData>> getTeacherID2SubjectsMap() {
        return m_TeacherID2SubjectsSMap;
    }
    public void setTeacherID2SubjectsMap(Map<Integer, Set<SubjectData>> i_TeachersID2SubjectsMap) {
        this.m_TeacherID2SubjectsSMap = i_TeachersID2SubjectsMap;
    }

    public Map<Integer, Set<StudyData>> getClassID2SubjectsMap() {
        return m_ClassesID2SubjMap;
    }
    public void setClassesID2SubjMap(Map<Integer, Set<StudyData>> i_ClassesID2SubjMap) {
        this.m_ClassesID2SubjMap = i_ClassesID2SubjMap;
    }


    public Map<String, String> getRulesNames2TypeMap() {
        return m_RulesNames2TypeMap;
    }
    public void setRulesNames2TypeMap(Map<String, String> i_RulesNames2TypeMap) {
        this.m_RulesNames2TypeMap = i_RulesNames2TypeMap;
    }

    public Integer getInitialPopulation() {
        return m_InitialPopulation;
    }

    public void setInitialPopulation(Integer i_InitialPopulation) {
        this.m_InitialPopulation = i_InitialPopulation;
    }

    public SelectionData getSelectionData() {
        return m_SelectionData;
    }

    public void setSelectionData(SelectionData i_SelectionData) {
        this.m_SelectionData = i_SelectionData;
    }

    public CrossoverData getCrossoverData() {
        return m_CrossoverData;
    }

    public void setCrossoverData(CrossoverData i_CrossoverData) {
        this.m_CrossoverData = i_CrossoverData;
    }

    public List<MutationData> getMutationsDataList() {
        return m_MutationsDataList;
    }

    public void setMutationsDataList(List<MutationData> i_MutationsDataList) {
        this.m_MutationsDataList = i_MutationsDataList;
    }

    public void replaceMutationData(Integer i_Index, MutationData i_MutationData)
    {
        m_MutationsDataList.set(i_Index,i_MutationData);
    }

    public List<String> getCrossoverNamesList() {
        return m_CrossoverNamesList;
    }

    public List<String> getSelectionNamesList() {
        return m_SelectionNamesList;
    }
}