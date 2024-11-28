document.addEventListener("DOMContentLoaded", () => {
  const loginButton = document.getElementById("loginButton");
  const loginSubmit = document.getElementById("loginSubmit");
  const slides = document.querySelectorAll('.hero-section');
  const dots = document.querySelectorAll('.dot');
  let currentSlide = 0;
  let sliderInterval;

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
