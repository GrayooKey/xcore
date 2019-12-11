/**
 * 功能: 多图上传
 * 注: 该文件适用于页面存在多个多图上传的情况
 *
 * 版本: 1.0
 * 日期: 2019/7/18
 * 编辑者: xuezb
 */

let uploaderManyPhoto;	//Web Uploader实例


let manyPhotoDiv = $(".ct_uploadManyPhoto_plus"),    //整个上传区域
	manyPhotoList = $('<ul class="photoList"></ul>').appendTo(manyPhotoDiv.find(".queueList")),   //将图片列表ul 放到 虚线方框区域中
	statusBar = manyPhotoDiv.find(".statusBar"),         //状态显示条
	info = statusBar.find(".info"),                     //信息提示
	uploadBtn = manyPhotoDiv.find(".uploadBtn"),         //上传按钮
	manyPlaceholder = manyPhotoDiv.find(".placeholder"),     //选择图片输入
	// progress = statusBar.find(".progress").hide(),   //进度条
	manyStateSign = "";      //状态标记


jQuery(function() {

	// 初始化Web Uploader
	uploaderManyPhoto = WebUploader.create({
		// swf文件路径
		swf: '/common/plugins/webUploader/swf/Uploader.swf',
		// 上传并发数, 允许同时最大上传进程数
		threads :10,
		// 指定选择文件的按钮容器, 不指定则不创建按钮, 可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick: {
			id: ".filePickerMany",		//指定选择文件的按钮容器，不指定则不创建按钮。
			innerHTML : "选择图片"	//指定按钮文字。不指定时优先从指定的容器中看是否自带文字。
		},
		// 验证文件总数量, 超出则不允许加入队列。此处默认是 4
		//fileNumLimit: typeof(limitManyPhotoNum)!="undefined" ? limitManyPhotoNum : 4,
		// 验证单个文件大小是否超出限制, 超出则不允许加入队列。此处默认是 2*1024*1024 单位:Bytes  (即上传限制默认为2MB)
		fileSingleSizeLimit: typeof(limitManyPhotoSize)!="undefined" ? limitManyPhotoSize : 2*1024*1024,
		// 指定接受哪些类型的文件, 可选
		accept: {
			title: 'Images',                    //文字描述
			extensions: 'gif,jpg,jpeg,bmp,png', //允许的文件后缀，不带点，多个用逗号分割。
			mimeTypes: 'image/*'                //多个用逗号分割
		},
		// 图片在上传前是否进行压缩
		compress: false
	});


	// 添加文件选择按钮，如果一个按钮不够，需要调用此方法来添加
	uploaderManyPhoto.addButton({
		id: ".filePickerAdd",
		innerHTML: "继续添加"
	});


	// 当文件被加入队列之前触发，此事件的handler返回值为false，则此文件不会被添加进入队列。
	uploaderManyPhoto.on('beforeFileQueued', function(file) {
		let $manyPhotoList = getManyPhotoListObj(file);
		let index = $manyPhotoList.find("li").size();	//图片上传列表中已有图片数量(index)

		//限制文件上传数量(包括已上传和待上传), 若编辑页面无指定(limitManyPhotoNum), 则默认是4
		if(typeof(limitManyPhotoNum)!="undefined" ? index>=limitManyPhotoNum : index>=4){
			parent.layer.msg('注:此处上传的图片数量不能超过'+ (typeof(limitManyPhotoNum)!="undefined" ? limitManyPhotoNum : 4) +'张！', {icon: 0});
			return false;
		}

		// 限制单个文件的大小, 超出则不允许加入队列。此处默认是 2*1024*1024 单位:Bytes  (即上传限制默认为2MB)
		if(typeof(limitManyPhotoSize)!="undefined" ? file.size>limitManyPhotoSize : file.size>2*1024*1024){
			parent.layer.msg('注:此处上传的单张图片大小不能超过'+  (typeof(limitManyPhotoSize)!="undefined" ? WebUploader.formatSize(limitManyPhotoSize) : WebUploader.formatSize(2*1024*1024))  +'！', {icon: 0});
			return false;
		}
	});


	// 当文件被加入队列以后触发
	uploaderManyPhoto.on('fileQueued', function(file){
		let $manyPhotoList = getManyPhotoListObj(file);

		areaState_many($manyPhotoList, "ready");	//各区域状态改变
		addFile_many(file);  			//增加图片
	});
});



