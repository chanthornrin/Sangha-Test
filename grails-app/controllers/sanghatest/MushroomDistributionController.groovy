package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import corebackend.simplegenericrestfulcontroller.generic.StatusCode
import grails.converters.JSON

class MushroomDistributionController extends SimpleGenericRestfulController<MushroomDistribution> {
    MushroomDistributionController() {
        super(MushroomDistribution)
    }

    def mushroomDistributionService

    @Override
    def index(PaginationCommand paginationCommand) {
        Long investorId = params.investorId
        Long fruitingRoomId = params.fruitingRoomId
        Long itemId = params.itemId
        String status = params.status
        String isDelete = params.isDelete
        def result = mushroomDistributionService.getAllMushroomDistribution(investorId, fruitingRoomId, itemId, isDelete, status, paginationCommand)
        render JSONFormat.respond(result) as JSON
    }

    @Override
    def beforeSave(MushroomDistribution mushroomDistribution) {
        mushroomDistribution.status = Sangha.MushroomDistribution.New
        mushroomDistributionService.validateMushroomDistribution(mushroomDistribution)
        return mushroomDistribution
    }

    @Override
    def beforeUpdate(MushroomDistribution mushroomDistribution) {
        if (mushroomDistribution.isCompleted) {
            mushroomDistribution.status = Sangha.MushroomDistribution.Completed
        }
        mushroomDistributionService.validateMushroomDistribution(mushroomDistribution)
        return mushroomDistribution
    }

    @Override
    def delete() {
        def mushroomDistribution = MushroomDistribution.findByIdAndIsDelete(params.id as Long, false)
        if (!mushroomDistribution) {
            render JSONFormat.respond(null, StatusCode.RecordNotFound, "mushroomDistribution Not found") as JSON
            return
        }
        mushroomDistribution.isDelete = true
        mushroomDistribution.save(flush: true)
        render JSONFormat.respond(null, StatusCode.OK, "success") as JSON

    }

}
