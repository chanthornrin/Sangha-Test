package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import grails.converters.JSON

class MushroomCollectionController extends SimpleGenericRestfulController<MushroomCollection> {

    MushroomCollectionController() {
        super(MushroomCollection)
    }

    def mushroomCollectionService

    @Override
    def index(PaginationCommand paginationCommand) {
        Long investorId = params.investorId as Long
        Long fruitingRoomId = params.fruitingRoomId as Long
        String isDelete = params.isDelete
        def result = mushroomCollectionService.getAllMushroomCollection(investorId, fruitingRoomId, isDelete, paginationCommand)
        render JSONFormat.respond(result) as JSON
    }

    @Override
    def beforeSave(MushroomCollection mushroomCollection) {
        mushroomCollectionService.validateMushroomCollection(mushroomCollection)
        return mushroomCollection
    }

    @Override
    def beforeUpdate(MushroomCollection mushroomCollection) {
        mushroomCollectionService.validateMushroomCollection(mushroomCollection)
        return mushroomCollection
    }

}
