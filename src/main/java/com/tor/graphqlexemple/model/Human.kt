package com.tor.graphqlexemple.model

import java.time.LocalDate

data class Human(
        var id: Int = -1,
        var inn: String?,
        var lastName: String,
        var firsName: String,
        var middleName: String,
        var sex: Boolean = false,
        var birthday: LocalDate,
        var brothersCount: Int = 0,
        var childrenCount: Int = 0,
        var sportText: String = "",
        var amateurText: String = "",
        var phone: String = "",
        var needHospice: Boolean = false,
        var isUkrainian: Boolean = true,
        var email: String = "",
        var email2: String = "",
        var remark: String = "",
        var isHumanDismissed: Boolean = false,
        var personCodeU: String = "",
        var idPerson: Int?,
        var experienceBegin: LocalDate?
)