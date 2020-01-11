<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>图标选择</title>
		<link rel="stylesheet" href="/common/plugins/layuiadmin/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="/common/plugins/layuiadmin/style/admin.css" media="all">
		<link rel="stylesheet" type="text/css" href="/common/font/iconfont.css">
		<script src="/common/plugins/jquery/jquery-2.1.1.min.js"></script>
		<script src="/common/plugins/layuiadmin/layui/layui.js"></script>
		<style type="text/css">
			.site-doc-icon{margin-bottom:50px;font-size:0}
			.site-doc-icon li{cursor:pointer;display:inline-block;vertical-align:middle;width:150px;line-height:30px;padding:15px 0;margin-right:-1px;margin-bottom:-1px;border:1px solid #e2e2e2;font-size:12px;text-align:center;color:#666;transition:all .3s;-webkit-transition:all .3s}
			.site-doc-icon li .layui-icon{display:inline-block;font-size:36px}
			.site-doc-icon li .iconfont{display:inline-block;font-size:36px}
			.site-doc-icon li .code{text-overflow:ellipsis;white-space:nowrap;overflow:hidden}
			.site-doc-icon li:hover{background-color:#f2f2f2;color:#000}
			.icon-title{text-align:center;font-size:20px;padding-bottom:5px}
			.success{background:url(/common/plugins/webUploader/img/success.png) no-repeat right bottom}
		</style>
	</head>
	<body style="background-color: #fff;">
		<div class="layui-fluid">
			<input type="hidden" id="layui_icon_name" value="${iconName}">
			<div class="layui-row layui-col-space15">
				
				<div class="icon-list">
					<div class="icon-title" id="layui">layui图标库列表</div>
					<ul class="site-doc-icon">
						<li>
							<i class="layui-icon layui-icon-rate-half"></i>
							<div class="doc-icon-name">半星</div>
							<div class="doc-icon-fontclass code">layui-icon-rate-half</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-rate"></i>
							<div class="doc-icon-name">星星-空心</div>
							<div class="doc-icon-fontclass code">layui-icon-rate</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-rate-solid"></i>
							<div class="doc-icon-name">星星-实心</div>
							<div class="doc-icon-fontclass code">layui-icon-rate-solid</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-cellphone"></i>
							<div class="doc-icon-name">手机</div>
							<div class="doc-icon-fontclass code">layui-icon-cellphone</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-vercode"></i>
							<div class="doc-icon-name">验证码</div>
							<div class="doc-icon-fontclass code">layui-icon-vercode</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-login-wechat"></i>
							<div class="doc-icon-name">微信</div>
							<div class="doc-icon-fontclass code">layui-icon-login-wechat</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-login-qq"></i>
							<div class="doc-icon-name">QQ</div>
							<div class="doc-icon-fontclass code">layui-icon-login-qq</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-login-weibo"></i>
							<div class="doc-icon-name">微博</div>
							<div class="doc-icon-fontclass code">layui-icon-login-weibo</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-password"></i>
							<div class="doc-icon-name">密码</div>
							<div class="doc-icon-fontclass code">layui-icon-password</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-username"></i>
							<div class="doc-icon-name">用户名</div>
							<div class="doc-icon-fontclass code">layui-icon-username</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-refresh-3"></i>
							<div class="doc-icon-name">刷新-粗</div>
							<div class="doc-icon-fontclass code">layui-icon-refresh-3</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-auz"></i>
							<div class="doc-icon-name">授权</div>
							<div class="doc-icon-fontclass code">layui-icon-auz</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-spread-left"></i>
							<div class="doc-icon-name">左向右伸缩菜单</div>
							<div class="doc-icon-fontclass code">layui-icon-spread-left</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-shrink-right"></i>
							<div class="doc-icon-name">右向左伸缩菜单</div>
							<div class="doc-icon-fontclass code">layui-icon-shrink-right</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-snowflake"></i>
							<div class="doc-icon-name">雪花</div>
							<div class="doc-icon-fontclass code">layui-icon-snowflake</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-tips"></i>
							<div class="doc-icon-name">提示说明</div>
							<div class="doc-icon-fontclass code">layui-icon-tips</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-note"></i>
							<div class="doc-icon-name">便签</div>
							<div class="doc-icon-fontclass code">layui-icon-note</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-home"></i>
							<div class="doc-icon-name">主页</div>
							<div class="doc-icon-fontclass code">layui-icon-home</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-senior"></i>
							<div class="doc-icon-name">高级</div>
							<div class="doc-icon-fontclass code">layui-icon-senior</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-refresh"></i>
							<div class="doc-icon-name">刷新</div>
							<div class="doc-icon-fontclass code">layui-icon-refresh</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-refresh-1"></i>
							<div class="doc-icon-name">刷新</div>
							<div class="doc-icon-fontclass code">layui-icon-refresh-1</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-flag"></i>
							<div class="doc-icon-name">旗帜</div>
							<div class="doc-icon-fontclass code">layui-icon-flag</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-theme"></i>
							<div class="doc-icon-name">主题</div>
							<div class="doc-icon-fontclass code">layui-icon-theme</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-notice"></i>
							<div class="doc-icon-name">消息-通知</div>
							<div class="doc-icon-fontclass code">layui-icon-notice</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-website"></i>
							<div class="doc-icon-name">网站</div>
							<div class="doc-icon-fontclass code">layui-icon-website</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-console"></i>
							<div class="doc-icon-name">控制台</div>
							<div class="doc-icon-fontclass code">layui-icon-console</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-face-surprised"></i>
							<div class="doc-icon-name">表情-惊讶</div>
							<div class="doc-icon-fontclass code">layui-icon-face-surprised</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-set"></i>
							<div class="doc-icon-name">设置-空心</div>
							<div class="doc-icon-fontclass code">layui-icon-set</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-template-1"></i>
							<div class="doc-icon-name">模板</div>
							<div class="doc-icon-fontclass code">layui-icon-template-1</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-app"></i>
							<div class="doc-icon-name">应用</div>
							<div class="doc-icon-fontclass code">layui-icon-app</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-template"></i>
							<div class="doc-icon-name">模板</div>
							<div class="doc-icon-fontclass code">layui-icon-template</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-praise"></i>
							<div class="doc-icon-name">赞</div>
							<div class="doc-icon-fontclass code">layui-icon-praise</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-tread"></i>
							<div class="doc-icon-name">踩</div>
							<div class="doc-icon-fontclass code">layui-icon-tread</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-male"></i>
							<div class="doc-icon-name">男</div>
							<div class="doc-icon-fontclass code">layui-icon-male</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-female"></i>
							<div class="doc-icon-name">女</div>
							<div class="doc-icon-fontclass code">layui-icon-female</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-camera"></i>
							<div class="doc-icon-name">相机-空心</div>
							<div class="doc-icon-fontclass code">layui-icon-camera</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-camera-fill"></i>
							<div class="doc-icon-name">相机-实心</div>
							<div class="doc-icon-fontclass code">layui-icon-camera-fill</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-more"></i>
							<div class="doc-icon-name">菜单-水平</div>
							<div class="doc-icon-fontclass code">layui-icon-more</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-more-vertical"></i>
							<div class="doc-icon-name">菜单-垂直</div>
							<div class="doc-icon-fontclass code">layui-icon-more-vertical</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-rmb"></i>
							<div class="doc-icon-name">金额-人民币</div>
							<div class="doc-icon-fontclass code">layui-icon-rmb</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-dollar"></i>
							<div class="doc-icon-name">金额-美元</div>
							<div class="doc-icon-fontclass code">layui-icon-dollar</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-diamond"></i>
							<div class="doc-icon-name">钻石-等级</div>
							<div class="doc-icon-fontclass code">layui-icon-diamond</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-fire"></i>
							<div class="doc-icon-name">火</div>
							<div class="doc-icon-fontclass code">layui-icon-fire</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-return"></i>
							<div class="doc-icon-name">返回</div>
							<div class="doc-icon-fontclass code">layui-icon-return</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-location"></i>
							<div class="doc-icon-name">位置-地图</div>
							<div class="doc-icon-fontclass code">layui-icon-location</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-read"></i>
							<div class="doc-icon-name">办公-阅读</div>
							<div class="doc-icon-fontclass code">layui-icon-read</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-survey"></i>
							<div class="doc-icon-name">调查</div>
							<div class="doc-icon-fontclass code">layui-icon-survey</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-face-smile"></i>
							<div class="doc-icon-name">表情-微笑</div>
							<div class="doc-icon-fontclass code">layui-icon-face-smile</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-face-cry"></i>
							<div class="doc-icon-name">表情-哭泣</div>
							<div class="doc-icon-fontclass code">layui-icon-face-cry</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-cart-simple"></i>
							<div class="doc-icon-name">购物车</div>
							<div class="doc-icon-fontclass code">layui-icon-cart-simple</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-cart"></i>
							<div class="doc-icon-name">购物车</div>
							<div class="doc-icon-fontclass code">layui-icon-cart</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-next"></i>
							<div class="doc-icon-name">下一页</div>
							<div class="doc-icon-fontclass code">layui-icon-next</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-prev"></i>
							<div class="doc-icon-name">上一页</div>
							<div class="doc-icon-fontclass code">layui-icon-prev</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-upload-drag"></i>
							<div class="doc-icon-name">上传-空心-拖拽</div>
							<div class="doc-icon-fontclass code">layui-icon-upload-drag</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-upload"></i>
							<div class="doc-icon-name">上传-实心</div>
							<div class="doc-icon-fontclass code">layui-icon-upload</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-download-circle"></i>
							<div class="doc-icon-name">下载-圆圈</div>
							<div class="doc-icon-fontclass code">layui-icon-download-circle</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-component"></i>
							<div class="doc-icon-name">组件</div>
							<div class="doc-icon-fontclass code">layui-icon-component</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-file-b"></i>
							<div class="doc-icon-name">文件-粗</div>
							<div class="doc-icon-fontclass code">layui-icon-file-b</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-user"></i>
							<div class="doc-icon-name">用户</div>
							<div class="doc-icon-fontclass code">layui-icon-user</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-find-fill"></i>
							<div class="doc-icon-name">发现-实心</div>
							<div class="doc-icon-fontclass code">layui-icon-find-fill</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-loading layui-icon layui-anim layui-anim-rotate layui-anim-loop"></i>
							<div class="doc-icon-name">loading</div>
							<div class="doc-icon-fontclass code">layui-icon-loading</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-loading-1 layui-icon layui-anim layui-anim-rotate layui-anim-loop"></i>
							<div class="doc-icon-name">loading</div>
							<div class="doc-icon-fontclass code">layui-icon-loading-1</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-add-1"></i>
							<div class="doc-icon-name">添加</div>
							<div class="doc-icon-fontclass code">layui-icon-add-1</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-play"></i>
							<div class="doc-icon-name">播放</div>
							<div class="doc-icon-fontclass code">layui-icon-play</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-pause"></i>
							<div class="doc-icon-name">暂停</div>
							<div class="doc-icon-fontclass code">layui-icon-pause</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-headset"></i>
							<div class="doc-icon-name">音频-耳机</div>
							<div class="doc-icon-fontclass code">layui-icon-headset</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-video"></i>
							<div class="doc-icon-name">视频</div>
							<div class="doc-icon-fontclass code">layui-icon-video</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-voice"></i>
							<div class="doc-icon-name">语音-声音</div>
							<div class="doc-icon-fontclass code">layui-icon-voice</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-speaker"></i>
							<div class="doc-icon-name">消息-通知-喇叭</div>
							<div class="doc-icon-fontclass code">layui-icon-speaker</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-fonts-del"></i>
							<div class="doc-icon-name">删除线</div>
							<div class="doc-icon-fontclass code">layui-icon-fonts-del</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-fonts-code"></i>
							<div class="doc-icon-name">代码</div>
							<div class="doc-icon-fontclass code">layui-icon-fonts-code</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-fonts-html"></i>
							<div class="doc-icon-name">HTML</div>
							<div class="doc-icon-fontclass code">layui-icon-fonts-html</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-fonts-strong"></i>
							<div class="doc-icon-name">字体加粗</div>
							<div class="doc-icon-fontclass code">layui-icon-fonts-strong</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-unlink"></i>
							<div class="doc-icon-name">删除链接</div>
							<div class="doc-icon-fontclass code">layui-icon-unlink</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-picture"></i>
							<div class="doc-icon-name">图片</div>
							<div class="doc-icon-fontclass code">layui-icon-picture</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-link"></i>
							<div class="doc-icon-name">链接</div>
							<div class="doc-icon-fontclass code">layui-icon-link</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-face-smile-b"></i>
							<div class="doc-icon-name">表情-笑-粗</div>
							<div class="doc-icon-fontclass code">layui-icon-face-smile-b</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-align-left"></i>
							<div class="doc-icon-name">左对齐</div>
							<div class="doc-icon-fontclass code">layui-icon-align-left</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-align-right"></i>
							<div class="doc-icon-name">右对齐</div>
							<div class="doc-icon-fontclass code">layui-icon-align-right</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-align-center"></i>
							<div class="doc-icon-name">居中对齐</div>
							<div class="doc-icon-fontclass code">layui-icon-align-center</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-fonts-u"></i>
							<div class="doc-icon-name">字体-下划线</div>
							<div class="doc-icon-fontclass code">layui-icon-fonts-u</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-fonts-i"></i>
							<div class="doc-icon-name">字体-斜体</div>
							<div class="doc-icon-fontclass code">layui-icon-fonts-i</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-tabs"></i>
							<div class="doc-icon-name">Tabs 选项卡</div>
							<div class="doc-icon-fontclass code">layui-icon-tabs</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-radio"></i>
							<div class="doc-icon-name">单选框-选中</div>
							<div class="doc-icon-fontclass code">layui-icon-radio</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-circle"></i>
							<div class="doc-icon-name">单选框-候选</div>
							<div class="doc-icon-fontclass code">layui-icon-circle</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-edit"></i>
							<div class="doc-icon-name">编辑</div>
							<div class="doc-icon-fontclass code">layui-icon-edit</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-share"></i>
							<div class="doc-icon-name">分享</div>
							<div class="doc-icon-fontclass code">layui-icon-share</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-delete"></i>
							<div class="doc-icon-name">删除</div>
							<div class="doc-icon-fontclass code">layui-icon-delete</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-form"></i>
							<div class="doc-icon-name">表单</div>
							<div class="doc-icon-fontclass code">layui-icon-form</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-cellphone-fine"></i>
							<div class="doc-icon-name">手机-细体</div>
							<div class="doc-icon-fontclass code">layui-icon-cellphone-fine</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-dialogue"></i>
							<div class="doc-icon-name">聊天 对话 沟通</div>
							
							<div class="doc-icon-fontclass code">layui-icon-dialogue</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-fonts-clear"></i>
							<div class="doc-icon-name">文字格式化</div>
							<div class="doc-icon-fontclass code">layui-icon-fonts-clear</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-layer"></i>
							<div class="doc-icon-name">窗口</div>
							<div class="doc-icon-fontclass code">layui-icon-layer</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-date"></i>
							<div class="doc-icon-name">日期</div>
							<div class="doc-icon-fontclass code">layui-icon-date</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-water"></i>
							<div class="doc-icon-name">水 下雨</div>
							<div class="doc-icon-fontclass code">layui-icon-water</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-code-circle"></i>
							<div class="doc-icon-name">代码-圆圈</div>
							<div class="doc-icon-fontclass code">layui-icon-code-circle</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-carousel"></i>
							<div class="doc-icon-name">轮播组图</div>
							<div class="doc-icon-fontclass code">layui-icon-carousel</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-prev-circle"></i>
							<div class="doc-icon-name">翻页</div>
							<div class="doc-icon-fontclass code">layui-icon-prev-circle</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-layouts"></i>
							<div class="doc-icon-name">布局</div>
							<div class="doc-icon-fontclass code">layui-icon-layouts</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-util"></i>
							<div class="doc-icon-name">工具</div>
							<div class="doc-icon-fontclass code">layui-icon-util</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-templeate-1"></i>
							<div class="doc-icon-name">选择模板</div>
							
							<div class="doc-icon-fontclass code">layui-icon-templeate-1</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-upload-circle"></i>
							<div class="doc-icon-name">上传-圆圈</div>
							
							<div class="doc-icon-fontclass code">layui-icon-upload-circle</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-tree"></i>
							<div class="doc-icon-name">树</div>
							<div class="doc-icon-fontclass code">layui-icon-tree</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-table"></i>
							<div class="doc-icon-name">表格</div>
							<div class="doc-icon-fontclass code">layui-icon-table</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-chart"></i>
							<div class="doc-icon-name">图表</div>
							<div class="doc-icon-fontclass code">layui-icon-chart</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-chart-screen"></i>
							<div class="doc-icon-name">图标 报表 屏幕</div>
							<div class="doc-icon-fontclass code">layui-icon-chart-screen</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-engine"></i>
							<div class="doc-icon-name">引擎</div>
							
							<div class="doc-icon-fontclass code">layui-icon-engine</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-triangle-d"></i>
							<div class="doc-icon-name">下三角</div>
							<div class="doc-icon-fontclass code">layui-icon-triangle-d</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-triangle-r"></i>
							<div class="doc-icon-name">右三角</div>
							<div class="doc-icon-fontclass code">layui-icon-triangle-r</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-file"></i>
							<div class="doc-icon-name">文件</div>
							<div class="doc-icon-fontclass code">layui-icon-file</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-set-sm"></i>
							<div class="doc-icon-name">设置-小型</div>
							<div class="doc-icon-fontclass code">layui-icon-set-sm</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-add-circle"></i>
							<div class="doc-icon-name">添加-圆圈</div>
							<div class="doc-icon-fontclass code">layui-icon-add-circle</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-404"></i>
							<div class="doc-icon-name">404</div>
							<div class="doc-icon-fontclass code">layui-icon-404</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-about"></i>
							<div class="doc-icon-name">关于</div>
							<div class="doc-icon-fontclass code">layui-icon-about</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-up"></i>
							<div class="doc-icon-name">箭头 向上</div>
							<div class="doc-icon-fontclass code">layui-icon-up</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-down"></i>
							<div class="doc-icon-name">箭头 向下</div>
							<div class="doc-icon-fontclass code">layui-icon-down</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-left"></i>
							<div class="doc-icon-name">箭头 向左</div>
							<div class="doc-icon-fontclass code">layui-icon-left</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-right"></i>
							<div class="doc-icon-name">箭头 向右</div>
							<div class="doc-icon-fontclass code">layui-icon-right</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-circle-dot"></i>
							<div class="doc-icon-name">圆点</div>
							<div class="doc-icon-fontclass code">layui-icon-circle-dot</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-search"></i>
							<div class="doc-icon-name">搜索</div>
							<div class="doc-icon-fontclass code">layui-icon-search</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-set-fill"></i>
							<div class="doc-icon-name">设置-实心</div>
							<div class="doc-icon-fontclass code">layui-icon-set-fill</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-group"></i>
							<div class="doc-icon-name">群组</div>
							<div class="doc-icon-fontclass code">layui-icon-group</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-friends"></i>
							<div class="doc-icon-name">好友</div>
							<div class="doc-icon-fontclass code">layui-icon-friends</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-reply-fill"></i>
							<div class="doc-icon-name">回复 评论 实心</div>
							<div class="doc-icon-fontclass code">layui-icon-reply-fill</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-menu-fill"></i>
							<div class="doc-icon-name">菜单 隐身 实心</div>
							<div class="doc-icon-fontclass code">layui-icon-menu-fill</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-log"></i>
							<div class="doc-icon-name">记录</div>
							<div class="doc-icon-fontclass code">layui-icon-log</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-picture-fine"></i>
							<div class="doc-icon-name">图片-细体</div>
							<div class="doc-icon-fontclass code">layui-icon-picture-fine</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-face-smile-fine"></i>
							<div class="doc-icon-name">表情-笑-细体</div>
							<div class="doc-icon-fontclass code">layui-icon-face-smile-fine</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-list"></i>
							<div class="doc-icon-name">列表</div>
							<div class="doc-icon-fontclass code">layui-icon-list</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-release"></i>
							<div class="doc-icon-name">发布 纸飞机</div>
							<div class="doc-icon-fontclass code">layui-icon-release</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-ok"></i>
							<div class="doc-icon-name">对 OK</div>
							<div class="doc-icon-fontclass code">layui-icon-ok</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-help"></i>
							<div class="doc-icon-name">帮助</div>
							<div class="doc-icon-fontclass code">layui-icon-help</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-chat"></i>
							<div class="doc-icon-name">客服</div>
							<div class="doc-icon-fontclass code">layui-icon-chat</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-top"></i>
							<div class="doc-icon-name">top 置顶</div>
							<div class="doc-icon-fontclass code">layui-icon-top</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-star"></i>
							<div class="doc-icon-name">收藏-空心</div>
							<div class="doc-icon-fontclass code">layui-icon-star</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-star-fill"></i>
							<div class="doc-icon-name">收藏-实心</div>
							<div class="doc-icon-fontclass code">layui-icon-star-fill</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-close-fill"></i>
							<div class="doc-icon-name">关闭-实心</div>
							<div class="doc-icon-fontclass code">layui-icon-close-fill</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-close"></i>
							<div class="doc-icon-name">关闭-空心</div>
							<div class="doc-icon-fontclass code">layui-icon-close</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-ok-circle"></i>
							<div class="doc-icon-name">正确</div>
							<div class="doc-icon-fontclass code">layui-icon-ok-circle</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-add-circle-fine"></i>
							<div class="doc-icon-name">添加-圆圈-细体</div>
							<div class="doc-icon-fontclass code">layui-icon-add-circle-fine</div>
						</li>
					</ul>
					<div class="icon-title" id="zai-a">iconfont</div>
					<ul class="site-doc-icon">
						<li>
							<i class="layui-icon layui-icon-aixin"></i>
							<div class="doc-icon-name">爱心</div>
							<div class="doc-icon-fontclass code">layui-icon-aixin</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-bianji"></i>
							<div class="doc-icon-name">编辑</div>
							<div class="doc-icon-fontclass code">layui-icon-bianji</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-Dyanjing"></i>
							<div class="doc-icon-name">3D眼镜</div>
							<div class="doc-icon-fontclass code">layui-icon-Dyanjing</div>
						</li>
						<li>
							<i class="layui-icon layui-icon-caidan"></i>
							<div class="doc-icon-name">彩蛋</div>
							<div class="doc-icon-fontclass code">layui-icon-caidan</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-anquan"></i>
                            <div class="doc-icon-name">安全</div>
                            <div class="doc-icon-fontclass code">layui-icon-anquan</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-bangzhu"></i>
                            <div class="doc-icon-name">帮助</div>
                            <div class="doc-icon-fontclass code">layui-icon-bangzhu</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-buganxingqu"></i>
                            <div class="doc-icon-name">不感兴趣</div>
                            <div class="doc-icon-fontclass code">layui-icon-buganxingqu</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-bofangjilu"></i>
                            <div class="doc-icon-name">播放记录</div>
                            <div class="doc-icon-fontclass code">layui-icon-bofangjilu</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-chuangzuo"></i>
                            <div class="doc-icon-name">
                            创作
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-chuangzuo</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-chenggong"></i>
                            <div class="doc-icon-name">
                            成功
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-chenggong</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-ceshi"></i>
                            <div class="doc-icon-name">
                            测试
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-ceshi</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-dianhua"></i>
                            <div class="doc-icon-name">
                            电话
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-dianhua</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-dianzan"></i>
                            <div class="doc-icon-name">
                            点赞
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-dianzan</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-dingwei"></i>
                            <div class="doc-icon-name">
                            定位
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-dingwei</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-diantong_guan"></i>
                            <div class="doc-icon-name">
                            电筒_关
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-diantong_guan</div>
						</li>
						<li>
						<i class="layui-icon layui-icon-dianying"></i>
                            <div class="doc-icon-name">
                            电影
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-dianying</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-ditu"></i>
                            <div class="doc-icon-name">
                            地图
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-ditu</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-diantong_kai"></i>
                            <div class="doc-icon-name">
                            电筒_开
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-diantong_kai</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-dianyingpiao"></i>
                            <div class="doc-icon-name">
                            电影票
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-dianyingpiao</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-dingdan"></i>
                            <div class="doc-icon-name">
                            订单
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-dingdan</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-gengduo"></i>
                            <div class="doc-icon-name">
                            更多
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-gengduo</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-fanbei"></i>
                            <div class="doc-icon-name">
                            翻倍
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-fanbei</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-faxian"></i>
                            <div class="doc-icon-name">
                            发现
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-faxian</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-ertongpiao"></i>
                            <div class="doc-icon-name">
                            儿童票
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-ertongpiao</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-gongyi"></i>
                            <div class="doc-icon-name">
                            公益
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-gongyi</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-hongbao"></i>
                            <div class="doc-icon-name">
                            红包
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-hongbao</div>
						</li>
						<li>
                            <i class="layui-icon layui-icon-fuzhi"></i>
                            <div class="doc-icon-name">
                            复制
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-fuzhi
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-fenxiang"></i>
                            <div class="doc-icon-name">
                            分享
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-fenxiang
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-huatifuhao"></i>
                            <div class="doc-icon-name">
                            话题符号
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-huatifuhao
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-huiyuan"></i>
                            <div class="doc-icon-name">
                            会员
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-huiyuan
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-aliyu"></i>
                            <div class="doc-icon-name">
                            阿里鱼
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-aliyu
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-jiju"></i>
                            <div class="doc-icon-name">
                            机具
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-jiju
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-jianshao"></i>
                            <div class="doc-icon-name">
                            减少
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-jianshao
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-kabao"></i>
                            <div class="doc-icon-name">
                            卡包
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-kabao
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-huati"></i>
                            <div class="doc-icon-name">
                            话题
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-huati
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-guanzhu"></i>
                            <div class="doc-icon-name">
                            关注
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-guanzhu
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-maiyizengyi"></i>
                            <div class="doc-icon-name">
                            买一赠一
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-maiyizengyi
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-mima"></i>
                            <div class="doc-icon-name">
                            密码
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-mima
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-nan"></i>
                            <div class="doc-icon-name">
                            男
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-nan
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-nv"></i>
                            <div class="doc-icon-name">
                            女
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-nv
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-lihe"></i>
                            <div class="doc-icon-name">
                            礼盒
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-lihe
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-paihangbang"></i>
                            <div class="doc-icon-name">
                            排行榜
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-paihangbang
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-lipinka"></i>
                            <div class="doc-icon-name">
                            礼品卡
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-lipinka
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-pengyouquan"></i>
                            <div class="doc-icon-name">
                            朋友圈
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-pengyouquan
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-mingxinghuodong"></i>
                            <div class="doc-icon-name">
                            明星活动
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-mingxinghuodong
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-qupiao"></i>
                            <div class="doc-icon-name">
                            取票
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-qupiao
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-saoyisao"></i>
                            <div class="doc-icon-name">
                            扫一扫
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-saoyisao
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-rili"></i>
                            <div class="doc-icon-name">
                            日历
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-rili
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-pinglun"></i>
                            <div class="doc-icon-name">
                            评论
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-pinglun
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-qinghuiyuan"></i>
                            <div class="doc-icon-name">
                            轻会员
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-qinghuiyuan
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-riqian"></i>
                            <div class="doc-icon-name">
                            日签
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-riqian
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shandian"></i>
                            <div class="doc-icon-name">
                            闪电
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shandian
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shaixuan"></i>
                            <div class="doc-icon-name">
                            筛选
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shaixuan
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shangquan"></i>
                            <div class="doc-icon-name">
                            商圈
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shangquan
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shanchu"></i>
                            <div class="doc-icon-name">
                            删除
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shanchu
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shezhi"></i>
                            <div class="doc-icon-name">
                            设置
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shezhi
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shangchuan"></i>
                            <div class="doc-icon-name">
                            上传
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shangchuan
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shequ"></i>
                            <div class="doc-icon-name">
                            社区
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shequ
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shengyinguanbi"></i>
                            <div class="doc-icon-name">
                            声音关闭
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shengyinguanbi
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shibai"></i>
                            <div class="doc-icon-name">
                            失败
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shibai
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shijian"></i>
                            <div class="doc-icon-name">
                            时间
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shijian
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shengyin"></i>
                            <div class="doc-icon-name">
                            声音
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shengyin
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shoucang"></i>
                            <div class="doc-icon-name">
                            收藏
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shoucang
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shouji"></i>
                            <div class="doc-icon-name">
                            手机
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shouji
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shouye"></i>
                            <div class="doc-icon-name">
                            首页
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shouye
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shuju"></i>
                            <div class="doc-icon-name">
                            数据
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shuju
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-shuaxin"></i>
                            <div class="doc-icon-name">
                            刷新
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-shuaxin
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-sousuo"></i>
                            <div class="doc-icon-name">
                            搜索
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-sousuo
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-tanhao"></i>
                            <div class="doc-icon-name">
                            叹号
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-tanhao
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-taolunqu"></i>
                            <div class="doc-icon-name">
                            讨论区
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-taolunqu
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-tianjiayouhuiquan"></i>
                            <div class="doc-icon-name">
                            添加优惠券
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-tianjiayouhuiquan
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-tishi"></i>
                            <div class="doc-icon-name">
                            提示
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-tishi
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-tupian"></i>
                            <div class="doc-icon-name">
                            图片
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-tupian
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-tuipiao"></i>
                            <div class="doc-icon-name">
                            退票
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-tuipiao
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-weixuanzhong"></i>
                            <div class="doc-icon-name">
                            未选中
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-weixuanzhong
                            </div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-weibo"></i>
                            <div class="doc-icon-name">
                            微博
                            </div>
                            <div class="doc-icon-fontclass code">layui-icon-weibo
                            </div>
						</li>

						<li>
						<i class="layui-icon layui-icon-wenjuan"></i>
						<div class="doc-icon-name">
						问卷
						</div>
						<div class="doc-icon-fontclass code">layui-icon-wenjuan
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-wode"></i>
						<div class="doc-icon-name">
						我的
						</div>
						<div class="doc-icon-fontclass code">layui-icon-wode
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-weixin"></i>
						<div class="doc-icon-name">
						微信
						</div>
						<div class="doc-icon-fontclass code">layui-icon-weixin
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-wodeguanzhu"></i>
						<div class="doc-icon-name">
						我的关注
						</div>
						<div class="doc-icon-fontclass code">layui-icon-wodeguanzhu
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-xiami"></i>
						<div class="doc-icon-name">
						虾米
						</div>
						<div class="doc-icon-fontclass code">layui-icon-xiami
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-xiangkan"></i>
						<div class="doc-icon-name">
						想看
						</div>
						<div class="doc-icon-fontclass code">layui-icon-xiangkan
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-xiangji"></i>
						<div class="doc-icon-name">
						相机
						</div>
						<div class="doc-icon-fontclass code">layui-icon-xiangji
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-xiaoxi"></i>
						<div class="doc-icon-name">
						消息
						</div>
						<div class="doc-icon-fontclass code">layui-icon-xiaoxi
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-xiazai"></i>
						<div class="doc-icon-name">
						下载
						</div>
						<div class="doc-icon-fontclass code">layui-icon-xiazai
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-xiaojuchang"></i>
						<div class="doc-icon-name">
						小聚场
						</div>
						<div class="doc-icon-fontclass code">layui-icon-xiaojuchang
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-xiaoshi"></i>
						<div class="doc-icon-name">
						小食
						</div>
						<div class="doc-icon-fontclass code">layui-icon-xiaoshi
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-xiaoxizhongxin"></i>
						<div class="doc-icon-name">
						消息中心
						</div>
						<div class="doc-icon-fontclass code">layui-icon-xiaoxizhongxin
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-yinhangka"></i>
						<div class="doc-icon-name">
						银行卡
						</div>
						<div class="doc-icon-fontclass code">layui-icon-yinhangka
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-yanjing"></i>
						<div class="doc-icon-name">
						眼睛
						</div>
						<div class="doc-icon-fontclass code">layui-icon-yanjing
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-yingchengka"></i>
						<div class="doc-icon-name">
						影城卡
						</div>
						<div class="doc-icon-fontclass code">layui-icon-yingchengka
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-yixiangkan"></i>
						<div class="doc-icon-name">
						已想看
						</div>
						<div class="doc-icon-fontclass code">layui-icon-yixiangkan
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-yingpingmoban"></i>
						<div class="doc-icon-name">
						影评模板
						</div>
						<div class="doc-icon-fontclass code">layui-icon-yingpingmoban
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-yanchu"></i>
						<div class="doc-icon-name">
						演出
						</div>
						<div class="doc-icon-fontclass code">layui-icon-yanchu
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-xiaomi"></i>
						<div class="doc-icon-name">
						小蜜
						</div>
						<div class="doc-icon-fontclass code">layui-icon-xiaomi
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-yingyuan"></i>
						<div class="doc-icon-name">
						影院
						</div>
						<div class="doc-icon-fontclass code">layui-icon-yingyuan
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-zengjia"></i>
						<div class="doc-icon-name">
						增加
						</div>
						<div class="doc-icon-fontclass code">layui-icon-zengjia
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-yuyin"></i>
						<div class="doc-icon-name">
						语音
						</div>
						<div class="doc-icon-fontclass code">layui-icon-yuyin
						</div>
						</li>

						<li>
						<i class="layui-icon layui-icon-youhuiquan"></i>
						<div class="doc-icon-name">
						优惠券
						</div>
						<div class="doc-icon-fontclass code">layui-icon-youhuiquan
						</div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-WIFI"></i>
                            <div class="doc-icon-name">WIFI</div>
                            <div class="doc-icon-fontclass code">layui-icon-WIFI</div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-zhibo"></i>
                            <div class="doc-icon-name">直播</div>
                            <div class="doc-icon-fontclass code">layui-icon-zhibo</div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-youkuhuiyuan"></i>
                            <div class="doc-icon-name">优酷会员</div>
                            <div class="doc-icon-fontclass code">layui-icon-youkuhuiyuan</div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-yulebao"></i>
                            <div class="doc-icon-name">娱乐宝</div>
                            <div class="doc-icon-fontclass code">layui-icon-yulebao</div>
						</li>

						<li>
                            <i class="layui-icon layui-icon-zhiwen"></i>
                            <div class="doc-icon-name">指纹</div>
                            <div class="doc-icon-fontclass code">layui-icon-zhiwen</div>
						</li>
					</ul>
				</div>
				<div class="layui-layout-admin">
					<div class="layui-footer" style="left: 0;z-index: 889;">
						<button class="layui-btn" onclick="on_sure();"><i class="layui-icon layui-icon-ok"></i>确定
						</button>
						<button type="button" class="layui-btn layui-btn-danger" onclick="ct_close();"><i class="layui-icon layui-icon-close"></i>取消
						</button>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">

            layui.config({
                base: '/common/plugins/layuiadmin/' //静态资源所在路径
            }).extend({
                index: 'lib/index' //主入口模块
            }).use(['index'], function () {
                var $ = layui.$
                    , admin = layui.admin
                    , element = layui.element;
                element.render('nav', 'component-nav');
                element.render('nav', 'component-nav-active');
            })

            $(document).ready(function () {
                if ($("#layui_icon_name").val() != "" && $("#layui_icon_name").val() != null) {
                    let iconName = $("#layui_icon_name").val();
                    $("." + iconName).parent().addClass("success");
                }
            });

            $(".site-doc-icon li").click(function () {
                $(".site-doc-icon li").removeClass("success");
                $(this).addClass("success");
                let icon = $(this).find(".code").text();
                icon = $.trim(icon);
                $("#layui_icon_name").val(icon);
            });

            //确定
            function on_sure() {
                let iconName = $("#layui_icon_name").val();
                parent.$("#menuIcon").val(iconName);
                ct_close();
            }

            //关闭当前弹出层窗口
            function ct_close() {
                let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index);
            }
		</script>
	</body>
</html>