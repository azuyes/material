var globalVariant;
$(document).ready(function () {
    InitGrid();
    globalVariant=parent.globalVariants;
});

var editRow = undefined;

// 初始化表格
function InitGrid() {
    $('#grid').datagrid({
        url:getRealPath()+"/WarehouseOut/getWarehouseOut",
        columns:[[
            {field:'materialName',title:'物资名称',width:100},
            {field:'materialSpecification',editor:'text',title:'规格型号',width:100},
            {field:'measureUnit',editor:'text',title:'计量单位',width:100},
            {field:'unitPrice',editor:'text',title:'单价',width:100},
            {field:'applyQuantity',editor:'text',title:'数量',width:100},
            {field:'money',editor:'text',title:'金额',width:100},
            {field:'note',editor:'text',title:'备注',width:100},
        ]],
        onClickCell:function(rowIndex, field, value){
            if(editRow!=undefined){
                $('#grid').datagrid('cancelEdit',editRow);
            }
            if(field=="materialName"){
                $('#materialSelectDlg').dialog('open').dialog('setTitle', '选择物资类别');
            }
            editRow=rowIndex;
            $('#grid').datagrid('beginEdit',rowIndex);
            var ed = $('#grid').datagrid('getEditor', {index: rowIndex, field: field });

        },


    });
}


function showMaterialSelect(){
    alert("click");
}

