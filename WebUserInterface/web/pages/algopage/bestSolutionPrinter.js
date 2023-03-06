function getRulesDataStr(rulesDataList)
{
    var rulesStr="";
    $.each(rulesDataList || [],function (index,rule) {
        rulesStr+= "<tr><td><table style='margin: auto;' class='table table-sm table-striped text-center align-middle table-bordered border'>" +
            "<tbody><tr><th>" + "Name" + "</th><td>" + rule["m_Name"] + "</td></tr>" +
            "<tr><th>" + "Type" + "</th><td>" + rule["m_Type"] + "</td></tr>" +
            "<tr><th>" + "Grade" + "</th><td>" + rule["m_Grade"] + "</td></tr>";
        rulesStr+= "</tbody></table></td></tr>";
    });

    return "<table class='table table-striped table-hover text-center table-bordered align-middle'>" +
        "<thead>" +
        "<tr class='table-warning'>" +
        "<th>Rules</th>" +
        "</tr>" +
        "</thead>" +
        "<tbody>"+rulesStr+"</tbody>" +
        "</table>";
}

function printRawSolution(rawSolutionData, appendToID){
    var rawStr="";
    $.each(rawSolutionData["m_RawSolution"] || [],function (index,lesson) {
        rawStr+="<table class='table table-sm table-striped w-auto text-center align-middle table-bordered border'>" +
            "<tbody><tr><th>" + "Day" + "</th><td>" + lesson["m_Day"] + "</td></tr>" +
            "<tr><th>" + "Hour" + "</th><td>" + lesson["m_Hour"] + "</td></tr>" +
            "<tr><th>" + "Class" + "</th><td>" + lesson["m_ClassName"] + "</td></tr>" +
            "<tr><th>" + "Teacher" + "</th><td>" + lesson["m_TeacherName"] + "</td></tr>" +
            "<tr><th>" + "Subject" + "</th><td>" + lesson["m_SubjectName"] + "</td></tr>" +
            "</tbody></table>";
    });
    $("<div class='row p-3'>" +
        "<div class='col-9 border-end'><div class='d-flex flex-row flex-wrap justify-content-between gap-1'>"+rawStr+"</div></div>" +
        "<div class='col-3'>"+getRulesDataStr(rawSolutionData["m_RuleDataList"])+"</div></div>").appendTo($("#"+appendToID).empty());
}

function printTeachersSolution (teachersList, appendToID, senderID, methodStr, userID){
    //m_TeacherID ---> teacher id member
    //m_TeacherName ---> teacher name member
    if(userID===undefined)
        userID=-1;
    var solutionContentID="solutionContent"+senderID;
    var solutionRulesID="solutionRules"+senderID;

    var teachersListStr="";
    $.each(teachersList || [],function (index,teacher) {
        teachersListStr+="<button type='button' class='list-group-item list-group-item-action list-group-item-light bestSolutionTeacherBTN'" + " id='"+teacher["m_TeacherID"]+"'>" +
            teacher["m_TeacherName"] +
            "</button>";
    });
    $("<div class='row p-3'>" +
        "<div class='col-xxl-1 border-end'><h6>Teacher Selection</h6><div class='list-group'>"+teachersListStr+"</div></div>" +
        "<div class='col-xxl-8 border-end' id='"+solutionContentID+"'></div>" +
        "<div class='col-xxl-3' id='"+solutionRulesID+"'></div>" +
        "</div>").appendTo($("#"+appendToID).empty());

    $(".bestSolutionTeacherBTN").click(function(){
        console.log("clicked on teacher "+this.getAttribute("id"));
        $.ajax({
            data:"teacherID="+this.getAttribute("id")+"&"+"otherUserID="+userID,
            url:"teacherSolution",
            method: methodStr,
            timeout:2000,
            success:function(teacherSolution){
                //day --->m_Day
                //hour --->m_Hour
                //class name --->m_ClassName
                //teacher name --->m_TeacherName
                //subject name --->m_SubjectName
                var teacherTimeTable=teacherSolution["m_Solution"];
                let numOfDays=teacherTimeTable.length;
                var daysStr="";
                for(let i=0; i<numOfDays;i++)
                {
                    daysStr+="<th>"+(i+1)+"</th>";
                }
                let numOfHours=teacherTimeTable[0]["m_LessonsInDay"].length;
                var hoursStr="";
                for(let i=0; i<numOfHours;i++)
                {
                    hoursStr+="<th>"+(i+1)+"</th>";
                }

                var allRows="";
                var rowStr="";
                for(let h=0; h<numOfHours;h++)
                {
                    rowStr+="<tr><th>"+(h+1)+"</th>";
                    for(let d=0; d<numOfDays;d++)
                    {
                        rowStr+="<td>";
                        if(teacherTimeTable[d]["m_LessonsInDay"][h]) {
                            rowStr += "<table style='margin: auto;' class='table table-sm table-striped w-auto text-center align-middle table-bordered border'>" +
                                "<tbody>" +
                                "<tr><th>" + "Class" + "</th><td>" + teacherTimeTable[d]["m_LessonsInDay"][h]["m_ClassName"] + "</td></tr>" +
                                "<tr><th>" + "Subject" + "</th><td>" + teacherTimeTable[d]["m_LessonsInDay"][h]["m_SubjectName"] + "</td></tr>" +
                                "</tbody></table>";
                        }
                        rowStr+="</td>";
                    }
                    rowStr+="</tr>"
                    allRows+=rowStr;
                    rowStr="";
                }

                $("<table style='margin: auto' class='table table-striped table-hover text-center table-bordered align-middle'>" +
                    "<thead>" +
                    "<tr class='table-primary align-middle'>" +
                    "<th>Hour / Day</th>" +
                    daysStr +
                    "</tr>" +
                    "</thead>" +
                    "<tbody>"+allRows+"</tbody>" +
                    "</table>").appendTo($("#"+solutionContentID).empty());

                //rules
                $(getRulesDataStr(teacherSolution["m_RuleDataList"])).appendTo($("#"+solutionRulesID).empty());
            },
            error:function(){
                console.log("failed to get teacher schedule");
            }
        })
    })
}

