package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import corebackend.simplegenericrestfulcontroller.generic.StatusCode
import grails.converters.JSON

class ItemController extends SimpleGenericRestfulController<Item> {
    ItemController() {
        super(Item)
    }
    def itemService

    @Override
    def index(PaginationCommand paginationCommand) {
        String name = params.name
        Long uomId = params.uomId as Long
        def result = itemService.getAllItem(name, uomId, paginationCommand)
        render JSONFormat.respond(result) as JSON
    }

    def getAllMushroomType(PaginationCommand paginationCommand) {
        String name = params.name
        def result = itemService.listAllMushRoomType(name, paginationCommand)
        render JSONFormat.respond(result) as JSON
    }

    @Override
    def beforeSave(Item item) {
        def uom = Uom.findById(item.uomId)
        if (!uom) {
            return JSONFormat.respond(null, StatusCode.RecordNotFound, "uom not found")
        }
        return item
    }

    @Override
    def beforeUpdate(Item item) {
        def uom = Uom.findById(item.uomId)
        if (!uom) {
            return JSONFormat.respond(null, StatusCode.RecordNotFound, "uom not found")
        }
        return item
    }
}
