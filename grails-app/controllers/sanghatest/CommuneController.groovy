package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import grails.converters.JSON

class CommuneController extends SimpleGenericRestfulController<Commune> {
    CommuneController() {
        super(Commune)
    }
    def communeService

    @Override
    def index(PaginationCommand paginationCommand) {
        String name = params.name
        def result = communeService.getAllCommune(name, paginationCommand)
        render JSONFormat.respond(result) as JSON
    }
}
