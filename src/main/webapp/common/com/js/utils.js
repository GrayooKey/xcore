/*****************************************************************
 * 描述: 该文件主要包含一些常用的不依赖于系统底层的js工具类方法
 ****************************************************************/

/**
 * 数字增长显示
 * @param elem 目标元素
 * @param endVal 目标值
 * @param startVal 开始值
 * @param duration 时长
 * @param decimal
 */
function countUp(elem, endVal, startVal, duration, decimal) {
    var startTime = 0;
    var dec = Math.pow(10, decimal);
    var progress,value;
    function startCount(timestamp) {
        if(!startTime) startTime = timestamp;
        progress = timestamp - startTime;
        value = startVal + (endVal - startVal) * (progress / duration);
        value = (value > endVal) ? endVal : value;
        value = Math.floor(value*dec) / dec;
        elem.innerHTML = value.toFixed(decimal);
        progress < duration && requestAnimationFrame(startCount)
    }
    requestAnimationFrame(startCount)
}

/**
 * 身份证号获取年龄
 * @param identityCard
 * @returns {number}
 */
function getAge(identityCard) {
    var len = (identityCard + "").length;
    if (len == 0) {
        return 0;
    } else {
        if ((len != 15) && (len != 18))//身份证号码只能为15位或18位其它不合法
        {
            return 0;
        }
    }
    var strBirthday = "";
    if (len == 18)//处理18位的身份证号码从号码中得到生日和性别代码
    {
        strBirthday = identityCard.substr(6, 4) + "/" + identityCard.substr(10, 2) + "/" + identityCard.substr(12, 2);
    }
    if (len == 15) {
        strBirthday = "19" + identityCard.substr(6, 2) + "/" + identityCard.substr(8, 2) + "/" + identityCard.substr(10, 2);
    }
    //时间字符串里，必须是“/”
    var birthDate = new Date(strBirthday);
    var nowDateTime = new Date();
    var age = nowDateTime.getFullYear() - birthDate.getFullYear();
    //再考虑月、天的因素;.getMonth()获取的是从0开始的，这里进行比较，不需要加1
    if (nowDateTime.getMonth() < birthDate.getMonth() || (nowDateTime.getMonth() == birthDate.getMonth() && nowDateTime.getDate() < birthDate.getDate())) {
        age--;
    }
    return age;
}

/********************************************** 日期时间格式化处理 Start ************************************************/

/**
 * 获取当前日期时间,格式为: "HH:MM:SS"
 * @returns {string}
 */
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var strHours = date.getHours();
    var strMinutes = date.getMinutes();
    var strSeconds = date.getSeconds();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (strMinutes >= 0 && strMinutes <= 9) {
        strMinutes = "0" + strMinutes;
    }
    if (strSeconds >= 0 && strSeconds <= 9) {
        strSeconds = "0" + strSeconds;
    }
    // var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
    //     + " " + date.getHours() + seperator2 + date.getMinutes()
    //     + seperator2 + date.getSeconds();
    var currentdate =  strHours + seperator2 + strMinutes
        + seperator2 + strSeconds;
    return currentdate;
}

/**
 * 将传入的毫秒参数转化为"xx天xx小时xx分钟xx秒"格式的字符串
 * @param msd  两个日期时间相差的毫秒数
 * @returns {String}
 */
