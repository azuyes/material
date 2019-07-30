$(document).ready(function () {
    InitGrid();
});

//初始化datagrid
function InitGrid() {
    $('#grid').datagrid({
        fit: false,
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        singleSelect: true,
        collapsible: true,
        rownumbers: true,
        sortOrder: 'asc',
        remoteSort: false,
        columns: [[
            {
                field : "check",
                checkbox: true,
                hidden: false
            },
            { title: '库房编号', field: 'WarehouseCode', width: '19%', halign: 'center', align: 'center'},
            { title: '库房名称', field: 'WarehouseName', width: '19%', halign: 'center', align: 'center'},
            { title: '部门名称', field: 'DepartmentName', width: '19%' ,halign: 'center', align: 'center'},
            { title: '稽核员', field: 'Inspector', width: '19%', halign: 'center', align: 'center'},
            { title: '保管员', field: 'Guard', width: '19%', halign: 'center', align: 'center'}
        ]]
    });

    $.getJSON(getRealPath() + "/WarehouseSetting/Inquire", function(data) {
        for (var i in data) {
            $('#grid').datagrid('insertRow',{
                row: {
                    WarehouseCode: data[i].warehouseCode,
                    WarehouseName: data[i].warehouseName,
                    DepartmentName: data[i].departmentName,
                    Inspector: data[i].inspector,
                    Guard: data[i].guard
                }
            });
        }
    });

    $.getJSON(getRealPath() + "/WarehouseSetting/GetUser", function(data) {
        for (var i in data) {
            var value = data[i];
            var text = data[i];
            $("#Inspector").append("<option value='" + value + "'>" + text + "</option>");
            $("#Guard").append("<option value='" + value + "'>" + text + "</option>");
        }
    })
}

//获取路径
function getRealPath(){
    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[1];
    var basePath = localObj.protocol + "//" + localObj.host + "/"+ contextPath;
    return basePath ;
}

//添加
function Add() {
    $('#WarehouseCode').attr("disabled", false);   //添加时系统用户编号设为可修改
    $('#dlg').dialog('open').dialog('setTitle','添加库房信息');
    $('#text').val("add");
    $('#fm').form('clear');
}

//保存库房信息
function saveData() {
    var WarehouseCode = $('#WarehouseCode').val();
    var WarehouseName = $('#WarehouseName').val();
    var DepartmentName = $('#DepartmentName').val();
    var Inspector = $('#Inspector').val();
    var Guard = $('#Guard').val();
    var text = $('#text').val();

    if(WarehouseCode === "") {
        WarehouseCode = null;
    }
    if(WarehouseName === "") {
        WarehouseCode = null;
    }
    if(DepartmentName === "") {
        WarehouseCode = null;
    }

    var jsonData = {
        "warehouseCode": WarehouseCode,
        "warehouseName": WarehouseName,
        "departmentName": DepartmentName,
        "inspector": Inspector,
        "guard": Guard
    };

    if(text === "add") {   //添加库房信息
        $.ajax({
            url: getRealPath() + "/WarehouseSetting/Add",
            data: JSON.stringify(jsonData),
            type: "POST",
            contentType: 'application/json',
            success: function (data) {
                if (data === true) {
                    $('#dlg').dialog('close');
                    $.messager.alert("提示", "添加成功", "info");
                    $('#grid').datagrid('insertRow', {
                        row: {
                            WarehouseCode: WarehouseCode,
                            WarehouseName: WarehouseName,
                            DepartmentName: DepartmentName,
                            Inspector: Inspector,
                            Guard: Guard
                        }
                    })
                } else {
                    $.messager.alert("提示", "添加失败，请重新操作！", "info");
                }
            },
            error: function (data) {
                $.messager.alert("提示", "添加失败，请重新操作！", "info");
            }
        });
    } else {   //修改库房信息
        $.ajax({
            url: getRealPath() + "/WarehouseSetting/Edit",
            data: JSON.stringify(jsonData),
            type: "POST",
            contentType: 'application/json',
            success: function (data) {
                if (data === true) {
                    $('#dlg').dialog('close');
                    $.messager.alert("提示", "修改成功", "info");
                    var rowIndex = $('#grid').datagrid('getRowIndex',$('#grid').datagrid('getSelected'));
                    $('#grid').datagrid('updateRow',{
                        index: rowIndex,
                        row: {
                            WarehouseCode: WarehouseCode,
                            WarehouseName: WarehouseName,
                            DepartmentName: DepartmentName,
                            Inspector: Inspector,
                            Guard: Guard
                        }
                    })
                } else {
                    $.messager.alert("提示", "修改失败，请重新操作！", "info");
                }
            },
            error: function (data) {
                $.messager.alert("提示", "修改失败，请重新操作！", "info");
            }
        });
    }
}

//修改
function Edit() {
    var row = $('#grid').datagrid('getSelections');
    if (row.length < 1) {
        $.messager.alert("提示", "请选择要修改的行！", "info");
    } else {
        row = $('#grid').datagrid('getSelected');
        $('#WarehouseCode').val(row.WarehouseCode);
        $('#WarehouseName').val(row.WarehouseName);
        $('#DepartmentName').val(row.DepartmentName);
        $('#Inspector').val(row.Inspector);
        $('#Guard').val(row.Guard);
        $('#WarehouseCode').attr("disabled", "disabled");    //系统用户编号是主键，设为不可修改
        $('#dlg').dialog('open').dialog('setTitle', '修改库房信息');
        $('#text').val("edit");
    }
}

//删除
function Remove() {
    var row = $('#grid').datagrid('getSelections');
    if (row.length < 1) {
        $.messager.alert("提示", "请选择要删除的行！", "info");
    } else {
        $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (deleteAction) {
            if (deleteAction) {
                row = $('#grid').datagrid('getSelected');
                var jsonData = {
                    "warehouseCode": row.WarehouseCode,
                    "warehouseName": row.WarehouseName,
                    "departmentName": row.DepartmentName,
                    "inspector": row.Inspector,
                    "guard": row.Guard
                };

                $.ajax({
                    url: getRealPath() + "/WarehouseSetting/Remove",
                    data: JSON.stringify(jsonData),
                    type: "POST",
                    contentType: 'application/json',
                    success: function (data) {
                        if (data === true) {
                            $.messager.alert("提示", "删除成功！", "info");
                            var rowIndex = $('#grid').datagrid('getRowIndex',$('#grid').datagrid('getSelected'));
                            $('#grid').datagrid('deleteRow', rowIndex);
                        } else {
                            $.messager.alert("提示", "删除失败，请重新操作！", "info");
                        }
                    },
                    error: function (data) {
                        $.messager.alert("提示", "删除失败，请重新操作！", "info");
                    }
                });
            }
        })
    }
}