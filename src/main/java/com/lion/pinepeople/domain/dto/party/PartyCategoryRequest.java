package com.lion.pinepeople.domain.dto.party;

import com.lion.pinepeople.domain.entity.Category;
import com.lion.pinepeople.domain.entity.Party;
import com.lion.pinepeople.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PartyCategoryRequest {

    private String partyTitle;
    private String partyContent;
    private Integer partySize;
    private Integer partyCost;
    private Date startDate;
    private Date endDate;
    private String address;
    private String announcement;
    private String branch; // 카테고리 대분류
    private String code; // 카테고리 소분류

    public Party toEntity(Category category, User user){
        return Party.builder()
                .partyContent(this.partyContent)
                .address(this.address)
                .partySize(this.partySize)
                .partyCost(this.partyCost)
                .partyTitle(this.partyTitle)
                .announcement(this.announcement)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .category(category)
                .user(user)
                .build();
    }

}