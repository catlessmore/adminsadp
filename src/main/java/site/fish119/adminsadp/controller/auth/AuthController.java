package site.fish119.adminsadp.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.fish119.adminsadp.controller.BaseController;
import site.fish119.adminsadp.domain.sys.User;
import site.fish119.adminsadp.security.AuthConstant;
import site.fish119.adminsadp.security.AuthRequest;
import site.fish119.adminsadp.service.auth.AuthService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller.auth
 * @Author fish119
 * @Date 2018/4/10 19:24
 * @Version V1.0
 */
@RestController
public class AuthController extends BaseController {
    private final AuthService service;

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        final String token = service.login(authRequest.getUsername(), authRequest.getPassword());
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "/auth/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
        HashMap<String, String> map = new HashMap<>();
        if (StringUtils.isEmpty(request.getHeader(AuthConstant.tokenHeader))) {
            map.put("error", "old token is null");
        } else {
            String token = request.getHeader(AuthConstant.tokenHeader);
            String refreshedToken = service.refresh(token);
            if (refreshedToken == null) {
                return ResponseEntity.badRequest().body(null);
            } else {
                map.put("token", token);
            }
        }
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public User register(@RequestBody AuthRequest addedUser) {
        return service.register(addedUser);
    }

    @RequestMapping(value = "/auth/registerAdmin", method = RequestMethod.POST)
    public User registerAdmin(@RequestBody AuthRequest addedUser) {
        return service.registerAdmin(addedUser);
    }

}
