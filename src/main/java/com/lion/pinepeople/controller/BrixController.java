package com.lion.pinepeople.controller;

import com.lion.pinepeople.domain.brix.BrixRequest;
import com.lion.pinepeople.domain.response.Response;
import com.lion.pinepeople.service.BrixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/{userId}/brix")
@RequiredArgsConstructor
@Slf4j
public class BrixController {
    private final BrixService brixService;

    /**
     *
     * @param userId 당도평가를 진행할 대상 아이디
     * @param request 별점
     * @param authentication 토큰 권한
     * @return 당도 평가 성공 메세지
     */
    @PostMapping
    public Response<Void> calculationBrix(@PathVariable Long userId, @RequestBody BrixRequest request, Authentication authentication){
        Long loginUserId = Long.parseLong(authentication.getName());
        String result = brixService.calculationBrix(request, userId, loginUserId);
        return Response.success(result);
    }

    /**
     *
     * @param userId 당도를 조회할 대상 아이디
     * @param authentication 로그인한 아이디
     * @return brix개수
     */
    @GetMapping
    public Response<Double> getBrix(@PathVariable Long userId, Authentication authentication){
        Long loginUserId = Long.parseLong(authentication.getName());
        Double result = brixService.getBrix(loginUserId, userId);
        return Response.success(result);
    }
}
