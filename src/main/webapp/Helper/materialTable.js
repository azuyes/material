$(document).ready(function () {
    InitGrid();
});

/**
 * @description 本函数读取materialclass表中所有level为1的条目并显示
 * @constructor
 */
function InitGrid()
{
    $('#grid').datagrid({   //定位到Table标签，Table标签的ID是grid
        url: getRealPath()+'/InboundManagement/getMaterialClass/1' , //指向后台的Action来获取当前菜单的信息的Json格式的数据
        height: '100%',
        width: '100%',
        fit: false,
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        singleSelect: true,//只允许选中一行
        collapsible: true,
        rownumbers: true,
        sortName: 'materialClassId',    //根据某个字段给easyUI排序
        sortOrder: 'asc',
        remoteSort: false,
        idField: 'materialClassId',
        columns: [[
            { field: 'ckeck', checkbox: true ,value:'assetclassid'},   //选择
            { title: '物资编号', field: 'materialClassId', width: '14%', align: 'left', halign: 'center' },
            { title: '物资类别', field: 'materialClassName', width: '14%', align: 'left', halign: 'center' },
            { title: '物资级别', field: 'materialClassLevel', width: '14%', align: 'right', halign: 'center' }

        ]],
        toolbar: '#tb'
    })
}


/**
 * @description 本函数将显示物资细节的datagrid初始化
 * @constructor
 */
function InitDetailGrid(id)
{
    $('#grid').datagrid({   //定位到Table标签，Table标签的ID是grid
        url: getRealPath()+'/InboundManagement/getMaterialDetailsById/'+ id, //指向后台的Action来获取当前菜单的信息的Json格式的数据
        height: '100%',
        width: '100%',
        fit: false,
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        singleSelect: true,//只允许选中一行
        collapsible: true,
        rownumbers: true,
        sortName: 'materialId',    //根据某个字段给easyUI排序
        sortOrder: 'asc',
        remoteSort: false,
        idField: 'materialId',
        columns: [[

            { title: '物资编号', field: 'materialId', width: '14%', align: 'left', halign: 'center' },
            { title: '物资名称', field: 'materialName', width: '14%', align: 'left', halign: 'center' },
            { title: '规格型号', field: 'specification2', width: '14%', align: 'right', halign: 'center' },
            { title: '计量单位', field: 'measureUnit2', width: '14%', align: 'right', halign: 'center' },
            { title: '单价', field: 'planPriceUnit', width: '14%', align: 'right', halign: 'center' }


        ]],

        toolbar:'#tb4'
    })

}

/**
 * @description 本函数用于返回到跳转至物资细节前的样子
 * @param id
 * @param levelFlag
 * @constructor
 */
function ReinitGrid(id,levelFlag)
{
    $('#grid').datagrid({   //定位到Table标签，Table标签的ID是grid
        url: getRealPath() + '/InboundManagement/getAssetClassByIdAndLevel/' + id + '/' + levelFlag,//指向后台的Action来获取当前菜单的信息的Json格式的数据
        height: '100%',
        width: '100%',
        fit: false,
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        singleSelect: true,//只允许选中一行
        collapsible: true,
        rownumbers: true,
        sortName: 'materialClassId',    //根据某个字段给easyUI排序
        sortOrder: 'asc',
        remoteSort: false,
        idField: 'materialClassId',
        columns: [[
            { field: 'ckeck', checkbox: true ,value:'assetclassid'},   //选择
            { title: '物资编号', field: 'materialClassId', width: '14%', align: 'left', halign: 'center' },
            { title: '物资类别', field: 'materialClassName', width: '14%', align: 'left', halign: 'center' },
            { title: '物资级别', field: 'materialClassLevel', width: '14%', align: 'right', halign: 'center' }

        ]],
        toolbar:'#tb'
    })
}

//获取路径
function getRealPath(){

    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[1];
    var basePath = localObj.protocol + "//" + localObj.host + "/"+ contextPath;
    return basePath ;
}