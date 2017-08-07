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