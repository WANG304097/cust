<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     isELIgnored="false"
    %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>WIN10-UI-DEMO</title>
    <link rel='Shortcut Icon' type='image/x-icon' href='/cust/web/win10/img/windows.ico'>
    <script type="text/javascript" src="/cust/web/win10/js/jquery-2.2.4.min.js"></script>
    <link href="/cust/web/win10/css/animate.css" rel="stylesheet">
    <script type="text/javascript" src="/cust/web/win10/component/layer-v3.0.3/layer/layer.js"></script>
    <link rel="stylesheet" href="/cust/web/win10/component/font-awesome-4.7.0/css/font-awesome.min.css">
    <link href="/cust/web/win10/css/default.css" rel="stylesheet">
    <script type="text/javascript" src="/cust/web/win10/js/win10.js"></script>


    <style>
        * {
            font-family: "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei", 华文细黑, STHeiti, MingLiu
        }
        /*磁贴自定义样式*/
        .win10-block-content-text {
            line-height: 44px;
            text-align: center;
            font-size: 16px;
        }
    </style>
    <script>
        Win10.onReady(function () {

            //设置壁纸
            Win10.setBgUrl({
                main:'/cust/web/win10/img/wallpapers/main.jpg',
                mobile:'/cust/web/win10/img/wallpapers/mobile.jpg',
            });

            Win10.setAnimated([
                'animated flip',
                'animated bounceIn',
            ], 0.01);

//             setTimeout(function () {
//                 Win10.newMsg('官方交流一群', '欢迎各位大侠加入讨论：<a target="_blank" href="https://jq.qq.com/?_wv=1027&k=4Er0u8i">[点击加入]205546163</a>')
//             }, 2500);

//             setTimeout(function () {
//                 Win10.openUrl('//win10ui.yuri2.cn/src/broadcast.html','<i class="fa fa-newspaper-o icon red"></i>最新资讯',[['300px', '380px'],'rt'])
//             },2000);


        });

        //该函数可删除 Orz
        function win10_forgive_me() {
            Win10.enableFullScreen();
            layer.alert('点击展示下一版本特性', {}, function(index){
                var blue=$('<img src="/cust/web/win10/img/presentation/bluescreen.jpg" style="position: fixed;width: 100%;height:100%;top:0;z-index:9999999999" />');
                setTimeout(function () {
                    $('body').append(blue);
                },3000);
                setTimeout(function () {
                    blue.remove();
                    Win10.disableFullScreen();
                    setTimeout(function () {
                        layer.msg('开个玩笑，别打我');

                    },1000);
                },7000);
                layer.close(index);
            });

        }
    </script>
