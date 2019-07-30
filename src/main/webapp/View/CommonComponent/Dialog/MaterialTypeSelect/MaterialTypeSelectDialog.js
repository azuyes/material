var curMaterialLevel=1;
$(document).ready(function () {

});

function drawGrid(level) {
    $("#materialSelectGrid").datagrid({
        url:getRealPath()+"",
        columns:[[
            {field:'materialClassId',title:'物资名称',width:100},
            {field:'materialSpecification',editor:'text',title:'规格型号',width:100},
            {field:'measureUnit',editor:'text',title:'计量单位',width:100},
        ]],
    })
}