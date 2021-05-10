package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import grails.gorm.transactions.Transactional

@Transactional
class ProvinceService {

    def getAllProvince(String name,PaginationCommand paginationCommand) {
        return Province.createCriteria().list(paginationCommand.params) {
            if(name){
                ilike("name","%${name}%")
            }
        }

    }
}
