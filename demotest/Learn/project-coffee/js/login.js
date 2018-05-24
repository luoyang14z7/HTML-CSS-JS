//登录
$(function(){
    var ok1=false;
    var ok2=false;


    $(".login-form #uphone").focus(function(){
        //$(this).next().text('用户名应该为6-20位之间').removeClass('state1').addClass('state2');
    }).blur(function(){
        if($(this).val().match(/^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/)&&$(this).val()!=''){
            $(this).next().text('输入正确').removeClass('state1').addClass('state4');
            ok1=true;

        }else{
            $(this).next().text('请输入正确的手机号').removeClass('state1').addClass('state3');
            //  $(this).next().text('该用户已被注册').removeClass('state1').addClass('state3');

        }
        if($(this).val()=='')
        {
            $(this).next().text('手机号不能为空').removeClass('state1').addClass('state3');
        }

    });
    $(" .login-form #upwd").focus(function(){
        //$(this).next().text('密码应该为6-12位之间').removeClass('state1').addClass('state2');
    }).blur(function(){
        if($(this).val().length >= 6 && $(this).val().length <=12 && $(this).val()!=''){
            $(this).next().text('输入正确').removeClass('state1').addClass('state4');
            ok2=true;
        }else{
            $(this).next().text('请输入6-12位密码').removeClass('state1').addClass('state3');

        }
        if($(this).val()=='')
        {
            $(this).next().text('密码不能为空').removeClass('state1').addClass('state3');
        }
    });

    $(".login-form .login-icon").click(function(){
        if(ok1 && ok2 ){

            $.ajax({       //用ajax来实现不刷新网页的基础上更新数据
                type:"post", //请求方式
                url:"", //路径
                data: (
                    $(".login-form #uphone").val(),
                    $(" .login-form #upwd").val()
                ),
                datatype:"json",
                async:false,
                success:function(check){
                    if(check.flag)
                    {
                        alert("登录成功");
                        sessionStorage.setItem();
                        sessionStorage.setItem("uphone",$(".login-form #uphone").val());
                        $(".right-login").css('display','none');
                        $(".head-exit").css('display','block');
                        $(".head-exit #uid").innerHTML($(".login-form #uphone").val());
                        window.location = "index.html";

                    }
                    else{
                        alert("手机号或密码错误");
                    }
                }
            });
        }else{
            return false;
        }
    });
    $(".head-exit").click(function(){
        sessionStorage.removeItem('uphone');
        $(".right-login").css('display','block');
        $(".head-exit").css('display','none');
        $(".head-exit #uid").innerHTML();

    })
})
$(function(){

})
