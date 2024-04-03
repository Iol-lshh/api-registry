package org.lshh.skeleton.core.port;

import org.lshh.skeleton.core.router.Router;

public interface RoutingPort {
    // 요청받음 -> 오케스트레이터 생성 -> 오케스트레이터 처리 -> 응답반환
    // 비동기? 동기?
        // 처리 방식(동기? 비동기?), 반환 방식(동기? 비동기?)
    // lazy 모드(후 콜백 반환)와 eager(바로 반환) 모드

    RoutingPort setRouter(Router router);

    Object request(String path);
}
