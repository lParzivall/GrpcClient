package ru.mitrasoft.grpcClient.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mitrasoft.grpcClient.services.AppUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/users")
    public List<String> getAllAppUsers(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password) {
        return appUserService.getAllAppUsers(username, password);
    }
}
