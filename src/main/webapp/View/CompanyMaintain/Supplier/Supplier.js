$(document).ready(function () {
    initGrid();
});


function initGrid() {
    $('#supplier').datagrid({
        toolbar: '#tb',
        url: getRealPath()+"/Supplier/getSuppliers",
        method:'post',
        rownumbers:true,
        singleSelect:true,
        toolbar:"#tb",
        striped:true,
        idField: 'supplierId',
        columns: [[
            { field: 'ckeck', checkbox: true ,value:'supplierId' },
            { title: '供应商编号', field: 'supplierId', width: '10%' },
            { title: '单位名称', field: 'companyName', width: '10%' },
            { title: '公司地址', field: 'address', width: '10%' },
            { title: '统一社会信用代码', field: 'trustId', width: '10%' },
            { title: '开户银行', field: 'bankName', width: '10%' },
            { title: '银行账号', field: 'accountNo', width: '10%' },
            { title: '固定电话', field: 'phoneNo', width: '8%' },
            { title: '移动电话', field: 'mobileNo', width: '10%' },
            { title: '注册资本(万元)', field: 'regCapital', width: '10%' ,formatter:function (value,rowData,rowIndex) {
                    if (value!=null){
                        return value+" 万元";
                    }
                } },
            { title: '法定代表人', field: 'legalRep', width: '8%' }
        ]]
    });
}

function showAddDialog() {
    $('#fm').form("clear");

    $('#supplierId').textbox('enable');//添加时部门编号设为可用
    $('#supplierId').textbox('readonly',false);//添加时部门编号设为只读

    $('#supplierId').textbox('textbox').css('background',"");


    $('#dlg').dialog('open').dialog('setTitle', '添加供应商');//打开对话框

    document.getElementById("function").value = "add";//savaData方法通过判断该字段选择提交到addSupplier方法还是updateSupplier方法

}


function showEditDialog() {

    var row = $('#supplier').datagrid('getSelections');

    if (row.length < 1) {
        $.messager.alert("提示", "请选择要修改的行！", "info");
    }
    else if (row.length > 1) {
        $.messager.alert("提示", "请只选择一行来修改！", "info");
    }
    else if (row.length = 1) {

        //获取要修改的字段
        row = $('#supplier').datagrid('getSelected');

        $('#fm').form("clear");
        $('#supplierId').textbox('enable');//添加时供货商编号设为可用
        $('#supplierId').textbox('setValue',row.supplierId);
        $('#supplierId').textbox('readonly',true);//添加时部门编号设为只读
        $('#supplierId').textbox('textbox').css('background',"#CCC");

        $('#companyName').textbox('setValue',row.companyName);
        $('#address').textbox('setValue',row.address);
        $('#trustId').textbox('setValue',row.trustId);
        $('#bankName').textbox('setValue',row.bankName);
        $('#accountNo').textbox('setValue',row.accountNo);
        $('#phoneNo').textbox('setValue',row.phoneNo);
        $('#mobileNo').textbox('setValue',row.mobileNo);
        $('#regCapital').textbox('setValue',row.regCapital);
        $('#legalRep').textbox('setValue',row.legalRep);


        $('#dlg').dialog('open').dialog('setTitle', '修改供应商信息');//打开对话框

        document.getElementById("function").value = "edit";
    }


}



//保存用户信息
function saveData() {
    var supplierId = document.getElementById("supplierId").value;
    var companyName = document.getElementById("companyName").value;
    var address = document.getElementById("address").value;
    var func = document.getElementById("function").value;
    var trustId = document.getElementById("trustId").value;
    var bankName = document.getElementById("bankName").value;
    var accountNo = document.getElementById("accountNo").value;
    var phoneNo = document.getElementById("phoneNo").value;
    var mobileNo = document.getElementById("mobileNo").value;
    var regCapital = document.getElementById("regCapital").value;
    var legalRep = document.getElementById("legalRep").value;

    //通过func判断是添加还是修改，从而提交到不同的方法
    if (func == "add") {
        if($("#fm").form("validate")){
            $.ajax({
                type: 'post',
                url: getRealPath()+'/Supplier/addSupplier',
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: '{ "supplierId":"'+supplierId+'", "companyName": "'+companyName+'", "address": "'+address+'","trustId":"'+trustId+ '", "bankName": "'+bankName+'", "accountNo": "'+accountNo+'", "phoneNo": "'+phoneNo+'", "mobileNo": "'+mobileNo+'", "regCapital": "'+regCapital+'", "legalRep": "'+legalRep+ '"}',
                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (data) {
                    $('#dlg').dialog('close');
                    alert(data.msg);
                    $('#supplier').datagrid('reload');

                }
            });
        }else {
            return $('#fm').form('validate');
        }
    } else {
        if ($("#fm").form("validate")) {
            $.ajax({
                type: 'post',
                url: getRealPath()+'/Supplier/updateSupplier',
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: '{ "supplierId":"'+supplierId+'", "companyName": "'+companyName+'", "address": "'+address+'","trustId":"'+trustId+ '", "bankName": "'+bankName+'", "accountNo": "'+accountNo+'", "phoneNo": "'+phoneNo+'", "mobileNo": "'+mobileNo+'", "regCapital": "'+regCapital+'", "legalRep": "'+legalRep+ '"}',
                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (data) {
                    $('#dlg').dialog('close');
                    alert(data.msg);
                    $('#supplier').datagrid('reload');

                }
            });
        } else {
            return $('#fm').form('validate');
        }
    }
}

//删除信息
function deleteSupplier() {
    var row = $('#supplier').datagrid('getSelections');
    if (row.length < 1) {
        $.messager.alert("提示", "请选择要删除的行！", "info");
    }
    else if (row.length > 1) {
        $.messager.alert("提示", "请只选择一行来删除！", "info");
    }else {

        $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (flag) {
            if (flag){
                row = $('#supplier').datagrid('getSelected');
                var supplierId = row.supplierId;
                $.ajax({
                    type: "post",
                    url: getRealPath()+'/Supplier/deleteSupplier/'+supplierId,
                    async: false,//false为异步传输，异步传输才能传全局变量
                    success: function (data) {
                        alert(data.msg);
                        $('#supplier').datagrid('reload');
                    },
                    error: function () {
                        alert("error");
                    }
                });
            }
        })
    }
}