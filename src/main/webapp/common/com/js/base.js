/*****************************************************************
 * 描述: 该文件主要包含一些常用的且依赖于系统底层的js工具类方法
 ****************************************************************/

/**
 * 数据字典翻译
 * @param dict 字典名
 * @param key
 * @returns {String}
 */
function changeDataDictByKey(dict,key){
    var state ="";
    $.ajax({
        type:"post",
        async:false,
        dataType:"text",
        url: '/urms/category/changeDataDictByKey?dict='+dict+'&key='+key,
        success : function(data){
            if(data!=""&&data!=null&&data!="null")
                state = data;
        }
    });
    return state;
}

/**
 * 数据字典翻译, 根据 dict 和 key 翻译多个值, 用","隔开
 * @param dict 字典名
 * @param key
 * @returns {String}
 */
function changeDataManyDictByKey(dict,key){
    var state ="";
    $.ajax({
        type:"post",
        async:false,
        dataType:"text",
        url: '/urms/category/changeDataManyDictByKey?dict='+dict+'&key='+key,
        success : function(data){
            if(data!=""&&data!=null&&data!="null")
                state = data;
        }
    });
    return state;
}


/**
 * 数据字典翻译 (根据行政区划编码获取行政区划名称)
 * @param areaCode 行政区划编码
 * @returns {String}
 */
function changeAreaNameByAreaCode(areaCode){
    var areaName ="";
    $.ajax({
        type:"post",
        async:false,
        dataType:"text",
        url: '/urms/areaCode/changeAreaNameByAreaCode?areaCode='+areaCode,
        success : function(data){
            if(data!=""&&data!=null&&data!="null")
                areaName = data;
        }
    });
    /*if (areaCode != null && areaCode != "") {
        var totalObj = new Array();
        totalObj = totalObj.concat(province).concat(city).concat(district).concat(street).concat(village);
        if (totalObj != null && totalObj.length > 0) {
            for (var i in totalObj) {
                if (totalObj[i].id === areaCode) {
                    areaName = totalObj[i].name;
                    break;
                }
            }
        }
    }*/
    return areaName;
}


/**
 * 获得字典键值对数组
 * @param dict 字典名
 * @returns {Array}
 */
function getDataDict(dict){
    var dataDict = new Array();
    $.ajax({
        type:"post",
        async:false,
        dataType:"json",
        url: '/urms/category/getDataDict?dict='+dict,
        success : function(data){
            dataDict[0] = data.keys;
            dataDict[1] = data.values;
        }
    });
    return dataDict;
}


/**
 * 获得字典值
 * @param dict 字典名
 * @returns {String}
 */
function getDict(dict){
    var dataDict = "";
    $.ajax({
        type:"post",
        async:false,
        dataType:"json",
        url: '/urms/category/getDict?dict='+dict,
        success : function(data){
            if(data.dict != 'undefined' && data.dict != null){
                dataDict = data.dict;
            }
        }
    });
    return dataDict;
}


/**
 * 子系统翻译
 * @param sysCode 子系统编码
 * @returns {String}
 */
function changeSubsystem(sysCode){
    var sysName ="";
    $.ajax({
        type:"post",
        async:false,
        dataType:"text",
        url: '/urms/category/changeSubsystem?sysCode='+sysCode,
        success : function(data){
            sysName = data;
        }
    });
    return sysName;
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

/**
 * @Description 图层类型选择
 * @param categoryCode 字典编码
 * @param id 回填id
 * @param name 回填name
 * @param selectNum 单选还是多选
 * @Author zc
 * @Date 2019/6/6 9:58
 **/
function choose_layer(categoryCode,id,name,selectNum){
    choose_layer_index = parent.layer.open({
        type: 2,
        title: "图层类型选择",
        shadeClose: true,//打开遮蔽
        shade: 0.6,
        maxmin: true, //开启最大化最小化按钮
        area: ["65%", "80%"],
        content: "/urms/category/category_choose?winName="+window.name+"&categoryCode="+categoryCode+"&id="+id+"&name="+name+"&selectNum="+selectNum,
        success: function(layero, index){
            $("#choose_layer_index").val(index);
        }
    });
}