/**
 * 功能: 该文件是对基础业务模块的新增、编辑、删除、查看、数据统计、组织树、地图标注 等公用底层js代码进行封装
 * 用处: 一般的列表页面、编辑页面、详情页面都引用该文件
 *
 * 版本: 1.0
 * 日期: 2019/7/4
 * 编辑者: xuezb
 */
//---------------------------------列表页面顶部数据统计相关方法-------------------------------------------------------------------

var baseCountDivSel = "#ct_baseCountDiv";   //页面顶部统计区域Div容器选择器
var baseCountSel = "#ct_baseCount";         //页面顶部统计区域ul容器选择器

$(document).ready(function(){
    if($(baseCountSel).length > 0){     //判断页面顶部统计区域的容器是否存在
        pageInit();//页面统计数据初始化
    }
});
//页面统计数据初始化
function pageInit() {
    $.ajax({
        type : 'post',
        async : true,
        dataType : 'json',
        url : urlStr + '_getBaseCount',
        success : function(data) {
            var total = 0;  //总数

            //根据统计类型的数量指定统计区域的高度以及每个li占据多少列
            let len = data.length;
            let liCol = 1;
            if(len === 0){
                liCol = 6;
            }else if(len === 2){
                liCol = 4;
            }else if(len === 3){
                liCol = 3;
            }else if(len >= 4 && len <= 5){
                liCol = 2;
            }else if(len >= 6 && len <= 11){
                liCol = 2;
                $(baseCountDivSel).css("height", "180px");
            }else if(len >= 12 && len <= 17){
                liCol = 2;
                $(baseCountDivSel).css("height", "270px");
            }else{
                $(baseCountDivSel).css("height", "180px");
            }

            let htm = "";
            for(let k in data){
                total += parseInt(data[k][1]);

                htm += '<li class="layui-col-xs'+liCol+'">' +
                    '    <span class="layadmin-backlog-body">' +
                    '        <h3>'+ data[k][0] +'</h3>' +
                    '        <p><cite id="type'+k+'">0</cite></p>' +
                    '    </span>' +
                    '</li>';
            }
            let primateHtm = '<li class="layui-col-xs'+liCol+'">' +
                '    <span class="layadmin-backlog-body">' +
                '        <h3>全部</h3>' +
                '        <p><cite id="typeAll">0</cite></p>' +
                '    </span>' +
                '</li>';
            $(baseCountSel).html(primateHtm + htm);

            // 数字增长显示
            countUp(document.getElementById("typeAll"), total, 0, 1000, 0);
            for(var k in data){
                countUp(document.getElementById("type"+k), data[k][1], 0, 1000, 0);
            }
        },
    });
}


//---------------------------------页面公用方法---------------------------------------------------------------------------

// 表格刷新
function reloadTable() {
    layui.table.reload(ctTableId, {});
}

//关闭当前弹出层窗口
function ct_close(){
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index);
}


//---------------------------------列表页面数据表格相关方法-------------------------------------------------------------------

var ctBaseForm_list = "ct_baseForm_list";   //列表页面查询搜索表单id
var ctTableId = "ct_baseTable";   //数据表格容器唯一id
var ctTableFilter = "ct_baseTable_filter";    //数据表格容器事件过滤器
var ctSearchFilter = "ct_baseForm_search";    //列表页面表单搜索按钮事件过滤器

var ctHeadToolBar = "#ct_baseTable_toolbar_head"; //头工具栏选择器
var ctRowToolBar = "#ct_baseTable_toolbar_row";   //行工具栏选择器


/********************* ztree(行政区划树) Start *****************************/
var zTreeObj;
let dataAreaName;

