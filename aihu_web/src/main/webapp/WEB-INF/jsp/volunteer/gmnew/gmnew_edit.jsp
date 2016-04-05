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
					
					<form action="gmnew/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="GMNEW_ID" id="GMNEW_ID" value="${pd.GMNEW_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">养老院编号:</td>
								<td><input type="text" name="GMN_GM_ID" id="GMN_GM_ID" value="${pd.GMN_GM_ID}" maxlength="32" placeholder="这里输入养老院编号" title="养老院编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">评论用户编号:</td>
								<td><input type="text" name="GMN_P_ID" id="GMN_P_ID" value="${pd.GMN_P_ID}" maxlength="32" placeholder="这里输入评论用户编号" title="评论用户编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">回复消息编号:</td>
								<td><input type="text" name="GMN_R_ID" id="GMN_R_ID" value="${pd.GMN_R_ID}" maxlength="32" placeholder="这里输入回复消息编号" title="回复消息编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">内容:</td>
								<td><input type="text" name="GMN_CONTENT" id="GMN_CONTENT" value="${pd.GMN_CONTENT}" maxlength="255" placeholder="这里输入内容" title="内容" style="width:98%;"/></td>
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
			if($("#GMN_GM_ID").val()==""){
				$("#GMN_GM_ID").tips({
					side:3,
		            msg:'请输入养老院编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMN_GM_ID").focus();
			return false;
			}
			if($("#GMN_P_ID").val()==""){
				$("#GMN_P_ID").tips({
					side:3,
		            msg:'请输入评论用户编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMN_P_ID").focus();
			return false;
			}
			if($("#GMN_R_ID").val()==""){
				$("#GMN_R_ID").tips({
					side:3,
		            msg:'请输入回复消息编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMN_R_ID").focus();
			return false;
			}
			if($("#GMN_CONTENT").val()==""){
				$("#GMN_CONTENT").tips({
					side:3,
		            msg:'请输入内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMN_CONTENT").focus();
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