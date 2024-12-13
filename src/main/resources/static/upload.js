$(document).ready(function() {
    // 업로드 버튼 클릭 이벤트
    $('.uploadBtn').click(function () {
        var formData = new FormData();

        // 파일 input에서 선택한 파일들 가져오기
        var inputFile = $("input[type='file']");
        var files = inputFile[0].files;

        // 선택된 파일들 formData에 추가
        for (var i = 0; i < files.length; i++) {
            formData.append("uploadFiles", files[i]);
        }

        // 업로드 요청
        $.ajax({
            url: '/uploadAjax',  // 서버로 이미지 파일을 업로드하는 URL
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            dataType: 'json',
            success: function (result) {
                console.log(result);  // 서버 응답 확인
                // 업로드된 이미지를 화면에 표시
                showUploadedImages(result);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("Error: " + textStatus);
            }
        });
    });

    // Ajax 업로드 이후 이미지들을 호출하는 함수
    function showUploadedImages(result) {
        console.log(result);  // 서버에서 반환된 결과 확인

        var divArea = $(".uploadResult");  // 이미지를 출력할 영역 (div)

        divArea.append("<img src='" + result.imageUrl + "' alt='Uploaded Image' style='max-width: 100%; height: auto;'>");
        
    }
});
