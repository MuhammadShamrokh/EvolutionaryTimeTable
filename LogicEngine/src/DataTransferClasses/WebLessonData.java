package DataTransferClasses;

public class WebLessonData {
        private Integer m_Day;
        private Integer m_Hour;
        private String m_ClassName;
        private String m_TeacherName;
        private String m_SubjectName;

        public WebLessonData(Integer i_Day,Integer i_Hour,String i_ClassName,String i_TeacherName,String i_SubjectName)
        {
            m_Day= i_Day;
            m_Hour=i_Hour;
            m_ClassName= i_ClassName;
            m_TeacherName= i_TeacherName;
            m_SubjectName= i_SubjectName;
        }

        public Integer getDay() {
            return m_Day;
        }

        public Integer getHour() {
            return m_Hour;
        }

        public String getClassName() {
            return m_ClassName;
        }

        public String getTeacherName() {
            return m_TeacherName;
        }

        public String getSubjectName() {
            return m_SubjectName;
        }

}
