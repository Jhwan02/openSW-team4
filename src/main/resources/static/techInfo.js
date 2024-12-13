document.addEventListener("DOMContentLoaded", function () {
    const searchButton = document.querySelector(".search-button");
    const searchModal = document.getElementById("searchModal");
    const closeModal = document.getElementById("closeModal");
    const searchInput = document.getElementById("searchInput");
    const searchResults = document.getElementById("searchResults");
    const container = document.querySelector(".container"); // 데이터가 표시되는 리스트 컨테이너
    const itemsPerPage = 15; // 한 페이지에 표시할 데이터 수
    let currentPage = 1; // 현재 페이지
    const listItems = Array.from(container.children); // 컨테이너 안의 모든 데이터 항목

    // 전체 페이지 수 계산
    const totalPages = Math.ceil(listItems.length / itemsPerPage);

    // 모달 열기
    searchButton.addEventListener("click", () => {
        searchModal.style.display = "block";
    });

    // 모달 닫기
    closeModal.addEventListener("click", () => {
        searchModal.style.display = "none";
    });

    // 모달 외부 클릭 시 닫기
    window.addEventListener("click", (event) => {
        if (event.target === searchModal) {
            searchModal.style.display = "none";
        }
    });

    // 검색 기능
    searchInput.addEventListener("input", () => {
        const keyword = searchInput.value.trim();
        if (keyword.length > 0) {
            fetch(`/api/search?keyword=${encodeURIComponent(keyword)}`)
                .then((response) => response.json())
                .then((data) => {
                    searchResults.innerHTML = ""; // 기존 결과 초기화
                    data.forEach((post) => {
                        const listItem = document.createElement("li");
                        listItem.textContent = post.title;
                        listItem.addEventListener("click", () => {
                            window.open(post.link, "_blank");
                        });
                        searchResults.appendChild(listItem);
                    });
                })
                .catch((error) => console.error("Error fetching search results:", error));
        } else {
            searchResults.innerHTML = ""; // 검색어 없을 시 초기화
        }
    });

    // 페이지 렌더링
    function renderPage(page) {
        // 모든 항목 숨기기
        listItems.forEach((item, index) => {
            if (index >= (page - 1) * itemsPerPage && index < page * itemsPerPage) {
                item.style.display = "flex"; // 현재 페이지 항목만 표시
            } else {
                item.style.display = "none"; // 나머지는 숨김
            }
        });
    }

    // 페이지네이션 버튼 생성
    function createPagination() {
        const paginationContainer = document.createElement("div");
        paginationContainer.classList.add("pagination");

        for (let i = 1; i <= totalPages; i++) {
            const button = document.createElement("button");
            button.textContent = i;
            button.classList.add("page-button");
            if (i === currentPage) button.classList.add("active");

            // 페이지 버튼 클릭 이벤트
            button.addEventListener("click", () => {
                currentPage = i;
                renderPage(currentPage);
                updatePagination(paginationContainer);
            });

            paginationContainer.appendChild(button);
        }

        // 기존 페이지네이션이 있으면 삭제 후 다시 추가
        const existingPagination = document.querySelector(".pagination");
        if (existingPagination) {
            existingPagination.remove();
        }
        container.parentElement.appendChild(paginationContainer);
    }

    // 페이지네이션 업데이트
    function updatePagination(paginationContainer) {
        const buttons = paginationContainer.querySelectorAll(".page-button");
        buttons.forEach((button, index) => {
            if (index + 1 === currentPage) {
                button.classList.add("active");
            } else {
                button.classList.remove("active");
            }
        });
    }

    // 초기화
    renderPage(currentPage);
    createPagination();
});
