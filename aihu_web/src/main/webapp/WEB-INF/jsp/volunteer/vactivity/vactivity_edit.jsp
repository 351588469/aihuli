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
					
					<form action="vactivity/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="VACTIVITY_ID" id="VACTIVITY_ID" value="${pd.VACTIVITY_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">主题:</td>
								<td><input type="text" name="VA_TOPIC" id="VA_TOPIC" value="${pd.VA_TOPIC}" maxlength="255" placeholder="这里输入主题" title="主题" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">城市:</td>
								<td><input type="text" name="VA_CITY" id="VA_CITY" value="${pd.VA_CITY}" maxlength="255" placeholder="这里输入城市" title="城市" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">地址:</td>
								<td><input type="text" name="VA_ADDRESS" id="VA_ADDRESS" value="${pd.VA_ADDRESS}" maxlength="255" placeholder="这里输入地址" title="地址" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">关键词:</td>
								<td><input type="text" name="VA_KEYWORD" id="VA_KEYWORD" value="${pd.VA_KEYWORD}" maxlength="255" placeholder="这里输入关键词" title="关键词" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">详情:</td>
								<td><input type="text" name="VA_CONTENT" id="VA_CONTENT" value="${pd.VA_CONTENT}" maxlength="255" placeholder="这里输入详情" title="详情" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">日程:</td>
								<td><input type="text" name="VA_SCHEDULE" id="VA_SCHEDULE" value="${pd.VA_SCHEDULE}" maxlength="255" placeholder="这里输入日程" title="日程" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">举办方团体编号:</td>
								<td><input type="text" name="VA_VT_ID" id="VA_VT_ID" value="${pd.VA_VT_ID}" maxlength="32" placeholder="这里输入举办方团体编号" title="举办方团体编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">点赞数量:</td>
								<td><input type="number" name="VA_PRAISE" id="VA_PRAISE" value="${pd.VA_PRAISE}" maxlength="32" placeholder="这里输入点赞数量" title="点赞数量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">报名人数上限:</td>
								<td><input type="number" name="VA_ENROLL_M" id="VA_ENROLL_M" value="${pd.VA_ENROLL_M}" maxlength="32" placeholder="这里输入报名人数上限" title="报名人数上限" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">活动开始时间:</td>
								<td><input class="span10 date-picker" name="VA_STIME" id="VA_STIME" value="${pd.VA_STIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="活动开始时间" title="活动开始时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">活动结束时间:</td>
								<td><input class="span10 date-picker" name="VA_ETIME" id="VA_ETIME" value="${pd.VA_ETIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="活动结束时间" title="活动结束时间" style="width:98%;"/></td>
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
			if($("#VA_TOPIC").val()==""){
				$("#VA_TOPIC").tips({
					side:3,
		            msg:'请输入主题',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VA_TOPIC").focus();
			return false;
			}
			if($("#VA_CITY").val()==""){
				$("#VA_CITY").tips({
					side:3,
		            msg:'请输入城市',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VA_CITY").focus();
			return false;
			}
			if($("#VA_ADDRESS").val()==""){
				$("#VA_ADDRESS").tips({
					side:3,
		            msg:'请输入地址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VA_ADDRESS").focus();
			return false;
			}
			if($("#VA_KEYWORD").val()==""){
				$("#VA_KEYWORD").tips({
					side:3,
		            msg:'请输入关键词',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VA_KEYWORD").focus();
			return false;
			}
			if($("#VA_CONTENT").val()==""){
				$("#VA_CONTENT").tips({
					side:3,
		            msg:'请输入详情',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VA_CONTENT").focus();
			return false;
			}
			if($("#VA_SCHEDULE").val()==""){
				$("#VA_SCHEDULE").tips({
					side:3,
		            msg:'请输入日程',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VA_SCHEDULE").focus();
			return false;
			}
			if($("#VA_VT_ID").val()==""){
				$("#VA_VT_ID").tips({
					side:3,
		            msg:'请输入举办方团体编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VA_VT_ID").focus();
			return false;
			}
			if($("#VA_PRAISE").val()==""){
				$("#VA_PRAISE").tips({
					side:3,
		            msg:'请输入点赞数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VA_PRAISE").focus();
			return false;
			}
			if($("#VA_ENROLL_M").val()==""){
				$("#VA_ENROLL_M").tips({
					side:3,
		            msg:'请输入报名人数上限',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VA_ENROLL_M").focus();
			return false;
			}
			if($("#VA_STIME").val()==""){
				$("#VA_STIME").tips({
					side:3,
		            msg:'请输入活动开始时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VA_STIME").focus();
			return false;
			}
			if($("#VA_ETIME").val()==""){
				$("#VA_ETIME").tips({
					side:3,
		            msg:'请输入活动结束时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VA_ETIME").focus();
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