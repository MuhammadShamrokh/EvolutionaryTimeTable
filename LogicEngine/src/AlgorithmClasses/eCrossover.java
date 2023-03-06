package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Lesson;
import DataClasses.AlgorithmData.Parent;

import java.util.Collection;

public enum eCrossover {
    DAYTIMEORIENTED
            {
                @Override
                public void activate(Parent p1, Parent p2, AmountOfObjectsCalc amounts,Character i_Char
                        , Collection<Integer> cuttingPoints, Generation i_NextGeneration) {

                    int index = 0;
                    Parent childOne = new Parent(amounts.getMaxAmountOfLessons());
                    Parent childTwo = new Parent(amounts.getMaxAmountOfLessons());

                    boolean childOneCopyFromFirstParent = true;
                    for (int i = 1; i <= amounts.getAmountOfDays(); i++)
                        for (int j = 1; j <= amounts.getAmountOfHours(); j++)
                            for (int k = 1; k <= amounts.getAmountOfClasses(); k++)
                                for (int m = 1; m <= amounts.getAmountOfTeachers(); m++)
                                    for (int n = 1; n <= amounts.getAmountOfSubjects(); n++) {
                                        Lesson checkLesson = new Lesson(i, j, k, m, n);
                                        if (childOneCopyFromFirstParent) {
                                            if (p1.isContain(checkLesson))
                                                childOne.addLessonToParent(checkLesson);
                                            if (p2.isContain(checkLesson))
                                                childTwo.addLessonToParent(checkLesson);
                                        } else {
                                            if (p2.isContain(checkLesson))
                                                childOne.addLessonToParent(checkLesson);
                                            if (p1.isContain(checkLesson))
                                                childTwo.addLessonToParent(checkLesson);
                                        }
                                        index++;
                                        if (cuttingPoints.contains(index))
                                            childOneCopyFromFirstParent = (!childOneCopyFromFirstParent);
                                    }
                    i_NextGeneration.addParentToGeneration(childOne);
                    i_NextGeneration.addParentToGeneration(childTwo);
                }

                @Override
                public String toString() {
                    return "DayTimeOriented";
                }


            },
    ASPECTORIENTED
            {
                @Override
                public void activate(Parent p1, Parent p2, AmountOfObjectsCalc amounts, Character i_Char, Collection<Integer> cuttingPoints, Generation i_NextGeneration) {
                    int outerLoopParameter=0,innerLoopParameter=0;
                    boolean isByClass=false;
                    switch (i_Char)
                    {
                        case 'C':
                            outerLoopParameter =amounts.getAmountOfClasses();
                            innerLoopParameter=amounts.getAmountOfTeachers();
                            isByClass=true;
                            break;
                        case 'T':
                            outerLoopParameter= amounts.getAmountOfTeachers();
                            innerLoopParameter=amounts.getAmountOfTeachers();
                            break;
                    }

                    int index = 0;
                    Parent childOne = new Parent(amounts.getMaxAmountOfLessons());
                    Parent childTwo = new Parent(amounts.getMaxAmountOfLessons());

                    boolean childOneCopyFromFirstParent = true;
                    for (int i = 1; i <= outerLoopParameter; i++)
                        for (int j = 1; j <= innerLoopParameter; j++)
                            for (int k = 1; k <= amounts.getAmountOfDays(); k++)
                                for (int m = 1; m <= amounts.getAmountOfHours(); m++)
                                    for (int n = 1; n <= amounts.getAmountOfSubjects(); n++) {
                                        Lesson checkLesson;
                                        if(isByClass)
                                        {
                                            checkLesson=new Lesson(k,m,i,j,n);
                                        }
                                        else
                                        {
                                            checkLesson=new Lesson(k,m,j,i,n);
                                        }
                                        if (childOneCopyFromFirstParent) {
                                            if (p1.isContain(checkLesson))
                                                childOne.addLessonToParent(checkLesson);
                                            if (p2.isContain(checkLesson))
                                                childTwo.addLessonToParent(checkLesson);
                                        } else {
                                            if (p2.isContain(checkLesson))
                                                childOne.addLessonToParent(checkLesson);
                                            if (p1.isContain(checkLesson))
                                                childTwo.addLessonToParent(checkLesson);
                                        }
                                        index++;
                                        if (cuttingPoints.contains(index))
                                            childOneCopyFromFirstParent = (!childOneCopyFromFirstParent);
                                    }
                    i_NextGeneration.addParentToGeneration(childOne);
                    i_NextGeneration.addParentToGeneration(childTwo);
                }

                @Override
                public String toString() {
                    return "AspectOriented";
                }


            };

    public abstract void activate(Parent p1, Parent p2, AmountOfObjectsCalc amounts,Character i_Char, Collection<Integer> cuttingPoints,Generation i_NextGeneration);
    public abstract String toString();
}
