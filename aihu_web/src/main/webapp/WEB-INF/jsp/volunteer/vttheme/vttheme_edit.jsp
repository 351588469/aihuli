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
					
					<form action="vttheme/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="VTTHEME_ID" id="VTTHEME_ID" value="${pd.VTTHEME_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">团体编号:</td>
								<td><input class="span10 date-picker" name="VTT_VT_ID" id="VTT_VT_ID" value="${pd.VTT_VT_ID}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="团体编号" title="团体编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">标题:</td>
								<td><input type="text" name="VTT_TITLE" id="VTT_TITLE" value="${pd.VTT_TITLE}" maxlength="255" placeholder="这里输入标题" title="标题" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">内容:</td>
								<td><input type="text" name="VTT_CONTENT" id="VTT_CONTENT" value="${pd.VTT_CONTENT}" maxlength="255" placeholder="这里输入内容" title="内容" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">发表人编号:</td>
								<td><input type="text" name="VTT_P_ID" id="VTT_P_ID" value="${pd.VTT_P_ID}" maxlength="32" placeholder="这里输入发表人编号" title="发表人编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">点赞数量:</td>
								<td><input type="number" name="VTT_PRAISE" id="VTT_PRAISE" value="${pd.VTT_PRAISE}" maxlength="32" placeholder="这里输入点赞数量" title="点赞数量" style="width:98%;"/></td>
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
			if($("#VTT_VT_ID").val()==""){
				$("#VTT_VT_ID").tips({
					side:3,
		            msg:'请输入团体编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VTT_VT_ID").focus();
			return false;
			}
			if($("#VTT_TITLE").val()==""){
				$("#VTT_TITLE").tips({
					side:3,
		            msg:'请输入标题',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VTT_TITLE").focus();
			return false;
			}
			if($("#VTT_CONTENT").val()==""){
				$("#VTT_CONTENT").tips({
					side:3,
		            msg:'请输入内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VTT_CONTENT").focus();
			return false;
			}
			if($("#VTT_P_ID").val()==""){
				$("#VTT_P_ID").tips({
					side:3,
		            msg:'请输入发表人编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VTT_P_ID").focus();
			return false;
			}
			if($("#VTT_PRAISE").val()==""){
				$("#VTT_PRAISE").tips({
					side:3,
		            msg:'请输入点赞数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#VTT_PRAISE").focus();
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