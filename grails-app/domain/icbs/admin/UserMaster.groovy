package icbs.admin

class UserMaster {

    String userName
    String lastName
    String firstName
    String middleName
    Date birthDate
    
    UserPassword password
    Date userAccessExpiryDate
    
    static constraints = {
    }
    
    static mapping = {
        id sqlType:'int', generator:'increment'
    }
    
    def beforeInsert() {
        
    }
    
    def beforeUpdate() {

    }    
    
    String getUserName() {
        "${userName}"
    }
}
