package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.StatusCode
import grails.gorm.transactions.Transactional

@Transactional
class MushroomCollectionService {

    def validateMushroomCollection(MushroomCollection mushroomCollection) {
        def investor = Investor.findByIdAndIsDelete(mushroomCollection.investorId, false)
        if (!investor) {
            return JSONFormat.respond(null, StatusCode.RecordNotFound, "investor not found")
        }
        def fruitingRoom = FruitingRoom.findByIdAndIsDelete(mushroomCollection.fruitingRoomId, false)
        if (!fruitingRoom) {
            return JSONFormat.respond(null, StatusCode.RecordNotFound, "fruitingRoom not found")
        }
        return mushroomCollection
    }

    def getAllMushroomCollection(Long investorId, Long fruitingRoomId, String isDelete, PaginationCommand paginationCommand) {
        return MushroomCollection.createCriteria().list(paginationCommand.params) {
            eq("isDelete", Boolean.valueOf(isDelete))
            if (investorId) {
                eq("investorId", investorId)
            }
            if(fruitingRoomId){
                eq("fruitingRoomId",fruitingRoomId)
            }

        }
    }
        def removeMushroomCollection(Long investorId) {
            def mushroomCollection = MushroomCollection.findByInvestorIdAndIsDelete(investorId, false)
            if (!mushroomCollection) {
                return null
            }
                mushroomCollection.each {
                    it.isDelete = true
                    it.save(flush: true)
                }

        }

        }


