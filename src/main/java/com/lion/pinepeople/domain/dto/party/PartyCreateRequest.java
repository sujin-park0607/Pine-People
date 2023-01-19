package com.lion.pinepeople.domain.dto.party;

<<<<<<< HEAD
=======
import com.lion.pinepeople.domain.entity.Party;
import com.lion.pinepeople.domain.entity.User;
>>>>>>> fd5ca66bb71328ea1b95556c58ccee548c89b8e1
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PartyCreateRequest {
    private String partyTitle;
    private String partyContent;
    private Integer partySize;
    private Integer partyCost;
    private Date startDate;
    private Date endDate;
    private String address;
    private String announcement;

    public Party toEntity(User user){
        return Party.builder()
                .partyContent(this.partyContent)
                .address(this.address)
                .partySize(this.partySize)
                .partyCost(this.partyCost)
                .partyTitle(this.partyTitle)
                .announcement(this.announcement)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .user(user)
                .build();
    }


}