// 各区域状态改变 (参数: 当前操作的图片列表ul对象(jQuery对象), 当前状态)
function areaState_many($manyPhotoList, state){
	manyStateSign = state;		//状态标记值改变
	switch (state){
		case "ready":			//上传队列或图片列表中有图片的状态
			$manyPhotoList.siblings(".placeholder").addClass("element-invisible"),				//隐藏 选择图片输入区
			$manyPhotoList.show(),										//显示 图片列表ul
			$manyPhotoList.parent().siblings(".statusBar").find(".filePickerAdd").removeClass("element-invisible"),	//显示 '继续添加'按钮
			$manyPhotoList.parent().siblings(".statusBar").removeClass("element-invisible"),				//显示 状态显示条
			uploaderManyPhoto.refresh();										//刷新容器(该方法是webuploader自带的刷新方法)
			break;
		case "empty":			//当所有文件移除后的状态
			$manyPhotoList.siblings(".placeholder").removeClass("element-invisible"),			//显示 选择图片输入区
			$manyPhotoList.hide(),										//隐藏 图片列表ul
			$manyPhotoList.parent().siblings(".statusBar").find(".filePickerAdd").addClass("element-invisible"),		//隐藏'继续添加'按钮
			$manyPhotoList.parent().siblings(".statusBar").addClass("element-invisible"),				//隐藏 状态显示条
			uploaderManyPhoto.refresh();										//刷新容器(该方法是webuploader自带的刷新方法)
			break;
		case "confirm":			//完成上传后的状态
			//uploadBtn.text("开始上传").addClass("disabled");			//置灰 上传按钮
			break;
	}
}


// 增加图片 (参数: 新选择的文件(为未上传), 已上传的文件, 已上传文件的图片路径, 已上传文件的附件id, 已上传的文件所在的图片列表ul对象[jQuery对象])
function addFile_many(selectFile,file,src,id,$manyPhotoList){
	let li = $('<li><a href="#" id="pShow"><p class="imgWrap"></p></a></li>'),          //文件列表ul下 单个图片容器li
		pd = $('<div class="file-panel"><span class="cancel">删除</span><span class="rotateRight">下载</span></div>').appendTo(li),  //图片右上角的删除标志
		imgP = li.find("p.imgWrap"),		//img标签的父级容器
		imgShow = li.find("#pShow");		//img标签的父父级容器


	//如果是新选择的文件, 图片路径src不存在, 建立一个可存取到当前选中文件file的url
	if(src==null||src==""){
		let objUrl = getObjectURL(selectFile) ;     //建立一个可存取到该file的url, 以便选择图片后马上预览
		if (objUrl) {
			imgP.empty().append($('<img src="'+objUrl+'" width="146px" height="146px" />'));	//添加img
		}
		// 文件状态变化时触发
		selectFile.on("statuschange", function(imgP, uploaderManyPhoto) {
			if(imgP=="complete")   //complete 属性可返回浏览器是否已完成对图像的加载。如果加载完成，则返回 true，否则返回 fasle。[这里是判断图片是否上传成功]
				li.append('<span class="success"></span>');	//给已上传的图片右下角添加一个蓝底的打勾标志
		});

		imgShow.attr("style","cursor:pointer");
		imgShow.attr("data-gallery","");
		imgShow.attr("title",selectFile.name);
		imgShow.attr("href",objUrl);

		// 图片右上角的删除标志点击事件
		pd.on("click", ".cancel", function() {
			on_delTempAttach_many(li, selectFile);	//删除待上传附件
		});

		$manyPhotoList = getManyPhotoListObj(selectFile);

	}else{      //对已上传的图片进行加载
		imgShow.attr("style","cursor:pointer");
		imgShow.attr("data-gallery","");
		imgShow.attr("title",file.attachName);
		imgShow.attr("href",src);

		imgP.empty().append($('<img id="'+id+'" class="alreadyUploaded" src="'+src+'" width="146px" height="146px" />'));
		imgShow.append('<span class="success"></span>');

		// 图片右上角的删除标志点击事件
		pd.on("click", ".cancel", function() {
			on_delAttach_many(li, file);	//删除已上传附件
		});

		let downloadUrl;
		//根据 attachType 判断附件保存在 "um_attach"表 或 "sub_attach"表
		if (typeof (attachType) != "undefined" && attachType == "urmsAttach") {
			downloadUrl = '/urms/attach/attach_downLoad?attachId='+id;     //附件下载url
		} else {
			downloadUrl = '/subsystem/subAttach/subAttach_downLoad?attachId='+id;     //附件下载url
		}

		// 图片右上角的下载标志点击事件
		pd.on("click", ".rotateRight", function() {
			location.href = downloadUrl;
		});
	}

	//鼠标滑过 显示删除下载标志
	li.on("mouseenter", function() {
		pd.stop().animate({
			height: 25
		});
	}),
	//鼠标移开 隐藏删除下载标志
	li.on("mouseleave", function() {
		pd.stop().animate({
			height: 0
		});
	});

	//将单个图片容器li 放到 文件列表ul 中 (即放到虚线框的显示区域)
	$manyPhotoList.append(li);


	//changeInfo();	//更新信息提示
}


					// 更新信息提示 (如选中文件数量photoNum,文件总大小photoSizes)
					function changeInfo() {
						let msg = "";
						if(manyStateSign === "ready"){
							msg = "已选择" + photoNum + "张图片，共" + WebUploader.formatSize(photoSizes) + "。"
						}
						info.html(msg);
					}


