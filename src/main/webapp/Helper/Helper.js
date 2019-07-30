/**
 * @description 本函数用于判断用户选择的条目是否为末页
 * @param id 先根据level对原id进行substring操作，再传进来
 * @returns 如果末页则返回true，不是末页则返回false
 * @author Su
 * @date 2019/7/15
 */
function isEnd(id) {

    var ifEnd;
    $.ajax({
        type: 'POST',
        //获取id前几位类似的数据的个数
        url: getRealPath() + '/InboundManagement/classSize/' + id,
        async: false,
        contentType: 'application/json',
        success: function (data) {
            if (data > 1)
            {
                ifEnd = false;
            }
            else
            {
                ifEnd = true;
            }

        },
        error: function () {
            alert("helper中的isEnd出错");
        }
    });

    return ifEnd;
}

/**
 * @description 本函数用于查找数据库中id和levelflag相符的数据
 * @param id 先根据等级substring，再传进来
 * @param levelFlag 要查询的等级
 * @returns 查询到数据的集合
 * @author Su
 * @date 2019/7/15
 */
function findAllEntries(id,levelFlag)
{
    var ret;

    $.ajax({
        type: 'POST',
        url: getRealPath() + '/InboundManagement/getAssetClassByIdAndLevel/' + id + '/' + levelFlag,
        async: false,
        contentType: 'application/json',
        success: function (data) {
            ret = data;
        },
        error: function () {
            alert("helper中的findAllEntries出错");
        }
    });

    return ret;
}


/**
 * @description 本函数用于保存物资明细
 * @author Su
 * @date 2019/7/17
 */
function saveMaterial(rowData) {

    var mName = rowData.materialName;
    var mSpecification = rowData.specification2;
    var mMeasureUnit = rowData.measureUnit2;
    var munitPrice = rowData.unitPrice;
    var msssl = rowData.sssl;
    var mcost = rowData.sssl * rowData.unitPrice;
    var mnote2 = rowData.note2;

    $.ajax({
        type: 'post',
        url:getRealPath()+'/InboundManagement/addMaterialDetail',
        dataType: "json",
        data:'{"materialName":"'+mName+'","specification2": "'+mSpecification+'","measureUnit2":"'+mMeasureUnit
            +'","unitPrice":"'+munitPrice+'","sssl":"'+msssl+'","cost":"'+mcost+'","note2":"'+mnote2+'"}',

        contentType: 'application/json; charset=utf-8',
        async: false,//false为异步传输，异步传输才能传全局变量
        success: function (result) {
            if(result==true){
                $.messager.alert("提示", "入库信息添加成功!", "info");
            }else{
                $.messager.alert("提示", "入库信息添加失败!", "info");
            }
        }
    });


}


function del(){
    alert("helper中的del")
}

/**
 * @description 本函数接收一个materialId，并判断它为哪个level的物资。
 */
function whichLevelIsIt(id) {
    var idFirst = id.substring(0,2);
    var idSecond = id.substring(2,4);
    var idThird = id.substring(4,6);
    var level;

    if(idThird != "00")
        level = 3;
    else if(idThird == "00" && idSecond != "00")
        level = 2;
    else if(idSecond == "00")
        level = 1;

    return level;
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