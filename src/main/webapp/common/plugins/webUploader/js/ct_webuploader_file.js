/**
 * 功能: 文件上传
 * 注: 单文件上传, 多文件上传 共用,但同一页面只能有一个文件上传
 *
 * 版本: 1.0
 * 日期: 2019/7/2
 * 编辑者: xuezb
 */

var uploaderFile;   //Web Uploader实例

var $ = jQuery,
    $list = $('#thelist'),
    $btn = $('#ctlBtn'),
    state = '';

//图片后缀
var photoSuffix = "bmp,jpg,png,jpeg,gif,JPG,BMP,PNG,JPEG,GIF";


jQuery(function() {

    // 获取附件
    queryAttach();

    // 初始化Web Uploader
    uploaderFile = WebUploader.create({
        // swf文件路径
        swf: '/common/plugins/webUploader/swf/Uploader.swf',
        //上传并发数, 允许同时最大上传进程数
        threads :10,
        // 指定选择文件的按钮容器, 不指定则不创建按钮, 可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#pickerFile',
        // 验证文件总数量, 超出则不允许加入队列。此处默认是 5
        fileNumLimit: typeof(limitFileNum)!="undefined" ? limitFileNum : 5,
        // 验证单个文件大小是否超出限制, 超出则不允许加入队列。此处默认是 5*1024*1024 单位:Bytes  (即上传限制默认为5MB)
        fileSingleSizeLimit: typeof(limitFileSingleSize)!="undefined" ? limitFileSingleSize : 5*1024*1024,
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    });



    // 当文件被加入队列之前触发，此事件的handler返回值为false，则此文件不会被添加进入队列。
    uploaderFile.on( 'beforeFileQueued', function( file ) {
        $("#attachTable #reminder").remove(); //去掉'暂无数据!'的友好提示

        var index = $("#attachTable tr").size();//文件上传列表中已有文件数量为(index-1)

        //限制文件上传数量(包括已上传和待上传), 若编辑页面无指定(limitFileNum), 则默认是5
        if(typeof(limitFileNum)!="undefined" ? index>limitFileNum : index>5){
            parent.layer.msg('注:此处上传的文件数量不能超过'+ (typeof(limitFileNum)!="undefined" ? limitFileNum : 5) +'个！', {icon: 0});
            return false;
        }

        // 限制单个文件的大小, 超出则不允许加入队列。此处默认是 5*1024*1024 单位:Bytes  (即上传限制默认为5MB)
        if(typeof(limitFileSingleSize)!="undefined" ? file.size>limitFileSingleSize : file.size>5*1024*1024){
            parent.layer.msg('注:此处上传的单个文件大小不能超过'+  (typeof(limitFileSingleSize)!="undefined" ? renderSize(limitFileSingleSize) : renderSize(5*1024*1024))  +'！', {icon: 0});
            return false;
        }
    });


    // 当文件被加入队列以后触发
    uploaderFile.on( 'fileQueued', function( file ) {
        var index = $("#attachTable tr").size();//文件上传列表中已有文件数量为(index-1), 新加入的文件序号为 index

        var tempHtm = "<tr>";
        tempHtm += "<td style='text-align: center;'>"+index+"</td>";//序号
        tempHtm += "<td style='text-align: center;'>"+file.name+"</td>";//文件名称
        tempHtm += "<td style='text-align: center;'>"+renderSize(file.size)+"</td>";//文件大小
        tempHtm += "<td style='text-align: center; color: #DD0000;'>待上传</td>";//上传状态
        tempHtm += "<td style='text-align: center;'></td>";//上传时间
        tempHtm += "<td style='text-align: center;'>";//操作
        if($("#noDelPhotoSign").length<1){//是否显示删除
            //删除
            tempHtm +="<a style='cursor:pointer' name='delAttach' id='delAttach' href='javaScript:void(0);' onclick='on_delTempAttach(this,\""+file.id+"\")'><i class='layui-icon layui-icon-delete' style='font-size: 26px; color: #dd0000; margin-right: 10px;'></i></a>";
        }
        tempHtm += "</td>";
        tempHtm += "</tr>";

        //将查询出来动态拼接的html 添加到表格中
        $("#attachTable").append(tempHtm);
    });


    // 文件上传过程中创建进度条实时显示。                                                                         [注:该方法考虑删除]
    uploaderFile.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo( $li ).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css( 'width', percentage * 100 + '%' );
    });


    // 文件上传成功                                                                                            [注:该方法考虑删除]
    uploaderFile.on( 'uploadSuccess', function( file ) {
        $( '#'+file.id ).find('p.state').text('已上传');
    });


    // 文件上传失败                                                                                            [注:该方法考虑删除]
    uploaderFile.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('上传出错');
    });


    // 文件上传完                                                                                              [注:该方法考虑删除]
    uploaderFile.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });


    // 任一事件触发 (type: 事件名)                                                                               [注:该方法考虑删除]
    uploaderFile.on( 'all', function( type ) {
        if ( type === 'startUpload' ) {             //当开始上传流程时触发
            state = 'uploading';
        } else if ( type === 'stopUpload' ) {       //当开始上传流程暂停时触发
            state = 'paused';
        } else if ( type === 'uploadFinished' ) {   //当所有文件上传结束时触发
            state = 'done';
        }

        if ( state === 'uploading' ) {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
    });


    // 开始上传按钮点击事件                                                                                       [注:该方法考虑删除]
    $btn.on( 'click', function() {
        if ( state === 'uploading' ) {
            uploaderFile.stop();        //暂停上传
        } else {
            uploaderFile.upload();      //开始上传
        }
    });
});



