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
    .then(response => response.text())
    .then(data => {
        if (data === 'success') {
            alert("로그인 성공!");
            window.location.href = "/"; // 메인 페이지로 이동
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
        body: JSON.stringify({ username , id , password }),
    })
    .then(response => response.text())
    .then(data => {
        if (data === 'success') {
            alert(`Username: ${username}\nID: ${id}\nPassword: ${password}`); //디버깅용 나중에 지워야함
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
