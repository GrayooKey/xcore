<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="opt" uri="/WEB-INF/taglib/option.tld" %>
<%@ taglib prefix="menu" uri="/WEB-INF/taglib/menuDefinition.tld"%>
<jsp:include page="/WEB-INF/page/common/ct_mall_com.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户列表</title>
    <script type="text/javascript">
        var currentPage = "list";   //声明当前页面是列表页面
    </script>
    <!-- zTree树插件 -->
    <link rel="stylesheet" href="/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="/common/plugins/ztree/js/jquery.ztree.all-3.5.js"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <%-- 数据统计 --%>
        <%--<div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">统计区域</div>
                <div class="layui-card-body">
                    <div class="layadmin-backlog" lay-anim="" lay-indicator="inside" lay-arrow="none" id="ct_baseCountDiv" style="width: 100%;height: 90px">
                        <ul id="ct_baseCount" class="layui-row layui-col-space10 layui-this">
                        </ul>
                    </div>
                </div>
            </div>
        </div>--%>

        <div class="layui-col-md12">
            <div class="layui-card">
                <%-- 查询搜索表单 --%>
                <form class="layui-form layui-card-header layuiadmin-card-header-auto" id="ct_baseForm_list">
                    <c:if test="${userType eq 3}">
                        <%-- 数据区域编码 --%>
                        <input type="hidden" id="dataAreaCode" name="dataAreaCode" />
                    </c:if>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-block">
                                <input type="text" name="loginName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">真实姓名</label>
                            <div class="layui-input-block">
                                <input type="text" name="userName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">身份证号</label>
                            <div class="layui-input-block">
                                <input type="text" name="idCard" autocomplete="off" class="layui-input">
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
                        <c:if test="${userType eq 3}">
                            <%-- ztree树 容器 --%>
                            <div class="layui-col-sm2">
                                <ul id="tree" class="ztree"></ul>
                            </div>
                        </c:if>


                        <div class="${userType eq 3 ? 'layui-col-sm10' : 'layui-col-sm12'}">
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
    var urlStr = "/urms/user/user";

    let userType = '${userType}';

    <%-- 从session中获取当前登陆用户信息 --%>
    let loginUserType = '${sessionScope.user.userType}';

    function renderTable(table) {
        //方法级渲染
        table.render({
            id: ctTableId, //容器id
            elem: '#' + ctTableId,   //容器选择器
            title: '用户列表',
            url: urlStr + '_load?userType='+userType,
            toolbar: ctHeadToolBar,  //头部工具栏(此处是自定义工具栏模板选择器)
            page: true,
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {title: "序号", type: 'numbers', fixed: 'left', width: 50},
                {title: "用户名", fixed: 'left', field: "loginName", align: "center", minWidth: 130},
                {title: "真实姓名", field: "userName", align: "center", minWidth: 130},
                {title: "手机号码", field: "mobile", align: "center", minWidth: 130},
                {title: "子系统编码", field: "sysCode", align: "center", minWidth: 130, hide: !(loginUserType == 1)},
                {title: "状态", fixed: 'right', field: "state", align: "center", width: 80, templet: function (obj) {
                        switch (obj.state) {
                            case 1: return '<span class="layui-badge layui-bg-blue">'+ changeDataDictByKey("user_state", obj.state) +'</span>';
                            case 2: return '<span class="layui-badge">'+ changeDataDictByKey("user_state", obj.state) +'</span>';
                            case 3: return '<span class="layui-badge layui-bg-black">'+ changeDataDictByKey("user_state", obj.state) +'</span>';
                            default:  return '<span class="layui-badge layui-bg-gray">'+ changeDataDictByKey("user_state", obj.state) +'</span>';
                        }
                    }
                },
                {title: "操作", fixed: 'right', align: 'center', unresize: 'true', toolbar: ctRowToolBar, width: 240}
            ]]
        });
    }


    function ct_toolbar_extra(data, event) {
        if (event === "ct_head_add_user") {
            if($("#tree").length > 0){
                var dataAreaCode = $("#dataAreaCode").val();
                if ((dataAreaCode == "" || dataAreaCode == null) && !($("#tree").hasClass("ct_nomustchoose"))) {
                    return layer.msg('请选择对应行政区域', {icon: 0});
                } else {
                    layer.open({
                        type: 2
                        , scrollbar: false //禁止滚动条
                        , title: '新增'
                        , content: urlStr + '_edit?userType='+userType +'&dataAreaCode=' + dataAreaCode
                        , area: ['100%', '100%']
                        , resize: false //禁止拉伸
                        , move: false//禁止拖动
                    });
                }
            }else{
                layer.open({
                    type: 2
                    , scrollbar: false //禁止滚动条
                    , title: '新增'
                    , content: urlStr+'_edit?userType='+userType
                    , area: ['100%', '100%']
                    , resize: false //禁止拉伸
                    , move: false//禁止拖动
                });
            }
            return true;
        }
    }


    function ct_tool_extra(data, layEvent){
        if(layEvent === 'ct_row_resetPwd'){  //重置密码
            layer.confirm('确定重置密码?',{icon:3}, function(index){
                layer.close(index);

                var url = urlStr + '_resetPwd?id=' + data.id;
                $.ajax({
                    url: url,
                    type: 'post',
                    async: false,
                    dataType: 'json',
                    success: function (data) {
                        if (data.result) {
                            layer.msg('密码重置成功', {icon: 1});
                        }else {
                            layer.msg('密码重置失败！', {icon: 2});
                        }
                    },
                    error: function(obj) {
                        layer.msg('密码重置失败！', {icon: 2});
                    }
                });
            });
            return true;
        } else if (layEvent === 'ct_row_relationRole') {
            layer.open({
                type: 2
                ,scrollbar: false
                ,title: '关联角色'
                ,content: urlStr+'_relationRole?id='+data.id
                ,area: ['100%', '100%']
                ,offset: '0px'
                ,btn: ['确定', '取消']
                ,yes: function (index, layero) {
                    let iframeWindow = window['layui-layer-iframe' + index];
                    //获取关联角色
                    let roleData = iframeWindow.transfer.getData('transfer');
                    let select_roleIds = "";
                    for(let i in roleData){
                        if (i < roleData.length - 1) {
                            select_roleIds += roleData[i].value + ",";
                        } else {
                            select_roleIds += roleData[i].value;
                        }
                    }
                    //保存或更新用户与角色的关联
                    $.ajax({
                        url: "/urms/user/user_saveOrUpdateRelation?userId="+data.id +"&roleIds="+select_roleIds,
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
            return true;
        }
    }
</script>
<script src="/common/com/js/ct_layui.js"></script>
</body>
</html>