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
					
					<form action="gmatype/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="GMATYPE_ID" id="GMATYPE_ID" value="${pd.GMATYPE_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">养老院:</td>
								<td>
									<input type="hidden" name="GMAT_GM_ID" id="GMAT_GM_ID" value="${pd.GMAT_GM_ID}"/>
									<input type="text" readonly="readonly" name="GMAT_GM_NAME" id="GMAT_GM_NAME" value="${pd.GMAT_GM_NAME}"/>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">类别名称:</td>
								<td><input type="text" name="GMAT_NAME" id="GMAT_NAME" value="${pd.GMAT_NAME}" maxlength="50" placeholder="这里输入类别名称" title="类别名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">类别编号:</td>
								<td><input type="number" name="GMAT_NUM" id="GMAT_NUM" value="${pd.GMAT_NUM}" maxlength="32" placeholder="这里输入类别编号" title="类别编号" style="width:98%;"/></td>
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
			if($("#GMAT_GM_ID").val()==""){
				$("#GMAT_GM_ID").tips({
					side:3,
		            msg:'请输入养老院编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMAT_GM_ID").focus();
			return false;
			}
			if($("#GMAT_NAME").val()==""){
				$("#GMAT_NAME").tips({
					side:3,
		            msg:'请输入类别名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMAT_NAME").focus();
			return false;
			}
			if($("#GMAT_NUM").val()==""){
				$("#GMAT_NUM").tips({
					side:3,
		            msg:'请输入类别编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMAT_NUM").focus();
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