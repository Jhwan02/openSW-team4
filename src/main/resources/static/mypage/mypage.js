document.addEventListener("DOMContentLoaded", () => {
    // 서버에서 데이터를 가져오는 함수
    fetch("/user/getuserdata") // 백엔드의 API URL
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch user info");
            }
            return response.json(); // JSON 형식으로 응답 받기
        })
        .then(data => {
            // 데이터를 HTML 요소에 삽입
            document.getElementById("name").textContent = data.username;
            document.getElementById("id").textContent = data.id;
        })
        .catch(error => {
            console.error("Error fetching user info:", error);
        });
});
