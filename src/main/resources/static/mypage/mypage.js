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

document.getElementById('show-password-form').addEventListener('click', function () {
    document.getElementById('password-form').style.display = 'block'; // 입력 창 표시
    this.style.display = 'none'; // 버튼 숨기기
});

document.getElementById('cancel-password-form').addEventListener('click', function () {
    document.getElementById('password-form').style.display = 'none'; // 입력 창 숨기기
    document.getElementById('show-password-form').style.display = 'inline-block'; // 버튼 다시 표시
});

document.getElementById('submit-password').addEventListener('click', function () {
    const newPassword = document.getElementById('new-password').value;

    if (!newPassword) {
        alert("비밀번호를 입력하세요.");
        return;
    }

    // Ajax 요청으로 비밀번호 변경
    fetch('/auth/change-password', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `newPassword=${encodeURIComponent(newPassword)}`, // URL-encoded 데이터
    })
    .then(response => {
        if (response.ok) {
            alert("비밀번호가 성공적으로 변경되었습니다.");
            document.getElementById('password-form').style.display = 'none';
            document.getElementById('show-password-form').style.display = 'inline-block';
        } else {
            alert("비밀번호 변경 중 오류가 발생했습니다.");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("비밀번호 변경 요청 중 문제가 발생했습니다.");
    });
});