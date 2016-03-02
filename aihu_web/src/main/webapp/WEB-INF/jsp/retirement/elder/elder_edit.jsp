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
					
					<form action="elder/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ELDER_ID" id="ELDER_ID" value="${pd.ELDER_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人姓名:</td>
								<td><input type="text" name="E_NAME" id="E_NAME" value="${pd.E_NAME}" maxlength="20" placeholder="这里输入老人姓名" title="老人姓名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人性别:</td>
								<td><input type="text" name="E_GENDER" id="E_GENDER" value="${pd.E_GENDER}" maxlength="5" placeholder="这里输入老人性别" title="老人性别" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">入住状态(1入住2退住3试住):</td>
								<td><input type="number" name="E_INTAKE" id="E_INTAKE" value="${pd.E_INTAKE}" maxlength="32" placeholder="这里输入入住状态(1入住2退住3试住)" title="入住状态(1入住2退住3试住)" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">身份证:</td>
								<td><input type="text" name="E_IDENTITY" id="E_IDENTITY" value="${pd.E_IDENTITY}" maxlength="20" placeholder="这里输入身份证" title="身份证" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">入住时间:</td>
								<td><input class="span10 date-picker" name="E_IDATE" id="E_IDATE" value="${pd.E_IDATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="入住时间" title="入住时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">阳历生日:</td>
								<td><input class="span10 date-picker" name="E_SDATE" id="E_SDATE" value="${pd.E_SDATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="阳历生日" title="阳历生日" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人户籍:</td>
								<td><input type="text" name="E_CENSUS" id="E_CENSUS" value="${pd.E_CENSUS}" maxlength="255" placeholder="这里输入老人户籍" title="老人户籍" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人住址:</td>
								<td><input type="text" name="E_ADDRESS" id="E_ADDRESS" value="${pd.E_ADDRESS}" maxlength="255" placeholder="这里输入老人住址" title="老人住址" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人联系电话:</td>
								<td><input type="text" name="E_TEL" id="E_TEL" value="${pd.E_TEL}" maxlength="20" placeholder="这里输入老人联系电话" title="老人联系电话" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">监护人姓名:</td>
								<td><input type="text" name="E_G_NAME" id="E_G_NAME" value="${pd.E_G_NAME}" maxlength="20" placeholder="这里输入监护人姓名" title="监护人姓名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">监护人关系:</td>
								<td><input type="text" name="E_G_REL" id="E_G_REL" value="${pd.E_G_REL}" maxlength="20" placeholder="这里输入监护人关系" title="监护人关系" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">监护人电话:</td>
								<td><input type="text" name="E_G_TEL" id="E_G_TEL" value="${pd.E_G_TEL}" maxlength="20" placeholder="这里输入监护人电话" title="监护人电话" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">身高:</td>
								<td><input type="text" name="E_HEIGHT" id="E_HEIGHT" value="${pd.E_HEIGHT}" maxlength="10" placeholder="这里输入身高" title="身高" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">体重:</td>
								<td><input type="text" name="E_WEIGHT" id="E_WEIGHT" value="${pd.E_WEIGHT}" maxlength="10" placeholder="这里输入体重" title="体重" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">血型:</td>
								<td><input type="text" name="E_BTYPE" id="E_BTYPE" value="${pd.E_BTYPE}" maxlength="10" placeholder="这里输入血型" title="血型" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">视力:</td>
								<td><input type="text" name="E_VISSION" id="E_VISSION" value="${pd.E_VISSION}" maxlength="20" placeholder="这里输入视力" title="视力" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">听力:</td>
								<td><input type="text" name="E_HEARING" id="E_HEARING" value="${pd.E_HEARING}" maxlength="20" placeholder="这里输入听力" title="听力" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">记忆力:</td>
								<td><input type="text" name="E_MEMORY" id="E_MEMORY" value="${pd.E_MEMORY}" maxlength="20" placeholder="这里输入记忆力" title="记忆力" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">神智:</td>
								<td><input type="text" name="E_MIND" id="E_MIND" value="${pd.E_MIND}" maxlength="20" placeholder="这里输入神智" title="神智" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">大小便:</td>
								<td><input type="text" name="E_RELIEVE" id="E_RELIEVE" value="${pd.E_RELIEVE}" maxlength="20" placeholder="这里输入大小便" title="大小便" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">血压(收缩压/舒张压):</td>
								<td><input type="text" name="E_STOLIC" id="E_STOLIC" value="${pd.E_STOLIC}" maxlength="20" placeholder="这里输入血压(收缩压/舒张压)" title="血压(收缩压/舒张压)" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">脉搏:</td>
								<td><input type="text" name="E_PULSE" id="E_PULSE" value="${pd.E_PULSE}" maxlength="20" placeholder="这里输入脉搏" title="脉搏" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">药物过敏史:</td>
								<td><input type="text" name="E_D_ALLERGY" id="E_D_ALLERGY" value="${pd.E_D_ALLERGY}" maxlength="255" placeholder="这里输入药物过敏史" title="药物过敏史" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">常服药名:</td>
								<td><input type="text" name="E_D_COMMON" id="E_D_COMMON" value="${pd.E_D_COMMON}" maxlength="255" placeholder="这里输入常服药名" title="常服药名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">慢性病史:</td>
								<td><input type="text" name="E_D_CHRONIC" id="E_D_CHRONIC" value="${pd.E_D_CHRONIC}" maxlength="255" placeholder="这里输入慢性病史" title="慢性病史" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">其他健康情况:</td>
								<td><input type="text" name="E_HNOTE" id="E_HNOTE" value="${pd.E_HNOTE}" maxlength="255" placeholder="这里输入其他健康情况" title="其他健康情况" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">评估分数:</td>
								<td><input type="number" name="E_A_SCORE" id="E_A_SCORE" value="${pd.E_A_SCORE}" maxlength="32" placeholder="这里输入评估分数" title="评估分数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">评估等级:</td>
								<td><input type="number" name="E_A_LEVEL" id="E_A_LEVEL" value="${pd.E_A_LEVEL}" maxlength="32" placeholder="这里输入评估等级" title="评估等级" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">最近一次评估表:</td>
								<td><input type="text" name="E_A_LEVELLIST" id="E_A_LEVELLIST" value="${pd.E_A_LEVELLIST}" maxlength="32" placeholder="这里输入最近一次评估表" title="最近一次评估表" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人头像:</td>
								<td><input type="text" name="E_AVATER" id="E_AVATER" value="${pd.E_AVATER}" maxlength="255" placeholder="这里输入老人头像" title="老人头像" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人密码:</td>
								<td><input type="text" name="E_PWD" id="E_PWD" value="${pd.E_PWD}" maxlength="64" placeholder="这里输入老人密码" title="老人密码" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建职工编号:</td>
								<td><input type="text" name="E_GMU_ID" id="E_GMU_ID" value="${pd.E_GMU_ID}" maxlength="32" placeholder="这里输入创建职工" title="创建职工" style="width:98%;"/></td>
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
			if($("#E_NAME").val()==""){
				$("#E_NAME").tips({
					side:3,
		            msg:'请输入老人姓名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_NAME").focus();
			return false;
			}
			if($("#E_GENDER").val()==""){
				$("#E_GENDER").tips({
					side:3,
		            msg:'请输入老人性别',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_GENDER").focus();
			return false;
			}
			if($("#E_INTAKE").val()==""){
				$("#E_INTAKE").tips({
					side:3,
		            msg:'请输入入住状态(1入住2退住3试住)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_INTAKE").focus();
			return false;
			}
			if($("#E_IDENTITY").val()==""){
				$("#E_IDENTITY").tips({
					side:3,
		            msg:'请输入身份证',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_IDENTITY").focus();
			return false;
			}
			if($("#E_IDATE").val()==""){
				$("#E_IDATE").tips({
					side:3,
		            msg:'请输入入住时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_IDATE").focus();
			return false;
			}
			if($("#E_SDATE").val()==""){
				$("#E_SDATE").tips({
					side:3,
		            msg:'请输入阳历生日',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_SDATE").focus();
			return false;
			}
			if($("#E_AGE").val()==""){
				$("#E_AGE").tips({
					side:3,
		            msg:'请输入老人年龄',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_AGE").focus();
			return false;
			}
			if($("#E_CENSUS").val()==""){
				$("#E_CENSUS").tips({
					side:3,
		            msg:'请输入老人户籍',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_CENSUS").focus();
			return false;
			}
			if($("#E_ADDRESS").val()==""){
				$("#E_ADDRESS").tips({
					side:3,
		            msg:'请输入老人住址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_ADDRESS").focus();
			return false;
			}
			if($("#E_TEL").val()==""){
				$("#E_TEL").tips({
					side:3,
		            msg:'请输入老人联系电话',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_TEL").focus();
			return false;
			}
			if($("#E_G_NAME").val()==""){
				$("#E_G_NAME").tips({
					side:3,
		            msg:'请输入监护人姓名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_G_NAME").focus();
			return false;
			}
			if($("#E_G_REL").val()==""){
				$("#E_G_REL").tips({
					side:3,
		            msg:'请输入监护人关系',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_G_REL").focus();
			return false;
			}
			if($("#E_G_TEL").val()==""){
				$("#E_G_TEL").tips({
					side:3,
		            msg:'请输入监护人电话',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_G_TEL").focus();
			return false;
			}
			if($("#E_HEIGHT").val()==""){
				$("#E_HEIGHT").tips({
					side:3,
		            msg:'请输入身高',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_HEIGHT").focus();
			return false;
			}
			if($("#E_WEIGHT").val()==""){
				$("#E_WEIGHT").tips({
					side:3,
		            msg:'请输入体重',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_WEIGHT").focus();
			return false;
			}
			if($("#E_BTYPE").val()==""){
				$("#E_BTYPE").tips({
					side:3,
		            msg:'请输入血型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_BTYPE").focus();
			return false;
			}
			if($("#E_VISSION").val()==""){
				$("#E_VISSION").tips({
					side:3,
		            msg:'请输入视力',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_VISSION").focus();
			return false;
			}
			if($("#E_HEARING").val()==""){
				$("#E_HEARING").tips({
					side:3,
		            msg:'请输入听力',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_HEARING").focus();
			return false;
			}
			if($("#E_MEMORY").val()==""){
				$("#E_MEMORY").tips({
					side:3,
		            msg:'请输入记忆力',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_MEMORY").focus();
			return false;
			}
			if($("#E_MIND").val()==""){
				$("#E_MIND").tips({
					side:3,
		            msg:'请输入神智',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_MIND").focus();
			return false;
			}
			if($("#E_RELIEVE").val()==""){
				$("#E_RELIEVE").tips({
					side:3,
		            msg:'请输入大小便',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_RELIEVE").focus();
			return false;
			}
			if($("#E_STOLIC").val()==""){
				$("#E_STOLIC").tips({
					side:3,
		            msg:'请输入血压(收缩压/舒张压)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_STOLIC").focus();
			return false;
			}
			if($("#E_PULSE").val()==""){
				$("#E_PULSE").tips({
					side:3,
		            msg:'请输入脉搏',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_PULSE").focus();
			return false;
			}
			if($("#E_D_ALLERGY").val()==""){
				$("#E_D_ALLERGY").tips({
					side:3,
		            msg:'请输入药物过敏史',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_D_ALLERGY").focus();
			return false;
			}
			if($("#E_D_COMMON").val()==""){
				$("#E_D_COMMON").tips({
					side:3,
		            msg:'请输入常服药名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_D_COMMON").focus();
			return false;
			}
			if($("#E_D_CHRONIC").val()==""){
				$("#E_D_CHRONIC").tips({
					side:3,
		            msg:'请输入慢性病史',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_D_CHRONIC").focus();
			return false;
			}
			if($("#E_HNOTE").val()==""){
				$("#E_HNOTE").tips({
					side:3,
		            msg:'请输入其他健康情况',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_HNOTE").focus();
			return false;
			}
			if($("#E_A_SCORE").val()==""){
				$("#E_A_SCORE").tips({
					side:3,
		            msg:'请输入评估分数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_A_SCORE").focus();
			return false;
			}
			if($("#E_A_LEVEL").val()==""){
				$("#E_A_LEVEL").tips({
					side:3,
		            msg:'请输入评估等级',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_A_LEVEL").focus();
			return false;
			}
			if($("#E_A_LEVELLIST").val()==""){
				$("#E_A_LEVELLIST").tips({
					side:3,
		            msg:'请输入最近一次评估表',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_A_LEVELLIST").focus();
			return false;
			}
			if($("#E_AVATER").val()==""){
				$("#E_AVATER").tips({
					side:3,
		            msg:'请输入老人头像',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_AVATER").focus();
			return false;
			}
			if($("#E_PWD").val()==""){
				$("#E_PWD").tips({
					side:3,
		            msg:'请输入老人密码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_PWD").focus();
			return false;
			}
			if($("#E_GMU_ID").val()==""){
				$("#E_GMU_ID").tips({
					side:3,
		            msg:'请输入创建职工',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#E_GMU_ID").focus();
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