package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Lesson;
import DataClasses.AlgorithmData.Parent;

import java.util.Random;

public enum eMutation {
    FLIPPING {
        @Override
        public void activate(Integer i_Tupples, Generation i_Generation,
                             AmountOfObjectsCalc i_AmountOfObj,
                             Character i_Char, Random i_Roller) {

            int parentIndex = i_Roller.nextInt(i_Generation.getGenerationSize());
            Parent parentAfterChange = i_Generation.getParentByIndex(parentIndex);
            int numOfChanges = i_Roller.nextInt(i_Tupples) + 1;
            for (int i = 0; i < numOfChanges; i++) {
                int lessonToChange = i_Roller.nextInt(parentAfterChange.getParentSize());
                makeAChangeInParent(parentAfterChange, i_AmountOfObj, i_Char, lessonToChange, i_Roller);
            }
            parentAfterChange.setFitness(-1);
        }

        @Override
        public String toString(Integer i_Tupples,Character i_Char) {
            return "Name=Flipping,Tupples="+i_Tupples+" ("+i_Char+")";
        }

    },
    SIZER {
        @Override
        public void activate(Integer i_Tupples, Generation i_Generation, AmountOfObjectsCalc i_AmountOfObj, Character i_Char, Random i_Roller) {
            int parentIndex = i_Roller.nextInt(i_Generation.getGenerationSize());
            Parent parentAfterChange = i_Generation.getParentByIndex(parentIndex);
            if(i_Tupples>0&&i_Tupples<=(i_AmountOfObj.getAmountOfDays()*i_AmountOfObj.getAmountOfHours()))
            {
                int addingMaxAmount=i_Tupples;
                int amountToAdd= i_Roller.nextInt(addingMaxAmount)+1;
                for(int i = 1; i<=amountToAdd && parentAfterChange.getParentSize()<i_AmountOfObj.getMaxAmountOfLessons(); i++) {
                    Integer day = i_Roller.nextInt(i_AmountOfObj.getAmountOfDays()) + 1;
                    Integer hour = i_Roller.nextInt(i_AmountOfObj.getAmountOfHours()) + 1;
                    Integer classID = i_Roller.nextInt(i_AmountOfObj.getAmountOfClasses()) + 1;
                    Integer teacherID = i_Roller.nextInt(i_AmountOfObj.getAmountOfTeachers()) + 1;
                    Integer subjectID = i_Roller.nextInt(i_AmountOfObj.getAmountOfSubjects()) + 1;

                    Lesson lesson = new Lesson(day, hour, classID, teacherID, subjectID);
                    if(!parentAfterChange.isContain(lesson)) {
                        parentAfterChange.addLessonToParent(lesson);
                    }
                }
                parentAfterChange.setFitness(-1);
            }

            else if(i_Tupples<0)
            {
                int removingMaxAmount=Math.abs(i_Tupples);
                int amountToRemove= i_Roller.nextInt(removingMaxAmount)+1;
                for(int i = 1; i<=amountToRemove && parentAfterChange.getParentSize()>i_AmountOfObj.getAmountOfDays(); i++)
                {
                    int index=i_Roller.nextInt(parentAfterChange.getParentSize());
                    parentAfterChange.removeLessonFromParent(index);
                }
                parentAfterChange.setFitness(-1);
            }
        }

        @Override
        public String toString(Integer i_Tupples,Character i_Char) {
            return "Name=Sizer,Tupples="+i_Tupples;
        }
    };
    public abstract void activate(Integer i_Tupples, Generation i_Generation,
                                        AmountOfObjectsCalc i_AmountOfObj,
                                        Character i_Char,Random i_Roller);

    public abstract String toString(Integer i_Tupples,Character i_Char);

    public void makeAChangeInParent(Parent i_Parent,AmountOfObjectsCalc i_AmountOfObj,Character i_Char
                                    ,Integer i_LessonIndex,Random i_Roller)
    {
        int newVal;
        switch (i_Char)
        {
            case 'C':
                newVal=i_Roller.nextInt(i_AmountOfObj.getAmountOfClasses())+1;
                i_Parent.getLessonByIndex(i_LessonIndex).setClassID(newVal);
                break;
            case 'H':
                newVal=i_Roller.nextInt(i_AmountOfObj.getAmountOfHours())+1;
                i_Parent.getLessonByIndex(i_LessonIndex).setHour(newVal);
                break;
            case 'D':
                newVal=i_Roller.nextInt(i_AmountOfObj.getAmountOfDays())+1;
                i_Parent.getLessonByIndex(i_LessonIndex).setDay(newVal);
                break;
            case 'T':
                newVal=i_Roller.nextInt(i_AmountOfObj.getAmountOfTeachers())+1;
                i_Parent.getLessonByIndex(i_LessonIndex).setTeacherID(newVal);
                break;
            case 'S':
                newVal=i_Roller.nextInt(i_AmountOfObj.getAmountOfSubjects())+1;
                i_Parent.getLessonByIndex(i_LessonIndex).setSubjectID(newVal);
                break;

        }

    }
}
