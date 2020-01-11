<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="opt" uri="/WEB-INF/taglib/option.tld" %>
<%@ taglib prefix="fns" uri="/WEB-INF/taglib/dict.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/page/common/ct_mall_com.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>系统配置</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">系统配置</div>
                <div class="layui-card-body" pad15>

                    <div class="layui-form" wid100 lay-filter="">
                        <input type="hidden" name="id" value="${systemConfigVo.id}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">系统名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="systemName" value="${systemConfigVo.systemName}" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">地图服务地址</label>
                            <div class="layui-input-block">
                                <input type="text" name="gisServerUrl" lay-verify="url" value="${systemConfigVo.gisServerUrl}" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">初始化行政区域</label>
                            <div class="layui-input-inline" style="width: 200px;">
                                <input type="text" name="mapCode" lay-verify="number" value="${systemConfigVo.mapCode}" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">修改后需要重新登录生效</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">文件上传路径</label>
                            <div class="layui-input-block">
                                <input type="text" name="uploadPath" value="${systemConfigVo.uploadPath}" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">虚拟路径</label>
                            <div class="layui-input-inline" style="width: 200px;">
                                <input type="text" name="virtualPath" value="${systemConfigVo.virtualPath}" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">文件服务地址,相对路径请以"/"开头</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">是否单一登陆</label>
                            <div class="layui-input-inline" style="width: 200px;">
                                <opt:selectLayui dictKey="comm_yesNot" name="singleLogin" value="${systemConfigVo.singleLogin}" isDefSelect="true"/>
                            </div>
                            <div class="layui-form-mid layui-word-aux">同一账号是否只能登录一个</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">token有效时长</label>
                            <div class="layui-input-inline" style="width: 200px;">
                                <input type="text" name="tokenValidTime" lay-verify="number" value="${systemConfigVo.tokenValidTime}" class="layui-input">
                            </div>
                            <div class="layui-input-inline layui-input-company">天</div>
                            <div class="layui-form-mid layui-word-aux">有效期内免登陆</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">验证码有效期</label>
                            <div class="layui-input-inline" style="width: 200px;">
                                <input type="text" name="codeValidTime" lay-verify="number" value="${systemConfigVo.codeValidTime}" class="layui-input">
                            </div>
                            <div class="layui-input-inline layui-input-company">分钟</div>
                            <div class="layui-form-mid layui-word-aux">短信验证码有效时长</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">验证码发送间隔</label>
                            <div class="layui-input-inline" style="width: 200px;">
                                <input type="text" name="sendInterval" lay-verify="number" value="${systemConfigVo.sendInterval}" class="layui-input">
                            </div>
                            <div class="layui-input-inline layui-input-company">秒</div>
                            <div class="layui-form-mid layui-word-aux">短信验证码发送间隔</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">验证码长度</label>
                            <div class="layui-input-inline" style="width: 200px;">
                                <input type="text" name="codeLength" value="${systemConfigVo.codeLength}" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">短信验证码长度,默认为6</div>
                        </div>

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">版权信息</label>
                            <div class="layui-input-block">
                                <textarea name="copyright" class="layui-textarea">© 2019 云汀信息</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" id="button_submit" lay-submit lay-filter="ct_baseForm_submit">确认保存</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script>
    //加载layui相关模块并使用
    layui.use('form', function () {
        var form = layui.form;

        //监听submit提交
        form.on('submit(ct_baseForm_submit)', function (data) {
            saveOrUpdate(data.field);//提交表单.
        });
    });

    //提交表单
    function saveOrUpdate(data) {
        $("#button_submit").attr("disabled","disabled");
        $.ajax({
            url: '/urms/systemConfig/systemConfig_saveOrUpdate',
            type: 'post',
            async: false,
            dataType: 'json',
            data: data,
            success: function (data) {
                if (data.result) {
                    parent.layer.msg('保存成功！', {icon: 1});
                    return false;
                } else {
                    $("#button_submit").removeAttr("disabled");
                    parent.layer.msg('保存出错！', {icon: 2});
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $("#button_submit").removeAttr("disabled");
                parent.layer.msg('保存出错！', {icon: 2});
            }
        });
    }


</script>
</body>
</html>