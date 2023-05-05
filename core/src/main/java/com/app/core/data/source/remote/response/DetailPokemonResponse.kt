package com.app.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailPokemonResponse(

    @field:SerializedName("location_area_encounters")
	val locationAreaEncounters: String? = null,

    @field:SerializedName("types")
	val types: List<TypesItemResponse>? = null,

    @field:SerializedName("base_experience")
	val baseExperience: Int? = null,

    @field:SerializedName("held_items")
	val heldItems: List<HeldItemsItemResponse>? = null,

    @field:SerializedName("weight")
	val weight: Int? = null,

    @field:SerializedName("is_default")
	val isDefault: Boolean? = null,

    @field:SerializedName("past_types")
	val pastTypes: List<Any>? = null,

    @field:SerializedName("sprites")
	val sprites: SpritesResponse? = null,

    @field:SerializedName("abilities")
	val abilities: List<AbilitiesItemResponse>? = null,

    @field:SerializedName("game_indices")
	val gameIndices: List<GameIndicesItemResponse>? = null,

    @field:SerializedName("species")
	val species: SpeciesResponse? = null,

    @field:SerializedName("stats")
	val stats: List<StatsItemResponse>? = null,

    @field:SerializedName("moves")
	val moves: List<MovesItemResponse>? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("forms")
	val forms: List<FormsItemResponse>? = null,

    @field:SerializedName("height")
	val height: Int? = null,

    @field:SerializedName("order")
	val order: Int? = null
)

