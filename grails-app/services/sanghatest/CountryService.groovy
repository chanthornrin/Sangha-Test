package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import grails.gorm.transactions.Transactional

@Transactional
class CountryService {

    def getAllCountry(String name,PaginationCommand paginationCommand) {
        return Country.createCriteria().list(paginationCommand.params) {
            if(name){
                ilike("name","%${name}%")
            }
        }

    }
}
