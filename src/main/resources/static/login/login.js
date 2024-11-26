document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // 폼 기본 동작 막기

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
    })
    .then(response => response.text())
    .then(data => {
        if (data === 'success') {
            alert("로그인 성공!");
            location.reload(); // 페이지 새로고침 (필요한 경우)
        } else {
            alert("로그인 실패! 아이디와 비밀번호를 확인하세요.");
        }
    })
    .catch(error => console.error('Error:', error));
});
