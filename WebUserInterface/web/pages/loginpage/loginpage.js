
$(function() {
    $("#loginForm").submit(function () {
        $.ajax({
            data: $(this).serialize(),
            url: this.action,
            timeout: 2000,
            error: function (errorObject) {
                $("#errormessage").empty().append(errorObject.responseText)
            },
            success: function (nextDirection) {
                window.location.replace(nextDirection);
            }
        });
        return false;
    });
});