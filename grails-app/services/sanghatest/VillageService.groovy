package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import grails.gorm.transactions.Transactional

@Transactional
class VillageService {

    def getAllVillage(String name,PaginationCommand paginationCommand) {
        return Village.createCriteria().list(paginationCommand.params) {
            if(name){
                ilike("name","%${name}%")
            }
        }

    }
}
