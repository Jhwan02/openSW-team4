document.addEventListener("DOMContentLoaded", function() {
    // 주기적으로 세션 정보를 확인하는 함수
    function checkSession() {
        fetch('/auth/session-info')
            .then(response => response.json())
            .then(data => {
                if (data.loggedIn) {
                    console.log("로그인 상태: " + data.username);
                    // 로그인 상태에 따른 추가 작업 수행
                } else {
                    console.log("로그아웃 상태");
                    // 로그아웃 상태에 따른 추가 작업 수행
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    // 페이지 로드 시 세션 정보 확인
    checkSession();

    // 1분마다
    setInterval(checkSession, 60000); // 1분 = 60000밀리초
});