;(function (global){
	var Common = function (){
		return Common.fn.init(global);
	}
	Common.fn = Common.prototype = {
		constructor : Common,
		justNeedNum : function (){
			console.log("justNeedNum");
		},
		doCheck : function (){
			this.justNeedNum();
			console.log("doCheck");
		}
 	}
	
	Common.extend = Common.fn.extend = function() {
		var  options, name, src, copy, target = this, i = 0, length = arguments.length;
		console.log(this);
		for ( ; i < length; i++ ) {
			if ( ( options = arguments[ i ] ) != null ) {
				for ( name in options ) {
					src = target[ name ];
					copy = options[ name ];
					target[ name ] = copy;
				}
			}
		}
		return target;
	}

	init = Common.fn.init =function (global){return this;}
	init.prototype = Common.fn ;
	Common.extend({
	    //空对象
		isEmptyObject : function( obj ) {
			var name;
			for ( name in obj ) {
				return false;
			}
				return true;
		},
		isWindow : function( obj ) {
			return obj != null && obj === obj.window;
		},
		
        //获取参数
        GetQueryString : function (name){
             var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
             var r = window.location.search.substr(1).match(reg);
             if(r!=null)return  unescape(r[2]); return null;
        },
        //检查是否为空 
         isNull : function (data){
    	if(data==null){
    		return true;
    	}
    	if(data==undefined){
    		return true;
    	}
    	if(data==""){
    		return true;
    	}
    	return false;
        },
		each: function( obj, callback ) {
			var length, i = 0;
				for ( i in obj ) {
					if ( callback.call( obj[ i ], i, obj[ i ] ) === false ) {
						break;
					}
				}
			return obj;
		},
	});
	Common.fn.extend({
		trim: function( text ) {
			return text == null ?
				"" :
				( text + "" ).replace( rtrim, "" );
		},
		isArray: Array.isArray
	})
	var rootCommon = Common();
	global.Common = Common;
}(window))





//去处重复数据
function unique(arr) {
    var result = [], hash = {};
    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
        if (!hash[elem]) {
            result.push(elem);
            hash[elem] = true;
        }
    }
    return result;
}
//获取cookie
function getCookie(name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg)){
		   return unescape(arr[2]);
    }else{
    	   return null;
    }
}
//获取参数
function GetQueryString(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}


var data=getMonthStartDate();
console.log(data);
/**
 * 获取本周、本季度、本月、上月的开端日期、停止日期
 */
