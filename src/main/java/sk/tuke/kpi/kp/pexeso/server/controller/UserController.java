package sk.tuke.kpi.kp.pexeso.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.kpi.kp.pexeso.entity.Players;
import sk.tuke.kpi.kp.pexeso.service.PlayersService;

import java.util.Objects;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UserController {

    @Autowired
    private PlayersService playersService;

    public Players loginPlayer;

    @RequestMapping("/login")
    public String login(@RequestParam String login,@RequestParam String password) {
        if (Objects.equals(password,"" ) || Objects.equals(login, "")){
            return "redirect:/";
        }else {
            loginPlayer= playersService.getPlayer(login,password);
        }
        if (Objects.equals(loginPlayer.getPasswords(), password) && Objects.equals(loginPlayer.getLogins(), login)){
                return "redirect:/pexeso";
            }else{
                 return "redirect:/";
             }

    }

    @RequestMapping("/register")
    public String register(@RequestParam (required = false) String rLogin,@RequestParam (required = false) String firstPassword,@RequestParam (required = false) String secondPassword) {

       if (rLogin!=null || firstPassword!= null || secondPassword != null){
           if (firstPassword.length()>8){
               if (firstPassword.equals(secondPassword)){
                   Players registerPlayer = new Players(rLogin,firstPassword);
                   playersService.addPlayer(registerPlayer);
                   return "redirect:/";
               }
           }
       }
        return "register";
    }

    @RequestMapping("/logout")
    public String logout(){
        loginPlayer = null;
        return "redirect:/";
    }

    public Players getLoginPlayer() {
        return loginPlayer;
    }

    public boolean isLogged(){
        return loginPlayer !=null;
    }
}
