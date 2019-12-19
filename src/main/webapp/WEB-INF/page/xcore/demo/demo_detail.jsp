<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fns" uri="/WEB-INF/taglib/dict.tld" %>
<%@ taglib prefix="opt" uri="/WEB-INF/taglib/option.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/page/common/ct_mall_com.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>示例</title>
    <script>
        var currentPage = "detail";   //声明当前页面是详情页面	
    </script>
    <script src="/common/com/js/ct_layui.js"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">

        <%-- 表格数据 Start --%>
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header"><strong>基本信息</strong></div>
                <div class="layui-card-body">
                    <table class="layui-table">
                        <colgroup>
                            <%-- 为表格中的第一个列设置了宽度 --%>
                            <col width="150"><col>
                        </colgroup>
                        <tbody>
                            <tr>
                                <td class="table_label">名称</td>
                                <td>${demoVo.name}</td>
                            </tr>
                            <tr>
                                <td class="table_label">价值</td>
                                <td>${demoVo.money}</td>
                            </tr>
                            <tr>
                                <td class="table_label">排序</td>
                                <td>${demoVo.number}</td>
                            </tr>
                            <tr>
                                <td class="table_label">使用时间</td>
                                <td><fmt:formatDate value="${demoVo.time}" pattern="yyyy-MM-dd"/></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%-- 表格数据 End --%>

    </div>
</div>

<%-- 底部关闭按钮 --%>
<div class="layui-form-item layui-layout-admin">
    <div class="layui-input-block">
        <div class="layui-footer" style="left: 0;z-index: 899;">
            <button type="button" class="layui-btn layui-btn-danger" onclick="ct_close();"><i class="layui-icon layui-icon-close"></i>关 闭</button>
        </div>
    </div>
</div>
</body>
</html>