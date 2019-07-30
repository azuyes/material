var materialClassId = '000000';
var materialClassLevel;
$(document).ready(function () {
    materialClassLevel = 1;
    initMaterialClassGrid("1",materialClassLevel);

});

//初始化表格
function initMaterialClassGrid(materialClassId,materialClassLevel) {
    $('#Material').datagrid({
        toolbar: '#tb',
        url: getRealPath()+"/MaterialClassSettingAll/queryMaterialClassByIdAndLevel/"+materialClassId+"/"+materialClassLevel,
        method:'post',
        rownumbers:true,
        singleSelect:true,
        toolbar: [{
            text:'上一级',
            iconCls: 'icon-back',
            handler: function(){
                lastLevel();
            }
        },'-',{
            text:'下一级',
            iconCls: 'icon-next',
            handler: function(){
                nextLevel();
            }
        },'-',{
            text:'明细',
            iconCls: 'icon-more',
            handler: function() {
                showMaterial();
            }
        }],
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

//根据物资类别查询物资，初始化表格
function initMaterialGrid(materialClassId) {
    $('#Material').datagrid({
        toolbar: '#tb',
        url: getRealPath()+'/MaterialSettingAll/queryMaterialsByClassId/'+materialClassId,
        method:'post',
        rownumbers:true,
        singleSelect:true,
        toolbar: [{
            text:'上一级',
            iconCls: 'icon-back',
            handler: function(){
                lastLevel();
            }
        },'-',{
            text:'添加',
            iconCls: 'icon-add',
            handler: function(){
                showAddDialog();
            }
        },'-',{
            text:'修改',
            iconCls: 'icon-edit',
            handler: function() {
                showEditDialog();
            }
        },'-',{
            text:'删除',
            iconCls: 'icon-edit',
            handler: function() {
                deleteMaterial();
            }
        }],
        striped:true,
        idField: 'materialId',
        columns: [[
            { field: 'ckeck', checkbox: true ,value:'materialId' },
            { title: '物资编号', field: 'materialId', width: '15%' },
            { title: '物资名称', field: 'materialName', width: '20%' },
            { title: '规格型号', field: 'specification2', width: '20%' },
            { title: '计量单位', field: 'measureUnit2', width: '20%' },
            { title: '计划价格', field: 'planPriceUnit', width: '20%' }
        ]]
    });
}

//下一级
function nextLevel() {
    var rows = $('#Material').datagrid('getSelections');

    if (rows.length < 1) {
        $.messager.alert("提示", "请先选择一行！", "info");
    }
    else if (rows.length > 1) {
        $.messager.alert("提示", "请只选择一行！", "info");
    }
    else if (rows.length = 1) {
        var row = $('#Material').datagrid('getSelected');
        if (materialClassId,materialClassLevel >= 3) {
            alert("当前已是末级，可查看物资明细");
        }else {
            materialClassId = row.materialClassId;
            materialClassLevel = row.materialClassLevel +1;
            initMaterialClassGrid(materialClassId,materialClassLevel);
        }
        $('#Material').datagrid('unselectAll');
    }

}

//上一级
function lastLevel() {

    if (materialClassLevel <= 1){
        alert("已经是第一级");
    } else {
        materialClassLevel -= 1;
        initMaterialClassGrid(materialClassId,materialClassLevel);
    }
    $('#Material').datagrid('unselectAll');
}

function showMaterial() {
    var row = $('#Material').datagrid('getSelections');

    if (row.length < 1) {
        $.messager.alert("提示", "请选择要查看的行！", "info");
    }
    else if (row.length > 1) {
        $.messager.alert("提示", "请只选择一行！", "info");
    }
    else if (row.length = 1) {
        materialClassLevel = 4;
        row = $('#Material').datagrid('getSelected');
        materialClassId = row.materialClassId;
        initMaterialGrid(materialClassId);
        $('#Material').datagrid('unselectAll');
    }
}

function showAddDialog() {
    $('#fm').form("clear");
    $.ajax({
        type: 'post',
        url: getRealPath()+'/MaterialSettingAll/getMaxMaterialId/'+materialClassId,
        // contentType: 'application/text; charset=utf-8',
        async: false,//false为异步传输，异步传输才能传全局变量
        success: function (data) {

            var newMaterialId = stringToNumAndAddOne(data);
            $("#materialId").textbox("setValue",newMaterialId);
        },error: function () {
            alert("error");
        }
    });

    $('#materialId').textbox('textbox').css('background',"");
    $("#materialId").textbox("readonly",true);
    $('#materialId').textbox('textbox').css('background',"#CCC");

    $('#dlg').dialog('open').dialog('setTitle', '添加物资');//打开对话框

    document.getElementById("function").value = "add";//savaData方法通过判断该字段选择提交到add方法还是update方法

}


function showEditDialog() {

    var row = $('#Material').datagrid('getSelections');

    if (row.length < 1) {
        $.messager.alert("提示", "请选择要修改的行！", "info");
    }
    else if (row.length > 1) {
        $.messager.alert("提示", "请只选择一行来修改！", "info");
    }
    else if (row.length = 1) {

        //获取要修改的字段
        row = $('#Material').datagrid('getSelected');

        $('#fm').form("clear");

        $("#materialId").textbox("readonly",true);
        $("#materialId").textbox("setValue",row.materialId);
        $('#materialId').textbox('textbox').css('background',"#CCC");

        $("#materialName").textbox("setValue",row.materialName);
        $("#specification2").textbox("setValue",row.specification2);
        $("#measureUnit2").textbox("setValue",row.measureUnit2);
        $("#planPriceUnit").textbox("setValue",row.planPriceUnit);


        $('#dlg').dialog('open').dialog('setTitle', '修改物资信息');//打开对话框

        document.getElementById("function").value = "edit";
    }
}

//保存信息
function saveData() {
    var materialId = document.getElementById("materialId").value;//全局变量中的id
    var materialName = document.getElementById("materialName").value;//用户输入的两位id
    var specification2 = document.getElementById("specification2").value;
    var measureUnit2 = document.getElementById("measureUnit2").value;
    var planPriceUnit = document.getElementById("planPriceUnit").value;
    var func = document.getElementById("function").value;

    //通过func判断是添加还是修改，从而提交到不同的方法
    if (func == "add") {
        if($("#fm").form("validate")){
            $.ajax({
                type: 'post',
                url: getRealPath()+'/MaterialSettingAll/addMaterial',
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: '{ "materialId":"'+materialId+'", "materialName": "'+materialName+'", "specification2": "'+specification2+'", "measureUnit2": "'+measureUnit2+'", "planPriceUnit": "'+planPriceUnit+'"}',
                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (data) {
                    $('#dlg').dialog('close');
                    alert(data.msg);
                    $('#Material').datagrid('reload');

                }
            });
        }else {
            return $('#fm').form('validate');
        }
    } else {
        if ($("#fm").form("validate")) {
            $.ajax({
                type: 'post',
                url: getRealPath()+'/MaterialSettingAll/updateMaterial',
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: '{ "materialId":"'+materialId+'", "materialName": "'+materialName+'", "specification2": "'+specification2+'", "measureUnit2": "'+measureUnit2+'", "planPriceUnit": "'+planPriceUnit+'"}',
                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (data) {
                    $('#dlg').dialog('close');
                    alert(data.msg);
                    $('#Material').datagrid('reload');

                }
            });
        } else {
            return $('#fm').form('validate');
        }
    }
}

/**
 * 字符串加1
 * @param string
 * @returns {string}
 */
function stringToNumAndAddOne(string) {
    var pre = string.slice(0,6)
    var suf = string.slice(6,string.length);
    var num = parseInt(suf)+1;
    suf = addPreZero(num)+"";//加空串转成字符串
    return pre+suf;
}

/**
 * //num不足六位时自动在左边补0，直到六位
 * @param 不足六位的num
 * @returns 6位num
 */

function addPreZero(num){
    return ('000000'+num).slice(-6);
}

//删除信息
function deleteMaterial() {
    var row = $('#Material').datagrid('getSelections');
    if (row.length < 1) {
        $.messager.alert("提示", "请选择要删除的行！", "info");
    }
    else if (row.length > 1) {
        $.messager.alert("提示", "请只选择一行来删除！", "info");
    }else {

        $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (flag) {
            if (flag){
                row = $('#Material').datagrid('getSelected');
                var materialId = row.materialId;
                $.ajax({
                    type: "post",
                    url: getRealPath()+'/MaterialSettingAll/deleteMaterial/'+materialId,
                    async: false,//false为异步传输，异步传输才能传全局变量
                    success: function (data) {
                        alert(data.msg);
                        $('#Material').datagrid('reload');
                    },
                    error: function () {
                        alert("error");
                    }
                });
            }
        })
    }
}
