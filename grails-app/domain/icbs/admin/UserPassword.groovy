package icbs.admin

class UserPassword {
    
    UserMaster user
    String password
    Date passwordDate
    Date expiryDate
    
    static constraints = {
    }
    
    static mapping = {
        id sqlType:'int', generator:'increment'
    }    
}
