<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="gm/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="GM_ID" id="GM_ID" value="${pd.GM_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
								<td><input type="text" name="GM_NAME" id="GM_NAME" value="${pd.GM_NAME}" maxlength="50" placeholder="这里输入名称" title="名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">头像:</td>
								<td><input type="text" name="GM_AVATER" id="GM_AVATER" value="${pd.GM_AVATER}" maxlength="100" placeholder="这里输入头像" title="头像" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">地址:</td>
								<td><input type="text" name="GM_ADDRESS" id="GM_ADDRESS" value="${pd.GM_ADDRESS}" maxlength="255" placeholder="这里输入地址" title="地址" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">联系电话:</td>
								<td><input type="text" name="GM_TEL" id="GM_TEL" value="${pd.GM_TEL}" maxlength="20" placeholder="这里输入联系电话" title="联系电话" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">联系人:</td>
								<td><input type="text" name="GM_CONCAT" id="GM_CONCAT" value="${pd.GM_CONCAT}" maxlength="10" placeholder="这里输入联系人" title="联系人" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">性质:</td>
								<td><input type="text" name="GM_NATURE" id="GM_NATURE" value="${pd.GM_NATURE}" maxlength="50" placeholder="这里输入性质" title="性质" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">营业面积:</td>
								<td><input type="text" name="GM_SQUARE" id="GM_SQUARE" value="${pd.GM_SQUARE}" maxlength="20" placeholder="这里输入营业面积" title="营业面积" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">床位数:</td>
								<td><input type="number" name="GM_BERTH_COUNT" id="GM_BERTH_COUNT" value="${pd.GM_BERTH_COUNT}" maxlength="32" placeholder="这里输入床位数" title="床位数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">简介:</td>
								<td><input type="text" name="GM_DESCRIPTION" id="GM_DESCRIPTION" value="${pd.GM_DESCRIPTION}" maxlength="255" placeholder="这里输入简介" title="简介" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">收住对象:</td>
								<td><input type="text" name="GM_RECEIVE" id="GM_RECEIVE" value="${pd.GM_RECEIVE}" maxlength="255" placeholder="这里输入收住对象" title="收住对象" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">收费标准:</td>
								<td><input type="text" name="GM_FEEDESC " id="GM_FEEDESC " value="${pd.GM_FEEDESC }" maxlength="255" placeholder="这里输入收费标准" title="收费标准" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">服务内容:</td>
								<td><input type="text" name="GM_SERVEINFO" id="GM_SERVEINFO" value="${pd.GM_SERVEINFO}" maxlength="255" placeholder="这里输入服务内容" title="服务内容" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">营业执照图片:</td>
								<td><input type="text" name="GM_LICENCE_PHOTO" id="GM_LICENCE_PHOTO" value="${pd.GM_LICENCE_PHOTO}" maxlength="255" placeholder="这里输入营业执照图片" title="营业执照图片" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">法人身份证正面:</td>
								<td><input type="text" name="GM_LEGALPERSON_PHOTOA" id="GM_LEGALPERSON_PHOTOA" value="${pd.GM_LEGALPERSON_PHOTOA}" maxlength="255" placeholder="这里输入法人身份证正面" title="法人身份证正面" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">法人身份证反面:</td>
								<td><input type="text" name="GM_LEGALPERSON_PHOTOB" id="GM_LEGALPERSON_PHOTOB" value="${pd.GM_LEGALPERSON_PHOTOB}" maxlength="255" placeholder="这里输入法人身份证反面" title="法人身份证反面" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">审核状态(1审核通过2审核未通过3待审核):</td>
								<td><input type="number" name="GM_CKSTATUS" id="GM_CKSTATUS" value="${pd.GM_CKSTATUS}" maxlength="32" placeholder="这里输入审核状态(1审核通过2审核未通过3待审核)" title="审核状态(1审核通过2审核未通过3待审核)" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">审核状态描述:</td>
								<td><input type="text" name="GM_CKDESC" id="GM_CKDESC" value="${pd.GM_CKDESC}" maxlength="50" placeholder="这里输入审核状态描述" title="审核状态描述" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建用户id(app_user):</td>
								<td><input type="text" name="GM_AU_ID" id="GM_AU_ID" value="${pd.GM_AU_ID}" maxlength="32" placeholder="这里输入创建用户id(app_user)" title="创建用户id(app_user)" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
						
					</form>
	
					<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#GM_NAME").val()==""){
				$("#GM_NAME").tips({
					side:3,
		            msg:'请输入名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_NAME").focus();
			return false;
			}
			if($("#GM_AVATER").val()==""){
				$("#GM_AVATER").tips({
					side:3,
		            msg:'请输入头像',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_AVATER").focus();
			return false;
			}
			if($("#GM_ADDRESS").val()==""){
				$("#GM_ADDRESS").tips({
					side:3,
		            msg:'请输入地址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_ADDRESS").focus();
			return false;
			}
			if($("#GM_TEL").val()==""){
				$("#GM_TEL").tips({
					side:3,
		            msg:'请输入联系电话',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_TEL").focus();
			return false;
			}
			if($("#GM_CONCAT").val()==""){
				$("#GM_CONCAT").tips({
					side:3,
		            msg:'请输入联系人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_CONCAT").focus();
			return false;
			}
			if($("#GM_NATURE").val()==""){
				$("#GM_NATURE").tips({
					side:3,
		            msg:'请输入性质',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_NATURE").focus();
			return false;
			}
			if($("#GM_SQUARE").val()==""){
				$("#GM_SQUARE").tips({
					side:3,
		            msg:'请输入营业面积',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_SQUARE").focus();
			return false;
			}
			if($("#GM_BERTH_COUNT").val()==""){
				$("#GM_BERTH_COUNT").tips({
					side:3,
		            msg:'请输入床位数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_BERTH_COUNT").focus();
			return false;
			}
			if($("#GM_DESCRIPTION").val()==""){
				$("#GM_DESCRIPTION").tips({
					side:3,
		            msg:'请输入简介',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_DESCRIPTION").focus();
			return false;
			}
			if($("#GM_RECEIVE").val()==""){
				$("#GM_RECEIVE").tips({
					side:3,
		            msg:'请输入收住对象',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_RECEIVE").focus();
			return false;
			}
			if($("#GM_FEEDESC ").val()==""){
				$("#GM_FEEDESC ").tips({
					side:3,
		            msg:'请输入收费标准',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_FEEDESC ").focus();
			return false;
			}
			if($("#GM_SERVEINFO").val()==""){
				$("#GM_SERVEINFO").tips({
					side:3,
		            msg:'请输入服务内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_SERVEINFO").focus();
			return false;
			}
			if($("#GM_LICENCE_PHOTO").val()==""){
				$("#GM_LICENCE_PHOTO").tips({
					side:3,
		            msg:'请输入营业执照图片',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_LICENCE_PHOTO").focus();
			return false;
			}
			if($("#GM_LEGALPERSON_PHOTOA").val()==""){
				$("#GM_LEGALPERSON_PHOTOA").tips({
					side:3,
		            msg:'请输入法人身份证正面',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_LEGALPERSON_PHOTOA").focus();
			return false;
			}
			if($("#GM_LEGALPERSON_PHOTOB").val()==""){
				$("#GM_LEGALPERSON_PHOTOB").tips({
					side:3,
		            msg:'请输入法人身份证反面',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_LEGALPERSON_PHOTOB").focus();
			return false;
			}
			if($("#GM_CKSTATUS").val()==""){
				$("#GM_CKSTATUS").tips({
					side:3,
		            msg:'请输入审核状态(1审核通过2审核未通过3待审核)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_CKSTATUS").focus();
			return false;
			}
			if($("#GM_CKDESC").val()==""){
				$("#GM_CKDESC").tips({
					side:3,
		            msg:'请输入审核状态描述',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_CKDESC").focus();
			return false;
			}
			if($("#GM_AU_ID").val()==""){
				$("#GM_AU_ID").tips({
					side:3,
		            msg:'请输入创建用户id(app_user)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_AU_ID").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>