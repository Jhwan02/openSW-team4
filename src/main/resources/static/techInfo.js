document.addEventListener("DOMContentLoaded", function () {
    let sortOrder = "desc"; // 기본 정렬은 최신순

    const toggleSortButton = document.getElementById("toggleSortButton");
    const postContainer = document.getElementById("post-container");

    // 초기 데이터 로드
    loadPosts(sortOrder);

    // 정렬 버튼 클릭 이벤트
    toggleSortButton.addEventListener("click", () => {
        sortOrder = sortOrder === "desc" ? "asc" : "desc";
        toggleSortButton.textContent = `정렬: ${sortOrder === "desc" ? "최신순" : "오래된 순"}`;
        loadPosts(sortOrder);
    });

    // 서버에서 데이터 로드
    function loadPosts(order) {
        fetch(`/api/techInfo?sort=${order}`)
            .then((response) => response.json())
            .then((data) => {
                postContainer.innerHTML = ""; // 기존 데이터 초기화
                data.forEach((post) => {
                    const listItem = document.createElement("li");
                    listItem.className = "container-list";
                    listItem.innerHTML = `
                        <a href="${post.link}" target="_blank">${post.title}</a>
                        <p>Date: ${post.date}</p>
                    `;
                    postContainer.appendChild(listItem);
                });
            })
            .catch((error) => console.error("Error loading posts:", error));
    }
});
