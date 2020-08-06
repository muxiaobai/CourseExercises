// 加 md5
fis.match('*.{png,js,css}', {
    release: '/$0',
    useHash: true
  });
// 启用 fis-spriter-csssprites 插件
fis.match('::package', {
spriter: fis.plugin('csssprites')
});

// 对 CSS 进行图片合并
fis.match('*.css', {
// 给匹配到的文件分配属性 `useSprite`
useSprite: true
});

fis.match('*.js', {
// fis-optimizer-uglify-js 插件进行压缩，已内置
optimizer: fis.plugin('uglify-js')
});

fis.match('*.css', {
// fis-optimizer-clean-css 插件进行压缩，已内置
optimizer: fis.plugin('clean-css')
});

// fis.match('*.html', {
//   optimizer: fis.plugin('html-compress')
// });
// fis.match('*.html', {
//   //invoke fis-optimizer-html-minifier
//   optimizer: fis.plugin('html-minifier')
// });
fis.match('*.png', {
  //fis-optimizer-png-compressor 插件进行压缩，已内置
optimizer: fis.plugin('png-compressor')
});

//fis3 release -d ./output
//npm install  --save-dev
//npm install 
//npm start