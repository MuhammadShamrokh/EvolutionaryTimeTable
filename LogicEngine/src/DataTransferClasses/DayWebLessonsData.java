package DataTransferClasses;

import java.util.ArrayList;
import java.util.List;

public class DayWebLessonsData {
    List<WebLessonData> m_LessonsInDay;

    public DayWebLessonsData()
    {
        m_LessonsInDay=new ArrayList<>();
    }

    public void addLessonToDay(WebLessonData i_WebLesson)
    {
        m_LessonsInDay.add(i_WebLesson);
    }


}
