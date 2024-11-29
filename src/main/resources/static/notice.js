document.addEventListener("DOMContentLoaded", () => {
    const notices = [
      { id: 1, title: "새로운 기능 업데이트", link: "/notices/1" },
      { id: 2, title: "유지보수 일정 안내", link: "/notices/2" },
      { id: 3, title: "사용자 가이드 변경", link: "/notices/3" }
    ];
  
    const listGroup = document.querySelector(".list-group");
  
    notices.forEach((notice) => {
      const listItem = document.createElement("li");
      listItem.className = "list-group-item";
  
      const link = document.createElement("a");
      link.href = notice.link;
      link.textContent = `${notice.title}`;
      link.target = "_blank";
  
      listItem.appendChild(link);
      listGroup.appendChild(listItem);
    });
  });
  