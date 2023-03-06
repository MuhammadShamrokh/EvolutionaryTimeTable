package Manager;

import AlgorithmClasses.*;
import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.FileInputDataClasses.CheckValidData;
import DataClasses.FileInputDataClasses.Clazz;
import DataClasses.FileInputDataClasses.Rule;
import DataClasses.FileInputDataClasses.TimeTable;
import DataTransferClasses.*;
import ParsedClasses.ETTDescriptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


public class LogicEngineManager {
    private Descriptor m_Descriptor;
    private EvolutionEngineData m_EvolutionEngineData;
    private AmountOfObjectsCalc m_MaxAmountOfObjects;
    private Integer m_ProblemIndex;
    private boolean m_IsFileLoaded = false;
    private boolean m_IsAlgoActivated = false;
    private Boolean m_IsAlgoRunning = false;

    public LogicEngineManager() {
        m_ProblemIndex = 0;
    }

    public LogicEngineManager(LogicEngineManager i_LogicEngineManager) {
        m_Descriptor = new Descriptor(i_LogicEngineManager.getDescriptor());
        m_MaxAmountOfObjects = i_LogicEngineManager.copyAmountOfData();
    }

    public Map<Integer, Integer> PrintAlgorithmProcess() {
        if (m_IsFileLoaded) {
            if (m_IsAlgoActivated) {
                return m_EvolutionEngineData.getGeneration2BestFitnessMap();
            } else {
                throw new RuntimeException("ERROR: Please activate the algorithm first");
            }
        } else {
            throw new RuntimeException("ERROR: Please load a file, then activate the algorithm before choosing this option");
        }
    }

    public void ActivateAlgorithm(Integer i_ReqGenerations, Integer i_PrintingReq, Integer i_ReqFitness, Integer i_ReqTimeInMinutes, Consumer<ProgressData> i_ProgressDataConsumer, Collection<eStoppingCondition> i_StopConditions) {
        if (m_IsFileLoaded) {
            m_EvolutionEngineData = new EvolutionEngineData();
            m_Descriptor.getEvolutionEngine().setNumOfGenerations(i_ReqGenerations);
            m_Descriptor.getEvolutionEngine().setPrintingReq(i_PrintingReq);
            m_Descriptor.getEvolutionEngine().setReqFitness(i_ReqFitness);
            m_Descriptor.getEvolutionEngine().setReqMinutes(i_ReqTimeInMinutes);
            m_IsAlgoActivated = true;
            //m_Descriptor.getEvolutionEngine().activateAlgorithm(m_Descriptor.getTimeTable(),m_MaxAmountOfObjects,m_EvolutionEngineData,i_ProgressDataConsumer,i_StopConditions);
        } else {
            throw new RuntimeException("ERROR: No file has been loaded, Please load a file before choosing this option.");
        }
    }

    public void ActivateAlgorithm(Consumer<ProgressData> i_ProgressDataConsumer, Integer i_PrintingReq) {
        if (m_IsFileLoaded) {
            m_EvolutionEngineData = new EvolutionEngineData();
            m_Descriptor.getEvolutionEngine().setPrintingReq(i_PrintingReq);
            m_IsAlgoActivated = true;
            setIsAlgoRunning(true);
            m_Descriptor.getEvolutionEngine().activateAlgorithm(m_Descriptor.getTimeTable(), m_MaxAmountOfObjects, m_EvolutionEngineData, i_ProgressDataConsumer);
            setIsAlgoRunning(false);
        } else {
            throw new RuntimeException("ERROR: No file has been loaded, Please load a file before choosing this option.");
        }
    }

    public void resumeAlgo() {
        m_Descriptor.getEvolutionEngine().resumeAlgo();
    }

    public AlgorithmReferenceData getAlgoRefData() {
        return m_Descriptor.getEvolutionEngine().getAlgoReferenceData();
    }

    public boolean getIsAlgoRunning() {
        synchronized (m_IsAlgoRunning) {
            return m_IsAlgoRunning;
        }
    }

    public void setIsAlgoRunning(Boolean i_IsAlgoRunning)
    {
        synchronized (m_IsAlgoRunning) {
            m_IsAlgoRunning=i_IsAlgoRunning;
        }
    }

    public Integer getInitialPopulation()
    {
        return m_Descriptor.getEvolutionEngine().getInitialPopulation();
    }

    public Integer getAmountOfDays() { return m_Descriptor.getTimeTable().getDays(); }

    public Integer getAmountOfHours() { return m_Descriptor.getTimeTable().getHours(); }