//建立一个可存取到该file的url, 以便选择图片后马上预览(参数: 新选择的文件[为未上传])
function getObjectURL(file) {
	let url = null ;
	if (window.createObjectURL!=undefined) { 	// basic
		url = window.createObjectURL(file.source.getSource()) ;
	} else if (window.URL!=undefined) { 		// mozilla(firefox)
		url = window.URL.createObjectURL(file.source.getSource());
	} else if (window.webkitURL!=undefined) { 	// webkit or chrome
		url = window.webkitURL.createObjectURL(file.source.getSource()) ;
	}
	return url ;
}


//获得附件 (根据附件ids获取已上传附件)
function queryManyPhoto(attachIds){
	let attachList = new Array();

	if (attachIds != null && attachIds != "") {
		let queryUrl;
		//根据 attachType 判断附件保存在 "um_attach"表 或 "sub_attach"表
		if (typeof (attachType) != "undefined" && attachType == "urmsAttach") {
			queryUrl = '/urms/attach/attach_getAttachListByIds?ids=' + attachIds;   //附件查询url
		} else {
			queryUrl = '/subsystem/subAttach/subAttach_getAttachListByIds?ids=' + attachIds;   //附件查询url
		}

		$.ajax({
			type: 'post',
			async: false,
			dataType: 'json',
			contentType: "application/json;charset=utf-8",
			url: queryUrl,
			success: function (data) {
				for (let i = 0; i < data.attachList.length; i++) {
					attachList.push(data.attachList[i]);
				}
				return attachList;
			}
		});
	}

	return JSON.stringify(attachList);
}


//删除_已上传附件 (参数: li对象[单个图片容器], 已上传附件)
function on_delAttach_many(obj, file){
	let url;
	//根据 attachType 判断附件保存在 "um_attach"表 或 "sub_attach"表
	if (typeof (attachType) != "undefined" && attachType == "urmsAttach") {
		url = '/urms/attach/attach_deleteAttachByIds?ids='+file.id;
	} else {
		url = '/subsystem/subAttach/subAttach_deleteAttachByIds?ids='+file.id;
	}

	parent.layer.confirm("确定删除图片吗？", {
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
					let photoList = obj.parent();
					let $manyPhotoList = $(photoList);  //DOM对象转换成jQuery对象

					obj.remove();			//删除页面元素li

					//各区域状态改变
					if ($manyPhotoList.find("li").length > 0) {
						areaState_many($manyPhotoList, "ready");
					} else {
						areaState_many($manyPhotoList,"empty");
					}

					//changeInfo();	//更新信息提示

					parent.layer.msg('删除图片成功！', {icon: 1});
				}else{
					parent.layer.msg('删除图片失败！', {icon: 2});
				}
			},
		});
	});
}


//删除_待上传附件 (参数: li对象[单个图片容器], 待上传的文件对象)
function on_delTempAttach_many(obj, selectFile){
	parent.layer.confirm("确定删除图片吗？", {
		btn: ["确定","取消"] //按钮
	}, function(){
		let photoList = obj.parent();
		let $manyPhotoList = $(photoList);  //DOM对象转换成jQuery对象

		obj.remove();			//删除页面元素li

		uploaderManyPhoto.removeFile(selectFile,true);	//删除上传队列中的文件

		//各区域状态改变
		if ($manyPhotoList.find("li").length > 0) {
			areaState_many($manyPhotoList, "ready");
		} else {
			areaState_many($manyPhotoList,"empty");
		}

		//changeInfo();	//更新信息提示

		parent.layer.msg('删除图片成功！', {icon: 1});
	});
}


//根据 file 对象 获取当前操作的图片列表ul对象(jQuery对象)
function getManyPhotoListObj(file) {
	let selectBtnDivId = "rt_" + file.source.ruid;  //'选择图片'按钮容器id 或 '继续添加'按钮容器id

	let photoList;
	let len = $("#" + selectBtnDivId).parent().parent().siblings(".photoList").length;

	//len大于0, 则说明是 点击'选择图片'添加图片, 否则是 点击'继续添加'添加图片
	if (len > 0) {
		photoList = $("#" + selectBtnDivId).parent().parent().siblings(".photoList").get(0);    //图片列表ul对象
	} else {
		photoList = $("#" + selectBtnDivId).parent().parent().parent().siblings(".queueList").find(".photoList").get(0);    //图片列表ul对象
	}

	let $manyPhotoList = $(photoList);  //DOM对象转换成jQuery对象
	return $manyPhotoList;
}