//获得附件 (根据实体id获取已上传附件)
function queryAttach(){
    var id = $("#id").val();    //实体id
    if(id!=null&&id!=""){
        var queryUrl;
        //根据 attachType 判断附件保存在 "um_attach"表 或 "sub_attach"表
        if (typeof (attachType) != "undefined" && attachType == "urmsAttach") {
            queryUrl = '/urms/attach/attach_getAttachListByFormId?formId='+id;   //附件查询url
        } else {
            queryUrl = '/subsystem/subAttach/subAttach_getAttachListByFormId?formId='+id;   //附件查询url
        }

        var downUrl;
        //根据 attachType 判断附件保存在 "um_attach"表 或 "sub_attach"表
        if (typeof (attachType) != "undefined" && attachType == "urmsAttach") {
            downUrl = '/urms/attach/attach_downLoad?attachId='+id;  //附件下载url
        } else {
            downUrl = '/subsystem/subAttach/subAttach_downLoad?attachId='+id;  //附件下载url
        }

        $.ajax({
            type : 'post',
            async:false,
            dataType : 'json',
            contentType:'application/json;charset=utf-8',
            url: queryUrl,
            success : function(data){
                var html = "";
                if(data.attachList.length > 0){
                    for (var i = 0; i < data.attachList.length; i++) {
                        html+="<tr style='text-align: center;'>";
                        html+="<td>"+(i+1)+"</td>";//序号
                        html+="<td>"+data.attachList[i].attachName+"</td>";//文件名称
                        html+="<td>"+renderSize(data.attachList[i].attachSize)+"</td>";//文件大小
                        html+="<td>已上传</td>";//上传状态
                        html+="<td>"+changeDate(data.attachList[i].createTime.time)+"</td>";//上传时间
                        html+="<td>";//操作
                        //如果文件的后缀表明该文件是图片格式的话,则显示图片标记,并可通过点击图片标记全屏显示图片
                        if(photoSuffix.indexOf(data.attachList[i].attachSuffix)>-1){
                            //预览
                            html+="<a style='cursor:pointer' name='seeAttach' id='seeAttach' data-gallery='' title='"+data.attachList[i].attachName+"' href='"+data.attachList[i].uploadPath+"'><i class='layui-icon layui-icon-picture' style='font-size: 21px; color: #5A84FF; margin-right: 10px;'></i></a>";
                        }
                        if($("#noDelPhotoSign").length<1){//是否显示删除
                            //删除
                            html+="<a style='cursor:pointer' name='delAttach' id='delAttach' href='javaScript:void(0);' onclick='on_delAttach(this,\""+data.attachList[i].id+"\")'><i class='layui-icon layui-icon-delete' style='font-size: 26px; color: #dd0000; margin-right: 10px;'></i></a>";
                        }
                        //下载
                        html+="<a name='downloadAttach' id='downloadAttach' href='"+downUrl+data.attachList[i].id+"'><i class='layui-icon layui-icon-download-circle' style='font-size: 22px; color: #0002FF;'></i></a>";
                        html+="</td>";
                        html+="</tr>";
                    }
                }else{
                    html += "<tr id='reminder'><td colspan='6' style='text-align: center;'>暂无数据!</td></tr>";
                }
                //将查询出来动态拼接的html 添加到表格中
                $("#attachTable").append(html);

                //动态生成图片预览区域
				$("#attachTable").after("<div id='blueimp-gallery' class='blueimp-gallery blueimp-gallery-controls'><div class='slides'></div><h3 class='title'>"+
                    "</h3><a class='prev'>‹</a><a class='next'>›</a><a class='close'>×</a><a class='play-pause'></a><ol class='indicator'></ol></div>");
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                parent.layer.msg('系统出错，请检查！', {icon: 2});
            }
        });
    } else {
        $("#attachTable").append("<tr id='reminder'><td colspan='6' style='text-align: center;'>暂无数据!</td></tr>");
    }
}

