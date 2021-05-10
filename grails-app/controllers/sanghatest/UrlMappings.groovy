package sanghatest

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        //Investor
        "/api/investor"(resources: "Investor")

        //InvestorType
        "/api/investorType"(resources: "InvestorType")
        //Bank
        "/api/bank"(resources: "Bank")

        //Country
        "/api/country"(resources: "Country")

        //Province
        "/api/province"(resources: "Province")

        //District
        "/api/district"(resources: "District")

        //Commune
        "/api/commune"(resources: "Commune")

        //Village
        "/api/village"(resources: "Village")

        //FruitingRoom
        "/api/fruitingRoom"(resources: "FruitingRoom")
        "/api/assignFruitingRoomBelongToInvestor"(controller: "FruitingRoom",action: "assignFruitingRoomBelongToInvestor")
        "/api/listAllFruitingRoomCode"(controller: "FruitingRoom",action: "listAllFruitingRoomCode")
    }
}