data class VersionGroupResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class BlackWhiteResponse(

    @field:SerializedName("back_shiny_female")
	val backShinyFemale: String? = null,

    @field:SerializedName("back_female")
	val backFemale: String? = null,

    @field:SerializedName("back_default")
	val backDefault: String? = null,

    @field:SerializedName("front_shiny_female")
	val frontShinyFemale: String? = null,

    @field:SerializedName("front_default")
	val frontDefault: String? = null,

    @field:SerializedName("animated")
	val animated: AnimatedResponse? = null,

    @field:SerializedName("front_female")
	val frontFemale: String? = null,

    @field:SerializedName("back_shiny")
	val backShiny: String? = null,

    @field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class MovesItemResponse(

    @field:SerializedName("version_group_details")
	val versionGroupDetails: List<VersionGroupDetailsItemResponse>? = null,

    @field:SerializedName("move")
	val move: MoveResponse? = null
)

data class OtherResponse(

    @field:SerializedName("dream_world")
	val dreamWorld: DreamWorldResponse? = null,

    @field:SerializedName("official-artwork")
	val officialArtwork: OfficialArtworkResponse? = null,

    @field:SerializedName("home")
	val home: HomeResponse? = null
)

data class DiamondPearlResponse(

	@field:SerializedName("back_shiny_female")
	val backShinyFemale: String? = null,

	@field:SerializedName("back_female")
	val backFemale: String? = null,

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("front_shiny_female")
	val frontShinyFemale: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: String? = null,

	@field:SerializedName("back_shiny")
	val backShiny: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class ItemResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class TypesItemResponse(

	@field:SerializedName("slot")
	val slot: Int? = null,

	@field:SerializedName("type")
	val type: TypeResponse? = null
)

data class EmeraldResponse(

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class DreamWorldResponse(

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: Any? = null
)

data class VersionGroupDetailsItemResponse(

    @field:SerializedName("level_learned_at")
	val levelLearnedAt: Int? = null,

    @field:SerializedName("version_group")
	val versionGroup: VersionGroupResponse? = null,

    @field:SerializedName("move_learn_method")
	val moveLearnMethod: MoveLearnMethodResponse? = null
)

data class SpritesResponse(

    @field:SerializedName("back_shiny_female")
	val backShinyFemale: String? = null,

    @field:SerializedName("back_female")
	val backFemale: String? = null,

    @field:SerializedName("other")
	val other: OtherResponse? = null,

    @field:SerializedName("back_default")
	val backDefault: String? = null,

    @field:SerializedName("front_shiny_female")
	val frontShinyFemale: String? = null,

    @field:SerializedName("front_default")
	val frontDefault: String? = null,

    @field:SerializedName("versions")
	val versions: VersionsResponse? = null,

    @field:SerializedName("front_female")
	val frontFemale: String? = null,

    @field:SerializedName("back_shiny")
	val backShiny: String? = null,

    @field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class OfficialArtworkResponse(

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class VersionResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class GenerationViResponse(

    @field:SerializedName("omegaruby-alphasapphire")
	val omegarubyAlphasapphire: OmegarubyAlphasapphireResponse? = null,

    @field:SerializedName("x-y")
	val xY: XYResponse? = null
)

data class HomeResponse(

	@field:SerializedName("front_shiny_female")
	val frontShinyFemale: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class YellowResponse(

	@field:SerializedName("front_gray")
	val frontGray: String? = null,

	@field:SerializedName("back_transparent")
	val backTransparent: String? = null,

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("back_gray")
	val backGray: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_transparent")
	val frontTransparent: String? = null
)

data class StatResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class StatsItemResponse(

    @field:SerializedName("stat")
	val stat: StatResponse? = null,

    @field:SerializedName("base_stat")
	val baseStat: Int? = null,

    @field:SerializedName("effort")
	val effort: Int? = null
)

data class GoldResponse(

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_transparent")
	val frontTransparent: String? = null,

	@field:SerializedName("back_shiny")
	val backShiny: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class GenerationIvResponse(

    @field:SerializedName("platinum")
	val platinum: PlatinumResponse? = null,

    @field:SerializedName("diamond-pearl")
	val diamondPearl: DiamondPearlResponse? = null,

    @field:SerializedName("heartgold-soulsilver")
	val heartgoldSoulsilver: HeartgoldSoulsilverResponse? = null
)

data class AnimatedResponse(

	@field:SerializedName("back_shiny_female")
	val backShinyFemale: String? = null,

	@field:SerializedName("back_female")
	val backFemale: String? = null,

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("front_shiny_female")
	val frontShinyFemale: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: String? = null,

	@field:SerializedName("back_shiny")
	val backShiny: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class GameIndicesItemResponse(

	@field:SerializedName("game_index")
	val gameIndex: Int? = null,

	@field:SerializedName("version")
	val version: VersionResponse? = null
)

data class MoveLearnMethodResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class VersionDetailsItemResponse(

    @field:SerializedName("version")
	val version: VersionResponse? = null,

    @field:SerializedName("rarity")
	val rarity: Int? = null
)

data class AbilitiesItemResponse(

    @field:SerializedName("is_hidden")
	val isHidden: Boolean? = null,

    @field:SerializedName("ability")
	val ability: AbilityResponse? = null,

    @field:SerializedName("slot")
	val slot: Int? = null
)

data class PlatinumResponse(

	@field:SerializedName("back_shiny_female")
	val backShinyFemale: String? = null,

	@field:SerializedName("back_female")
	val backFemale: String? = null,

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("front_shiny_female")
	val frontShinyFemale: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: String? = null,

	@field:SerializedName("back_shiny")
	val backShiny: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class GenerationIiResponse(

    @field:SerializedName("gold")
	val gold: GoldResponse? = null,

    @field:SerializedName("crystal")
	val crystal: CrystalResponse? = null,

    @field:SerializedName("silver")
	val silver: SilverResponse? = null
)

data class CrystalResponse(

	@field:SerializedName("back_transparent")
	val backTransparent: String? = null,

	@field:SerializedName("back_shiny_transparent")
	val backShinyTransparent: String? = null,

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_transparent")
	val frontTransparent: String? = null,

	@field:SerializedName("front_shiny_transparent")
	val frontShinyTransparent: String? = null,

	@field:SerializedName("back_shiny")
	val backShiny: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class HeldItemsItemResponse(

    @field:SerializedName("item")
	val item: ItemResponse? = null,

    @field:SerializedName("version_details")
	val versionDetails: List<VersionDetailsItemResponse>? = null
)

data class AbilityResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class GenerationIResponse(

    @field:SerializedName("yellow")
	val yellow: YellowResponse? = null,

    @field:SerializedName("red-blue")
	val redBlue: RedBlueResponse? = null
)

data class UltraSunUltraMoonResponse(

	@field:SerializedName("front_shiny_female")
	val frontShinyFemale: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class RubySapphireResponse(

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("back_shiny")
	val backShiny: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class HeartgoldSoulsilverResponse(

	@field:SerializedName("back_shiny_female")
	val backShinyFemale: String? = null,

	@field:SerializedName("back_female")
	val backFemale: String? = null,

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("front_shiny_female")
	val frontShinyFemale: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: String? = null,

	@field:SerializedName("back_shiny")
	val backShiny: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class GenerationViiResponse(

    @field:SerializedName("icons")
	val icons: IconsResponse? = null,

    @field:SerializedName("ultra-sun-ultra-moon")
	val ultraSunUltraMoon: UltraSunUltraMoonResponse? = null
)

data class MoveResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class GenerationIiiResponse(

    @field:SerializedName("firered-leafgreen")
	val fireredLeafgreen: FireredLeafgreenResponse? = null,

    @field:SerializedName("ruby-sapphire")
	val rubySapphire: RubySapphireResponse? = null,

    @field:SerializedName("emerald")
	val emerald: EmeraldResponse? = null
)

data class FormsItemResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class SilverResponse(

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_transparent")
	val frontTransparent: String? = null,

	@field:SerializedName("back_shiny")
	val backShiny: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class VersionsResponse(

    @field:SerializedName("generation-iii")
	val generationIii: GenerationIiiResponse? = null,

    @field:SerializedName("generation-ii")
	val generationIi: GenerationIiResponse? = null,

    @field:SerializedName("generation-v")
	val generationV: GenerationVResponse? = null,

    @field:SerializedName("generation-iv")
	val generationIv: GenerationIvResponse? = null,

    @field:SerializedName("generation-vii")
	val generationVii: GenerationViiResponse? = null,

    @field:SerializedName("generation-i")
	val generationI: GenerationIResponse? = null,

    @field:SerializedName("generation-viii")
	val generationViii: GenerationViiiResponse? = null,

    @field:SerializedName("generation-vi")
	val generationVi: GenerationViResponse? = null
)

data class FireredLeafgreenResponse(

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("back_shiny")
	val backShiny: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class SpeciesResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class IconsResponse(

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: Any? = null
)

data class GenerationViiiResponse(

	@field:SerializedName("icons")
	val icons: IconsResponse? = null
)

data class GenerationVResponse(

	@field:SerializedName("black-white")
	val blackWhite: BlackWhiteResponse? = null
)

data class TypeResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class OmegarubyAlphasapphireResponse(

	@field:SerializedName("front_shiny_female")
	val frontShinyFemale: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class XYResponse(

	@field:SerializedName("front_shiny_female")
	val frontShinyFemale: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class RedBlueResponse(

	@field:SerializedName("front_gray")
	val frontGray: String? = null,

	@field:SerializedName("back_transparent")
	val backTransparent: String? = null,

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("back_gray")
	val backGray: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_transparent")
	val frontTransparent: String? = null
)
