package com.mysite.sbb.login;

@GetMapping("/session-info")
public ResponseEntity<Map<String, Object>> getSessionInfo(HttpSession session) {
    Map<String, Object> response = new HashMap<>();
    User sessionUser = (User) session.getAttribute("user");

    if (sessionUser != null) {
        response.put("success", true);
        response.put("id", sessionUser.getId());
        response.put("username", sessionUser.getUsername());
        logger.debug("세션 정보: 아이디={}, 이름={}", sessionUser.getId(), sessionUser.getUsername());
    } else {
        response.put("success", false);
        response.put("message", "세션에 사용자 정보가 없습니다.");
        logger.debug("세션 정보가 없습니다.");
    }
    return ResponseEntity.ok(response);
}