$(document).ready(function(){
    if(currentPage == 'list' && $("#tree").length > 0){
        zTreeObj = $.fn.zTree.init($("#tree"), setting);//初始化ztree
    }
});
//初始化树
var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    callback:{
        onClick: zTreeOnClick
    },
    async: {//异步加载
        enable: true,
        type: "post",
        dataType: "text",
        contentType:"application/x-www-form-urlencoded",
        url: "/urms/areaCode/areaCode_areaTree",
        autoParam: ["id"]
    }
};
//点击树后回调
function zTreeOnClick(event, treeId, treeNode) {
    $("#dataAreaCode").val(treeNode.areaCode);
    dataAreaName = treeNode.name;
    var dataForm = queryParams(ctBaseForm_list);
    layui.table.reload(ctTableId, {
        where: JSON.parse(dataForm)
    });
}
/**
 * 传递请求参数(列表分页查询)
 * @param formId    表单id
 * @returns
 */
function queryParams(formId) {
    if($("#"+formId).length>0) {
        var form = document.getElementById(formId);
        var data = getFormJson(form);
        var json =  (JSON.stringify(data)).replace(/}{/, ',');//参数组合
        return json;
    }
}
/*
 * 表单字段转换
 * @param frm    表单对象
 * @returns
 */
function getFormJson(frm) {
    var o = {};
    var a = $(frm).serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
/************************** ztree End **********************************/


//加载layui相关模块并使用
layui.use(['form', 'table', 'layer'], function() {
    var form = layui.form;
    var table = layui.table;
    var layer = layui.layer;


    //判断当前页面是否为列表页面
    if(currentPage === 'list'){
        //数据表格渲染
        renderTable(table);
    }

    //加载外部非普遍性的监听
    if (typeof(ct_load_extra) != "undefined") {
        ct_load_extra(form);
    }

    //监听搜索
    form.on('submit('+ ctSearchFilter +')', function(data){
        var field = data.field; //data.field : 获取查询搜索表单数据
        //表格重载
        table.reload(ctTableId, {
            where: field
        });
        return false;
    });

    //监听头工具栏事件
    table.on('toolbar('+ctTableFilter+')', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        var data = checkStatus.data;

        //非普遍性的(额外特殊的)头工具栏事件
        if (typeof (ct_toolbar_extra) != "undefined") {
            if (ct_toolbar_extra(obj.event)) {
                return; //中断该函数代码的继续执行
            }
        }

        //普遍性的头工具栏事件
        switch(obj.event){
            case 'ct_head_add': //新增
                if($("#tree").length > 0){ //判断列表页面是否存在 树容器(#tree),若存在,则新增时需进行逻辑判断
                    var dataAreaCode = $("#dataAreaCode").val();
                    // 如果 dataAreaCode 为空 且 树容器(#tree)不包含 "ct_nomustchoose"类。    [注: 树容器(#tree) 如果包含 "ct_nomustchoose" 则说明新增时不必一定选择行政区域!!!]
                    if ((dataAreaCode === "" || dataAreaCode == null) && !($("#tree").hasClass("ct_nomustchoose"))) {
                        return layer.msg('请选择对应行政区域', {icon: 0});
                    } else {
                        layer.open({
                            type: 2
                            , scrollbar: false //禁止滚动条
                            , title: '在' + dataAreaName + '下新增'
                            , content: urlStr + '_edit?dataAreaCode=' + dataAreaCode
                            , area: ['100%', '100%']
                            , resize: false //禁止拉伸
                            , move: false//禁止拖动
                        });
                    }
                }else{
                    layer.open({
                        type: 2
                        , scrollbar: false //禁止滚动条
                        , title: '新增'
                        , content: urlStr+'_edit'
                        , area: ['100%', '100%']
                        , resize: false //禁止拉伸
                        , move: false//禁止拖动
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
            case 'ct_head_delCascade':  //删除(存在特殊表关联的情况, 如建筑表关联单元表)
                if(data.length === 0){
                    return layer.msg('请选择数据', {icon: 0});
                }
                var ids = '';   //选中的Id
                $(data).each(function (index, item) {
                    ids += item.id + ',';
                });
                layer.confirm('确定删除选中数据?',{icon:3}, function(index){
                    layer.close(index);
                    ct_deleteCascade(ids);
                });
                break;
            default:
                break;
        }
    });

    //监听行工具事件
    table.on('tool('+ctTableFilter+')', function(obj){
        var data = obj.data;
        var layEvent = obj.event;

        //非普遍性的(额外特殊的)行工具事件
        if (typeof(ct_tool_extra) != "undefined") {
            if (ct_tool_extra(data, layEvent)) {
                return; //中断该函数代码的继续执行
            }
        }

        //普遍性的行工具事件
        switch(obj.event){
            case 'ct_row_detail':
                layer.open({
                    type: 2
                    ,scrollbar: false
                    ,title: '详情'
                    ,content: urlStr+'_detail?id='+data.id
                    ,area: ['100%', '100%']
                    ,resize:false //禁止拉伸
                    ,move: false//禁止拖动
                });
                break;
            case 'ct_row_edit':
                layer.open({
                    type: 2
                    ,scrollbar: false
                    ,title: '编辑'
                    ,content: urlStr+'_edit?id='+data.id
                    ,area: ['100%', '100%']
                    ,resize:false //禁止拉伸
                    ,move: false//禁止拖动
                });
                break;
            case 'ct_row_del':
                layer.confirm('确定删除该行数据?',{icon:3}, function(index){
                    layer.close(index);
                    ct_delete(data.id);
                });
                break;
            case 'ct_row_delCascade':
                layer.confirm('确定删除该行数据?',{icon:3}, function(index){
                    layer.close(index);
                    ct_deleteCascade(data.id);
                });
                break;
            default:
                break;
        }
    });


    // 删除数据
    function ct_delete(ids) {
        var url = urlStr + '_delete?ids=' + ids;
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


    // 删除数据(存在特殊表关联的情况, 如建筑表关联单元表)
    function ct_deleteCascade(ids) {
        var url = urlStr + '_deleteCascade?ids=' + ids;
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
                    layer.msg(data.promptMessage, {icon: 2});
                }
            },
            error: function(obj) {
                layer.msg('删除失败！', {icon: 2});
            }
        });
    }
});


