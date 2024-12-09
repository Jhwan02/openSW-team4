// DOM ��� ��������
const searchButton = document.querySelector('.search-button');
const searchModal = document.getElementById('searchModal');
const closeModal = document.getElementById('closeModal');
const searchInput = document.getElementById('searchInput');
const searchResults = document.getElementById('searchResults');
const cards = document.querySelectorAll('.card');
const modal = document.getElementById("imageModal");
const modalImg = document.getElementById("modalImage");
const closeBtn = document.querySelector(".close");

// �˻� ��ư Ŭ�� �� ��� ����
searchButton?.addEventListener('click', () => {
    searchModal.style.display = 'block';
    searchInput.focus(); // �˻�â�� ��Ŀ��
});

// ��� �ݱ� ��ư Ŭ�� �� ��� �ݱ�
closeModal?.addEventListener('click', () => {
    closeSearchModal();
});

// ��� �ܺ� Ŭ�� �� ��� �ݱ�
window.addEventListener('click', (event) => {
    if (event.target === searchModal) {
        closeSearchModal();
    }
});

// �˻� �Է� �̺�Ʈ
searchInput?.addEventListener('input', () => {
    const keyword = searchInput.value.toLowerCase();
    filterCards(keyword);
});

// ī�� ���͸� �Լ�
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

    // �˻� ����� ���� ��� �޽��� ǥ��
    searchResults.innerHTML = found ? '' : `<li>�˻� ����� �����ϴ�.</li>`;
}

// ��� ī�� ǥ�� �Լ�
function showAllCards() {
    cards.forEach((card) => {
        card.style.display = 'block';
    });
}

// �˻� ��� �ݱ� �Լ�
function closeSearchModal() {
    searchModal.style.display = 'none';
    searchInput.value = ''; // �˻��� �ʱ�ȭ
    searchResults.innerHTML = ''; // �˻� ��� �ʱ�ȭ
    showAllCards(); // ��� ī�� �ٽ� ǥ��
}

// �̹��� Ŭ�� �� Ȯ�� ��� ����
document.querySelectorAll(".card-image img").forEach((image) => {
    image.addEventListener("click", function () {
        modal.style.display = "block";
        modalImg.src = this.src;
    });
});

// ��� �ݱ� ��ư Ŭ�� �̺�Ʈ
closeBtn?.addEventListener("click", () => {
    modal.style.display = "none";
});

// ��� �ܺ� Ŭ�� �� �ݱ�
modal?.addEventListener("click", (event) => {
    if (event.target === modal) {
        modal.style.display = "none";
    }
});

// ī�� ������ Ŭ�� �� Ư�� URL�� �̵�
document.querySelectorAll('.card-content').forEach((content) => {
    content.addEventListener('click', () => {
        window.location.href = "http://localhost:8080/recruit/list"; // �̵��� URL ����
    });
});
