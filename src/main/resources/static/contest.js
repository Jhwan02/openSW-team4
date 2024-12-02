// DOM 요소 가져오기
const searchButton = document.querySelector('.search-button');
const searchModal = document.getElementById('searchModal');
const closeModal = document.getElementById('closeModal');
const searchInput = document.getElementById('searchInput');
const searchResults = document.getElementById('searchResults');
const cards = document.querySelectorAll('.card');

// 검색 버튼 클릭 시 모달 열기
searchButton.addEventListener('click', () => {
    searchModal.style.display = 'block';
    searchInput.focus(); // 검색창에 바로 포커스
});

// 모달 닫기 버튼 클릭 시 모달 닫기
closeModal.addEventListener('click', () => {
    searchModal.style.display = 'none';
    searchInput.value = ''; // 입력된 검색어 초기화
    searchResults.innerHTML = ''; // 검색 결과 초기화
    showAllCards(); // 모든 카드 다시 표시
});

// 모달 외부 클릭 시 모달 닫기
window.addEventListener('click', (event) => {
    if (event.target === searchModal) {
        searchModal.style.display = 'none';
        searchInput.value = '';
        searchResults.innerHTML = '';
        showAllCards();
    }
});

// 검색 입력 이벤트
searchInput.addEventListener('input', () => {
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
    if (!found) {
        searchResults.innerHTML = `<li>검색 결과가 없습니다.</li>`;
    } else {
        searchResults.innerHTML = '';
    }
}

// 모든 카드 표시 함수
function showAllCards() {
    cards.forEach((card) => {
        card.style.display = 'block';
    });
}
