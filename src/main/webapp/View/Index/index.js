var userName;
var userNo;
var globalVariants;
$(function () {
	//自定义validatebox规则
	$.extend($.fn.validatebox.defaults.rules,{
		equalTo: {
			validator: function (value, param) {
				return value == $(param[0]).val();
			},
			message: '两次输入的字符不一至'
		},
		idCode:{
			validator:function(value,param){
				return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
			},
			message: '请输入正确的身份证号'
		},
		loginName: {
			validator: function (value, param) {
				return /^[\u0391-\uFFE5\w]+$/.test(value);
			},
			message: '登录名称只允许汉字、英文字母、数字及下划线。'
		}
	});

	//获取cookie字符串
	var strCookie=document.cookie;
	//将多cookie切割为多个名/值对
	var arrCookie=strCookie.split("; ");
	var status;
	//遍历cookie数组，处理每个cookie对，找出status对应的值
	for(var i=0;i<arrCookie.length;i++){
		var arr=arrCookie[i].split("=");
		//找到名称为userId的cookie，并返回它的值
		if("status"==arr[0]){
			status=arr[1];
			break;
		}
	}
	//先判断cookie的值是否为success
 	if(status=="success"){
		document.cookie="status=false;path=/";
		globalVariants=getGlobalVariant();
		//从公共参数表获取财务日期
        var cwdata,unit;
        /*$.ajax({
            type: 'post',
            url: getRealPath()+'/Publicparameter/findPublicparameter/current_date',
            dataType: "json",
            contentType: 'application/json; charset=utf-8',
            async: false,//false为异步传输，异步传输才能传全局变量
            success: function (data) {
                var year = data.confValue.substring(0,4);
                var month = data.confValue.substring(4,6);
                cwdata = year + "-" + month;
                document.getElementById('time1').innerHTML = "当前财务日期：" + cwdata.toString();
            }
        });
        //从公共参数表获取单位
        $.ajax({
            type: 'post',
            url: getRealPath()+'/Publicparameter/findPublicparameter/unit',
            dataType: "json",
            contentType: 'application/json; charset=utf-8',
            async: false,//false为异步传输，异步传输才能传全局变量
            success: function (data) {
                unit = data.confValue.toString();
                document.getElementById('Unit').innerHTML = "单位：" + unit.toString();
            }
        });*/

		//获取用户id
	    userNo = getQueryString('userNo');
		userName = getQueryUrlString('userName');
		globalVariants.globalUserNo=userNo;
		globalVariants.globalUserName=userName;

		document.getElementById('User').innerHTML = "用户：" + userName;

		$("#west_other li").click(
			function () {
				$(this).css("background", "#f2f2f2").siblings().css("background", "#ffffff");
				$(this).css("color", "#2b7dbc").siblings().css("color", "#585858");
				$(this).css("font-weight", "600").siblings().css("font-weight", "500");
			}
		);
		$("#west_userManager li").click(
			function () {
				$(this).css("background", "#f2f2f2").siblings().css("background", "#ffffff");
				$(this).css("color", "#2b7dbc").siblings().css("color", "#585858");
				$(this).css("font-weight", "600").siblings().css("font-weight", "500");
			}
		);

		$('#north_menu a').click(
			function () {
				$(this).css("color", "#2b7dbc").siblings().css("color", "#585858");
				$(this).css("font-weight", "600").siblings().css("font-weight", "500");
			}
		);

		<!--初始化设置全部菜单不可用-->

		$('#other_module').css("display", 'none');//
		$('#userManager_module').css("display", 'none');//

		//隐藏中间层
		document.getElementById("MaterialPlan").parentNode.style.display = "none";
		document.getElementById("WarehouseInput").parentNode.style.display = "none";
		document.getElementById("WarehouseOutput").parentNode.style.display = "none";
		document.getElementById("WarehouseInputCheck").parentNode.style.display = "none";
		document.getElementById("WarehouseOutputCheck").parentNode.style.display = "none";
		document.getElementById("InternalAllocate").parentNode.style.display = "none";
		document.getElementById("AccQuery").parentNode.style.display = "none";
		document.getElementById("MaterialCheck").parentNode.style.display = "none";
		document.getElementById("MaterialStatisticsInquire").parentNode.style.display = "none";
		document.getElementById("MaterialMaintain").parentNode.style.display = "none";
		document.getElementById("DepartmentMaintain").parentNode.style.display = "none";
		document.getElementById("OtherFunctions").parentNode.style.display = "none";
		//
		$('#MaterialPlan1').css("display", 'none');
		$('#MaterialPlan2').css("display", 'none');

		$('#WarehouseInput1').css("display", 'none');
		$('#WarehouseInput2').css("display", 'none');
		$('#WarehouseInput3').css('display', 'none');
		$('#WarehouseInput4').css('display', 'none');

		$('#WarehouseOutput1').css('display', 'none');
		$('#WarehouseOutput2').css('display', 'none');
		$('#WarehouseOutput3').css('display', 'none');
		$('#WarehouseOutput4').css('display', 'none');

		$('#InternalAllocate1').css('display', 'none');

		$('#WarehouseInputCheck1').css('display', 'none');
		$('#WarehouseInputCheck2').css('display', 'none');
		$('#WarehouseInputCheck3').css('display', 'none');
        $('#WarehouseInputCheck4').css('display', 'none');

		$('#WarehouseOutputCheck1').css('display', 'none');
		$('#WarehouseOutputCheck2').css('display', 'none');
		$('#WarehouseOutputCheck3').css('display', 'none');
		$('#WarehouseOutputCheck4').css('display', 'none');

		$('#AccQuery1').css('display', 'none');
		$('#AccQuery2').css('display', 'none');


        $('#MaterialCheck1').css('display', 'none');

		$('#MaterialStatisticsInquire1').css('display', 'none');
		$('#MaterialStatisticsInquire2').css('display', 'none');
		$('#MaterialStatisticsInquire3').css('display', 'none');
		$('#MaterialStatisticsInquire4').css('display', 'none');

		$('#MaterialMaintain1').css('display', 'none');
		$('#MaterialMaintain2').css('display', 'none');
		$('#MaterialMaintain3').css('display', 'none');
		$('#MaterialMaintain4').css('display', 'none');
		$('#MaterialMaintain5').css('display', 'none');
		$('#MaterialMaintain6').css('display', 'none');
		$('#MaterialMaintain7').css('display', 'none');
		$('#MaterialMaintain8').css('display', 'none');

		$('#DepartmentMaintain1').css('display', 'none');
		$('#DepartmentMaintain2').css('display', 'none');
		$('#DepartmentMaintain3').css('display', 'none');


		$('#OtherFunctions1').css('display', 'none');



		var functionAuthority = '';
		if(userNo !=null){
			//admin用户只有权限分配的功能
			if(userNo=="0000"){
                $('#userManager_module').css("display", 'block');
				$('#UserManagement').css('display', 'block');
                $('#UserManager').css('display', 'block');
				document.getElementById("user_management").parentNode.style.display = "block";

			}else {
                display('OtherSettings');
				$.ajax({
					type: "post",
					url:getRealPath()+'/UserManagement/getAuthority/'+userNo,
					async: false,//false为异步传输，异步传输才能传全局变量
					error: function () {
						alert("error");
					},
					success:function (data) {
						functionAuthority=data.split(',');
						for (var i=0;i<functionAuthority.length;i++){
							var functionNo= functionAuthority[i];
							switch(functionNo){
								case "050101":
									document.getElementById('MaterialPlan').parentNode.style.display="block";
									$('#MaterialPlan1').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050102":
									document.getElementById('MaterialPlan').parentNode.style.display="block";
									$('#MaterialPlan2').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050201":
									document.getElementById('WarehouseInput').parentNode.style.display="block";
									$('#WarehouseInput1').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050202":
									document.getElementById('WarehouseInput').parentNode.style.display="block";
									$('#WarehouseInput2').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050203":
									document.getElementById('WarehouseInput').parentNode.style.display="block";
									$('#WarehouseInput3').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050204":
									document.getElementById('WarehouseInput').parentNode.style.display="block";
									$('#WarehouseInput4').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050301":
									document.getElementById('WarehouseOutput').parentNode.style.display="block";
									$('#WarehouseOutput1').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050302":
									document.getElementById('WarehouseOutput').parentNode.style.display="block";
									$('#WarehouseOutput2').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050303":
									document.getElementById('WarehouseOutput').parentNode.style.display="block";
									$('#WarehouseOutput3').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050304":
									document.getElementById('WarehouseOutput').parentNode.style.display="block";
									$('#WarehouseOutput4').css("display",'block');
									$('#other_module').css("display",'block');
									break;

								case "050401":
									document.getElementById('InternalAllocate').parentNode.style.display="block";
									$('#InternalAllocate1').css("display",'block');
									$('#other_module').css("display",'block');
									break;

								case "050501":
									document.getElementById('WarehouseInputCheck').parentNode.style.display="block";
									$('#WarehouseInputCheck1').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050502":
									document.getElementById('WarehouseInputCheck').parentNode.style.display="block";
									$('#WarehouseInputCheck2').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050503":
									document.getElementById('WarehouseInputCheck').parentNode.style.display="block";
									$('#WarehouseInputCheck3').css("display",'block');
									$('#other_module').css("display",'block');
									break;
                                case "050504":
                                    document.getElementById('WarehouseInputCheck').parentNode.style.display="block";
                                    $('#WarehouseInputCheck4').css("display",'block');
                                    $('#other_module').css("display",'block');
                                    break;
								case "050601":
									document.getElementById('WarehouseOutputCheck').parentNode.style.display="block";
									$('#WarehouseOutputCheck1').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050602":
									document.getElementById('WarehouseOutputCheck').parentNode.style.display="block";
									$('#WarehouseOutputCheck2').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050603":
									document.getElementById('WarehouseOutputCheck').parentNode.style.display="block";
									$('#WarehouseOutputCheck3').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050604":
									document.getElementById('WarehouseOutputCheck').parentNode.style.display="block";
									$('#WarehouseOutputCheck4').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050701":
									document.getElementById('AccQuery').parentNode.style.display="block";
									$('#AccQuery1').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050702":
									document.getElementById('AccQuery').parentNode.style.display="block";
									$('#AccQuery2').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050801":
									document.getElementById('MaterialCheck').parentNode.style.display="block";
									$('#MaterialCheck1').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050901":
									document.getElementById('MaterialStatisticsInquire').parentNode.style.display="block";
									$('#MaterialStatisticsInquire1').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050902":
									document.getElementById('MaterialStatisticsInquire').parentNode.style.display="block";
									$('#MaterialStatisticsInquire2').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050903":
									document.getElementById('MaterialStatisticsInquire').parentNode.style.display="block";
									$('#MaterialStatisticsInquire3').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "050904":
									document.getElementById('MaterialStatisticsInquire').parentNode.style.display="block";
									$('#MaterialStatisticsInquire4').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "051001":
									document.getElementById('MaterialMaintain').parentNode.style.display="block";
									$('#MaterialMaintain1').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "051002":
									document.getElementById('MaterialMaintain').parentNode.style.display="block";
									$('#MaterialMaintain2').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "051003":
									document.getElementById('MaterialMaintain').parentNode.style.display="block";
									$('#MaterialMaintain3').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "051004":
									document.getElementById('MaterialMaintain4').parentNode.style.display="block";
									$('#MaterialMaintain4').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "051005":
									document.getElementById('MaterialMaintain').parentNode.style.display="block";
									$('#MaterialMaintain5').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "051006":
									document.getElementById('MaterialMaintain').parentNode.style.display="block";
									$('#MaterialMaintain6').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "051007":
									document.getElementById('MaterialMaintain').parentNode.style.display="block";
									$('#MaterialMaintain7').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "051008":
									document.getElementById('MaterialMaintain').parentNode.style.display="block";
									$('#MaterialMaintain8').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "051101":
									document.getElementById('DepartmentMaintain').parentNode.style.display="block";
									$('#DepartmentMaintain1').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "051102":
									document.getElementById('DepartmentMaintain').parentNode.style.display="block";
									$('#DepartmentMaintain2').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "051103":
									document.getElementById('DepartmentMaintain').parentNode.style.display="block";
									$('#DepartmentMaintain3').css("display",'block');
									$('#other_module').css("display",'block');
									break;
								case "051201":
									document.getElementById('OtherFunctions').parentNode.style.display="block";
									$('#OtherFunctions1').css("display",'block');
									$('#other_module').css("display",'block');
									break;

								default:
									break;
							}
						}
					}
				});
			}
		}
	}else {
		window.location.href = "../../Login.html";
		window.open("../../Login.html", '_self');
	}

});

