
//#################################################################################################
//模仿jQuery，创建zhang自己的常用方法对象
/*
;(function() {
    // 注册到window对象和$
     var zhang = window.zhang =window.$$$ = function(selector) {
        return new zhang.fn.init(selector);
    }
    // 版本
    zhang.VERSION = '0.0.1(dev)';
    // 创建fn的命名空间,该内容为框架基础功能
    zhang.fn = zhang.prototype = {
        constructor: zhang,
        // initialize [初始化]
        init : function(selector) {
//            "" ,  null ,  undefined
            if (!selector) {
                selector=document;
            }
            selector = selector || document;
            if (selector.nodeType) {
                this[0] = selector;
                return this;
            }
             if (typeof selector === "string")
                    this[0] = document.getElementById(selector);
             return this;
        }
        
    };
    // zhang.fn缓存zhang.prototype;避免频繁的操作zhang.prototype
   //内部处理了实例创建不用new去生成实例，处理了prototype保证多实例共享方法减少资源开支
    zhang.fn.init.prototype = zhang.fn;
    // 扩展zhang.js对象。用来在fn命名空间上增加新函数
    zhang.extend = zhang.fn.extend = function(obj, property) {
        if (!property) {
            property = obj;
            obj = this;
        }
        // obj用以扩展的对象，prop为扩展的函数集,如果参数只有一个，则扩展新函数到zhang对象上
        for ( var i in property) {
            obj[i] = property[i];
        }
        return obj;
    };
     zhang.extend({
        isNull : function(data){
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
        }
    });
    // 给fn添加的功能，需要先选择节点，然后才能操作
    // 调用方式： [$("id").get();],对象方法
    zhang.fn.extend({
        get : function(name) {
            return name;
        }
    });
    // 给zhang对象添加的功能（静态方法），可以直接点方法名 ，进行操作
    // 调用方式： [$.getQueryString("查询名");]
     zhang.extend({
        getQueryString : function(options) {
            return  zhang.Query.getQueryString(options);
        }
    });
    zhang.Query = {
     getQueryString: function (name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
    }
    };
})(window);
console.log(zhang.Query.getQueryString("id"));

console.log(zhang.VERSION);
console.log($$$());
console.log(zhang.isNull());
console.log(zhang.getQueryString("id"));
console.log(zhang().get("na"));
*/
//#################################################################################################
//Shadow DOM
/*var shadow=document.getElementById("shadow");
var root=shadow.createShadowRoot();
var cnt = document.createElement("div");
cnt.innerHTML = "shadow";
root.appendChild(cnt);
*/

//#################################################################################################
//ES6
/*set map 有序集合 ，有序键值对
var set=new Set();
set.add("zhang");
set.add("wang");
console.log(set); 
console.log(set.has("zhang"));
set.forEach(console.log);
set.forEach(function(i){console.log(i);});
set.clear();
console.log(set.has("zhang"));

console.log("==================================================");
var map=new Map();
map.set("str","val");
map.set({"zx":{}},{"val":"cv"});
console.log(map);
console.log(map.get("str"));
console.log(map.has("str"));
map.forEach(function(i){console.log(i);});
var keys=map.keys();
for (key of keys) {
    console.log(key);
}
var values=map.values();
for (value of values) {
    console.log(value);
}
var entries=map.entries();
for (entry of entries) {
    console.log(entry);
}
map.clear();
console.log(map);
console.log(map.get("str"));
console.log(map.has("str"));
//Symbol使用
console.log(Symbol("is"));
console.log(typeof Symbol());
console.log(Symbol("zx").toString());


*/
//###############################################################################################//
//数据类型
//简单类型和对应的复杂类型，相等问题,实质上是，引用类型和值类型的区别
/*var price1=10;
var price2='10';
var price3=Number('10');
var price4=new Number(10);//引用
var price5=price4;//引用
console.log(price1===price2);//false
console.log(price1===price3);//true
console.log(price1===price4);//false
console.log( price1==price4);//true考虑==和===的区别
console.log(price4===price5);//true
price4=10;
console.log(price4===price5);//false
console.log( typeof price5);//object
console.log( typeof price4);//number
*/
//简单类型 字符串，数字，null undifined true false
//只要不是使用new关键字的实例，对于一上五种类型都是简单类型。
/*var primitiveString1='foo';
var primitiveString2=String('foo');
var primitiveNumber1=12;
var primitiveNumber2=Number(12);
var primitiveBoolean1=true;
var primitiveBoolean2=Boolean('true');
console.log(typeof primitiveString1, typeof primitiveString2);
console.log(typeof primitiveNumber1,typeof primitiveNumber2);
console.log(typeof primitiveBoolean1,typeof primitiveBoolean2);
*/
//原生构造函数,new 复杂类型
/*var mystring=new String('zhang');
var mynumber=new Number(12);
var myboolean=new Boolean(false);
var myobject=new Object();
var myfunction=new Function();
var myarray=new Array();
var mydate=new Date();
var myregexp=new RegExp();
var myerror=new Error();
原生的构造函数都是native code
console.log(String.constructor);
console.log(Number.constructor);
console.log(Boolean.constructor);
console.log(Object.constructor);
console.log(Function.constructor);
console.log(Array.constructor);
console.log(Date.constructor);
console.log(RegExp.constructor);
console.log(Error.constructor);
*/
//使用Object创建其他复杂类型，因为其他的比如Array是继承自Object，然后Array在其中添加了一些常用的方法，
/*
console.log(  new Object('foo') );
console.log(  new Object(1) );
console.log(  new Object(true) );
console.log(  new Object(function(){}) );
console.log(  new Object([]) );
console.log(  new Object(/[a-z]/) );
console.log( typeof new Object('foo') );
console.log( typeof new Object(1) );
console.log( typeof new Object(true) );
console.log( typeof new Object(function(){}) );
console.log( typeof new Object([]) );
console.log( typeof new Object(/[a-z]/) );
*/

