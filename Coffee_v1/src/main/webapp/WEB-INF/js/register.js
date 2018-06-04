//注册
$(function(){
    var ok1=false;
    var ok2=false;
    var ok3=false;


    $("#uphone").focus(function(){
        //$(this).next().text('用户名应该为6-20位之间').removeClass('state1').addClass('state2');
    }).blur(function(){
        if($(this).val().match(/^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/)&&$(this).val()!=''){
            //$(this).next().text('输入正确').removeClass('state1').addClass('state4');


            $.ajax({
                type:"post",
                url:"/CheckUserName",
                data: "uphone=" + $("#uphone").val(),
                dataType: "json",
                async:false,
                success:function(obj){
                    if(obj.flag)
                    {
                        $("#uphone").next().text("用户名可使用").css("color","green");
                        ok1=true;
                    }else{
                        $("#uphone").next().text("该用户名已存在，请登录").css("color","red");
                    }


                }
            });

        }else{
            $(this).next().text('请输入正确的手机号').css("color","red");
            //  $(this).next().text('该用户已被注册').removeClass('state1').addClass('state3');


        }
        if($(this).val()=='')
        {
            $(this).next().text('手机号不能为空').css("color","red");

        }

    });


    $("#upwd").focus(function(){
        //$(this).next().text('密码应该为6-12位之间').removeClass('state1').addClass('state2');
    }).blur(function(){
        if($(this).val().length >= 6 && $(this).val().length <=12 && $(this).val()!=''){
            $(this).next().text('输入正确').css("color","green");
            ok2=true;
        }else{
            $(this).next().text('请输入6-12位密码').css("color","red");

        }
        if($(this).val()=='')
        {
            $(this).next().text('密码不能为空').css("color","red");
        }
    });


    $("#upwd2").focus(function(){
        //$(this).next().text('输入的确认密码要和上面的密码一致,规则也要相同').removeClass('state1').addClass('state2');
    }).blur(function(){
        if($(this).val().length >= 6 && $(this).val().length <=20 && $(this).val()!='' && $(this).val() == $("#upwd").val()){
            $(this).next().text('输入正确').css("color","green");
            ok3=true;
        }else{
            $(this).next().text('两次密码有误').removeClass('state1').addClass('state3');
        }
        if($(this).val()=='')
        {
            $(this).next().text('密码不能为空').css("color","red");
        }

    });

    $("#sign").off().on('click', function(){
        if(ok1 && ok2 && ok3 ){
            $.ajax({       //用ajax来实现不刷新网页的基础上更新数据
                type: "post", //请求方式
                url: "/adduser", //路径
                dataType: "json",
                data: {"uphone":$("#uphone").val(),"upwd":$("#upwd").val()},
                aysnc: false,
                success: function (obj) {
                    if (obj.flag) {
                        alert("注册成功");
                          window.location = "login.html"; //注册成功就跳转到login.html
                    }
                    else {
                        alert("注册失败");
                    }

                }
            });
        }
    });
});


