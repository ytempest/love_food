﻿<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <link rel="stylesheet"
          href="http//fonts.googleapis.com/css?family=Open+Sans:400,300"/>

    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-skins.min.css"/>

    <script src="assets/js/ace-extra.min.js"></script>

    <script src="assets/self/common.js"></script>
    <script src="assets/self/request.js"></script>
    <script src="assets/self/user_page_list_request.js"></script>
    <script src="assets/self/user_update_request.js"></script>
</head>


<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small><i
                        class="icon-food"></i> 大街小巷后台管理系统
                </small>
            </a>
            <!-- /.brand -->
        </div>
        <!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue"><a data-toggle="dropdown" href="#"
                                          class="dropdown-toggle"> <img class="nav-user-photo"
                                                                        src="assets/avatars/user.jpg"/>
                    <span class="user-info">
								<small>欢迎光临</small>${ adminInfo.adminName }
						</span> <i class="icon-caret-down"></i>
                </a>

                    <ul
                            class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li><a href="#"> <i class="icon-cog"></i> 设置
                        </a></li>

                        <li><a href="javascript:void(0);"
                               onclick="replaceContentClick('admin/admin_info_preview')"> <i
                                class="icon-user"></i> 个人资料
                        </a></li>

                        <li class="divider"></li>

                        <li><a href="login.jsp"> <i class="icon-off"></i> 退出
                        </a></li>
                    </ul>
                </li>
            </ul>

        </div>
        <!-- /.navbar-header -->
    </div>
    <!-- /.container -->
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#"> <span
                class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="nav nav-list">
                <li class="active"><a href="index.jsp"> <i
                        class="icon-dashboard"></i> <span class="menu-text"> 控制台 </span>
                </a></li>

                <li><a href="#" class="dropdown-toggle"> <i
                        class="icon-list"></i> <span class="menu-text"> 用户管理 </span> <b
                        class="arrow icon-angle-down"></b>
                </a>

                    <ul class="submenu">
                        <li><a href="javascript:void(0);"
                               onclick="ajaxGetRequest('user/list.s')"> <i
                                class="icon-double-angle-right"></i> 用户列表
                        </a></li>

                        <li><a href="javascript:void(0);"
                               onclick="replaceContentClick('manage/user_manage_add_user')">
                            <i class="icon-double-angle-right"></i> 添加用户
                        </a></li>
                    </ul>
                </li>

                <li><a href="#" class="dropdown-toggle"> <i
                        class="icon-comments"></i> <span class="menu-text"> 话题管理 </span>

                    <b class="arrow icon-angle-down"></b>
                </a>

                    <ul class="submenu">
                        <li><a href="form-elements.html"> <i
                                class="icon-double-angle-right"></i> 话题列表
                        </a></li>

                        <li><a href="form-wizard.html"> <i
                                class="icon-double-angle-right"></i> 向导提示 &amp; 验证
                        </a></li>

                        <li><a href="wysiwyg.html"> <i
                                class="icon-double-angle-right"></i> 编辑器
                        </a></li>

                        <li><a href="dropzone.html"> <i
                                class="icon-double-angle-right"></i> 文件上传
                        </a></li>
                    </ul>
                </li>

                <li><a href="#" class="dropdown-toggle"> <i
                        class="icon-calendar "></i> <span class="menu-text"> 活动管理
						</span> <b class="arrow icon-angle-down"></b>
                </a>

                    <ul class="submenu">
                        <li><a href="profile.html"> <i
                                class="icon-double-angle-right"></i> 活动列表
                        </a></li>

                        <li><a href="inbox.html"> <i
                                class="icon-double-angle-right"></i> 增加活动
                        </a></li>

                        <li><a href="pricing.html"> <i
                                class="icon-double-angle-right"></i> 售价单
                        </a></li>

                        <li><a href="invoice.html"> <i
                                class="icon-double-angle-right"></i> 购物车
                        </a></li>

                        <li><a href="timeline.html"> <i
                                class="icon-double-angle-right"></i> 时间轴
                        </a></li>

                        <li><a href="login.html"> <i
                                class="icon-double-angle-right"></i> 登录 &amp; 注册
                        </a></li>
                    </ul>
                </li>

                <li><a href="#" class="dropdown-toggle"> <i
                        class="icon-book"></i> <span class="menu-text"> 菜谱管理 </span> <b
                        class="arrow icon-angle-down"></b>
                </a>

                    <ul class="submenu">
                        <li><a href="faq.html"> <i
                                class="icon-double-angle-right"></i> 帮助
                        </a></li>

                        <li><a href="error-404.html"> <i
                                class="icon-double-angle-right"></i> 404错误页面
                        </a></li>

                        <li><a href="error-500.html"> <i
                                class="icon-double-angle-right"></i> 500错误页面
                        </a></li>

                        <li><a href="grid.html"> <i
                                class="icon-double-angle-right"></i> 网格
                        </a></li>

                        <li><a href="blank.html"> <i
                                class="icon-double-angle-right"></i> 空白页面
                        </a></li>
                    </ul>
                </li>
            </ul>
            <!-- /.nav-list -->

            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left"
                   data-icon1="icon-double-angle-left"
                   data-icon2="icon-double-angle-right"></i>
            </div>

            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'collapsed')
                } catch (e) {
                }
            </script>
        </div>

        <div id="content_container" class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li><i class="icon-home home-icon"></i> <a
                            href="javascript:void(0);">首页</a></li>
                    <li class="active">控制台</li>
                </ul>
                <!-- .breadcrumb -->
            </div>

            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                        <div class="alert alert-block alert-success">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="icon-remove"></i>
                            </button>

                            <i class="icon-ok green"></i> 欢迎使用 <strong class="green">
                            大街小巷后台管理系统
                            <small>(v1.0)</small>
                        </strong>
                        </div>

                        <div class="hr hr32 hr-dotted"></div>

                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->

        </div>
    </div>

    <a href="#" id="btn-scroll-up"
       class="btn-scroll-up btn btn-sm btn-inverse"> <i
            class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->

