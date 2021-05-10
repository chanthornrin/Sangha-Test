package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import grails.converters.JSON

class DistrictController extends SimpleGenericRestfulController<District> {
    DistrictController() {
        super(District)
    }
    def districtService

    @Override
    def index(PaginationCommand paginationCommand) {
        String name = params.name
        def result = districtService.getAllDistrict(name, paginationCommand)
        render JSONFormat.respond(result) as JSON
    }
}
