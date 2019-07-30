var materialClassId = '000000';
var materialClassLevel;
$(document).ready(function () {
    materialClassLevel = 1;
    initGrid("1",materialClassLevel);

});

//初始化表格
function initGrid(materialClassId,materialClassLevel) {
    $('#MaterialClass').datagrid({
        toolbar: '#tb',
        url: getRealPath()+"/MaterialClassSettingAll/queryMaterialClassByIdAndLevel/"+materialClassId+"/"+materialClassLevel,
        method:'post',
        rownumbers:true,
        singleSelect:true,
        toolbar:"#tb",
        striped:true,
        idField: 'materialClassId',
        columns: [[
            { field: 'ckeck', checkbox: true ,value:'materialClassId' },
            { title: '物资类别编号', field: 'materialClassId', width: '30%' },
            { title: '物资类别名称', field: 'materialClassName', width: '30%' },
            { title: '级次', field: 'materialClassLevel', width: '35%' }
        ]]
    });
}
//下一级
function nextLevel() {
    var rows = $('#MaterialClass').datagrid('getSelections');

    if (rows.length < 1) {
        $.messager.alert("提示", "请先选择一行！", "info");
    }
    else if (rows.length > 1) {
        $.messager.alert("提示", "请只选择一行！", "info");
    }
    else if (rows.length = 1) {
        var row = $('#MaterialClass').datagrid('getSelected');
        if (materialClassId,materialClassLevel >= 3) {
            alert("当前已是末页")
        }else {
            materialClassId = row.materialClassId;
            materialClassLevel = row.materialClassLevel +1;
            initGrid(materialClassId,materialClassLevel);
        }
        $('#MaterialClass').datagrid('unselectAll');
    }

}

//上一级
function lastLevel() {

        if (materialClassLevel <= 1){
            alert("已经是第一级");
        } else {
            materialClassLevel -= 1;
            initGrid(materialClassId,materialClassLevel);
        }
        $('#MaterialClass').datagrid('unselectAll');
}

function showAddDialog() {
    $('#fm').form("clear");

    $('#materialClassId').textbox('textbox').css('background',"");

    $("#materialClassLevel").textbox("readonly",true);
    $("#materialClassLevel").textbox("setValue",materialClassLevel);
    $('#materialClassLevel').textbox('textbox').css('background',"#CCC");

    $('#dlg').dialog('open').dialog('setTitle', '添加物资类别');//打开对话框

    document.getElementById("function").value = "add";//savaData方法通过判断该字段选择提交到add方法还是update方法

}

function showEditDialog() {

    var row = $('#MaterialClass').datagrid('getSelections');

    if (row.length < 1) {
        $.messager.alert("提示", "请选择要修改的行！", "info");
    }
    else if (row.length > 1) {
        $.messager.alert("提示", "请只选择一行来修改！", "info");
    }
    else if (row.length = 1) {

        //获取要修改的字段
        row = $('#MaterialClass').datagrid('getSelected');

        $('#fm').form("clear");

        if (row.materialClassLevel == 1){
            $("#materialClassId").textbox('setValue',row.materialClassId.substr(0,2));
        }else if(row.materialClassLevel == 2){
            $("#materialClassId").textbox('setValue',row.materialClassId.substr(2,2));

        }else if(row.materialClassLevel == 3){
            $("#materialClassId").textbox('setValue',row.materialClassId.substr(4,2));

        }

        $('#materialClassName').textbox('setValue',row.materialClassName);

        $("#materialClassId").textbox("readonly",true);
        $('#materialClassId').textbox('textbox').css('background',"#CCC");

        $("#materialClassLevel").textbox("readonly",true);
        $("#materialClassLevel").textbox("setValue",materialClassLevel);
        $('#materialClassLevel').textbox('textbox').css('background',"#CCC");

        $('#dlg').dialog('open').dialog('setTitle', '修改物资类别');//打开对话框

        document.getElementById("function").value = "edit";
    }
}

//保存信息
function saveData() {
    var currentId = materialClassId;//全局变量中的id
    // alert(currentId);
    var inputId = document.getElementById("materialClassId").value;//用户输入的两位id
    var materialClassName = document.getElementById("materialClassName").value;
    var materialClassLevel = document.getElementById("materialClassLevel").value;
    var func = document.getElementById("function").value;

    //通过func判断是添加还是修改，从而提交到不同的方法
    if (func == "add") {
        if($("#fm").form("validate")){
            $.ajax({
                type: 'post',
                url: getRealPath()+'/MaterialClassSettingAll/addMaterialClass/'+currentId,
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: '{ "materialClassId":"'+inputId+'", "materialClassName": "'+materialClassName+'", "materialClassLevel": "'+materialClassLevel+'"}',
                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (data) {
                    $('#dlg').dialog('close');
                    alert(data.msg);
                    $('#MaterialClass').datagrid('reload');

                }
            });
        }else {
            return $('#fm').form('validate');
        }
    } else {
        if ($("#fm").form("validate")) {
            $.ajax({
                type: 'post',
                url: getRealPath()+'/MaterialClassSettingAll/updateMaterialClass/'+currentId,
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: '{ "materialClassId":"'+inputId+'", "materialClassName": "'+materialClassName+'", "materialClassLevel": "'+materialClassLevel+'"}',

                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (data) {
                    $('#dlg').dialog('close');
                    alert(data.msg);
                    $('#MaterialClass').datagrid('reload');

                }
            });
        } else {
            return $('#fm').form('validate');
        }
    }
}

//删除信息
function deleteMaterialClass() {
    var row = $('#MaterialClass').datagrid('getSelections');
    if (row.length < 1) {
        $.messager.alert("提示", "请选择要删除的行！", "info");
    }
    else if (row.length > 1) {
        $.messager.alert("提示", "请只选择一行来删除！", "info");
    }else {

        $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (flag) {
            if (flag){
                row = $('#MaterialClass').datagrid('getSelected');
                var materialClassId = row.materialClassId;
                $.ajax({
                    type: "post",
                    url: getRealPath()+'/MaterialClassSettingAll/deleteMaterialClass/'+materialClassId,
                    async: false,//false为异步传输，异步传输才能传全局变量
                    success: function (data) {
                        alert(data.msg);
                        $('#MaterialClass').datagrid('reload');
                    },
                    error: function () {
                        alert("error");
                    }
                });
            }
        })
    }
}
