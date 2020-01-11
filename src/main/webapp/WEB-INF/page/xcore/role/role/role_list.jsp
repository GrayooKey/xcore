<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="opt" uri="/WEB-INF/taglib/option.tld" %>
<%@ taglib prefix="menu" uri="/WEB-INF/taglib/menuDefinition.tld"%>
<jsp:include page="/WEB-INF/page/common/ct_mall_com.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>角色信息</title>
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
                            <label class="layui-form-label">角色名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="roleName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">角色编码</label>
                            <div class="layui-input-block">
                                <input type="text" name="roleCode" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">短信验证</label>
                            <div class="layui-input-block">
                                <opt:selectLayui dictKey="comm_yesNot" name="smsValidation" isDefSelect="true"/>
                            </div>
                        </div>

                        <div class="layui-inline">
                            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="ct_baseForm_search">
                                <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <div class="layui-card-body">
                    <div class="layui-row">
                        <div class="layui-col-sm12">
                            <%-- 数据表格 --%>
                            <table class="layui-hide" id="ct_baseTable" lay-filter="ct_baseTable_filter"></table>
                            <%-- 头工具栏 --%>
                            <script type="text/html" id="ct_baseTable_toolbar_head">
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
    var urlStr = "/urms/role/role";

    <%-- 从session中获取当前登陆用户信息 --%>
    let loginUserType = '${sessionScope.user.userType}';

    function renderTable(table) {
        //方法级渲染
        table.render({
            id: ctTableId, //容器id
            elem: '#' + ctTableId,   //容器选择器
            title: '角色信息列表',
            url: urlStr + '_load',
            toolbar: ctHeadToolBar,  //头部工具栏(此处是自定义工具栏模板选择器)
            page: true,
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {title: "序号", type: 'numbers', fixed: 'left', width: 50},
                {title: "角色名称", field: "roleName", align: "center", minWidth: 180},
                {title: "角色编码", field: "roleCode", align: "center", minWidth: 150},
                {title: "短信验证", field: "smsValidation", align: "center", minWidth: 120, templet: function (obj) {
                        return changeDataDictByKey("comm_yesNot", obj.smsValidation);
                    }
                },
                {title: "子系统编码", field: "sysCode", align: "center", minWidth: 130, hide: !(loginUserType == 1)},
                {title: "操作", fixed: 'right', align: 'center', unresize: 'true', toolbar: ctRowToolBar, width: 200}
            ]]
        });
    }


    //---------------------------------列表页面数据表格相关方法-------------------------------------------------------------------

    var ctBaseForm_list = "ct_baseForm_list";   //列表页面查询搜索表单id
    var ctTableId = "ct_baseTable";   //数据表格容器唯一id
    var ctTableFilter = "ct_baseTable_filter";    //数据表格容器事件过滤器
    var ctSearchFilter = "ct_baseForm_search";    //列表页面表单搜索按钮事件过滤器

    var ctHeadToolBar = "#ct_baseTable_toolbar_head"; //头工具栏选择器
    var ctRowToolBar = "#ct_baseTable_toolbar_row";   //行工具栏选择器


    //加载layui相关模块并使用
    layui.use(['form', 'table', 'layer'], function() {
        var form = layui.form;
        var table = layui.table;
        var layer = layui.layer;


        //数据表格渲染
        renderTable(table);

        //监听搜索
        form.on('submit('+ ctSearchFilter +')', function(data){
            var field = data.field; //data.field : 获取查询搜索表单数据
            //表格重载
            table.reload(ctTableId, {
                where: field
            });
            return false;
        });

        //监听头工具栏事件
        table.on('toolbar('+ctTableFilter+')', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var data = checkStatus.data;
            switch(obj.event){
                case 'ct_head_add': //新增
                    layer.open({
                        type: 2
                        , scrollbar: false //禁止滚动条
                        , title: '新增'
                        , content: urlStr + '_edit'
                        , area: ['100%', '100%']
                        , resize: false //禁止拉伸
                        , move: false//禁止拖动
                    });
                    break;
                case 'ct_head_del':  //删除
                    if(data.length === 0){
                        return layer.msg('请选择数据', {icon: 0});
                    }
                    var ids = '';   //选中的Id
                    $(data).each(function (index, item) {
                        ids += item.id + ',';
                    });
                    layer.confirm('确定删除选中数据?',{icon:3}, function(index){
                        layer.close(index);
                        ct_delete(ids);
                    });
                    break;
                case 'ct_head_delCascade':  //删除(存在特殊表关联的情况, 如建筑表关联单元表)
                    if(data.length === 0){
                        return layer.msg('请选择数据', {icon: 0});
                    }
                    var ids = '';   //选中的Id
                    $(data).each(function (index, item) {
                        ids += item.id + ',';
                    });
                    layer.confirm('确定删除选中数据?',{icon:3}, function(index){
                        layer.close(index);
                        ct_deleteCascade(ids);
                    });
                    break;
            };
        });

        //监听行工具事件
        table.on('tool('+ctTableFilter+')', function(obj){
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
            } else if (layEvent === 'ct_row_del') {
                layer.confirm('确定删除该行数据?', {icon: 3}, function (index) {
                    layer.close(index);
                    ct_delete(data.id);
                });
            } else if (layEvent === 'ct_row_relationUser') {
                layer.open({
                    type: 2
                    ,scrollbar: false
                    ,title: '关联用户'
                    ,content: urlStr+'_relationUser?id='+data.id
                    ,area: ['100%', '100%']
                    ,offset: '0px'
                    ,btn: ['确定', '取消']
                    ,yes: function (index, layero) {
                        let iframeWindow = window['layui-layer-iframe' + index];
                        //获取关联用户
                        let userData = iframeWindow.transfer.getData('transfer');
                        let select_userIds = "";
                        for(let i in userData){
                            if (i < userData.length - 1) {
                                select_userIds += userData[i].value + ",";
                            } else {
                                select_userIds += userData[i].value;
                            }
                        }
                        //保存或更新角色与用户的关联
                        $.ajax({
                            url: "/urms/role/role_saveOrUpdateRelation?roleId="+data.id +"&userIds="+select_userIds,
                            type: 'post',
                            async: false,
                            dataType: 'json',
                            success: function (data) {
                                if (data.result) {
                                    reloadTable();
                                    layer.msg('保存成功', {icon: 1});

                                    layer.close(index);
                                }else {
                                    layer.msg('保存失败！', {icon: 2});
                                }
                            },
                            error: function(obj) {
                                layer.msg('保存失败！', {icon: 2});
                            }
                        });
                    }
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
</script>
</body>
</html>