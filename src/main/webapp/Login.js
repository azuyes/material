$(document).ready(function () {
    //初始化表
    $("#date").textbox('textbox').css("font-size", "20pt");
    getWarehouseSelect();
});

function getWarehouseSelect(){
    $.ajax({
        url:getRealPath()+"/UserManagement/getWarehouse",
        type:'post',
        dataType: 'json',
        async:true,
        success:function(data){
            if(data.length!=0){
                for(var i=0;i<data.length;i++){
                    var option="<option value="+data[i].warehouseCode+">"+data[i].warehouseName+"</option>";
                    $('#Warehouse').append(option);
                }
            }

        },
        error:function(){
            alert("不能获取库房信息");
        }
    });
}

function denglu() {

    var userNo = $('#userNo').val();
    var userPass = $('#userPass').val();
    var warehouse= $('#Warehouse').val();
    var date = $('#date').datebox('getValue');

    //做一层验证
    if (userNo == '') {
        document.getElementById("showInfo").style.display = "block";
        document.getElementById("showInfo").innerText = "用户编号不能为空!";
    } else if (userPass == '') {
        document.getElementById("showInfo").style.display = "block";
        document.getElementById("showInfo").innerText = "密码不能为空!";
    } else if(warehouse == '') {
        document.getElementById("showInfo").style.display = "block";
        document.getElementById("showInfo").innerText = "库房不能为空!";
    } else if(date == '') {
        document.getElementById("showInfo").style.display = "block";
        document.getElementById("showInfo").innerText = "操作日期不能为空!";
    }

    else {
        $.ajax({
            type: "post",   //post提交方式默认是get
            dataType: "json",
            // url: ""+getRealPath()+"/UserMan/login?userNo="+userNo+"&userPass="+userPass+"",
            url: ""+getRealPath()+"/UserManagement/login?userNo="+userNo+"&userPass="+userPass+"",
            async: false,
            success: function (data) {
                if(data.code==100){
                    if(data.extend.isMatch){
                        window.open(getRealPath()+"/View/Index/index.html?userName="+data.extend.userName+"&userNo="+userNo+"", '_self');
                        document.cookie="status=success;path=/";//使cookie在整个网站下可用，可以将cookie_dir指定为根目录
                        document.cookie="globalWarehouse="+warehouse+";"
                        document.cookie="globalDate="+date+";"

                    }else{
                        document.getElementById("showInfo").innerText = "用户名或密码错误";
                    }

                }else {
                    $('#showInfo').css('display','block');
                    document.getElementById("showInfo").innerText = "无此用户";
                }
            }
        });

    }
}

function hideShowPsw() {

    var demo_img = document.getElementById("img");
    var demoInput = document.getElementById("userPass");
    if (demoInput.type == "password") {
        demoInput.type = "text";
        demo_img.src = "View/Resourse/Login/visible.png";
    }else {
        demoInput.type = "password";
        demo_img.src = "View/Resourse/Login/invisible.png";
    }
}

function getRealPath(){
    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[1];
    var basePath = localObj.protocol + "//" + localObj.host + "/"+ contextPath;
    return basePath ;
}
