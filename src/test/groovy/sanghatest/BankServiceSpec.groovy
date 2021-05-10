package sanghatest

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class BankServiceSpec extends Specification implements ServiceUnitTest<BankService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
