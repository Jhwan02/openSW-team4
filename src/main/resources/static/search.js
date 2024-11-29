document.addEventListener("DOMContentLoaded", function () {
    const searchButton = document.querySelector(".search-button");
    const searchModal = document.getElementById("searchModal");
    const closeModal = document.getElementById("closeModal");
    const searchInput = document.getElementById("searchInput");
    const searchResults = document.getElementById("searchResults");

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
            fetch(`/question/api/search?keyword=${encodeURIComponent(keyword)}`)
                .then((response) => response.json())
                .then((data) => {
                    searchResults.innerHTML = ""; // 기존 결과 초기화
                    data.forEach((item) => {
                        const listItem = document.createElement("li");
                        listItem.textContent = item.subject;
                        listItem.addEventListener("click", () => {
                            window.location.href = `/question/detail/${item.id}`; // 해당 글로 이동
                        });
                        searchResults.appendChild(listItem);
                    });
                })
                .catch((error) => console.error("Error fetching search results:", error));
        } else {
            searchResults.innerHTML = ""; // 검색어 없을 시 초기화
        }
    });
});