//---------------------------------编辑页面相关方法-------------------------------------------------------------------

var ctBaseForm_edit = "ct_baseForm_edit";   //编辑页面表单id
var ctBaseForm_edit_extend = "ct_baseForm_edit_extend";   //编辑页面扩展表表单id
var ctSubmitFilter = "ct_baseForm_submit";  //编辑页面表单提交按钮事件过滤器

//加载layui相关模块并使用
layui.use('form', function () {
    var form = layui.form;

    //监听submit提交
    form.on('submit('+ctSubmitFilter+')', function (data) {

        /*将提交按钮禁用,防止重复提交*/
        $(".layui-btn[lay-filter='ct_baseForm_submit']").attr("lay-filter", "ct_baseForm_submit_hide");


        //判断编辑页面中是否存在变量 ct_beforeSave, 如果声明了, 则可调用编辑页面的 ct_beforeSave() 方法进行保存实体前的一些逻辑处理
        if (typeof(ct_beforeSave)!="undefined") {
            let boolean = ct_beforeSave();
            if (!boolean) {
                /*重新启用提交按钮*/
                $(".layui-btn[lay-filter='ct_baseForm_submit_hide']").attr("lay-filter", "ct_baseForm_submit");
                return false;
            }
        }


        //判断编辑页面中是否有地图标注ID(layerLabelId)存在
        if($("#layerLabelId").length > 0){
            ct_backfill(); //回写标注所在地
            saveOrUpdate_layerLabel();//地图标注信息保存
        }


        //判断编辑页面中是否有 混合附件上传的情况，文件上传(#ct_uploadFile_plus)、单图上传(#ct_uploadSinglePhoto_plus), 多图上传(#ct_uploadManyPhoto_plus), 若某一个存在, 则调用编辑页面的 ct_upload_plus() 方法进行附件上传
        if ($("#ct_uploadFile_plus").length > 0 || $(".ct_uploadSinglePhoto_plus").length > 0 || $(".ct_uploadManyPhoto_plus").length > 0) {
            ct_upload_plus(data); //保存附件
        } else {
            saveOrUpdate(data);//提交表单.
        }

        return false;
    });
});

