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
    <title>菜单配置</title>

</head>
<body>
<div class="layui-fluid" style="margin-bottom: 65px;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <form class="layui-form" id="ct_baseForm_edit">
                    <%-- BaseModule start --%>
                    <input type="hidden" id="id" name="id" value="${menuVo.id}"/>
                    <input type="hidden" id="dataSource" name="dataSource" value="1"/>
                    <%-- BaseModule end --%>
                    <input type="hidden" id="pId" name="pId" value="${menuVo.pId}"/>
                    <input type="hidden" id="pIds" name="pIds" value="${menuVo.pIds}"/>
                    <input type="hidden" id="pNames" name="pNames" value="${menuVo.pNames}"/>
                    <input type="hidden" id="menuLevel" name="menuLevel" value="${menuVo.menuLevel}"/>
                    <input type="hidden" id="isLeaf" name="isLeaf" value="${menuVo.isLeaf}"/>

                    <div class="layui-card-header"><strong>基础信息</strong><span class="layui-word-aux">（加 * 内容属于必填项）</span></div>
                    <div class="layui-card-body">
                        <div class="layui-row">

                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*菜单名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="menuName" name="menuName" value="${menuVo.menuName}" autocomplete="off" class="layui-input" lay-verify="required" lay-verType="tips">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*菜单编码</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="menuCode" id="menuCode" value="${menuVo.menuCode}" autocomplete="off" class="layui-input" lay-verify="required" lay-verType="tips">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs3">
                                <div style="color: red;padding: 9px;display: none;" id="menuCode_text">该编码已存在!</div>
                            </div>

                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">窗口名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="winName" name="winName" value="${menuVo.winName}" autocomplete="off" class="layui-input" >
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs3">
                                <div style="color: #999;padding: 9px;">用于弹窗标题</div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">*菜单类型</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="menu_type" isDefSelect="false" id="menuType" name="menuType" value="${menuVo.menuType }"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">打开方式</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="menu_openType" isDefSelect="false" id="menuOpenType" name="menuOpenType" value="${menuVo.menuOpenType }"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">排序</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" name="sortNum" id="sortNum" value="${menuVo.sortNum}">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">图标</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="menuIcon" id="menuIcon" value="${menuVo.menuIcon}" autocomplete="off" class="layui-input" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">状态</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="menu_state" isDefSelect="false" id="state" name="state" value="${menuVo.state }"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6 layui-col-xs-offset2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">链接地址</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="menuUrl" id="menuUrl" value="${menuVo.menuUrl}" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="layui-layout-admin">
                        <div class="layui-footer" style="left: 0;z-index: 889;">
                            <button class="layui-btn" lay-submit lay-filter="ct_baseForm_submit"><i
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
                </form>

                <div class="layui-card-header"><strong>功能定义</strong></div>
                    <div class="layui-card-body">
                        <table class="layui-hide" id="t_menuAttribute" lay-filter="t_menuAttribute"></table>
                        <script type="text/html" id="t_menuAttribute-table-toolbar">
                            <div class="layui-btn-container">
                                 <button class="layui-btn layui-btn-sm" lay-event="add" title="添加"><i class="layui-icon layui-icon-add-1"></i></button>
                                 <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="ct_head_del"
                                            title="删除"><i class="layui-icon layui-icon-delete"></i></button>
                            </div>
                        </script>

                        <script type="text/html" id="table-toolbar-bar">
                            <a class="layui-btn layui-btn-xs" lay-event="edit" title="编辑"><i class="layui-icon layui-icon-edit"></i></a>
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script>
    var urlStr = "/urms/menu/menu";
    var isUpdate = false;
    var ctTableId = "ct_baseTable";   //数据表格容器唯一id

    $(document).ready(function(){
        if($("#id").val()!="" && $("#id").val()!=null){
            isUpdate = true;
        }else {
            let pId = $("#pId").val();
            $.get(urlStr+"_getSortNum?pId="+pId,function(data,status){
                $("#sortNum").val(data);
            });
        }
    });

    $("#menuCode").change(function(){
        let code = $("#menuCode").val();
        isExistMenu(code);
    });

    //加载layui相关模块并使用
    layui.use(['form', 'table','layer'], function () {
        var table = layui.table;
        var form = layui.form;
        var layer = layui.layer;
        var menuId = $("#id").val();

        //方法级渲染
        table.render({
            id: ctTableId,
            elem: '#t_menuAttribute',
            title: '菜单功能列表',
            url: urlStr+'_loadAttribute?menuId='+menuId,
            toolbar: '#t_menuAttribute-table-toolbar',
            page: true,
            method: "post",
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {title: "序号", type: 'numbers', fixed: 'left', width: 50},
                {title: "功能名称", field: "attributeName",align: "center"},
                {title: "功能编码",field: "attributeCode",align: "center" },
                {title: "操作方法",field: "attributeMethod",align: "center" },
                {title: "功能按钮", field: "color", width: 130,align:"center", templet: function (obj) {
                    switch (obj.showMethod){
                        case 1:
                            return "<span class=\"layui-btn layui-btn-xs "+obj.color+"\"><i class=\"layui-icon "+obj.icon+"\" ></i></span>";
                        case 2:
                            return "<span class=\"layui-btn layui-btn-xs "+obj.color+"\">"+obj.attributeName+"</span>";
                        case 3:
                            return "<span class=\"layui-btn layui-btn-xs "+obj.color+"\"><i class=\"layui-icon "+obj.icon+"\" ></i>"+obj.attributeName+"</span>";
                        case 4:
                            return "<span class=\"layui-btn layui-btn-xs "+obj.color+"\">"+obj.attributeName+"<i class=\"layui-icon "+obj.icon+"\" ></i></span>";
                        default:
                            return "<span class=\"layui-btn layui-btn-xs "+obj.color+"\"><i class=\"layui-icon "+obj.icon+"\" ></i></span>";
                        }
                    }
                },
                {title: "类型",field: "pageType",align: "center", width: 100, templet: function (obj) {
                        return changeDataDictByKey("menu_pageType", obj.pageType);
                    }
                },
                {title: "排序",field: "sortNum",align: "center", width: 80},
                {fixed: 'right',width: 120, align: 'center', unresize: 'true', toolbar: '#table-toolbar-bar', title: "操作"}
            ]]
        });

        //头工具栏事件
        table.on('toolbar(t_menuAttribute)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var data = checkStatus.data;
            switch(obj.event){
                case 'add': //新增
                    if(menuId==""|| menuId==null){
                         return layer.msg('请先保存菜单!', {icon: 0});
                    }else {
                        layer.open({
                        type: 2,
                        scrollbar: false, //禁止滚动条
                        title: '目录功能-添加',
                        content: urlStr+"_editAttr?menu.id="+menuId,
                        area: ['80%', '80%']
                        });
                    }
                    break;
                case 'ct_head_del':  //删除
                    if(data.length === 0){
                        return layer.msg('请选择数据', {icon: 0});
                    }
                    var ids = '';   //选中的Id
                    $(data).each(function (index, item) {
                        ids += item.id + ',';
                    });
                    layer.confirm('确定删除选中数据?',{icon:3}, function(index){
                        layer.close(index);
                        ct_delete(ids);
                    });
                    break;
            };
            return false;
        });


        //监听行工具事件
        table.on('tool(t_menuAttribute)', function(obj){
            var data = obj.data;
            if(obj.event === 'edit'){
                layer.open({
                type: 2,
                scrollbar: false,
                title: '菜单功能-功能编辑',
                content: urlStr+'_editAttr?id='+data.id,
                area: ['100%', '100%'] ,
                resize:false,
                move: false//禁止拖动
                });
            }
        });

        
        //监听submit提交
        form.on('submit(ct_baseForm_submit)', function (data) {
            let code = $("#menuCode").val();
            let id = $("#id").val();
            let url = urlStr+"_isExist?menuCode="+code+"&id="+id;
            $.get(url,function(data,status){
                if(data){
                    $("#menuCode_text").show();
                }else {
                    $("#menuCode_text").hide();
                    saveOrUpdate(data);//提交表单.
                };
            });
            return false;
        });
    });

    //图标选择
    $("#menuIcon").click(function(){
        let icon = $("#menuIcon").val();
        layer.open({
            type: 2,
            scrollbar: false,
            title: '选择图标',
            content: urlStr + '_chooseIcon?icon=' + icon,
            area: ['100%', '100%'] ,
            resize: false ,
            move: false//禁止拖动
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
                    let pNode = parent.cTreeObj.getNodeByParam("id", $("#pId").val(), null);//父节点
                    if(isUpdate){//更新
                        var node = parent.cTreeObj.getNodeByParam("id", data.id, null);
                        if(node != null){
                            node.name = $("#menuName").val();
                            parent.cTreeObj.updateNode(node);
                        }
                    }else{
                        parent.cTreeObj.addNodes(pNode, {id:""+data.id+"",name:""+$("#menuName").val()+""});//新增
                    }
                    ct_close();
                } else {
                    parent.layer.msg('保存出错！', {icon: 2});
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                parent.layer.msg('保存出错！', {icon: 2});
            }
        });
    }


    function isExistMenu(code) {
        let id = $("#id").val();
        let url = urlStr+"_isExist?menuCode="+code+"&id="+id;
        $.get(url,function(data,status){
            if(data){
                $("#menuCode_text").show();
                return true;
            }else {
                $("#menuCode_text").hide();
                return false;
            };
        });
    }

    // 删除数据
    function ct_delete(ids) {
        var url = urlStr + '_deleteAttr?ids=' + ids;
        $.ajax({
            url: url,
            type: 'post',
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data.result) {
                    reloadTable();
                    layer.msg('删除成功', {icon: 1});
                }else {
                    layer.msg('删除失败！', {icon: 2});
                }
            },
            error: function(obj) {
                layer.msg('删除失败！', {icon: 2});
            }
        });
    }
    // 表格刷新
    function reloadTable() {
        layui.table.reload(ctTableId, {});
    }

    //关闭当前弹出层窗口
    function ct_close(){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
</script>
</body>
</html>