<script
        src="http//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

<script type="text/javascript">
    window.jQuery
    || document
        .write("<script src='assets/js/jquery-2.0.3.min.js'>"
            + "<" + "script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>" + "<" + "script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ("ontouchend" in document)
        document
            .write("<script src='assets/js/jquery.mobile.custom.min.js'>"
                + "<" + "script>");
</script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>

<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="assets/js/jquery.slimscroll.min.js"></script>
<script src="assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="assets/js/jquery.sparkline.min.js"></script>
<script src="assets/js/flot/jquery.flot.min.js"></script>
<script src="assets/js/flot/jquery.flot.pie.min.js"></script>
<script src="assets/js/flot/jquery.flot.resize.min.js"></script>

<!-- ace scripts -->

<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    jQuery(function ($) {
        $('.easy-pie-chart.percentage')
            .each(
                function () {
                    var $box = $(this).closest('.infobox');
                    var barColor = $(this).data('color')
                        || (!$box.hasClass('infobox-dark') ? $box
                                .css('color')
                            : 'rgba(255,255,255,0.95)');
                    var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)'
                        : '#E2E2E2';
                    var size = parseInt($(this).data('size')) || 50;
                    $(this)
                        .easyPieChart(
                            {
                                barColor: barColor,
                                trackColor: trackColor,
                                scaleColor: false,
                                lineCap: 'butt',
                                lineWidth: parseInt(size / 10),
                                animate: /msie\s*(8|7|6)/
                                    .test(navigator.userAgent
                                        .toLowerCase()) ? false
                                    : 1000,
                                size: size
                            });
                })

        $('.sparkline').each(
            function () {
                var $box = $(this).closest('.infobox');
                var barColor = !$box.hasClass('infobox-dark') ? $box
                    .css('color') : '#FFF';
                $(this).sparkline('html', {
                    tagValuesAttribute: 'data-values',
                    type: 'bar',
                    barColor: barColor,
                    chartRangeMin: $(this).data('min') || 0
                });
            });

        var placeholder = $('#piechart-placeholder').css({
            'width': '90%',
            'min-height': '150px'
        });
        var data = [{
            label: "social networks",
            data: 38.7,
            color: "#68BC31"
        }, {
            label: "search engines",
            data: 24.5,
            color: "#2091CF"
        }, {
            label: "ad campaigns",
            data: 8.2,
            color: "#AF4E96"
        }, {
            label: "direct traffic",
            data: 18.6,
            color: "#DA5430"
        }, {
            label: "other",
            data: 10,
            color: "#FEE074"
        }]

        function drawPieChart(placeholder, data, position) {
            $.plot(placeholder, data, {
                series: {
                    pie: {
                        show: true,
                        tilt: 0.8,
                        highlight: {
                            opacity: 0.25
                        },
                        stroke: {
                            color: '#fff',
                            width: 2
                        },
                        startAngle: 2
                    }
                },
                legend: {
                    show: true,
                    position: position || "ne",
                    labelBoxBorderColor: null,
                    margin: [-30, 15]
                },
                grid: {
                    hoverable: true,
                    clickable: true
                }
            })
        }

        drawPieChart(placeholder, data);

        /**
         we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
         so that's not needed actually.
         */
        placeholder.data('chart', data);
        placeholder.data('draw', drawPieChart);

        var $tooltip = $(
            "<div class='tooltip top in'><div class='tooltip-inner'></div></div>")
            .hide().appendTo('body');
        var previousPoint = null;

        placeholder.on('plothover', function (event, pos, item) {
            if (item) {
                if (previousPoint != item.seriesIndex) {
                    previousPoint = item.seriesIndex;
                    var tip = item.series['label'] + " : "
                        + item.series['percent'] + '%';
                    $tooltip.show().children(0).text(tip);
                }
                $tooltip.css({
                    top: pos.pageY + 10,
                    left: pos.pageX + 10
                });
            } else {
                $tooltip.hide();
                previousPoint = null;
            }

        });

        var d1 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.5) {
            d1.push([i, Math.sin(i)]);
        }

        var d2 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.5) {
            d2.push([i, Math.cos(i)]);
        }

        var d3 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.2) {
            d3.push([i, Math.tan(i)]);
        }

        var sales_charts = $('#sales-charts').css({
            'width': '100%',
            'height': '220px'
        });
        $.plot("#sales-charts", [{
            label: "Domains",
            data: d1
        }, {
            label: "Hosting",
            data: d2
        }, {
            label: "Services",
            data: d3
        }], {
            hoverable: true,
            shadowSize: 0,
            series: {
                lines: {
                    show: true
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                tickLength: 0
            },
            yaxis: {
                ticks: 10,
                min: -2,
                max: 2,
                tickDecimals: 3
            },
            grid: {
                backgroundColor: {
                    colors: ["#fff", "#fff"]
                },
                borderWidth: 1,
                borderColor: '#555'
            }
        });

        $('#recent-box [data-rel="tooltip"]').tooltip({
            placement: tooltip_placement
        });

        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('.tab-content')
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            var w2 = $source.width();

            if (parseInt(off2.left) < parseInt(off1.left)
                + parseInt(w1 / 2))
                return 'right';
            return 'left';
        }

        $('.dialogs,.comments').slimScroll({
            height: '300px'
        });

        //Android's default browser somehow is confused when tapping on label which will lead to dragging the task
        //so disable dragging when clicking on label
        var agent = navigator.userAgent.toLowerCase();
        if ("ontouchstart" in document && /applewebkit/.test(agent)
            && /android/.test(agent))
            $('#tasks').on('touchstart', function (e) {
                var li = $(e.target).closest('#tasks li');
                if (li.length == 0)
                    return;
                var label = li.find('label.inline').get(0);
                if (label == e.target || $.contains(label, e.target))
                    e.stopImmediatePropagation();
            });

        $('#tasks').sortable({
            opacity: 0.8,
            revert: true,
            forceHelperSize: true,
            placeholder: 'draggable-placeholder',
            forcePlaceholderSize: true,
            tolerance: 'pointer',
            stop: function (event, ui) { //just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
                $(ui.item).css('z-index', 'auto');
            }
        });
        $('#tasks').disableSelection();
        $('#tasks input:checkbox').removeAttr('checked').on('click',
            function () {
                if (this.checked)
                    $(this).closest('li').addClass('selected');
                else
                    $(this).closest('li').removeClass('selected');
            });

    })
</script>
</body>

</html>