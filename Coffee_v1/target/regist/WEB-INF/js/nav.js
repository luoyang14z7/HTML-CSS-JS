$(document).ready(function(){
    if(sessionStorage==null)
    {
        window.location = "login.html";
    }
    else{
        $(".head-login").css('display','none');
        $(".head-exit").css('display','block');
        $(".head-exit #uid").text(sessionStorage.getItem('uphone'));
    }



    $(".nav li").hover(function(){
    $(this).find(".down").stop().fadeIn(100);
},function(){
    $(this).find(".down").stop().fadeOut();
})

    $(".nav .down li").mouseover(function(){
        $(this).find("span").stop().animate({fontSize:'1.4em'},"1000");
})
    $(".nav .down li").mouseout(function(){
        $(this).find("span").stop().animate({fontSize:'18px'});
    })



    /*alert($(".content .Beverage").offset().top);
   //alert($(".content .Dessert").offset().top);
    $(window).scroll(function(){

        //var st=$(this).scrollTop();
    })

    $(" .down .bev ").click(function(){
       // var idx=$("this").index();
    //var bert=$(".content .Dessert").offset().top;

        //$("body,html").animate({scrollTop(1452)})
        $("body,html").scrollTop(300)
    })
        $(" .down .des ").click(function(){
        $("body,html").scrollTop(1300);

    })*/
    //轮播
//$("ol").addClass("on");

    $(".pic li").eq(0).show();
    $(".num li").eq(0).addClass("on");
    var size=$(".pic li").length;

    //手动控制轮播
    $(".num li").mouseover(function () {
        $(this).addClass("on").siblings().removeClass("on");
        var idx=$(this).index();
        i=idx;
        $(".pic li").eq(idx).fadeIn().siblings().fadeOut();
       // $(".banner .cover2").addClass("in")
    })

    //自动轮播器
    var i=0;
    var t=setInterval(move,2000)

    //停住
    $(".banner").hover(function(){
        clearInterval(t)
    },function(){
        t=setInterval(move,2000)
    })


    function move(){
        i++;
        if(i==size)
        {
            i=0;
        }
        $(".pic li").eq(i).fadeIn().siblings().fadeOut();
        $(".num li").eq(i).addClass("on").siblings().removeClass("on");
    }


   /* $(window).scroll(function () {
        $(".banner .cover2").addClass("in")

    });*/




})
