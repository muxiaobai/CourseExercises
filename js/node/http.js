var http=require('http');
http.createServer(function (request,response){
	response.writeHead(200,{'Content-Type':'text/plain'});
	response.writer('Hello World');
	response.end();
}).listen(1337);