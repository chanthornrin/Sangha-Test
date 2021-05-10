package sanghatest

class InvestorType {
    String name
    String description
    Date dateCreated
    Date lastUpdated
    static constraints = {
        description nullable: true
    }
}
