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
<script type="text/javascript">
//检测老人姓名(单个)
	function zzyCheck1(){
		var name=$('#ZZY_E_NAME').val();
		var gm_id=$('#GMB_GM_ID').val();
		$.ajax({
   		type: "POST",
   		url: "<%=basePath%>elder/zzyCheckByName.do",
   		data: "E_NAME="+name+'&E_GM_ID='+gm_id,
   		success: function(msg){
    	 	if(msg!=""){//该养老院中存有该老人信息
    	 		$('#GMB_E_ID').val(msg);
    	 		//alert("检测到老人信息!");
    	 		$('#IMG_E_ID').attr('src','<%=basePath%>/static/images/zzy_right.png');
    	 		$('#IMG_E_ID').css('visibility','visible');
    	 	}else{
    	 		//alert("未检测到老人信息，请重新输入!");
    	 		$('#IMG_E_ID').attr('src','<%=basePath%>/static/images/zzy_wrong.png');
    	 		$('#IMG_E_ID').css('visibility','visible');
    	 	}
  		 }
		});
	}
</script>

<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="gmberth/zzySearch.do" name="Form" id="Form" method="post">
						<input type="hidden" name="GMBERTH_ID" id="GMBERTH_ID" value="${pd.GMBERTH_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">楼栋:</td>
								<td><input type="number" name="TERM_FLOOR" id="TERM_FLOOR" value="${pd.TERM_FLOOR}" maxlength="255" placeholder="这里输入楼栋" title="楼栋" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">楼层:</td>
								<td><input type="number" name="TERM_LAYER" id="TERM_LAYER" value="${pd.TERM_LAYER}" maxlength="255" placeholder="这里输入楼层" title="楼层" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">房间:</td>
								<td><input type="text" name="TERM_ROOM" id="TERM_ROOM" value="${pd.TERM_ROOM}" maxlength="255" placeholder="这里输入房间" title="房间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">居住老人:</td>
								<td><input type="text" name="TERM_E_NAME" id="TERM_E_NAME" value="${pd.TERM_E_NAME}" maxlength="255" placeholder="这里输入老人姓名" title="老人姓名" style="width:98%;"/></td>
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