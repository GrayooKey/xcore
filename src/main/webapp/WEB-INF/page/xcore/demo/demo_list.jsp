<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="opt" uri="/WEB-INF/taglib/option.tld" %>
<jsp:include page="/WEB-INF/page/common/ct_mall_com.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>示例</title>
    <script type="text/javascript">
        var currentPage = "list";   //声明当前页面是列表页面	
    </script>
    <script src="/common/com/js/ct_layui.js"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <%-- 数据统计 --%>
        <%--<div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">统计区域</div>
                <div class="layui-card-body">
                    <div class="layadmin-backlog" lay-anim="" lay-indicator="inside" lay-arrow="none"
                         id="ct_baseCountDiv" style="width: 100%;height: 90px">
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
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="name" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <%--<div class="layui-inline">
                            <label class="layui-form-label">类型</label>
                            <div class="layui-input-block">
                                <opt:selectLayui dictKey="isNot" name="number" isDefSelect="true"/>
                            </div>
                        </div>--%>

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
                                <div class="layui-btn-container">
                                    <button class="layui-btn layui-btn-sm" lay-event="ct_head_add" title="添加"><i
                                            class="layui-icon layui-icon-add-1"></i></button>
                                    <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="ct_head_del"
                                            title="删除"><i class="layui-icon layui-icon-delete"></i></button>
                                </div>
                            </script>
                            <%-- 行工具栏 --%>
                            <script type="text/html" id="ct_baseTable_toolbar_row">
                                <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="ct_row_detail" title="详情"><i
                                        class="layui-icon layui-icon-search"></i></a>
                                <a class="layui-btn layui-btn-xs" lay-event="ct_row_edit" title="编辑"><i
                                        class="layui-icon layui-icon-edit"></i></a>
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var urlStr = "/xcore/demo/demo";

    function renderTable(table) {
        //方法级渲染
        table.render({
            id: ctTableId, //容器id
            elem: '#' + ctTableId,   //容器选择器
            title: '示例列表',
            url: urlStr + '_load',
            toolbar: ctHeadToolBar,  //头部工具栏(此处是自定义工具栏模板选择器)
            page: true,
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {title: "序号", type: 'numbers', fixed: 'left', width: 50},
                {title: "名称", fixed: 'left', field: "name", align: "center", width: 260},
                {title: "价值", field: "money", align: "center"},
                {title: "时间", field: "time", align: "center", templet: function (obj) {
                        return obj.demoUseTime != null ? layui.util.toDateString(obj.demoUseTime.time, "yyyy-MM-dd") : "";
                    }
                 },
                {title: "类型", fixed: 'right', field: "number", align: "center", width: 120, templet: function (obj) {
                        //return changeDataDictByKey("isNot", obj.demoIsOpen);
                        return obj.number;
                    }
                },
                {title: "操作", fixed: 'right', align: 'center', unresize: 'true', toolbar: ctRowToolBar, width: 140}
            ]]
        });
    }
</script>
</body>
</html>