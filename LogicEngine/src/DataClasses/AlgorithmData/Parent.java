package DataClasses.AlgorithmData;

import DataClasses.FileInputDataClasses.Teacher;

import java.util.*;

public class Parent implements Comparable<Parent>{
    private List<Lesson> m_LessonsList;
    private Random m_Roller;
    private Integer m_Fitness;


    public Parent()
    {
        m_LessonsList =new ArrayList<>();
        m_Roller=new Random();
        m_Fitness=-1;
    }

    public Parent(Integer i_Length)
    {
        m_LessonsList =new ArrayList<>(i_Length);
        m_Roller=new Random();
        m_Fitness=-1;
    }

    public Parent(Parent i_Parent)
    {
        m_LessonsList =new ArrayList<>(i_Parent.getParentSize());
        for(Lesson lesson :i_Parent.getLessonsList())
            m_LessonsList.add(new Lesson(lesson));
        m_Roller=new Random();
        m_Fitness=-1;
    }

    public List<Lesson> getLessonsList() {
        return m_LessonsList;
    }

    public Integer getParentSize(){return m_LessonsList.size();}

    public Lesson getLessonByIndex(Integer index) {
        return m_LessonsList.get(index);
    }

    public Integer getFitness() { return m_Fitness; }

    public boolean isContain(Lesson i_Lesson)
    {
        return m_LessonsList.contains(i_Lesson);
    }

    public void buildSolution (AmountOfObjectsCalc i_AmountOfObjects)
    {
        Integer minAmountOfLessons,maxAmountOfLessons;
        minAmountOfLessons=i_AmountOfObjects.getLessonsInSolution();
        maxAmountOfLessons=i_AmountOfObjects.getMaxAmountOfLessons()/i_AmountOfObjects.getAmountOfSubjects(); //c d h t
        int lessonsInSolution= m_Roller.nextInt(maxAmountOfLessons-minAmountOfLessons)+minAmountOfLessons;

        for(int i=1;i<=lessonsInSolution;i++)
        {
            Integer day= m_Roller.nextInt(i_AmountOfObjects.getAmountOfDays())+1;
            Integer hour= m_Roller.nextInt(i_AmountOfObjects.getAmountOfHours())+1;
            Integer classID= m_Roller.nextInt(i_AmountOfObjects.getAmountOfClasses())+1;
            Integer teacherID= m_Roller.nextInt(i_AmountOfObjects.getAmountOfTeachers())+1;
            Integer subjectID= m_Roller.nextInt(i_AmountOfObjects.getAmountOfSubjects())+1;

            Lesson lesson=new Lesson(day,hour,classID,teacherID,subjectID);
            if(!m_LessonsList.contains(lesson))
                m_LessonsList.add(lesson);
        }
    }


    public void setFitness(Integer i_Fitness) {
        this.m_Fitness = i_Fitness;
    }
    public void addLessonToParent(Lesson i_Lesson) {
        m_LessonsList.add(i_Lesson);
    }

    public void addLessonToParent(Integer i_Day,Integer i_Hour,Integer i_ClassID,Integer i_TeacherID,Integer i_SubjectID){
        Lesson lesson=new Lesson(i_Day,i_Hour,i_ClassID,i_TeacherID,i_SubjectID);
        m_LessonsList.add(lesson);
    }

    public void removeLessonFromParent(Integer i_Index)
    {
        m_LessonsList.remove(i_Index);
    }

    @Override
    public int compareTo(Parent i_Parent) {
        return (i_Parent.getFitness()-this.m_Fitness);
    }


    @Override
    public String toString() {
        return "Parent{"
                + m_LessonsList +
                '}';
    }
}