function millisecondToDate(msd) {
    var time = msd / 1000;
    if (null != time && "" != time) {
        if (time > 60 && time < 60 * 60) {
            time = parseInt(time / 60.0) + "分" + parseInt((parseFloat(time / 60.0) -
                parseInt(time / 60.0)) * 60) + "秒";
        }
        else if (time >= 60 * 60 && time < 60 * 60 * 24) {
            time = parseInt(time / 3600.0) + "小时" + parseInt((parseFloat(time / 3600.0) -
                parseInt(time / 3600.0)) * 60) + "分" +
                parseInt((parseFloat((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60) -
                    parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60)) * 60) + "秒";
        }else if(time >= 60 * 60 * 24){
            var days=Math.floor(time/(24*3600));
            //计算出小时数
            var leave1=time%(24*3600);    //计算天数后剩余的毫秒数
            var hours=Math.floor(leave1/(3600));
            //计算相差分钟数
            var leave2=leave1%(3600);        //计算小时数后剩余的毫秒数
            var minutes=Math.floor(leave2/(60));
            //计算相差秒数
            var leave3=leave2%(60);      //计算分钟数后剩余的毫秒数
            var seconds=Math.round(leave3);
            time = days+"天 "+hours+"小时 "+minutes+"分"+seconds+"秒";
        }
        else {
            time = parseInt(time) + "秒";
        }
    }
    return time;
}

/**
 * 将传入的时间戳转化为"yyyy-MM-dd HH:mm:ss"格式的字符串
 * @param cellval 时间戳
 * @returns {String}
 */
function changeDateFormat(cellval) {
    if (cellval != null) {
        var date = new Date(parseInt(cellval));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

        return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
    }
}

/**
 * 将传入的时间戳转化为"yyyy-MM-dd"格式的字符串
 * @param cellval 时间戳
 * @returns {String}
 */
function changeDateFormat1(cellval) {
    if (cellval != null) {
        var date = new Date(parseInt(cellval));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

        return date.getFullYear() + "-" + month + "-" + currentDate;
    }
}

/**
 * 将传入的时间戳转化为"HH:mm:ss yyyy-MM-dd"格式的字符串
 * @param cellval 时间戳
 * @returns {String}
 */
function changeDateFormat2(cellval) {
    if (cellval != null) {
        var date = new Date(parseInt(cellval));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

        return  hours + ":" + minutes + ":" + seconds+" "+date.getFullYear() + "-" + month + "-" + currentDate ;
    }
}

/**
 * 将传入的日期转化为指定格式的字符串
 * @param fmt   格式化模板{String}
 * @param date  日期时间{Date}
 * @returns {String}
 */
function dateFtt(fmt,date) {
    date = new Date(date);
    var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}

/********************************************** 日期时间格式化处理 End ************************************************/


/********************************************* 生成随机数 Start ************************************************/

/**
 * 生成4位随机十六进制数字
 * @returns {String}
 */
function random4() {
    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
};

/**
 * 生成32位随机十六进制数字 (uuid)
 * @returns {String}
 */
function random32() {
    return (S4()+S4()+S4()+S4()+S4()+S4()+S4()+S4());
};

/**
 * 生成6位随机码 不包含大写字母
 * @returns {string}
 */
function random6() {
    return (((1+Math.random())*0x100000)|0).toString(16);
};

/**
 * 随机生成一定位数的密码
 * @param min 最小位数
 * @param max 最大位数
 * @returns {string}
 */
function createPassword(min,max) {
    //可以生成随机密码的相关数组
    var num = ["0","1","2","3","4","5","6","7","8","9"];
    var english = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];
    var ENGLISH = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
    var special = ["-","_","#"];
    var config = num.concat(english).concat(ENGLISH).concat(special);

    //先放入一个必须存在的
    var arr = [];
    arr.push(getOne(num));
    arr.push(getOne(english));
    arr.push(getOne(ENGLISH));
    arr.push(getOne(special));

    //获取需要生成的长度
    var len = min + Math.floor(Math.random()*(max-min+1));

    for(var i=4; i<len; i++){
        //从数组里面抽出一个
        arr.push(config[Math.floor(Math.random()*config.length)]);
    }

    //乱序
    var newArr = [];
    for(var j=0; j<len; j++){
        newArr.push(arr.splice(Math.random()*arr.length,1)[0]);
    }

    //随机从数组中抽出一个数值
    function getOne(arr) {
        return arr[Math.floor(Math.random()*arr.length)];
    }

    return newArr.join("");
}

/********************************************* 生成随机数 End ************************************************/