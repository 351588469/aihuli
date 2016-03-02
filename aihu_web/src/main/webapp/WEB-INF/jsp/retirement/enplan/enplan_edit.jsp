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
					
					<form action="enplan/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ENPLAN_ID" id="ENPLAN_ID" value="${pd.ENPLAN_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">护理项目编号:</td>
								<td><input type="text" name="ENP_GMN_ID" id="ENP_GMN_ID" value="${pd.ENP_GMN_ID}" maxlength="32" placeholder="这里输入护理项目编号" title="护理项目编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">养老院编号:</td>
								<td><input type="text" name="ENP_GM_ID" id="ENP_GM_ID" value="${pd.ENP_GM_ID}" maxlength="32" placeholder="这里输入养老院编号" title="养老院编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">计划申请职工编号:</td>
								<td><input type="text" name="ENP_GMU_ID" id="ENP_GMU_ID" value="${pd.ENP_GMU_ID}" maxlength="32" placeholder="这里输入计划申请职工编号" title="计划申请职工编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">护理老人编号:</td>
								<td><input type="text" name="ENP_E_ID" id="ENP_E_ID" value="${pd.ENP_E_ID}" maxlength="32" placeholder="这里输入护理老人编号" title="护理老人编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">单位时间需护理次数:</td>
								<td><input type="number" name="ENP_NEED" id="ENP_NEED" value="${pd.ENP_NEED}" maxlength="32" placeholder="这里输入单位时间需护理次数" title="单位时间需护理次数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">时间单位(1日2周3月):</td>
								<td><input type="number" name="ENP_UNIT" id="ENP_UNIT" value="${pd.ENP_UNIT}" maxlength="32" placeholder="这里输入时间单位(1日2周3月)" title="时间单位(1日2周3月)" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">计划开始日期:</td>
								<td><input class="span10 date-picker" name="ENP_SDATE" id="ENP_SDATE" value="${pd.ENP_SDATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="计划开始日期" title="计划开始日期" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">计划结束时间:</td>
								<td><input class="span10 date-picker" name="ENP_EDATE" id="ENP_EDATE" value="${pd.ENP_EDATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="计划结束时间" title="计划结束时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">计划状态:</td>
								<td><input type="number" name="ENP_STATUS" id="ENP_STATUS" value="${pd.ENP_STATUS}" maxlength="32" placeholder="这里输入计划状态" title="计划状态" style="width:98%;"/></td>
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
			if($("#ENP_GMN_ID").val()==""){
				$("#ENP_GMN_ID").tips({
					side:3,
		            msg:'请输入护理项目编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ENP_GMN_ID").focus();
			return false;
			}
			if($("#ENP_GM_ID").val()==""){
				$("#ENP_GM_ID").tips({
					side:3,
		            msg:'请输入养老院编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ENP_GM_ID").focus();
			return false;
			}
			if($("#ENP_GMU_ID").val()==""){
				$("#ENP_GMU_ID").tips({
					side:3,
		            msg:'请输入计划申请职工编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ENP_GMU_ID").focus();
			return false;
			}
			if($("#ENP_E_ID").val()==""){
				$("#ENP_E_ID").tips({
					side:3,
		            msg:'请输入护理老人编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ENP_E_ID").focus();
			return false;
			}
			if($("#ENP_NEED").val()==""){
				$("#ENP_NEED").tips({
					side:3,
		            msg:'请输入单位时间需护理次数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ENP_NEED").focus();
			return false;
			}
			if($("#ENP_UNIT").val()==""){
				$("#ENP_UNIT").tips({
					side:3,
		            msg:'请输入时间单位(1日2周3月)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ENP_UNIT").focus();
			return false;
			}
			if($("#ENP_SDATE").val()==""){
				$("#ENP_SDATE").tips({
					side:3,
		            msg:'请输入计划开始日期',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ENP_SDATE").focus();
			return false;
			}
			if($("#ENP_EDATE").val()==""){
				$("#ENP_EDATE").tips({
					side:3,
		            msg:'请输入计划结束时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ENP_EDATE").focus();
			return false;
			}
			if($("#ENP_STATUS").val()==""){
				$("#ENP_STATUS").tips({
					side:3,
		            msg:'请输入计划状态',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ENP_STATUS").focus();
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