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
					<form action="elder/zzySearch.do" name="Form" id="Form" method="post">
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">
									养老院:
									<input type="hidden" id="TERM_GM_ID" name="TERM_GM_ID"/>
								</td>
								<td>
									<select  name="SELECT_GMID" id="SELECT_GMID" style="width:50%;"
									onchange="$('#TERM_GM_ID').val(this.value)">
									<c:if test="${pd.TERM_GM_ID==''||pd.TERM_GM_ID==null}">
									<option value="">下拉选择</option>
									</c:if>
									<c:forEach items="${GM_LIST}" var="staff" varStatus="vs">
									<option value="${staff.GM_ID}" <c:if test="${staff.GM_ID==pd.TERM_GM_ID}">selected</c:if>>${staff.GM_NAME}</option>
									</c:forEach>
 									</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">性别:</td>
								<td>
									<input type="hidden" name="TERM_GENDER" id="TERM_GENDER" value="${pd.TERM_GENDER}"/>
									<select  name="SELECT_GENDER" id="SELECT_GENDER" style="width:50%"
									onchange="$('#TERM_GENDER').val(this.value)">
									<option value="">下拉选择</option>
    								<option value="男" <c:if test="${pd.TERM_GENDER=='男'}">selected</c:if>>男</option>
   									<option value="女" <c:if test="${pd.TERM_GENDER=='女'}">selected</c:if>>女</option>
   									</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">入住状态:</td>
								<td>
									<input type="hidden" name="TERM_INTAKE" id="TERM_INTAKE" value="${pd.TERM_INTAKE}"/>
									<select  name="SELECT_INTAKE" id="SELECT_INTAKE" style="width:50%"
									onchange="$('#TERM_INTAKE').val(this.value)">
									<c:if test="${pd.TERM_INTAKE==''||pd.TERM_INTAKE==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="1" <c:if test="${pd.TERM_INTAKE=='1'}">selected</c:if>>入住</option>
   									<option value="2" <c:if test="${pd.TERM_INTAKE=='2'}">selected</c:if>>退住</option>
   									<option value="3" <c:if test="${pd.TERM_INTAKE=='3'}">selected</c:if>>试住</option>
   									</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">入住时间:</td>
								<td>
									<input class="span10 date-picker" name="TERM_IDATE_START" id="TERM_IDATE_START" value="${pd.TERM_IDATE_START}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="起始时间" title="起始时间" style="width:40%;"/>
									至
									<input class="span10 date-picker" name="TERM_IDATE_END" id="TERM_IDATE_END" value="${pd.TERM_IDATE_END}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="截止时间" title="截止时间" style="width:40%;"/>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">阳历生日:</td>
								<td>
									<input class="span10 date-picker" name="TERM_SDATE_START" id="TERM_SDATE_START" value="${pd.TERM_SDATE_START}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="起始时间" title="起始时间" style="width:40%;"/>
									至
									<input class="span10 date-picker" name="TERM_SDATE_END" id="TERM_SDATE_END" value="${pd.TERM_SDATE_END}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="截止时间" title="截止时间" style="width:40%;"/>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">农历生日:</td>
								<td>
									<input class="span10 date-picker" name="TERM_LDATE_START" id="TERM_LDATE_START" value="${pd.TERM_LDATE_START}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="起始时间" title="起始时间" style="width:40%;"/>
									至
									<input class="span10 date-picker" name="TERM_LDATE_END" id="TERM_LDATE_END" value="${pd.TERM_LDATE_END}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="截止时间" title="截止时间" style="width:40%;"/>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">年龄范围:</td>
								<td>
									<input  name="TERM_AGE_START" id="TERM_AGE_START" value="${pd.TERM_AGE_START}" type="number" placeholder="起始年龄" title="起始年龄" style="width:40%;"/>
									至
									<input  name="TERM_AGE_END" id="TERM_AGE_END" value="${pd.TERM_AGE_END}" type="number" placeholder="截止年龄" title="截止年龄" style="width:40%;"/>
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