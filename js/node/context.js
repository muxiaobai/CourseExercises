/*作用域和上下文
*上下文
*
*/
/*
var pet={
	words:'pet',
	speak:function () {
		console.log(this.words);
		console.log(this);
		console.log(this===pet);
	}
}
pet.speak();
*/

/*function pet(words){
	this.words=words;
	console.log(this.words);
	console.log(this);
}
pet('...');
*/

function pet(words) {
	this.words=words;
	this.speak=function () {
	console.log(this.words);
	console.log(this);
	}
}
var cat=new pet("Miao");
cat.speak();
/*
*作用域
*/
/*var globalVarible="This is global varible";
function globalFunction() {
	var localVarible="This is local varible";
	console.log("Visit global/local varible");
	console.log(globalVarible);
	console.log(localVarible);
	globalVarible="This is Change varible";
	console.log(globalVarible);
	function localFunction() {
		var innerLocalVarible="This is inner local varible";
		console.log("Visit global/local/innerLocal varible");
		console.log(globalVarible);
		console.log(localVarible);
		console.log(innerLocalVarible);
	}
	localFunction();
}
globalFunction();*/