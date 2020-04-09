package researchanddevelopment

class CarlosController {
    def newServiceCarlosService
    
    def index() { }
    
    def login(){
        println params
        def pwdOk = true
        pwdOk = newServiceCarlosService.authenticate(params.password, params.userId)
        if (pwdOk != true){
            return 'ErrorLogin'
        }
        
    }

    
}