//删除_已上传附件 (参数: a标签对象, 已上传附件id)
function on_delAttach(obj,attachId){
    var url;
    //根据 attachType 判断附件保存在 "um_attach"表 或 "sub_attach"表
    if (typeof (attachType) != "undefined" && attachType == "urmsAttach") {
        url = '/urms/attach/attach_deleteAttachByIds?ids='+attachId;
    } else {
        url = '/subsystem/subAttach/subAttach_deleteAttachByIds?ids='+attachId;
    }

    parent.layer.confirm("确定删除附件吗？", {
        btn: ["确定","取消"] //按钮
    }, function(){
        $.ajax({
            type:'post',
            async:false,
            dataType : 'json',
            contentType:"application/json;charset=utf-8",
            url: url,
            success : function(data){
                if(data.result){
                    $(obj).parent().parent().remove();  //删除当前行(a标签对象的父级是td, td的父级是tr)

                    //刷新序号
                    var i = 0;
                    $("#attachTable tr").each(function(){
                        if($(this).children().get(0).innerText!="序号"){
                            $(this).children().get(0).innerText=i+1;
                            i++;
                        }
                    });

                    //判断文件列表是否为空,若为空则给个友好提示
                    if($("#attachTable tr").size()<2){
                        $("#attachTable").append("<tr id='reminder'><td colspan='6' style='text-align: center;'>暂无数据!</td></tr>");
                    }

                    parent.layer.msg('删除附件成功！', {icon: 1});
                }else{
                    parent.layer.msg('删除附件失败！', {icon: 2});
                }
            },
            error : function(result){
                parent.layer.msg('系统出错，请检查！', {icon: 2});
            }
        });
    });
}


//删除_待上传附件 (参数: a标签对象, 待上传的文件的id)
function on_delTempAttach(obj, fileId){
    parent.layer.confirm("确定删除附件吗？", {
        btn: ["确定", "取消"] //按钮
    }, function () {
        $(obj).parent().parent().remove();      //删除当前行(a标签对象的父级是td, td的父级是tr)
        uploaderFile.removeFile(fileId, true);  //移除文件,将文件从上传队列(queue)中删除

        //刷新序号
        var i = 0;
        $("#attachTable tr").each(function(){
            if($(this).children().get(0).innerText!="序号"){
                $(this).children().get(0).innerText=i+1;
                i++;
            }
        });

        //判断文件列表是否为空,若为空则给个友好提示
        if($("#attachTable tr").size()<2){
            $("#attachTable").append("<tr id='reminder'><td colspan='6' style='text-align: center;'>暂无数据!</td></tr>");
        }

        parent.layer.msg('删除成功！', {icon: 1});
    });
}


//------------------------------------------- 文件上传相关的工具类方法 --------------------------------------------------------------

/**
 * 校验文件名合法
 * @param name:附件名称(file.name)
 * @returns {boolean}
 */
function isValidFilename(name) {
    var reg = new RegExp('[\\\\/,:*?\"<>|=&%^&!]');
    if (reg.test(_name)) {
        parent.layer.msg("上传的文件名不能包含 \\\\/,:*?\"<>|=&%^&! 这些非法字符,请修改后重新上传!");
        return false;
    } else {
        return true;
    }
}

/**
 * 附件大小格式处理
 * @param value:附件大小(file.size)
 * @returns {string}
 */
function renderSize(value){
    if(null==value||value==''){
        return "0 Bytes";
    }
    var unitArr = new Array("Bytes","KB","MB","GB","TB","PB","EB","ZB","YB");
    var index=0;
    var srcsize = parseFloat(value);
    var quotient = srcsize;
    while(quotient>1024){
        index +=1;
        quotient=quotient/1024;
    }
    return roundFun(quotient,2)+" "+unitArr[index];
}

/**
 * 四舍五入保留小数位数
 * @param numberRound:被处理的数
 * @param roundDigit:保留几位小数位
 * @returns {number}
 */
function roundFun(numberRound,roundDigit){
    if(numberRound>=0) {
        var   tempNumber   =   parseInt((numberRound * Math.pow(10,roundDigit)+0.5))/Math.pow(10,roundDigit);
        return   tempNumber;
    }else{
        numberRound1=-numberRound;
        var  tempNumber   =   parseInt((numberRound1 * Math.pow(10,roundDigit)+0.5))/Math.pow(10,roundDigit);
        return   -tempNumber;
    }
}

/**
 * 时间格式化
 * @param time:毫秒数
 * @returns {string}
 */
function changeDate(time){
    var date = new Date();
    date.setTime(time);
    var hh = "0"+date.getHours();
    var mm = "0"+date.getMinutes();
    var dd = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+hh.substring(hh.length-2,hh.length)+":"+mm.substring(mm.length-2,mm.length);
    return dd;
}