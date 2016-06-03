/**
 * Created by Denis on 31.05.2016.
 */
function showUserArea(areaId) {
    hideActionForms();
    document.getElementById(areaId+"Form").style.display = "block";
}

function hideActionForms() {
    $("form").each(function (i) {
        $(this).css("display", "none");
    });
    $("#actionResultArea").css("display", "none");
}
