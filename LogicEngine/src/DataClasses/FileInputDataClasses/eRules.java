package DataClasses.FileInputDataClasses;

import DataClasses.AlgorithmData.Lesson;
import DataClasses.AlgorithmData.Parent;

import java.util.*;
import java.util.stream.Collectors;

public enum eRules {
    TEACHERISHUMAN
            {
                @Override
                public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable,Integer i_TotalHours) {
                    Integer fitness;
                    Integer numOfTeachers = i_TimeTable.getTeachers().getTeacherListSize();
                    Set<Integer> badTeachersIDSet = new HashSet<>();
                    List<Lesson> lessonsList = i_Parent.getLessonsList();
                    for (int i = 1; i <= numOfTeachers; i++) {
                        int teacherID = i;
                        List<Lesson> lessonsOfTeacherList = lessonsList.stream().filter(l1 -> l1.getTeacherID().equals(teacherID)).collect(Collectors.toList());
                        for (Lesson lesson : lessonsOfTeacherList) {
                            long count = lessonsOfTeacherList.stream().filter(l1 -> l1.getDay().equals(lesson.getDay())
                                    && l1.getHour().equals(lesson.getHour())).count();
                            if (count > 1) {
                                badTeachersIDSet.add(teacherID);
                                break;
                            }
                        }
                    }
                    fitness = 100-(badTeachersIDSet.size() * 100 / numOfTeachers);
                    return fitness;
                }

