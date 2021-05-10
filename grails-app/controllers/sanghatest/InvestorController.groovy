package sanghatest

import TestEnum.WeekDay
import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.SimpleGenericRestfulController
import corebackend.simplegenericrestfulcontroller.generic.StatusCode
import grails.converters.JSON

class InvestorController extends SimpleGenericRestfulController<Investor> {
    InvestorController() {
        super(Investor)
    }
    def investorService
    @Override
    def index(PaginationCommand paginationCommand) {
        String code = params.code
        String name = params.name
        String isDelete = params.isDelete
        print(WeekDay.Monday.isDay.toString())
        def result = investorService.getAllInvestor(code, name, isDelete, paginationCommand)
        render JSONFormat.respond(result) as JSON
    }

    @Override
    def beforeSave(Investor investor) {
        def code = Investor.findByCodeAndIsDelete(investor.code, false)
        if (code) {
            return JSONFormat.respond(null, StatusCode.RecordNotFound, "code used already")
        }
        def result=investorService.validateInvestor(investor)
        return result
    }

    @Override
    def beforeUpdate(Investor investor) {
        investorService.validateInvestor(investor)
        return investor
    }

    @Override
    def delete() {
        def investor = Investor.findByIdAndIsDelete(params.id as Long, false)
        if (!investor) {
            render JSONFormat.respond(null, StatusCode.RecordNotFound, "investor not found") as JSON
            return
        }
        investor.isDelete = true
        investor.save(flush: true)
        render JSONFormat.respond(investor, StatusCode.OK, "success") as JSON
    }
}
