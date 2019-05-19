/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.UserAuth;
import Responses.BaseResponse;
import Utils.JwTokenHelper;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author KingMathew
 */
@Path("/auth")
public class Auth {
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public BaseResponse getToken(UserAuth user) {
        if (user.getUser().equals("king") && user.getPass().equals("123")){
            String privateKey = JwTokenHelper.getInstance().generatePrivateKey(user.getUser(), user.getPass());
            return new BaseResponse(privateKey, 1);
        }else{
            return new BaseResponse("Usuario y/o contraseña incorrectos.", 0);
        }
        
    }
    
}
