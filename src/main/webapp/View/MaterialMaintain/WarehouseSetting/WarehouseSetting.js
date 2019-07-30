$(document).ready(function () {
    initGrid();
});

//初始化表格
function initGrid() {
    $('#warehouse').datagrid({
        toolbar: '#tb',
        url: getRealPath()+"/WarehouseSetting/getWarehouses",
        method:'post',
        rownumbers:true,
        singleSelect:true,
        toolbar:"#tb",
        striped:true,
        idField: 'warehouseCode',
        columns: [[
            { field: 'ckeck', checkbox: true ,value:'warehouseCode' },
            { title: '库房编号', field: 'warehouseCode', width: '20%' },
            { title: '部门编号', field: 'departmentId', width: '20%' },
            { title: '库房名称', field: 'warehouseName', width: '20%' },
            { title: '保管员', field: 'guard', width: '25%' },
            { title: '稽查员', field: 'inspector', width: '25%' }
        ]]
    });
}

function showAddDialog() {
    $('#fm').form("clear");

    $('#warehouseCode').textbox('enable');//添加时设为可用
    $('#warehouseCode').textbox('readonly',false);//添加时设为只读

    $('#warehouseCode').textbox('textbox').css('background',"");


    $('#dlg').dialog('open').dialog('setTitle', '添加库房');//打开对话框

    document.getElementById("function").value = "add";//savaData方法通过判断该字段选择提交到add方法还是update方法

}

function showEditDialog() {

    var row = $('#warehouse').datagrid('getSelections');

    if (row.length < 1) {
        $.messager.alert("提示", "请选择要修改的行！", "info");
    }
    else if (row.length > 1) {
        $.messager.alert("提示", "请只选择一行来修改！", "info");
    }
    else if (row.length = 1) {

        //获取要修改的字段
        row = $('#warehouse').datagrid('getSelected');

        $('#fm').form("clear");
        $('#warehouseCode').textbox('enable');//添加时设为可用
        $('#warehouseCode').textbox('setValue',row.warehouseCode);
        $('#warehouseCode').textbox('readonly',true);//添加时设为只读
        $('#warehouseCode').textbox('textbox').css('background',"#CCC");

        $('#departmentId').textbox('setValue',row.departmentId);
        $('#warehouseName').textbox('setValue',row.warehouseName);
        $('#guard').textbox('setValue',row.guard);
        $('#inspector').textbox('setValue',row.inspector);


        $('#dlg').dialog('open').dialog('setTitle', '修改库房信息');//打开对话框

        document.getElementById("function").value = "edit";
    }

}

//保存用户信息
function saveData() {
    var warehouseCode = document.getElementById("warehouseCode").value;
    var departmentId = document.getElementById("departmentId").value;
    var warehouseName = document.getElementById("warehouseName").value;
    var func = document.getElementById("function").value;
    var guard = document.getElementById("guard").value;
    var inspector = document.getElementById("inspector").value;

    //通过func判断是添加还是修改，从而提交到不同的方法
    if (func == "add") {
        if($("#fm").form("validate")){
            $.ajax({
                type: 'post',
                url: getRealPath()+'/WarehouseSetting/addWarehouse',
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: '{ "warehouseCode":"'+warehouseCode+'", "departmentId": "'+departmentId+'", "warehouseName": "'+warehouseName+'","guard":"'+guard+ '", "inspector": "'+inspector+ '"}',
                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (data) {
                    $('#dlg').dialog('close');
                    alert(data.msg);
                    $('#warehouse').datagrid('reload');

                }
            });
        }else {
            return $('#fm').form('validate');
        }
    } else {
        if ($("#fm").form("validate")) {
            $.ajax({
                type: 'post',
                url: getRealPath()+'/WarehouseSetting/updateWarehouse',
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: '{ "warehouseCode":"'+warehouseCode+'", "departmentId": "'+departmentId+'", "warehouseName": "'+warehouseName+'","guard":"'+guard+ '", "inspector": "'+inspector+ '"}',
                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (data) {
                    $('#dlg').dialog('close');
                    alert(data.msg);
                    $('#warehouse').datagrid('reload');

                }
            });
        } else {
            return $('#fm').form('validate');
        }
    }
}

//删除信息
function deleteWarehouse() {
    var row = $('#warehouse').datagrid('getSelections');
    if (row.length < 1) {
        $.messager.alert("提示", "请选择要删除的行！", "info");
    }
    else if (row.length > 1) {
        $.messager.alert("提示", "请只选择一行来删除！", "info");
    }else {

        $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (flag) {
            if (flag){
                row = $('#warehouse').datagrid('getSelected');
                var warehouseCode = row.warehouseCode;
                $.ajax({
                    type: "post",
                    url: getRealPath()+'/WarehouseSetting/deleteWarehouse/'+warehouseCode,
                    async: false,//false为异步传输，异步传输才能传全局变量
                    success: function (data) {
                        alert(data.msg);
                        $('#warehouse').datagrid('reload');
                    },
                    error: function () {
                        alert("error");
                    }
                });
            }
        })
    }
}