    public Integer getReqGenerations()
    {
        return m_Descriptor.getEvolutionEngine().getNumOfGenerations();
    }

    public Integer getPrintingReq()
    {
        return m_Descriptor.getEvolutionEngine().getPrintingReq();
    }
    public MutationData getMutationDataByIndex(Integer i_Index)
    {
        return m_Descriptor.getEvolutionEngine().getMutations().getMutationDataByIndex(i_Index);
    }
    public void addNewMutationToList(String i_Name,String i_Tupples,String i_Char,String i_Probability)
    {
        m_Descriptor.getEvolutionEngine().getMutations().createAndAddMutationToList(i_Name,i_Tupples,i_Char,i_Probability);
    }

    public void deleteMutationByIndex(Integer i_MutationIndex)
    {
        m_Descriptor.getEvolutionEngine().getMutations().deleteMutationByIndex(i_MutationIndex);
    }

    public void updateMutation(Integer i_Index,String i_Name,String i_Tupples,String i_Char,String i_Probability)
    {
        m_Descriptor.getEvolutionEngine().getMutations().updateMutation(i_Index,i_Name,i_Tupples,i_Char,i_Probability);
    }

    public void updateAlgoReference(String i_InitialPopulation,String i_ReqGenerations,String i_ReqFitness,String i_ReqTimeInMinutes,
                              String i_CrossoverName,String i_NumOfCuttingPoints,String i_CrossoverComponent,
                              String i_SelectionType, String i_Percent, String i_PTE, String i_Elitism)
    {
        //checking stop conditions client chose and updating them(it also check elitism)
        checkAndUpdateStoppingArguments(i_InitialPopulation,i_ReqGenerations,i_ReqFitness,i_ReqTimeInMinutes);
        //checking crossover and selections arguments and updating them
        checkAndUpdateAlgoRefArguments(i_CrossoverName,i_NumOfCuttingPoints,i_CrossoverComponent,i_SelectionType,i_Percent,i_PTE,i_Elitism,i_InitialPopulation);


    }
    private void checkAndUpdateAlgoRefArguments(String i_CrossoverName,String i_NumOfCuttingPoints,String i_CrossoverComponent,
                                                String i_SelectionType, String i_Percent, String i_PTE, String i_Elitism,String i_InitialPopulation) {
        m_Descriptor.getEvolutionEngine().setCrossover(new Crossover(i_CrossoverName,i_NumOfCuttingPoints,i_CrossoverComponent));
        m_Descriptor.getEvolutionEngine().setSelection(new Selection(i_SelectionType,i_Percent,i_PTE,i_Elitism,Integer.parseInt(i_InitialPopulation)));
    }

    private void checkAndUpdateStoppingArguments(String i_InitialPopulation,String i_reqGenerations, String i_reqFitness, String i_reqTimeInMinutes) {
        if(i_InitialPopulation.isEmpty())
            throw new RuntimeException("Error, Please enter initial population size");
        int initialPopulation;
        try {
             initialPopulation = Integer.parseInt(i_InitialPopulation);
        } catch (Exception e) {
            throw new RuntimeException("Error, Initial Population must be a number.");
        }
        if (initialPopulation <= 0) {
            throw new RuntimeException("Error, Initial Population must be positive");
            }
        else {
            m_Descriptor.getEvolutionEngine().setInitialPopulationAmount(initialPopulation);
        }

        if (!i_reqGenerations.isEmpty()) {
            int reqGenerations;
            try {
                 reqGenerations = Integer.parseInt(i_reqGenerations);
            } catch (Exception e) {
                throw new RuntimeException("Error, Generations to make must be a number.");
            }

            if (reqGenerations < 100) {
                throw new RuntimeException("Error, Req generation stop condition cant be lower than 100.");
            } else {
                m_Descriptor.getEvolutionEngine().setNumOfGenerations(reqGenerations);
            }
        }
        else
        {
            m_Descriptor.getEvolutionEngine().setNumOfGenerations(0);
        }



        if(!i_reqFitness.isEmpty()) {
            int reqFitness;
            try {
                 reqFitness = Integer.parseInt(i_reqFitness);
            } catch (Exception e) {
                throw new RuntimeException("Error, Req fitness must be a number.");
            }
            if (reqFitness < 0 || reqFitness > 100)
                throw new RuntimeException("Error: Req fitness must be between 1-100");
            else
                m_Descriptor.getEvolutionEngine().setReqFitness(reqFitness);
            }
        else
        {
            m_Descriptor.getEvolutionEngine().setReqFitness(0);
        }


        if(!i_reqTimeInMinutes.isEmpty()) {
            int reqTime;
            try {
                reqTime = Integer.parseInt(i_reqTimeInMinutes);
            } catch (Exception e) {
                throw new RuntimeException("Error, Req time must be an Integer.");
            }
            if (reqTime <= 0) {
                throw new RuntimeException("Error, Req time must be a positive number!");
            }
            else {
                m_Descriptor.getEvolutionEngine().setReqMinutes(reqTime);
            }
        }
        else
        {
            m_Descriptor.getEvolutionEngine().setReqMinutes(0);
        }


    }

