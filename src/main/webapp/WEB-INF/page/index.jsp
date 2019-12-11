<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>智慧铜仁·子系统管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<%--layui公共静态文件--%>
	<link rel="stylesheet" href="/common/plugins/layuiadmin/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="/common/plugins/layuiadmin/style/admin.css" media="all">
	<script src="/common/plugins/layuiadmin/layui/layui.js"></script>
	<script>
		layui.config({
			base: '/common/plugins/layuiadmin/' //静态资源所在路径
		}).extend({
			index: 'lib/index' //主入口模块
		}).use(['index','console']);
	</script>
</head>
<body class="layui-layout-body">
<div id="LAY_app">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<!-- 头部区域 -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item layadmin-flexible" lay-unselect><a href="javascript:;" layadmin-event="flexible" title="侧边伸缩"> <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
				</a></li>
				<li class="layui-nav-item layui-hide-xs" lay-unselect><a href="http://www.trcity.cn" target="_blank" title="公众网"> <i class="layui-icon layui-icon-website"></i>
				</a></li>
				<li class="layui-nav-item" lay-unselect><a href="javascript:;" layadmin-event="refresh" title="刷新"> <i class="layui-icon layui-icon-refresh-3"></i>
				</a></li>
				<li class="layui-nav-item layui-hide-xs" lay-unselect><input type="text" placeholder="搜索..." autocomplete="off" class="layui-input layui-input-search" layadmin-event="serach" lay-action="template/search.html?keywords="></li>
			</ul>
			<ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
				<li class="layui-nav-item" lay-unselect><a lay-href="app/message/index.html" layadmin-event="message" lay-text="消息中心"> <i class="layui-icon layui-icon-notice"></i> <!-- 如果有新消息，则显示小圆点 --> <span class="layui-badge-dot"></span>
				</a></li>
				<li class="layui-nav-item layui-hide-xs" lay-unselect><a href="javascript:;" layadmin-event="theme"> <i class="layui-icon layui-icon-theme"></i>
				</a></li>
				<li class="layui-nav-item layui-hide-xs" lay-unselect><a href="javascript:;" layadmin-event="fullscreen"> <i class="layui-icon layui-icon-screen-full"></i>
				</a></li>
			</ul>
		</div>

		<!-- 侧边菜单 -->
		<div class="layui-side layui-side-menu">
			<div class="layui-side-scroll">
				<div class="layui-logo" lay-href="/urms/indexHome">
					<span>智慧铜仁·系统管理</span>
				</div>
				<ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">


					<li data-name="home" class="layui-nav-item">
						<a href="javascript:;" lay-tips="测试" lay-direction="2">
							<i class="layui-icon layui-icon-home"></i>
							<cite>测试</cite>
						</a>
						<dl class="layui-nav-child">
							<dd data-name="menu">
								<a href="javascript:;" lay-href="/xcore/demo/demo_list" lay-ctHref="ct_menu">示例</a>
							</dd>
						</dl>
					</li>


					<li data-name="home" class="layui-nav-item">
						<a href="javascript:;" lay-tips="系统管理" lay-direction="2">
							<i class="layui-icon layui-icon-home"></i>
							<cite>系统管理</cite>
						</a>
						<dl class="layui-nav-child">
							<dd data-name="menu">
								<a href="javascript:;" lay-href="/urms/menu/menu_list" lay-ctHref="ct_menu">菜单管理</a>
							</dd>
							<dd data-name="category">
								<a href="javascript:;" lay-href="/urms/category/category_list" lay-ctHref="ct_menu">数据字典</a>
							</dd>
							<dd data-name="category">
								<a href="javascript:;" lay-href="/urms/areaCode/areaCode_list" lay-ctHref="ct_menu">行政区划</a>
							</dd>
							<dd data-name="console">
								<a href="javascript:;" lay-href="/urms/systemConfig/systemConfig_edit" lay-ctHref="ct_menu">系统配置</a>
							</dd>
							<dd data-name="subsystem">
								<a href="javascript:;" lay-href="/urms/subsystem/subsystem_list" lay-ctHref="ct_menu">子系统定义</a>
							</dd>
							<dd data-name="console">
								<a href="javascript:;" lay-href="/urms/user/user_list?userType=2" lay-ctHref="ct_menu">子系统管理员</a>
							</dd>
							<dd data-name="console">
								<a href="javascript:;" lay-href="/baseLib/population/population_list">数据导入</a>
							</dd>
						</dl>
					</li>
					<li data-name="systemOption" class="layui-nav-item">
						<a href="javascript:;" lay-tips="子系统配置" lay-direction="2">
							<i class="layui-icon layui-icon-home"></i>
							<cite>子系统配置</cite>
						</a>
						<dl class="layui-nav-child">
							<dd data-name="console">
								<a href="javascript:;" lay-href="/urms/theme/theme_list" lay-ctHref="ct_menu">主题配置</a>
							</dd>
							<dd data-name="console">
								<a href="javascript:;" lay-href="/urms/subsystemConfig/subsystemConfig_list" lay-ctHref="ct_menu">参数配置</a>
							</dd>
							<dd data-name="console">
								<a href="javascript:;" lay-href="/urms/subsystem/subsystem_menuTreeList" lay-ctHref="ct_menu">菜单配置</a>
							</dd>
						</dl>
					</li>
					<li data-name="orgFrame" class="layui-nav-item">
						<a href="javascript:;" lay-href="/urms/areaCode/orgFrame_list" lay-ctHref="ct_menu" lay-tips="组织机构" lay-direction="2">
							<i class="layui-icon layui-icon-home"></i>
							<cite>组织机构</cite>
						</a>
					</li>
					<li data-name="user" class="layui-nav-item">
						<a href="javascript:;" lay-tips="用户管理" lay-direction="2">
							<i class="layui-icon layui-icon-home"></i>
							<cite>用户管理</cite>
						</a>
						<dl class="layui-nav-child">
							<dd data-name="console">
								<a href="javascript:;" lay-href="/urms/user/user_list?userType=3" lay-ctHref="ct_menu">政务用户</a>
							</dd>
							<dd data-name="console">
								<a href="javascript:;" lay-href="/urms/user/user_list?userType=4" lay-ctHref="ct_menu">个人(注册会员)</a>
							</dd>
							<dd data-name="console">
								<a href="javascript:;" lay-href="/urms/user/user_list?userType=5" lay-ctHref="ct_menu">商家/企业会员</a>
							</dd>
							<dd data-name="console">
								<a href="javascript:;" lay-href="/urms/user/user_list?userType=6" lay-ctHref="ct_menu">临时用户</a>
							</dd>
						</dl>
					</li>
					<li data-name="role" class="layui-nav-item">
						<a href="javascript:;" lay-href="/urms/role/role_list" lay-ctHref="ct_menu" lay-tips="角色管理" lay-direction="2">
							<i class="layui-icon layui-icon-home"></i>
							<cite>角色管理</cite>
						</a>
					</li>
					<li data-name="right" class="layui-nav-item">
						<a href="javascript:;" lay-tips="权限管理" lay-direction="2">
							<i class="layui-icon layui-icon-component"></i>
							<cite>权限管理</cite>
						</a>
						<dl class="layui-nav-child">
							<dd data-name="console">
								<a href="javascript:;" lay-href="/urms/roleRight/roleRight_list" lay-ctHref="ct_menu">角色权限</a>
							</dd>
						</dl>
					</li>
					<li data-name="device" class="layui-nav-item">
						<a href="javascript:;" lay-tips="平台建设" lay-direction="2">
							<i class="layui-icon layui-icon-component"></i>
							<cite>平台建设</cite>
						</a>
					</li>
					<li data-name="device" class="layui-nav-item">
						<a href="javascript:;" lay-tips="问题反馈" lay-direction="2">
							<i class="layui-icon layui-icon-component"></i>
							<cite>问题反馈</cite>
						</a>
					</li>
					<li data-name="device" class="layui-nav-item">
						<a href="javascript:;" lay-tips="访问管理" lay-direction="2">
							<i class="layui-icon layui-icon-component"></i>
							<cite>访问管理</cite>
						</a>
					</li>
					<li data-name="log" class="layui-nav-item">
						<a href="javascript:;" lay-tips="登录日志" lay-direction="2" lay-href="/urms/user/loginLog/loginLog_list" lay-ctHref="ct_menu">
							<i class="layui-icon layui-icon-component"></i>
							<cite>登录日志</cite>
						</a>
					</li>
					<li data-name="api" class="layui-nav-item">
						<a href="javascript:;" lay-tips="API配置" lay-direction="2">
							<i class="layui-icon layui-icon-component"></i>
							<cite>API配置</cite>
						</a>
					</li>
				</ul>
			</div>
		</div>

		<!-- 页面标签 -->
		<div class="layadmin-pagetabs" id="LAY_app_tabs">
			<div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
			<div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
			<div class="layui-icon layadmin-tabs-control layui-icon-down">
				<ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
					<li class="layui-nav-item" lay-unselect><a href="javascript:;"></a>
						<dl class="layui-nav-child layui-anim-fadein">
							<dd layadmin-event="closeThisTabs">
								<a href="javascript:;">关闭当前标签页</a>
							</dd>
							<dd layadmin-event="closeOtherTabs">
								<a href="javascript:;">关闭其它标签页</a>
							</dd>
							<dd layadmin-event="closeAllTabs">
								<a href="javascript:;">关闭全部标签页</a>
							</dd>
						</dl></li>
				</ul>
			</div>
			<div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
				<ul class="layui-tab-title" id="LAY_app_tabsheader">
					<li lay-id="/urms/indexHome" lay-attr="/urms/indexHome" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
				</ul>
			</div>
		</div>

		<!-- 主体内容 -->
		<div class="layui-body" id="LAY_app_body">
			<div class="layadmin-tabsbody-item layui-show">
				<iframe src="/urms/indexHome" frameborder="0" class="layadmin-iframe"></iframe>
			</div>
		</div>

		<!-- 辅助元素，一般用于移动设备下遮罩 -->
		<div class="layadmin-body-shade" layadmin-event="shade"></div>
	</div>
</div>
</body>
</html>
