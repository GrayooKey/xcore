<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>${winName}</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<%@include file="/WEB-INF/page/common/layuiHead.jsp"%>
</head>
<body class="layui-layout-body">
<div id="LAY_app">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<!-- 头部区域 -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item layadmin-flexible" lay-unselect>
					<a href="javascript:;" layadmin-event="flexible" title="侧边伸缩"> <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i></a>
				</li>
				<li class="layui-nav-item" lay-unselect>
					<a href="javascript:;" layadmin-event="refresh" title="刷新"> <i class="layui-icon layui-icon-refresh-3"></i></a>
				</li>
			</ul>
		</div>

		<!-- 侧边菜单 -->
		<div class="layui-side layui-side-menu">
			<div class="layui-side-scroll">
				<div class="layui-logo" lay-href="/subsystem/index/${menuCode}/indexHome">
					<span>${winName}</span>
				</div>
				<ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
					<c:if test="${not empty secondList}">
						<c:forEach items="${secondList}" var="secondMenu" >
							<c:if test="${menuId == secondMenu.pId }">
								<c:if test="${secondMenu.isLeaf == 1}">
									<li data-name="home" class="layui-nav-item">
										<a href="javascript:;" lay-href="${secondMenu.menuUrl}${secondMenu.menuUrl.contains('?') ? '&' : '?'}menuCode=${secondMenu.menuCode}"  lay-tips="${secondMenu.menuName}" lay-ctHref="ct_menu" lay-direction="2">
											<i class="layui-icon ${secondMenu.menuIcon}"></i>
											<cite>${secondMenu.menuName}</cite>
										</a>
									</li>
								</c:if>
								<c:if test="${secondMenu.isLeaf == 0}">
									<li data-name="home" class="layui-nav-item">
										<a href="javascript:;" lay-tips="${secondMenu.menuName}" lay-direction="2">
											<i class="layui-icon ${secondMenu.menuIcon}"></i>
											<cite>${secondMenu.menuName}</cite>
										</a>
										<dl class="layui-nav-child">
											<c:forEach items="${thirdList}" var="thirdMenu">
												<c:if test="${secondMenu.id == thirdMenu.pId }">
													<c:if test="${thirdMenu.isLeaf == 1}">
														<dd data-name="console">
															<a href="javascript:;" lay-href="${thirdMenu.menuUrl}${thirdMenu.menuUrl.contains('?') ? '&' : '?'}menuCode=${thirdMenu.menuCode}" lay-ctHref="ct_menu">${thirdMenu.menuName}</a>
														</dd>
													</c:if>
													<c:if test="${thirdMenu.isLeaf == 0}">
														<dd data-name="console">
															<li data-name="home" class="layui-nav-item">
																<a href="javascript:;" lay-tips="${thirdMenu.menuName}" lay-direction="2">
																	<i class="layui-icon layui-icon-group"></i>
																	<cite>${thirdMenu.menuName}</cite>
																</a>
																<dl class="layui-nav-child">
																	<c:forEach items="${fourList}" var="fourMenu">
																		<c:if test="${thirdMenu.id == fourMenu.pId }">
																			<c:if test="${fourMenu.isLeaf == 1}">
																				<dd data-name="console">
																					<a href="javascript:;" lay-href="${fourMenu.menuUrl}${fourMenu.menuUrl.contains('?') ? '&' : '?'}menuCode=${fourMenu.menuCode}" lay-ctHref="ct_menu">${fourMenu.menuName}</a>
																				</dd>
																			</c:if>
																			<c:if test="${fourMenu.isLeaf == 0}">
																				<dd data-name="console">
																					<li data-name="home" class="layui-nav-item">
																						<a href="javascript:;" lay-tips="${fourMenu.menuName}" lay-direction="2">
																							<cite>${fourMenu.menuName}</cite>
																						</a>
																						<dl class="layui-nav-child">
																							<c:forEach items="${fiveList}" var="fiveMenu">
																								<c:if test="${fourMenu.id == fiveMenu.pId }">
																									<dd data-name="console">
																										<a href="javascript:;" lay-href="${fiveMenu.menuUrl}${fiveMenu.menuUrl.contains('?') ? '&' : '?'}menuCode=${fiveMenu.menuCode}" lay-ctHref="ct_menu">${fiveMenu.menuName}</a>
																									</dd>
																								</c:if>
																							</c:forEach>
																						</dl>
																					</li>
																				</dd>
																			</c:if>
																		</c:if>
																	</c:forEach>
																</dl>
															</li>
														</dd>
													</c:if>
												</c:if>
											</c:forEach>
										</dl>
									</li>
								</c:if>
							</c:if>
						</c:forEach>
					</c:if>
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
						</dl>
					</li>
				</ul>
			</div>
			<div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
				<ul class="layui-tab-title" id="LAY_app_tabsheader">
					<li lay-id="/subsystem/index/${menuCode}/indexHome" lay-attr="/subsystem/index/${menuCode}/indexHome" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
				</ul>
			</div>
		</div>

		<!-- 主体内容 -->
		<div class="layui-body" id="LAY_app_body">
			<div class="layadmin-tabsbody-item layui-show">
				<iframe src="/subsystem/index/${menuCode}/indexHome" frameborder="0" class="layadmin-iframe"></iframe>
			</div>
		</div>

		<!-- 辅助元素，一般用于移动设备下遮罩 -->
		<div class="layadmin-body-shade" layadmin-event="shade"></div>
	</div>
</div>
</body>
</html>
