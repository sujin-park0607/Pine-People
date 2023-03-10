//package com.lion.pinepeople.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lion.pinepeople.domain.dto.admin.AllBlackListResponse;
//import com.lion.pinepeople.domain.dto.admin.BlackListRequest;
//import com.lion.pinepeople.domain.dto.admin.BlackListResponse;
//import com.lion.pinepeople.domain.dto.user.role.UserRoleResponse;
//import com.lion.pinepeople.domain.entity.BlackList;
//import com.lion.pinepeople.domain.entity.User;
//import com.lion.pinepeople.enums.UserRole;
//import com.lion.pinepeople.exception.ErrorCode;
//import com.lion.pinepeople.exception.customException.AppException;
//import com.lion.pinepeople.service.AdminService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(AdminController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//class AdminControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    AdminService adminService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    String url1 = "/api/admin/1/change-role";
//    String url2 = "/api/admin/black-lists";
//
//
//
//    @Test
//    @DisplayName("?????? ?????? ??????")
//    @WithMockUser
//    void change_role_success() throws Exception{
//        UserRoleResponse response = new UserRoleResponse("test","???????????? ????????? ?????????????????????.");
//        given(adminService.changeRole(any(),any())).willReturn(response);
//
//        mockMvc.perform(post(url1)
//                .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.result.userName").value(response.getUserName()))
//                .andExpect(jsonPath("$.result.message").value(response.getMessage()))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("?????? ????????? ?????? admin??? ??????")
//    @WithMockUser
//    void change_role_fail_admin() throws Exception{
//        given(adminService.changeRole(any(),any())).willThrow(new AppException(ErrorCode.INVALID_PERMISSION, "?????? ????????? ADMIN ???????????????."));
//
//        mockMvc.perform(post(url1)
//                        .with(csrf()))
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.resultCode").value("ERROR"))
//                .andExpect(jsonPath("$.result.errorCode").value(String.valueOf(ErrorCode.INVALID_PERMISSION)))
//                .andExpect(jsonPath("$.result.message").value("?????? ????????? ADMIN ???????????????."))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("????????? ?????? ??? ?????? ??????")
//    @WithMockUser
//    void change_role_fail_non_userId() throws Exception{
//
//        given(adminService.changeRole(any(),any())).willThrow(new AppException(ErrorCode.USER_NOT_FOUND, "?????? ????????? ????????? ????????? ?????? ??? ????????????."));
//
//        mockMvc.perform(post(url1)
//                        .with(csrf()))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.resultCode").value("ERROR"))
//                .andExpect(jsonPath("$.result.errorCode").value(String.valueOf(ErrorCode.USER_NOT_FOUND)))
//                .andExpect(jsonPath("$.result.message").value("?????? ????????? ????????? ????????? ?????? ??? ????????????."))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("??????????????? ?????? ??????")
//    @WithMockUser
//    void addBlackList_success() throws Exception{
//        BlackListRequest request = new BlackListRequest(1L);
//        String response = "??????????????? ?????? ?????????????????????";
//        given(adminService.addBlackList(any(),any())).willReturn(response);
//
//        mockMvc.perform(post(url2)
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.resultCode").value("SUCCESS"))
//                .andExpect(jsonPath("$.result").value(response))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("??????????????? ?????? ??????")
//    @WithMockUser
//    void deleteBlackList_success() throws Exception{
//        url2 += "/1";
//        String response = "????????????????????? ?????? ?????? ???????????????.";
//        given(adminService.deleteBlackList(any(),any())).willReturn(response);
//
//        mockMvc.perform(delete(url2)
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.resultCode").value("SUCCESS"))
//                .andExpect(jsonPath("$.result").value(response))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("??????????????? ?????? ?????? ??????")
//    @WithMockUser
//    void getAllBlackList_success() throws Exception{
//        BlackList blackList = BlackList.builder()
//                .blackListId(1L)
//                .startDate(LocalDateTime.now())
//                .build();
//        Page<AllBlackListResponse> response = new PageImpl<>(List.of(AllBlackListResponse.fromEntity(blackList)));
//
//        given(adminService.getAllBlackList(any(),any())).willReturn(response);
//
//        mockMvc.perform(get(url2)
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.resultCode").value("SUCCESS"))
//                .andExpect(jsonPath("$.result.content[0].blackListId").value(1L))
//                .andExpect(jsonPath("$.result.content[0].startDate").exists())
//                .andExpect(jsonPath("$.result.pageable").exists())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("??????????????? ?????? ?????? ??????")
//    @WithMockUser
//    void getBlackList_success() throws Exception{
//        url2 += "/1";
//        BlackListResponse response = BlackListResponse.builder()
//                .blackListId(1L)
//                .startDate(LocalDateTime.now())
//                .fromUserEmail(List.of("aaa@naver.com"))
//                .build();
//
//        given(adminService.getBlackList(any(),any())).willReturn(response);
//        System.out.println(response.getFromUserEmail().get(0));
//        mockMvc.perform(get(url2)
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.resultCode").value("SUCCESS"))
//                .andExpect(jsonPath("$.result.blackListId").value(1L))
//                .andExpect(jsonPath("$.result.startDate").exists())
//                .andExpect(jsonPath("$.result.fromUserEmail[0]").value(response.getFromUserEmail().get(0)))
//                .andDo(print());
//    }
//
//}