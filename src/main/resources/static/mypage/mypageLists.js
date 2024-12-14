fetch('/recruit/posts?page=0')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#recruit-table tbody');

            // 데이터의 각 항목을 테이블에 추가
            data.content.forEach(item => {
                const row = document.createElement('tr');

                row.innerHTML = `
                    <td>${item.id}</td>
                    <td>${item.subject}</td>
                    <td>${item.category}</td>
                    <td>${item.createDate ? new Date(item.createDate).toLocaleString() : 'Unknown'}</td>
                `;
                row.addEventListener('click', () => {
                    window.location.href = `/recruit/detail/${item.id}`;
                });

                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error:', error));