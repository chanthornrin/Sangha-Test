package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.JSONFormat
import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import corebackend.simplegenericrestfulcontroller.generic.StatusCode
import grails.gorm.transactions.Transactional

@Transactional
class InvestorService {

    def getAllInvestor(String code, String name, String isDelete, PaginationCommand paginationCommand) {
        return Investor.createCriteria().list(paginationCommand.params) {
            eq("isDelete", Boolean.valueOf(isDelete))
            if (code) {
                eq("code", code)
            }
            if (name) {
                ilike("firstName", "%${name}%")
                ilike("lastName", "%${name}%")
            }
        }
    }

    def validateInvestor(Investor investor) {
        def investorType = InvestorType.findById(investor.investorTypeId)
        if (!investorType) {
            return JSONFormat.respond(null, StatusCode.RecordNotFound, "InvestorType not found")
        }
        def province = Province.findById(investor.provinceId)
        if (!province) {
            return JSONFormat.respond(null, StatusCode.RecordNotFound, "province not found")
        }
        def district=District.findById(investor.districtId)
        if(!district){
            return JSONFormat.respond(null,StatusCode.RecordNotFound,"district not found")
        }
        def commune=Commune.findById(investor.communeId)
        if(!commune){
            return JSONFormat.respond(null,StatusCode.RecordNotFound,"commune not found")
        }
        def bank=Bank.findById(investor.bankId)
        if(!bank){
            return JSONFormat.respond(null,StatusCode.RecordNotFound,"bank not found")
        }
        return  investor
    }
}
