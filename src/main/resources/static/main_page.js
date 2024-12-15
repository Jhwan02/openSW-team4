document.addEventListener("DOMContentLoaded", () => {
  const loginButton = document.getElementById("loginButton");
  const loginSubmit = document.getElementById("loginSubmit");
  const slides = document.querySelectorAll('.hero-section');
  const dots = document.querySelectorAll('.dot');
  let currentSlide = 0;
  let sliderInterval;
  
  // 햄버거 버튼과 드롭다운 메뉴
  const hamburger = document.getElementById("hamburger");
  const dropdownMenu = document.getElementById("dropdownMenu");

  // 햄버거 버튼 클릭 이벤트
  hamburger.addEventListener("click", () => {
    dropdownMenu.classList.toggle("show"); // 드롭다운 메뉴 표시/숨김
  });

// 드롭다운 외부 클릭 시 닫기
document.addEventListener("click", (event) => {
  if (!hamburger.contains(event.target) && !dropdownMenu.contains(event.target)) {
    dropdownMenu.classList.remove("show");
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


async function fetchAndRenderHotCompetitions() {
  try {
      // API 호출
      const response = await fetch('/api/crawler/top3');
      if (!response.ok) {
          throw new Error('데이터를 가져오는 데 실패했습니다.');
      }

      // JSON 데이터로 파싱
      const competitions = await response.json();

      // HTML DOM에 렌더링
      const hotSection = document.querySelector('.hot-section .row');
      hotSection.innerHTML = ''; // 기존 정적 콘텐츠 제거
      // 공모전 데이터 디버그
      console.log(competitions);
      // 각 공모전 데이터를 HTML로 생성
      competitions.forEach((competition, index) => {
          const hotPostHTML = `
              <div class="col-md-4 mb-4">
                  <div class="hot-post card">
                      <img src="${competition.imageUrl}" class="card-img-top" alt="HOT 게시물 ${index + 1}">
                      <div class="card-body">
                          <h5 class="card-title">${competition.title}</h5>
                          <p class="card-text">D-Day: ${competition.date}</p>
                          <p class="card-text">조회수: ${competition.view}</p>
                          <a href="http://localhost:8080/competitions" target="_blank" class="btn btn-primary">더 보기</a>
                      </div>
                  </div>
              </div>
          `;
          hotSection.insertAdjacentHTML('beforeend', hotPostHTML);
      });
  } catch (error) {
      console.error('Error fetching competitions:', error);
      alert('🔥인기 공모전 데이터를 가져오는 데 문제가 발생했습니다.');
  }
}

// 페이지 로드 시 인기 공모전 데이터 가져오기
document.addEventListener('DOMContentLoaded', fetchAndRenderHotCompetitions);
