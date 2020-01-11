<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
		<title>智慧铜仁·智慧社区-找回密码</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/common/outside/com/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/common/outside/subComm/member/forgetPassword/css/index.css" />
		<link rel="stylesheet" href="/common/outside/subComm/member/forgetPassword/css/forgetPassword.css" />
		<link rel="stylesheet" href="/common/outside/subComm/member/forgetPassword/css/gloab.css" />
		<link rel="stylesheet" href="/common/outside/com/font/iconfont.css" />
	</head>
	<body>
		<div class="onlineaffairs_content_center">
			<h1 class="onlineaffairs_title"><span class="onlineaffairs_title_left"></span>智慧社区·找回密码<span class="onlineaffairs_title_right"></span></h1>
		</div>
		<div class="login-box f-mt10 f-pb50">
			<div class="main bgf">
				<div class="reg-box-pan display-inline">
					<div class="step">
						<ul>
							<li class="col-xs-4 on">
								<span class="num"><em class="f-r5"></em><i>1</i></span>
								<span class="line_bg lbg-r"></span>
								<p class="lbg-txt">填写注册手机号码</p>
							</li>
							<li class="col-xs-4">
								<span class="num"><em class="f-r5"></em><i>2</i></span>
								<span class="line_bg lbg-l"></span>
								<span class="line_bg lbg-r"></span>
								<p class="lbg-txt">设置新密码</p>
							</li>
							<li class="col-xs-4">
								<span class="num"><em class="f-r5"></em><i>3</i></span>
								<span class="line_bg lbg-l"></span>
								<p class="lbg-txt">找回成功</p>
							</li>
						</ul>
					</div>
					<div class="reg-box" id="verifyCheck" style="margin-top:20px;">
						<div class="part1" style="display:block">
							<div class="item col-xs-12">
								<span class="intelligent-label f-fl"><b class="ftx04">*</b>手机号：</span>
								<div class="f-fl item-ifo">
									<input type="text" class="txt03 f-r3 required" keycodes="tel" tabindex="2" data-valid="isNonEmpty||isPhone" data-error="手机号码不能为空||手机号码格式不正确" maxlength="11" id="phone">
									<span class="ie8 icon-close close hide"></span>
									<label class="icon-sucessfill blank hide"></label>
									<label class="focus">请填写11位有效的手机号码</label>
									<label class="focus valid"></label>
								</div>
							</div>
							<div class="item col-xs-12">
								<span class="intelligent-label f-fl"><b class="ftx04">*</b>验证码：</span>
								<div class="f-fl item-ifo">
									<input type="text" maxlength="4" class="txt03 f-r3 f-fl required" tabindex="4" style="width:167px" id="randCode" data-valid="isNonEmpty" data-error="验证码不能为空">
									<span class="ie8 icon-close close hide"></span>
									<label class="f-size12 c-999 f-fl f-pl10">
                            						<img src="/common/outside/subComm/member/personRegister/images/yzm.jpg">                               
                            					</label>
									<label class="icon-sucessfill blank hide" style="left:380px"></label>
									<label class="focusa">看不清？<a href="javascript:;" class="c-blue">换一张</a></label>
									<label class="focus valid" style="left:370px"></label>
								</div>
							</div>
							<div class="item col-xs-12">
								<span class="intelligent-label f-fl">&nbsp;</span>
								<div class="f-fl item-ifo">
									<a href="javascript:;" class="btn btn-blue f-r3" id="btn_part1">下一步</a>
								</div>
							</div>
						</div>
						<div class="part2" style="display:none">
							<div class="popups">
								<span class="icon_wrong"><i class="icon iconfont icon-icon_wrong"></i></span>
								<div class="itemIfo">
									<i class="icon iconfont icon-duihao"></i> 手机短信已发至 <span id="phones"></span>，2分钟内有效
								</div>
								<div class="item">
									<span class="intelligent-label f-fl">短信验证码：</span>
									<div class="f-fl item-ifo">
										<input type="text" maxlength="6" id="verifyNo" class="txt03 f-r3 f-fl required" tabindex="4" style="width:167px" data-valid="isNonEmpty||isInt" data-error="验证码不能为空||请输入6位数字验证码">
									</div>
								</div>
								<div class="c-red hirt">
									<i class="icon iconfont icon-gantan"></i> 验证码已过期
								</div>
								<div class="verify-btn">
									<a class="btn btn-blue" href="#" role="button">验证</a>
								</div>
							</div>
						</div>
						<div class="part3" style="display:none">
							<div class="item col-xs-12">
								<span class="intelligent-label f-fl"><b class="ftx04">*</b>密码：</span>
								<div class="f-fl item-ifo">
									<input type="password" id="password" maxlength="20" class="txt03 f-r3 required" tabindex="3" style="ime-mode:disabled;" onpaste="return  false" autocomplete="off" data-valid="isNonEmpty||between:6-20||level:2" data-error="密码不能为空||密码长度6-20位||该密码太简单，有被盗风险，建议字母+数字的组合">
									<span class="ie8 icon-close close hide" style="right:55px"></span>
									<span class="showpwd" data-eye="password"></span>
									<label class="icon-sucessfill blank hide"></label>
									<label class="focus">6-20位英文（区分大小写）、数字、字符的组合</label>
									<label class="focus valid"></label>
									<span class="clearfix"></span>
									<label class="strength">
					                            	<span class="f-fl f-size12">安全程度：</span>
					                            	<b><i>弱</i><i>中</i><i>强</i></b>
					                            </label>
								</div>
							</div>
							<div class="item col-xs-12">
								<span class="intelligent-label f-fl"><b class="ftx04">*</b>确认密码：</span>
								<div class="f-fl item-ifo">
									<input type="password" maxlength="20" class="txt03 f-r3 required" tabindex="4" style="ime-mode:disabled;" onpaste="return  false" autocomplete="off" data-valid="isNonEmpty||between:6-16||isRepeat:password" data-error="密码不能为空||密码长度6-16位||两次密码输入不一致" id="rePassword">
									<span class="ie8 icon-close close hide" style="right:55px"></span>
									<span class="showpwd" data-eye="rePassword"></span>
									<label class="icon-sucessfill blank hide"></label>
									<label class="focus">请再输入一遍上面的密码</label>
									<label class="focus valid"></label>
								</div>
							</div>
							<div class="item col-xs-12">
								<span class="intelligent-label f-fl">&nbsp;</span>
								<div class="f-fl item-ifo">
									<a href="javascript:;" class="btn btn-blue f-r3" id="btn_part3">下一步</a>
								</div>
							</div>
						</div>
						<div class="part4 text-center" style="display:none">
							<div class="succeed_icon c-blue"><i class="icon iconfont icon-duihao"></i></div>
							<h3 class="m-t-20">恭喜您，新密码已经成功设置~</h3>
							<div class="text-center m-t-50">
								<div class="item-ifo ">
									<a href="javascript:;" class="btn btn-blue f-r3" id="btn_part4">立即登录</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="masking">

		</div>
	</body>
	<script src="/common/outside/com/js/jquery.min.js"></script>
	<script src="/common/outside/subComm/member/personRegister/js/register.js"></script>
	<script>
		$(function() {

			//第一页的确定按钮
			$("#btn_part1").click(function() {
				if(!verifyCheck._click()) return;
				$(".popups").appendTo(".masking");
				$(".masking").show();
				$(".part2").show();
				$(".step li").eq(1).addClass("on");
				var phone_val = $("#phone").val();
				$("#phones").html(phone_val)

			});
			//第二页的确定按钮
			$("#btn_part2").click(function() {
				if(!verifyCheck._click()) return;
				$(".part2").hide();
				$(".part3").show();
			});
			//第三页的确定按钮
			$("#btn_part3").click(function() {
				if(!verifyCheck._click()) return;
				$(".part3").hide();
				$(".part4").show();
				$(".step li").eq(2).addClass("on");
				//				countdown({
				//					maxTime: 10,
				//					ing: function(c) {
				//						$("#times").text(c);
				//					},
				//					after: function() {
				//						window.location.href = "my.html";
				//					}
				//				});
			});
			//点击验证关闭弹出层
			$(".verify-btn a").on("click", part3_show)
			$(".icon-icon_wrong").on("click", part2_hide)

			function part2_hide() {
				$(".masking").hide();
				$(".part2").hide();
			}

			function part3_show() {
				$(".masking").hide();
				$(".part1").hide();
				$(".part2").hide();
				$(".part3").show();
			}

		})

		function showoutc() {
			$(".m-sPopBg,.m-sPopCon").show();
		}

		function closeClause() {
			$(".m-sPopBg,.m-sPopCon").hide();
		}
	</script>

</html>