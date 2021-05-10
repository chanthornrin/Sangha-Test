package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import grails.converters.JSON

class ProvinceController extends SimpleGenericRestfulController<Province> {
    ProvinceController() {
        super(Province)
    }
    def provinceService

    @Override
    def index(PaginationCommand paginationCommand) {
        String name = params.name
        def result = provinceService.getAllProvince(name, paginationCommand)
        render JSONFormat.respond(result) as JSON
    }
}
