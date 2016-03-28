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
							<!--图片处理开始 -->
								<div id="zhongxin" style="padding-top: 13px;">
									<table id="table_report"
										class="table table-striped table-bordered table-hover">
										<!-- 单张图片处理开始 -->
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">上传头像:</td>
											<td>
												<c:if test="${pd.GM_AVATER!=null&&pd.GM_AVATER!=''}">
												<a target="_blank" style="" id="A_AVATER"
													href="<%=basePath%>uploadFiles/uploadImgs/${pd.GM_AVATER}">
													<img src="<%=basePath%>uploadFiles/uploadImgs/${pd.GM_AVATER}" id="IMG_AVATER"
													style="width:30px;height:30px"
													/>
												</a>
												</c:if>
												<c:if test="${pd.GM_AVATER==null||pd.GM_AVATER==''}">
												<a target="_blank" style="visibility:hidden;" id="A_AVATER"
													href="">
													<img src=""
													style="width:30px;height:30px"
													/>
												</a>
												</c:if>
											</td>
											<td>
												<form id="AVATER">
												<input style="padding:0;margin:0;width:200px;float:left" type="file" name="file"/>
												<input style="padding:0;margin:0;float:left" type="button" value="上传" onclick="doUpload('AVATER')" />
												</form>
											</td>
										</tr>
										<!-- 单张图片处理结束 -->
										<!-- 单张图片处理开始 -->
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">营业执照图片:</td>
											<td>
												<c:if test="${pd.GM_LICENCE_PHOTO!=null&&pd.GM_LICENCE_PHOTO!=''}">
												<a target="_blank" style="" id="A_LICENCE_PHOTO"
													href="<%=basePath%>uploadFiles/uploadImgs/${pd.GM_LICENCE_PHOTO}">
													<img src="<%=basePath%>uploadFiles/uploadImgs/${pd.GM_LICENCE_PHOTO}" id="IMG_LICENCE_PHOTO"
													style="width:30px;height:30px"
													/>
												</a>
												</c:if>
												<c:if test="${pd.GM_LICENCE_PHOTO==null||pd.GM_LICENCE_PHOTO==''}">
												<a target="_blank" style="visibility:hidden;" id="A_LICENCE_PHOTO"
													href="">
													<img src="" id="IMG_AVATER"
													style="width:30px;height:30px"
													/>
												</a>
												</c:if>
											</td>
											<td>
												<form id="LICENCE_PHOTO">
												<input style="padding:0;margin:0;width:200px;float:left" type="file" name="file"/>
												<input style="padding:0;margin:0;float:left" type="button" value="上传" onclick="doUpload('LICENCE_PHOTO')" />
												</form>
											</td>
										</tr>
										<!-- 单张图片处理结束 -->
										<!-- 单张图片处理开始 -->
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">法人身份证正面:</td>
											<td>
												<c:if test="${pd.GM_LEGALPERSON_PHOTOA!=null&&pd.GM_LEGALPERSON_PHOTOA!=''}">
												<a target="_blank" style="" id="A_LEGALPERSON_PHOTOA"
													href="<%=basePath%>uploadFiles/uploadImgs/${pd.GM_LEGALPERSON_PHOTOA}">
													<img src="<%=basePath%>uploadFiles/uploadImgs/${pd.GM_LEGALPERSON_PHOTOA}" id="IMG_LEGALPERSON_PHOTOA"
													style="width:30px;height:30px"
													/>
												</a>
												</c:if>
												<c:if test="${pd.GM_LEGALPERSON_PHOTOA==null||pd.GM_LEGALPERSON_PHOTOA==''}">
												<a target="_blank" style="visibility:hidden;" id="A_LEGALPERSON_PHOTOA"
													href="">
													<img src="" id="IMG_LEGALPERSON_PHOTOA"
													style="width:30px;height:30px"
													/>
												</a>
												</c:if>
											</td>
											<td>
												<form id="LEGALPERSON_PHOTOA">
												<input style="padding:0;margin:0;width:200px;float:left" type="file" name="file"/>
												<input style="padding:0;margin:0;float:left" type="button" value="上传" onclick="doUpload('LEGALPERSON_PHOTOA')" />
												</form>
											</td>
										</tr>
										<!-- 单张图片处理结束 -->
										<!-- 单张图片处理开始 -->
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">法人身份证反面:</td>
											<td>
												<c:if test="${pd.GM_LEGALPERSON_PHOTOB!=null&&pd.GM_LEGALPERSON_PHOTOB!=''}">
												<a target="_blank" style="" id="A_LEGALPERSON_PHOTOB"
													href="<%=basePath%>uploadFiles/uploadImgs/${pd.GM_LEGALPERSON_PHOTOB}">
													<img src="<%=basePath%>uploadFiles/uploadImgs/${pd.GM_LEGALPERSON_PHOTOB}" id="IMG_LEGALPERSON_PHOTOB"
													style="width:30px;height:30px"
													/>
												</a>
												</c:if>
												<c:if test="${pd.GM_LEGALPERSON_PHOTOB==null||pd.GM_LEGALPERSON_PHOTOB==''}">
												<a target="_blank" style="visibility:hidden;" id="A_LEGALPERSON_PHOTOB"
													href="">
													<img src="" id="IMG_LEGALPERSON_PHOTOB"style="width:30px;height:30px"/>
												</a>
												</c:if>
											</td>
											<td>
												<form id="LEGALPERSON_PHOTOB">
												<input style="padding:0;margin:0;width:200px;float:left" type="file" name="file"/>
												<input style="padding:0;margin:0;float:left" type="button" value="上传" onclick="doUpload('LEGALPERSON_PHOTOB')" />
												</form>
											</td>
										</tr>
										<!-- 单张图片处理结束 -->
									</table>
								</div>
							<!-- 图片处理结束 -->
							<form action="gm/${msg}.do" name="Form" id="Form" method="post">
								<input type="hidden" name="GM_ID" id="GM_ID" value="${pd.GM_ID}" />
								<input type="hidden" name="GM_LICENCE_PHOTO" id="GM_LICENCE_PHOTO"/>
								<input type="hidden" name="GM_LEGALPERSON_PHOTOA" id="GM_LEGALPERSON_PHOTOA"/>
								<input type="hidden" name="GM_LEGALPERSON_PHOTOB" id="GM_LEGALPERSON_PHOTOB"/>
								<input type="hidden" name="GM_AVATER" id="GM_AVATER"/>
								<div id="zhongxin" style="padding-top: 13px;">
									<table id="table_report"
										class="table table-striped table-bordered table-hover">
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
											<td><input type="text" name="GM_NAME" id="GM_NAME"
												value="${pd.GM_NAME}" maxlength="50" placeholder="这里输入名称"
												title="名称" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">地址:</td>
											<td><input type="text" name="GM_ADDRESS" id="GM_ADDRESS"
												value="${pd.GM_ADDRESS}" maxlength="255"
												placeholder="这里输入地址" title="地址" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">联系电话:</td>
											<td><input type="text" name="GM_TEL" id="GM_TEL"
												value="${pd.GM_TEL}" maxlength="20" placeholder="这里输入联系电话"
												title="联系电话" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">联系人:</td>
											<td><input type="text" name="GM_CONCAT" id="GM_CONCAT"
												value="${pd.GM_CONCAT}" maxlength="10" placeholder="这里输入联系人"
												title="联系人" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">性质:</td>
											<td><input type="text" name="GM_NATURE" id="GM_NATURE"
												value="${pd.GM_NATURE}" maxlength="50" placeholder="这里输入性质"
												title="性质" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">营业面积:</td>
											<td><input type="text" name="GM_SQUARE" id="GM_SQUARE"
												value="${pd.GM_SQUARE}" maxlength="20"
												placeholder="这里输入营业面积" title="营业面积" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">床位数:</td>
											<td><input type="number" name="GM_BERTH_COUNT"
												id="GM_BERTH_COUNT" value="${pd.GM_BERTH_COUNT}"
												maxlength="32" placeholder="这里输入床位数" title="床位数"
												style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">简介:</td>
											<td><input type="text" name="GM_DESCRIPTION"
												id="GM_DESCRIPTION" value="${pd.GM_DESCRIPTION}"
												maxlength="255" placeholder="这里输入简介" title="简介"
												style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">收住对象:</td>
											<td><input type="text" name="GM_RECEIVE" id="GM_RECEIVE"
												value="${pd.GM_RECEIVE}" maxlength="255"
												placeholder="这里输入收住对象" title="收住对象" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">收费标准:</td>
											<td><input type="text" name="GM_FEEDESC "
												id="GM_FEEDESC " value="${pd.GM_FEEDESC}" maxlength="255"
												placeholder="这里输入收费标准" title="收费标准" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">服务内容:</td>
											<td><input type="text" name="GM_SERVEINFO"
												id="GM_SERVEINFO" value="${pd.GM_SERVEINFO}" maxlength="255"
												placeholder="这里输入服务内容" title="服务内容" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="text-align: center;" colspan="10"><a
												class="btn btn-mini btn-primary" onclick="save();">保存</a> <a
												class="btn btn-mini btn-danger"
												onclick="top.Dialog.close();">取消</a></td>
										</tr>
									</table>
								</div>

								<div id="zhongxin2" class="center" style="display:none">
									<br />
									<br />
									<br />
									<br />
									<br />
									<img src="static/images/jiazai.gif" /><br />
									<h4 class="lighter block green">提交中...</h4>
								</div>

							</form>

							<div id="zhongxin2" class="center" style="display:none">
								<img src="static/images/jzx.gif" style="width: 50px;" /><br />
								<h4 class="lighter block green"></h4>
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