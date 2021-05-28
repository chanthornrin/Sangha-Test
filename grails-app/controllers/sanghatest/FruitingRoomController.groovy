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
    def convertNumberToWordService
    def fruitingRoomService

    @Override
    def index(PaginationCommand paginationCommand) {
        String code = params.code
        String name = params.name
        String isDelete = params.isDelete
        Long investorId = params.investorId as Long
        def result = fruitingRoomService.getAllFruitingRoom(code, name, investorId, isDelete, paginationCommand)
        render JSONFormat.respond(result) as JSON
        def convert = convertNumberToWordService.convertLessThanOneThousand(999)
        //def convertNumber=convertNumberToWordService.convertZeroToBillions(1000000)
        println(convert)
        //  println(convertNumber)
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
        if (fruitingRoom.hasErrors()) {
            render JSONFormat.respond(null, StatusCode.Invalid, getError(fruitingRoom)) as JSON
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

    def assignFruitingRoomBelongToInvestor() {
        def JSONObject = request.JSON
        def fruitingRoom = FruitingRoom.findByIdAndIsDelete(JSONObject.fruitingRoomId as Long, false)
        if (!fruitingRoom) {
            render JSONFormat.respond(null, StatusCode.RecordNotFound, "fruitingRoom not found") as JSON
            return
        }
        def investor = Investor.findByIdAndIsDelete(JSONObject.investorId as Long, false)
        if (!investor) {
            render JSONFormat.respond(null, StatusCode.RecordNotFound, "investor not found") as JSON
            return
        }
        fruitingRoom.investors.find{
            if(it==investor){
                render JSONFormat.respond(null,StatusCode.Invalid,"fruitingRoom assign already") as JSON
                return
            }
            fruitingRoom.addToInvestors(investor)
            fruitingRoom.validate()
            if(fruitingRoom.hasErrors()){
                render JSONFormat.respond(null,StatusCode.Invalid,getError(fruitingRoom)) as JSON
                return
            }
            fruitingRoom.save(flush:true)
            render JSONFormat.respond(fruitingRoom) as JSON
        }
    }
    @Override
    def show() {
        def object = FruitingRoom.findById(params.id as Long)
        if (!object)
            render JSONFormat.respondSingleObject(null, StatusCode.RecordNotFound, "fruitingRoom not found") as JSON
        Map<String, FruitingRoom> fruitingRoom = new LinkedHashMap<>(object.properties)
        fruitingRoom.id = object.id
        //find size of investor
        fruitingRoom.totalInvestors = object.investors.size()
        render JSONFormat.respondSingleObject(fruitingRoom) as JSON
    }
}
