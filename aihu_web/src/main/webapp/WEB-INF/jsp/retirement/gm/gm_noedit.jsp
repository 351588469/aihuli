<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	
	function doUpload(formid) {
     var formData = new FormData($("#"+formid)[0]);
     $.ajax({
          url: '<%=basePath%>pictures/zzyAdd.do' ,
          type: 'POST',
          data: formData,
          async: false,
          cache: false,
          contentType: false,
          processData: false,
          success: function (returndata) {
          		alert('图片上传成功');
              	$('#'+'GM_'+formid).val(returndata);
              	$('#'+'A_'+formid).css('visibility','visible');
              	$('#'+'A_'+formid).attr('href','<%=basePath%>uploadFiles/uploadImgs/'+returndata); 
              	$('#'+'IMG_'+formid).attr('src','<%=basePath%>uploadFiles/uploadImgs/'+returndata); 
          },
          error: function (returndata) {
              alert(returndata);
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
								<div id="zhongxin" style="padding-top: 13px">
									<img src="<%=basePath%>/static/images/zzy_gm_noedit.png"/>
								</div>
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
		function save() {
			if ($("#GM_NAME").val() == "") {
				$("#GM_NAME").tips({
					side : 3,
					msg : '请输入名称',
					bg : '#AE81FF',
					time : 2
				});
				$("#GM_NAME").focus();
				return false;
			}
			if ($("#GM_ADDRESS").val() == "") {
				$("#GM_ADDRESS").tips({
					side : 3,
					msg : '请输入地址',
					bg : '#AE81FF',
					time : 2
				});
				$("#GM_ADDRESS").focus();
				return false;
			}
			if ($("#GM_TEL").val() == "") {
				$("#GM_TEL").tips({
					side : 3,
					msg : '请输入联系电话',
					bg : '#AE81FF',
					time : 2
				});
				$("#GM_TEL").focus();
				return false;
			}
			if ($("#GM_CONCAT").val() == "") {
				$("#GM_CONCAT").tips({
					side : 3,
					msg : '请输入联系人',
					bg : '#AE81FF',
					time : 2
				});
				$("#GM_CONCAT").focus();
				return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}

		$(function() {
			//日期框
			$('.date-picker').datepicker({
				autoclose : true,
				todayHighlight : true
			});
		});
	</script>
</body>
</html>