var now = new Date(); // 当前日期
var nowDayOfWeek = now.getDay(); // 今天本周的第几天
var nowDay = now.getDate(); // 当前日
var nowMonth = now.getMonth(); // 当前月
var nowYear = now.getYear(); // 当前年
nowYear += (nowYear < 2000) ? 1900 : 0; //
var lastMonthDate = new Date(); // 上月日期
lastMonthDate.setDate(1);
lastMonthDate.setMonth(lastMonthDate.getMonth() - 1);
//var lastYear = lastMonthDate.getYear();
var lastMonth = lastMonthDate.getMonth();
// 格局化日期：yyyy-MM-dd
function formatDate(date) {
    var myyear = date.getFullYear();
    var mymonth = date.getMonth() + 1;
    var myweekday = date.getDate();
    if (mymonth < 10) {
        mymonth = "0" + mymonth;
    }
    if (myweekday < 10) {
        myweekday = "0" + myweekday;
    }
    return (myyear + "-" + mymonth + "-" + myweekday);
}
// 获得某月的天数
function getMonthDays(myMonth) {
    var monthStartDate = new Date(nowYear, myMonth, 1);
    var monthEndDate = new Date(nowYear, myMonth + 1, 1);
    var days = (monthEndDate - monthStartDate) / (1000 * 60 * 60 * 24);
    return days;
}
// 获得本季度的开端月份
function getQuarterStartMonth() {
    var quarterStartMonth = 0;
    if (nowMonth < 3) {
        quarterStartMonth = 0;
    }
    if (2 < nowMonth && nowMonth < 6) {
        quarterStartMonth = 3;
    }
    if (5 < nowMonth && nowMonth < 9) {
        quarterStartMonth = 6;
    }
    if (nowMonth > 8) {
        quarterStartMonth = 9;
    }
    return quarterStartMonth;
}
//获得当天
function getCurrDate() {
    return formatDate(now);
}
//获得昨日
function getLastDate() {
    var ld = new Date();
    ld.setDate(now.getDate()-1);//获取AddDayCount天后的日期 
    return formatDate(ld);
}
// 获得本周的开端日期
function getWeekStartDate() {
    var weekStartDate = new Date();
    weekStartDate.setDate(now.getDate()-nowDayOfWeek+1);
    return formatDate(weekStartDate);
}
// 获得本周的停止日期
function getWeekEndDate() {
    var weekStartDate = new Date();
    weekStartDate.setDate(now.getDate()-nowDayOfWeek+7);
    return formatDate(weekStartDate);
}
//获得上周的开端日期
function getLastWeekStartDate() {
    var lastWeekStartDate = new Date();
    lastWeekStartDate.setDate(now.getDate()-nowDayOfWeek-6);
    return formatDate(lastWeekStartDate);
}
//获得上周的停止日期
function getLastWeekEndDate() {
    var lastWeekEndDate = new Date();
    lastWeekEndDate.setDate(now.getDate()-nowDayOfWeek);
    return formatDate(lastWeekEndDate);
}
// 获得本月的开端日期
function getMonthStartDate() {
    var monthStartDate = new Date(nowYear, nowMonth, 1);
    return formatDate(monthStartDate);
}
// 获得本月的停止日期
function getMonthEndDate() {
    var monthEndDate = new Date(nowYear, nowMonth, getMonthDays(nowMonth));
    return formatDate(monthEndDate);
}
// 获得上月开端时候
function getLastMonthStartDate() {
    var lastMonthStartDate = new Date(nowYear, lastMonth, 1);
    return formatDate(lastMonthStartDate);
}
// 获得上月停止时候
function getLastMonthEndDate() {
    var lastMonthEndDate = new Date(nowYear, lastMonth, getMonthDays(lastMonth));
    return formatDate(lastMonthEndDate);
}
// 获得本季度的开端日期
function getQuarterStartDate() {
    var quarterStartDate = new Date(nowYear, getQuarterStartMonth(), 1);
    return formatDate(quarterStartDate);
}
// 或的本季度的停止日期
function getQuarterEndDate() {
    var quarterEndMonth = getQuarterStartMonth() + 2;
    var quarterStartDate = new Date(nowYear, quarterEndMonth,
            getMonthDays(quarterEndMonth));
    return formatDate(quarterStartDate);
}
//上季度的开端日期
function getLastQuarterStartDate() {
    var lastQuarterStartDate = new Date(nowYear, getQuarterStartMonth()-3, 1);
    return formatDate(lastQuarterStartDate);
}
//上季度的停止日期
function getLastQuarterEndDate() {
    var quarterEndMonth = getQuarterStartMonth() - 1;
    var quarterStartDate = new Date(nowYear, quarterEndMonth,
            getMonthDays(quarterEndMonth));
    return formatDate(quarterStartDate);
}


function getSPTime(obj){
    var alltime ="";
    var allsecond ="";
    var day ="";
    allsecond =new Date() - new Date();
    day = getWorkDayCount("cn",new Date(),new Date());
    alltime = timeStamp(allsecond/1000,day);
    return alltime;
}

//秒转xx天xx小时xx分钟xx秒
function timeStamp( second_time,holiday){  
	var time = parseInt(second_time) + "秒";  
	if( parseInt(second_time )> 60){  
	
	  var second = parseInt(second_time) % 60;  
	  var min = parseInt(second_time / 60);  
	  time = min + "分" + second + "秒";  
	    
	  if( min > 60 ){  
	      min = parseInt(second_time / 60) % 60;  
	      var hour = parseInt( parseInt(second_time / 60) /60 );  
	      time = hour + "小时" + min + "分" + second + "秒";  
	
	      if( hour > 24 ){  
	          hour = parseInt( parseInt(second_time / 60) /60 ) % 24;  
	          var day = parseInt( parseInt( parseInt(second_time / 60) /60 ) / 24 );
	          //这里要排除周末 和节假日
	          day -= holiday;
	          time = day + "天" + hour + "小时" + min + "分" + second + "秒";  
	      }  
	  }  
	}  
    return time;          
} 


