<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="opt" uri="/WEB-INF/taglib/option.tld" %>
<jsp:include page="/WEB-INF/page/common/ct_mall_com.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>角色权限</title>
    <!-- zTree树插件 -->
    <link rel="stylesheet" href="/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="/common/plugins/ztree/js/jquery.ztree.all-3.5.js"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="layui-row">
                        <div class="layui-col-sm3">
                            <%-- 查询搜索表单 --%>
                            <form class="layui-form layui-card-header layuiadmin-card-header-auto" id="ct_baseForm_list">
                                <span style="display: inline-block;">角色名称&nbsp;&nbsp;</span>
                                <input style="display: inline-block; width: 200px;" type="text" name="roleName" autocomplete="off" class="layui-input">
                                <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="ct_baseForm_search">
                                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                                </button>
                            </form>
                            <%-- 角色表格 --%>
                            <table class="layui-hide" id="ct_baseTable" lay-filter="ct_baseTable_filter"></table>
                        </div>

                        <div class="layui-col-sm3" style="margin-left: 50px;">
                            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                                <legend>菜单</legend>
                            </fieldset>
                            <div id="menuTreeTip">
                                <h1 style="text-align: center; margin-top: 40%; color: #0093ff;">请先选择角色</h1>
                            </div>
                            <div id="menuTreeDiv" style="display: none;">
                                <%-- 菜单权限 --%>
                                <ul id="menuTree" class="ztree"></ul>
                                <%-- 底部按钮 --%>
                                <div style="float: right; margin-top: 10%;">
                                    <button type="button" class="layui-btn layui-btn-normal" onclick="saveMenuRight();"><i class="layui-icon layui-icon-ok"></i>保存角色菜单权限</button>
                                    <button type="button" class="layui-btn layui-btn-danger" onclick="cleanMenuRight();"><i class="layui-icon layui-icon-fonts-clear"></i>清除</button>
                                </div>
                            </div>
                        </div>

                        <div class="layui-col-sm3" style="margin-left: 50px;">
                            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                                <legend>菜单功能</legend>
                            </fieldset>
                            <div id="menuAttributeTip">
                                <h1 style="text-align: center; margin-top: 40%; color: #0093ff;">请先选择菜单</h1>
                            </div>
                            <div id="menuAttributeTableDiv" style="display: none;">
                                <%-- 菜单功能表格 --%>
                                <table class="layui-hide" id="menuAttribute_table" lay-filter="menuAttribute_table_filter"></table>
                                <%-- 底部按钮 --%>
                                <div style="float: right; margin-top: 10%;">
                                    <button type="button" class="layui-btn layui-btn-normal" onclick="saveMenuAttribute();"><i class="layui-icon layui-icon-ok"></i>保存功能点</button>
                                    <button type="button" class="layui-btn layui-btn-danger" onclick="cleanMenuAttribute();"><i class="layui-icon layui-icon-fonts-clear"></i>清除功能点</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- 当前点击选中的角色id --%>
<input type="hidden" id="roleId">
<%-- 当前点击选中的菜单id --%>
<input type="hidden" id="menuId">

