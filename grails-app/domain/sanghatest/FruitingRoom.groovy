package sanghatest

class FruitingRoom {
    String code
    String photo
    Double longitude
    Double latitude
    String name
    String size
    String location
    Date installationDate
    Double installationCost
    String installationTools
    Boolean isDelete = false
    Date dateCreated
    Date lastUpdated
    static hasMany = [investors: Investor]
    static mapping = {
        investors cascade: 'all-delete-orphan'
    }

    static constraints = {
        code unique: true
        photo nullable: true
        installationCost nullable: true
        installationTools nullable: true
    }
}
