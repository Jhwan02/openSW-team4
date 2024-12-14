document.addEventListener("DOMContentLoaded", () => {
    // 로그인 상태 확인
    fetch("/auth/session-info")
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch session info");
            }
            return response.json();
        })
        .then(data => {
            if (data.loggedIn) {
                // 로그인 상태일 때
                //alert(`로그인 상태입니다. 사용자 이름: ${data.username}, 아이디: ${data.id}`);
                document.getElementById("loginButton").style.display = "none"; // 로그인 버튼 숨기기
                document.getElementById("myPageButtonContainer").style.display = "block"; // 마이페이지 버튼 보이기
                
                const myPageButton = document.getElementById("myPageButton");
                myPageButton.onclick = function() {
                    window.location.href = "/mypage"; // 마이페이지로 이동
                }
                if (window.location.pathname === '/'){
                    document.getElementById("mainLoginButton").style.display = "none"; // 메인 로그인 버튼 보이기
                }
                
 
        }
            else {
                // 로그아웃 상태일 때
                //alert("로그아웃 상태입니다!");
                document.getElementById("loginButton").style.display = "block"; // 로그인 버튼 표시
                document.getElementById("myPageButtonContainer").style.display = "none"; // 마이페이지 버튼 보이기
                if (window.location.pathname === '/'){
                    document.getElementById("mainLoginButton").style.display = "block"; // 메인 로그인 버튼 보이기
                }
                
            }
        })
        .catch(error => {
            console.error("Error fetching session info:", error);
        });
});
