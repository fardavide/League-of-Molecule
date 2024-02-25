@file:Suppress("MemberVisibilityCanBePrivate")

package fardavide.molecule.domain

val Champion.Companion.samples get() = ChampionSamples
val Version.Companion.samples get() = VersionSamples

object ChampionSamples {

    // region Aatrox
    val Aatrox = Champion(
        lore = "Once honored defenders of Shurima against the Void, Aatrox and his brethren would eventually " +
            "become an even greater threat to Runeterra, and were defeated only by cunning mortal sorcery. But " +
            "after centuries of imprisonment, Aatrox was the first to find...",
        id = "Aatrox",
        image = Image(
            full = "Aatrox.png",
            group = "champion",
            height = 48,
            sprite = "champion0.png",
            width = 48,
            x = 0,
            y = 0
        ),
        info = Info(attack = 8, defense = 4, magic = 3, difficulty = 4),
        key = "266",
        name = "Aatrox",
        parType = "Blood Well",
        stats = Stats(
            armor = 38.0,
            armorPerLevel = 3.25,
            attackDamage = 60.0,
            attackDamagePerLevel = 5.0,
            attackRange = 175.0,
            attackSpeed = 0.651,
            attackSpeedPerLevel = 2.5,
            crit = 0.0,
            critPerLevel = 0.0,
            hp = 580.0,
            hpPerLevel = 90.0,
            hpRegen = 3.0,
            hpRegenPerLevel = 0.75,
            moveSpeed = 345.0,
            mana = 0.0,
            manaPerLevel = 0.0,
            manaRegen = 0.0,
            manaRegenPerLevel = 0.0,
            magicDefence = 32.0,
            magicDefencePerLevel = 1.25
        ),
        tags = listOf("Fighter", "Tank"),
        title = "the Darkin Blade",
        version = "14.4.1"
    )
    // endregion

    // region Akali
    val Akali = Champion(
        lore = "Abandoning the Kinkou Order and her title of the Fist of Shadow, Akali now strikes alone, " +
            "ready to be the deadly weapon her people need.",
        id = "Akali",
        image = Image(
            full = "Akali.png",
            group = "champion",
            height = 48,
            sprite = "champion0.png",
            width = 48,
            x = 96,
            y = 0
        ),
        info = Info(attack = 5, defense = 3, magic = 8, difficulty = 7),
        key = "84",
        name = "Akali",
        parType = "Energy",
        stats = Stats(
            armor = 23.0,
            armorPerLevel = 4.7,
            attackDamage = 62.0,
            attackDamagePerLevel = 3.3,
            attackRange = 125.0,
            attackSpeed = 0.625,
            attackSpeedPerLevel = 3.2,
            crit = 0.0,
            critPerLevel = 0.0,
            hp = 570.0,
            hpPerLevel = 119.0,
            hpRegen = 9.0,
            hpRegenPerLevel = 0.9,
            moveSpeed = 345.0,
            mana = 200.0,
            manaPerLevel = 0.0,
            manaRegen = 50.0,
            manaRegenPerLevel = 0.0,
            magicDefence = 37.0,
            magicDefencePerLevel = 2.05
        ),
        tags = listOf("Assassin"),
        title = "the Rogue Assassin",
        version = "14.4.1"
    )
    // endregion

