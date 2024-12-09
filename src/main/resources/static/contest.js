// DOM 요소 가져오기
const searchButton = document.querySelector('.search-button');
const searchModal = document.getElementById('searchModal');
const closeModal = document.getElementById('closeModal');
const searchInput = document.getElementById('searchInput');
const searchResults = document.getElementById('searchResults');
const cards = document.querySelectorAll('.card');
const modal = document.getElementById("imageModal");
const modalImg = document.getElementById("modalImage");
const closeBtn = document.querySelector(".close");

// 검색 버튼 클릭 시 모달 열기
searchButton?.addEventListener('click', () => {
    searchModal.style.display = 'block';
    searchInput.focus(); // 검색창에 포커스
});

// 모달 닫기 버튼 클릭 시 모달 닫기
closeModal?.addEventListener('click', () => {
    closeSearchModal();
});

// 모달 외부 클릭 시 모달 닫기
window.addEventListener('click', (event) => {
    if (event.target === searchModal) {
        closeSearchModal();
    }
});

// 검색 입력 이벤트
searchInput?.addEventListener('input', () => {
    const keyword = searchInput.value.toLowerCase();
    filterCards(keyword);
});

// 카드 필터링 함수
function filterCards(keyword) {
    let found = false;
    cards.forEach((card) => {
        const title = card.querySelector('.card-title').textContent.toLowerCase();
        if (title.includes(keyword)) {
            card.style.display = 'block';
            found = true;
        } else {
            card.style.display = 'none';
        }
    });

    // 검색 결과가 없는 경우 메시지 표시
    searchResults.innerHTML = found ? '' : `<li>검색 결과가 없습니다.</li>`;
}

// 모든 카드 표시 함수
function showAllCards() {
    cards.forEach((card) => {
        card.style.display = 'block';
    });
}

// 검색 모달 닫기 함수
function closeSearchModal() {
    searchModal.style.display = 'none';
    searchInput.value = ''; // 검색어 초기화
    searchResults.innerHTML = ''; // 검색 결과 초기화
    showAllCards(); // 모든 카드 다시 표시
}

// 이미지 클릭 시 확대 모달 열기
document.querySelectorAll(".card-image img").forEach((image) => {
    image.addEventListener("click", function () {
        modal.style.display = "block";
        modalImg.src = this.src;
    });
});

// 모달 닫기 버튼 클릭 이벤트
closeBtn?.addEventListener("click", () => {
    modal.style.display = "none";
});

// 모달 외부 클릭 시 닫기
modal?.addEventListener("click", (event) => {
    if (event.target === modal) {
        modal.style.display = "none";
    }
});

// 카드 콘텐츠 클릭 시 특정 URL로 이동
document.querySelectorAll('.card-content').forEach((content) => {
    content.addEventListener('click', () => {
        window.location.href = "http://localhost:8080/recruit/list"; // 이동할 URL 설정
    });
});
