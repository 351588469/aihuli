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
					
					<form action="ernurse/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ERNURSE_ID" id="ERNURSE_ID" value="${pd.ERNURSE_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人编号:</td>
								<td><input type="text" name="ERN_E_ID" id="ERN_E_ID" value="${pd.ERN_E_ID}" maxlength="32" placeholder="这里输入老人编号" title="老人编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">记录职工编号:</td>
								<td><input type="text" name="ERN_GMU_ID" id="ERN_GMU_ID" value="${pd.ERN_GMU_ID}" maxlength="32" placeholder="这里输入记录职工编号" title="记录职工编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">护理计划编号:</td>
								<td><input type="text" name="ERN_ENP_ID" id="ERN_ENP_ID" value="${pd.ERN_ENP_ID}" maxlength="32" placeholder="这里输入护理计划编号" title="护理计划编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">护理次数:</td>
								<td><input type="number" name="ERN_COUNT" id="ERN_COUNT" value="${pd.ERN_COUNT}" maxlength="32" placeholder="这里输入护理次数" title="护理次数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">客户端记录时间:</td>
								<td><input class="span10 date-picker" name="ERN_GTIME" id="ERN_GTIME" value="${pd.ERN_GTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="客户端记录时间" title="客户端记录时间" style="width:98%;"/></td>
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
			if($("#ERN_E_ID").val()==""){
				$("#ERN_E_ID").tips({
					side:3,
		            msg:'请输入老人编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ERN_E_ID").focus();
			return false;
			}
			if($("#ERN_GMU_ID").val()==""){
				$("#ERN_GMU_ID").tips({
					side:3,
		            msg:'请输入记录职工编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ERN_GMU_ID").focus();
			return false;
			}
			if($("#ERN_ENP_ID").val()==""){
				$("#ERN_ENP_ID").tips({
					side:3,
		            msg:'请输入护理计划编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ERN_ENP_ID").focus();
			return false;
			}
			if($("#ERN_COUNT").val()==""){
				$("#ERN_COUNT").tips({
					side:3,
		            msg:'请输入护理次数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ERN_COUNT").focus();
			return false;
			}
			if($("#ERN_GTIME").val()==""){
				$("#ERN_GTIME").tips({
					side:3,
		            msg:'请输入客户端记录时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ERN_GTIME").focus();
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