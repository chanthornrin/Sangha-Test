package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import grails.gorm.transactions.Transactional

@Transactional
class FruitingRoomService {

    def getAllFruitingRoom(String code, String name, Long investorId, String isDelete, PaginationCommand paginationCommand) {
        return FruitingRoom.createCriteria().list(paginationCommand.params) {
            eq("isDelete", Boolean.valueOf(isDelete))
            if (code) {
                eq("code", code)
            }
            if (name) {
                ilike("name", "%${name}%")
            }
            if (investorId) {
                investors {
                    eq("id", investorId)
                }
            }
        }

    }
    def removeFruitingRoom(def investor) {
        def fruitingRoom = FruitingRoom.findAllByIsDelete(false)
        if (!fruitingRoom) {
            return null
        }
        fruitingRoom.investors.find{
            if(it == investor){
                fruitingRoom.isDelete=true
                fruitingRoom.save(flush:true)
            }
        }
    }


}
