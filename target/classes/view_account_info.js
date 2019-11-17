$(document).ready(function () {
    // var semesterId = window.location.pathname.split("/")[3];
    var accId = 5;
    // $("#retrieve-resources").click(function () {
    $.ajax({
        type: 'GET',
        url: '/view/home/' + accId, // Using our resources.json file to serve results
        dataType: 'json',
        success: function (result) {
            var accountInfo = $("#account-info");
            var data = "<p><l>Name: </l>" + result[0].fname + "<l> </l>" + result[0].lname + "</p>"
                + "<p><l>Code: </l>" + result[0].code + "</p>";

            accountInfo.html(data);
        }
    });
});