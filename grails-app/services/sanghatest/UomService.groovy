package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import grails.gorm.transactions.Transactional

@Transactional
class UomService {
    def getAllUom(String name, PaginationCommand paginationCommand) {
        return Uom.createCriteria().list(paginationCommand.params) {
            if (name) {
                ilike("name", "%${name}%")
            }
        }
    }
}
