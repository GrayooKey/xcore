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
    <title>用户详情</title>
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
                                <td class="table_label">所属子系统</td>
                                <td>${subsystemName}</td>
                            </tr>
                            <tr>
                                <td class="table_label">用户名</td>
                                <td>${userVo.loginName}</td>
                            </tr>
                            <tr>
                                <td class="table_label">网咯昵称</td>
                                <td>${userVo.nickName}</td>
                            </tr>
                            <tr>
                                <td class="table_label">真实姓名</td>
                                <td>${userVo.userName}</td>
                            </tr>
                            <tr>
                                <td class="table_label">头像</td>
                                <td>
                                    <c:forEach items="${attachList}" var="attach">
                                        <a style='cursor:pointer' data-gallery='' title='${attach.attachName}' href='${attach.uploadPath}'>
                                            <img style="width: 95px; height: 95px;" src="${attach.uploadPath}" alt="${attach.attachName}">
                                        </a>
                                    </c:forEach>
                                </td>
                                <%-- 照片预览 --%>
                                <div id='blueimp-gallery' class='blueimp-gallery blueimp-gallery-controls'>
                                    <div class='slides'></div>
                                    <h3 class='title'></h3>
                                    <a class='prev'>‹</a><a class='next'>›</a><a class='close'>×</a><a class='play-pause'></a>
                                    <ol class='indicator'></ol>
                                </div>
                            </tr>
                            <tr>
                                <td class="table_label">身份证号</td>
                                <td>${userVo.idCard}</td>
                            </tr>
                            <tr>
                                <td class="table_label">手机号码</td>
                                <td>${userVo.mobile}</td>
                            </tr>
                            <tr>
                                <td class="table_label">固定电话</td>
                                <td>${userVo.officePhone}</td>
                            </tr>
                            <tr>
                                <td class="table_label">生日</td>
                                <td><fmt:formatDate value="${userVo.birthday}" pattern="yyyy-MM-dd"/></td>
                            </tr>
                            <tr>
                                <td class="table_label">性别</td>
                                <td>${fns:getDictValue("population_sex", userVo.sex)}</td>
                            </tr>
                            <tr>
                                <td class="table_label">民族</td>
                                <td>${fns:getDictValue("population_nation", userVo.nation)}</td>
                            </tr>
                            <tr>
                                <td class="table_label">文化程度/学历</td>
                                <td>${fns:getDictValue("population_degree", userVo.education)}</td>
                            </tr>
                            <tr>
                                <td class="table_label">电子邮箱</td>
                                <td>${userVo.email}</td>
                            </tr>
                            <tr>
                                <td class="table_label">QQ</td>
                                <td>${userVo.qq}</td>
                            </tr>
                            <tr>
                                <td class="table_label">户籍地</td>
                                <td>${fns:getAreaValue(userVo.bornProvince)}${fns:getAreaValue(userVo.bornCity)}${fns:getAreaValue(userVo.bornDistrict)}${fns:getAreaValue(userVo.bornStreet)}${fns:getAreaValue(userVo.bornVillage)}</td>
                            </tr>
                            <tr>
                                <td class="table_label">户籍详址</td>
                                <td>${userVo.bornAddress}</td>
                            </tr>
                            <tr>
                                <td class="table_label">居住地</td>
                                <td>${fns:getAreaValue(userVo.liveProvince)}${fns:getAreaValue(userVo.liveCity)}${fns:getAreaValue(userVo.liveDistrict)}
                                    ${fns:getAreaValue(userVo.liveStreet)}${fns:getAreaValue(userVo.liveVillage)}</td>
                            </tr>
                            <tr>
                                <td class="table_label">居住详址</td>
                                <td>${userVo.liveAddress}</td>
                            </tr>
                            <tr>
                                <td class="table_label">用户类型</td>
                                <td>${fns:getDictValue("user_type", userVo.userType)}</td>
                            </tr>
                            <tr>
                                <td class="table_label">状态</td>
                                <td>${fns:getDictValue("user_state", userVo.state)}</td>
                            </tr>
                            <tr>
                                <td class="table_label">备注</td>
                                <td>${userVo.memo}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <c:if test="${userVo.userType eq 6}">
                    <div class="layui-card-header"><strong>扩展信息</strong></div>
                    <div class="layui-card-body">
                        <table class="layui-table">
                            <colgroup>
                                <%-- 为表格中的第一个列设置了宽度 --%>
                                <col width="150"><col>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td class="table_label">已登录次数</td>
                                    <td>${userExtendVo.loginNum}</td>
                                </tr>
                                <tr>
                                    <td class="table_label">最多登录次数</td>
                                    <td>${userExtendVo.maxLoginNum}</td>
                                </tr>
                                <tr>
                                    <td class="table_label">限制登录IP</td>
                                    <td>${userExtendVo.ipAddress}</td>
                                </tr>
                                <tr>
                                    <td class="table_label">登录有限期限</td>
                                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${userExtendVo.validityDate}" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </div>
        <%-- 表格数据 End --%>

    </div>
</div>

<%-- 底部关闭按钮 --%>
<div class="layui-form-item layui-layout-admin">
    <div class="layui-input-block">
        <div class="layui-footer" style="left: 0;z-index: 889;">
            <button type="button" class="layui-btn layui-btn-danger" onclick="ct_close();"><i class="layui-icon layui-icon-close"></i>关 闭</button>
        </div>
    </div>
</div>
</body>
</html>