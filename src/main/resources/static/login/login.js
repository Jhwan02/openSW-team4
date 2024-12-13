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
            const loginModal = document.getElementById("loginModal"); // 모달의 ID
            alert("로그인 성공!");
            if (loginModal) {
                // Bootstrap을 사용하는 경우
                const bootstrapModal = bootstrap.Modal.getInstance(loginModal);
                loginModal.classList.remove("show"); // 페이드 아웃
                setTimeout(() => {
                    loginModal.style.display = "none"; // 애니메이션 후 숨김 처리
                }, 300); // 0.3초 후 실행 (CSS의 transition 시간과 동일하게 설정)
                if (bootstrapModal) bootstrapModal.hide();

                // 수동으로 모달 숨기기 (CSS로 직접 제어)
                loginModal.style.display = "none"; 
            }
            const loginButton = document.getElementById("mainLoginButton");
            loginButton.style.display = "none"; // 로그인 버튼 숨기기

            const myPageButtonContainer = document.getElementById("myPageButtonContainer");
            myPageButtonContainer.style.display = "block"; // 마이페이지 버튼 보이기

            const myPageButton = document.getElementById("myPageButton");
            myPageButton.onclick = function() {
                window.location.href = "/mypage"; // 마이페이지로 이동

            // 환영 메시지 표시
            const welcomeMessage = document.getElementById("welcomeMessage");
            welcomeMessage.textContent = `${data.username}님 환영합니다`;
            welcomeMessage.style.display = "inline";
            }
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
