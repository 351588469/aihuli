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
              	$('#'+'GMU_'+formid).val(returndata);
              	$('#'+'A_'+formid).css('visibility','visible');
              	$('#'+'A_'+formid).attr('href','<%=basePath%>uploadFiles/uploadImgs/'+returndata); 
              	$('#'+'IMG_'+formid).attr('src','<%=basePath%>uploadFiles/uploadImgs/'+returndata); 
              	//alert($('#'+'GMU_'+formid).val());
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
												<c:if test="${pd.GMU_AVATER!=null&&pd.GMU_AVATER!=''}">
												<a target="_blank" style="" id="A_AVATER"
													href="<%=basePath%>uploadFiles/uploadImgs/${pd.GMU_AVATER}">
													<img src="<%=basePath%>uploadFiles/uploadImgs/${pd.GMU_AVATER}" id="IMG_AVATER"
													style="width:30px;height:30px"
													/>
												</a>
												</c:if>
												<c:if test="${pd.GMU_AVATER==null||pd.GMU_AVATER==''}">
												<a target="_blank" style="visibility:hidden;" id="A_AVATER"
													href="">
													<img src="" id="IMG_AVATER"
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
									</table>
								</div>
					<!-- 图片处理结束 -->
					<form action="gmuser/${msg}.do" name="Form" id="Form" method="post">
						<input type="hidden" name="GMUSER_ID" id="GMUSER_ID" value="${pd.GMUSER_ID}"/>
						<input type="hidden" name="GMU_AVATER" id="GMU_AVATER" value="${pd.GMU_AVATER}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align:center;padding:0;margin:0">
									养老院:
								</td>
								<td>
									<input type="hidden"name="GMU_GM_ID" id="GMU_GM_ID" value="${GM_ID}">
									<input type="text" readonly="readonly" value="${GM_NAME}" style="width:98%"/>
								</td>
								<!--<td><input type="text" name="GMU_GM_ID" id="GMU_GM_ID" value="${pd.GMU_GM_ID}" maxlength="32" placeholder="这里输入职工所属养老院编号" title="职工所属养老院编号" style="width:98%;"/></td>
								-->
							</tr>
							<tr>
								<td style="width:75px;padding:0;margin:0; text-align: center;">
									<img style="margin:0;padding:0;float:right" src="<%=basePath%>plugins/zoomimage/images/star.png"><br/>
									姓名:
								</td>
								<td>
									<input type="text" name="GMU_NAME" id="GMU_NAME" value="${pd.GMU_NAME}" maxlength="20" placeholder="这里输入职工姓名" title="职工姓名" style="width:98%;"/>
								</td>
							</tr>
							<tr>
								<td style="width:75px;padding:0;margin:0; text-align: center;">
									<img style="margin:0;padding:0;float:right" src="<%=basePath%>plugins/zoomimage/images/star.png"><br/>
									性别:
								</td>
								<td>
									<input type="hidden" readonly="readonly" name="GMU_GENDER" id="GMU_GENDER" value="${pd.GMU_GENDER}"/>
									<select  name="SELECT_GENDER" id="SELECT_GENDER" style="width:50%"
									onchange="$('#GMU_GENDER').val(this.value)">
									<c:if test="${pd.GMU_GENDER==''||pd.GMU_GENDER==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="男" <c:if test="${pd.GMU_GENDER=='男'}">selected</c:if>>男</option>
   									<option value="女" <c:if test="${pd.GMU_GENDER=='女'}">selected</c:if>>女</option>
   									</select>
 								</td>
								<!-- <td><input type="text" name="GMU_GENDER" id="GMU_GENDER" value="${pd.GMU_GENDER}" maxlength="5" placeholder="这里输入职工性别" title="职工性别" style="width:98%;"/></td>
								 -->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">生日:</td>
								<td><input class="span10 date-picker" name="GMU_BIRTHDAY" id="GMU_BIRTHDAY" value="${pd.GMU_BIRTHDAY}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="职工生日" title="职工生日" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;padding:0;margin:0; text-align: center;">
									<img style="margin:0;padding:0;float:right" src="<%=basePath%>plugins/zoomimage/images/star.png"><br/>
									入职时间:
								</td>
								<td><input class="span10 date-picker" name="GM_EDATE" id="GM_EDATE" value="${pd.GM_EDATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="职工入职时间" title="职工入职时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;padding:0;margin:0; text-align: center;">
									<img style="margin:0;padding:0;float:right" src="<%=basePath%>plugins/zoomimage/images/star.png"><br/>
									职务:
								</td>
								<td>
									<input type="hidden" readonly="readonly" name="GMU_DUTIES" id="GMU_DUTIES" value="${pd.GMU_DUTIES}"/>
									<select  name="SELECT_DUTIES" id="SELECT_DUTIES" style="width:50%"
									onchange="$('#GMU_DUTIES').val(this.value)">
									<c:if test="${pd.GMU_DUTIES==''||pd.GMU_DUTIES==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="1" <c:if test="${pd.GMU_DUTIES=='1'}">selected</c:if>>护士长</option>
   									<option value="2" <c:if test="${pd.GMU_DUTIES=='2'}">selected</c:if>>护士</option>
   									</select>
 								</td>
								<!--<td><input type="number" name="GMU_DUTIES" id="GMU_DUTIES" value="${pd.GMU_DUTIES}" maxlength="32" placeholder="这里输入职工职务" title="职工职务" style="width:98%;"/></td>
							 	 --> 
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">健康状况:</td>
								<td>
									<input type="hidden" readonly="readonly" name="GMU_HEALTH" id="GMU_HEALTH" value="${pd.GMU_HEALTH}" />
									<select  name="SELECT_HEALTH" id="SELECT_HEALTH" style="width:50%"
									onchange="$('#GMU_HEALTH').val(this.value)">
									<c:if test="${pd.GMU_HEALTH==''||pd.GMU_HEALTH==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="健康" <c:if test="${pd.GMU_HEALTH=='健康'}">selected</c:if>>健康</option>
   									<option value="亚健康" <c:if test="${pd.GMU_HEALTH=='亚健康'}">selected</c:if>>亚健康</option>
   									<option value="不健康" <c:if test="${pd.GMU_HEALTH=='不健康'}">selected</c:if>>不健康</option>
 									</select>
 								</td>
								<!-- <td><input type="text" name="GMU_HEALTH" id="GMU_HEALTH" value="${pd.GMU_HEALTH}" maxlength="20" placeholder="这里输入职工健康状况" title="职工健康状况" style="width:98%;"/></td>
								 -->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">婚姻状况:</td>
								<td>
									<input type="hidden"readonly="readonly" name="GMU_MARRIAGE" id="GMU_MARRIAGE" value="${pd.GMU_MARRIAGE}" />
									<select  name="SELECT_MARRIAGE" id="SELECT_MARRIAGE" style="width:50%"
									onchange="$('#GMU_MARRIAGE').val(this.value)">
									<c:if test="${pd.GMU_MARRIAGE==''||pd.GMU_MARRIAGE==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="已婚" <c:if test="${pd.GMU_MARRIAGE=='已婚'}">selected</c:if>>已婚</option>
   									<option value="未婚" <c:if test="${pd.GMU_MARRIAGE=='未婚'}">selected</c:if>>未婚</option>
 									</select>
 								</td>
								<!-- <td><input type="text" name="GMU_MARRIAGE" id="GMU_MARRIAGE" value="${pd.GMU_MARRIAGE}" maxlength="10" placeholder="这里输入职工婚姻状况" title="职工婚姻状况" style="width:98%;"/></td>
								 -->
							</tr>
							<tr>
								<td style="width:75px;padding:0;margin:0; text-align: center;">
								<img style="margin:0;padding:0;float:right" src="<%=basePath%>plugins/zoomimage/images/star.png"><br/>
								联系电话:
								</td>
								<td><input type="text" name="GMU_TEL" id="GMU_TEL" value="${pd.GMU_TEL}" maxlength="20" placeholder="这里输入职工联系电话" title="职工联系电话" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">电子邮箱:</td>
								<td><input type="text" name="GMU_EMAIL" id="GMU_EMAIL" value="${pd.GMU_EMAIL}" maxlength="50" placeholder="这里输入职工电子邮箱" title="职工电子邮箱" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工户籍:</td>
								<td><input type="text" name="GMU_CENSUS" id="GMU_CENSUS" value="${pd.GMU_CENSUS}" maxlength="255" placeholder="这里输入职工户籍" title="职工户籍" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">家庭住址:</td>
								<td><input type="text" name="GMU_ADDRESS" id="GMU_ADDRESS" value="${pd.GMU_ADDRESS}" maxlength="255" placeholder="这里输入职工家庭住址" title="职工家庭住址" style="width:98%;"/></td>
							</tr>
							<tr>
							<td style="width:75px;padding:0;margin:0; text-align: center;">
									<img style="margin:0;padding:0;float:right" src="<%=basePath%>plugins/zoomimage/images/star.png"><br/>
									入职状态:
								</td>
								<td>
									<input type="hidden" readonly="readonly"name="GMU_ESTATUS" id="GMU_ESTATUS" value="${pd.GMU_ESTATUS}"/>
									<select  name="SELECT_ESTATUS" id="SELECT_ESTATUS" style="width:50%"
									onchange="$('#GMU_ESTATUS').val(this.value)">
									<c:if test="${pd.GMU_ESTATUS==''||pd.GMU_ESTATUS==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="1" <c:if test="${pd.GMU_ESTATUS=='1'}">selected</c:if>>入职</option>
   									<option value="2" <c:if test="${pd.GMU_ESTATUS=='2'}">selected</c:if>>离职</option>
 									<option value="3" <c:if test="${pd.GMU_ESTATUS=='3'}">selected</c:if>>请假</option>
 									</select>
 								</td>
								<!--<td><input type="number" name="GMU_ESTATUS" id="GMU_ESTATUS" value="${pd.GMU_ESTATUS}" maxlength="32" placeholder="这里输入职工入职状态(1:入职2:离职3:请假)" title="职工入职状态(1:入职2:离职3:请假)" style="width:98%;"/></td>-->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工学历:</td>
								<td>
									<input type="hidden"readonly="readonly" name="GMU_EDUCATION" id="GMU_EDUCATION" value="${pd.GMU_EDUCATION}"/>
									<select  name="SELECT_EDUCATION" id="SELECT_EDUCATION" style="width:50%"
									onchange="$('#GMU_EDUCATION').val(this.value)">
									<c:if test="${pd.GMU_EDUCATION==''||pd.GMU_EDUCATION==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="高中及以下" <c:if test="${pd.GMU_EDUCATION=='高中及以下'}">selected</c:if>>高中及以下</option>
    								<option value="大专" <c:if test="${pd.GMU_EDUCATION=='大专'}">selected</c:if>>大专</option>
    								<option value="本科" <c:if test="${pd.GMU_EDUCATION=='本科'}">selected</c:if>>本科</option>
    								<option value="研究生及以上" <c:if test="${pd.GMU_EDUCATION=='研究生及以上'}">selected</c:if>>研究生及以上</option>
 									</select>
    								
 								</td>
								<!--<td><input type="text" name="GMU_EDUCATION" id="GMU_EDUCATION" value="${pd.GMU_EDUCATION}" maxlength="20" placeholder="这里输入职工学历" title="职工学历" style="width:98%;"/></td>
								 -->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">毕业院校:</td>
								<td><input type="text" name="GMU_COLLAGE" id="GMU_COLLAGE" value="${pd.GMU_COLLAGE}" maxlength="50" placeholder="这里输入职工毕业院校" title="职工毕业院校" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工专业:</td>
								<td><input type="text" name="GMU_MAJOR" id="GMU_MAJOR" value="${pd.GMU_MAJOR}" maxlength="50" placeholder="这里输入职工专业" title="职工专业" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">英语水平:</td>
								<td>
									<input type="hidden" name="GMU_LENGLISH" id="GMU_LENGLISH" value="${pd.GMU_LENGLISH}"/>
									<select  name="SELECT_LENGLISH" id="SELECT_LENGLISH" style="width:50%;"
									onchange="$('#GMU_LENGLISH').val(this.value)">
    								<c:if test="${pd.GMU_LENGLISH==''||pd.GMU_LENGLISH==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="低" <c:if test="${pd.GMU_LENGLISH=='低'}">selected</c:if>>低</option>
    								<option value="一般" <c:if test="${pd.GMU_LENGLISH=='一般'}">selected</c:if>>一般</option>
    								<option value="较好" <c:if test="${pd.GMU_LENGLISH=='较好'}">selected</c:if>>较好</option>
    								<option value="好" <c:if test="${pd.GMU_LENGLISH=='好'}">selected</c:if>>好</option>
 									</select>
 								</td>
								<!--<td><input type="text" name="GMU_LENGLISH" id="GMU_LENGLISH" value="${pd.GMU_LENGLISH}" maxlength="20" placeholder="这里输入职工英语水平" title="职工英语水平" style="width:98%;"/></td>
								-->
							</tr>
							<tr>
								<td style="width:75px;text-align:center;padding:0;margin:0;valign:middle"><br/>计算机水平:</td>
								<td>
									<input type="hidden"name="GMU_LCOMPUTER" id="GMU_LCOMPUTER" value="${pd.GMU_LCOMPUTER}"/>
									<select  name="SELECT_LCOMPUTER" id="SELECT_LCOMPUTER" style="width:50%"
									onchange="$('#GMU_LCOMPUTER').val(this.value)">
    								<c:if test="${pd.GMU_LCOMPUTER==''||pd.GMU_LCOMPUTER==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="低" <c:if test="${pd.GMU_LCOMPUTER=='低'}">selected</c:if>>低</option>
    								<option value="一般" <c:if test="${pd.GMU_LCOMPUTER=='一般'}">selected</c:if>>一般</option>
    								<option value="较好" <c:if test="${pd.GMU_LCOMPUTER=='较好'}">selected</c:if>>较好</option>
    								<option value="好" <c:if test="${pd.GMU_LCOMPUTER=='好'}">selected</c:if>>好</option>
 									</select>
 								</td>
								<!--<td><input type="text" name="GMU_LCOMPUTER" id="GMU_LCOMPUTER" value="${pd.GMU_LCOMPUTER}" maxlength="20" placeholder="这里输入职工计算机水平" title="职工计算机水平" style="width:98%;"/></td>
								-->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工工资:</td>
								<td><input type="text" name="GMU_WAGE" id="GMU_WAGE" value="${pd.GMU_WAGE}" maxlength="10" placeholder="这里输入职工工资" title="职工工资" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">职工密码:</td>
								<td><input type="text" name="GMU_PWD" id="GMU_PWD" value="${pd.GMU_PWD}" maxlength="64" placeholder="（若不输入则为默认密码:123456）" title="职工密码" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建用户:</td>
								<td>
									<input type="text" name="GMU_GMUID" id="GMU_GMUID" value='${user.USER_ID}' style="display:none"/>
									<input type="text" value='${user.NAME}' readonly="readonly" style="width:98%;"/>
								</td>
								<!--<td><input type="text" name="GMU_GMUID" id="GMU_GMUID" value="${pd.GMU_GMUID}" maxlength="32" placeholder="这里输入创建用户" title="创建用户" style="width:98%;"/></td>
								 -->
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
				$("#SELECT_GM_ID").tips({
					side:3,
		            msg:'请选择养老院',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SELECT_GM_ID").focus();
			return false;
			}
			if($("#GMU_DUTIES").val()==""){
				$("#SELECT_DUTIES").tips({
					side:3,
		            msg:'请选择职务',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SELECT_DUTIES").focus();
			return false;
			}
			if($("#GMU_GENDER").val()==""){
				$("#SELECT_GENDER").tips({
					side:3,
		            msg:'请选择性别',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SELECT_GENDER").focus();
			return false;
			}
			if($("#GMU_EDATE").val()==""){
				$("#GMU_EDATE").tips({
					side:3,
		            msg:'请输入职工入职时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMU_EDATE").focus();
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