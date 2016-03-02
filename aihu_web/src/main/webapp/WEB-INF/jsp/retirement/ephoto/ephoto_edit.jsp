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
					
					<form action="ephoto/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="EPHOTO_ID" id="EPHOTO_ID" value="${pd.EPHOTO_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人编号:</td>
								<td><input type="text" name="EP_E_ID" id="EP_E_ID" value="${pd.EP_E_ID}" maxlength="32" placeholder="这里输入老人编号" title="老人编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人照片存储路径:</td>
								<td><input type="text" name="EP_PATH" id="EP_PATH" value="${pd.EP_PATH}" maxlength="255" placeholder="这里输入老人照片存储路径" title="老人照片存储路径" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">照片描述:</td>
								<td><input type="text" name="EP_DESC" id="EP_DESC" value="${pd.EP_DESC}" maxlength="255" placeholder="这里输入照片描述" title="照片描述" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态(1有效3伪删除):</td>
								<td><input type="number" name="EP_STATUS" id="EP_STATUS" value="${pd.EP_STATUS}" maxlength="32" placeholder="这里输入状态(1有效3伪删除)" title="状态(1有效3伪删除)" style="width:98%;"/></td>
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
			if($("#EP_E_ID").val()==""){
				$("#EP_E_ID").tips({
					side:3,
		            msg:'请输入老人编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EP_E_ID").focus();
			return false;
			}
			if($("#EP_PATH").val()==""){
				$("#EP_PATH").tips({
					side:3,
		            msg:'请输入老人照片存储路径',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EP_PATH").focus();
			return false;
			}
			if($("#EP_DESC").val()==""){
				$("#EP_DESC").tips({
					side:3,
		            msg:'请输入照片描述',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EP_DESC").focus();
			return false;
			}
			if($("#EP_STATUS").val()==""){
				$("#EP_STATUS").tips({
					side:3,
		            msg:'请输入状态(1有效3伪删除)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EP_STATUS").focus();
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