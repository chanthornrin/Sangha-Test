package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.StatusCode
import grails.gorm.transactions.Transactional

@Transactional
class MushroomDistributionService {
    def validateMushroomDistribution(MushroomDistribution mushroomDistribution) {
        def investor = Investor.findByIdAndIsDelete(mushroomDistribution.investorId, false)
        if (!investor) {
            return JSONFormat.respond(null, StatusCode.RecordNotFound, "investor not found")
        }
        def fruitingRoom = FruitingRoom.findByIdAndIsDelete(mushroomDistribution.fruitingRoomId, false)
        if (!investor) {
            return JSONFormat.respond(null, StatusCode.RecordNotFound, "fruitingRoom not found")
        }
        def item = Item.findById(mushroomDistribution.itemId)
        if (!item) {
            return JSONFormat.respond(null, StatusCode.RecordNotFound, "item not found")
        }
        def uom = Uom.findById(mushroomDistribution.uomId)
        if (!uom) {
            return JSONFormat.respond(null, StatusCode.RecordNotFound, "uom not found")
        }
        return mushroomDistribution

    }

    def getAllMushroomDistribution(Long investorId, Long fruitingRoomId, Long itemId, String isDelete, String status, PaginationCommand paginationCommand) {
        return MushroomDistribution.createCriteria().list(paginationCommand.params) {
            eq("isDelete", Boolean.valueOf(isDelete))
            if (investorId) {
                eq("investorId", investorId)
            }
            if (fruitingRoomId) {
                eq("fruitingRoomId", fruitingRoomId)
            }
            if (itemId) {
                eq("itemId", itemId)
            }
            if (status) {
                eq("status", status)
            }
        }
    }
    def removeMushroomDistribution(Long investorId){
        def mushroomDistribution=MushroomDistribution.findByInvestorIdAndIsDelete(investorId,false)
        if(!mushroomDistribution){
            return null
        }
        mushroomDistribution.isDelete=true
        mushroomDistribution.save(flush:true)
    }
}
