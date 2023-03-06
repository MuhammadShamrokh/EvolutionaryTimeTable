
var labelsUpdate;
$(function() {
    $("#startBtn").click(function () {
        $.ajax({
            url:"activate",
            timeout:2000,
            success:function(){
                activateAlgo();
            },
            error:function()
            {
                var myModal = new bootstrap.Modal(document.getElementById('activateModal'));
                myModal.show();
            }
        })

    })
})

$(function() {
    $("#modalActivateBTN").click(function(){
        activateAlgo();
    })
})



function activateAlgo(){
    var showEvery=document.getElementById("showEvery").value;
    $.ajax({
        data:"showEvery="+showEvery.toString(),
        url:"activate",
        method:'POST',
        timeout:2000,
        success: function(){
            console.log("algorithm was activated successfully");
            playBtnDisabilityManagement();
            $("#updatesLine").empty();
            $("#generationProgressBar").css('width', 0+'%').attr('aria-valuenow', 0);
            $("#fitnessProgressBar").css('width', 0+'%').attr('aria-valuenow', 0);
            $("#timeProgressBar").css('width', 0+'%').attr('aria-valuenow', 0);
            labelsUpdate=setInterval(algoProgressUpdate,1000);
            mainChart.data.datasets[0].data=[];
            mainChart.data.labels=[];
        },
        error: function(errorObject){
            var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
            $("#titleModalLabel").text("ERROR!");
            $("#bodyModalLabel").text(errorObject.responseText);
            myModal.show();
        }

    });
}

$(function() {
    $("#pauseBtn").click(function () {
        $.ajax({
            url:"pause",
            timeout: 2000,
            success: function(){
                console.log("paused the algorithm");
                pauseBtnDisabilityManagement();
                clearInterval(labelsUpdate);
            },
            error: function(errorObj){
                console.log("failed to pause algorithm because: "+errorObj.responseText);
            }
        })
    })
})

$(function() {
    $("#stopBtn").click(function () {
        $.ajax({
            url:"stopAlgo",
            timeout: 2000,
            success: function(progressData){
                console.log("stopped algorithm");
                stopBtnDisabilityManagement();
                clearInterval(labelsUpdate);
                $("#statusLine").empty().text("Generation made: "+progressData["m_GenerationMade"]);
                $("#updatesLine").empty().text("After "+progressData["m_GenerationMade"]+" generations, Best fitness: "+progressData["m_Fitness"]);
            },
            error: function(errorObj){
                console.log("failed to stop algorithm because: "+errorObj.responseText);
            }
        })
    })
})



function playBtnDisabilityManagement()
{
    document.getElementById("startBtn").disabled = true;
    document.getElementById("pauseBtn").disabled=false;
    document.getElementById("stopBtn").disabled=false;
    document.getElementById("addNewMutation").disabled = true;
    document.getElementById("submitBtn").disabled = true;
    $(".updateMutation").prop('disabled', true);
    $(".deleteMutation").prop('disabled', true);

}

function pauseBtnDisabilityManagement()
{
    document.getElementById("startBtn").disabled = false;
    document.getElementById("pauseBtn").disabled=true;
    document.getElementById("stopBtn").disabled=false;
    document.getElementById("addNewMutation").disabled = false;
    document.getElementById("submitBtn").disabled = false;
    $(".updateMutation").prop('disabled', false);
    $(".deleteMutation").prop('disabled', false);

}

function stopBtnDisabilityManagement()
{
    document.getElementById("startBtn").disabled = false;
    document.getElementById("pauseBtn").disabled=true;
    document.getElementById("stopBtn").disabled=true;
    document.getElementById("addNewMutation").disabled = false;
    document.getElementById("submitBtn").disabled = false;
    $(".updateMutation").prop('disabled', false);
    $(".deleteMutation").prop('disabled', false);

}