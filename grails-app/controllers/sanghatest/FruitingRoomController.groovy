package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import corebackend.simplegenericrestfulcontroller.generic.StatusCode
import grails.converters.JSON

class FruitingRoomController extends SimpleGenericRestfulController<FruitingRoom> {
    FruitingRoomController() {
        super(FruitingRoom)
    }

    @Override
    def save() {
        def JSONObj = request.JSON
        FruitingRoom fruitingRoom = new FruitingRoom()
        fruitingRoom.properties = JSONObj
        def code = FruitingRoom.findByCodeAndIsDelete(fruitingRoom.code, false)
        if (code) {
            render JSONFormat.respond(null, StatusCode.Invalid, "code used already") as JSON
            return
        }
        def index = fruitingRoom.location.indexOf(',')
        if (index >= 0) {
            fruitingRoom.latitude = fruitingRoom.location.substring(0, index) as Double
            fruitingRoom.longitude = fruitingRoom.location.substring(index + 1) as Double
        }
        def investor = Investor.findByIdAndIsDelete(JSONObj.investorId as Long, false)
        if (!investor) {
            render JSONFormat.respond(null, StatusCode.RecordNotFound, "investor not found") as JSON
            return
        }
        fruitingRoom.addToInvestors(investor)
        fruitingRoom.validate()
        if(fruitingRoom.hasErrors()){
            render JSONFormat.respond(null,StatusCode.Invalid,getError(fruitingRoom)) as JSON
            return
        }

        
        fruitingRoom.save(flush: true)
        render JSONFormat.respond(fruitingRoom, StatusCode.OK, "success") as JSON

    }

    @Override
    def beforeUpdate(FruitingRoom fruitingRoom) {
        def index = fruitingRoom.location.indexOf(',')
        if (index >= 0) {
            fruitingRoom.latitude = fruitingRoom.location.substring(0, index)
            fruitingRoom.longitude = fruitingRoom.location.substring(index + 1)
        }
        return fruitingRoom
    }
}
