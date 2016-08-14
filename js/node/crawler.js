var http=require('http');
var cheerio=require('cheerio');
var url='http://www.imooc.com/learn/348';
//取数据
function filterChapter(html) {
	var $=cheerio.load(html);
	var chapters=$('.chapter');
	var courseData=[];
	chapters.each(function (item) {
		var chapter=$(this);
		var chapterTitle=chapter.find('strong').text();
		var videos=chapter.find('.video').children('li');
		var chapterData={
			chapterTitle:chapterTitle,
			videos:[]
		}

		// [{
		// 	chapterTitle:'',
		// 	videos:[title:'',id:'']
		// }]
		//videos数据
		videos.each(function (item) {
			var video=$(this).find('studyvideo');
			var videoTitle=video.text();
			var id=video.attr('href').split('video/')[1];
			chapterData.videos.push({
				title:videoTitle,
				id:id
			});
		});
		courseData.push(chapterData);
	});
	return courseData;
}
//打印数据
function printCourseInfo(courseData) {
	courseData.forEach(function (item) {
		var chapterTitle=item.chapterTitle;
		console.log(chapterTitle+'\n');
		item.videos.forEach(function (video) {
			console.log(video.id+':'+video.title);
		});
	});
}
//
http.get(url,function (response) {
	var html='';
	response.on('data',function (data) {
		html+=data;
	});
	response.on('end',function () {
		//console.log(html);
		var courseData=filterChapter(html);
		console.log(courseData);
		printCourseInfo(courseData);
	});
}).on('error',function () {
	console.log('获取课程出错！');
})

