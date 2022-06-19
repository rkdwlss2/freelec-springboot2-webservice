package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {
    @Test
    public void real_profile이_조회된다(){
        //given
        String expectProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectProfile);
    }

    @Test
    public void real_profile이_없으면_첫_번째가_조회된다(){
        //given
        String expectProfile = "oauth";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectProfile);
    }

    @Test
    public void active_profile이_없으면_default가_조회된다(){
        //given
        String expectProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectProfile);
    }

}