    // region Ezreal
    val Ezreal = Champion(
        lore = "A dashing adventurer, unknowingly gifted in the magical arts, Ezreal raids long-lost catacombs, " +
            "tangles with ancient curses, and overcomes seemingly impossible odds with ease.",
        id = "Ezreal",
        image = Image(
            full = "Ezreal.png",
            group = "champion",
            height = 48,
            sprite = "champion1.png",
            width = 48,
            x = 0,
            y = 0
        ),
        info = Info(
            attack = 7,
            defense = 2,
            magic = 6,
            difficulty = 7
        ),
        key = "81",
        name = "Ezreal",
        parType = "Mana",
        stats = Stats(
            armor = 24.0,
            armorPerLevel = 4.7,
            attackDamage = 62.0,
            attackDamagePerLevel = 2.5,
            attackRange = 550.0,
            attackSpeed = 0.625,
            attackSpeedPerLevel = 2.5,
            crit = 0.0,
            critPerLevel = 0.0,
            hp = 600.0,
            hpPerLevel = 102.0,
            hpRegen = 4.0,
            hpRegenPerLevel = 0.65,
            moveSpeed = 325.0,
            mana = 375.0,
            manaPerLevel = 70.0,
            manaRegen = 8.5,
            manaRegenPerLevel = 1.0,
            magicDefence = 30.0,
            magicDefencePerLevel = 1.3
        ),
        tags = listOf("Marksman", "Mage"),
        title = "the Prodigal Explorer",
        version = "14.4.1"
    )
    // endregion

    // region Kai'Sa
    val Kaisa = Champion(
        lore = "Claimed by the Void when she was only a child, Kai'Sa managed to survive through sheer tenacity " +
            "and strength of will. Her experiences have made her a deadly hunter and, to some, the harbinger of " +
            "a future they would rather not live to see.",
        id = "Kaisa",
        image = Image(
            full = "Kaisa.png",
            group = "champion",
            height = 48,
            sprite = "champion1.png",
            width = 48,
            x = 144,
            y = 96
        ),
        info = Info(attack = 8, defense = 5, magic = 3, difficulty = 6),
        key = "145",
        name = "Kai'Sa",
        parType = "Mana",
        stats = Stats(
            armor = 25.0,
            armorPerLevel = 4.2,
            attackDamage = 59.0,
            attackDamagePerLevel = 2.6,
            attackRange = 525.0,
            attackSpeed = 0.644,
            attackSpeedPerLevel = 1.8,
            crit = 0.0,
            critPerLevel = 0.0,
            hp = 640.0,
            hpPerLevel = 102.0,
            hpRegen = 3.5,
            hpRegenPerLevel = 0.55,
            moveSpeed = 335.0,
            mana = 345.0,
            manaPerLevel = 40.0,
            manaRegen = 8.2,
            manaRegenPerLevel = 0.7,
            magicDefence = 30.0,
            magicDefencePerLevel = 1.3
        ),
        tags = listOf("Marksman"),
        title = "Daughter of the Void",
        version = "14.4.1"
    )
    // endregion

    // region Lulu
    val Lulu = Champion(
        lore = "The yordle mage Lulu is known for conjuring dreamlike illusions and fanciful creatures as she " +
            "roams Runeterra with her fairy companion Pix.",
        id = "Lulu",
        image = Image(
            full = "Lulu.png",
            group = "champion",
            height = 48,
            sprite = "champion2.png",
            width = 48,
            x = 144,
            y = 48
        ),
        info = Info(attack = 4, defense = 5, magic = 7, difficulty = 5),
        key = "117",
        name = "Lulu",
        parType = "Mana",
        stats = Stats(
            hp = 595.0,
            hpPerLevel = 92.0,
            mana = 350.0,
            manaPerLevel = 55.0,
            moveSpeed = 330.0,
            armor = 26.0,
            armorPerLevel = 4.9,
            magicDefence = 30.0,
            magicDefencePerLevel = 1.3,
            attackRange = 550.0,
            hpRegen = 6.0,
            hpRegenPerLevel = 0.6,
            manaRegen = 11.0,
            manaRegenPerLevel = 0.6,
            crit = 0.0,
            critPerLevel = 0.0,
            attackDamage = 47.0,
            attackDamagePerLevel = 2.6,
            attackSpeedPerLevel = 2.25,
            attackSpeed = 0.625
        ),
        tags = listOf("Support", "Mage"),
        title = "the Fae Sorceress",
        version = "14.4.1"
    )
    // endregion

