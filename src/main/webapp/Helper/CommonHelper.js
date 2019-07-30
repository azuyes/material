//获取路径前缀
function getRealPath(){

    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[1];
    var basePath = localObj.protocol + "//" + localObj.host + "/"+ contextPath;
    return basePath ;
}

function getGlobalVariant(){
    var returnRes={};
    var strCookie=document.cookie;
    //将多cookie切割为多个名/值对
    var arrCookie=strCookie.split("; ");
    var status;
    //遍历cookie数组，处理每个cookie对，找出status对应的值
    for(var i=0;i<arrCookie.length;i++){
        var arr=arrCookie[i].split("=");
        //找到名称为userId的cookie，并返回它的值
        returnRes[arr[0]]=arr[1];
    }
    return returnRes;
}