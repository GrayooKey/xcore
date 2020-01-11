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
    <title>行政区划新增编辑页面</title>
    <style type="text/css">
        .layui-input-block {
            margin-left: 120px;
        }

        .layui-form-label {
            width: 90px;
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
                    <input type="hidden" id="id" name="id" value="${areaCodeVo.id}"/>
                    <input type="hidden" id="dataSource" name="dataSource" value="1"/>
                    <%-- BaseModule end --%>

                    <input type="hidden" id="pId" name="pId" value="${areaCodeVo.pId}"/>
                    <input type="hidden" id="pAreaCode" name="pAreaCode" value="${areaCodeVo.pAreaCode}"/>
                    <input type="hidden" id="pIds" name="pIds" value="${areaCodeVo.pIds}"/>
                    <input type="hidden" id="pNames" name="pNames" value="${areaCodeVo.pNames}"/>
                    <input type="hidden" id="isLeaf" name="isLeaf" value="${areaCodeVo.isLeaf}"/>
                    <input type="hidden" id="level" name="level" value="${areaCodeVo.level}"/>

                    <input type="hidden" id="opId" name="opId" value="${areaCodeVo.opId}"/>
                    <input type="hidden" id="oisLeaf" name="oisLeaf" value="${areaCodeVo.oisLeaf}"/>
                    <input type="hidden" id="ohasNextOrg" name="ohasNextOrg" value="${areaCodeVo.ohasNextOrg}"/>

                    <div class="layui-card-header"><strong>基础信息</strong><span class="layui-word-aux">（加 * 内容属于必填项）</span></div>
                    <div class="layui-card-body">
                        <div class="layui-row">
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*行政区划名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="areaName" name="areaName" value="${areaCodeVo.areaName}"
                                               maxlength="100" lay-verify="required" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*行政区划编码</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="areaCode" name="areaCode" value="${areaCodeVo.areaCode}"
                                               maxlength="100" lay-verify="required" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs3">
                                <div style="color: red;padding: 9px;display: none;" id="areaCode_text">该编码已存在!</div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">类型</label>
                                    <div class="layui-input-block">
                                        <select id="aoType" name="aoType" lay-verify="required">
                                            <option value="1" <c:if test="${areaCodeVo.aoType eq 1}">selected</c:if>>行政区划</option>
                                            <option value="2" <c:if test="${areaCodeVo.aoType eq 2}">selected</c:if>>网格</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">状态</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="comm_state" id="state" name="state" value="${areaCodeVo.state != null ? areaCodeVo.state : 1}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">排序</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="sortNum" name="sortNum" value="${areaCodeVo.sortNum}"
                                               maxlength="10" lay-verify="positiveint" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item layui-layout-admin">
                        <div class="layui-input-block">
                            <div class="layui-footer" style="left: 0;z-index: 889;">
                                <button class="layui-btn" lay-submit lay-filter="ct_baseForm_submit" id="button_submit"><i class="layui-icon layui-icon-ok"></i>提交</button>
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
    var urlStr = "/urms/areaCode/areaCode";

    let areaCode = $("#areaCode").val();

    let id = $("#id").val();
    let isUpdate = false;

    $(function () {
        if (id != null && id != "") {
            isUpdate = true;
        } else {
            let pId = $("#pId").val();
            $.get(urlStr + "_getSortNum?pId=" + pId, function (data, status) {
                $("#sortNum").val(data);
            });
        }
    });

    // 行政区划编码 值改变
    $("#areaCode").change(function () {
        let code = $("#areaCode").val();
        if (code != null && code != "" && !(areaCode === code)) {
            isExistAreaCode(code);
        }
    });

    /**
     * 检查行政区划编码是否唯一
     */
    function isExistAreaCode(code) {
        let url = urlStr + "_isExist?code=" + code;
        let isOnly;
        $.ajaxSettings.async = false;
        $.get(url, function (data, status) {
            if (data.result) {
                $("#areaCode_text").hide();
                isOnly = true;
            } else {
                $("#areaCode_text").show();
                isOnly = false;
            }
        }, 'json');
        return isOnly;
    }


    //加载layui相关模块并使用
    layui.use('form', function () {
        var form = layui.form;

        //监听submit提交
        form.on('submit(ct_baseForm_submit)', function (data) {
            let code = $("#areaCode").val();
            if (areaCode == null || areaCode === "" || !(areaCode === code)) {
                let b = isExistAreaCode(code);
                if (!b) {
                    layui.layer.msg('输入的行政区划编码已存在, 请检查!', {icon: 7});
                    return false;
                } else {
                    saveOrUpdate(data);//提交表单.
                }
            } else {
                saveOrUpdate(data);//提交表单.
            }
        });
    });

    //提交表单
    function saveOrUpdate(data) {
        $.ajax({
            url: urlStr + '_saveOrUpdate',
            type: 'post',
            async: false,
            dataType: 'json',
            data: $('#ct_baseForm_edit').serialize(),
            success: function (data) {
                if (data.result) {
                    parent.layer.msg('保存成功！', {icon: 1});
                    parent.reloadTable();//刷新父级列表页面
                    //加载树
                    var pNode = parent.cTreeObj.getNodeByParam("id", $("#pId").val(), null);//父节点
                    if (isUpdate) {//更新
                        let node = parent.cTreeObj.getNodeByParam("id", data.id, null);
                        if (node != null) {
                            node.name = $("#areaName").val();
                            parent.cTreeObj.updateNode(node);
                        }
                    } else {
                        parent.cTreeObj.addNodes(pNode, {id: "" + data.id + "", name: "" + $("#areaName").val() + ""});//新增
                    }
                    ct_close();
                } else {
                    $("#button_submit").removeAttr("disabled");
                    parent.layer.msg('保存出错！', {icon: 2});
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $("#button_submit").removeAttr("disabled");
                parent.layer.msg('保存出错！', {icon: 2});
            }
        });
    }


    //关闭当前弹出层窗口
    function ct_close() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
</script>
</body>
</html>