</head>
<body>
<div id="win10">
    <div class="desktop">
        <div id="win10-shortcuts" class="shortcuts-hidden">
                    
            <c:forEach items="${menus}" var="m" >
             <c:forEach items="${m.child}" var="m2" >
         
           <div class="shortcut" onclick='Win10.openUrl("${pageContext.request.contextPath}${m2.menuUrl}","<i class=\"fa fa-user icon black-green\"></i>${m2.menuName}")'>
                <i class="fa fa-user icon black-green"></i>
                <div class="title"> ${m2.menuName}</div>
            </div>
          
          </c:forEach>
            
            </c:forEach>
            
          </div>
        <div id="win10-desktop-scene"></div>
      </div>
    <div id="win10-menu" class="hidden">
        <div class="list win10-menu-hidden animated animated-slideOutLeft">
            <div class="item"><i class="red icon fa fa-wrench fa-fw"></i><span>API测试</span></div>
            <div class="sub-item" onclick="Win10.openUrl('/cust/web/win10/child.html','子页')">父子页沟通</div>
            <div class="sub-item" onclick="Win10.commandCenterOpen()">打开消息中心</div>
            <div class="sub-item" onclick="Win10.newMsg('API测试','通过API可以发送消息至消息中心，自定义标题与内容(点击我试试)',function() {alert('click')})">发送带回调的消息</div>
            <div class="sub-item" onclick="Win10.menuClose()">关闭菜单</div>
            <div class="item" ><i class="blue icon fa fa-gavel fa-fw"></i>辅助工具</div>
            <div class="sub-item" onclick="Win10.openUrl('win10ui.yuri2.cn/src/tools/builder-shortcut.html','图标代码生成器')">桌面图标代码生成器</div>
            <div class="sub-item" onclick="Win10.openUrl('win10ui.yuri2.cn/src/tools/builder-tile.html','磁贴代码生成器')">磁贴代码生成器</div>
            <div class="sub-item" onclick="Win10.openUrl('win10ui.yuri2.cn/src/tools/builder-menu.html','菜单代码生成器')">菜单代码生成器</div>
            <div class="item" onclick="Win10.aboutUs()"><i class="purple icon fa fa-info-circle fa-fw"></i>关于</div>
            <div class="item" onclick="layer.open({
                title:'支持作者',
                type: 1,
                area:'300px',
                offset:'50px',
                shadeClose:true,
                content: '<img width=\'300\' src=\'/cust/web/win10/img/presentation/donation.jpg\' />'
            })"><i class="green icon fa fa-thumbs-up fa-fw"></i>捐赠</div>
            <div class="item" ><a href="${pageContext.request.contextPath}/user/login"><i class="black icon fa fa-power-off fa-fw"></i>退出登录</a></div>
        </div>
        <div class="blocks">
            <div class="menu_group">
                <div class="title">
                    应用示例
                </div>
                <div loc="1,1" size="6,3" class="block">
                    <div class="content red detail" >
                        <iframe style="margin-top: 10px" frameborder="no" border="0" marginwidth="0" marginheight="0" width=264 height=110 src="//music.163.com/outchain/player?type=2&id=110771&auto=0&height=90"></iframe>
                    </div>
                    <div class="content red cover">
                        <img width="264" style="position: relative;top: -50px;" src="/cust/web/win10/img/presentation/wangyiyun.gif" />
                    </div>
                </div>
                <div loc="1,4" size="4,4" class="block">
                    <div class="content blue cover" style="font-size: 30px;line-height: 176px;text-align: center">
                        <i class="fa fa-cloud fa-fw"></i> 天气
                    </div>
                    <div class="content detail" style="background-color: rgb(46,147,217)">
                        <iframe src="//www.seniverse.com/weather/weather.aspx?uid=U43DF172E7&cid=CHBJ000000&l=&p=SMART&a=1&u=C&s=13&m=2&x=1&d=1&fc=&bgc=2E93D9&bc=&ti=0&in=0&li=" frameborder="0" scrolling="no" width="200" height="300" allowTransparency="true"></iframe>
                    </div>
                </div>
                <div loc="5,4" size="2,2" class="block">
                    <div class="content">
                        <img style="display: inline-block;border-radius: 4px" width="88px" src="http://q2.qlogo.cn/headimg_dl?bs=824831811&dst_uin=824831811&src_uin=824831811&fid=824831811&spec=100&url_enc=0&referer=bu_interface&term_type=PC"/>
                    </div>
                </div>
                <div loc="5,6" size="2,2" class="block" onclick="win10_forgive_me()">
                    <div class="content red">
                        <div style="text-align: center;font-size: 30px;line-height: 44px"><i class="fa fa-bug fa-fw"></i></div>
                        <div style="text-align: center;font-size: 16px;line-height: 44px">别点我</div>
                    </div>
                </div>
            </div>

            <div class="menu_group">
                <div class="title">
                    常用场景
                </div>
                <div loc="1,1" size="4,3" class="block">
                    <div class="content" style="background-color: orangered">
                        <div class="win10-block-content-text" style="font-size: 26px;line-height: 88px">WIN10-UI</div>
                        <div class="win10-block-content-text">显示信息</div>
                    </div>
                </div>
                <div loc="5,1" size="2,1" class="block">
                    <div class="content" style="background-color: green">
                        <div  class="win10-block-content-text">文字按钮</div>
                    </div>
                </div>
                <div loc="5,2" size="2,2" class="block">
                    <div class="content">
                        <img style="width: 40px;height: 40px;margin: 5px auto;display: block" src="/cust/web/win10/img/icon/baidu.png">
                        <div class="win10-block-content-text">图标按钮</div>
                    </div>
                </div>
                <div loc="1,4" size="6,3" class="block">
                    <div class="content" style="background: url('/cust/web/win10/img/presentation/1.png');background-size: auto">
                        <div style="line-height:132px;text-align: center;">显示图片</div>
                    </div>
                </div>
                <div loc="1,7" size="2,1" class="block">
                    <div class="content" style="background-color: grey" onclick="Win10.openUrl('//www.baidu.com')">
                        <div class="win10-block-content-text">内部链接</div>
                    </div>
                </div>
                <div loc="3,7" size="2,1" class="block">
                    <div class="content" style="background-color: blue" onclick="window.open('https://www.baidu.com')">
                        <div class="win10-block-content-text">外部链接</div>
                    </div>
                </div>
            </div>
        </div>
        <div id="win10-menu-switcher"></div>
    </div>
    <div id="win10_command_center" class="hidden_right">
        <div class="title">
            <h4 style="float: left">消息中心 </h4>
            <span id="win10_btn_command_center_clean_all">全部清除</span>
        </div>
        <div class="msgs"></div>
    </div>
    <div id="win10_task_bar">
        <div id="win10_btn_group_left" class="btn_group">
            <div id="win10_btn_win" class="btn"><span class="fa fa-windows"></span></div>
            <div class="btn" id="win10-btn-browser"><span class="fa fa-internet-explorer"></span></div>
        </div>
        <div id="win10_btn_group_middle" class="btn_group"></div>
        <div id="win10_btn_group_right" class="btn_group">
            <div class="btn" id="win10_btn_time">
                <!--0:00<br/>-->
                <!--1993/8/13-->
            </div>
            <div class="btn" id="win10_btn_command"><span id="win10-msg-nof" class="fa fa-comment-o"></span></div>
            <div class="btn" id="win10_btn_show_desktop"></div>
        </div>
    </div>
</div>
</body>
</html>