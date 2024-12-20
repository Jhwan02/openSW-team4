document.addEventListener("DOMContentLoaded", function () {
    const searchButton = document.querySelector(".search-button");
    const searchModal = document.getElementById("searchModal");
    const closeModal = document.getElementById("closeModal");
    const searchInput = document.getElementById("searchInput");
    const searchResults = document.getElementById("searchResults");
    // const container = document.querySelector(".container"); // 데이터가 표시되는 리스트 컨테이너

    // const listItems = Array.from(container.children); // 컨테이너 안의 모든 데이터 항목


    // 모달 열기
    searchButton.addEventListener("click", () => {
        searchModal.style.display = "block";
    });

    // 모달 닫기
    closeModal.addEventListener("click", () => {
        searchModal.style.display = "none";
        searchResults.innerHTML = ""; // 검색 결과 초기화
        searchInput.value = ""; // 검색어 초기화
    });

    // 모달 외부 클릭 시 닫기
    window.addEventListener("click", (event) => {
        if (event.target === searchModal) {
            searchModal.style.display = "none";
            searchResults.innerHTML = ""; // 검색 결과 초기화
            searchInput.value = ""; // 검색어 초기화
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

   
});
