package DataClasses.FileInputDataClasses;

import ParsedClasses.ETTRequirements;
import ParsedClasses.ETTStudy;

import java.util.*;

public class Requirements {
    private List<Study> m_StudyList;

    public Requirements(ETTRequirements i_ETTRequirements)
    {
        m_StudyList=new ArrayList<>();
        List<ETTStudy> ettStudies = i_ETTRequirements.getETTStudy();
        for(ETTStudy ettStudy:ettStudies)
        {
            m_StudyList.add(new Study(ettStudy));
        }
    }

    public Requirements(Requirements i_Requirements)
    {
        m_StudyList=new ArrayList<>();
        List<Study> studies = i_Requirements.getStudyList();
        for(Study study:studies)
        {
            m_StudyList.add(new Study(study));
        }
    }

    public Integer getReqHoursBySubjId(Integer i_Id)
    {
        Optional<Study> study = m_StudyList.stream().filter(lesson -> lesson.getHours().equals(i_Id)).findFirst();
        return study.get().getHours();
    }


    @Override
    public String toString() {
        return "Requirements{" +
                "m_StudyList=" + m_StudyList +
                '}';
    }

    public List<Study> getStudyList() {
        return m_StudyList;
    }

    public Map<Integer,Integer> getSubjectId2ReqHoursMap()
    {
        Map<Integer,Integer> retMap=new HashMap<>();
        for(Study study:m_StudyList)
        {
            retMap.put(study.getSubjectID(), study.getHours());
        }
        return retMap;
    }


}
