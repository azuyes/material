//全局变量：保存用户点击“对应物资明细”是所选的条目id。用于ShowAddDialog()中
var theIdSelectedWhenPressDetail;
var ifEnd = false;

$(document).ready(function () {
    InitMaterialClassGrid();
    SelfInitGrid();
});

document.write("<script  src='../../../../Helper/Helper.js'></script>");

/**
 * @description 本函数读取materialclass表中所有level为1的条目并显示
 * @author Su
 */
function InitMaterialClassGrid()
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
            { field: 'ckeck', checkbox: true },   //选择
            { title: '物资编号', field: 'materialClassId', width: '14%', align: 'left', halign: 'center' },
            { title: '物资类别', field: 'materialClassName', width: '14%', align: 'left', halign: 'center' },
            { title: '物资级别', field: 'materialClassLevel', width: '14%', align: 'right', halign: 'center' }

        ]],
        toolbar: '#tb'
    })

    $('#grid').datagrid({onDblClickRow : function (index,field,value) {
            obj.nextLevel_click();
        }});

}


/**
 * @description 本函数将显示物资细节的datagrid初始化
 * @author Su
 */
function InitDetailGrid(id)
{
    ifEnd = true;

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
            { field: 'ckeck', checkbox: true },   //选择
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

/**
 * @description 本函数初始化‘上级’、‘下级’、‘对应物资明细’三个按钮的点击事件
 *
 */
function SelfInitGrid() {

    $('#tb4').hide();

    //级数
    var materiallevel = 1;
    //编号,在点击下级的时候获得，用于返回上一级
    var materialclassnum = null ;

    //obj定义了toolbar中的四个事件
    obj = {
        /**
         * 上一级按钮点击事件
         */
        upperLevel_click : function( ){

            if(materiallevel != 1)
            { //判断是否为第一级
                var id, levelFLag;//当前为 一级是id是前两位，二级时id为前四位
                switch (materiallevel) {

                    case 2:
                        InitMaterialClassGrid();
                        materiallevel = 1;
                        break;

                    case 3:
                        id = materialclassnum.toString().substring(0, 2);
                        levelFLag = 2;
                        var data = findAllEntries(id,levelFLag);
                        $('#grid').datagrid('loadData', data);
                        materiallevel = levelFLag;
                        break;

                    case -1:
                        $('#tb4').hide();
                        var mid = materialclassnum;
                        var level = whichLevelIsIt(mid);
                        //将进入详情前的级别设置好
                        if(level == 3) {
                            //第3级别
                            ReinitGrid(mid.substring(0,4),level);
                            materiallevel = 3;
                        }
                        else if (level == 2){
                            // 第2级别
                            ReinitGrid(mid.substring(0,2),level);
                            materiallevel = 2;
                        }
                        else{
                            // 第1级别
                            InitMaterialClassGrid();
                            materiallevel = 1;
                        }
                        break;
                }
            }
            else {
                $.messager.alert('提示', '当前已为第一级！', 'info');
            }
        },

        /**
         * 下一级按钮点击事件
         */
        nextLevel_click:function () {
            var row = $('#grid').datagrid('getSelected');

            if(row != null) {
                materialclassnum = row.materialClassId; //获取选中的分类编号
                var id, levelFLag;

                if (materiallevel != 3) {//判断是否为末级
                    if (materiallevel == 2) {
                        id = materialclassnum.toString().substring(0, 4);
                        levelFLag = 3;

                        var ifEnd = isEnd(id);
                        if(ifEnd == false){
                            var data = findAllEntries(id,levelFLag);
                            $('#grid').datagrid('loadData', data);
                            materiallevel = 3;
                        }
                        else if(ifEnd == true) {
                            $.messager.alert('提示', '当前已为末页!', 'info');
                        }
                    }
                    else if (materiallevel == 1)
                    {
                        id = materialclassnum.toString().substring(0, 2);
                        levelFLag = 2;

                        var ifEnd = isEnd(id);

                        if(ifEnd == false){
                            var data = findAllEntries(id,levelFLag);
                            $('#grid').datagrid('loadData', data);
                            materiallevel = 2;
                        }
                        else if(ifEnd == true) {
                            $.messager.alert('提示', '当前已为末页!', 'info');
                        }
                    }
                }
                else {
                    $.messager.alert('提示', '当前已为末页！', 'info');
                }
            }
            else{
                $.messager.alert('提示', '必须选择一条记录！', 'info');
            }
            $('#grid').datagrid('clearSelections');
        },


        showDetail : function () {

            var row = $('#grid').datagrid('getSelected');

            //用户点击查看明细的同时给全局变量赋值
            theIdSelectedWhenPressDetail = row.materialClassId;
            var idSelected = row.materialClassId;

            if(row != null) {
                var ifEnd;
                var level = whichLevelIsIt(idSelected);

                //判断当前是否为末页
                if(level == 1){
                    ifEnd = isEnd(idSelected.substring(0,2));
                }
                else if(level == 2){
                    ifEnd = isEnd(idSelected.substring(0,4));
                }
                else{
                    ifEnd = true;
                }

                if(ifEnd == true) {
                    //如果为末页，加载详情datagrid
                    materialclassnum = row.materialClassId;
                    materiallevel = -1;
                    InitDetailGrid(materialclassnum);
                    $('#tb').hide();
                }
                else if(ifEnd == false){
                    $.messager.alert('提示', '该资产类别不是末级，不允许操作！', 'info');
                    return;
                }
            }else{
                $.messager.alert('提示', '必须选择一条记录！', 'info');
            }
            $('#grid').datagrid('clearSelections');
        },

    };

}


/**
 * @description 本函数用于弹窗添加物资。函数根据数据库中materialId字段，自动为物资设置编号。
 * @Author 这个函数没用
 */
// function ShowAddDialog() {
//
//     var row = $('#grid').datagrid('getSelected');
//
//     $('#materialClassId').textbox('disable');
//     $('#materialId').textbox('disable');
//     $('#materialName').textbox('disable');
//     $('#specification2').textbox('disable');
//     $('#measureUnit2').textbox('disable');
//     $('#planPriceUnit').textbox('disable');
//
//     $.ajax({
//         type : 'POST',
//         url:getRealPath()+'/InboundManagement/getMaxMaterialId/' + row.materialId.substring(0,6),
//         contentType : 'application/json',
//         success : function(data) {
//             if(data[0] == null){
//                 $('#materialClassId').textbox('setValue',theIdSelectedWhenPressDetail);
//                 $('#materialId').textbox('setValue',theIdSelectedWhenPressDetail+"000001");
//                 alert("查到的数据:"+data[0]);
//             }else{
//                 $('#materialClassId').textbox('setValue',row.materialId.substring(0,6));
//                 $('#materialId').textbox('setValue',row.materialId);
//                 $('#materialName').textbox('setValue',row.materialName);
//                 $('#specification2').textbox('setValue',row.specification2);
//                 $('#measureUnit2').textbox('setValue',row.measureUnit2);
//                 $('#planPriceUnit').textbox('setValue',row.planPriceUnit);
//
//             }
//         },
//         error:function () {
//             alert("查询出错");
//         }
//     });
//
//     parent.$('#dlgmclass').dialog({ buttons:[{
//             text: '确定',
//             type:'submit',
//             iconCls: 'icon-ok'
//         }]});
//
//     // // $('#dlg').dialog({
//     // //     buttons:[{
//     // //         text: '确定',
//     // //         type:'submit',
//     // //         iconCls: 'icon-ok'
//     // //     }]});
//     //
//     // $('#dlg').dialog('open').dialog('setTitle', '物资添加');//打开对话框
//
//     $('#fm').form('clear');//清空内容
//
// }


/**
 * @description 用户点击“添加”按钮，将当前对话框关闭，并将对应位置的数据填入最初的表格中。
 * @author Su
 */
function addMaterial() {
    var row = $('#grid').datagrid('getSelected');

    if(row != null){
        parent.$('#dlgmclass').dialog('close');
        addOneRow(row);
    }
    else
        $.messager.alert('提示', '必须选择一条记录', 'info');
}


/**
 * @description 为最初的网格添加新的一行
 * @author Su
 */
function addOneRow(row)
{
    parent.$('#grid').datagrid('appendRow',
        {
            materialName :row.materialName,
            specification2:row.specification2,
            measureUnit2:row.measureUnit2,
            unitPrice:row.planPriceUnit,
            sssl:'',
            cost:'',
            note:''
        });
}


/**
 * @description 删除某条物资明细，调用helper.js中的del函数
 */
function deleteData() {
    del();
}

function showCompanyDialog1() {
    // parent.$('#dlgCompany').dialog(
    //     {
    //         title: 'My Dialog',
    //         width: 400,
    //         height: 200,
    //         closed: false,
    //         cache: false,
    //         // combobox:[
    //         //     {
    //         //         title:
    //         //     }
    //         // ],
    //         modal: true
    //     }
    // );
}

//获取路径
function getRealPath(){

    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[1];
    var basePath = localObj.protocol + "//" + localObj.host + "/"+ contextPath;
    return basePath ;
}