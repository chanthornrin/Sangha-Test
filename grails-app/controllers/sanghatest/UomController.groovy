package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import grails.converters.JSON

class UomController extends SimpleGenericRestfulController<Uom> {
    UomController() {
        super(Uom)
    }
    def uomService

    @Override
    def index(PaginationCommand paginationCommand) {
        String name = params.name
        def result = uomService.getAllUom(name, paginationCommand)
        render JSONFormat.respond(result) as JSON
    }


}
