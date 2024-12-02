// DOM ��� ��������
const searchButton = document.querySelector('.search-button');
const searchModal = document.getElementById('searchModal');
const closeModal = document.getElementById('closeModal');
const searchInput = document.getElementById('searchInput');
const searchResults = document.getElementById('searchResults');
const cards = document.querySelectorAll('.card');

// �˻� ��ư Ŭ�� �� ��� ����
searchButton.addEventListener('click', () => {
    searchModal.style.display = 'block';
    searchInput.focus(); // �˻�â�� �ٷ� ��Ŀ��
});

// ��� �ݱ� ��ư Ŭ�� �� ��� �ݱ�
closeModal.addEventListener('click', () => {
    searchModal.style.display = 'none';
    searchInput.value = ''; // �Էµ� �˻��� �ʱ�ȭ
    searchResults.innerHTML = ''; // �˻� ��� �ʱ�ȭ
    showAllCards(); // ��� ī�� �ٽ� ǥ��
});

// ��� �ܺ� Ŭ�� �� ��� �ݱ�
window.addEventListener('click', (event) => {
    if (event.target === searchModal) {
        searchModal.style.display = 'none';
        searchInput.value = '';
        searchResults.innerHTML = '';
        showAllCards();
    }
});

// �˻� �Է� �̺�Ʈ
searchInput.addEventListener('input', () => {
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
    if (!found) {
        searchResults.innerHTML = `<li>�˻� ����� �����ϴ�.</li>`;
    } else {
        searchResults.innerHTML = '';
    }
}

// ��� ī�� ǥ�� �Լ�
function showAllCards() {
    cards.forEach((card) => {
        card.style.display = 'block';
    });
}
