package com.santana.eventsagenda.factory

import com.santana.eventsagenda.data.model.EventDTO


object EventFactory {

    fun mockEventPoolParty() = EventDTO(
        date = 1606262400000,
        description = "Festa de pool party com amigos e texto Lorem Ipsum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?",
        title = "Pool Party",
        image = "https://www.bonde.com.br/img/bondenews/2018/11/img_2842.jpg",
        longitude = -53.4488875,
        latitude = -33.6923026,
        price = 49.9,
        id = "42"
    )

    fun mockEventBirthday() = EventDTO(
        date = 1609372800000,
        description = "Muita alegria na festa de aniversáio mais Lorem Ipsum da cidade. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?",
        title = "Aniversário do Antônio Coutinho",
        image = "https://www.bonde.com.br/img/bondenews/2018/11/img_2842.jpg",
        longitude = -53.4488875,
        latitude = -33.6923026,
        price = 15.0,
        id = "43"
    )

    fun mockEvents() =
        arrayListOf(mockEventPoolParty(), mockEventBirthday())
}