package com.app.core.domain.model

data class DetailPokemon(
    val locationAreaEncounters: String? = null,
    val types: List<TypesItem>? = null,
    val baseExperience: Int? = null,
    val heldItems: List<HeldItemsItem>? = null,
    val weight: Int? = null,
    val isDefault: Boolean? = null,
    val pastTypes: List<Any>? = null,
    val sprites: Sprites? = null,
    val abilities: List<AbilitiesItem>? = null,
    val gameIndices: List<GameIndicesItem>? = null,
    val species: Species? = null,
    val stats: List<StatsItem>? = null,
    val moves: List<MovesItem>? = null,
    val name: String? = null,
    val id: Int? = null,
    val forms: List<FormsItem>? = null,
    val height: Int? = null,
    val order: Int? = null
)

data class VersionGroup(
    val name: String? = null,
    val url: String? = null
)

data class BlackWhite(
    val backShinyFemale: String? = null,
    val backFemale: String? = null,
    val backDefault: String? = null,
    val frontShinyFemale: String? = null,
    val frontDefault: String? = null,
    val animated: Animated? = null,
    val frontFemale: String? = null,
    val backShiny: String? = null,
    val frontShiny: String? = null
)

data class MovesItem(
    val versionGroupDetails: List<VersionGroupDetailsItem>? = null,
    val move: Move? = null
)

data class Other(
    val dreamWorld: DreamWorld? = null,
    val officialArtwork: OfficialArtwork? = null,
    val home: Home? = null
)

data class DiamondPearl(
    val backShinyFemale: String? = null,
    val backFemale: String? = null,
    val backDefault: String? = null,
    val frontShinyFemale: String? = null,
    val frontDefault: String? = null,
    val frontFemale: String? = null,
    val backShiny: String? = null,
    val frontShiny: String? = null
)

data class Item(
    val name: String? = null,
    val url: String? = null
)

data class TypesItem(
    val slot: Int? = null,
    val type: Type? = null
)

data class Emerald(
    val frontDefault: String? = null,
    val frontShiny: String? = null
)

data class DreamWorld(
    val frontDefault: String? = null,
    val frontFemale: Any? = null
)

data class VersionGroupDetailsItem(
    val levelLearnedAt: Int? = null,
    val versionGroup: VersionGroup? = null,
    val moveLearnMethod: MoveLearnMethod? = null
)

data class Sprites(
    val backShinyFemale: String? = null,
    val backFemale: String? = null,
    val other: Other? = null,
    val backDefault: String? = null,
    val frontShinyFemale: String? = null,
    val frontDefault: String? = null,
    val versions: Versions? = null,
    val frontFemale: String? = null,
    val backShiny: String? = null,
    val frontShiny: String? = null
)

data class OfficialArtwork(
    val frontDefault: String? = null,
    val frontShiny: String? = null
)

data class Version(
    val name: String? = null,
    val url: String? = null
)

data class GenerationVi(
    val omegarubyAlphasapphire: OmegarubyAlphasapphire? = null,
    val xY: XY? = null
)

data class Home(
    val frontShinyFemale: String? = null,
    val frontDefault: String? = null,
    val frontFemale: String? = null,
    val frontShiny: String? = null
)

data class Yellow(
    val frontGray: String? = null,
    val backTransparent: String? = null,
    val backDefault: String? = null,
    val backGray: String? = null,
    val frontDefault: String? = null,
    val frontTransparent: String? = null
)

data class Stat(
    val name: String? = null,
    val url: String? = null
)

data class StatsItem(
    val stat: Stat? = null,
    val baseStat: Int? = null,
    val effort: Int? = null
)

data class Gold(
    val backDefault: String? = null,
    val frontDefault: String? = null,
    val frontTransparent: String? = null,
    val backShiny: String? = null,
    val frontShiny: String? = null
)

data class GenerationIv(
    val platinum: Platinum? = null,
    val diamondPearl: DiamondPearl? = null,
    val heartgoldSoulsilver: HeartgoldSoulsilver? = null
)

data class Animated(
    val backShinyFemale: String? = null,
    val backFemale: String? = null,
    val backDefault: String? = null,
    val frontShinyFemale: String? = null,
    val frontDefault: String? = null,
    val frontFemale: String? = null,
    val backShiny: String? = null,
    val frontShiny: String? = null
)

