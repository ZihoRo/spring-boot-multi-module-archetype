#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller;

import ${package}.client.common.list.ListWrapper;
import ${package}.client.common.result.Result;
import ${package}.core.user.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by ${userName} on ${today}.
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value = "/findUserNameByDesc", method = RequestMethod.GET)
    public Result<ListWrapper<String>> findUserNameByDesc(
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "currentPage", required = false) Integer currentPage
    ) {
        return userInfoService.findUserNameByDesc(pageSize, currentPage);
    }

    @RequestMapping(value = "/server/time", method = RequestMethod.GET)
    public Date serverTime() {
        return new Date();
    }
}
