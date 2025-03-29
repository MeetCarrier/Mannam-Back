package com.kslj.mannam.oauth2.controller;

import com.kslj.mannam.domain.user.dto.UserSignUpRequestDto;
import com.kslj.mannam.domain.user.enums.SocialType;
import com.kslj.mannam.domain.user.service.UserService;
import com.kslj.mannam.oauth2.dto.OAuth2RegistrationDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OAuthController {

    private final UserService userService;

    @GetMapping("/oauth/signup/detail")
    public String showSignUpForm() {
        return "signup_detail";
    }

    @PostMapping("/oauth/signup/detail")
    public String processSignUp(@ModelAttribute("oAuth2RegistrationDto") OAuth2RegistrationDto request,
                         HttpSession session) {
        String socialId = (String) session.getAttribute("UNREGISTERED_SOCIAL_ID");
        String provider = (String) session.getAttribute("SOCIAL_TYPE");
        SocialType socialType = SocialType.from(provider);

        UserSignUpRequestDto newUser = UserSignUpRequestDto.builder()
                .socialId(socialId)
                .socialType(socialType)
                .nickname(request.getNickname())
                .gender(request.getGender())
                .region(request.getRegion())
                .personalities(request.getPersonalities())
                .preferences(request.getPreferences())
                .interests(request.getInterests())
                .build();

        userService.createUser(newUser);

        return "redirect:/welcome";
    }
}