    // region Nami
    val Nami = Champion(
        lore = "A headstrong young vastaya of the seas, Nami was the first of the Marai tribe to leave the " +
            "waves and venture onto dry land, when their ancient accord with the Targonians was broken.",
        id = "Nami",
        image = Image(
            full = "Nami.png",
            group = "champion",
            height = 48,
            sprite = "champion2.png",
            width = 48,
            x = 432,
            y = 96
        ),
        info = Info(attack = 5, defense = 4, magic = 7, difficulty = 8),
        key = "267",
        name = "Nami",
        parType = "Mana",
        stats = Stats(
            hp = 560.0,
            hpPerLevel = 88.0,
            mana = 365.0,
            manaPerLevel = 43.0,
            moveSpeed = 335.0,
            armor = 29.0,
            armorPerLevel = 5.2,
            magicDefence = 30.0,
            magicDefencePerLevel = 1.3,
            attackRange = 550.0,
            hpRegen = 5.5,
            hpRegenPerLevel = 0.55,
            manaRegen = 11.5,
            manaRegenPerLevel = 0.4,
            crit = 0.0,
            critPerLevel = 0.0,
            attackDamage = 51.0,
            attackDamagePerLevel = 3.1,
            attackSpeedPerLevel = 2.61,
            attackSpeed = 0.644
        ),
        tags = listOf("Support", "Mage"),
        title = "the Tidecaller",
        version = "14.4.1"
    )
    // endregion

    // region Sett
    val Sett = Champion(
        lore = "A leader of Ionia's growing criminal underworld, Sett rose to prominence in the wake of the " +
            "war with Noxus.",
        id = "Sett",
        image = Image(
            full = "Sett.png",
            group = "champion",
            height = 48,
            sprite = "champion3.png",
            width = 48,
            x = 240,
            y = 96
        ),
        info = Info(attack = 8, defense = 5, magic = 1, difficulty = 2),
        key = "875",
        name = "Sett",
        parType = "Grit",
        stats = Stats(
            hp = 670.0,
            hpPerLevel = 114.0,
            mana = 0.0,
            manaPerLevel = 0.0,
            moveSpeed = 340.0,
            armor = 33.0,
            armorPerLevel = 5.2,
            magicDefence = 28.0,
            magicDefencePerLevel = 2.05,
            attackRange = 125.0,
            hpRegen = 7.0,
            hpRegenPerLevel = 0.5,
            manaRegen = 0.0,
            manaRegenPerLevel = 0.0,
            crit = 0.0,
            critPerLevel = 0.0,
            attackDamage = 60.0,
            attackDamagePerLevel = 4.0,
            attackSpeedPerLevel = 1.75,
            attackSpeed = 0.625
        ),
        tags = listOf("Fighter", "Tank"),
        title = "the Boss",
        version = "14.4.1"
    )
    // endregion

    // region Teemo
    val Teemo = Champion(
        lore = "Undeterred by even the most dangerous and threatening of obstacles, Teemo scouts the world " +
            "with boundless enthusiasm and a cheerful spirit. A yordle with an unwavering sense of morality, he " +
            "takes pride in following the Bandle Scout's Code, sometimes...",
        id = "Teemo",
        image = Image(
            full = "Teemo.png",
            group = "champion",
            height = 48,
            sprite = "champion4.png",
            width = 48,
            x = 144,
            y = 48
        ),
        info = Info(
            attack = 5,
            defense = 3,
            magic = 7,
            difficulty = 6
        ),
        key = "17",
        name = "Teemo",
        parType = "Mana",
        stats = Stats(
            armor = 24.0,
            armorPerLevel = 4.95,
            attackDamage = 54.0,
            attackDamagePerLevel = 3.0,
            attackRange = 500.0,
            attackSpeed = 0.69,
            attackSpeedPerLevel = 3.38,
            crit = 0.0,
            critPerLevel = 0.0,
            hp = 598.0,
            hpPerLevel = 104.0,
            hpRegen = 5.5,
            hpRegenPerLevel = 0.65,
            moveSpeed = 330.0,
            mana = 334.0,
            manaPerLevel = 25.0,
            manaRegen = 9.6,
            manaRegenPerLevel = 0.45,
            magicDefence = 30.0,
            magicDefencePerLevel = 1.3
        ),
        tags = listOf("Marksman", "Assassin"),
        title = "the Swift Scout",
        version = "14.4.1"
    )
    // endregion

