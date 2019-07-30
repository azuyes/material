$(document).ready(function () {
    // departmentTree();
    //初始化表格
    InitGrid();
});


//将数据库的字段与tree的属性名称对应起来
$.fn.tree.defaults.loadFilter = function (data) {
    var nodes = [];
    for(var i=0; i<data.length; i++){
        var row = data[i];
        if(row.departmentlevel == 1){
            nodes.push({
                id:row.departmentid ,
                text:row.departmentname,
                departmentnum:row.departmentnum,
                children:new Array(),
            });
        }else {
            if (row.departmentlevel == 2) {
                for (var j = 0; j < nodes.length; j++) {
                    if (nodes[j].departmentnum.toString().substring(0, 3) == row.departmentnum.toString().substring(0, 3)) {
                        nodes[j].children.push({
                            id: row.departmentid,
                            text: row.departmentname,
                            departmentnum: row.departmentnum,
                            children: new Array(),
                        });
                        break;
                    }
                }
            }
            if (row.departmentlevel == 3) {
                for (var j = 0; j < nodes.length; j++) {
                    if (nodes[j].departmentnum.toString().substring(0, 3) == row.departmentnum.toString().substring(0, 3)) {
                        for (var k = 0; k < nodes[j].children.length; k++) {
                            if (nodes[j].children[k].departmentnum.toString().substring(3, 6) == row.departmentnum.toString().substring(3, 6)) {
                                nodes[j].children[k].children.push({
                                    id: row.departmentid,
                                    text: row.departmentname,
                                    departmentnum: row.departmentnum,
                                    children: new Array(),
                                });
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    return nodes;
};
$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;

//在添加用户和修改用户信息时，部门信息按RTree形式供选择
/*function departmentTree() {
    $('#department').combotree({
        url: getRealPath() + '/UserManagement/getDepartment',
        editable: false,
        lines:true,
        animate:true,
        onSelect:function(node) {
            departmentNum = node.departmentnum.toString();
            //按资产类别名进行查询
            searchByAssetClassName(departmentNum);
        },
        onLoadSuccess:function (){   //在数据加载成功以后触发方法
            $("#department").combotree('expandAll');//展开所有节点
        },
    });
}*/


//按资产类别名查询（已实现）
function searchByAssetClassName(departmentNum) {
    $.ajax({
        type: 'POST',
        url: getRealPath() + '/UserManagement/hasChild/'+ departmentNum,
        async:false,
        contentType: 'application/json',
        success: function (data) {
            if(data){
                $('#department').combotree('clear');
                $.messager.alert("提示", "请选择末级部门！", "info");
            }
        },
        error: function () {
            $.messager.alert("提示", "访问连接失败！", "info");
        }
    });
}

//初始化datagrid
function InitGrid(queryData) {
    //自定义validatebox规则
    $.extend($.fn.validatebox.defaults.rules,{
        equalTo: {
            validator: function (value, param) {
                return value == $(param[0]).val();
            },
            message: '两次输入的字符不一至'
        },
        idCode:{
            validator:function(value,param){
                return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
            },
            message: '请输入正确的身份证号'
        },
        loginName: {
            validator: function (value, param) {
                return /^[\u0391-\uFFE5\w]+$/.test(value);
            },
            message: '登录名称只允许汉字、英文字母、数字及下划线。'
        }
    });

    //将数据库的字段与tree的属性名称对应起来
    var convertTreeData = function(rows) {
        var nodes = [];
        for(var i=0; i<rows.length; i++){
            var row = rows[i];
            nodes.push({
                id:row.menuNo,
                text:row.menuName,
                state:row.state,
                // checked:row.checked,
                // attributes:row.attributes
            });
        }
        return nodes;
    };
    $.fn.tree.defaults.loadFilter = convertTreeData;

    //加载datagrid
    $('#grid').datagrid({   //定位到Table标签，Table标签的ID是grid
        url: getRealPath()+'/UserManagement/findUser' , //指向后台的Action来获取当前菜单的信息的Json格式的数据
        height: function () { return document.body.clientHeight * 0.9 },
        width: function () { return document.body.clientWidth * 0.9 },
        fit: false,
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        singleSelect: true,//只允许选中一行
        collapsible: true,
        rownumbers: true,
        sortName: 'userNo',    //根据某个字段给easyUI排序
        sortOrder: 'asc',
        remoteSort: false,
        idField: 'userNo',
        //queryParams: queryData,  //异步查询的参数
        columns: [[
            { field: 'ckeck', checkbox: true ,value:'userid'},   //选择
            { title: '用户编号', field: 'userNo', width: '15%' },
            { title: '用户名称', field: 'userName', width: '15%' },
            //{ title: '用户密码', field: 'userPass', width: '15%' },
            { title: '所属部门', field: 'department', width: '20%' },
            //{ title: '备注信息', field: 'userNote', width: '20%' },

            {
                title: '操作', field: 'opt', width: '10%', align: 'center', formatter:
                    function (value,row) {
                        var str = "";
                        str += '<span><a href="#" class="easyui-linkbutton" style="text-decoration: none;color:blue;" onclick="ShowRoleDialog(' + row.userNo + ')">权限设置</a></span>';
                        return str;
                        //return "<a onclick=\"show('"+row.userNo+"')\" > 查看 </a>";
                    }
            }
        ]],
        toolbar: '#tb',
        onDblClickRow: function (rowIndex, rowData) {//获取选中的行
            $('#grid').datagrid('uncheckAll');
            $('#grid').datagrid('checkRow', rowIndex);
        }
    })
}

//添加
function ShowAddDialog() {
    $('#fm').form('clear');//清空内容
    $('#userNo').textbox('enable');//添加时用户编号设为可修改
    $('#userPass').textbox('disable');//添加时用户密码设为不可修改
    $('#dlg').dialog('open').dialog('setTitle', '添加用户');//打开对话框
    $('#userPass').textbox('setValue',"123456");
    $('#department').combobox({
        valueField: 'departmentId',
        textField: 'departmentName',
        editable: false,
        url:getRealPath()+'/UserManagement/getDepartment',
        onLoadSuccess:function(data){
            $('#department').combobox('setValue',"请选择部门");

        },
        onSelect:function (data) {
            $('#department').combobox('setValue',data.departmentName);
            $('#hiddepartId').attr('value',data.departmentId);
            $('#hiddepartName').attr('value',data.departmentName);

        },
    });



    //从公共参数表获取密码
    /*$.ajax({
        type: 'post',
        url: getRealPath()+'/Publicparameter/findPublicparameter/publicpassword',
        dataType: "json",
        contentType: 'application/json; charset=utf-8',
        async: false,//false为异步传输，异步传输才能传全局变量
        success: function (data) {

        },
        error:function () {
            alert("error!");
        }
    });*/
    document.getElementById("test").value = "add";
}


//修改
function ShowEditDialog() {

    var row = $('#grid').datagrid('getSelections');

    if (row.length < 1) {
        $.messager.alert("提示", "请选择要修改的行！", "info");
    }
    else if (row.length > 1) {
        $.messager.alert("提示", "请只选择一行来修改！", "info");
    }
    else if (row.length = 1) {

        $('#userNo').textbox('disable');//添加时用户编号设为不可修改
        $('#userPass').textbox('disable');//添加时用户密码设为不可修改
        //获取要修改的字段
        row = $('#grid').datagrid('getSelected');
        $('#userNo').textbox('setValue',row.userno);
        $('#userName').textbox('setValue',row.username);
        //$('#department').textbox('setValue',row.department);
        $('#department').combobox({
            valueField: 'departmentId',
            textField: 'departmentName',
            editable: false,
            url:getRealPath()+'/UserManagement/getDepartment',
            onLoadSuccess:function(data){
                $('#department').combobox('setValues', row.department);

            },
            onSelect:function (data) {
                $('#department').combobox('setValue',data.departmentName);
                $('#hiddepartId').attr('value',data.departmentId);
                $('#hiddepartName').attr('value',data.departmentName);

            },
        });

        $('#userPass').textbox('setValue',row.password);

        $('#dlg').dialog('open').dialog('setTitle', '修改用户信息');
        document.getElementById("test").value = "modify";
        $('#fm').form('load', row);
    }
}


//打开权限设置对话框

function ShowRoleDialog(userNo) {
    $("#tt").tree({
        url:getRealPath()+'/UserManagement/findFunctionAuthority',
        //data: data,
        checkbox: true,
        animate: true,//定义当节点展开折叠时是否显示动画效果。
        cascadeCheck:true,//定义是否级联检查。
        onLoadSuccess:function (){   //在数据加载成功以后触发方法
            $("#tt").tree('expandAll');//展开所有节点

        },
        onExpand : function () {      //在节点展开的时候触发方法
            var FunctionAuthority = '';
            $.ajax({
                type: "post",
                url: getRealPath()+'/UserManagement/getAuthority/'+userNo,
                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (data) {
                    FunctionAuthority = data;
                },
                error: function () {
                    alert("error");
                }
            });
            //tree赋初值，即将数据库中已有的权限选中
            if (FunctionAuthority !== "") {
                //var TreeID = new Array();
                var TreeID = FunctionAuthority.split(",");
                for (var i = 0; i < TreeID.length; i++) {
                    var node = $('#tt').tree('find', TreeID[i]);
                    $('#tt').tree('check', node.target);
                }
            }
        }
    });

    $('#dlg-User').dialog('open').dialog('setTitle', '设置用户权限');
}

//权限设置对话框保存
function saveRoleData() {
    var row = $('#grid').datagrid('getSelected');
    var userNo = row.userNo;
    //var adminID = document.getElementById("AdminID").value = "0";
    var nodes = $('#tt').tree('getChecked');
    var FunctionAuthority = '';
    for (var i = 0; i < nodes.length; i++) {
        if (FunctionAuthority != '') FunctionAuthority += ',';
        FunctionAuthority += nodes[i].id;
    }
    console.log("FunctionAuthority",FunctionAuthority);
    $.ajax({
        type: "post",
        url: getRealPath()+'/UserManagement/saveAuthority/'+userNo+"/"+FunctionAuthority,
        async: false,//false为异步传输，异步传输才能传全局变量
        success: function (result) {
            if (result) {
                $('#dlg-User').dialog('close');
                $.messager.alert("提示", "恭喜您，权限信息设置成功！", "info");
            }
            else {
                $.messager.alert("提示", "修改失败，请重新操作！", "info");

            }
        },
        error: function (e) {
            console.log(e);
        }
    });
}


//删除信息
function deleteData() {
    //var adminID = document.getElementById("AdminID").value = "0";
    //var test = document.getElementById("test").value = "delete";
    var row = $('#grid').datagrid('getSelected');
    var num = 0;
    if (row.length < 1) {
        $.messager.alert("提示", "请选择要删除的用户！", "info");
    } else {
        $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (flag) {
            if (flag) {
                var userNo = row.userNo;
                $.ajax({
                    type: "post",
                    url: getRealPath()+'/UserManagement/delUser/'+userNo,
                    // url: getRealPath()+'/UserManagement/delInfo',
                    async: false,//false为异步传输，异步传输才能传全局变量
                    success: function (result) {
                        if (result == true) {
                            num++;
                        }
                    },
                    error: function () {
                        alert("error");
                    }
                });
                if (num == 1) {
                    $('#grid').datagrid('clearSelections'); //清空选中的行
                    InitGrid();
                    $.messager.alert("提示", "恭喜您，成功删除" + num + "条信息！", "info");
                } else {
                    $.messager.alert("提示", "删除失败，请重新操作！", "info");
                    InitGrid();

                }
            }
        });
    }
}

//保存用户信息
function saveData() {
    var userNo = document.getElementById("userNo").value;
    var userName = document.getElementById("userName").value;
    var departmentId=$('#hiddepartId').attr('value');
    var departmentName=$('#hiddepartName').attr('value');

    var userPass = document.getElementById("userPass").value;
    var test = document.getElementById("test").value;

    if (test == "add") {
        if($("#fm").form("validate")){
            $.ajax({
                type: 'post',
                url: getRealPath()+'/UserManagement/addUser',
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: '{ "userNo":"'+userNo+'", "userName": "'+userName+'", "department": "'+departmentName+'","userPass":"'+userPass+ '", "departId": "'+departmentId+ '"}',
                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (result) {
                    if (result == true) {
                        $('#dlg').dialog('close');
                        $.messager.alert("提示", "恭喜您，信息添加成功!", "info");
                        $('#grid').datagrid('clearSelections'); //清空选中的行
                        //InitGrid();
                        $('#grid').datagrid('reload');
                        //$('#fm').form('submit');
                    }
                    else {
                        $.messager.alert("提示", "添加失败，用户编号重复，请重新操作！", "info");

                    }
                }
            });
        }else {
            return $('#fm').form('validate');
        }
    } else {
        if ($("#fm").form("validate")) {
            $.ajax({
                type: 'post',
                url: getRealPath()+'/UserManagement/addUser',
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: '{ "userNo":"'+userNo+'", "userName": "'+userName+'", "department": "'+departmentName+'","userPass":"'+userPass+ '", "departId": "'+departmentId+ '"}',
                async: false,//false为异步传输，异步传输才能传全局变量
                success: function (result) {
                    if (result) {
                        $('#dlg').dialog('close');
                        $('#grid').datagrid('clearSelections'); //清空选中的行
                        $.messager.alert("提示", "恭喜您，信息修改成功", "info");
                        InitGrid();
                    }
                    else {
                        $.messager.alert("提示", "修改失败，请重新操作！", "info");

                    }
                },
                error: function () {
                    alert("error!");
                }
            });
        } else {
            return $('#fm').form('validate');
        }
    }
}

//获取路径
function getRealPath(){

    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[1];
    var basePath = localObj.protocol + "//" + localObj.host + "/"+ contextPath;
    return basePath ;
}
