
$(document).ready(function () {
    initGrid();
});

//初始化表格
function initGrid() {
    $('#accountingDepartment').datagrid({
        toolbar: '#tb',
        url: getRealPath()+"/AccountingDepartment/getDepartments",
        method:'post',
        rownumbers:true,
        singleSelect:true,
        toolbar:"#tb",
        striped:true,
        idField: 'departmentId',
        columns: [[
            { field: 'ckeck', checkbox: true ,value:'departmentId' },
            { title: '部门编号', field: 'departmentId', width: '10%' },
            { title: '部门名称', field: 'departmentName', width: '10%' },
            { title: '部门属性', field: 'departmentProperty', width: '15%' },
            { title: '级次', field: 'departmentLevel', width: '10%' },
            { title: '核算类别', field: 'accountCategory', width: '12%' },
            { title: '核算项目', field: 'accountItem', width: '15%' },
            { title: '生产系统部门编号', field: 'manufactureSystemDepartmentId', width: '15%' },
            { title: '分摊系数', field: 'allocationCoefficient', width: '10%' }
        ]]
    });
}



function showAddDialog() {
    $('#fm').form("clear");

    $('#departmentId').textbox('enable');//添加时部门编号设为可用
    $('#departmentId').textbox('readonly',false);

    $('#departmentId').textbox('textbox').css('background',"");


    $('#dlg').dialog('open').dialog('setTitle', '添加部门');//打开对话框

    document.getElementById("function").value = "add";//savaData方法通过判断该字段选择提交到addSupplier方法还是updateSupplier方法

}


function showEditDialog() {

    var row = $('#accountingDepartment').datagrid('getSelections');

    if (row.length < 1) {
        $.messager.alert("提示", "请选择要修改的行！", "info");
    }
    else if (row.length > 1) {
        $.messager.alert("提示", "请只选择一行来修改！", "info");
    }
    else if (row.length = 1) {

        //获取要修改的字段
        row = $('#accountingDepartment').datagrid('getSelected');

        $('#fm').form("clear");
        $('#departmentId').textbox('enable');//添加时供货商编号设为可用
        $('#departmentId').textbox('setValue',row.departmentId);
        $('#departmentId').textbox('readonly',true);//添加时部门编号设为只读
        $('#departmentId').textbox('textbox').css('background',"#CCC");

        $('#departmentName').textbox('setValue',row.departmentName);
        $('#departmentProperty').textbox('setValue',row.departmentProperty);
        $('#departmentLevel').textbox('setValue',row.departmentLevel);
        $('#accountCategory').textbox('setValue',row.accountCategory);
        $('#accountItem').textbox('setValue',row.accountItem);
        $('#manufactureSystemDepartmentID').textbox('setValue',row.manufactureSystemDepartmentID);
        $('#allocationCoefficient').textbox('setValue',row.allocationCoefficient);


        $('#dlg').dialog('open').dialog('setTitle', '修改部门信息');//打开对话框

        document.getElementById("function").value = "edit";
    }

}

//保存用户信息
function saveData() {
    var departmentId = document.getElementById("departmentId").value;
    var departmentName = document.getElementById("departmentName").value;
    var departmentProperty = document.getElementById("departmentProperty").value;
    var departmentLevel = document.getElementById("departmentLevel").value;
    var accountCategory = document.getElementById("accountCategory").value;
    var func = document.getElementById("function").value;
    var accountItem = document.getElementById("accountItem").value;
    var manufactureSystemDepartmentID = document.getElementById("manufactureSystemDepartmentID").value;
    var allocationCoefficient = document.getElementById("allocationCoefficient").value;

    //通过func判断是添加还是修改，从而提交到不同的方法
    if (func == "add") {
        if($("#fm").form("validate")){
            $.ajax({
                type: 'post',
                url: getRealPath()+'/AccountingDepartment/addDepartment',
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: '{ "departmentId":"'+departmentId+'", "departmentName": "'+departmentName+'", "departmentProperty": "'+departmentProperty+'","departmentLevel":"'+departmentLevel+ '", "accountCategory": "'+accountCategory+'", "accountItem": "'+accountItem+'", "manufactureSystemDepartmentID": "'+manufactureSystemDepartmentID+'", "allocationCoefficient": "'+allocationCoefficient+ '"}',
                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (data) {
                    $('#dlg').dialog('close');
                    alert(data.msg);
                    $('#accountingDepartment').datagrid('reload');

                }
            });
        }else {
            return $('#fm').form('validate');
        }
    } else {
        if ($("#fm").form("validate")) {
            $.ajax({
                type: 'post',
                url: getRealPath()+'/AccountingDepartment/updateDepartment',
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: '{ "departmentId":"'+departmentId+'", "departmentName": "'+departmentName+'", "departmentProperty": "'+departmentProperty+'","departmentLevel":"'+departmentLevel+ '", "accountCategory": "'+accountCategory+'", "accountItem": "'+accountItem+'", "manufactureSystemDepartmentID": "'+manufactureSystemDepartmentID+'", "allocationCoefficient": "'+allocationCoefficient+ '"}',
                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (data) {
                    $('#dlg').dialog('close');
                    alert(data.msg);
                    $('#accountingDepartment').datagrid('reload');

                }
            });
        } else {
            return $('#fm').form('validate');
        }
    }
}

//删除信息
function deleteDepartment() {
    var row = $('#accountingDepartment').datagrid('getSelections');
    if (row.length < 1) {
        $.messager.alert("提示", "请选择要删除的行！", "info");
    }
    else if (row.length > 1) {
        $.messager.alert("提示", "请只选择一行来删除！", "info");
    }else {

        $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (flag) {
            if (flag){
                row = $('#accountingDepartment').datagrid('getSelected');
                var departmentId = row.departmentId;
                $.ajax({
                    type: "post",
                    url: getRealPath()+'/AccountingDepartment/deleteDepartment/'+departmentId,
                    async: false,//false为异步传输，异步传输才能传全局变量
                    success: function (data) {
                        alert(data.msg);
                        $('#accountingDepartment').datagrid('reload');
                    },
                    error: function () {
                        alert("error");
                    }
                });
            }
        })
    }
}