    public void LoadFile(File i_File) throws  JAXBException {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance("ParsedClasses");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ETTDescriptor ettDescriptor = (ETTDescriptor) jaxbUnmarshaller.unmarshal(i_File);
            CheckValidData checker=new CheckValidData(ettDescriptor);
            checker.checkFile();
            m_Descriptor=new Descriptor(ettDescriptor);
            m_MaxAmountOfObjects =getAmountOfData();
            m_IsFileLoaded=true;
            m_IsAlgoActivated=false;
        }
        catch (JAXBException e) {
            throw new JAXBException("An error with unmarshalling the file");
        }
    }

    public void LoadFile(InputStream i_InputStream) throws  JAXBException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("ParsedClasses");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ETTDescriptor ettDescriptor = (ETTDescriptor) jaxbUnmarshaller.unmarshal(i_InputStream);
            CheckValidData checker=new CheckValidData(ettDescriptor);
            checker.checkFile();
            m_Descriptor=new Descriptor(ettDescriptor);
            m_MaxAmountOfObjects =getAmountOfData();
            m_IsFileLoaded=true;
            m_IsAlgoActivated=false;
        }
        catch (JAXBException e) {
            throw new JAXBException("Please choose XML file");
        }
    }

    public BestSolutionsData getBestSolutionData() {
        if(m_IsFileLoaded) {
            if(m_IsAlgoActivated) {
                return m_EvolutionEngineData.getBestSolutionData();
            }
            else {
                throw new RuntimeException("ERROR: Please activate the algorithm first");
            }
        }
        else {
            throw new RuntimeException("ERROR: Please load a file, then activate the algorithm before choosing this option");
        }
    }

    public Descriptor getDescriptor() {
        return m_Descriptor;
    }

    public Integer getProblemIndex() {
        return m_ProblemIndex;
    }

    public DataPrinter getFileData() {
        if(m_IsFileLoaded) {
            DataPrinter dataPrinter = new DataPrinter();
            //building Subject map
            dataPrinter.SetSubjectsSet(m_Descriptor.getTimeTable().getSubjects().getSubjectSet());
            //building Teachers Map

            dataPrinter.setTeacherID2SubjectsMap(m_Descriptor.getTimeTable().getTeachers().getTeacherID2SubjectsMap(dataPrinter.getSubjectsSet()));

            //building Class map
            dataPrinter.setClassesID2SubjMap(m_Descriptor.getTimeTable().getClazzes().getClassID2SubjectsMap(dataPrinter.getSubjectsSet()));
            //building Rules map
            dataPrinter.setRulesNames2TypeMap(m_Descriptor.getTimeTable().getRules().getRulesNames2TypeMap());

            dataPrinter.setInitialPopulation(m_Descriptor.getEvolutionEngine().getInitialPopulation());
            dataPrinter.setSelectionData(m_Descriptor.getEvolutionEngine().getSelection().getSelectionData());
            dataPrinter.setCrossoverData(m_Descriptor.getEvolutionEngine().getCrossover().getCrossoverData());
            dataPrinter.setMutationsDataList(m_Descriptor.getEvolutionEngine().getMutations().getMutationsDataList());

            return dataPrinter;
        }
        else
        {
            throw new RuntimeException("ERROR: No file has been loaded, Please load a file before choosing this option");
        }
    }

    public WebFileData getWebFileData() {
        WebFileData webFileData=new WebFileData();
        webFileData.setSubjectsData(m_Descriptor.getTimeTable().getSubjects().getSubjectSet());
        webFileData.setTeacherData(m_Descriptor.getTimeTable().getTeachers().getTeachersData(m_Descriptor.getTimeTable().getSubjects()));
        webFileData.setClassData(m_Descriptor.getTimeTable().getClazzes().getClassesData(m_Descriptor.getTimeTable().getSubjects()));
        webFileData.setRuleData(m_Descriptor.getTimeTable().getRules().getRulesData());
        return webFileData;
    }

    public List<MutationData> getMutationDataList()
    {
        return m_Descriptor.getEvolutionEngine().getMutations().getMutationsDataList();
    }

    public AmountOfObjectsCalc copyAmountOfData(){
        return m_MaxAmountOfObjects;
    }

    public AmountOfObjectsCalc getAmountOfData() {
        TimeTable table= m_Descriptor.getTimeTable();
        Integer lessonInSolution=0,hardRolesCount=0,softRolesCount=0;
        List<Clazz> classesList = table.getClazzes().getClassesList();
        for(Clazz c:classesList)
        {
            int sum=c.getRequirements().getStudyList().stream().mapToInt(study-> study.getHours()).sum();
            lessonInSolution+=sum;
        }
        List<Rule> rulesList=table.getRules().getRulesList();
        for(Rule rule:rulesList)
        {
            if(rule.getType().equals(Rule.eType.HARD)) {
                hardRolesCount++;
            }
            else {
                softRolesCount++;
            }
        }

        AmountOfObjectsCalc maxValues=new AmountOfObjectsCalc(table.getDays(), table.getHours(),
                table.getTeachers().getTeachersList().size(),
                table.getClazzes().getClassesList().size(),
                table.getSubjects().getSubjectsList().size(),
                hardRolesCount,softRolesCount,
                lessonInSolution);
        return maxValues;
    }

    public boolean getIsFileLoaded() {
        return m_IsFileLoaded;
    }

    public boolean getIsAlgoActivated() {
        return m_IsAlgoActivated;
    }

    public Boolean getStopBoolean()
    {
        return m_Descriptor.getEvolutionEngine().getStopBoolean();
    }

    public String getTeacherNameById(Integer i_ID)
    {
        return m_Descriptor.getTimeTable().getTeachers().getTeacherNameById(i_ID);
    }

    public String getClassNameById(Integer i_ID)
    {
        return m_Descriptor.getTimeTable().getClazzes().getClassNameById(i_ID);
    }

    public String getSubjectNameById(Integer i_ID)
    {
        return m_Descriptor.getTimeTable().getSubjects().getSubjectNameById(i_ID);
    }

    public Integer getMaxLessons()
    {
        if(m_MaxAmountOfObjects==null)
            return 0;
        else
            return m_MaxAmountOfObjects.getMaxAmountOfLessons();
    }

    public void setIsFileLoaded(Boolean i_newVal)
    {
        m_IsFileLoaded=i_newVal;
    }

    public void setProblemIndex(Integer i_ProblemIndex) {
        this.m_ProblemIndex = i_ProblemIndex;
    }

    public void updateAlgoData(DataPrinter i_DataPrinter)
    {
        setNewCrossover(i_DataPrinter.getCrossoverData());
        setNewSelection(i_DataPrinter.getSelectionData());
        setNewMutations(i_DataPrinter.getMutationsDataList());
    }

    public void setStoppingCondition(List<eStoppingCondition> i_StoppingConditionList)
    {
        m_Descriptor.getEvolutionEngine().setStoppingConditionList(i_StoppingConditionList);
    }


    private void setNewCrossover(CrossoverData i_CrossoverData)
    {
        Crossover crossover=new Crossover(i_CrossoverData);
        m_Descriptor.getEvolutionEngine().setCrossover(crossover);
    }

    private void setNewSelection(SelectionData i_SelectionData)
    {
        Selection selection=new Selection(i_SelectionData);
        m_Descriptor.getEvolutionEngine().setSelection(selection);
    }

    private void setNewMutations(List<MutationData> i_MutationsDataList)
    {
        Mutations mutations=new Mutations(i_MutationsDataList);
        m_Descriptor.getEvolutionEngine().setMutations(mutations);
    }


    public void setStopBoolean(Boolean i_Boolean)
    {
        m_Descriptor.getEvolutionEngine().setStopBoolean(i_Boolean);
    }

    public WebLessonData getWebLessonData(LessonData i_LessonData)
    {
        String className=getClassNameById(i_LessonData.getClassID());
        String subjectName=getSubjectNameById(i_LessonData.getSubjectID());
        String teacherName=getTeacherNameById(i_LessonData.getTeacherID());
        return new WebLessonData(i_LessonData.getDay(),i_LessonData.getHour(),className,teacherName,subjectName);
    }

}
