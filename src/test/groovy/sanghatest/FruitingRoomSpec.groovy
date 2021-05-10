package sanghatest

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class FruitingRoomSpec extends Specification implements DomainUnitTest<FruitingRoom> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
