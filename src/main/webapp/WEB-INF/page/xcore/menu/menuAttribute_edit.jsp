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
    <title>菜单功能</title>
    <script src="/common/com/js/ct_layui.js"></script>
    <script type="text/javascript">
        var currentPage = "edit";   //声明当前页面是编辑页面
    </script>
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
                <form class="layui-form" id="ct_baseForm_edit">
                    <%-- BaseModule start --%>
                    <input type="hidden" id="id" name="id" value="${menuAttributeVo.id}" />
                    <input type="hidden" id="menuId" name="menu.id" value="${menuAttributeVo.menu.id}" />

                    <div class="layui-card-header"><strong>基础信息</strong><span class="layui-word-aux">（加 * 内容属于必填项）</span></div>
                    <div class="layui-card-body">
                        <div class="layui-row">

                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*功能名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="attributeName" name="attributeName" value="${menuAttributeVo.attributeName}" autocomplete="off" class="layui-input" lay-verify="required" lay-verType="tips">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*功能编码</label>
                                    <div class="layui-input-block">
                                        <select name="attributeCode" layVerify="required" layVerType="tips" lay-search>
                                            <option value="">---请选择---</option>
                                            <c:forEach items="${menuAttributeCodes}" var="ca">
                                                <option value="${ca.attrKey}" id="${ca.attrKey}" <c:if test="${menuAttributeVo.attributeCode eq ca.attrKey}"> selected</c:if>>${ca.attrValue}</option>
                                            </c:forEach>

                                        </select>
                                        <%--<opt:selectLayui dictKey="menu_attributeCode" name="attributeCode" value="${menuAttributeVo.attributeCode}" isDefSelect="true"  layVerify="required" layVerType="tips"/>--%>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs3">
                                <div style="color: #999;padding: 9px;">
                                    <span><i class="layui-icon alone-tips"  lay-tips="对应的事件编码(lay-event)">&#xe702;</i></span>
                                    <span style="color: red;display: none;" id="menuCode_text">该编码已存在!</span>
                                </div>
                            </div>

                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">自定义方法</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="attributeMethod" id="attributeMethod" value="${menuAttributeVo.attributeMethod}" autocomplete="off" class="layui-input" placeholder="请输入完整方法名,例如test(),多个请用;隔开">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs3">
                                <div style="color: #999;padding: 9px;">
                                    <span><i class="layui-icon alone-tips"  lay-tips="用于调用自定义的click方法">&#xe702;</i></span>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">图标</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="menu_attributeIcon" name="icon" value="${menuAttributeVo.icon}" isDefSelect="true" />
                                    </div>
                                </div>
                            </div>

                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">图标显示方式</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="menu_buttonShowMethod" name="showMethod" value="${menuAttributeVo.showMethod}" />
                                    </div>
                                </div>
                            </div>

                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">操作底色</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="menu_buttonColor" name="color" value="${menuAttributeVo.color}" />
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">页面类型</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="menu_pageType" name="pageType" id="pageType" layFilter="pageType" value="${menuAttributeVo.pageType}" />
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2 attributePosition" style="display: none;">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">功能位置</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="menu_attributePosition" name="position" id="position" value="${menuAttributeVo.position}" isDefSelect="true" layVerify="" layVerType="tips"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">排序</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" name="sortNum" id="sortNum" value="${menuAttributeVo.sortNum}">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">状态</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="menu_state" isDefSelect="false" id="state" name="state" value="${menuAttributeVo.state }"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">备注</label>
                                    <div class="layui-input-block">
                                        <textarea name="memo" class="layui-textarea">${menuAttributeVo.memo}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item layui-layout-admin">
                        <div class="layui-input-block">
                            <div class="layui-footer" style="left: 0;z-index: 889;">
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
    var urlStr = "/urms/menu/menuAttr";

    layui.use('form', function(){
        let id = $("#id").val();
        let form = layui.form;

        form.on('select(pageType)', function(data){
            let pageType = data.value;
            changePageType(pageType);
        });
        //功能编码检查
        let menuId = $("#menuId").val();
        let attrCode = '${menuAttributeVo.attributeCode}';
        $.get(urlStr + "_getAttribute?menuId=" + $("#menuId").val(), function (data, status) {
            if(data != null && data.length>0){
                for(let i=0; i<data.length; i++){
                    if(data[i] != attrCode){
                        $("#"+data[i]).attr("disabled","disabled");
                    }
                }
                form.render('select');
            }
        });
        if (id === null || id === "") {
            $.get(urlStr + "_getSortNum?menuId=" + $("#menuId").val(), function (data, status) {
                $("#sortNum").val(data);
            });
        }
        //页面类型切换
        let pageType = $("#pageType").val();
        changePageType(pageType);
    });

    function changePageType(pageType) {
        if(pageType == 1){
            $(".attributePosition").show();
            $("#position").attr("lay-verify","required");
        }else {
            $(".attributePosition").hide();
            $("#position").attr("lay-verify","");
        }
    }



    //Tips
    $('*[lay-tips]').on('mouseenter', function(){
        let content = $(this).attr('lay-tips');
        this.index = layer.tips('<div style="padding: 10px; font-size: 14px; color: #eee;">'+ content + '</div>', this, {
            time: -1
            ,maxWidth: 280
            ,tips: [3, '#3A3D49']
            });
        }).on('mouseleave', function(){
        layer.close(this.index);
    });
</script>
</body>
</html>