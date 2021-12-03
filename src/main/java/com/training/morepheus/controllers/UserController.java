package com.training.morepheus.controllers;

import com.training.morepheus.models.User;
import com.training.morepheus.services.ApiService;
import com.training.morepheus.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController implements ApiController<User, Long>{

    private final ApiService<User, Long> userService;

    /**
     * @param userService
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param page
     * @param sort
     * @param order
     * @return
     */
    @GetMapping
    @Override
    @PreAuthorize("hasAnyRole('REG_USER','ADMIN')")
    public List<User> getAll(
            @RequestParam(name = "page", required = false) Long page,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "order", required = false) String order) {
        return userService.getAll(page,sort,order);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(path = "{id}")
    @Override
    @PreAuthorize("hasAnyRole('REG_USER','ADMIN')")
    public User getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    /**
     * @param user
     * @return
     */
    @PostMapping
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    /**
     * @param id
     * @param user
     * @return
     */
    @PutMapping(path = "{id}")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public User update(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    /**
     * @param id
     */
    @DeleteMapping(path = "{id}")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
