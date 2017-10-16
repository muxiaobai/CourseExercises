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