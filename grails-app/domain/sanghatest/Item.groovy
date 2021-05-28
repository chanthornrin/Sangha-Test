package sanghatest

class Item {
    String name
    Double price
    Long uomId
    Date dateCreated
    Date lastUpdated
    static constraints = {
        price nullable: true
        uomId nullable: true
    }
}
