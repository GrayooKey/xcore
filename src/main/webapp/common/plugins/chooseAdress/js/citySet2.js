/**
 * 功能: 该文件是二级联动的核心配置文件, 跟其它的几个文件【chooseAdress.css（样式文件）、Popt.js(固定配置)、dataJson.js(数据文件)】构成一个完整的js组件
 * 用处: 一般用于编辑页面的二级联动
 *
 * 版本: 1.0
 * 日期: 2019/8/1
 * 编辑者: xuezb
 */

function SelCity2(obj, e) {
    var ths = obj;

    var dal = '<div class="_citys">'
        + '<span title="关闭" id="cColse" >×</span>'
        + '<ul id="_citysheng" class="_citys0">'
        + '<li class="citySel">省份</li>'
        + '<li>城市</li>'
        + '</ul>'
        + '<div id="_citys0" class="_citys1"></div>'
        + '<div style="display:none" id="_citys1" class="_citys1"></div>'
        + '</div>';


    Iput.show({
        id: ths, event: e, content: dal, width: "470"
    });
    $("#cColse").click(function () {
        Iput.colse();
    });

    var tb_province = [];
    var b = province;
    for (var i = 0, len = b.length; i < len; i++) {
        tb_province.push('<a data-level="0" data-id="' + b[i]['id'] + '" data-name="' + b[i]['name'] + '">' + b[i]['name'] + '</a>');
    }
    $("#_citys0").append(tb_province.join(""));


    //省级的点击
    $("#_citys0 a").click(function () {
        var citySS = getCity($(this));		//获取选择省份的所有城市
        $("#_citys1 a").remove();			//先清空上一次的内容,
        $("#_citys1").append(citySS);		//再添加此次内容
        $("._citys1").hide();				//将两个div全部隐藏,
        $("._citys1:eq(1)").show();			//再显示第二个div(市级div)

        $("#_citys0 a,#_citys1 a").removeClass("AreaS");	//先清空之前div的样式AreaS,
        $(this).addClass("AreaS");							//再为最新的div添加样式AreaS

        ths.value = $(this).data("name");			//点击省级后输入框的值(省),
        $("#" + province_).val($(this).data("id"));	//省级编码code(隐藏框),

        $("#" + city_).val(null);					//再将省级下(市)code清空

        var lev = $(this).data("name");
        if (document.getElementById("hProvince") == null) {
            var hiddenIputs = $('<input>', {
                type: 'hidden', name: "hProvince", "data-id": $(this).data("id"), id: "hProvince", val: lev
            });
            $(ths).after(hiddenIputs);
        } else {
            $("#hProvince").val(lev);
            $("#hProvince").attr("data-id", $(this).data("id"));
        }


        //市级的点击
        $("#_citys1 a").click(function () {
            $("#_citys1 a").removeClass("AreaS");			//先清空之前div的样式AreaS,
            $(this).addClass("AreaS");						//再为最新的div添加样式AreaS

            var hP = $("#hProvince").val();
            ths.value = hP + "-" + $(this).data("name");	//点击市级后输入框的值(省+市),
            $("#" + city_).val($(this).data("id"));			//市级编码code(隐藏框)

            var lev = $(this).data("name");
            if (document.getElementById("hCity") == null) {
                var hiddenIputs = $('<input>', {
                    type: 'hidden', name: "hCity", "data-id": $(this).data("id"), id: "hCity", val: lev
                });
                $(ths).after(hiddenIputs);
            } else {
                $("#hCity").val(lev);
                $("#hCity").attr("data-id", $(this).data("id"));
            }
            Iput.colse();
        });
    });


    $("#_citysheng li").click(function () {
        $("#_citysheng li").removeClass("citySel");
        $(this).addClass("citySel");
        var s = $("#_citysheng li").index(this);
        $("._citys1").hide();
        $("._citys1:eq(" + s + ")").show();
    });
}


//获得某个省的所有城市
function getCity(obj) {
    var c = obj.data('id');
    var e = city;
    var f = [];
    var g = '';
    for (var i = 0, plen = e.length; i < plen; i++) {
        if (e[i]['pid'] == parseInt(c)) {
            f.push(e[i]);
        }
    }
    for (var j = 0, clen = f.length; j < clen; j++) {
        g += '<a data-level="1" data-id="' + f[j]['id'] + '" data-name="' + f[j]['name'] + '" title="' + f[j]['name'] + '">' + f[j]['name'] + '</a>'
    }
    $("#_citysheng li").removeClass("citySel");
    $("#_citysheng li:eq(1)").addClass("citySel");
    return g;
}