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
					
					<form action="gmaresult/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="GMARESULT_ID" id="GMARESULT_ID" value="${pd.GMARESULT_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">本次评估标记:</td>
								<td><input type="text" name="GMAR_CODE" id="GMAR_CODE" value="${pd.GMAR_CODE}" maxlength="32" placeholder="这里输入本次评估标记" title="本次评估标记" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">评测老人编号:</td>
								<td><input type="text" name="GMAR_E_ID" id="GMAR_E_ID" value="${pd.GMAR_E_ID}" maxlength="32" placeholder="这里输入评测老人编号" title="评测老人编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">该题评测得分:</td>
								<td><input type="number" name="GMAR_SCORE" id="GMAR_SCORE" value="${pd.GMAR_SCORE}" maxlength="32" placeholder="这里输入该题评测得分" title="该题评测得分" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">题目编号:</td>
								<td><input type="text" name="GMAR_GMAI_ID" id="GMAR_GMAI_ID" value="${pd.GMAR_GMAI_ID}" maxlength="32" placeholder="这里输入题目编号" title="题目编号" style="width:98%;"/></td>
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
			if($("#GMAR_CODE").val()==""){
				$("#GMAR_CODE").tips({
					side:3,
		            msg:'请输入本次评估标记',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMAR_CODE").focus();
			return false;
			}
			if($("#GMAR_E_ID").val()==""){
				$("#GMAR_E_ID").tips({
					side:3,
		            msg:'请输入评测老人编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMAR_E_ID").focus();
			return false;
			}
			if($("#GMAR_SCORE").val()==""){
				$("#GMAR_SCORE").tips({
					side:3,
		            msg:'请输入该题评测得分',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMAR_SCORE").focus();
			return false;
			}
			if($("#GMAR_GMAI_ID").val()==""){
				$("#GMAR_GMAI_ID").tips({
					side:3,
		            msg:'请输入题目编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMAR_GMAI_ID").focus();
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