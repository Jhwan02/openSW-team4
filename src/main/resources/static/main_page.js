document.addEventListener("DOMContentLoaded", () => {
  const loginButton = document.getElementById("loginButton");
  const loginSubmit = document.getElementById("loginSubmit");
  const slides = document.querySelectorAll('.hero-section');
  const dots = document.querySelectorAll('.dot');
  let currentSlide = 0;
  let sliderInterval;

  // 로그인 버튼 이벤트
  loginSubmit.addEventListener("click", () => {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    if (username && password) {
      loginButton.innerHTML = `<i class="fa-regular fa-user fa-xl"></i>`;
      loginButton.removeAttribute("data-bs-toggle");
      loginButton.removeAttribute("data-bs-target");

      const loginModal = bootstrap.Modal.getInstance(document.getElementById("loginModal"));
      loginModal.hide();
    } else {
      alert("아이디와 비밀번호를 입력하세요.");
    }
  });

  // 슬라이드 표시 함수
  function showSlide(index) {
    slides.forEach((slide, i) => {
      slide.style.transform = `translateX(${100 * (i - index)}%)`;
    });

    dots.forEach((dot, i) => {
      dot.classList.toggle('active', i === index);
    });

    currentSlide = index;
  }

  // 자동 슬라이드 전환
  function startSlider() {
    sliderInterval = setInterval(() => {
      currentSlide = (currentSlide + 1) % slides.length;
      showSlide(currentSlide);
    }, 3000);
  }

  // 슬라이드 이동 및 자동 슬라이드 타이머 재설정
  dots.forEach(dot => {
    dot.addEventListener('click', () => {
      const index = parseInt(dot.dataset.slide, 10);
      showSlide(index);

      // 자동 슬라이드 타이머 재설정
      clearInterval(sliderInterval);
      startSlider();
    });
  });

  
  // 초기화
  showSlide(currentSlide);
  startSlider();
});
