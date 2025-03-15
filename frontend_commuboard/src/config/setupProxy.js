const { createProxyMiddleware } = require('http-proxy-middleware');

// 백-프론트 사이 데이터 통신을 연결하기 위한 프록시 설정. cors 정책 등.
module.exports = function(app) {
  app.use(
    '/api', // 프록시를 적용할 경로
    createProxyMiddleware({
      target: 'http://localhost:8080', // Spring Boot 서버 주소
      changeOrigin: true,
    })
  );
};
