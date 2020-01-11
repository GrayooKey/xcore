<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="opt" uri="/WEB-INF/taglib/option.tld" %>
<%@ taglib prefix="menu" uri="/WEB-INF/taglib/menuDefinition.tld"%>
<jsp:include page="/WEB-INF/page/common/ct_mall_com.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>菜单列表</title>
    <!-- zTree树插件 -->
    <link rel="stylesheet" href="/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="/common/plugins/ztree/js/jquery.ztree.all-3.5.js"></script>
    <style type="text/css">
    .layui-icon {
        font-family: layui-icon, iconfont !important;
    }
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">

        <div class="layui-col-md12">
            <div class="layui-card">
                <%-- 查询搜索表单 --%>
                <form class="layui-form layui-card-header layuiadmin-card-header-auto" id="ct_baseForm_list">
                    <div class="layui-form-item">

                        <div class="layui-inline">
                            <label class="layui-form-label">菜单名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="menuName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">菜单编码</label>
                            <div class="layui-input-block">
                                <input type="text" name="menuCode" autocomplete="off" class="layui-input">
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
    var urlStr = "/urms/menu/menu";
    var ctTableId = "ct_baseTable";   //数据表格容器唯一id
    var cTreeObj;
    $(document).ready(function(){
        cTreeObj = $.fn.zTree.init($("#categoryTree"), setting);//初始化ztree
    });
    //初始化树
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback:{
            onClick: cTreeOnClick
        },
        async: {//异步加载
            enable: true,
            type: "post",
            dataType: "text",
            contentType:"application/x-www-form-urlencoded",
            url: "/urms/menu/menu_loadTree",
            autoParam: ["id"]
        }
    };

    //点击树后回调
    function cTreeOnClick(event, treeId, treeNode) {
        $("#pId").val(treeNode.id);
        $("#id").val(treeNode.id);
        var dataForm = queryParams("ct_baseForm_list");
        layui.table.reload(ctTableId, {
            where: JSON.parse(dataForm),
        });
    }


    //加载layui相关模块并使用
    layui.use([ 'table','form', 'layer'], function() {
        var form = layui.form;
        var table = layui.table;
        var layer = layui.layer;

        //方法级渲染
        table.render({
            id: ctTableId, //容器id
            elem: '#' + ctTableId,   //容器选择器
            title: '菜单列表',
            url: urlStr + '_load',
            toolbar: '#ct_toolbar_head',  //头部工具栏(此处是自定义工具栏模板选择器)
            page: true,
            method: "post",
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {title: "序号", type: 'numbers', fixed: 'left', width: 50},
                {title: "菜单名称", field: "menuName", align: "center", width: 260},
                {title: "菜单编码", field: "menuCode", align: "center"},
                {title: "链接", field: "menuUrl", align: "center"},
                {title: "排序", field: "sortNum", width: 80,align:"center"},
				{title: "状态",field: "state",align: "center", width: 120,templet: function (obj) {
                        switch (obj.state) {
                            case 1: return '<span><i class="layui-icon layui-icon-circle-dot" style="color: #46BE8A;"></i>'+ changeDataDictByKey("menu_state", obj.state) +'</span>';
                            case 2: return '<span><i class="layui-icon layui-icon-circle-dot" style="color: #c6362a;"></i>'+ changeDataDictByKey("menu_state", obj.state) +'</span>';
                            case 3: return '<span><i class="layui-icon layui-icon-circle-dot" style="color: #1A0D21;"></i>'+ changeDataDictByKey("menu_state", obj.state) +'</span>';
                            case 4: return '<span><i class="layui-icon layui-icon-circle-dot" style="color: #07B4C6;"></i>'+ changeDataDictByKey("menu_state", obj.state) +'</span>';
                            case 5: return '<span><i class="layui-icon layui-icon-circle-dot" style="color: #001FC6;"></i>'+ changeDataDictByKey("menu_state", obj.state) +'</span>';
                            case 0:  return "";
                            default:  return '<span><i class="layui-icon layui-icon-circle-dot" style="color: #FFF6DC;"></i>'+ changeDataDictByKey("menu_state", obj.state) +'</span>';
                        }
                    }
				},
                {title: "操作", fixed: 'right', align: 'center', unresize: 'true', toolbar: '#ct_baseTable_toolbar_row', width: 90}
            ]]
        });

        //监听搜索
        form.on('submit(ct_baseForm_search)', function(data){
            var field = data.field; //data.field : 获取查询搜索表单数据
            //表格重载
            table.reload(ctTableId, {
                where: field
            });
            return false;
        });

        //监听头工具栏事件
        table.on('toolbar(ct_table_filter)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var data = checkStatus.data;
            switch(obj.event){
                case 'ct_head_add': //新增
                    var pId = $("#pId").val();
                    if ((pId == "" || pId == null)) {
                        return layer.msg('请选择父菜单', {icon: 0});
                    } else {
                        layer.open({
                            type: 2
                            , scrollbar: false //禁止滚动条
                            , title: '新增菜单'
                            , content: urlStr + '_edit?pId=' + pId
                            , area: ['100%', '100%']
                            , resize: false //禁止拉伸
                            , move: false//禁止拖动
                        });
                    }
                    break;
                case 'ct_head_del':  //删除
                    if(data.length === 0){
                        return layer.msg('请选择数据', {icon: 0});
                    }
                    var ids = '';   //选中的Id
                    var isLeafs = '';
                    let isContain = false;
                    $(data).each(function (index, item) {
                        if(item.id == 0){
                            isContain = true;
                        }
                        ids += item.id + ',';
                        isLeafs += item.isLeaf + ',';
                    });
                    if (isContain) {
                        layer.msg('根目录不能删除!', {icon: 0});
                    } else {
                        layer.confirm('确定删除选中数据?',{icon:3}, function(index){
                            if(isLeafs !=null && isLeafs != '' && isLeafs.toString().indexOf('0,') > -1){
                                return layer.alert('该菜单含有子菜单,不能直接删除!', {icon: 0});
                            }else{
                                layer.close(index);
                                ct_delete(ids);
                            }
                        });
                    }
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(ct_table_filter)', function(obj){
            var data = obj.data;
            var layEvent = obj.event;

            if(layEvent === 'ct_row_detail'){
                layer.open({
                    type: 2
                    ,scrollbar: false
                    ,title: '详情'
                    ,content: urlStr+'_detail?id='+data.id
                    ,area: ['100%', '100%']
                    ,resize:false //禁止拉伸
                    ,move: false//禁止拖动
                });
            } else if(layEvent === 'ct_row_edit'){
                layer.open({
                    type: 2
                    ,scrollbar: false
                    ,title: '编辑'
                    ,content: urlStr+'_edit?id='+data.id
                    ,area: ['100%', '100%']
                    ,resize:false //禁止拉伸
                    ,move: false//禁止拖动
                });
            }
        });


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
                            if(node != null){
                                cTreeObj.removeNode(node);
                            }
                        }
                        layer.msg('删除成功', {icon: 1});
                    }else {
                        layer.msg('删除失败！', {icon: 2});
                    }
                },
                error: function(obj) {
                    layer.msg('删除失败！', {icon: 2});
                }
            });
        }
    });


    // 表格刷新
    function reloadTable() {
        layui.table.reload(ctTableId, {});
    }

    //关闭当前弹出层窗口
    function ct_close(){
        let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }

</script>
</body>
</html>