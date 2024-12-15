document.addEventListener("DOMContentLoaded", function () {
    // 모든 포스터 보기 버튼에 이벤트 추가
    const lookPosterButtons = document.querySelectorAll(".look_poster");
    const goFindTeamButtons = document.querySelectorAll(".go_find_team");

    // 포스터 보기 버튼 클릭 시
    lookPosterButtons.forEach((button, index) => {
        button.addEventListener("click", () => {
            const parentItem = button.closest(".competition-item"); // 현재 버튼이 속한 카드
            const posterImage = parentItem.querySelector(".competition-image").src; // 이미지 src 가져오기

            // 모달 생성
            const modal = document.createElement("div");
            modal.className = "modal-overlay";
            modal.innerHTML = `
                <div class="modal-content">
                    <img src="${posterImage}" alt="Poster" class="modal-poster">
                    <button class="modal-close">닫기</button>
                </div>
            `;
            document.body.appendChild(modal);

            // 모달 닫기
            const closeButton = modal.querySelector(".modal-close");
            closeButton.addEventListener("click", () => {
                document.body.removeChild(modal);
            });

            // 모달 외부 클릭 시 닫기
            modal.addEventListener("click", (event) => {
                if (event.target === modal) {
                    document.body.removeChild(modal);
                }
            });
        });
    });

    // 인원 모집 버튼 클릭 시
    goFindTeamButtons.forEach((button) => {
        button.addEventListener("click", () => {
            window.location.href = "http://localhost:8080/contest/create"; // contest.html로 이동
        });
    });
});