<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="opt" uri="/WEB-INF/taglib/option.tld" %>
<%@ taglib prefix="menu" uri="/WEB-INF/taglib/menuDefinition.tld"%>
<jsp:include page="/WEB-INF/page/common/ct_mall_com.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>行政区划列表</title>
    <!-- zTree树插件 -->
    <link rel="stylesheet" href="/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="/common/plugins/ztree/js/jquery.ztree.all-3.5.js"></script>
    <style type="text/css">
        .layui-input-block {
            margin-left: 115px;
        }

        .layui-form-label {
            width: 85px;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">

        <div class="layui-col-md12">
            <div class="layui-card">
                <input type="hidden" id="pName">

                <%-- 查询搜索表单 --%>
                <form class="layui-form layui-card-header layuiadmin-card-header-auto" id="ct_baseForm_list">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">行政区划名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="areaName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">行政区划编码</label>
                            <div class="layui-input-block">
                                <input type="text" name="areaCode" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="ct_baseForm_search">
                                <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                            </button>
                        </div>
                    </div>
                    <input type="hidden" name="pId" id="pId">
                </form>

                <div class="layui-card-body">
                    <div class="layui-row">
                        <div class="layui-col-sm2">
                            <ul id="categoryTree" class="ztree"></ul>
                        </div>
                        <div class="layui-col-sm10">
                            <%-- 数据表格 --%>
                            <table class="layui-hide" id="ct_baseTable" lay-filter="ct_table_filter"></table>
                            <%-- 头工具栏 --%>
                            <%--<script type="text/html" id="ct_toolbar_head">
                                <div class="layui-btn-container">
                                    <button class="layui-btn layui-btn-sm" lay-event="ct_head_add" title="添加"><i class="layui-icon layui-icon-add-1"></i></button>
                                    <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="ct_head_del" title="删除"><i class="layui-icon layui-icon-delete"></i></button>
                                    <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="ct_head_creat2" title="生成省级js文件"><i class="layui-icon layui-icon-release"></i>生成省级js文件</button>
                                    <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="ct_head_creat3" title="生成市级js文件"><i class="layui-icon layui-icon-release"></i>生成市级js文件</button>
                                    <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="ct_head_creat4" title="生成区县级js文件"><i class="layui-icon layui-icon-release"></i>生成区县级js文件</button>
                                    <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="ct_head_creat5" title="生成街道镇级js文件"><i class="layui-icon layui-icon-release"></i>生成街道镇级js文件</button>
                                    <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="ct_head_creat6" title="生成村社区级js文件"><i class="layui-icon layui-icon-release"></i>生成村社区级js文件</button>
                                </div>
                            </script>--%>
                            <%-- 头工具栏 --%>
                            <script type="text/html" id="ct_toolbar_head">
                                <menu:definition menuCode="${menuCode}" attributePosition="head"/>
                            </script>
                            <%-- 行工具栏 --%>
                            <script type="text/html" id="ct_baseTable_toolbar_row">
                                <menu:definition menuCode="${menuCode}" attributePosition="row"/>
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var urlStr = "/urms/areaCode/areaCode";

    var ctTableId = "ct_baseTable";   //数据表格容器唯一id

    /********************* ztree(行政区划树) Start *****************************/
    var cTreeObj;

    $(document).ready(function () {
        cTreeObj = $.fn.zTree.init($("#categoryTree"), setting);//初始化ztree
    });

    //初始化树
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: cTreeOnClick
        },
        async: {//异步加载
            enable: true,
            type: "post",
            dataType: "text",
            contentType: "application/x-www-form-urlencoded",
            url: "/urms/areaCode/areaCode_areaTree?type=manager",
            autoParam: ["id"]
        }
    };

    //点击树后回调
    function cTreeOnClick(event, treeId, treeNode) {
        $("#pId").val(treeNode.id);
        $("#pName").val(treeNode.name);
        let dataForm = queryParams("ct_baseForm_list");
        layui.table.reload(ctTableId, {
            where: JSON.parse(dataForm)
        });
    }
    /************************** ztree End **********************************/


    //加载layui相关模块并使用
    layui.use(['table', 'form', 'layer'], function () {
        var form = layui.form;
        var table = layui.table;
        var layer = layui.layer;

        //方法级渲染
        table.render({
            id: ctTableId, //容器id
            elem: '#' + ctTableId,   //容器选择器
            title: '行政区划列表',
            url: urlStr + '_load',
            toolbar: '#ct_toolbar_head',  //头部工具栏(此处是自定义工具栏模板选择器)
            page: true,
            method: "post",
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {title: "序号", type: 'numbers', fixed: 'left', width: 50},
                {title: "行政区划名称", field: "areaName", align: "center"},
                {title: "行政区划编码", field: "areaCode", align: "center", width: 180},
                {title: "类型", field: "aoType", align: "center", width: 120, templet: function (obj) {
                        switch (obj.aoType) {
                            case 1: return '<span style="color: #009688">'+ changeDataDictByKey("area_aoType", obj.aoType) +'</span>';
                            case 2: return '<span style="color: #01AAED">'+ changeDataDictByKey("area_aoType", obj.aoType) +'</span>';
                            case 0:  return "";
                            default:  return '<span style="color: #000000">'+ changeDataDictByKey("area_aoType", obj.aoType) +'</span>';
                        }
                    }
                },
                {title: "上级目录", field: "pNames", align: "center"},
                {title: "级别", field: "level", align: "center", width: 80},
                {title: "排序", field: "sortNum", align: "center", width: 80},
                {title: "操作", fixed: 'right', align: 'center', unresize: 'true', toolbar: '#ct_baseTable_toolbar_row', width: 140}
            ]]
        });

        //监听搜索
        form.on('submit(ct_baseForm_search)', function (data) {
            var field = data.field; //data.field : 获取查询搜索表单数据
            //表格重载
            table.reload(ctTableId, {
                where: field
            });
            return false;
        });

        //监听头工具栏事件
        table.on('toolbar(ct_table_filter)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            var data = checkStatus.data;
            switch (obj.event) {
                case 'ct_head_add': //新增
                    let pId = $("#pId").val();
                    let pName = $("#pName").val();
                    if ((pId == "" || pId == null)) {
                        return layer.msg('请选择对应行政区划', {icon: 0});
                    } else {
                        layer.open({
                            type: 2
                            , scrollbar: false //禁止滚动条
                            , title: '在 ' + pName + ' 下新增'
                            , content: urlStr + '_edit?pId=' + pId
                            , area: ['100%', '100%']
                            , resize: false //禁止拉伸
                            , move: false//禁止拖动
                        });
                    }
                    break;
                case 'ct_head_del':  //删除
                    if (data.length === 0) {
                        return layer.msg('请选择数据', {icon: 0});
                    }
                    var ids = '';   //选中的Id
                    var isLeafs = '';
                    var oisLeafs = '';
                    $(data).each(function (index, item) {
                        ids += item.id + ',';
                        isLeafs += item.isLeaf + ',';
                        oisLeafs += item.oisLeaf + ',';
                    });
                    layer.confirm('确定删除选中数据?', {icon: 3}, function (index) {
                        if (isLeafs.toString().indexOf('0,') > -1) {
                            return layer.alert('要删除的目录含有子目录,不能直接删除!', {icon: 0});
                        } else if (oisLeafs.toString().indexOf('0,') > -1) {
                            return layer.alert('要删除的目录存在关联的组织机构,不能直接删除!', {icon: 0});
                        } else {
                            layer.close(index);
                            ct_delete(ids);
                        }
                    });
                    break;
                case 'ct_head_creat2': //生成省级js文件
                    createAreaCodeFile(2);
                    break;
                case 'ct_head_creat3': //生成市级js文件
                    createAreaCodeFile(3);
                    break;
                case 'ct_head_creat4': //生成区县级js文件
                    createAreaCodeFile(4);
                    break;
                case 'ct_head_creat5': //生成镇/街道级js文件
                    createAreaCodeFile(5);
                    break;
                case 'ct_head_creat6': //生成村/社区级js文件
                    createAreaCodeFile(6);
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(ct_table_filter)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;

            if (layEvent === 'ct_row_edit') {
                layer.open({
                    type: 2
                    , scrollbar: false
                    , title: '编辑'
                    , content: urlStr + '_edit?id=' + data.id
                    , area: ['100%', '100%']
                    , resize: false //禁止拉伸
                    , move: false//禁止拖动
                });
            }
        });

        function createAreaCodeFile(type) {
            layer.confirm('确定生成js文件?', {icon: 3}, function (index) {
                layer.close(index);
                var indexMsg = layer.msg('拼命生成中，请稍后...', {
                    time: 0 //不自动关闭
                    , btn: ['后台运行']
                    , yes: function (index) {
                        layer.close(index);
                    }
                });
                $.get("/urms/areaCode/refreshAreaFile?type="+type, function (data, status) {
                    layer.close(indexMsg);
                    data = JSON.parse(data);
                    if (data.result) {
                        return layer.msg('生成成功!', {icon: 1});
                    } else {
                        return layer.alert('生成失败，请重试!', {icon: 0});
                    }
                });
            });
        }
        // 删除数据
        function ct_delete(ids) {
            var url = urlStr + '_delete?ids=' + ids;
            $.ajax({
                url: url,
                type: 'post',
                async: false,
                dataType: 'json',
                success: function (data) {
                    if (data.result) {
                        reloadTable();
                        var arrayIds = ids.split(",");
                        for (var i = 0; i < arrayIds.length; i++) {
                            var node = cTreeObj.getNodeByParam("id", arrayIds[i], null);
                            if (node != null) {
                                cTreeObj.removeNode(node);
                            }
                        }
                        layer.msg('删除成功', {icon: 1});
                    } else {
                        layer.msg('删除失败！', {icon: 2});
                    }
                },
                error: function (obj) {
                    layer.msg('删除失败！', {icon: 2});
                }
            });
        }

    });

    // 表格刷新
    function reloadTable() {
        layui.table.reload(ctTableId, {});
    }
</script>
</body>
</html>