    // region Yasuo
    val Yasuo = Champion(
        lore = "An Ionian of deep resolve, Yasuo is an agile swordsman who wields the air itself against his enemies.",
        id = "Yasuo",
        image = Image(
            full = "Yasuo.png",
            group = "champion",
            height = 48,
            sprite = "champion5.png",
            width = 48,
            x = 288,
            y = 0
        ),
        info = Info(attack = 8, defense = 4, magic = 4, difficulty = 10),
        key = "157",
        name = "Yasuo",
        parType = "Flow",
        stats = Stats(
            armor = 30.0,
            armorPerLevel = 4.6,
            attackDamage = 60.0,
            attackDamagePerLevel = 3.0,
            attackRange = 175.0,
            attackSpeed = 0.697,
            attackSpeedPerLevel = 3.5,
            crit = 0.0,
            critPerLevel = 0.0,
            hp = 590.0,
            hpPerLevel = 110.0,
            hpRegen = 6.5,
            hpRegenPerLevel = 0.9,
            moveSpeed = 345.0,
            mana = 100.0,
            manaPerLevel = 0.0,
            manaRegen = 0.0,
            manaRegenPerLevel = 0.0,
            magicDefence = 32.0,
            magicDefencePerLevel = 2.05
        ),
        tags = listOf("Fighter", "Assassin"),
        title = "the Unforgiven",
        version = "14.4.1"
    )
    // endregion

    // region Zeri
    val Zeri = Champion(
        lore = "A headstrong, spirited young woman from Zaun's working-class, Zeri channels her electric " +
            "magic to charge herself and her custom-crafted gun. Her volatile power mirrors her emotions, its " +
            "sparks reflecting her lightning-fast approach to life.",
        id = "Zeri",
        image = Image(
            full = "Zeri.png",
            group = "champion",
            height = 48,
            sprite = "champion5.png",
            width = 48,
            x = 96,
            y = 48
        ),
        info = Info(attack = 8, defense = 5, magic = 3, difficulty = 6),
        key = "221",
        name = "Zeri",
        parType = "Mana",
        stats = Stats(
            hp = 630.0,
            hpPerLevel = 110.0,
            mana = 250.0,
            manaPerLevel = 45.0,
            moveSpeed = 330.0,
            armor = 24.0,
            armorPerLevel = 4.2,
            magicDefence = 30.0,
            magicDefencePerLevel = 1.3,
            attackRange = 500.0,
            hpRegen = 3.25,
            hpRegenPerLevel = 0.7,
            manaRegen = 6.0,
            manaRegenPerLevel = 0.8,
            crit = 0.0,
            critPerLevel = 0.0,
            attackDamage = 56.0,
            attackDamagePerLevel = 2.0,
            attackSpeedPerLevel = 2.0,
            attackSpeed = 0.658
        ),
        tags = listOf("Marksman"),
        title = "The Spark of Zaun",
        version = "14.4.1"
    )
    // endregion

    fun all() = listOf(
        Aatrox,
        Akali,
        Ezreal,
        Kaisa,
        Lulu,
        Nami,
        Sett,
        Teemo,
        Yasuo,
        Zeri
    )
}

object VersionSamples {

    val v14_4_0 = Version("14.4.0")
    val v14_4_1 = Version("14.4.1")

    fun all() = listOf(
        v14_4_0,
        v14_4_1
    )

    fun latest() = all().latest()
}
