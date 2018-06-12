$(document).ready(function() {
    var uid = sessionStorage.getItem("uid");
    if (uid == null) {
        alert("请先登录！");
        window.location.href = "login.html";
    }
    $.ajax({
        type: "post",
        url: "getorderlist",
        dateType: "json",
        data: {
            "uid": uid,
        },
        success: function (date) {
           /* alert(date[0].oid);*/
            $("#oid").html(date[0].oid);
            $("#oname").html(date[0].oname);
            $("#oaddress").html(date[0].oaddress);
            $("#ophone").html(date[0].ophone);
            $("#opri").html(date[0].paypri);
            $("#opaychose").html(date[0].opay);
            $("#odata").html( new Date(date[0].odata * 1000).toLocaleString());
            $("#ostat").html(date[0].ostat);
        }
    });
});