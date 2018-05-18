var slideList=[
    {'ind':0,'img':'image/slide-1.jpg'},
    {'ind':1,'img':'image/slide-2.jpg'},
    {'ind':2,'img':'image/slide-3.jpg'},
    {'ind':3,'img':'image/slide-4.jpg'},
    {'ind':4,'img':'image/slide-5.jpg'},
    {'ind':5,'img':'image/slide-6.jpg'},
    {'ind':5,'img':'image/slide-7.jpg'}
]
//slide ����
var slide={
    itmes:5,//��ʼ����ʾ�ֲ�����
    mheight:0,//�����Ӹ߶�
    speed:2000,//�����ٶ�
    num:4,
    autospeed:2500,//�ֲ����
    mwidth:0,//ÿ��ͼƬ���
    showItems:0,//ʵ����ʾ����
    timer:0,//��ʱ��
    canMmove:true,//�����ж��Ƿ���Խ�����һ������
    //��Ӧʽ���ڴ�С����ʾ����
    responsiveSize: {
        xs: {
            size:480,
            Items: 2
        },
        sm: {
            size:640,
            Items: 3
        },
        md: {
            size:768,
            Items: 3
        }
    },
    //��ʼ������
    init:function(){
        slide.screenChange();
        slide.itemsChange();
        slide.widthChange();
        slide.heightChange();
        $(".slideBox").css('left',-slide.mwidth);
        slide.autoPlay();
    },
    //����Ļ��С�����ı�
    screenChange:function(){
        $(window).resize(function(){
            $(".slideBox").css('left',-slide.mwidth);
            slide.itemsChange();
            slide.widthChange();
            slide.heightChange();
        })
    },

    heightChange:function(){
        slide.mheight=$('.slide .slideBox>div img').height();
        $('.slide').css('height',slide.mheight+"px");
    },

    widthChange:function(){
        slide.mwidth=$('.slide div').width()/slide.showItems;
        $('.slide .slideBox div').css('width',slide.mwidth+"px");
    },

    itemsChange:function(){
        if($('.slide div').width()<=slide.responsiveSize.xs.size){
            slide.showItems=slide.responsiveSize.xs.Items;
        }else if($('.slide div').width()<=slide.responsiveSize.sm.size){
            slide.showItems=slide.responsiveSize.sm.Items;
        }else if($('.slide div').width()<=slide.responsiveSize.md.size){
            slide.showItems=slide.responsiveSize.md.Items;
        }else{
            slide.showItems=slide.itmes;
        }
    },

    moveLeft:function(){
        slide.canMmove=false;
        var space=parseInt($(".slideBox").css('left'))-slide.mwidth;
        $(".slideBox").stop().animate({
            left: space
        }, {
            queue:false, duration:slide.speed,
            easing: "linear",complete:function(){
                $(".slideBox").css('left',-slide.mwidth);
               $('.slideBox div:first-child').insertAfter( $('.slideBox div:last-child'));
                slide.canMmove=true;
            }
        } );
    },

    moveRight:function(){
        slide.canMmove=false;
        var space=parseInt($(".slideBox").css('left'))+slide.mwidth;
        $(".slideBox").animate({
            left: space
        },{
            queue:false, duration:slide.speed,
            easing: "linear",complete:function(){
               $(".slideBox").css('left',-slide.mwidth);
                $('.slideBox div:last-child').insertBefore( $('.slideBox div:first-child'));
                slide.canMmove=true;
            }
        });
    },

    autoPlay:function(){
        slide.timer=setInterval(function(){
            slide.moveRight();
        },slide.autospeed)
    }
}

$('.leftRow').click(function(){
    if(slide.canMmove===true){
        clearInterval(slide.timer);
        slide.moveLeft();
        slide.autoPlay();
    }
});

$('.rightRow').click(function(){
    if(slide.canMmove===true) {
        clearInterval(slide.timer);
        slide.moveRight();
        slide.autoPlay();
    }
});


slide.init();
