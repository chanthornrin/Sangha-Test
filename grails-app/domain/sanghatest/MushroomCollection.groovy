package sanghatest

class MushroomCollection {
    Long investorId
    Long fruitingRoomId
    Double totalQty = 0.0
    Boolean isDelete = false
    Date dateCreated
    Date lastUpdated

    static hasMany = [collectionItems: CollectionItem]
    static mapping = {
        collectionItems cascade: 'all-delete-orphan'
    }
    static constraints = {
    }
}
