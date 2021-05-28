package sanghatest

class Uom {
    String name
    String description
    Date dateCreated
    Date lastUpdated
    static constraints = {
        description nullable: true
    }
}
