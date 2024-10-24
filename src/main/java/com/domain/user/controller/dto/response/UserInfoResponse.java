package com.domain.user.controller.dto.response;

import com.domain.user.domain.Account;
import lombok.Getter;

@Getter
public class UserInfoResponse {

    private final String alias;
    private final String loginId;
    private final String imageUrl;
    private final String job;
    private final String genre;
    private final String introduce;
    private final String age;

    private final int agreePolicyCount;

    private UserInfoResponse(Account account) {
        this.alias = account.getUser().getAlias();
        this.loginId = account.getLoginId();
        this.imageUrl = account.getUser().getProfileImageUrl();
        this.job = account.getUser().getJob();
        this.genre = account.getUser().getGenre();
        this.introduce = account.getUser().getIntroduce();
        this.agreePolicyCount = account.getUser().getUserPolicies().size();
        this.age = account.getUser().getAge();
    }

    public static UserInfoResponse from(Account account) {
        return new UserInfoResponse(account);
    }

}
