

$(function() {
    $.ajax({
        data: "",
        url: "filedata",
        timeout: 2000,
        success: (function(webFileData){
            //Teachers
            $("#teachersTableBody").empty();
            $.each(webFileData["m_TeacherData"] || [],function (index,teacher) {
                var subjectsStr="";
                $.each(teacher["m_TeacherSubjects"] || [],function (index,subject) {
                    subjectsStr+=subject["m_SubjectName"]+"<br>";
                })
                $("<tr><td>" + teacher["m_TeacherID"] +
                    "</td><td>" + teacher["m_TeacherName"] +
                    "</td><td>" + teacher["m_WorkingHours"] +
                    "</td><td>" + subjectsStr +
                    "</td></tr>").appendTo($("#teachersTableBody"))
            });

            //Classes
            $("#classesTableBody").empty();
            $.each(webFileData["m_ClassData"] || [],function (index,clazz) {
                var subjectsStr="";
                $.each(clazz["m_ClassSubjects"] || [],function (index,subject) {
                    subjectsStr+=subject["m_SubjectName"]+"<br>";
                })
                $("<tr><td>" + clazz["m_ClassID"] +
                    "</td><td>" + clazz["m_ClassName"] +
                    "</td><td>" + subjectsStr +
                    "</td></tr>").appendTo($("#classesTableBody"))
            });

            //Subjects
            $("#subjectsTableBody").empty();
            $.each(webFileData["m_SubjectsData"] || [],function (index,subject) {
                $("<tr><td>" + subject["m_SubjectID"] +
                    "</td><td>" + subject["m_SubjectName"] +
                    "</td></tr>").appendTo($("#subjectsTableBody"))
            });

            //Rules
            $("#rulesTableBody").empty();
            $.each(webFileData["m_RuleData"] || [],function (index,rule) {
                $("<tr><td>" + rule["m_RuleName"] +
                    "</td><td>" + rule["m_RuleType"] +
                    "</td></tr>").appendTo($("#rulesTableBody"))
            });
        }),
        error: function (){
            console.log("Error printing file");
        }
    });
});


