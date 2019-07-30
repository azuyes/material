$(document).ready(function () {
    //日期设置
    var mydate = new Date();
    var currentYear = mydate.getFullYear();//年
    var currentMonth = mydate.getMonth() + 1;//月
    $('#year_Input').val(currentYear);
    $('#month_Input').val(currentMonth);
});