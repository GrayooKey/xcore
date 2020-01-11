<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="opt" uri="/WEB-INF/taglib/option.tld" %>
<%@ taglib prefix="fns" uri="/WEB-INF/taglib/dict.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/WEB-INF/page/common/ct_mall_com.jsp"></jsp:include>
<%-- 多级联动地址选择 --%>
<jsp:include page="/WEB-INF/page/common/chooseAdress.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户编辑</title>
    <script src="/common/com/js/ct_layui.js"></script>
    <script src="/common/com/js/md5.js"></script>
    <script type="text/javascript">
        var currentPage = "edit";   //声明当前页面是编辑页面
    </script>

    <style>
        .layui-form-label {
            width: 126px;!important;
        }
        .layui-input-block {
            margin-left: 156px;!important;
        }

        /*身份证正反面*/
        #singleTwo img, #singleThree img {
            width: 236px;
            height: 146px;
        }

        .radio_container .layui-form-radio {
            width: 155px;
        }
    </style>
</head>
<body>
<div class="layui-fluid" style="margin-bottom: 50px;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <form class="layui-form" id="ct_baseForm_edit">
                    <%-- BaseModule start --%>
                    <input type="hidden" id="id" name="id" value="${userVo.id}"/>
                    <input type="hidden" id="dataAreaCode" name="dataAreaCode" value="${userVo.dataAreaCode}"/>
                    <input type="hidden" id="dataSource" name="dataSource" value="1"/>
                    <%-- BaseModule end --%>

                    <%-- 用户类型 --%>
                    <input type="hidden" id="userType" name="userType" value="${userVo.userType}"/>

                    <%--企业/商家ID--%>
                    <input type="hidden" id="enterpriseId" name="enterpriseId" value="${userVo.enterpriseId}"/>

                    <%-- 五级联动地址选择 start --%>
                    <input type="hidden" id="bornProvince" name="bornProvince" value="${userVo.bornProvince}"/>
                    <input type="hidden" id="bornCity" name="bornCity" value="${userVo.bornCity}"/>
                    <input type="hidden" id="bornDistrict" name="bornDistrict" value="${userVo.bornDistrict}"/>
                    <input type="hidden" id="bornStreet" name="bornStreet" value="${userVo.bornStreet}"/>
                    <input type="hidden" id="bornVillage" name="bornVillage" value="${userVo.bornVillage}"/>

                    <input type="hidden" id="liveProvince" name="liveProvince" value="${userVo.liveProvince}"/>
                    <input type="hidden" id="liveCity" name="liveCity" value="${userVo.liveCity}"/>
                    <input type="hidden" id="liveDistrict" name="liveDistrict" value="${userVo.liveDistrict}"/>
                    <input type="hidden" id="liveStreet" name="liveStreet" value="${userVo.liveStreet}"/>
                    <input type="hidden" id="liveVillage" name="liveVillage" value="${userVo.liveVillage}"/>
                    <%-- 五级联动地址选择 end --%>

                    <div class="layui-card-header"><strong>基础信息</strong><span class="layui-word-aux">（加 * 内容属于必填项）</span></div>
                    <div class="layui-card-body">
                        <div class="layui-row">
                            <div class="layui-col-xs12">
                                <div class="layui-col-xs6">
                                    <c:if test="${userVo.userType eq 2}">
                                        <div class="layui-col-xs12">
                                            <div class="layui-form-item">
                                                <label class="layui-form-label">*所属子系统</label>
                                                <div class="layui-input-block">
                                                    <c:if test="${not empty userVo.sysCode}">
                                                        <input type="hidden" id="sysCode" name="sysCode" value="${userVo.sysCode}"/>
                                                        <label class="layui-form-label" style="text-align: left; font-weight: bold;">${subsystemName}</label>
                                                    </c:if>
                                                    <c:if test="${empty userVo.sysCode}">
                                                        <select id="sysCode" name="sysCode" lay-verify="required" lay-verType="tips" lay-search>
                                                            <option value="">---请选择---</option>
                                                            <c:forEach items="${subsystemList}" var="subsystem">
                                                                <option value="${subsystem.sysCode}"
                                                                        <c:if test="${fn:contains(excludeSysCodeStr, subsystem.sysCode)}">disabled</c:if>>${subsystem.sysName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>

                                    <div class="layui-col-xs12">
                                        <div class="layui-form-item">
                                            <label class="layui-form-label">*用户名</label>
                                            <div class="layui-input-block">
                                                <input type="text" class="layui-input" id="loginName" name="loginName" value="${userVo.loginName}"
                                                       maxlength="30" lay-verify="required" lay-verType="tips" autocomplete="off"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-xs12">
                                        <div class="layui-form-item">
                                            <label class="layui-form-label">*密码</label>
                                            <div class="layui-input-block">
                                                <input type="hidden" id="password" name="password"/>
                                                <input type="password" class="layui-input" id="pwd"
                                                       minlength="6" maxlength="15" lay-verify="required|layrangelength" lay-verType="tips" autocomplete="new-password"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-xs12">
                                        <div class="layui-form-item">
                                            <label class="layui-form-label">*确认密码</label>
                                            <div class="layui-input-block">
                                                <input type="password" class="layui-input" id="pwdConfirm"
                                                       minlength="6" maxlength="15" lay-verify="required|layrangelength" lay-verType="tips" autocomplete="new-password"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-xs12">
                                        <div class="layui-form-item">
                                            <label class="layui-form-label">真实姓名</label>
                                            <div class="layui-input-block">
                                                <input type="text" class="layui-input" id="userName" name="userName" value="${userVo.userName}"
                                                       minlength="2" maxlength="50" lay-verify="layrangelength" lay-verType="tips" autocomplete="off"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-xs12">
                                        <div class="layui-form-item">
                                            <label class="layui-form-label">身份证号</label>
                                            <div class="layui-input-block">
                                                <input type="text" class="layui-input" id="idCard" name="idCard" value="${userVo.idCard}"
                                                       lay-verify="identity" lay-verType="tips" autocomplete="off"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-col-xs6">
                                    <%-- 单图上传 start--%>
                                    <div class="layui-col-xs12">
                                        <div class="layui-form-item">
                                            <label class="layui-form-label">头像</label>
                                            <div class="layui-input-block">
                                                <%--整个上传区域--%>
                                                <div id="ct_uploadPhoto" class="wu-example">
                                                    <div class="queueList" style="height: 150px;width: 150px">
                                                        <div id="dndArea" class="placeholder">
                                                            <div id="filePicker"></div>
                                                            <!-- 图片列表 -->
                                                        </div>
                                                    </div>
                                                </div>
                                                <%-- 照片预览 --%>
                                                <div id='blueimp-gallery' class='blueimp-gallery blueimp-gallery-controls'>
                                                    <div class='slides'></div>
                                                    <h3 class='title'></h3>
                                                    <a class='prev'>‹</a><a class='next'>›</a><a class='close'>×</a><a class='play-pause'></a>
                                                    <ol class='indicator'></ol>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%-- 单图上传 end--%>
                                </div>
                            </div>

                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">性别</label>
                                    <div class="layui-input-block radio_container">
                                        <opt:radioLayui dictKey="population_sex" id="sex" name="sex" value="${userVo.sex}" layVerify="" layVerType="tips"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">网咯昵称</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="nickName" name="nickName" value="${userVo.nickName}"
                                               maxlength="30" lay-verify="" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">手机号码</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="mobile" name="mobile" value="${userVo.mobile}"
                                               lay-verify="mobilephone" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">固定电话</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="officePhone" name="officePhone" value="${userVo.officePhone}"
                                               lay-verify="telephone" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">生日</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="birthday" name="birthday"
                                               value="<fmt:formatDate value='${userVo.birthday}' pattern="yyyy-MM-dd"/>" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">民族</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="population_nation" id="nation" name="nation" value="${userVo.nation}" laySearch="true" isDefSelect="true"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">文化程度/学历</label>
                                    <div class="layui-input-block">
                                        <opt:selectLayui dictKey="population_degree" id="education" name="education" value="${userVo.education}" laySearch="true" isDefSelect="true"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">电子邮箱</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="email" name="email" value="${userVo.email}"
                                               lay-verify="email" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">QQ</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="qq" name="qq" value="${userVo.qq}"
                                               lay-verify="qqnum" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>

                            <%-- 五级联动 --%>
                            <div class="layui-col-xs12">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">户籍地址</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="ct_FiveInOne"
                                               value="${fns:getAreaValue(userVo.bornProvince)}${fns:getAreaValue(userVo.bornCity)}${fns:getAreaValue(userVo.bornDistrict)}${fns:getAreaValue(userVo.bornStreet)}${fns:getAreaValue(userVo.bornVillage)}"
                                               lay-verify="" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs12">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">详细地址</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="bornAddress" name="bornAddress" value="${userVo.bornAddress}"
                                               maxlength="200" lay-verify="" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs12">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">居住地址</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="ct_FiveInTwo"
                                               value="${fns:getAreaValue(userVo.liveProvince)}${fns:getAreaValue(userVo.liveCity)}${fns:getAreaValue(userVo.liveDistrict)}${fns:getAreaValue(userVo.liveStreet)}${fns:getAreaValue(userVo.liveVillage)}"
                                               lay-verify="" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs12">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">详细地址</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="liveAddress" name="liveAddress" value="${userVo.liveAddress}"
                                               maxlength="200" lay-verify="" lay-verType="tips" autocomplete="off"/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs12">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">备注</label>
                                    <div class="layui-input-block radio_container">
                                            <textarea class="layui-textarea" id="memo" name="memo" placeholder=""
                                                      maxlength="1000" lay-verify="" lay-verType="tips">${userVo.memo}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="layui-card-header"><strong>用户状态</strong></div>
                    <div class="layui-card-body">
                        <div class="layui-row">
                            <div class="layui-col-xs6">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">状态</label>
                                    <div class="layui-input-block radio_container">
                                        <opt:radioLayui dictKey="user_state" id="state" name="state" value="${empty userVo.state ? 1 : userVo.state}" layVerify="selectrequired" layVerType="tips"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%-- 底部按钮 --%>
                    <div class="layui-form-item layui-layout-admin">
                        <div class="layui-input-block">
                            <div class="layui-footer" style="left: 0;z-index: 889;">
                                <button class="layui-btn" lay-submit lay-filter="ct_baseForm_submit"><i class="layui-icon layui-icon-ok"></i>提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary"><i class="layui-icon layui-icon-refresh"></i>重置</button>
                                <button type="button" class="layui-btn layui-btn-danger" onclick="ct_close();"><i class="layui-icon layui-icon-close"></i>取消</button>
                            </div>
                        </div>
                    </div>
                </form>

                <%-- 如果是临时用户(userType=6), 则添加扩展表信息 --%>
                <c:if test="${userVo.userType eq 6}">
                    <%@include file="/WEB-INF/page/urms/user/userExtend/userExtend_edit.jsp" %>
                </c:if>
            </div>
        </div>
    </div>
</div>

<%-- 单图上传 start --%>
<link href="/common/plugins/webUploader/css/webuploader.css" rel="stylesheet">
<link href="/common/plugins/webUploader/css/ct_webuploader_photo.css" rel="stylesheet">
<script src="/common/plugins/webUploader/js/webuploader.js"></script>
<script src="/common/plugins/webUploader/js/ct_webuploader_singlePhoto.js"></script>
<%-- 单图上传   end --%>
<script>
    var urlStr = "/urms/user/user";

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        laydate.render({
            elem: '#birthday',
            trigger: 'click'
        });
    });


    /**
     * 确认密码
     */
    function confirm() {

    }


    /*五级联动地址选择    开始*/
    var province_;
    var city_;
    var district_;
    var street_;
    var village_;

    $("#ct_FiveInOne").click(function (e) {
        province_ = "bornProvince";
        city_ = "bornCity";
        district_ = "bornDistrict";
        street_ = "bornStreet";
        village_ = "bornVillage";

        SelCity5(this, e);
    });

    $("#ct_FiveInTwo").click(function (e) {
        province_ = "liveProvince";
        city_ = "liveCity";
        district_ = "liveDistrict";
        street_ = "liveStreet";
        village_ = "liveVillage";

        SelCity5(this, e);
    });
    /*五级联动地址选择    结束*/

    
    /*身份证号 解析 其他信息*/
    $("#idCard").blur(function(){
        var idNo = $("#idCard").val();
    
        if(!idNo || idNo.length < 18) return;
    
        //获取出生日期
        var birth=idNo.substring(6, 10) + "-" + idNo.substring(10, 12) + "-" + idNo.substring(12, 14);
        $("#birthday").val(birth);
    
        // 获取户籍详址
        // var address = id_card_area.getAreaByIdCard(idNo);
        // $("#bornAddress").val(address);
        
        layui.use(['form'], function() {
            let form = layui.form;
            //获取性别
            const flag = parseInt(idNo.substr(16, 1)) % 2 == 1;
            if (flag) {
                //男
                $("input[name='sex'][value='1']").attr("checked", true);
                $("input[name='sex'][value='2']").attr("checked", false);
            } else {
                //女
                $("input[name='sex'][value='1']").attr("checked", false);
                $("input[name='sex'][value='2']").attr("checked", true);
            }
            form.render(); //更新全部
        });
    });

    /**
     * 比较两次密码输入是否一致, 并在保存之前先对密码进行第一次加密
     */
    function ct_beforeSave() {
        var pwd = $("#pwd").val();
        var pwdConfirm = $("#pwdConfirm").val();
        if (pwd != pwdConfirm) {
            parent.layer.msg('两次密码输入不一致，请检查！', {icon: 7});
            return false;
        }

        var loginName = $("#loginName").val();
        var rpwd = md5(loginName+pwd);  //对密码进行第一次加密
        $("#password").val(rpwd);
        return true;
    }


    /*------------------------------------图片上传 开始------------------------------------*/
    //var limitFileSingleSize = 6*1024*1024;    //验证单个文件大小是否超出限制, 超出则不允许加入队列。单位:Bytes
    var attachType = "urmsAttach";

    //附件上传 [可以是 单图/多图/单文件/多文件 中任意一种]
    function ct_upload(formId){
        /*--------------页面只存在一个文件上传(单文件/多文件)的情况下--------------*/
        if (uploader.getFiles().length > 0) {   //检查上传对列是否有附件
            uploader.options.server = '/urms/attach/attach_upload?formId='+formId;
            uploader.upload();
            uploader.on('uploadFinished', function(file) {
                parent.layer.msg('保存成功！', {icon: 1});
                parent.reloadTable();//刷新父级列表页面
                ct_close();//关闭当前弹出窗口
            });
        } else {
            parent.layer.msg('保存成功！', {icon: 1});
            parent.reloadTable();//刷新父级列表页面
            ct_close();//关闭当前弹出窗口
        }
    }
    /*------------------------------------图片上传 结束------------------------------------*/
</script>
</body>
</html>