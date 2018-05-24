
$(document).ready(function() {
    $(".orderbox").eq(0).show();
   $(".er li").eq(0).addClass("on")

    //alert($(".er li").length);
    //alert($(".box").length)
    //var size= $(".box").length;
    //alert($(".box").index());

    $(".button1").click(function(){
        $(".orderbox").eq(1).show();
        $(".orderbox").eq(0).hide();
        $(".orderbox").eq(2).hide();
        $(".er li").eq(1).addClass("on").siblings().removeClass("on");
    })
    $(".button2").click(function(){
        $(".orderbox").eq(2).show();
        $(".orderbox").eq(0).hide();
        $(".orderbox").eq(1).hide();
        $(".er li").eq(2).addClass("on").siblings().removeClass("on");
    })


})