data class GameIndicesItem(
    val gameIndex: Int? = null,
    val version: Version? = null
)

data class MoveLearnMethod(
    val name: String? = null,
    val url: String? = null
)

data class VersionDetailsItem(
    val version: Version? = null,
    val rarity: Int? = null
)

data class AbilitiesItem(
    val isHidden: Boolean? = null,
    val ability: Ability? = null,
    val slot: Int? = null
)

data class Platinum(
    val backShinyFemale: String? = null,
    val backFemale: String? = null,
    val backDefault: String? = null,
    val frontShinyFemale: String? = null,
    val frontDefault: String? = null,
    val frontFemale: String? = null,
    val backShiny: String? = null,
    val frontShiny: String? = null
)

data class GenerationIi(
    val gold: Gold? = null,
    val crystal: Crystal? = null,
    val silver: Silver? = null
)

data class Crystal(
    val backTransparent: String? = null,
    val backShinyTransparent: String? = null,
    val backDefault: String? = null,
    val frontDefault: String? = null,
    val frontTransparent: String? = null,
    val frontShinyTransparent: String? = null,
    val backShiny: String? = null,
    val frontShiny: String? = null
)

data class HeldItemsItem(
    val item: Item? = null,
    val versionDetails: List<VersionDetailsItem>? = null
)

data class Ability(
    val name: String? = null,
    val url: String? = null
)

data class GenerationI(
    val yellow: Yellow? = null,
    val redBlue: RedBlue? = null
)

data class UltraSunUltraMoon(
    val frontShinyFemale: String? = null,
    val frontDefault: String? = null,
    val frontFemale: String? = null,
    val frontShiny: String? = null
)

data class RubySapphire(
    val backDefault: String? = null,
    val frontDefault: String? = null,
    val backShiny: String? = null,
    val frontShiny: String? = null
)

data class HeartgoldSoulsilver(
    val backShinyFemale: String? = null,
    val backFemale: String? = null,
    val backDefault: String? = null,
    val frontShinyFemale: String? = null,
    val frontDefault: String? = null,
    val frontFemale: String? = null,
    val backShiny: String? = null,
    val frontShiny: String? = null
)

data class GenerationVii(
    val icons: Icons? = null,
    val ultraSunUltraMoon: UltraSunUltraMoon? = null
)

data class Move(
    val name: String? = null,
    val url: String? = null
)

data class GenerationIii(
    val fireredLeafgreen: FireredLeafgreen? = null,
    val rubySapphire: RubySapphire? = null,
    val emerald: Emerald? = null
)

data class FormsItem(
    val name: String? = null,
    val url: String? = null
)

data class Silver(
    val backDefault: String? = null,
    val frontDefault: String? = null,
    val frontTransparent: String? = null,
    val backShiny: String? = null,
    val frontShiny: String? = null
)

data class Versions(
    val generationIii: GenerationIii? = null,
    val generationIi: GenerationIi? = null,
    val generationV: GenerationV? = null,
    val generationIv: GenerationIv? = null,
    val generationVii: GenerationVii? = null,
    val generationI: GenerationI? = null,
    val generationViii: GenerationViii? = null,
    val generationVi: GenerationVi? = null
)

data class FireredLeafgreen(
    val backDefault: String? = null,
    val frontDefault: String? = null,
    val backShiny: String? = null,
    val frontShiny: String? = null
)

data class Species(

    val name: String? = null,
    val url: String? = null
)

data class Icons(
    val frontDefault: String? = null,
    val frontFemale: Any? = null
)

data class GenerationViii(
    val icons: Icons? = null
)

data class GenerationV(
    val blackWhite: BlackWhite? = null
)

data class Type(
    val name: String? = null,
    val url: String? = null
)

data class OmegarubyAlphasapphire(
    val frontShinyFemale: String? = null,
    val frontDefault: String? = null,
    val frontFemale: String? = null,
    val frontShiny: String? = null
)

data class XY(
    val frontShinyFemale: String? = null,
    val frontDefault: String? = null,
    val frontFemale: String? = null,
    val frontShiny: String? = null
)

data class RedBlue(
    val frontGray: String? = null,
    val backTransparent: String? = null,
    val backDefault: String? = null,
    val backGray: String? = null,
    val frontDefault: String? = null,
    val frontTransparent: String? = null
)
