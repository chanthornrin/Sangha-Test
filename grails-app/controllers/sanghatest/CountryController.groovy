package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import grails.converters.JSON

class CountryController extends SimpleGenericRestfulController<Country> {
    CountryController(){
        super(Country)
    }

    def countryService
    @Override
    def index(PaginationCommand paginationCommand) {
        String name=params.name
        def result=countryService.getAllCountry(name,paginationCommand)
        render JSONFormat.respond(result) as JSON
    }
}