//查找对应属性的顺序是 myArray----->Array.prototype------>Object.prototype
/*var myArray=[];
Object.prototype.foo='object-foo';
Array.prototype.foo='array.foo';
myArray.foo='foo';
console.log(myArray);
console.log(myArray.foo);//查找最近的foo属性，如果没有返回undifined
*/

/*除了Function外，类型都是Object
console.log(typeof mystring);
console.log(typeof mynumber);
console.log(typeof myboolean);
console.log(typeof myobject);
console.log('myfunction:'+ typeof myfunction);
console.log(typeof myarray);
console.log(typeof mydate);
console.log(typeof myregexp);
console.log(typeof myerror);
*/
//###############################################################################################//
//对象
//两种创建对象的方法，new Object()和new function方法
/*function(){}
var Person=function(name,age){
    this.name=name;
    this.age=age;
    this.getAge=function(){return this.age;};
}
Person.prototype={constructor:Person};
var myPerson=new Person('zhang',20);
console.log(myPerson);
console.log(myPerson.constructor==Person);
console.log(Person.prototype.constructor);

var User=function(name,age){
    this.name=name;
    this.age=age;
    this.getAge=function(){return this.age;};
}
//User.prototype={constructor:User};
var myUser=new User('zhang',20);
console.log(myUser);
*/
/*new Object();
var myPerson=new Object();
myPerson.name='zhang';
myPerson.age=20;
myPerson.getAge=function(){return this.age;};
console.log(myPerson);
console.log(myPerson.getAge());
*/

/*for in hasOwnProperty
var Person=function(name,age){
    this.name=name;
    this.age=age;
    this.getAge=function(){return this.age;};
}
var myPerson=new Person('zhang',20);
Person.prototype.forin=function(){
    for(var key in this){
        if(myPerson.hasOwnProperty(key)){
        console.log(key+':'+this[key]);
        }else{
        console.log('不是Own的属性'+key+':'+this[key]);  
        }
    }
};
myPerson.forin();
Object.defineProperty(Person.prototype,"constructor",{
    enumerable:false,
});
*/
/*
Object.defineProperty通过这个方法设置对象中的属性或者方法的可读可写可配置可枚举四大属性
//属性
Person.prototype.pro=1;
Object.defineProperty(Person.prototype,"pro",{
    value:2,
    enumerable:true,
    writable:true,
    configurable:true
});
//方法
Person.prototype.getName=function(){return 'method';};
Object.defineProperty(Person.prototype,"getName",{
    enumerable:false,
});
*/
//###############################################################################################//
//函数返回值默认undifined

/*嵌套函数自执行
(function(arg){
    (function(arg){
        console.log(arg);
        console.log('hi');
    })(arg);    
})('adssad');
*/
/*函数自执行，需要依靠(),
(function(arg){
    console.log(arg);
}('my'));

(function(arg){
    console.log(arg);
})('wai');
var fun=function(arg){console.log(arg);}('var');
*/

/*call apply arguments
var myFunction=function(foo,bar){
    this.foo=foo;
    this.bar=bar;
    console.log(this);//指调用者arguments指参数
    console.log(arguments);
    //arguments 有两个属性callee和length  //callee指代函数自己，length指参数的长度
};
console.log(myFunction());
var myObject={'name':'zhang'};
var myObject1={'name':'wang'};

myFunction.call(myObject,'foo','bar');
myFunction.apply(myObject,['foo','ba']);
myFunction.call(myObject1,'foo','bar');
myFunction.apply(myObject1,['foo','ba']);
*/
/*两种形式定义方法，区别在于解释器的提升
var fnuexp=function(){
return ' function expression';
}//函数表达式,不会提升，因此在之前不能被调用
function funlan(){
    return ' function language';
}//函数语句(函数定义),被提升
*/

/*外部的this会带入到对象中，
var myObject={
    fun1:function(bar){
        bar();//window
        console.log('fun1'+this);//myObject
        var fun2=function(){
            console.log('fun2'+this);//window
            var fun3=function(){
            console.log('fun3'+this);       //window        
            }();
        }();
    }
}
var outfun=function(){console.log(this);};
myObject.fun1(outfun);
*/

//###############################################################################################//
//