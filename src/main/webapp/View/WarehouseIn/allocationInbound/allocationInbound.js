var indexOfRowEditing;

$(document).ready(function () {
    InitGrid();
    SetOnClickEvent();
});
document.write("<script  src='../../../Helper/Helper.js'></script>");

/**
 * @description 验证‘数量’字段必须为有效整数
 * @author Su
 */
$.extend($.fn.validatebox.defaults.rules, {
    digits: {// 验证身份证
        validator: function (value) {
            return /^[1-9]\d*$/.test(value);
        },
        message: '请输入有效整数'
    }
});


/**
 * @description 初始化包含7个字段的表格。
 * @author Su
 */
function InitGrid()
{
    $('#grid').datagrid({   //定位到Table标签，Table标签的ID是grid
        url: getRealPath()+'/InboundManagement/getMaterialStockIn' , //指向后台的Action来获取当前菜单的信息的Json格式的数据
        height: '100%',
        width: '100%',
        fit: false,
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        singleSelect: true,//只允许选中一行
        collapsible: true,
        rownumbers: true,
        sortOrder: 'asc',
        remoteSort: false,
        columns: [[
            { field: 'ckeck', checkbox: true },   //选择
            { title: '物资名称', field: 'materialName', width: '14%', align: 'left', halign: 'center'},
            { title: '规格型号', field: 'specification2', width: '14%', align: 'left', halign: 'center' },
            { title: '计量单位', field: 'measureUnit2', width: '14%', align: 'right', halign: 'center' },
            { title: '单价', field: 'unitPrice', width: '14%', align: 'right', halign: 'center' },
            { title: '数量', field: 'sssl', width: '14%', align: 'right', halign: 'center' ,
                             editor:{
                                type : 'validatebox',
                                options :{
                                    required : true,
                                    validType : 'digits'
                                }
                             }},
            { title: '金额', field: 'cost', width: '14%', align: 'right', halign: 'center' },
            { title: '备注', field: 'note2', width: '14%', align: 'right', halign: 'center',editor:'text' }
        ]],
        toolbar: '#tb',
        onAfterEdit : function (rowIndex, rowData, changes) {
            $('#grid').datagrid('updateRow',{
                index: rowIndex,
                row: {
                    cost: rowData.unitPrice * rowData.sssl
                }
            });

            saveMaterial(rowData);
        }
    })
}


/**
 * @description 为表格设置点击事件：编辑‘数量’字段。
 * @author Su
 */
function SetOnClickEvent() {
    var row;
    $('#grid').datagrid({onClickCell : function (index,field,value) {
            var rows = $('#grid').datagrid('getRows');
            row = rows[index];
            //给全局变量赋值
            indexOfRowEditing = index;

            if(row.sssl != '') {
                $.messager.alert('提示', '本行已有数据', 'info');
            }
            else if(field == "materialName" && row.sssl == '')
            {
                showMaterialClassDialog();
            }
            else if(field == 'sssl' && row.sssl == '')
            {
                $('#grid').datagrid('beginEdit',index);    
            }

        }});
}


/**
 * @description 本函数弹出“物资类别”对话框。
 * @author Su
 */
function showMaterialClassDialog() {
    $("#dlgmclass").dialog({
        content:"<iframe scrolling='auto' frameborder='0' src='../../../View/CommonComponent/Dialog/MaterialTypeSelect/materialClassDialogPage.html' style='width:100%; " +
           "height:100%; display:block;'></iframe>",
        buttons:[{
                    text: '关闭',
                    iconCls: 'icon-cancel',
             }]
      });
    $('#dlgmclass').dialog('open').dialog('setTitle', '物资类别');//打开对话框

}


/**
 * @description 点击“XX单位”，弹出公司对话框
 */
function showCompanyDialog() {
    showCompanyDialog1();
}


/**
 * @description 点击“保存”按钮，结束编辑
 */
function endEdit()
{
    $('#grid').datagrid('endEdit',indexOfRowEditing);
}

/**
 * @description 获取路径
 */
function getRealPath(){

    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[1];
    var basePath = localObj.protocol + "//" + localObj.host + "/"+ contextPath;
    return basePath ;
}
