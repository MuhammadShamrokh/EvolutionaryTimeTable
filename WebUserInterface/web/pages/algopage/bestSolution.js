$(function(){
    $("#bestSolutionButton").click(function(){
        var select = document.getElementById('showValue');
        var value = select.options[select.selectedIndex].value;
      switch(value)
      {
          case "":
              break;
          case "Raw":
              console.log("Raw");
              $.ajax({
                  url:"rawSolution",
                  timeout:2000,
                  success: function(rawSolutionData){
                      printRawSolution(rawSolutionData, "bestSolutionContent");
                  },
                  error:function(errorObj){
                      var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                      $("#titleModalLabel").text("Error!");
                      $("#bodyModalLabel").text(errorObj.responseText);
                      myModal.show();
                  }
              })
              break;
          case "Teacher":
              console.log("sending ajax to get Teachers ID Names List");
              $.ajax({
                  url:"teachersNamesList",
                  timeout: 2000,
                  success:function (teachersList){
                     printTeachersSolution(teachersList, "bestSolutionContent", "Main", "GET");
                  },
                  error:function (errorObject){
                      console.log("failed to get Teachers id names list")
                      var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                      $("#titleModalLabel").text("ERROR!");
                      $("#bodyModalLabel").text(errorObject.responseText);
                      myModal.show();
                  }
              })
              break;

          case "Class":
              console.log("sending ajax to get Teachers ID Names List");
              $.ajax({
                  url:"classesNamesList",
                  timeout: 2000,
                  success:function (classesList){
                      printClassesSolution(classesList, "bestSolutionContent", "Main", "GET");
                  },
                  error:function (errorObject){
                      console.log("failed to get classes id names list")
                      var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                      $("#titleModalLabel").text("ERROR!");
                      $("#bodyModalLabel").text(errorObject.responseText);
                      myModal.show();
                  }
              })
              break;
      }
    });
});