function display(value) {
	var wests = new Array(2);
	wests[0] = document.getElementById('west_other');
	wests[1] = document.getElementById('west_userManager');
	if(value == 'OtherSettings') {
		displayNone(wests);
        $("#west_other").css("display",'block');
	} else if(value == 'UserManager') {
		displayNone(wests);
        $("#west_userManager").css("display",'block');
	}
}

function displayNone(values) {
	for(var i = 0; i < values.length; i++) {
		values[i].style.display = 'none';
	}
}

function addTab(id, title, url) {
	//如果是执行计提减值，判断是否年末
	if(title == "计提减值准备"){
        var date = new Date();
        if(!verifyTime(date)){
            $.messager.alert("提示","尚未年末，无法进行计提减值！","Info");
            return ;
        }
	}

	if($(id).tabs('exists', title)) {
		$(id).tabs('select', title);
	} else {
		var content = '<iframe  frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
		$(id).tabs('add', {
			title: title,
			content: content,
			closable: true
		});
	}
}

function Exit() {
	document.cookie="status=false;path=/";
    window.location.href = "../../Login.html";
    window.open("../../Login.html", '_self');
}

//获取url后的某一个query的值
function getQueryString( name ) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg); //获取url中"?"符后的字符串并正则匹配
	var context = "";
	if (r != null)
		context = r[2];
	reg = null;
	r = null;
	return context == null || context == "" || context == "undefined" ? "" : context;
}

