$(document).ready(function(){
    $.ajax({

        type: "post",
        url: "getshoplist",
        dateType: "json",
        async: false,
        success: function (date) {

            var str = "";
            var str2 = "<h1>1111111111111111111111111111111111</h1>";
            alert(date[1].shoppri);
           for (var i = 0; i < 'data'.length; i++) {

                str += "<div class=\"shoppro\">";
                str += " <img src=" + 'data[i]'.shopimg + ">";
               alert('data'[i].shopimg);
                //alert(data[i].mman);
                str += "<div class=\"shoptxt\">";
                str += "<h4>" + 'data[i]'.shopname + "</h4>";
               alert('date[i]'.shopname);
                str += "<p>" + data[i].shopdes + "</p>";
                str += "</div>";
                str += "<div class=\"shopprice\">";
                str += "<span>" + data[i].shoppri + "</span>";
                str += "<span><a href=\"#\">添加至购物车</a></span>";
                str += "</div>";
                str += "</div>";
            }
            $("#hs").append(str);
            $("#hs2").append(str2);
        }


    });
});

