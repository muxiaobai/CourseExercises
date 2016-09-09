//获取参数
function GetQueryString(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
//检查是否为空 
function checkNull(data){
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
