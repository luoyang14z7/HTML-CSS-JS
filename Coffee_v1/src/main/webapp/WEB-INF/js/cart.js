$(window).load(function () {
    $(".orderbox").eq(0).show();
    $(".er li").eq(0).addClass("on")

    //alert($(".er li").length);
    //alert($(".box").length)
    //var size= $(".box").length;
    //alert($(".box").index());

    $(".button1").click(function () {
        $(".orderbox").eq(1).show();
        $(".orderbox").eq(0).hide();
        $(".orderbox").eq(2).hide();
        $(".er li").eq(1).addClass("on").siblings().removeClass("on");
    })
    $(".button2").click(function () {
        $(".orderbox").eq(2).show();
        $(".orderbox").eq(0).hide();
        $(".orderbox").eq(1).hide();
        $(".er li").eq(2).addClass("on").siblings().removeClass("on");
    })


});

$(document).ready(function() {
    var cartallpri = new Number(0);
    var orderallpri = new Number(0);
    var uid = sessionStorage.getItem("uid");
    if (uid == null) {
        alert("请先登录！");
        window.location.href = "login.html";
    }
    $.ajax({
        type: "post",
        url: "getcartlist",
        dateType: "json",
        data: {
            "uid": uid,
        },
        async: false,
        success: function (date) {
            var uid = sessionStorage.getItem("uid");
            var str = "";
            var datalist = eval('(' + 'date' + ')');
            var orderstr = "";

            /*alert(datalist[1].shopname);*/
            $(document).on("click", ".click", function () {

                var i = $(this).val();
                var sid = datalist[i].sid;

                console.log(i);
                console.log(uid + sid);
                $.ajax({       //用ajax来实现不刷新网页的基础上更新数据
                    type: "post", //请求方式
                    url: "deletclick", //路径
                    data: {
                        "uid": uid,
                        "sid": sid
                    },
                    dataType: "json",
                    async: true,
                    success:function (date) {
                        location.reload();
                    }

                });
            });
            $(document).on("click", ".jia", function () {
                var i = $(this).val();
                var sid = datalist[i].sid;

                console.log(i);
                console.log(uid + sid);
                $.ajax({       //用ajax来实现不刷新网页的基础上更新数据
                    type: "post", //请求方式
                    url: "jiaclick", //路径
                    data: {
                        "uid": uid,
                        "sid": sid
                    },
                    dataType: "json",
                    async: true,
                    success:function (date) {
                        location.reload();
                    }

                });
            });
            $(document).on("click", ".jian", function () {
                var i = $(this).val();
                var sid = datalist[i].sid;
                var num = datalist[i].shopnum;

                console.log(i);
                console.log(uid + sid);
                $.ajax({       //用ajax来实现不刷新网页的基础上更新数据
                    type: "post", //请求方式
                    url: "jianclick", //路径
                    data: {
                        "uid": uid,
                        "sid": sid,
                        "num": num
                    },
                    dataType: "json",
                    async: true,
                    success:function (date) {
                        location.reload();
                    }

                });
            });
            for (var i = 0; i < datalist.length; i++) {
                str += "<tr>"
                str += "<td ><a href=\"#\"><img src=" + datalist[i].shopimg + " height=\"180\" class=\"img\"></a></td>";
                str += "<td><a  href=\"#\">" + datalist[i].shopname + "</a></td>";
                str += "<td align=\"center\">￥" + datalist[i].shoppri + ".00<input name=\"goods_price\" type=\"hidden\" value=\"2600.00\"></td>";
                str += "<td align=\"center\" class='icon'>";
                str += "<button id='jian' class=\"jian\" value="+ i + " >-</a>";
                str += "<input type=\"text\" name=\"goods_quantity\" id='numinput' class=\"input\" style=\"width:30px;text-align:center;ime-mode:Disabled;\" value=" + datalist[i].shopnum +" >";
                str += "<button id='jia' class=\"jia\" value="+ i +">+</button>";
                str += "</td>";
                str += "<td align=\"center\"><button  id='delete' class=\"click\" value=" + i +" >删除</button></td>";
                str += "</tr>"
                cartallpri += Number(datalist[i].shoppri) * Number(datalist[i].shopnum);


            }
            str += "<th colspan=\"9\" align=\"right\" bgcolor=\"#f5f5f5\" > 商品件数："+ datalist.length + "件&nbsp;&nbsp;&nbsp; 商品总金额（不含运费）：<font color=\"#FF0000\" >￥"+cartallpri+".00</font></th>"

            $("#cartlist").append(str);


            for (var i = 0; i < datalist.length; i++) {
                orderstr += "<tr>"
                orderstr += "<td ><a href=\"#\"><img src=" + datalist[i].shopimg + " height=\"180\" class=\"img\"></a></td>";
                orderstr += "<td><a  href=\"#\">" + datalist[i].shopname + "</a></td>";
                orderstr += "<td align=\"center\">￥" + datalist[i].shoppri + ".00<input name=\"goods_price\" type=\"hidden\" value=\"2600.00\"></td>";
                orderstr += "<td align=\"center\">";
                orderstr += "<input type=\"text\" name=\"goods_quantity\" class=\"input\" style=\"width:30px;text-align:center;ime-mode:Disabled;\" value=" + datalist[i].shopnum +" readonly=\"readonly\" >";
                orderstr += "</td>";
                orderstr += "</tr>"


            }
            orderallpri = cartallpri + 6;
            orderstr += "<th colspan=\"9\" align=\"right\" bgcolor=\"#f5f5f5\" > 商品件数："+ datalist.length + "件&nbsp;&nbsp;&nbsp; 商品总金额(商品金额+运费6元)：<font color=\"#FF0000\" >￥"+ orderallpri +".00</font></th>"


            $("#carttoorder").append(orderstr);

        },




    });

    $("#pay").click(function () {
        var payname = "";
        var payphone = "";
        var payaddress = "";
        var paychose = "";
        var paydata = Math.round(new Date().getTime()/1000).toString();

        payname = $("#payname").val();
        payphone = $("#payphone").val();
        payaddress = $("#payaddress").val();
        paychose = $('input:radio[name="paychose"]:checked').val();

        $.ajax({
            type: "post",
            url: "putorder",
            dateType: "json",
            data: {
                "uid": uid,
                "payname": payname,
                "payphone": payphone,
                "payaddress": payaddress,
                "paypri" : orderallpri,
                "paychose" : paychose,
                "paydata" :paydata

            },
            async: false,
            success: function (date) {
                $("#ali").append(date);


            }


        });
    });
});

