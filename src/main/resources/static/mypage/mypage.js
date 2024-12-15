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

function deleteUser(id) {
    if (confirm("정말로 탈퇴하시겠습니까?")) {
        fetch('/auth/delete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id: id })
        })
        .then(response => {
            if (response.ok) {
                alert("탈퇴되었습니다.");
                window.location.href = "/"; // 탈퇴 후 리다이렉트
            } else {
                alert("탈퇴 중 오류가 발생했습니다.");
            }
        })
        .catch(error => console.error("Error:", error));
    }
}