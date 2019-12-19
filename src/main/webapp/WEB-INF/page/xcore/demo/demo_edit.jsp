<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="opt" uri="/WEB-INF/taglib/option.tld" %>
<%@ taglib prefix="fns" uri="/WEB-INF/taglib/dict.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/page/common/ct_mall_com.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>示例</title>
    <script type="text/javascript">
        var currentPage = "edit";   //声明当前页面是编辑页面
    </script>
    <script src="/common/com/js/ct_layui.js"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <form class="layui-form" id="ct_baseForm_edit">
                    <%-- BaseModule start --%>
                    <input type="hidden" id="id" name="id" value="${demoVo.id}"/>
                    <input type="hidden" id="dataSource" name="dataSource" value="1"/>
                    <%-- BaseModule end --%>

                    <div class="layui-card-header"><strong>基础信息</strong><span style="color: #A5A5A5">（加 * 内容属于必填项）</span></div>
                    <div class="layui-card-body">
                        <div class="layui-row">
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">* 名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="name" name="name" value="${demoVo.name}" lay-verify="required" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">价值</label>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input" id="money" name="money" value="${demoVo.money}" lay-verify="" lay-verType="tips" autocomplete="off"/>
                                        <span style="position: absolute;top:8px;right: 8px;">元</span>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">排序</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="number" name="number" value="${demoVo.number}" lay-verify="" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">时间</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="time" name="time" value="<fmt:formatDate value='${demoVo.time}' pattern="yyyy-MM-dd"/>" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <%--<div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">下拉选择</label>
                                    <div class="layui-input-block">
                                        <opt:radioLayui dictKey="isNot" name="number" value="${demoVo.number}"/>
                                    </div>
                                </div>
                            </div>--%>
                        </div>
                    </div>

                    <%-- 底部按钮 --%>
                    <div class="layui-form-item layui-layout-admin">
                        <div class="layui-input-block">
                            <div class="layui-footer" style="left: 0;z-index: 899;">
                                <button class="layui-btn" lay-submit lay-filter="ct_baseForm_submit"><i class="layui-icon layui-icon-ok"></i>提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary"><i class="layui-icon layui-icon-refresh"></i>重置</button>
                                <button type="button" class="layui-btn layui-btn-danger" onclick="ct_close();"><i class="layui-icon layui-icon-close"></i>取消</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    var urlStr = "/xcore/demo/demo";

    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#time',
            trigger: 'click'
        });
    });
</script>
</body>
</html>