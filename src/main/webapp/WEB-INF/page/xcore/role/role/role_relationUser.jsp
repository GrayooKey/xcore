<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="opt" uri="/WEB-INF/taglib/option.tld" %>
<jsp:include page="/WEB-INF/page/common/ct_mall_com.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>关联用户</title>
    <!-- zTree树插件 -->
    <link rel="stylesheet" href="/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="/common/plugins/ztree/js/jquery.ztree.all-3.5.js"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="layui-row layui-col-space30">
                        <%-- ztree树 容器 --%>
                        <div class="layui-col-sm2">
                            <ul id="tree" class="ztree"></ul>
                        </div>

                        <%-- 关联用户id --%>
                        <input type="hidden" id="userIds" value="${userIds}">

                        <div class="layui-col-sm6">
                            <%--穿梭框 start--%>
                            <div id="transferDiv"></div>
                            <%--穿梭框 end--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    /************************** 穿梭框 Start **************************/
    var transfer;

    $(function () {

        //加载穿梭框组件
        layui.use('transfer', function () {
            transfer = layui.transfer;

            //渲染
            transfer.render({
                elem: '#transferDiv',
                title: ['全部用户', '关联用户'],
                data: [],
                parseData: function(res){
                    return {
                        "value": res.id,
                        "title": res.name,
                        "disabled": res.disabled,
                        "checked": res.checked
                    }
                },
                id: 'transfer',
                showSearch: true,
                width: 300,
                height: 550,
                text: {
                    none: '无数据',
                    searchNone: '无匹配数据'
                },
                onchange: function (data, index) {
                    //已关联用户
                    let userArr = new Array();
                    let userIds = $("#userIds").val();
                    if (userIds != null && userIds != "") {
                        userArr = userIds.split(",");
                    }

                    if (index === 0) {
                        for (let i in data) {
                            userArr.push(data[i].value);
                        }
                    } else {
                        for (let i in data){
                            userArr = userArr.filter(function (item) {
                                return item != data[i].value;
                            });
                        }
                    }

                    $("#userIds").val(userArr.length > 0 ? userArr.join(",") : "");
                }
            });

            //初始化已关联用户
            initRelationUser();
        });

    });


    /**
     * 初始化已关联用户
     */
    function initRelationUser(){
        /*用户集合*/
        $.ajax({
            url: "/urms/user/user_queryUserList",
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data) {
                    //全部用户
                    let array = new Array();
                    for (let i in data) {
                        array.push({"id": data[i].id, "name": data[i].userName});
                    }

                    //已关联用户
                    let userArr = new Array();
                    let userIds = $("#userIds").val();
                    if (userIds != null && userIds != "") {
                        userArr = userIds.split(",");
                    }

                    //实例重载
                    transfer.reload('transfer', {
                        data: array,
                        value: userArr
                    });
                }
            }
        });
    }
    /************************** 穿梭框 End **************************/


    /********************* ztree(行政区划树) Start *****************************/
    var zTreeObj;
    $(document).ready(function(){
        zTreeObj = $.fn.zTree.init($("#tree"), setting);//初始化ztree

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
        /*用户集合*/
        $.ajax({
            url: "/urms/user/user_queryUserList?dataAreaCode="+treeNode.areaCode,
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data) {

                    //先隐藏全部
                    $("ul.layui-transfer-data:first li input").parent().hide();
                    //再显示过滤后的
                    for(let i in data) {
                        $("ul.layui-transfer-data:first li input[value='" + data[i].id + "']").parent().show();
                    }
                }
            }
        });
    }
    /************************** ztree End **********************************/
</script>
</body>
</html>