                @Override
                public String getRuleName() {
                    return "Teacher is human";
                }
            },
    SINGULARITY
            {
                @Override
                public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable,Integer i_TotalHours)
                {
                    Integer fitness;
                    Integer numOfClasses = i_TimeTable.getClazzes().getClassesListSize();
                    Set<Integer> badClassesIDSet = new HashSet<>();
                    List<Lesson> lessonsList = i_Parent.getLessonsList();
                    for (int i = 1; i <= numOfClasses; i++) {
                        int classID = i;
                        List<Lesson> lessonsOfClassList = lessonsList.stream().filter(l1 -> l1.getClassID().equals(classID)).collect(Collectors.toList());
                        for (Lesson lesson : lessonsOfClassList) {
                            long count = lessonsOfClassList.stream().filter(l1 -> l1.getDay().equals(lesson.getDay())
                                    && l1.getHour().equals(lesson.getHour())).count();
                            if (count > 1) {
                                badClassesIDSet.add(classID);
                                break;
                            }
                        }
                    }
                    fitness = 100-(badClassesIDSet.size() * 100 / numOfClasses);
                    return fitness;
                }

                @Override
                public String getRuleName() {
                    return "Singularity";
                }
            },
    KNOWLEDGEABLE
            {
                @Override
                public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable,Integer i_TotalHours)
                {
                    Integer fitness;
                    List<Integer> teacherGradesInRule=new ArrayList<>();
                    Teachers teachers = i_TimeTable.getTeachers();
                    int amountOfSubjects=i_TimeTable.getSubjects().getSubjectsListSize();
                    for(Teacher t:teachers.getTeachersList())
                    {
                        List<Lesson> LessonsTeacherTeachInSolution=i_Parent.getLessonsList().stream()
                                .filter(lesson->lesson.getTeacherID().equals(t.getId()))
                                .collect(Collectors.toList());
                        List<Integer> badSubjectsID=new ArrayList<>();
                        List<Integer> canTeachSubjects=t.getSubjectsIDList();
                        int allCantTeachAmount=amountOfSubjects-t.getAmountOfSubjectsTeacherTeach();
                        for(Lesson lesson:LessonsTeacherTeachInSolution)
                        {
                            if(!canTeachSubjects.contains(lesson.getSubjectID()))
                            {
                                if(!badSubjectsID.contains(lesson.getSubjectID()))
                                {
                                    badSubjectsID.add(lesson.getSubjectID());
                                    if(badSubjectsID.size()==allCantTeachAmount)
                                        break;
                                }
                            }
                        }
                        int alreadyTeachAndCantTeachAmount= badSubjectsID.size();
                        int teacherGrade=((allCantTeachAmount-alreadyTeachAndCantTeachAmount)/allCantTeachAmount)*100;
                        teacherGradesInRule.add(teacherGrade);

                    }
                    fitness=(teacherGradesInRule.stream().mapToInt(grade->grade).sum())/(teacherGradesInRule.size());
                    return fitness;
                }

                @Override
                public String getRuleName() {
                    return "Knowledgeable";
                }
            },
    SATISFACTORY
            {
                @Override
                public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable,Integer i_TotalHours)
                {
                    Integer numOfClasses = i_TimeTable.getClazzes().getClassesListSize();
                    List<Integer> classesGrades=new ArrayList<>();
                    List<Lesson> lessonsList = i_Parent.getLessonsList();
                    for (int i = 1; i <= numOfClasses; i++) {
                        int classID = i;
                        int badSubjects=0;
                        int numOfClassSubjects=i_TimeTable.getClazzes().getClassById(classID).getRequirements().getStudyList().size();
                        List<Lesson> lessonsOfClassList = lessonsList.stream().filter(l1 -> l1.getClassID().equals(classID)).collect(Collectors.toList());
                        Map<Integer, Integer> subjectId2ReqHoursMap = i_TimeTable.getClazzes().getClassById(classID).getRequirements().getSubjectId2ReqHoursMap();
                        Map<Integer,Integer> sumMap=new HashMap<>();
                        subjectId2ReqHoursMap.keySet().forEach(key->sumMap.put(key,0));
                        List<Lesson> alreadyCounted=new ArrayList<>();
                        for(Lesson lesson:lessonsOfClassList)
                        {
                            long count = alreadyCounted.stream().filter(subLesson -> subLesson.getDay().equals(lesson.getDay()) &&
                                    subLesson.getHour().equals(lesson.getHour()) &&
                                    subLesson.getSubjectID().equals(lesson.getSubjectID())).count();
                            if(count==0) {
                                if (sumMap.containsKey(lesson.getSubjectID())) {
                                    Integer currentValue = sumMap.get(lesson.getSubjectID()) + 1;
                                    sumMap.put(lesson.getSubjectID(), currentValue);
                                    alreadyCounted.add(lesson);
                                }
                            }
                        }
                        for(Map.Entry<Integer,Integer> entry:sumMap.entrySet())
                        {
                            if(entry.getValue()!=subjectId2ReqHoursMap.get(entry.getKey()))
                            {
                                badSubjects++;
                            }
                        }
                        classesGrades.add(100-(badSubjects*100/numOfClassSubjects));
                    }
                    return classesGrades.stream().mapToInt(i->i).sum()/classesGrades.size();
                }

                @Override
                public String getRuleName() {
                    return "Satisfactory";
                }
            },
    DAYOFFTEACHER
            {
                @Override
                public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable,Integer i_TotalHours) {
                    Integer retFitnessForRule=0;
                    Integer numOfTeachers=i_TimeTable.getTeachers().getTeacherListSize();
                    Integer numOfDaysInWeek=i_TimeTable.getDays();
                    List<Integer> teachersWhoHaveBreakList=new ArrayList<>();
                    List<Teacher> teachersList=i_TimeTable.getTeachers().getTeachersList();
                    for(Teacher t:teachersList)
                    {
                        boolean foundDayOff=false;
                        for(int i=1;i<=numOfDaysInWeek && !foundDayOff ;i++)
                        {
                            Integer day=i;
                            if(i_Parent.getLessonsList().stream()
                                    .filter(lesson->lesson.getTeacherID().equals(t.getId()))
                                    .filter(lesson -> lesson.getDay().equals(day)).count()==0)
                            {
                                foundDayOff=true;
                                if(!teachersWhoHaveBreakList.contains(t.getId())) //not needed if,but ill keep
                                    teachersWhoHaveBreakList.add(t.getId());

                            }
                        }
                    }
                    retFitnessForRule=(teachersWhoHaveBreakList.size()/numOfTeachers)*100;
                    return retFitnessForRule;
                }

                @Override
                public String getRuleName() {
                    return "Day-off teacher";
                }
            },
    SEQUENTIALITY
            {
                @Override
                public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable,Integer i_TotalHours) {
                    List<Clazz> classesList=i_TimeTable.getClazzes().getClassesList();
                    int daysInWeek=i_TimeTable.getDays();
                    int hoursInDay=i_TimeTable.getHours();
                    List<Integer> classesGrades=new ArrayList<>();
                    for(Clazz c:classesList)
                    {
                        int badDays=0;
                        for(int i=1;i<=daysInWeek;i++)
                        {
                            Boolean isFoundBadDay=false;
                            int counter=0;
                            int day=i;
                            List<Lesson> lessonsForClassInDay = i_Parent.getLessonsList().stream()
                                    .filter(lesson -> lesson.getClassID().equals(c.getId()))
                                    .filter(lesson -> lesson.getDay().equals(day))
                                    .collect(Collectors.toList());
                            int subjID=0;
                            for(int j=1;j<=hoursInDay && !isFoundBadDay;j++)
                            {
                                int hour=j;
                                if(lessonsForClassInDay.stream()
                                        .filter(lesson -> lesson.getHour().equals(hour)).count()>0)
                                {
                                    int nextSubjID=lessonsForClassInDay.stream()
                                            .filter(lesson -> lesson.getHour().equals(hour))
                                            .findFirst().get().getSubjectID();

                                    if(nextSubjID==subjID)
                                    {
                                        counter++;
                                        if(counter>i_TotalHours)
                                        {
                                            badDays++;
                                            isFoundBadDay=true;
                                        }
                                    }
                                    else {
                                        counter = 0;
                                        subjID=nextSubjID;
                                    }
                                }
                                else {
                                    counter = 0;
                                    subjID=0;
                                }
                            }

                        }
                        int classGrade=((daysInWeek-badDays)/daysInWeek)*100;
                        classesGrades.add(classGrade);
                    }

                    return (classesGrades.stream().mapToInt(i->i).sum()/classesGrades.size());
                }

                @Override
                public String getRuleName() {
                    return "Sequentiality";
                }
            },
    DAYOFFCLASS
    {
        @Override
        public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable, Integer i_TotalHours) {
            Integer retFitnessForRule=0;
            Integer numOfClasses=i_TimeTable.getClazzes().getClassesListSize();
            Integer numOfDaysInWeek=i_TimeTable.getDays();
            List<Integer> classesThatHaveBreakList=new ArrayList<>();
            List<Clazz> classesList=i_TimeTable.getClazzes().getClassesList();
            for(Clazz c:classesList)
            {
                boolean foundDayOff=false;
                for(int i=1;i<=numOfDaysInWeek && !foundDayOff ;i++)
                {
                    Integer day=i;
                    if(i_Parent.getLessonsList().stream()
                            .filter(lesson->lesson.getClassID().equals(c.getId()))
                            .filter(lesson -> lesson.getDay().equals(day)).count()==0)
                    {
                        foundDayOff=true;
                        if(!classesThatHaveBreakList.contains(c.getId())) //not needed if,but ill keep
                            classesThatHaveBreakList.add(c.getId());

                    }
                }
            }
            retFitnessForRule=(classesThatHaveBreakList.size()/numOfClasses)*100;
            return retFitnessForRule;
        }

        @Override
        public String getRuleName() {
            return "Day-off class";
        }
    },
    WORKINGHOURSPREFERENCE
    {
        @Override
        public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable, Integer i_TotalHours) {
            Integer retFitnessForRule=0;
            Integer daysInWeek=i_TimeTable.getDays();
            Integer hoursInDay=i_TimeTable.getHours();
            List<Integer> teachersIDWithGoodTeachingHours=new ArrayList<>();
            List<Teacher> teachersList=i_TimeTable.getTeachers().getTeachersList();
            for(Teacher t:teachersList)
            {
                Integer prefHours=t.getTeacherWorkingHours();
                List<Lesson> teacherLessons=i_Parent.getLessonsList().stream().filter(lesson -> lesson.getTeacherID().equals(t.getId())).collect(Collectors.toList());
                int lessonsCount=0;
                for(int i=1;i<=daysInWeek;i++) {
                    for (int j = 1; j <= hoursInDay; j++) {
                        int day = i;
                        int hour = j;
                        if (teacherLessons.stream().filter(lesson -> lesson.getDay().equals(day)).filter(lesson -> lesson.getHour().equals(hour)).count() > 0)
                            lessonsCount++;
                    }
                }
                if(lessonsCount==prefHours)
                    teachersIDWithGoodTeachingHours.add(t.getId());

            }
            retFitnessForRule=(teachersIDWithGoodTeachingHours.size()/i_TimeTable.getTeachers().getTeacherListSize())*100;
            return retFitnessForRule;
        }

        @Override
        public String getRuleName() {
            return "Working hours preference";
        }
    };

    public abstract Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable,Integer i_TotalHours);
    public abstract String getRuleName();
}
