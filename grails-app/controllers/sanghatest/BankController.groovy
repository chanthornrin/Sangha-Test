package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import grails.converters.JSON

class BankController extends SimpleGenericRestfulController<Bank> {
    BankController() {
        super(Bank)
    }
    def bankService

    def index(PaginationCommand paginationCommand) {
        String name=params.name
        def result=bankService.getAllBank(name,paginationCommand)
        render JSONFormat.respond(result) as JSON
    }
}
