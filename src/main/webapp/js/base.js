$(function(){

    //下拉菜单处理
    var profile = $("#profile");
    var downMenu = $("#down-menu");

    // 鼠标移入和移出事件
    profile.mouseover(function(){
        downMenu.show();
        $(this).css("background-color","#333");
        /*$(this).css("background-color","#ff0000");*/
    }).mouseout(function(){
        downMenu.hide();
        $(this).css("background-color","#3d444c");
    });

    // 侧栏的滚动条
    downMenu.mouseover(function(){
        downMenu.show();
    }).mouseout(function(){
        downMenu.hide();
    });


});
