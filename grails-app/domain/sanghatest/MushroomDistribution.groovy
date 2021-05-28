package sanghatest

class MushroomDistribution {
    Long fruitingRoomId
    Long itemId
    Long investorId
    Long uomId
    Double qty
    Date distributionDate
    Double totalPrice
    String note
    String status
    Boolean isCompleted = false
    Boolean isDelete = false
    Date dateCreated
    Date lastUpdated
    static constraints = {
        totalPrice nullable: true
        note nullable: true
    }
}
