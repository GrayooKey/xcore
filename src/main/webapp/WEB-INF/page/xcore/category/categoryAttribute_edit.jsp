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
    <title>数据字典属性</title>

</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <form class="layui-form" id="ct_baseForm_edit">
                    <input type="hidden" id="id" name="id" value="${categoryAttributeVo.id}"/>
                    <input type="hidden" id="pId" name="category.id" value="${categoryAttributeVo.category.id}"/>
                    <input type="hidden" id="dataSource" name="dataSource" value="1"/>

                    <div class="layui-card-header"><strong>基础信息</strong></div>
                    <div class="layui-card-body">
                        <div class="layui-row">

                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*属性键(value)</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="attrKey" name="attrKey" value="${categoryAttributeVo.attrKey}" lay-verify="required" lay-verType="tips"
                                               autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs3">
                                <div style="color: red;padding: 9px;display: none;" id="attrKey_text">该属性键已存在!</div>
                            </div>

                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*属性值(key)</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="attrValue" name="attrValue"
                                               value="${categoryAttributeVo.attrValue}" lay-verify="required" lay-verType="tips"
                                               autocomplete="off"/>
                                    </div>
                                </div>
                            </div>

                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">是否默认</label>
                                    <div class="layui-input-block">
                                        <opt:radioLayui dictKey="comm_yesNot" id="isDefault" name="isDefault" value="${categoryAttributeVo.isDefault}" />
                                    </div>
                                </div>
                            </div>

                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">排序</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="sortNum" name="sortNum"
                                               value="${categoryAttributeVo.sortNum}" lay-verify="" lay-verType="tips"
                                               autocomplete="off"/>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="layui-form-item layui-layout-admin">
                        <div class="layui-input-block">
                            <div class="layui-footer" style="left: 0;z-index: 889;">
                                <button class="layui-btn" lay-submit lay-filter="ct_baseForm_submit" id="button_submit"><i
                                        class="layui-icon layui-icon-ok"></i>提交
                                </button>
                                <button type="reset" class="layui-btn layui-btn-primary"><i
                                        class="layui-icon layui-icon-refresh"></i>重置
                                </button>
                                <button type="button" class="layui-btn layui-btn-danger" onclick="ct_close();"><i
                                        class="layui-icon layui-icon-close"></i>取消
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    var urlStr = "/urms/category/categoryAttr";

    $(document).ready(function(){
        if($("#id").val()=="" || $("#id").val()==null){
            let pId = $("#pId").val();
            $.get(urlStr+"_getSortNum?pId="+pId,function(data,status){
                $("#sortNum").val(data);
            });
        }
    });

    $("#attrKey").change(function(){
        let attrKey = $("#attrKey").val();
        isExistAttrKey(attrKey);
    });

    //加载layui相关模块并使用
    layui.use('form', function () {
        var form = layui.form;
        //监听submit提交
        form.on('submit(ct_baseForm_submit)', function (data) {
            $("#button_submit").attr("disabled","disabled");
            var categoryId = $("#pId").val();
            let attrKey = $("#attrKey").val();
            let id = $("#id").val();
            let url = urlStr+"_isExist?attrKey="+attrKey+"&categoryId="+categoryId+"&id="+id;
            $.get(url,function(data,status){
                if(data){
                    $("#button_submit").removeAttr("disabled");
                    $("#attrKey_text").show();
                }else {
                    $("#attrKey_text").hide();
                    saveOrUpdate(data);//提交表单.
                };
            });
            return false;
        });
    });


    //提交表单
    function saveOrUpdate(data){
        $.ajax({
            url: urlStr+'_saveOrUpdate',
            type: 'post',
            async: false,
            dataType: 'json',
            data: $('#ct_baseForm_edit').serialize(),
            success: function (data) {
                if (data.result) {
                    parent.layer.msg('保存成功！', {icon: 1});
                    parent.reloadTable();//刷新父级列表页面
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
    function ct_close(){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }

    function isExistAttrKey(attrKey) {
        var categoryId = $("#pId").val();
        let id = $("#id").val();
        let url = urlStr+"_isExist?attrKey="+attrKey+"&categoryId="+categoryId+"&id="+id;
        $.get(url,function(data,status){
            if(data){
                $("#attrKey_text").show();
                return true;
            }else {
                $("#attrKey_text").hide();
                return false;
            };
        });
    }
</script>
</body>
</html>