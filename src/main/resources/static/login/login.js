document.getElementById("loginButton").addEventListener("click", function(event) {
    event.preventDefault(); // 기본 동작 방지

    const id = document.getElementById("id").value;
    const password = document.getElementById("password").value;

    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ id, password }),
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("로그인 성공!");
            const loginButton = document.getElementById("mainLoginButton");
            loginButton.textContent = "마이페이지";
            loginButton.onclick = function() {
                window.location.href = "/mypage"; // 마이페이지로 이동
            };

            // 환영 메시지 표시
            const welcomeMessage = document.getElementById("welcomeMessage");
            welcomeMessage.textContent = `${data.username}님 환영합니다`;
            welcomeMessage.style.display = "inline";

        } else {
            alert("로그인 실패! 아이디와 비밀번호를 확인하세요.");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("로그인 중 오류가 발생했습니다.");
    });
});

document.getElementById("registerButton").addEventListener("click", function(event) {
    event.preventDefault(); // 기본 동작 방지

    const username = document.getElementById("registerUsername").value;
    const id = document.getElementById("registerId").value;
    const password = document.getElementById("registerPassword").value;

    fetch('/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, id, password }),
    })
    .then(response => response.text())
    .then(data => {
        if (data === 'success') {
            alert("회원가입 성공!");
            window.location.href = "/"; // 메인 페이지로 이동
        } else {
            alert("회원가입 실패! 아이디가 이미 존재합니다.");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("회원가입 중 오류가 발생했습니다.");
    });
});
