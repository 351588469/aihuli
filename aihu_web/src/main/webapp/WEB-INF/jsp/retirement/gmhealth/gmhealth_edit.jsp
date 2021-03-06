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
					
					<form action="gmhealth/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="GMHEALTH_ID" id="GMHEALTH_ID" value="${pd.GMHEALTH_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">养老院编号:</td>
								<td><input type="text" name="GMH_GM_ID" id="GMH_GM_ID" value="${pd.GMH_GM_ID}" maxlength="32" placeholder="这里输入养老院编号" title="养老院编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">数据类型(1单数据2双数据):</td>
								<td><input type="number" name="GMH_TYPE" id="GMH_TYPE" value="${pd.GMH_TYPE}" maxlength="32" placeholder="这里输入数据类型(1单数据2双数据)" title="数据类型(1单数据2双数据)" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">项目名称:</td>
								<td><input type="text" name="GMH_NAME" id="GMH_NAME" value="${pd.GMH_NAME}" maxlength="20" placeholder="这里输入项目名称" title="项目名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">项目描述:</td>
								<td><input type="text" name="GMH_CONTENT" id="GMH_CONTENT" value="${pd.GMH_CONTENT}" maxlength="255" placeholder="这里输入项目描述" title="项目描述" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">项目状态:</td>
								<td><input type="number" name="GMH_STATUS" id="GMH_STATUS" value="${pd.GMH_STATUS}" maxlength="32" placeholder="这里输入项目状态" title="项目状态" style="width:98%;"/></td>
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
			if($("#GMH_GM_ID").val()==""){
				$("#GMH_GM_ID").tips({
					side:3,
		            msg:'请输入养老院编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMH_GM_ID").focus();
			return false;
			}
			if($("#GMH_TYPE").val()==""){
				$("#GMH_TYPE").tips({
					side:3,
		            msg:'请输入数据类型(1单数据2双数据)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMH_TYPE").focus();
			return false;
			}
			if($("#GMH_NAME").val()==""){
				$("#GMH_NAME").tips({
					side:3,
		            msg:'请输入项目名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMH_NAME").focus();
			return false;
			}
			if($("#GMH_CONTENT").val()==""){
				$("#GMH_CONTENT").tips({
					side:3,
		            msg:'请输入项目描述',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMH_CONTENT").focus();
			return false;
			}
			if($("#GMH_STATUS").val()==""){
				$("#GMH_STATUS").tips({
					side:3,
		            msg:'请输入项目状态',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMH_STATUS").focus();
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