// $.get("/system/config/holiday/list.do",{year:2017},function(data){console.log(data);})
//法定节假日和调休日的设定
// var Holiday = ["2017-01-01", "2017-01-02", "2017-01-03", "2017-01-22", "2017-01-23", "2017-01-24", "2017-01-25", "2017-01-26", "2017-01-27", "2017-01-28", "2017-04-02", "2017-04-03", "2017-04-04", "2017-04-29", "2017-04-30", "2017-05-01", "2017-06-22", "2017-06-23", "2017-06-24", "2017-09-30", "2017-10-01", "2017-10-02", "2017-10-03", "2017-10-04", "2017-10-05", "2017-10-06", "2017-10-07"];
var Holiday = ["2017-01-01", "2017-01-02", "2017-01-27", "2017-01-28", "2017-01-29", "2017-01-30", "2017-01-31", "2017-02-01", "2017-02-02", "2017-04-02", "2017-04-03", "2017-04-04", "2017-05-01", "2017-05-28", "2017-05-29", "2017-05-30", "2017-10-01", "2017-10-02", "2017-10-03", "2017-10-04", "2017-10-05", "2017-10-06", "2017-10-07","2017-10-08"];

var WeekendsOff = ["2017-01-22", "2017-01-23",  "2017-04-01", "2017-05-27", "2017-09-30"];


function nearlyWeeks (mode, weekcount, end) {
  /*
  功能：计算当前时间（或指定时间），向前推算周数(weekcount)，得到结果周的第一天的时期值；
  参数：
  mode -推算模式（'cn'表示国人习惯【周一至周日】；'en'表示国际习惯【周日至周一】）
  weekcount -表示周数（0-表示本周， 1-前一周，2-前两周，以此推算）；
  end -指定时间的字符串（未指定则取当前时间）；
  */

  if (mode == undefined) mode = "cn";
  if (weekcount == undefined) weekcount = 0;
  if (end != undefined)
      end = new Date(new Date(end).toDateString());
  else
      end = new Date(new Date().toDateString());

  var days = 0;
  if (mode == "cn")
      days = (end.getDay() == 0 ? 7 : end.getDay()) - 1;
  else
      days = end.getDay();

  return new Date(end.getTime() - (days + weekcount * 7) * 24 * 60 * 60 * 1000);
};

function getWorkDayCount (mode, beginDay, endDay) {
  /*
  功能：计算一段时间内工作的天数。不包括周末和法定节假日，法定调休日为工作日，周末为周六、周日两天；
  参数：
  mode -推算模式（'cn'表示国人习惯【周一至周日】；'en'表示国际习惯【周日至周一】）
  beginDay -时间段开始日期；
  endDay -时间段结束日期；
  */
  var begin = new Date(beginDay.toDateString());
  var end = new Date(endDay.toDateString());

  //每天的毫秒总数，用于以下换算
  var daytime = 24 * 60 * 60 * 1000;
  //两个时间段相隔的总天数
  var days = (end - begin) / daytime + 1;
  //时间段起始时间所在周的第一天
  var beginWeekFirstDay = nearlyWeeks(mode, 0, beginDay.getTime()).getTime();
  //时间段结束时间所在周的最后天
  var endWeekOverDay = nearlyWeeks(mode, 0, endDay.getTime()).getTime() + 6 * daytime;

  //由beginWeekFirstDay和endWeekOverDay换算出，周末的天数
  var weekEndCount = ((endWeekOverDay - beginWeekFirstDay) / daytime + 1) / 7 * 2;
  //根据参数mode，调整周末天数的值
  if (mode == "cn") {
      if (endDay.getDay() > 0 && endDay.getDay() < 6)
          weekEndCount -= 2;
      else if (endDay.getDay() == 6)
          weekEndCount -= 1;

      if (beginDay.getDay() == 0) weekEndCount -= 1;
  }
  else {
      if (endDay.getDay() < 6) weekEndCount -= 1;

      if (beginDay.getDay() > 0) weekEndCount -= 1;
  }
  
  //根据调休设置，调整周末天数（排除调休日）
  $.each(WeekendsOff, function (i, offitem) {
      var itemDay = new Date(offitem.split('-')[0] + "/" + offitem.split('-')[1] + "/" + offitem.split('-')[2]);
      //如果调休日在时间段区间内，且为周末时间（周六或周日），周末天数值-1
      if (itemDay.getTime() >= begin.getTime() && itemDay.getTime() <= end.getTime() && (itemDay.getDay() == 0 || itemDay.getDay() == 6))
          weekEndCount -= 1;
  });
  //根据法定假日设置，计算时间段内周末的天数（包含法定假日）
  $.each(Holiday, function (i, itemHoliday) {
      var itemDay = new Date(itemHoliday.split('-')[0] + "/" + itemHoliday.split('-')[1] + "/" + itemHoliday.split('-')[2]);
      //如果法定假日在时间段区间内，且为工作日时间（周一至周五），周末天数值+1
      if (itemDay.getTime() >= begin.getTime() && itemDay.getTime() <= end.getTime() && itemDay.getDay() > 0 && itemDay.getDay() < 6)
          weekEndCount += 1;
  });
  //工作日 = 总天数 - 周末天数（包含法定假日并排除调休日）
  return weekEndCount;
};

