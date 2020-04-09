package icbs.admin

class Branch {
    
    String name
    String address
    String tin
    Date openingDate
    
    static constraints = {
    }
    
    static mapping = {
        id sqlType:'int', generator:'increment'
    }     
}
