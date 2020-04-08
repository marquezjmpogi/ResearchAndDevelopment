package researchanddevelopment

class CarlosController {

    def index() { }
    
    def authenticatePassword(String password, Integer userId){
        Boolean pwdOk = true
        def passkey = md5(UserMaster.get(userId).userName)
        // password is md5 string
        def myPwd = md5(passkey + UserMaster.get(userId).password)
        if (password != myPwd){
            pwdOk = false    
        }
        return pwdOk
    }
    
}
