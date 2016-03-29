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
					
					<form action="gmnurse/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="GMNURSE_ID" id="GMNURSE_ID" value="${pd.GMNURSE_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">养老院:</td>
								<td>
									<input type="hidden" name="GMN_GM_ID" id="GMN_GM_ID" value="${pd.GMN_GM_ID}">
									<input type="text" readonly="readonly"name="GMN_GM_NAME" id="GMN_GM_NAME" value="${pd.GMN_GM_NAME}"style="width:98%;"/>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
								<td><input type="text" name="GMN_NAME" id="GMN_NAME" value="${pd.GMN_NAME}" maxlength="50" placeholder="这里输入护理项目名称" title="护理项目名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">描述:</td>
								<td><input type="text" name="GMN_CONTENT" id="GMN_CONTENT" value="${pd.GMN_CONTENT}" maxlength="255" placeholder="这里输入护理项目描述" title="护理项目描述" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">类别:</td>
								<td>	
									<input type="hidden" name="GMN_TYPE" id="GMN_TYPE" value="${pd.GMN_TYPE}"/>
									<select  name="SELECT_TYPE" id="SELECT_TYPE" style="width:50%"
									onchange="$('#GMN_TYPE').val(this.value)">
									<c:if test="${pd.GMN_TYPE==''||pd.GMN_TYPE==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="1" <c:if test="${pd.GMN_TYPE=='1'}">selected</c:if>>定制项目</option>
   									<option value="2" <c:if test="${pd.GMN_TYPE=='2'}">selected</c:if>>常规项目</option>
   									</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<td>
									<input type="hidden" name="GMN_STATUS" id="GMN_STATUS" value="${pd.GMN_STATUS}"/>
									<select  name="SELECT_STATUS" id="SELECT_STATUS" style="width:50%"
									onchange="$('#GMN_STATUS').val(this.value)">
									<c:if test="${pd.GMN_STATUS==''||pd.GMN_STATUS==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="1" <c:if test="${pd.GMN_STATUS=='1'}">selected</c:if>>有效</option>
   									<option value="2" <c:if test="${pd.GMN_STATUS=='2'}">selected</c:if>>无效</option>
   									</select>
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
			if($("#GMN_GM_ID").val()==""){
				$("#GMN_GM_ID").tips({
					side:3,
		            msg:'请输入项目所属养老院编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMN_GM_ID").focus();
			return false;
			}
			if($("#GMN_NAME").val()==""){
				$("#GMN_NAME").tips({
					side:3,
		            msg:'请输入护理项目名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMN_NAME").focus();
			return false;
			}
			if($("#GMN_CONTENT").val()==""){
				$("#GMN_CONTENT").tips({
					side:3,
		            msg:'请输入护理项目描述',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMN_CONTENT").focus();
			return false;
			}
			if($("#GMN_STATUS").val()==""){
				$("#GMN_STATUS").tips({
					side:3,
		            msg:'请输入状态（1有效、2 无效）',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMN_STATUS").focus();
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