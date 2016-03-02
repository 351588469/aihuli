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
					
					<form action="edplan/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="EDPLAN_ID" id="EDPLAN_ID" value="${pd.EDPLAN_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人编号:</td>
								<td><input type="text" name="EDP_E_ID" id="EDP_E_ID" value="${pd.EDP_E_ID}" maxlength="32" placeholder="这里输入老人编号" title="老人编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">计划申请职工编号:</td>
								<td><input type="text" name="EDP_GMU_ID" id="EDP_GMU_ID" value="${pd.EDP_GMU_ID}" maxlength="32" placeholder="这里输入计划申请职工编号" title="计划申请职工编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">计划所属养老院编号:</td>
								<td><input type="text" name="EDP_GM_ID" id="EDP_GM_ID" value="${pd.EDP_GM_ID}" maxlength="32" placeholder="这里输入计划所属养老院编号" title="计划所属养老院编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用药开始日期:</td>
								<td><input class="span10 date-picker" name="EDP_SDATE" id="EDP_SDATE" value="${pd.EDP_SDATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="用药开始日期" title="用药开始日期" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用药截止时间:</td>
								<td><input class="span10 date-picker" name="EDP_EDATE" id="EDP_EDATE" value="${pd.EDP_EDATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="用药截止时间" title="用药截止时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用药时间点:</td>
								<td><input type="text" name="EDP_MHOUR" id="EDP_MHOUR" value="${pd.EDP_MHOUR}" maxlength="10" placeholder="这里输入用药时间点" title="用药时间点" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用药时间说明:</td>
								<td><input type="text" name="EDP_MDESC" id="EDP_MDESC" value="${pd.EDP_MDESC}" maxlength="10" placeholder="这里输入用药时间说明" title="用药时间说明" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">药品名称:</td>
								<td><input type="text" name="EDP_MNAME" id="EDP_MNAME" value="${pd.EDP_MNAME}" maxlength="20" placeholder="这里输入药品名称" title="药品名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">药品剂量:</td>
								<td><input type="text" name="EDP_MDOSAGE" id="EDP_MDOSAGE" value="${pd.EDP_MDOSAGE}" maxlength="20" placeholder="这里输入药品剂量" title="药品剂量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">药品数量:</td>
								<td><input type="number" name="EDP_MCOUNT" id="EDP_MCOUNT" value="${pd.EDP_MCOUNT}" maxlength="32" placeholder="这里输入药品数量" title="药品数量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用药计划说明:</td>
								<td><input type="text" name="EDP_DESC" id="EDP_DESC" value="${pd.EDP_DESC}" maxlength="255" placeholder="这里输入用药计划说明" title="用药计划说明" style="width:98%;"/></td>
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
			if($("#EDP_E_ID").val()==""){
				$("#EDP_E_ID").tips({
					side:3,
		            msg:'请输入老人编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EDP_E_ID").focus();
			return false;
			}
			if($("#EDP_GMU_ID").val()==""){
				$("#EDP_GMU_ID").tips({
					side:3,
		            msg:'请输入计划申请职工编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EDP_GMU_ID").focus();
			return false;
			}
			if($("#EDP_GM_ID").val()==""){
				$("#EDP_GM_ID").tips({
					side:3,
		            msg:'请输入计划所属养老院编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EDP_GM_ID").focus();
			return false;
			}
			if($("#EDP_SDATE").val()==""){
				$("#EDP_SDATE").tips({
					side:3,
		            msg:'请输入用药开始日期',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EDP_SDATE").focus();
			return false;
			}
			if($("#EDP_EDATE").val()==""){
				$("#EDP_EDATE").tips({
					side:3,
		            msg:'请输入用药截止时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EDP_EDATE").focus();
			return false;
			}
			if($("#EDP_MHOUR").val()==""){
				$("#EDP_MHOUR").tips({
					side:3,
		            msg:'请输入用药时间点',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EDP_MHOUR").focus();
			return false;
			}
			if($("#EDP_MDESC").val()==""){
				$("#EDP_MDESC").tips({
					side:3,
		            msg:'请输入用药时间说明',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EDP_MDESC").focus();
			return false;
			}
			if($("#EDP_MNAME").val()==""){
				$("#EDP_MNAME").tips({
					side:3,
		            msg:'请输入药品名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EDP_MNAME").focus();
			return false;
			}
			if($("#EDP_MDOSAGE").val()==""){
				$("#EDP_MDOSAGE").tips({
					side:3,
		            msg:'请输入药品剂量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EDP_MDOSAGE").focus();
			return false;
			}
			if($("#EDP_MCOUNT").val()==""){
				$("#EDP_MCOUNT").tips({
					side:3,
		            msg:'请输入药品数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EDP_MCOUNT").focus();
			return false;
			}
			if($("#EDP_DESC").val()==""){
				$("#EDP_DESC").tips({
					side:3,
		            msg:'请输入用药计划说明',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EDP_DESC").focus();
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