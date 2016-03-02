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
					
					<form action="gmbresult/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="GMBRESULT_ID" id="GMBRESULT_ID" value="${pd.GMBRESULT_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">房间编号:</td>
								<td><input type="text" name="GMBR_GMB_ID" id="GMBR_GMB_ID" value="${pd.GMBR_GMB_ID}" maxlength="32" placeholder="这里输入房间编号" title="房间编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">房间环境:</td>
								<td><input type="number" name="GMBR_ENVIRONMENT" id="GMBR_ENVIRONMENT" value="${pd.GMBR_ENVIRONMENT}" maxlength="32" placeholder="这里输入房间环境" title="房间环境" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">房间卫生:</td>
								<td><input type="number" name="GMBR_SANITATION" id="GMBR_SANITATION" value="${pd.GMBR_SANITATION}" maxlength="32" placeholder="这里输入房间卫生" title="房间卫生" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">房间安全:</td>
								<td><input type="number" name="GMBR_SAFE" id="GMBR_SAFE" value="${pd.GMBR_SAFE}" maxlength="32" placeholder="这里输入房间安全" title="房间安全" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">房间设备:</td>
								<td><input type="number" name="GMBR_EQUIPMENT" id="GMBR_EQUIPMENT" value="${pd.GMBR_EQUIPMENT}" maxlength="32" placeholder="这里输入房间设备" title="房间设备" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">房间照片数量:</td>
								<td><input type="number" name="GMBR_CPHOTO" id="GMBR_CPHOTO" value="${pd.GMBR_CPHOTO}" maxlength="32" placeholder="这里输入房间照片数量" title="房间照片数量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">第一张照片:</td>
								<td><input type="text" name="GMBR_PHOTOA" id="GMBR_PHOTOA" value="${pd.GMBR_PHOTOA}" maxlength="255" placeholder="这里输入第一张照片" title="第一张照片" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">第二张照片:</td>
								<td><input type="text" name="GMBR_PHOTOB" id="GMBR_PHOTOB" value="${pd.GMBR_PHOTOB}" maxlength="255" placeholder="这里输入第二张照片" title="第二张照片" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">第三张照片:</td>
								<td><input type="text" name="GMBR_PHOTOC" id="GMBR_PHOTOC" value="${pd.GMBR_PHOTOC}" maxlength="255" placeholder="这里输入第三张照片" title="第三张照片" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">第四张照片:</td>
								<td><input type="text" name="GMBR_PHOTOD" id="GMBR_PHOTOD" value="${pd.GMBR_PHOTOD}" maxlength="255" placeholder="这里输入第四张照片" title="第四张照片" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">房间评测描述:</td>
								<td><input type="text" name="GMBR_DESC" id="GMBR_DESC" value="${pd.GMBR_DESC}" maxlength="255" placeholder="这里输入房间评测描述" title="房间评测描述" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">评测人编号:</td>
								<td><input type="text" name="GMBR_GMU_ID" id="GMBR_GMU_ID" value="${pd.GMBR_GMU_ID}" maxlength="32" placeholder="这里输入评测人编号" title="评测人编号" style="width:98%;"/></td>
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
			if($("#GMBR_GMB_ID").val()==""){
				$("#GMBR_GMB_ID").tips({
					side:3,
		            msg:'请输入房间编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMBR_GMB_ID").focus();
			return false;
			}
			if($("#GMBR_ENVIRONMENT").val()==""){
				$("#GMBR_ENVIRONMENT").tips({
					side:3,
		            msg:'请输入房间环境',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMBR_ENVIRONMENT").focus();
			return false;
			}
			if($("#GMBR_SANITATION").val()==""){
				$("#GMBR_SANITATION").tips({
					side:3,
		            msg:'请输入房间卫生',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMBR_SANITATION").focus();
			return false;
			}
			if($("#GMBR_SAFE").val()==""){
				$("#GMBR_SAFE").tips({
					side:3,
		            msg:'请输入房间安全',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMBR_SAFE").focus();
			return false;
			}
			if($("#GMBR_EQUIPMENT").val()==""){
				$("#GMBR_EQUIPMENT").tips({
					side:3,
		            msg:'请输入房间设备',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMBR_EQUIPMENT").focus();
			return false;
			}
			if($("#GMBR_CPHOTO").val()==""){
				$("#GMBR_CPHOTO").tips({
					side:3,
		            msg:'请输入房间照片数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMBR_CPHOTO").focus();
			return false;
			}
			if($("#GMBR_PHOTOA").val()==""){
				$("#GMBR_PHOTOA").tips({
					side:3,
		            msg:'请输入第一张照片',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMBR_PHOTOA").focus();
			return false;
			}
			if($("#GMBR_PHOTOB").val()==""){
				$("#GMBR_PHOTOB").tips({
					side:3,
		            msg:'请输入第二张照片',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMBR_PHOTOB").focus();
			return false;
			}
			if($("#GMBR_PHOTOC").val()==""){
				$("#GMBR_PHOTOC").tips({
					side:3,
		            msg:'请输入第三张照片',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMBR_PHOTOC").focus();
			return false;
			}
			if($("#GMBR_PHOTOD").val()==""){
				$("#GMBR_PHOTOD").tips({
					side:3,
		            msg:'请输入第四张照片',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMBR_PHOTOD").focus();
			return false;
			}
			if($("#GMBR_DESC").val()==""){
				$("#GMBR_DESC").tips({
					side:3,
		            msg:'请输入房间评测描述',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMBR_DESC").focus();
			return false;
			}
			if($("#GMBR_GMU_ID").val()==""){
				$("#GMBR_GMU_ID").tips({
					side:3,
		            msg:'请输入评测人编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMBR_GMU_ID").focus();
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