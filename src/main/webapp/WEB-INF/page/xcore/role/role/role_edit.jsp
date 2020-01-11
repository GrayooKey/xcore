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
    <title>角色信息</title>
    <script src="/common/com/js/ct_layui.js"></script>
    <script type="text/javascript">
        var currentPage = "edit";   //声明当前页面是编辑页面
    </script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <form class="layui-form" id="ct_baseForm_edit">
                    <%-- BaseModule start --%>
                    <input type="hidden" id="id" name="id" value="${roleVo.id}"/>
                    <input type="hidden" id="dataAreaCode" name="dataAreaCode" value="${roleVo.dataAreaCode}"/>
                    <input type="hidden" id="dataSource" name="dataSource" value="1"/>
                    <%-- BaseModule end --%>

                    <div class="layui-card-header"><strong>基础信息</strong><span class="layui-word-aux">（加 * 内容属于必填项）</span></div>
                    <div class="layui-card-body">
                        <div class="layui-row">
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*角色名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="roleName" name="roleName" value="${roleVo.roleName}"
                                               maxlength="50" lay-verify="required" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs12">
                                <div class="layui-col-xs6">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">*角色编码</label>
                                        <div class="layui-input-block">
                                            <input type="text" class="layui-input" id="roleCode" name="roleCode" value="${roleVo.roleCode}"
                                                   maxlength="50" lay-verify="required" lay-verType="tips" autocomplete="off"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-col-xs3">
                                    <div style="color: red;padding: 9px;display: none;" id="roleCode_text">该角色编码已存在!</div>
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">短信验证</label>
                                    <div class="layui-input-block radio_container">
                                        <opt:radioLayui dictKey="comm_yesNot" name="smsValidation" value="${roleVo.smsValidation}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%-- 底部按钮 --%>
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
    var urlStr = "/urms/role/role";

    let roleCode = '${roleVo.roleCode}';

    let id = '${roleVo.id}';
    let isUpdate = false;
    $(function () {
        if (id != null && id != "") {
            isUpdate = true;
        }
    });


    // 角色编码 值改变
    $("#roleCode").change(function(){
        let code = $("#roleCode").val();
        if (!(roleCode === code) && code != null && code != "") {    //如果 角色编码的值发生改变 且 角色编码不为空
            isExistRoleCode(code);
        }
    });

    /**
     * 检查角色编码是否唯一
     */
    function isExistRoleCode(code) {
        let url = urlStr + "_checkRoleCode?roleCode=" + code;
        let isOnly;
        $.ajaxSettings.async = false;
        $.get(url, function (data, status) {
            if (data.result) {
                $("#roleCode_text").hide();
                isOnly = true;
            } else {
                $("#roleCode_text").show();
                isOnly = false;
            }
        }, 'json');
        return isOnly;
    }

    /**
     * 保存角色信息 之前 如果角色编码不唯一, 则不能进行保存
     */
    function ct_beforeSave() {
        let code = $("#roleCode").val();
        if (roleCode == null || roleCode === "" || !(roleCode === code)) {    //如果 原来的机构编码为空 或 机构编码的值发生改变
            let b = isExistRoleCode(code);
            if (!b) {
                layui.layer.msg('请输入正确的角色编码!', {icon: 7});
            }
            return b;
        } else {
            return true;
        }
    }
</script>
</body>
</html>