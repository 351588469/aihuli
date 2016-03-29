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
					
					<form action="gmberth/${msg}.do" name="Form" id="Form" method="post">
						<input type="hidden" name="GMBERTH_ID" id="GMBERTH_ID" value="${pd.GMBERTH_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">养老院:</td>
								<td>
									<input type="hidden"name="GMB_GM_ID" id="GMB_GM_ID" value="${GM_ID}">
									<input type="text" readonly="readonly" value="${GM_NAME}" style="width:98%"/>
								</td>
								<!--<td><input type="text" name="GMU_GM_ID" id="GMU_GM_ID" value="${pd.GMU_GM_ID}" maxlength="32" placeholder="这里输入职工所属养老院编号" title="职工所属养老院编号" style="width:98%;"/></td>
								-->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">数据类别:</td>
								<td>
									<input type="hidden" name="GMB_TYPE" id="GMB_TYPE" value="3"/>
									<input type="text" readonly="readonly" value="房间"/>
 								</td>
								<!--<td><input type="number" name="GMB_TYPE" id="GMB_TYPE" value="${pd.GMB_TYPE}" maxlength="32" placeholder="这里输入数据类别(1:仅有楼栋2:至楼层3:至房间4：至床位)" title="数据类别(1:仅有楼栋2:至楼层3:至房间4：至床位)" style="width:98%;"/></td>
								-->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">楼栋号:</td>
								<td><input type="number" name="GMB_FLOOR" id="GMB_FLOOR" value="${pd.GMB_FLOOR}" maxlength="32" placeholder="这里输入楼栋号" title="楼栋号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">楼层号:</td>
								<td><input type="number" name="GMB_LAYER" id="GMB_LAYER" value="${pd.GMB_LAYER}" maxlength="32" placeholder="这里输入楼层号" title="楼层号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">房间号:</td>
								<td>
									<input type="text" name="GMB_ROOM" id="GMB_ROOM" value="${pd.GMB_ROOM}" maxlength="20" placeholder="这里输入房间号" title="房间号" style="width:98%;"/>
									<input type="hidden"name="GMB_BERTH" id="GMB_BERTH" value="0"/>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">
									职工:
									<input type="hidden" id="GMB_GMU_ID" name="GMB_GMU_ID"/>
								</td>
								<td>
									<c:forEach items="${staffList}" var="staff" varStatus="vs">
										 <input type="checkbox" name="cb_staff"  value="${staff.GMUSER_ID}"
										 	<c:if test="${staff.checked==1}">checked="checked"</c:if>
										 />${staff.GMU_NAME}
									</c:forEach>
								</td>
								<!-- <td><input type="text" name="GMB_GMU_ID" id="GMB_GMU_ID" value="${pd.GMB_GMU_ID}" maxlength="32" placeholder="这里输入房间负责职工编号" title="房间负责职工编号" style="width:98%;"/></td> -->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">房间状态:</td>
								<td>
									<input type="hidden" name="GMB_STATUS" id="GMB_STATUS" value="${pd.GMB_STATUS}"/>
									<select  name="SELECT_STATUS" id="SELECT_STATUS" style="width:50%;"
									onchange="$('#GMB_STATUS').val(this.value)">
									<c:if test="${pd.GMB_STATUS==''||pd.GMB_STATUS==null}">
									<option value="">下拉选择</option>
									</c:if>
    								<option value="1" <c:if test="${pd.GMB_STATUS==1}">selected</c:if>>可以入住</option>
   									<option value="2" <c:if test="${pd.GMB_STATUS==2}">selected</c:if>>不能入住</option>
 									</select>
 								</td>
								<!--<td><input type="number" name="GMB_STATUS" id="GMB_STATUS" value="${pd.GMB_STATUS}" maxlength="32" placeholder="这里输入床位状态(1:可以入住2:不能入住)" title="床位状态(1:可以入住2:不能入住)" style="width:98%;"/></td>
								-->
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">说明:</td>
								<td><input type="text" name="GMB_DESC" id="GMB_DESC" value="${pd.GMB_DESC}" maxlength="255" placeholder="这里输入说明" title="说明" style="width:98%;"/></td>
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
			//zzy
			//房间负责人选择框取值
			var zzy_cb1=$('[name=cb_staff]');
			var zzy_str="";
			for(i=0;i<zzy_cb1.length;i++){
				if(zzy_cb1[i].checked){
					zzy_str+=zzy_cb1[i].value+";";
				}
			}
			$("#GMB_GMU_ID").val(zzy_str);
			var TYPE=$("#GMB_TYPE").val();
			//if($("#GMB_TYPE".val()))
			/*if($("#GMB_GM_ID").val()==""){
				$("#GMB_GM_ID").tips({
					side:3,
		            msg:'请输入房间所属养老院编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMB_GM_ID").focus();
			return false;
			}
			if($("#GMB_FLOOR").val()==""){
				$("#GMB_FLOOR").tips({
					side:3,
		            msg:'请输入楼栋号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMB_FLOOR").focus();
			return false;
			}
			if($("#GMB_LAYER").val()==""&&TYPE>1){
				$("#GMB_LAYER").tips({
					side:3,
		            msg:'请输入楼层号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMB_LAYER").focus();
			return false;
			}
			if($("#GMB_ROOM").val()==""&&TYPE>2){
				$("#GMB_ROOM").tips({
					side:3,
		            msg:'请输入房间号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMB_ROOM").focus();
			return false;
			}
			if($("#GMB_BERTH").val()==""&&TYPE>2){
				$("#GMB_BERTH").tips({
					side:3,
		            msg:'请输入床位号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMB_BERTH").focus();
			return false;
			}
			if($("#GMB_E_ID").val()==""){
				$("#GMB_E_ID").tips({
					side:3,
		            msg:'请输入居住老人编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMB_E_ID").focus();
			return false;
			}
			if($("#GMB_DESC").val()==""){
				$("#GMB_DESC").tips({
					side:3,
		            msg:'请输入说明',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GMB_DESC").focus();
			return false;
			}*/
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