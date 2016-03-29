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
					
					<form action="gmaitem/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="GMAITEM_ID" id="GMAITEM_ID" value="${pd.GMAITEM_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">第几题:</td>
								<td><input type="number" name="GMAI_NUMBER" id="GMAI_NUMBER" value="${pd.GMAI_NUMBER}" maxlength="32" placeholder="这里输入第几题" title="第几题" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">类别:</td>
								<td>
									<input type="text" readonly="readonly" name="GMAI_GMAT_NAME" id="GMAI_GMAT_NAME" value="${pd.GMAI_GMAT_NAME}" maxlength="32" style="width:98%;"/>
									<input type="hidden" id="GMAI_GMAT_ID" value="${pd.GMAI_GMAT_ID}"/>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">分值:</td>
								<td><input type="number" name="GMAI_SCORE" id="GMAI_SCORE" value="${pd.GMAI_SCORE}" maxlength="32" placeholder="这里输入题目最大或最小分值" title="题目最大或最小分值" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">内容:</td>
								<td><input type="text" name="GMAI_CONTENT" id="GMAI_CONTENT" value="${pd.GMAI_CONTENT}" maxlength="255" placeholder="这里输入题目具体内容" title="题目具体内容" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">养老院:</td>
								<td>
									<input type="hidden" name="GMAI_GM_ID" id="GMAI_GM_ID" value="${pd.GMAI_GM_ID}"/>
									<input type="text" readonly="readonly" name="GMAI_GM_NAME" id="GMAI_GM_NAME" value="${pd.GMAI_GM_NAME}"style="width:98%;"/>
								</td>
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
			if($("#GMAI_NUMBER").val()==""){
				$("#GMAI_NUMBER").tips({
					side:3,
		            msg:'请输入第几题',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMAI_NUMBER").focus();
			return false;
			}
			if($("#GMAI_GMAT_ID").val()==""){
				$("#GMAI_GMAT_ID").tips({
					side:3,
		            msg:'请输入题目所属类别编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMAI_GMAT_ID").focus();
			return false;
			}
			if($("#GMAI_SCORE").val()==""){
				$("#GMAI_SCORE").tips({
					side:3,
		            msg:'请输入题目最大或最小分值',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMAI_SCORE").focus();
			return false;
			}
			if($("#GMAI_CONTENT").val()==""){
				$("#GMAI_CONTENT").tips({
					side:3,
		            msg:'请输入题目具体内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMAI_CONTENT").focus();
			return false;
			}
			if($("#GMAI_GM_ID").val()==""){
				$("#GMAI_GM_ID").tips({
					side:3,
		            msg:'请输入养老院编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMAI_GM_ID").focus();
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