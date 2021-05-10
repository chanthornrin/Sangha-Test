package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import grails.converters.JSON

class VillageController extends SimpleGenericRestfulController<Village> {
    VillageController(){
        super(Village)
    }
    def villageService
    @Override
    def index(PaginationCommand paginationCommand) {
        String name=params.name
        def result=villageService.getAllVillage(name,paginationCommand)
        render JSONFormat.respond(result) as JSON
    }
}
