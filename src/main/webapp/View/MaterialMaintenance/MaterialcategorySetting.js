var i = 1;   //物资类别级数

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
                field: "check",
                checkbox: true,
                hidden: false
            },
            {title: '物资类别编号', field: 'MaterialCode', width: '20%', halign: 'center', align: 'center'},
            {title: '物资类别名称', field: 'MaterialName', width: '20%', halign: 'center', align: 'center'},
            {title: '物资类别级数', field: 'MaterialSeries', width: '20%', halign: 'center', align: 'center'}
        ]]
    });

    $.getJSON(getRealPath() + "/MaterialSetting/Inquire?i=" + i, function(data) {
        for (var d in data) {
            $('#grid').datagrid('insertRow',{
                row: {
                    MaterialCode: data[d].materialClassId,
                    MaterialName: data[d].materialClassName,
                    MaterialSeries: data[d].materialClassLevel
                }
            });
        }
    });

    $('#uper')[0].style.display = 'none';
    $('#MaterialSeries').attr("disabled", "disabled");    //物资类别级数不可修改
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
    $('#MaterialCode').attr("disabled", false);
    $('#dlg').dialog('open').dialog('setTitle','添加物资类别');
    $('#text').val("add");
    $('#fm').form('clear');
    $('#MaterialSeries').val(i);
}

//保存
function saveData() {
    var MaterialCode = $('#MaterialCode').val();
    var MaterialName = $('#MaterialName').val();
    var text = $('#text').val();

    if(MaterialCode.length !== 2) {
        $.messager.alert("提示", "物资类别编号错误，请重新操作！", "info");
        return;
    } else {
        MaterialCode += "0000";
    }

    if(MaterialName === "") {
        MaterialName = null;
    }

    var jsonData = {
        "materialClassId": MaterialCode,
        "materialClassName": MaterialName,
        "materialClassLevel": i
    };

    if(text === "add") {   //添加物资类别信息
        $.ajax({
            url: getRealPath() + "/MaterialSetting/Add",
            data: JSON.stringify(jsonData),
            type: "POST",
            contentType: 'application/json',
            success: function (data) {
                if (data === true) {
                    $('#dlg').dialog('close');
                    $.messager.alert("提示", "添加成功", "info");
                    $('#grid').datagrid('insertRow', {
                        row: {
                            MaterialCode: MaterialCode,
                            MaterialName: MaterialName,
                            MaterialSeries: i
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
    } else {     //修改物资类别信息
        $.ajax({
            url: getRealPath() + "/MaterialSetting/Edit",
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
                            MaterialCode: MaterialCode,
                            MaterialName: MaterialName,
                            MaterialSeries: i
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
        $('#MaterialCode').val(row.MaterialCode.slice(0, 2));
        $('#MaterialName').val(row.MaterialName);
        $('#MaterialSeries').val(i);
        $('#dlg').dialog('open').dialog('setTitle', '修改物资类别');
        $('#text').val("edit");
        $('#MaterialCode').attr("disabled", "disabled");
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
                    "materialClassId": row.MaterialCode,
                    "materialClassName": row.MaterialName,
                    "materialClassLevel": i
                };

                $.ajax({
                    url: getRealPath() + "/MaterialSetting/Remove",
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
