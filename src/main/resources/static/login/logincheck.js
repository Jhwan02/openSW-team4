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
                //alert("로그인 상태입니다: " + data.username);
                document.getElementById("loginButton").style.display = "none"; // 로그인 버튼 숨기기
            } else {
                // 로그아웃 상태일 때
                //alert("로그아웃 상태입니다!");
                document.getElementById("loginButton").style.display = "block"; // 로그인 버튼 표시
            }
        })
        .catch(error => {
            console.error("Error fetching session info:", error);
        });
});
