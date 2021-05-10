package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import grails.gorm.transactions.Transactional

@Transactional
class BankService {

    def getAllBank(String name,PaginationCommand paginationCommand) {
        return Bank.createCriteria().list(paginationCommand.params) {
            if(name){
                ilike("name","%${name}%")
            }
        }

    }
}
