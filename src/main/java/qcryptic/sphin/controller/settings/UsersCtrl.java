package qcryptic.sphin.controller.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qcryptic.sphin.enums.SettingsEnum;
import qcryptic.sphin.service.IUsersSvc;
import qcryptic.sphin.vo.DbResponseVo;

import static qcryptic.sphin.controller.settings.SettingsCtrl.defaultModelAdds;

/**
 * Created by Kyle on 10/21/2017.
 */
@Controller
@RequestMapping("/settings/users")
public class UsersCtrl {

    @Autowired
    private IUsersSvc usersSvc;

    @GetMapping
    public String users(Model model) {
        model = defaultModelAdds(model, SettingsEnum.USERS);
        String[] roles = new String[]{"Admin", "Everyone"};
        model.addAttribute("userRoles", roles);
        return "pages/settings";
    }

    @ResponseBody
    @PostMapping("/makeInvite")
    public DbResponseVo makeInvite(@RequestParam("rank") String rankName) {
        return usersSvc.generateInviteLink(rankName, 24);
    }

}
