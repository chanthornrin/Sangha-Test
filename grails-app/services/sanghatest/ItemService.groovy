package sanghatest

import corebackend.simplegenericrestfulcontroller.generic.PaginationCommand
import grails.gorm.transactions.Transactional
import org.hibernate.transform.AliasToBeanResultTransformer
import org.hibernate.transform.AliasToEntityMapResultTransformer
import org.hibernate.transform.ResultTransformer

@Transactional
class ItemService {
    def getAllItem(String name, Long uomId, PaginationCommand paginationCommand) {
        return Item.createCriteria().list(paginationCommand.params) {
            if (name) {
                ilike("name", "%${name}%")
            }
            if (uomId) {
                eq("uomId", uomId)
            }
        }
    }

    def listAllMushRoomType(String name, PaginationCommand paginationCommand) {
        return Item.createCriteria().list(paginationCommand.params) {
            projections {
                property('id', 'id')
                property('name', 'name')
                property('dateCreated', 'dateCreated')
                property('lastUpdated', 'lastUpdated')
            }
            ResultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
            if (name) {
                ilike("name", "%${name}%")
            }
        }
    }
}
