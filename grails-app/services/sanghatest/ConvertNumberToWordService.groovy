package sanghatest

import grails.gorm.transactions.Transactional

import java.text.DecimalFormat

@Transactional
class ConvertNumberToWordService {
    String[] tenName = [
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    ]
    String[] numName = [
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    ]

    def convertLessThanOneThousand(int number) {

        //convertLessThan OneThousand
        String soFar;

        if (number % 100 < 20) {
            soFar = numName[number % 100]
            number /= 100;
        } else {
            soFar = numName[number % 10]
            number /= 10;

            soFar = tenName[number % 10] + soFar
            number /= 10;
        }
        if (number == 0) return soFar;
        return numName[number] + " hundred" + soFar;
    }

    def convertZeroToBillions(Long number) {

    }
}
