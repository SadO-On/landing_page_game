package studio.s98.landingpagegame.data

import studio.s98.landingpagegame.data.models.WordMapper
import studio.s98.landingpagegame.viewmodel.UserLevel
import studio.s98.landingpagegame.viewmodel.Word

class MainRepositoryImp(
) : MainRepository {

    private val localDataStore = LocalDataStore()

    override fun generateNewWords(num: Int): List<Word> {
        return localDataStore.generateNewWords(num).map { WordMapper.map(it) }
    }

    override fun isContained(word: String): Boolean {
        return localDataStore.isContained(word)
    }

    override fun addXP(stars: Int) {
        val xp = stars * 15
        val current = localDataStore.getXP()
        if (current + xp > 100) {
            var level = localDataStore.getLevel()
            level += 1
            localDataStore.addLevel(level)
            localDataStore.addXp(0)
        } else {
            localDataStore.addXp(current + xp)
        }
    }

    override fun getUserLevel(): UserLevel {
        val xp = localDataStore.getXP()
        val level = localDataStore.getLevel()
        return UserLevel(level, xp.toFloat(), isFirstTime = xp == 0 && level == 0)
    }
}
