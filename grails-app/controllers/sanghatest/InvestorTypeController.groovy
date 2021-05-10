package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import grails.converters.JSON

class InvestorTypeController extends SimpleGenericRestfulController<InvestorType> {

    InvestorTypeController() {
        super(InvestorType)
    }
    def investorTypeService

    @Override
    def index(PaginationCommand paginationCommand) {
        String name = params.name
        def result = investorTypeService.getAllInvestorType(name, paginationCommand)
        render JSONFormat.respond(result) as JSON
    }
}