//格式化日期
Date.prototype.Format = function (fmt) {  
    var o = {  
        "M+": this.getMonth() + 1, //月份   
        "d+": this.getDate(), //日   
        "h+": this.getHours(), //小时   
        "m+": this.getMinutes(), //分   
        "s+": this.getSeconds(), //秒   
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
        "S": this.getMilliseconds() //毫秒   
    };  
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var k in o)  
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
    return fmt;  
}  
  
var date= new Date().Format("yyyy-MM-dd");//Format("输入你想要的时间格式:yyyy-MM-dd,yyyyMMdd")  

//日期天减一
function daysJian(){  
  var date = new Date();//获取当前时间  
  date.setDate(date.getDate()-1);//设置天数 -1 天  
   var time = date.Format("yyyy-MM-dd");  
}  




//通用事件监听
 // event(事件)工具集，来源：github.com/markyun
    markyun.Event = {
        // 页面加载完成后
        readyEvent : function(fn) {
            if (fn==null) {
                fn=document;
            }
            var oldonload = window.onload;
            if (typeof window.onload != 'function') {
                window.onload = fn;
            } else {
                window.onload = function() {
                    oldonload();
                    fn();
                };
            }
        },
        // 视能力分别使用dom0||dom2||IE方式 来绑定事件
        // 参数： 操作的元素,事件名称 ,事件处理程序
        addEvent : function(element, type, handler) {
            if (element.addEventListener) {
                //事件类型、需要执行的函数、是否捕捉
                element.addEventListener(type, handler, false);
            } else if (element.attachEvent) {
                element.attachEvent('on' + type, function() {
                    handler.call(element);
                });
            } else {
                element['on' + type] = handler;
            }
        },
        // 移除事件
        removeEvent : function(element, type, handler) {
            if (element.removeEventListener) {
                element.removeEventListener(type, handler, false);
            } else if (element.datachEvent) {
                element.detachEvent('on' + type, handler);
            } else {
                element['on' + type] = null;
            }
        },
        // 阻止事件 (主要是事件冒泡，因为IE不支持事件捕获)
        stopPropagation : function(ev) {
            if (ev.stopPropagation) {
                ev.stopPropagation();
            } else {
                ev.cancelBubble = true;
            }
        },
        // 取消事件的默认行为
        preventDefault : function(event) {
            if (event.preventDefault) {
                event.preventDefault();
            } else {
                event.returnValue = false;
            }
        },
        // 获取事件目标
        getTarget : function(event) {
            return event.target || event.srcElement;
        },
        // 获取event对象的引用，取到事件的所有信息，确保随时能使用event；
        getEvent : function(e) {
            var ev = e || window.event;
            if (!ev) {
                var c = this.getEvent.caller;
                while (c) {
                    ev = c.arguments[0];
                    if (ev && Event == ev.constructor) {
                        break;
                    }
                    c = c.caller;
                }
            }
            return ev;
        }
    };