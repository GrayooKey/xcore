<%--
  说明: 该文件主要用于基础的列表、编辑、详情三种页面, 包含了三者经常引用的css和js文件。
--%>
<!DOCTYPE html>
<html>
<%-- 以下代码IE=edge告诉IE使用最新的引擎渲染网页，chrome=1则可以激活Chrome Frame --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<head>
    <%-- Jquery --%>
    <script type="text/javascript" src="/common/plugins/jquery/jquery-2.1.1.min.js" ></script>
    <%-- Layui --%>
    <link rel="stylesheet" href="/common/plugins/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/common/plugins/layuiadmin/style/admin.css" media="all">
    <script src="/common/plugins/layuiadmin/layui/layui.js"></script>
    <%-- 基础js --%>
    <script src="/common/com/js/base.js"></script>
    <script type="text/javascript" src="/common/com/js/utils.js"></script>
    <%-- 照片预览--%>
    <link rel="stylesheet" href="/common/plugins/blueimp/css/blueimp-gallery.min.css" type="text/css">
    <script src="/common/plugins/blueimp/js/jquery.blueimp-gallery.min.js"></script>

    <style>
        <%-- 列表页面下拉框样式 (使 下拉框宽度 和 普通输入框 一致) --%>
        .layui-form-select .layui-input {
            padding-right: 0px;
        }

        <%-- 编辑页面日期控件样式 --%>
        /*.layui-laydate-content>.layui-laydate-list {*/
            /*padding-bottom: 0px;*/
            /*overflow: hidden;*/
        /*}*/
        /*.layui-laydate-content>.layui-laydate-list>li{*/
            /*width:50%*/
        /*}*/
        /*.merge-box .scrollbox .merge-list {*/
            /*padding-bottom: 5px;*/
        /*}
         选择年月出现错误
        */

        <%-- 编辑页面 radio 的宽度设置 --%>
        .radio_container .layui-form-radio {
            width: 130px;
        }

        <%-- 详情页面表格样式 --%>
        .table_label {
            background-color: #f2f2f2;
            text-align: right;
        }
        <%-- 扩展图标 --%>
        .layui-icon {
            font-family: layui-icon, iconfont !important;
        }
    </style>
</head>
<body>
</body>
</html>