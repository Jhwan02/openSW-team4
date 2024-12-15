fetch('/auth/posts?page=0')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#recruit-table tbody');

            // 데이터의 각 항목을 테이블에 추가
            data.content.forEach(item => {
                const row = document.createElement('tr');
                if(item.boardName === '자유'){ //자유게시판 데이터인 경우 카테고리를 자유게시판으로 설정
                    item.data.category = '자유';
                }
                row.innerHTML = `
                    <td>${item.boardName}</td>
                    <td>${item.data.id}</td>
                    <td>${item.data.subject}</td>
                    <td>${item.data.category}</td>
                    <td>${item.data.createDate ? new Date(item.data.createDate).toLocaleString() : 'Unknown'}</td>
                `;
                row.addEventListener('click', () => {
                    if(item.boardName === '공모전')
                        window.location.href = `/recruit/detail/${item.data.id}`;
                    else if(item.boardName === '자유')
                        window.location.href = `/question/detail/${item.data.id}`;
                });

                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching posts:', error));

fetch('/auth/comments?page=0')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#comment-table tbody');

            // 데이터의 각 항목을 테이블에 추가
            data.content.forEach(item => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${item.boardName}</td>
                    <td>${item.questionTitle}</td>
                    <td>${item.data.content}</td>
                    <td>${item.data.createDate ? new Date(item.data.createDate).toLocaleString() : 'Unknown'}</td>
                `;
                
                row.addEventListener('click', () => {
                    if(item.boardName === '공모전')
                        window.location.href = `/recruit/detail/${item.questionId}`;
                    else if(item.boardName === '자유')
                        window.location.href = `/question/detail/${item.qusetionId}`;
                });

                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching comments:', error));