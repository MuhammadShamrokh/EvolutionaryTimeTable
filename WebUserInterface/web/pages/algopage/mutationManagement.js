$(function(){
    $("#mutationsForm").submit(function(){
        $.ajax({
            data: $(this).serialize(),
            url:this.action,
            method:'POST',
            timeout:2000,
            success: function(msgObject){
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("SUCCESS!");
                $("#bodyModalLabel").text("Mutation has been added successfully");
                myModal.show();
                mutationUpdate();
            },
            error: function(errorObject){
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("ERROR!");
                $("#bodyModalLabel").text(errorObject.responseText);
                myModal.show();
            }
        });
        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;

    })
});
$(function(){
    $("#updateMutationForm").submit(function(){
        $.ajax({
            data: $(this).serialize(),
            url:this.action,
            method:'POST',
            timeout:2000,
            success: function(){
                mutationUpdate();
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("SUCCESS!");
                $("#bodyModalLabel").text("Mutation has been Edited successfully");
                myModal.show();
                mutationUpdate();

            },
            error: function(errorObject){
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("ERROR!");
                $("#bodyModalLabel").text(errorObject.responseText);
                myModal.show();
            }

        });
        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    });
})


$(function (){
    $.ajax({
        url:"mutation",
        type:'GET',
        success:function (mutationDataList){
            refreshMutationTableRows(mutationDataList)
        }
    });
});


function mutationUpdate()
{
    $.ajax({
        url:"mutation",
        type:'GET',

        success:function (mutationDataList){
            refreshMutationTableRows(mutationDataList)
        }
    });

}

function refreshMutationTableRows(mutationDataList) {
    $("#mutationsTableBody").empty();
    $.each(mutationDataList || [], function (index, mutation) {
        $("<tr><td>" + mutation["m_Name"] +
            "</td><td>" + mutation["m_Probability"] +
            "</td><td>" + mutation["m_Tupples"] +
            "</td><td>" + mutation["m_Component"] +
            "</td><td><button class='btn btn-primary updateMutation' id='" + index + "' type='button'>Edit</button></td>" +
            "</td><td><button class='btn btn-danger deleteMutation' id='" + index + "' type='button'>Delete</button></td></tr>").appendTo($("#mutationsTableBody"))
    });

    $(".updateMutation").click(function () {
        $.ajax({
            data: "mutationIndex="+this.getAttribute("id"),
            url:"mutationUpdate",
            timeout:2000,
            success: function(mutation){
                document.getElementById("tupplesUpdate").value=mutation["m_Tupples"].toString();
                document.getElementById("probabilityUpdate").value=mutation["m_Probability"].toString();
                document.getElementById("mutationUpdateType").value=mutation["m_Name"].toString();
                document.getElementById("componentUpdate").value=mutation["m_Component"].charAt(0);
                var updateMutationModal = new bootstrap.Modal(document.getElementById('mutationModal'));
                updateMutationModal.show();
            },
            error: function() {
                console.log("error while clicking on update btn");
            }
        })
    });
    $(".deleteMutation").click(function () {
        $.ajax({
            headers: {"mutationIndex":this.getAttribute("id")},
            url: "mutation",
            method:"DELETE",
            timeout: 2000,
            success: (function () {
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("SUCCESS!");
                $("#bodyModalLabel").text("Mutation has been deleted successfully");
                myModal.show();
                mutationUpdate();
            }),
            error: function () {
                console.log("Error deleting mutation");
            }

        })

    });


}