<script type="text/javascript">
    //---------------------------------  角色列表  -------------------------------------
    let ctTableId = "ct_baseTable";   //数据表格容器唯一id
    let ctTableFilter = "ct_baseTable_filter";    //数据表格容器事件过滤器
    let ctSearchFilter = "ct_baseForm_search";    //列表页面表单搜索按钮事件过滤器

    //加载layui相关模块并使用
    layui.use(['form', 'table', 'layer'], function() {
        let form = layui.form;
        let table = layui.table;

        //方法级渲染
        table.render({
            id: ctTableId, //容器id
            elem: '#' + ctTableId,   //容器选择器
            title: '角色信息列表',
            url: '/urms/role/role_load',
            toolbar: false,  //头部工具栏(此处是自定义工具栏模板选择器)
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['prev', 'page', 'next', 'skip', 'count'], //自定义分页布局
                groups: 1,      //只显示 1 个连续页码
                first: false,   //不显示首页
                last: false     //不显示尾页
            },
            cols: [[
                {title: "角色名称", field: "roleName", align: "center", minWidth: 180},
            ]]
        });

        //监听搜索
        form.on('submit('+ ctSearchFilter +')', function(data){
            let field = data.field; //data.field : 获取查询搜索表单数据
            //表格重载
            table.reload(ctTableId, {
                where: field
            });
            return false;
        });

        //监听行单击事件
        table.on('row(' + ctTableFilter + ')', function (obj) {
            //标注选中样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
            //隐藏选择角色提示, 显示菜单树
            $("#menuTreeTip").hide();
            $("#menuTreeDiv").show();

            //判断: 如果选择的角色改变, 则隐藏菜单功能表格, 显示选择菜单提示
            if ($("#roleId").val() != obj.data.id) {
                $("#menuAttributeTableDiv").hide();
                $("#menuAttributeTip").show();
            }

            //初始化菜单树
            $("#roleId").val(obj.data.id);
            initMenuTree();
        });
    });


    //---------------------------------  菜单树(菜单权限)  -------------------------------------
    let menuTreeObj;

    /**
     * 初始化菜单树
     */
    function initMenuTree() {
        //初始化ztree
        menuTreeObj = $.fn.zTree.init($("#menuTree"), setting);
    }

    let setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback:{
            onClick: zTreeOnClick,
            beforeAsync: zTreeBeforeAsync,
            onAsyncSuccess: zTreeOnAsyncSuccess  //异步加载成功回调
        },
        async: {//异步加载
            enable: true,
            type: "post",
            dataType: "text",
            contentType:"application/x-www-form-urlencoded",
            url: "/urms/roleRight/roleRight_menuTree",
            autoParam: ["id"]
        },
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: { "Y": "ps", "N": "ps" }
        }
    };

    function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
        expandNodes(menuTreeObj.getNodes());//异步全部打开
    }

    function expandNodes(nodes) {
        if (!nodes) return;
        for (var i=0, l=nodes.length; i<l; i++) {
            menuTreeObj.expandNode(nodes[i], true, false, false);       //展开 / 折叠 指定的节点
            if (nodes[i].isParent && nodes[i].zAsync) {             //treeNode.zAsync: 记录 treeNode 节点是否已经进行过异步加载，避免父节点反复异步加载数据。
                expandNodes(nodes[i].children);                //重复展开父节点
            }
        }
    }

    //点击树后回调
    function zTreeOnClick(event, treeId, treeNode) {
        $("#menuId").val(treeNode.id);

        //隐藏选择菜单提示, 显示菜单功能表格
        $("#menuAttributeTip").hide();
        $("#menuAttributeTableDiv").show();

        //加载菜单功能
        let menuAttributeData = new Array();

        let roleId = $("#roleId").val();
        $.ajaxSettings.async = false;
        $.post('/urms/roleRight/roleRight_loadMenuAttribute', {menuId: treeNode.id, roleId: roleId}, function (data, status) {
                if (data) {
                    menuAttributeData = data;
                }
        }, 'json');

        menuAttributeTable.reload('menuAttribute_table', {
            data: menuAttributeData
        });
    }
    
    //异步调用前触发
    function zTreeBeforeAsync(treeId, treeNode) {
        $.fn.zTree.getZTreeObj("menuTree").setting.async.url = "/urms/roleRight/roleRight_menuTree?roleId=" + $("#roleId").val();
    }


    //保存角色菜单权限
    function saveMenuRight() {
        let nodes = menuTreeObj.getCheckedNodes(true);
        if (nodes.length == 0) {
            parent.layer.msg('请选择菜单!', {icon: 0});
        } else {
            let menuIds = "";
            for (let i = 0; i < nodes.length; i++) {
                menuIds += nodes[i].id + ",";
            }
            menuIds = menuIds.substring(0, menuIds.length - 1);

            $.ajax({
                type : 'post',
                async:false,
                dataType : 'json',
                url: '/urms/roleRight/roleRight_saveOrUpdate?menuIds=' + menuIds + '&roleId=' + $("#roleId").val(),
                success : function(data){
                    if(data.result){
                        parent.layer.msg('菜单保存成功!', {icon: 1});
                    }else{
                        parent.layer.msg('菜单保存出错, 请检查!', {icon: 2});
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    parent.layer.msg('系统出错, 请检查!', {icon: 2});
                }
            });
        }
    }

    //清除角色菜单权限
    function cleanMenuRight() {
        layui.layer.confirm('确定清除角色菜单权限?', {icon: 3}, function (index) {
            layer.close(index);
            $.ajax({
                type: 'post',
                async: false,
                dataType: 'json',
                url: '/urms/roleRight/roleRight_clean?roleId=' + $("#roleId").val(),
                success: function (data) {
                    if (data.result) {
                        //取消菜单树所有勾选
                        menuTreeObj.checkAllNodes(false);
                        //清空菜单功能表格选择状态
                        $("div[lay-id='menuAttribute_table'] div.layui-form-checked").removeClass("layui-form-checked");
                        layer.msg('菜单清除成功！', {icon: 1})
                    } else {
                        layer.msg('菜单清除失败，请检查！', {icon: 2});
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.msg('系统出错, 请检查!', {icon: 2});
                }
            });
        });
    }


    //---------------------------------  菜单功能  -------------------------------------
    //菜单功能表格
    let menuAttributeTable;

    layui.use(['table'], function() {
        menuAttributeTable = layui.table;

        //方法级渲染
        menuAttributeTable.render({
            id: 'menuAttribute_table', //容器id
            elem: '#menuAttribute_table',   //容器选择器
            page: false,
            limit: 20,
            cols: [[
                {type: 'checkbox'},
                {title: "功能名称", field: "attributeName", align: "center"}
            ]],
            data: []
        });
    });


    //保存功能点
    function saveMenuAttribute() {
        let checkStatus =layui.table.checkStatus('menuAttribute_table');

        //保存菜单功能点前要确保对应的菜单是角色菜单权限中已有的
        let roleId = $("#roleId").val();
        let menuIds = "";
        $.ajaxSettings.async = false;
        $.post('/urms/roleRight/roleRight_queryRoleRightListByRoleId', {roleId: roleId}, function (data, status) {
            if (data) {
                menuIds = data.menuIds;
            }
        }, 'json');

        let menuId = $("#menuId").val();    //当前选中的菜单ID

        if (menuIds.indexOf(menuId) === -1) {
            parent.layer.msg('请先保存菜单功能对应的菜单权限!', {icon: 0});
        } else if (checkStatus.data.length == 0) {
            parent.layer.msg('请选择菜单功能!', {icon: 0});
        } else {

            let select_menuAttributeIds = "";
            for (let i = 0; i < checkStatus.data.length; i++) {
                select_menuAttributeIds += checkStatus.data[i].id + ",";
            }
            select_menuAttributeIds = select_menuAttributeIds.substring(0, select_menuAttributeIds.length - 1);

            $.ajax({
                type: 'post',
                async: false,
                dataType: 'json',
                url: '/urms/roleRight/roleRight_saveOrUpdateMenuAttribute?menuId=' + menuId + '&menuAttributeIds=' + select_menuAttributeIds + '&roleId=' + $("#roleId").val(),
                success: function (data) {
                    if (data.result) {
                        parent.layer.msg('菜单功能保存成功!', {icon: 1});
                    } else {
                        parent.layer.msg('菜单功能保存出错, 请检查!', {icon: 2});
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    parent.layer.msg('系统出错, 请检查!', {icon: 2});
                }
            });
        }
    }

    //清除功能点
    function cleanMenuAttribute() {
        let menuId = $("#menuId").val();    //当前选中的菜单ID

        layui.layer.confirm('确定清除角色菜单功能?', {icon: 3}, function (index) {
            layer.close(index);
            $.ajax({
                type: 'post',
                async: false,
                dataType: 'json',
                url: '/urms/roleRight/roleRight_cleanMenuAttribute?menuId=' + menuId + '&roleId=' + $("#roleId").val(),
                success: function (data) {
                    if (data.result) {
                        //清空表格选择状态
                        $("div[lay-id='menuAttribute_table'] div.layui-form-checked").removeClass("layui-form-checked");

                        layer.msg('菜单功能清除成功！', {icon: 1})
                    } else {
                        layer.msg('菜单功能清除失败，请检查！', {icon: 2});
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.msg('系统出错, 请检查!', {icon: 2});
                }
            });
        });
    }
</script>
</body>
</html>