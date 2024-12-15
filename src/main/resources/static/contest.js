// DOM ??? ????????
const searchButton = document.querySelector('.search-button');
const searchModal = document.getElementById('searchModal');
const closeModal = document.getElementById('closeModal');
const searchInput = document.getElementById('searchInput');
const searchResults = document.getElementById('searchResults');
const cards = document.querySelectorAll('.card');
const modal = document.getElementById("imageModal");
const modalImg = document.getElementById("modalImage");
const closeBtn = document.querySelector(".close");

// ??? ??? ??? ?? ??? ????
searchButton?.addEventListener('click', () => {
    searchModal.style.display = 'block';
    searchInput.focus(); // ?????? ??��??
});

// ??? ??? ??? ??? ?? ??? ???
closeModal?.addEventListener('click', () => {
    closeSearchModal();
});

// ??? ??? ??? ?? ??? ???
window.addEventListener('click', (event) => {
    if (event.target === searchModal) {
        closeSearchModal();
    }
});

// ??? ??? ????
searchInput?.addEventListener('input', () => {
    const keyword = searchInput.value.toLowerCase();
    filterCards(keyword);
});

// ??? ????? ???
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

    // ??? ????? ???? ??? ????? ???
    searchResults.innerHTML = found ? '' : `<li>??? ????? ???????.</li>`;
}

// ??? ??? ??? ???
function showAllCards() {
    cards.forEach((card) => {
        card.style.display = 'block';
    });
}

// ??? ??? ??? ???
function closeSearchModal() {
    searchModal.style.display = 'none';
    searchInput.value = ''; // ????? ????
    searchResults.innerHTML = ''; // ??? ??? ????
    showAllCards(); // ??? ??? ??? ???
}

// ????? ??? ?? ??? ??? ????
document.querySelectorAll(".card-image img").forEach((image) => {
    image.addEventListener("click", function () {
        modal.style.display = "block";
        modalImg.src = this.src;
    });
});

// ??? ??? ??? ??? ????
closeBtn?.addEventListener("click", () => {
    modal.style.display = "none";
});

// ??? ??? ??? ?? ???
modal?.addEventListener("click", (event) => {
    if (event.target === modal) {
        modal.style.display = "none";
    }
});

// // ??? ?????? ??? ?? ??? URL?? ???
// document.querySelectorAll('.card-content').forEach((content) => {
//     content.addEventListener('click', () => {
//         window.location.href = "http://localhost:8080/recruit/list"; // ????? URL ????
//     });
// });

document.querySelectorAll('.recruit-button').forEach((button) => {
    button.addEventListener('click', (event) => {
        event.stopPropagation(); // 부모 클릭 이벤트 무시
        window.location.href = "http://localhost:8080/contest/create";
    });
});