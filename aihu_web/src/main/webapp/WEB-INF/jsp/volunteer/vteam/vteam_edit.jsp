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
					
					<form action="vteam/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="VTEAM_ID" id="VTEAM_ID" value="${pd.VTEAM_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
								<td><input type="text" name="VT_NAME" id="VT_NAME" value="${pd.VT_NAME}" maxlength="255" placeholder="这里输入名称" title="名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">图标:</td>
								<td><input type="text" name="VT_LOGO" id="VT_LOGO" value="${pd.VT_LOGO}" maxlength="255" placeholder="这里输入图标" title="图标" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">所在城市:</td>
								<td><input type="text" name="VT_CITY" id="VT_CITY" value="${pd.VT_CITY}" maxlength="255" placeholder="这里输入所在城市" title="所在城市" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">详细地址:</td>
								<td><input type="text" name="VT_ADDRESS" id="VT_ADDRESS" value="${pd.VT_ADDRESS}" maxlength="255" placeholder="这里输入详细地址" title="详细地址" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">描述:</td>
								<td><input type="text" name="VT_DESCRIBE" id="VT_DESCRIBE" value="${pd.VT_DESCRIBE}" maxlength="255" placeholder="这里输入描述" title="描述" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">负责人编号:</td>
								<td><input type="text" name="VT_C_ID" id="VT_C_ID" value="${pd.VT_C_ID}" maxlength="32" placeholder="这里输入负责人编号" title="负责人编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">成立时间:</td>
								<td><input class="span10 date-picker" name="VT_HTIME" id="VT_HTIME" value="${pd.VT_HTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="成立时间" title="成立时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">审核状态:</td>
								<td><input type="number" name="VT_STATUS" id="VT_STATUS" value="${pd.VT_STATUS}" maxlength="255" placeholder="这里输入描述" title="描述" style="width:98%;"/></td>
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
			if($("#VT_NAME").val()==""){
				$("#VT_NAME").tips({
					side:3,
		            msg:'请输入名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VT_NAME").focus();
			return false;
			}
	
			if($("#VT_CITY").val()==""){
				$("#VT_CITY").tips({
					side:3,
		            msg:'请输入所在城市',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VT_CITY").focus();
			return false;
			}
			if($("#VT_ADDRESS").val()==""){
				$("#VT_ADDRESS").tips({
					side:3,
		            msg:'请输入详细地址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VT_ADDRESS").focus();
			return false;
			}
			if($("#VT_DESCRIBE").val()==""){
				$("#VT_DESCRIBE").tips({
					side:3,
		            msg:'请输入描述',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VT_DESCRIBE").focus();
			return false;
			}
			if($("#VT_C_ID").val()==""){
				$("#VT_C_ID").tips({
					side:3,
		            msg:'请输入负责人编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VT_C_ID").focus();
			return false;
			}
			/*if($("#VT_HTIME").val()==""){
				$("#VT_HTIME").tips({
					side:3,
		            msg:'请输入成立时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VT_HTIME").focus();
			return false;
			}*/
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