package sanghatest

class Investor {
    String code
    String firstName
    String lastName
    Long investorTypeId
    String photo
    String address
    Long provinceId
    Long districtId
    Long communeId
    String phoneLine1
    String phoneLine2
    Double investmentPercent
    Double investmentCost
    Date startInvestDate
    Date endInvestDate
    Date registerDate
    Long bankId
    String bankHolderNumber
    Boolean isDelete = false
    Date dateCreated
    Date lastUpdated
    static constraints = {
        code unique: true
        photo nullable: true
        phoneLine2 nullable: true
        bankId nullable: true
        bankHolderNumber nullable: true
    }
}