//地图标注信息保存
function saveOrUpdate_layerLabel(){
    $.ajax({
        url : '/gis/layerLabel/layerLabel_saveOrUpdate',
        type : 'post',
        async : false,
        dataType : 'json',
        data : $('#layerForm').serialize(),
        success : function(data) {
            if (data.result) {
                $("#layerLabelId").val(data.id);
                $("#layerLabel_x").val(data.x);
                $("#layerLabel_y").val(data.y);
            } else {
                parent.layer.msg('保存出错！', {icon: 2});
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            parent.layer.msg('保存出错！', {icon: 2});
        }
    });
}


//提交表单
function saveOrUpdate(data){
    $.ajax({
        url: urlStr+'_saveOrUpdate',
        type: 'post',
        async: false,
        dataType: 'json',
        data: $('#'+ctBaseForm_edit).serialize(),
        success: function (data) {
            if (data.result) {

                //判断编辑页面中是否存在变量 ct_afterSave, 如果声明了, 则可调用编辑页面的 ct_afterSave() 方法进行保存实体后的一些逻辑处理
                if (typeof(ct_afterSave)!="undefined") {
                    ct_afterSave(data);
                }

                //判断编辑页面是否有 扩展表信息(#ct_baseForm_edit_extend : 扩展表表单id)
                if ($("#ct_baseForm_edit_extend").length > 0) {
                    $("#primaryTableId").val(data.id);  //将主表id保存在扩展表中
                    saveOrUpdate_extend();
                }


                //判断编辑页面是否有 文件上传(#ct_uploadFile)、图片上传(#ct_uploadPhoto), 若某一个存在, 则调用编辑页面的 ct_upload() 方法进行附件上传
                if($("#ct_uploadFile").length>0 || $("#ct_uploadPhoto").length>0){
                    ct_upload(data.id); //保存附件 (参数: 实体id)
                }else{
                    parent.layer.msg('保存成功！', {icon: 1});
                    parent.reloadTable();//刷新父级列表页面
                    ct_close();//关闭当前弹出窗口
                }

            } else {
                /*重新启用提交按钮*/
                $(".layui-btn[lay-filter='ct_baseForm_submit_hide']").attr("lay-filter", "ct_baseForm_submit");
                parent.layer.msg('保存出错！', {icon: 2});
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            /*重新启用提交按钮*/
            $(".layui-btn[lay-filter='ct_baseForm_submit_hide']").attr("lay-filter", "ct_baseForm_submit");
            parent.layer.msg('保存出错！', {icon: 2});
        }
    });
}


//提交扩展表表单
function saveOrUpdate_extend() {
    $.ajax({
        url: urlStr+'_saveOrUpdate_extend',
        type: 'post',
        async: false,
        dataType: 'json',
        data: $('#'+ctBaseForm_edit_extend).serialize(),
        success: function (data) {
            if (data.result) {
                //...
            } else {
                /*重新启用提交按钮*/
                $(".layui-btn[lay-filter='ct_baseForm_submit_hide']").attr("lay-filter", "ct_baseForm_submit");
                parent.layer.msg('保存出错！', {icon: 2});
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            /*重新启用提交按钮*/
            $(".layui-btn[lay-filter='ct_baseForm_submit_hide']").attr("lay-filter", "ct_baseForm_submit");
            parent.layer.msg('保存出错！', {icon: 2});
        }
    });
}

