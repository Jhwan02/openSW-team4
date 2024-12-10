// Script 로드 확인
console.log("Script loaded");

// like-button 및 like-count 요소 확인
console.log(document.getElementById("like-button"));

document.addEventListener("DOMContentLoaded", function () {
    const likeButton = document.getElementById("like-button");
    const likeCountSpan = document.getElementById("like-count");

    // 버튼의 data-question-id 속성에서 질문 ID를 가져오기
    const questionId = likeButton?.getAttribute("data-question-id");

    if (!questionId) {
        console.error("Invalid question ID");
        return;
    }

    // 좋아요 버튼 클릭 이벤트
    likeButton.addEventListener("click", function () {
        console.log("Like button clicked");

        // Fetch 요청으로 서버에 좋아요 요청 전송
        fetch(`/question/like/${questionId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(response => {
                if (!response.ok) {
                    // 서버 응답이 오류인 경우 에러 처리
                    return response.json().then(err => {
                        throw new Error(err.message || "좋아요 요청 중 문제가 발생했습니다.");
                    });
                }
                return response.json();
            })
            .then(data => {
                console.log("Server response:", data);

                if (data.success) {
                    // 서버에서 성공적으로 좋아요 수를 반환했을 경우
                    likeCountSpan.textContent = `좋아요 ${data.likes}`;
                } else {
                    // 서버에서 실패 메시지가 전달된 경우
                    console.warn("Server reported failure:", data.message);
                    alert(data.message || "좋아요 처리 실패");
                }
            })
            .catch(error => {
                // 오류 메시지 출력 및 사용자 알림
                console.error("Error:", error);
                alert("좋아요 처리 중 오류가 발생했습니다.");
            });
    });
});
