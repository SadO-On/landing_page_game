package studio.s98.landingpagegame.data.models

import studio.s98.landingpagegame.viewmodel.Word

object WordMapper
{
    fun map(local: WordLocal): Word {
        return Word(
            id = local.id,
            word = local.word
        )
    }
}