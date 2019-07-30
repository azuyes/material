$(document).ready(function () {
    $('#dlg').dialog('open').dialog('setTitle','请选择日期');
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
            { title: '物资类别编号', field: 'MaterialClassID', width: '7%', halign: 'center', align: 'center'},
            { title: '物资编号', field: 'MaterialID', width: '6%', halign: 'center', align: 'center'},
            { title: '物资名称', field: 'MaterialName', width: '6%', halign: 'center', align: 'center'},
            { title: '规格型号', field: 'Specification', width: '6%', halign: 'center', align: 'center'},
            { title: '计量单位', field: 'MeasureUnit', width: '6%', halign: 'center', align: 'center'},
            { title: '计划单位', field: 'Guard', width: '6%', halign: 'center', align: 'center'},
            { title: '物料组', field: 'MaterialGroup', width: '6%', halign: 'center', align: 'center'},
            { title: '数量', field: 'Quantity', width: '6%', halign: 'center', align: 'center'},
            { title: '金额', field: 'Cost', width: '6%', halign: 'center', align: 'center'},
            { title: '质量技术标准', field: 'ZLJSBZ', width: '7%', halign: 'center', align: 'center'},
            { title: '采购执行数量', field: 'BuyExecuteQuantity', width: '7%', halign: 'center', align: 'center'},
            { title: '使用执行数量', field: 'UseExecuteQuantity', width: '7%', halign: 'center', align: 'center'},
            { title: '供应商', field: 'Provider', width: '6%', halign: 'center', align: 'center'},
            { title: '交货日期', field: 'DeliveryDate', width: '6%', halign: 'center', align: 'center'},
            { title: '备注', field: 'Note2', width: '6%', halign: 'center', align: 'center'}
        ]]
    });

    /*$.getJSON(getRealPath() + "/MaterialPlan/Inquire", function(data) {
        for (var i in data) {
            $('#grid').datagrid('insertRow',{
                row: {
                    MaterialClassID: data[i].warehouseCode,
                    MaterialID: data[i].warehouseName,
                    MaterialName: data[i].departmentName,
                    Specification: data[i].inspector,
                    MeasureUnit: data[i].guard,
                    Guard: data[i].guard,
                    MaterialGroup: data[i].guard,
                    Quantity: data[i].guard,
                    Cost: data[i].guard,
                    ZLJSBZ: data[i].guard,
                    BuyExecuteQuantity: data[i].guard,
                    UseExecuteQuantity: data[i].guard,
                    Provider: data[i].guard,
                    DeliveryDate: data[i].guard,
                    Note2: data[i].note2
                }
            });
        }
    });*/
}

//获取路径
function getRealPath(){
    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[1];
    var basePath = localObj.protocol + "//" + localObj.host + "/"+ contextPath;
    return basePath ;
}

//显示或者隐藏当前计划的保存明细
function Show() {
    if($('#chk_PlanSearch').is(':checked')) {
        $("#plan").attr("style","display:block;");
        InitGrid();
    } else {
        $("#plan").attr("style","display:none;");
    }
}

//进入申请计划录入界面
function Enter() {
    $("#face").attr("style","display:block;");
    $('#dlg').dialog('close');
}