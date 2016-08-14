/*
*call apply
*/

// var pet={
// 	words:'....',
// 	speak:function (say) {
// 		console.log(say+''+this.words);
// 	}
// }
//pet.speak("Speak");
// var dog={
// 	words:'Wang'
// }
// pet.speak.call(dog,'Speak');//类似java的反射invoke


function Pet(words) {
	console.log('Pet:'+words);
	this.words=words;
	this.speak=function () {
		console.log(this.words);
	}
}
function Dog(words) {
	console.log('Dog:'+words);
	Pet.call(this,words);
}
var dog=new Dog('Wang');
dog.speak();