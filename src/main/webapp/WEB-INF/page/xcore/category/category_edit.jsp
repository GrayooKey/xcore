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
    <title>数据字典新增编辑页面</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <form class="layui-form" id="ct_baseForm_edit">
                    <%-- BaseModule start --%>
                    <input type="hidden" id="id" name="id" value="${categoryVo.id}"/>
                    <input type="hidden" id="pId" name="pId" value="${categoryVo.pId}"/>
                    <input type="hidden" id="pIds" name="pIds" value="${categoryVo.pIds}"/>
                    <input type="hidden" id="pNames" name="pNames" value="${categoryVo.pNames}"/>
                    <input type="hidden" id="isLeaf" name="isLeaf" value="${categoryVo.isLeaf}"/>
                    <input type="hidden" id="dataSource" name="dataSource" value="1"/>
                    <%-- BaseModule end --%>

                    <div class="layui-card-header"><strong>基础信息</strong><span class="layui-word-aux">（加 * 内容属于必填项）</span></div>
                    <div class="layui-card-body">
                        <div class="layui-row">

                            <div class="layui-col-xs5 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*字典名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="categoryName" name="categoryName" value="${categoryVo.categoryName}" lay-verify="required" lay-verType="tips"
                                               autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs5 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*字典编码</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="categoryCode" name="categoryCode"
                                               value="${categoryVo.categoryCode}" lay-verify="required" lay-verType="tips"
                                               autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs3">
                                <div style="color: red;padding: 9px;display: none;" id="categoryCode_text">该编码已存在!</div>
                            </div>

                            <div class="layui-col-xs5 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*字典类型</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="dict_categoryType" id="categoryType" name="categoryType" value="${categoryVo.categoryType}" />
                                    </div>
                                </div>
                            </div>

                            <div class="layui-col-xs5 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">排序</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="sortNum" name="sortNum"
                                               value="${categoryVo.sortNum}" lay-verify="" lay-verType="tips"
                                               autocomplete="off"/>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="layui-form-item layui-layout-admin">
                        <div class="layui-input-block">
                            <div class="layui-footer" style="left: 0;z-index: 889;">
                                <button class="layui-btn" lay-submit lay-filter="ct_baseForm_submit" id="button_submit">
                                    <i class="layui-icon layui-icon-ok"></i>提交
                                </button>
                                <button type="reset" class="layui-btn layui-btn-primary">
                                    <i class="layui-icon layui-icon-refresh"></i>重置
                                </button>
                                <button type="button" class="layui-btn layui-btn-danger" onclick="ct_close();">
                                    <i class="layui-icon layui-icon-close"></i>取消
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
    var urlStr = "/urms/category/category";
    var isUpdate = false;

    $(document).ready(function(){
        if($("#id").val()!="" && $("#id").val()!=null){
            isUpdate = true;
        }else {
            let pId = $("#pId").val();
            $.get(urlStr+"_getCategoryCode?pId="+pId,function(data,status){
                $("#categoryCode").val(data);
            });
            $.get(urlStr+"_getSortNum?pId="+pId,function(data,status){
                $("#sortNum").val(data);
            });
        }
    });

    $("#categoryCode").change(function(){
        let code = $("#categoryCode").val();
        isExistCategory(code);
    });

    //加载layui相关模块并使用
    layui.use('form', function () {
        var form = layui.form;
        //监听submit提交
        form.on('submit(ct_baseForm_submit)', function (data) {
            $("#button_submit").attr("disabled","disabled");
            let code = $("#categoryCode").val();
            let id = $("#id").val();
            let url = urlStr+"_isExistCategory?categoryCode="+code+"&id="+id;
            $.get(url,function(data,status){
                if(data){
                    $("#button_submit").removeAttr("disabled");
                    $("#categoryCode_text").show();
                }else {
                    $("#categoryCode_text").hide();
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
                    //加载树
                    var pNode = parent.cTreeObj.getNodeByParam("id", $("#pId").val(), null);//父节点
                    if(isUpdate){//更新
                        let node = parent.cTreeObj.getNodeByParam("id", data.id, null);
                        if(node != null){
                            node.name = $("#categoryName").val();
                            node.categoryType = $("#categoryType").val();
                            parent.cTreeObj.updateNode(node);
                        }
                    }else{
                        parent.cTreeObj.addNodes(pNode, {id:""+data.id+"",name:""+$("#categoryName").val()+"",categoryType:""+$("#categoryType").val()+""});//新增
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


    function isExistCategory(code) {
        let id = $("#id").val();
        let url = urlStr+"_isExistCategory?categoryCode="+code+"&id="+id;
        $.get(url,function(data,status){
            if(data){
                $("#categoryCode_text").show();
                return true;
            }else {
                $("#categoryCode_text").hide();
                return false;
            };
        });
    }
    //关闭当前弹出层窗口
    function ct_close(){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }

</script>
</body>
</html>