//获取Url中中文参数的方法
function getQueryUrlString(name) {
	var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
	var r = window.location.search.substr(1).match(reg);
	if(r != null) {
		return decodeURI(r[2]);
		//alert(decodeURI(r[2]));
	}
	return "请登录";
}

//获取当前时间
function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}


function login(){
	$('#dlg').dialog('close'),
	$('#verify').dialog('open').dialog('setTitle','验证身份信息');
	$('#userNo_verify').attr("disabled", "disabled"); //系统用户编号是主键，设为不可修改
	$('#userNo_verify').val(userNo);
}

//验证身份
function verifyInfo(){
	$('#showInfo').css('display','none');
	var userPass = document.getElementById("userPass_verify").value;
	var userNo = document.getElementById("userNo_verify").value;
	var result = " ";
	if(userPass==''){
		document.getElementById("showInfo").style.display = "block";
		document.getElementById("showInfo").innerText = "密码不能为空!";
	}else {
		if($('fm_verify').form("validate")){
			$.ajax({
				type:"post",
				url:  getRealPath()+'/UserManagement/login/'+userNo+"/"+userPass,
				contentType: 'application/json; charset=utf-8',
				dataType: "json",
				async: false,
				error: function () {
					alert("error!");
				},
				success: function (data) {
					if(data.code==100) {
						result = data.extend.userName;
					}else {
						result = " ";
					}
				}
			});
			if(result != " "){
				$('#userPass_verify').val('');//清除输入的正确密码
				$('#verify').dialog('close');
				$('#dlg').dialog('open').dialog('setTitle', '个人设置');
				$('#userNo').attr("disabled", "disabled"); //系统用户编号是主键，设为不可修改

				//给输入框赋初值
				$('#userNo').val(userNo);
				$('#userName').val(userName);

			}else if(result == " "){
				$('#showInfo').css('display','block');
				document.getElementById("showInfo").innerText = "密码错误!";
			}

		}else {
			return $('fm_verify').form("validate");
		}
	}

}
//保存信息  
function saveData() {
	var userNo = document.getElementById("userNo").value;
	var userName = document.getElementById("userName").value;
	var userPass = document.getElementById("userPass").value;
	var userPass1 = document.getElementById("userPass1").value;
	 if ($("#fm").form("validate")) {
		$.ajax({
			type: "post",
			url:  getRealPath()+'/UserManagement/updateMsg',
			contentType: 'application/json; charset=utf-8',
			dataType: "json",
			data: '{ "userNo":"'+userNo+'", "userName": "'+userName+'","userPass":"'+userPass+'"}',
			async: false, //false为异步传输，异步传输才能传全局变量
			success: function(data) {
				if(data == "1") {
					$('#dlg').dialog('close');
					$.messager.alert("提示", "恭喜您，信息修改成功", "info");
					//InitGrid();
				} else {
					$.messager.alert("提示", "修改失败，请重新操作！", "info");

				}
			},
			error: function() {
				alert("error");
			}
		});
	 } else {
	 	return $('#fm').form('validate');
	 }

}


//关闭tab
function tabsClose(id){
    var tab=$(id).tabs('getSelected');//获取当前选中tabs
    var index = $(id).tabs('getTabIndex',tab);//获取当前选中tabs的index
    $(id).tabs('close',index);//关闭对应index的tabs
}

//判断当前日期是否年末
function verifyTime(date) {
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if(month == 12 && strDate == 31){
        return true;
    }else{
        return false;
    }
}

function getRealPath(){
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol + "//" + localObj.host + "/"+ contextPath;
	return basePath ;
}
