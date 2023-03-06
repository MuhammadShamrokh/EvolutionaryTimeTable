$(function() { // onload...do
    $("#loadForm").submit(function() {
        var fileLoader = this[0].files[0];
        var fileCount=0;
        var formData = new FormData();
        formData.append(fileCount.toString(), fileLoader);

        $.ajax({
            method:'POST',
            data: formData,
            url: this.action,
            processData: false, // Don't process the files
            contentType: false, // Set content type to false as jQuery will tell the server its a query string request
            timeout: 4000,
            error: function(errorObject) {
                var myModal = new bootstrap.Modal(document.getElementById('fileModal'));
                $("#titleModalLabel").text("ERROR!");
                $("#bodyModalLabel").text(errorObject.responseText);
                myModal.show();
            },
            success: function(r) {
                var myModal = new bootstrap.Modal(document.getElementById('fileModal'));
                $("#titleModalLabel").text("SUCCESS!");
                $("#bodyModalLabel").text("The file has been loaded successfully");
                myModal.show();
            }
        });

        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    })
})
