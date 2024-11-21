document.addEventListener("DOMContentLoaded", () => {
    const loginButton = document.getElementById("loginButton");
    const loginSubmit = document.getElementById("loginSubmit");
  
    loginSubmit.addEventListener("click", () => {
      const username = document.getElementById("username").value;
      const password = document.getElementById("password").value;
  
      if (username && password) {
        // 로그인 성공 시 버튼 변경
        loginButton.innerHTML = `<i class="fa-regular fa-user fa-xl"></i>`;
        loginButton.removeAttribute("data-bs-toggle");
        loginButton.removeAttribute("data-bs-target");
  
        // 모달 닫기
        const loginModal = bootstrap.Modal.getInstance(document.getElementById("loginModal"));
        loginModal.hide();
      } else {
        alert("아이디와 비밀번호를 입력하세요.");
      }
    });
  });
  