function printClassesSolution (classesList, appendToID, senderID, methodStr, userID){
    //m_ClassID -->class id member
    //m_ClassName --->class name member
    if(userID===undefined)
        userID=-1;
    var solutionContentID="solutionContent"+senderID;
    var solutionRulesID="solutionRules"+senderID;

    var classesListStr="";
    $.each(classesList || [],function (index,clazz) {
        classesListStr+="<button type='button' class='list-group-item list-group-item-action list-group-item-light bestSolutionClassBTN'" + " id='"+clazz["m_ClassID"]+"'>" +
            clazz["m_ClassName"] +
            "</button>";
    });

    $("<div class='row p-3'>" +
        "<div class='col-xxl-1 border-end'><h6>Class Selection</h6><div class='list-group'>"+classesListStr+"</div></div>" +
        "<div class='col-xxl-8 border-end' id='"+solutionContentID+"'></div>" +
        "<div class='col-xxl-3' id='"+solutionRulesID+"'></div>" +
        "</div>")
        .appendTo($("#"+appendToID).empty());

    $(".bestSolutionClassBTN").click(function(){
        console.log("clicked on class with id "+this.getAttribute("id"));
        $.ajax({
            data:"classID="+this.getAttribute("id")+"&"+"otherUserID="+userID,
            url:"classSolution",
            method: methodStr,
            timeout:2000,
            success:function(classSolution){
                //day --->m_Day
                //hour --->m_Hour
                //class name --->m_ClassName
                //teacher name --->m_TeacherName
                //subject name --->m_SubjectName
                var classTimeTable=classSolution["m_Solution"];
                let numOfDays=classTimeTable.length;
                var daysStr="";
                for(let i=0; i<numOfDays;i++)
                {
                    daysStr+="<th>"+(i+1)+"</th>";
                }
                let numOfHours=classTimeTable[0]["m_LessonsInDay"].length;
                var hoursStr="";
                for(let i=0; i<numOfHours;i++)
                {
                    hoursStr+="<th>"+(i+1)+"</th>";
                }

                var allRows="";
                var rowStr="";
                for(let h=0; h<numOfHours;h++)
                {
                    rowStr+="<tr><th>"+(h+1)+"</th>";
                    for(let d=0; d<numOfDays;d++)
                    {
                        rowStr+="<td>";
                        if(classTimeTable[d]["m_LessonsInDay"][h]) {
                            rowStr += "<table style='margin: auto;' class='table table-sm table-striped w-auto text-center align-middle table-bordered border'>" +
                                "<tbody>" +
                                "<tr><th>" + "Teacher" + "</th><td>" + classTimeTable[d]["m_LessonsInDay"][h]["m_TeacherName"] + "</td></tr>" +
                                "<tr><th>" + "Subject" + "</th><td>" + classTimeTable[d]["m_LessonsInDay"][h]["m_SubjectName"] + "</td></tr>" +
                                "</tbody></table>";
                        }
                        rowStr+="</td>";
                    }
                    rowStr+="</tr>"
                    allRows+=rowStr;
                    rowStr="";
                }

                $("<table style='margin: auto' class='table table-striped table-hover text-center table-bordered align-middle'>" +
                    "<thead>" +
                    "<tr class='table-primary align-middle'>" +
                    "<th>Hour / Day</th>" +
                    daysStr +
                    "</tr>" +
                    "</thead>" +
                    "<tbody>"+allRows+"</tbody>" +
                    "</table>").appendTo($("#"+solutionContentID).empty());

                //rules
                $(getRulesDataStr(classSolution["m_RuleDataList"])).appendTo($("#"+solutionRulesID).empty());
            },
            error:function(){
                console.log("failed to get Class schedule");
            }
        })
    })
}