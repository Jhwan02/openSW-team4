document.querySelectorAll('.table tbody tr').forEach(row => {
    row.addEventListener('click', () => {
        window.location.href = '/post-detail.html';
    });
});
