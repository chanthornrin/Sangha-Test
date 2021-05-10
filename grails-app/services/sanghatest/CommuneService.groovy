package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import grails.gorm.transactions.Transactional

@Transactional
class CommuneService {

    def getAllCommune(String name,PaginationCommand paginationCommand) {
        return Commune.createCriteria().list(paginationCommand.params) {
            if(name){
                ilike("name","%${name}%")
            }
        }

    }
}
