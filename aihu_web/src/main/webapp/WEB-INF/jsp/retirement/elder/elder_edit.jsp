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
              	$('#'+'E_'+formid).val(returndata);
              	$('#'+'A_'+formid).css('visibility','visible');
              	$('#'+'A_'+formid).attr('href','<%=basePath%>uploadFiles/uploadImgs/'+returndata); 
              	$('#'+'IMG_'+formid).attr('src','<%=basePath%>uploadFiles/uploadImgs/'+returndata); 
              	//alert($('#'+'E_'+formid).val());
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
												<c:if test="${pd.E_AVATER!=null&&pd.E_AVATER!=''}">
												<a target="_blank" style="" id="A_AVATER"
													href="<%=basePath%>uploadFiles/uploadImgs/${pd.E_AVATER}">
													<img src="<%=basePath%>uploadFiles/uploadImgs/${pd.E_AVATER}" id="IMG_AVATER"
													style="width:30px;height:30px"
													/>
												</a>
												</c:if>
												<c:if test="${pd.E_AVATER==null||pd.E_AVATER==''}">
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
					<form action="elder/${msg}.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ELDER_ID" id="ELDER_ID" value="${pd.ELDER_ID}"/>
						<input type="hidden" name="E_AVATER" id="E_AVATER" value="${pd.E_AVATER}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">养老院:</td>
								<td>
									<input type="hidden"name="E_GM_ID" id="E_GM_ID" value="${GM_ID}">
									<input type="text" readonly="readonly" value="${GM_NAME}" style="width:98%"/>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人姓名:</td>
								<td><input type="text" name="E_NAME" id="E_NAME" value="${pd.E_NAME}" maxlength="20" placeholder="这里输入老人姓名" title="老人姓名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">老人性别:</td>
								<td>
									<input type="hidden" readonly="readonly" name="E_GENDER" id="E_GENDER" value="${pd.E_GENDER}"/>
									<select  name="SELECT_GENDER" id="SELECT_GENDER" style="width:50%"
									onchange="$('#E_GENDER').val(this.value)">
									<c:if test="${pd.E_GENDER==''||pd.E_GENDER==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="男" <c:if test="${pd.E_GENDER=='男'}">selected</c:if>>男</option>
   									<option value="女" <c:if test="${pd.E_GENDER=='女'}">selected</c:if>>女</option>
   									</select>
   								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">入住状态</td>
								<td>
									<input type="hidden" name="E_INTAKE" id="E_INTAKE" value="${pd.E_INTAKE}"/>
									<select  name="SELECT_INTAKE" id="SELECT_INTAKE" style="width:98%;"
									onchange="$('#E_INTAKE').val(this.value)">
									<c:if test="${pd.E_INTAKE==''||pd.E_INTAKE==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="1" <c:if test="${pd.E_INTAKE=='1'}">selected</c:if>>入住</option>
   									<option value="2" <c:if test="${pd.E_INTAKE=='2'}">selected</c:if>>退住</option>
   									<option value="3" <c:if test="${pd.E_INTAKE=='3'}">selected</c:if>>试住</option>
 									</select>
 								</td>
								<!--<td><input type="number" name="E_INTAKE" id="E_INTAKE" value="${pd.E_INTAKE}" maxlength="32" placeholder="这里输入入住状态(1入住2退住3试住)" title="入住状态(1入住2退住3试住)" style="width:98%;"/></td>
								-->
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
								<td>
									<input type="hidden" readonly="readonly" name="E_BTYPE" id="E_BTYPE" value="${pd.E_BTYPE}"/>
									<select  name="SELECT_BTYPE" id="SELECT_BTYPE" style="width:50%"
									onchange="$('#E_BTYPE').val(this.value)">
									<c:if test="${pd.E_BTYPE==''||pd.E_BTYPE==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="A" <c:if test="${pd.E_BTYPE=='A'}">selected</c:if>>A</option>
   									<option value="B" <c:if test="${pd.E_BTYPE=='B'}">selected</c:if>>B</option>
   									<option value="AB" <c:if test="${pd.E_BTYPE=='AB'}">selected</c:if>>AB</option>
   									<option value="O" <c:if test="${pd.E_BTYPE=='O'}">selected</c:if>>O</option>
   									<option value="RH" <c:if test="${pd.E_BTYPE=='RH'}">selected</c:if>>RH</option>
   									</select>
   								</td>
								<!--<td><input type="text" name="E_BTYPE" id="E_BTYPE" value="${pd.E_BTYPE}" maxlength="10" placeholder="这里输入血型" title="血型" style="width:98%;"/></td>
								-->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">视力:</td>
								<td>
									<input type="hidden" name="E_VISSION" id="E_VISSION" value="${pd.E_VISSION}"/>
									<select  name="SELECT_VISSION" id="SELECT_VISSION" style="width:50%;"
									onchange="$('#E_VISSION').val(this.value)">
    								<c:if test="${pd.E_VISSION==''||pd.E_VISSION==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="1" <c:if test="${pd.E_VISSION=='1'}">selected</c:if>>很差</option>
    								<option value="2" <c:if test="${pd.E_VISSION=='2'}">selected</c:if>>较差</option>
    								<option value="3" <c:if test="${pd.E_VISSION=='3'}">selected</c:if>>一般</option>
    								<option value="4" <c:if test="${pd.E_VISSION=='4'}">selected</c:if>>较好</option>
    								<option value="5" <c:if test="${pd.E_VISSION=='5'}">selected</c:if>>好</option>
 									</select>
 								</td>
								<!--<td><input type="text" name="E_VISSION" id="E_VISSION" value="${pd.E_VISSION}" maxlength="20" placeholder="这里输入视力" title="视力" style="width:98%;"/></td>-->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">听力:</td>
								<td>
									<input type="hidden" name="E_HEARING" id="E_HEARING" value="${pd.E_HEARING}"/>
									<select  name="SELECT_HEARING" id="SELECT_HEARING" style="width:50%;"
									onchange="$('#E_HEARING').val(this.value)">
    								<c:if test="${pd.E_HEARING==''||pd.E_HEARING==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="1" <c:if test="${pd.E_HEARING=='1'}">selected</c:if>>很差</option>
    								<option value="2" <c:if test="${pd.E_HEARING=='2'}">selected</c:if>>较差</option>
    								<option value="3" <c:if test="${pd.E_HEARING=='3'}">selected</c:if>>一般</option>
    								<option value="4" <c:if test="${pd.E_HEARING=='4'}">selected</c:if>>较好</option>
    								<option value="5" <c:if test="${pd.E_HEARING=='5'}">selected</c:if>>好</option>
 									</select>
 								</td>
								<!--<td><input type="text" name="E_HEARING" id="E_HEARING" value="${pd.E_HEARING}" maxlength="20" placeholder="这里输入听力" title="听力" style="width:98%;"/></td>-->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">记忆力:</td>
								<td>
									<input type="hidden" name="E_MEMORY" id="E_MEMORY" value="${pd.E_MEMORY}"/>
									<select  name="SELECT_MEMORY" id="SELECT_MEMORY" style="width:50%;"
									onchange="$('#E_MEMORY').val(this.value)">
    								<c:if test="${pd.E_MEMORY==''||pd.E_MEMORY==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="1" <c:if test="${pd.E_MEMORY=='1'}">selected</c:if>>很差</option>
    								<option value="2" <c:if test="${pd.E_MEMORY=='2'}">selected</c:if>>较差</option>
    								<option value="3" <c:if test="${pd.E_MEMORY=='3'}">selected</c:if>>一般</option>
    								<option value="4" <c:if test="${pd.E_MEMORY=='4'}">selected</c:if>>较好</option>
    								<option value="5" <c:if test="${pd.E_MEMORY=='5'}">selected</c:if>>好</option>
 									</select>
 								</td>
								<!--<td><input type="text" name="E_MEMORY" id="E_MEMORY" value="${pd.E_MEMORY}" maxlength="20" placeholder="这里输入记忆力" title="记忆力" style="width:98%;"/></td>
								-->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">神智:</td>
								<td>
									<input type="hidden" name="E_MIND" id="E_MIND" value="${pd.E_MIND}"/>
									<select  name="SELECT_MIND" id="SELECT_MIND" style="width:50%;"
									onchange="$('#E_MIND').val(this.value)">
    								<c:if test="${pd.E_MIND==''||pd.E_MIND==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="1" <c:if test="${pd.E_MIND=='1'}">selected</c:if>>很差</option>
    								<option value="2" <c:if test="${pd.E_MIND=='2'}">selected</c:if>>较差</option>
    								<option value="3" <c:if test="${pd.E_MIND=='3'}">selected</c:if>>一般</option>
    								<option value="4" <c:if test="${pd.E_MIND=='4'}">selected</c:if>>较好</option>
    								<option value="5" <c:if test="${pd.E_MIND=='5'}">selected</c:if>>好</option>
 									</select>
 								</td>
								<!--<td><input type="text" name="E_MIND" id="E_MIND" value="${pd.E_MIND}" maxlength="20" placeholder="这里输入神智" title="神智" style="width:98%;"/></td>
								-->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">大小便:</td>
								<td>
									<input type="hidden" name="E_RELIEVE" id="E_RELIEVE" value="${pd.E_RELIEVE}"/>
									<select  name="SELECT_RELIEVE" id="SELECT_RELIEVE" style="width:50%;"
									onchange="$('#E_RELIEVE').val(this.value)">
    								<c:if test="${pd.E_RELIEVE==''||pd.E_RELIEVE==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="1" <c:if test="${pd.E_RELIEVE=='1'}">selected</c:if>>很差</option>
    								<option value="2" <c:if test="${pd.E_RELIEVE=='2'}">selected</c:if>>较差</option>
    								<option value="3" <c:if test="${pd.E_RELIEVE=='3'}">selected</c:if>>一般</option>
    								<option value="4" <c:if test="${pd.E_RELIEVE=='4'}">selected</c:if>>较好</option>
    								<option value="5" <c:if test="${pd.E_RELIEVE=='5'}">selected</c:if>>好</option>
 									</select>
 								</td>
								<!--<td><input type="text" name="E_RELIEVE" id="E_RELIEVE" value="${pd.E_RELIEVE}" maxlength="20" placeholder="这里输入大小便" title="大小便" style="width:98%;"/></td>
								 -->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">血压:</td>
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
								<td style="width:75px;text-align: right;padding-top: 13px;">老人密码:</td>
								<td><input type="text" name="E_PWD" id="E_PWD" value="${pd.E_PWD}" maxlength="64" placeholder="这里输入老人密码" title="老人密码" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td>
									<c:if test="${pd.E_GMU_ID==''||pd.E_GMU_ID==null}">
									
									</c:if>
									<input type="hidden" name="E_GMU_ID" id="E_GMU_ID" value="${user.USER_ID}"/>
									<input type="text" readonly="readonly" value="${user.USERNAME}"/>
								</td>
								<!--<td><input type="text" name="E_GMU_ID" id="E_GMU_ID" value="${pd.E_GMU_ID}" maxlength="32" placeholder="这里输入创建职工" title="创建职工" style="width:98%;"/></td>
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