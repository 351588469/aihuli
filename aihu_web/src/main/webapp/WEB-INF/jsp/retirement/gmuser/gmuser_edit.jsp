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
					
					<form action="gmuser/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="GMUSER_ID" id="GMUSER_ID" value="${pd.GMUSER_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工所属养老院编号:</td>
								<td><input type="text" name="GMU_GM_ID" id="GMU_GM_ID" value="${pd.GMU_GM_ID}" maxlength="32" placeholder="这里输入职工所属养老院编号" title="职工所属养老院编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工姓名:</td>
								<td><input type="text" name="GMU_NAME" id="GMU_NAME" value="${pd.GMU_NAME}" maxlength="20" placeholder="这里输入职工姓名" title="职工姓名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工性别:</td>
								<td><input type="text" name="GMU_GENDER" id="GMU_GENDER" value="${pd.GMU_GENDER}" maxlength="5" placeholder="这里输入职工性别" title="职工性别" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工生日:</td>
								<td><input class="span10 date-picker" name="GMU_BIRTHDAY" id="GMU_BIRTHDAY" value="${pd.GMU_BIRTHDAY}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="职工生日" title="职工生日" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工入职时间:</td>
								<td><input class="span10 date-picker" name="GM_EDATE" id="GM_EDATE" value="${pd.GM_EDATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="职工入职时间" title="职工入职时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工所属部门:</td>
								<td><input type="number" name="GMU_DEP" id="GMU_DEP" value="${pd.GMU_DEP}" maxlength="32" placeholder="这里输入职工所属部门" title="职工所属部门" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工职务:</td>
								<td><input type="number" name="GMU_DUTIES" id="GMU_DUTIES" value="${pd.GMU_DUTIES}" maxlength="32" placeholder="这里输入职工职务" title="职工职务" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工直属领导编号:</td>
								<td><input type="text" name="GMU_LEADERID" id="GMU_LEADERID" value="${pd.GMU_LEADERID}" maxlength="32" placeholder="这里输入职工直属领导编号" title="职工直属领导编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工健康状况:</td>
								<td><input type="text" name="GMU_HEALTH" id="GMU_HEALTH" value="${pd.GMU_HEALTH}" maxlength="20" placeholder="这里输入职工健康状况" title="职工健康状况" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工婚姻状况:</td>
								<td><input type="text" name="GMU_MARRIAGE" id="GMU_MARRIAGE" value="${pd.GMU_MARRIAGE}" maxlength="10" placeholder="这里输入职工婚姻状况" title="职工婚姻状况" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工联系电话:</td>
								<td><input type="text" name="GMU_TEL" id="GMU_TEL" value="${pd.GMU_TEL}" maxlength="20" placeholder="这里输入职工联系电话" title="职工联系电话" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工电子邮箱:</td>
								<td><input type="text" name="GMU_EMAIL" id="GMU_EMAIL" value="${pd.GMU_EMAIL}" maxlength="50" placeholder="这里输入职工电子邮箱" title="职工电子邮箱" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工户籍:</td>
								<td><input type="text" name="GMU_CENSUS" id="GMU_CENSUS" value="${pd.GMU_CENSUS}" maxlength="255" placeholder="这里输入职工户籍" title="职工户籍" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工家庭住址:</td>
								<td><input type="text" name="GMU_ADDRESS" id="GMU_ADDRESS" value="${pd.GMU_ADDRESS}" maxlength="255" placeholder="这里输入职工家庭住址" title="职工家庭住址" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工入职状态(1:入职2:离职3:请假):</td>
								<td><input type="number" name="GMU_ESTATUS" id="GMU_ESTATUS" value="${pd.GMU_ESTATUS}" maxlength="32" placeholder="这里输入职工入职状态(1:入职2:离职3:请假)" title="职工入职状态(1:入职2:离职3:请假)" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工学历:</td>
								<td><input type="text" name="GMU_EDUCATION" id="GMU_EDUCATION" value="${pd.GMU_EDUCATION}" maxlength="20" placeholder="这里输入职工学历" title="职工学历" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工毕业院校:</td>
								<td><input type="text" name="GMU_COLLAGE" id="GMU_COLLAGE" value="${pd.GMU_COLLAGE}" maxlength="50" placeholder="这里输入职工毕业院校" title="职工毕业院校" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工专业:</td>
								<td><input type="text" name="GMU_MAJOR" id="GMU_MAJOR" value="${pd.GMU_MAJOR}" maxlength="50" placeholder="这里输入职工专业" title="职工专业" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工英语水平:</td>
								<td><input type="text" name="GMU_LENGLISH" id="GMU_LENGLISH" value="${pd.GMU_LENGLISH}" maxlength="20" placeholder="这里输入职工英语水平" title="职工英语水平" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工计算机水平:</td>
								<td><input type="text" name="GMU_LCOMPUTER" id="GMU_LCOMPUTER" value="${pd.GMU_LCOMPUTER}" maxlength="20" placeholder="这里输入职工计算机水平" title="职工计算机水平" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工工资:</td>
								<td><input type="text" name="GMU_WAGE" id="GMU_WAGE" value="${pd.GMU_WAGE}" maxlength="10" placeholder="这里输入职工工资" title="职工工资" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工头像:</td>
								<td><input type="text" name="GMU_AVATER" id="GMU_AVATER" value="${pd.GMU_AVATER}" maxlength="255" placeholder="这里输入职工头像" title="职工头像" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工密码:</td>
								<td><input type="text" name="GMU_PWD" id="GMU_PWD" value="${pd.GMU_PWD}" maxlength="64" placeholder="这里输入职工密码" title="职工密码" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建用户:</td>
								<td><input type="text" name="GMU_GMUID" id="GMU_GMUID" value="${pd.GMU_GMUID}" maxlength="32" placeholder="这里输入创建用户" title="创建用户" style="width:98%;"/></td>
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
			if($("#GMU_GM_ID").val()==""){
				$("#GMU_GM_ID").tips({
					side:3,
		            msg:'请输入职工所属养老院编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_GM_ID").focus();
			return false;
			}
			if($("#GMU_NAME").val()==""){
				$("#GMU_NAME").tips({
					side:3,
		            msg:'请输入职工姓名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_NAME").focus();
			return false;
			}
			if($("#GMU_GENDER").val()==""){
				$("#GMU_GENDER").tips({
					side:3,
		            msg:'请输入职工性别',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_GENDER").focus();
			return false;
			}
			if($("#GMU_BIRTHDAY").val()==""){
				$("#GMU_BIRTHDAY").tips({
					side:3,
		            msg:'请输入职工生日',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_BIRTHDAY").focus();
			return false;
			}
			if($("#GM_EDATE").val()==""){
				$("#GM_EDATE").tips({
					side:3,
		            msg:'请输入职工入职时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GM_EDATE").focus();
			return false;
			}
			if($("#GMU_DEP").val()==""){
				$("#GMU_DEP").tips({
					side:3,
		            msg:'请输入职工所属部门',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_DEP").focus();
			return false;
			}
			if($("#GMU_DUTIES").val()==""){
				$("#GMU_DUTIES").tips({
					side:3,
		            msg:'请输入职工职务',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_DUTIES").focus();
			return false;
			}
			if($("#GMU_LEADERID").val()==""){
				$("#GMU_LEADERID").tips({
					side:3,
		            msg:'请输入职工直属领导编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_LEADERID").focus();
			return false;
			}
			if($("#GMU_HEALTH").val()==""){
				$("#GMU_HEALTH").tips({
					side:3,
		            msg:'请输入职工健康状况',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_HEALTH").focus();
			return false;
			}
			if($("#GMU_MARRIAGE").val()==""){
				$("#GMU_MARRIAGE").tips({
					side:3,
		            msg:'请输入职工婚姻状况',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_MARRIAGE").focus();
			return false;
			}
			if($("#GMU_TEL").val()==""){
				$("#GMU_TEL").tips({
					side:3,
		            msg:'请输入职工联系电话',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_TEL").focus();
			return false;
			}
			if($("#GMU_EMAIL").val()==""){
				$("#GMU_EMAIL").tips({
					side:3,
		            msg:'请输入职工电子邮箱',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_EMAIL").focus();
			return false;
			}
			if($("#GMU_CENSUS").val()==""){
				$("#GMU_CENSUS").tips({
					side:3,
		            msg:'请输入职工户籍',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_CENSUS").focus();
			return false;
			}
			if($("#GMU_ADDRESS").val()==""){
				$("#GMU_ADDRESS").tips({
					side:3,
		            msg:'请输入职工家庭住址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_ADDRESS").focus();
			return false;
			}
			if($("#GMU_ESTATUS").val()==""){
				$("#GMU_ESTATUS").tips({
					side:3,
		            msg:'请输入职工入职状态(1:入职2:离职3:请假)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_ESTATUS").focus();
			return false;
			}
			if($("#GMU_EDUCATION").val()==""){
				$("#GMU_EDUCATION").tips({
					side:3,
		            msg:'请输入职工学历',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_EDUCATION").focus();
			return false;
			}
			if($("#GMU_COLLAGE").val()==""){
				$("#GMU_COLLAGE").tips({
					side:3,
		            msg:'请输入职工毕业院校',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_COLLAGE").focus();
			return false;
			}
			if($("#GMU_MAJOR").val()==""){
				$("#GMU_MAJOR").tips({
					side:3,
		            msg:'请输入职工专业',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_MAJOR").focus();
			return false;
			}
			if($("#GMU_LENGLISH").val()==""){
				$("#GMU_LENGLISH").tips({
					side:3,
		            msg:'请输入职工英语水平',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_LENGLISH").focus();
			return false;
			}
			if($("#GMU_LCOMPUTER").val()==""){
				$("#GMU_LCOMPUTER").tips({
					side:3,
		            msg:'请输入职工计算机水平',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_LCOMPUTER").focus();
			return false;
			}
			if($("#GMU_WAGE").val()==""){
				$("#GMU_WAGE").tips({
					side:3,
		            msg:'请输入职工工资',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_WAGE").focus();
			return false;
			}
			if($("#GMU_AVATER").val()==""){
				$("#GMU_AVATER").tips({
					side:3,
		            msg:'请输入职工头像',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_AVATER").focus();
			return false;
			}
			if($("#GMU_PWD").val()==""){
				$("#GMU_PWD").tips({
					side:3,
		            msg:'请输入职工密码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_PWD").focus();
			return false;
			}
			if($("#GMU_GMUID").val()==""){
				$("#GMU_GMUID").tips({
					side:3,
		            msg:'请输入创建用户',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_GMUID").focus();
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