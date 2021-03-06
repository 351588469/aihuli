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
					
					<form action="erdrug/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ERDRUG_ID" id="ERDRUG_ID" value="${pd.ERDRUG_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">记录职工编号:</td>
								<td><input type="text" name="ERD_GMU_ID" id="ERD_GMU_ID" value="${pd.ERD_GMU_ID}" maxlength="32" placeholder="这里输入记录职工编号" title="记录职工编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">计划编号:</td>
								<td><input type="text" name="ERD_EDP_ID" id="ERD_EDP_ID" value="${pd.ERD_EDP_ID}" maxlength="32" placeholder="这里输入计划编号" title="计划编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">客户端记录时间:</td>
								<td><input class="span10 date-picker" name="ERD_GTIME" id="ERD_GTIME" value="${pd.ERD_GTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="客户端记录时间" title="客户端记录时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">记录值:</td>
								<td><input type="text" name="ERD_VALUE" id="ERD_VALUE" value="${pd.ERD_VALUE}" maxlength="10" placeholder="这里输入记录值" title="记录值" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">记录说明:</td>
								<td><input type="text" name="ERD_DESC" id="ERD_DESC" value="${pd.ERD_DESC}" maxlength="255" placeholder="这里输入记录说明" title="记录说明" style="width:98%;"/></td>
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
			if($("#ERD_GMU_ID").val()==""){
				$("#ERD_GMU_ID").tips({
					side:3,
		            msg:'请输入记录职工编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ERD_GMU_ID").focus();
			return false;
			}
			if($("#ERD_EDP_ID").val()==""){
				$("#ERD_EDP_ID").tips({
					side:3,
		            msg:'请输入计划编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ERD_EDP_ID").focus();
			return false;
			}
			if($("#ERD_GTIME").val()==""){
				$("#ERD_GTIME").tips({
					side:3,
		            msg:'请输入客户端记录时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ERD_GTIME").focus();
			return false;
			}
			if($("#ERD_VALUE").val()==""){
				$("#ERD_VALUE").tips({
					side:3,
		            msg:'请输入记录值',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ERD_VALUE").focus();
			return false;
			}
			if($("#ERD_DESC").val()==""){
				$("#ERD_DESC").tips({
					side:3,
		            msg:'请输入记录说明',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ERD_DESC").focus();
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