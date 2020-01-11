<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="opt" uri="/WEB-INF/taglib/option.tld" %>
<jsp:include page="/WEB-INF/page/common/ct_mall_com.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>关联角色</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="layui-row layui-col-space30">
                        <%-- 关联用户id --%>
                        <input type="hidden" id="roleIds" value="${roleIds}">

                        <div class="layui-col-sm8">
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
                title: ['全部角色', '关联角色'],
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
                    //已关联角色
                    let roleArr = new Array();
                    let roleIds = $("#roleIds").val();
                    if (roleIds != null && roleIds != "") {
                        roleArr = roleIds.split(",");
                    }

                    if (index === 0) {
                        for (let i in data) {
                            roleArr.push(data[i].value);
                        }
                    } else {
                        for (let i in data){
                            roleArr = roleArr.filter(function (item) {
                                return item != data[i].value;
                            });
                        }
                    }

                    $("#roleIds").val(roleArr.length > 0 ? roleArr.join(",") : "");
                }
            });

            //初始化已关联角色
            initRelationRole();
        });

    });


    /**
     * 初始化已关联角色
     */
    function initRelationRole(){
        /*角色集合*/
        $.ajax({
            url: "/urms/role/role_queryRoleList",
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data) {
                    //全部用户
                    let array = new Array();
                    for (let i in data) {
                        array.push({"id": data[i].id, "name": data[i].roleName});
                    }

                    //已关联角色
                    let roleArr = new Array();
                    let roleIds = $("#roleIds").val();
                    if (roleIds != null && roleIds != "") {
                        roleArr = roleIds.split(",");
                    }

                    //实例重载
                    transfer.reload('transfer', {
                        data: array,
                        value: roleArr
                    });
                }
            }
        });
    }
    /************************** 穿梭框 End **************************/
</script>
</body>
</html>