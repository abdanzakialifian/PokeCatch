package com.app.core.utils

import com.app.core.data.source.local.entity.PokemonEntity
import com.app.core.data.source.remote.response.DetailPokemonResponse
import com.app.core.data.source.remote.response.PokemonResultsItemResponse
import com.app.core.domain.model.AbilitiesItem
import com.app.core.domain.model.Ability
import com.app.core.domain.model.Animated
import com.app.core.domain.model.BlackWhite
import com.app.core.domain.model.Crystal
import com.app.core.domain.model.DetailPokemon
import com.app.core.domain.model.DiamondPearl
import com.app.core.domain.model.DreamWorld
import com.app.core.domain.model.Emerald
import com.app.core.domain.model.FireredLeafgreen
import com.app.core.domain.model.FormsItem
import com.app.core.domain.model.GameIndicesItem
import com.app.core.domain.model.GenerationI
import com.app.core.domain.model.GenerationIi
import com.app.core.domain.model.GenerationIii
import com.app.core.domain.model.GenerationIv
import com.app.core.domain.model.GenerationV
import com.app.core.domain.model.GenerationVi
import com.app.core.domain.model.GenerationVii
import com.app.core.domain.model.GenerationViii
import com.app.core.domain.model.Gold
import com.app.core.domain.model.HeartgoldSoulsilver
import com.app.core.domain.model.HeldItemsItem
import com.app.core.domain.model.Home
import com.app.core.domain.model.Icons
import com.app.core.domain.model.Item
import com.app.core.domain.model.Move
import com.app.core.domain.model.MoveLearnMethod
import com.app.core.domain.model.MovesItem
import com.app.core.domain.model.OfficialArtwork
import com.app.core.domain.model.OmegarubyAlphasapphire
import com.app.core.domain.model.Other
import com.app.core.domain.model.Platinum
import com.app.core.domain.model.Pokemon
import com.app.core.domain.model.PokemonResultsItem
import com.app.core.domain.model.RedBlue
import com.app.core.domain.model.RubySapphire
import com.app.core.domain.model.Silver
import com.app.core.domain.model.Species
import com.app.core.domain.model.Sprites
import com.app.core.domain.model.Stat
import com.app.core.domain.model.StatsItem
import com.app.core.domain.model.Type
import com.app.core.domain.model.TypesItem
import com.app.core.domain.model.UltraSunUltraMoon
import com.app.core.domain.model.Version
import com.app.core.domain.model.VersionDetailsItem
import com.app.core.domain.model.VersionGroup
import com.app.core.domain.model.VersionGroupDetailsItem
import com.app.core.domain.model.Versions
import com.app.core.domain.model.XY
import com.app.core.domain.model.Yellow

fun PokemonResultsItemResponse.mapToPokemonResultsItem(): PokemonResultsItem = PokemonResultsItem(
    name = this.name, url = this.url
)

fun DetailPokemonResponse.mapToDetailPokemon(): DetailPokemon = DetailPokemon(
    locationAreaEncounters = this.locationAreaEncounters,
    types = this.types?.map { typesItemResponse ->
        TypesItem(
            slot = typesItemResponse.slot,
            type = Type(name = typesItemResponse.type?.name, url = typesItemResponse.type?.url)
        )
    },
    baseExperience = this.baseExperience,
    heldItems = this.heldItems?.map { heldItemsItemResponse ->
        HeldItemsItem(item = Item(
            name = heldItemsItemResponse.item?.name, url = heldItemsItemResponse.item?.url
        ),
            versionDetails = heldItemsItemResponse.versionDetails?.map { versionDetailsItemResponse ->
                VersionDetailsItem(
                    version = Version(
                        name = versionDetailsItemResponse.version?.name,
                        url = versionDetailsItemResponse.version?.url
                    ), rarity = versionDetailsItemResponse.rarity
                )
            })
    },
    weight = this.weight,
    isDefault = this.isDefault,
    pastTypes = this.pastTypes,
    sprites = Sprites(
        backShinyFemale = this.sprites?.backShinyFemale,
        backFemale = this.sprites?.backFemale,
        other = Other(
            dreamWorld = DreamWorld(
                frontDefault = this.sprites?.other?.dreamWorld?.frontDefault,
                frontFemale = this.sprites?.other?.dreamWorld?.frontFemale
            ), officialArtwork = OfficialArtwork(
                frontDefault = this.sprites?.other?.officialArtwork?.frontDefault,
                frontShiny = this.sprites?.other?.officialArtwork?.frontShiny
            ), home = Home(
                frontShinyFemale = this.sprites?.other?.home?.frontShinyFemale,
                frontDefault = this.sprites?.other?.home?.frontDefault,
                frontFemale = this.sprites?.other?.home?.frontFemale,
                frontShiny = this.sprites?.other?.home?.frontShiny
            )
        ),
        backDefault = this.sprites?.backDefault,
        frontShinyFemale = this.sprites?.frontShinyFemale,
        frontDefault = this.sprites?.frontDefault,
        versions = Versions(
            generationIii = GenerationIii(
                fireredLeafgreen = FireredLeafgreen(
                    backDefault = this.sprites?.versions?.generationIii?.fireredLeafgreen?.backDefault,
                    frontDefault = this.sprites?.versions?.generationIii?.fireredLeafgreen?.frontDefault,
                    backShiny = this.sprites?.versions?.generationIii?.fireredLeafgreen?.backShiny,
                    frontShiny = this.sprites?.versions?.generationIii?.fireredLeafgreen?.frontShiny
                ), rubySapphire = RubySapphire(
                    backDefault = this.sprites?.versions?.generationIii?.rubySapphire?.backDefault,
                    frontDefault = this.sprites?.versions?.generationIii?.rubySapphire?.frontDefault,
                    backShiny = this.sprites?.versions?.generationIii?.rubySapphire?.backShiny,
                    frontShiny = this.sprites?.versions?.generationIii?.rubySapphire?.frontShiny
                ), emerald = Emerald(
                    frontDefault = this.sprites?.versions?.generationIii?.emerald?.frontDefault,
                    frontShiny = this.sprites?.versions?.generationIii?.emerald?.frontShiny
                )
            ), generationIi = GenerationIi(
                gold = Gold(
                    backDefault = this.sprites?.versions?.generationIi?.gold?.backDefault,
                    frontDefault = this.sprites?.versions?.generationIi?.gold?.frontDefault,
                    frontTransparent = this.sprites?.versions?.generationIi?.gold?.frontTransparent,
                    backShiny = this.sprites?.versions?.generationIi?.gold?.backShiny,
                    frontShiny = this.sprites?.versions?.generationIi?.gold?.frontShiny
                ), crystal = Crystal(
                    backTransparent = this.sprites?.versions?.generationIi?.crystal?.backTransparent,
                    backShinyTransparent = this.sprites?.versions?.generationIi?.crystal?.backShinyTransparent,
                    backDefault = this.sprites?.versions?.generationIi?.crystal?.backDefault,
                    frontDefault = this.sprites?.versions?.generationIi?.crystal?.frontDefault,
                    frontTransparent = this.sprites?.versions?.generationIi?.crystal?.frontTransparent,
                    frontShinyTransparent = this.sprites?.versions?.generationIi?.crystal?.frontShinyTransparent,
                    backShiny = this.sprites?.versions?.generationIi?.crystal?.backShiny,
                    frontShiny = this.sprites?.versions?.generationIi?.crystal?.frontShiny
                ), silver = Silver(
                    backDefault = this.sprites?.versions?.generationIi?.silver?.backDefault,
                    frontDefault = this.sprites?.versions?.generationIi?.silver?.frontDefault,
                    frontTransparent = this.sprites?.versions?.generationIi?.silver?.frontTransparent,
                    backShiny = this.sprites?.versions?.generationIi?.silver?.backShiny,
                    frontShiny = this.sprites?.versions?.generationIi?.silver?.frontShiny
                )
            ), generationV = GenerationV(
                blackWhite = BlackWhite(
                    backShinyFemale = this.sprites?.versions?.generationV?.blackWhite?.backShinyFemale,
                    backFemale = this.sprites?.versions?.generationV?.blackWhite?.backFemale,
                    backDefault = this.sprites?.versions?.generationV?.blackWhite?.backDefault,
                    frontShinyFemale = this.sprites?.versions?.generationV?.blackWhite?.frontShinyFemale,
                    frontDefault = this.sprites?.versions?.generationV?.blackWhite?.frontDefault,
                    animated = Animated(
                        backShinyFemale = this.sprites?.versions?.generationV?.blackWhite?.animated?.backShinyFemale,
                        backFemale = this.sprites?.versions?.generationV?.blackWhite?.animated?.backShinyFemale,
                        backDefault = this.sprites?.versions?.generationV?.blackWhite?.animated?.backDefault,
                        frontShinyFemale = this.sprites?.versions?.generationV?.blackWhite?.animated?.frontShinyFemale,
                        frontDefault = this.sprites?.versions?.generationV?.blackWhite?.animated?.frontDefault,
                        frontFemale = this.sprites?.versions?.generationV?.blackWhite?.animated?.frontFemale,
                        backShiny = this.sprites?.versions?.generationV?.blackWhite?.animated?.backShiny,
                        frontShiny = this.sprites?.versions?.generationV?.blackWhite?.animated?.frontShiny
                    ),
                    frontFemale = this.sprites?.versions?.generationV?.blackWhite?.frontFemale,
                    backShiny = this.sprites?.versions?.generationV?.blackWhite?.backShiny,
                    frontShiny = this.sprites?.versions?.generationV?.blackWhite?.frontShiny
                )
            ), generationIv = GenerationIv(
                platinum = Platinum(
                    backShinyFemale = this.sprites?.versions?.generationIv?.platinum?.backShinyFemale,
                    backFemale = this.sprites?.versions?.generationIv?.platinum?.backFemale,
                    backDefault = this.sprites?.versions?.generationIv?.platinum?.backDefault,
                    frontShinyFemale = this.sprites?.versions?.generationIv?.platinum?.frontShinyFemale,
                    frontDefault = this.sprites?.versions?.generationIv?.platinum?.frontDefault,
                    frontFemale = this.sprites?.versions?.generationIv?.platinum?.frontFemale,
                    backShiny = this.sprites?.versions?.generationIv?.platinum?.backShiny,
                    frontShiny = this.sprites?.versions?.generationIv?.platinum?.frontShiny
                ), diamondPearl = DiamondPearl(
                    backShinyFemale = this.sprites?.versions?.generationIv?.diamondPearl?.backShinyFemale,
                    backFemale = this.sprites?.versions?.generationIv?.diamondPearl?.backFemale,
                    backDefault = this.sprites?.versions?.generationIv?.diamondPearl?.backDefault,
                    frontShinyFemale = this.sprites?.versions?.generationIv?.diamondPearl?.frontShinyFemale,
                    frontDefault = this.sprites?.versions?.generationIv?.diamondPearl?.frontDefault,
                    frontFemale = this.sprites?.versions?.generationIv?.diamondPearl?.frontFemale,
                    backShiny = this.sprites?.versions?.generationIv?.diamondPearl?.backShiny,
                    frontShiny = this.sprites?.versions?.generationIv?.diamondPearl?.frontShiny
                ), heartgoldSoulsilver = HeartgoldSoulsilver(
                    backShinyFemale = this.sprites?.versions?.generationIv?.heartgoldSoulsilver?.backShinyFemale,
                    backFemale = this.sprites?.versions?.generationIv?.heartgoldSoulsilver?.backFemale,
                    backDefault = this.sprites?.versions?.generationIv?.heartgoldSoulsilver?.backDefault,
                    frontShinyFemale = this.sprites?.versions?.generationIv?.heartgoldSoulsilver?.frontShinyFemale,
                    frontDefault = this.sprites?.versions?.generationIv?.heartgoldSoulsilver?.frontDefault,
                    frontFemale = this.sprites?.versions?.generationIv?.heartgoldSoulsilver?.frontFemale,
                    backShiny = this.sprites?.versions?.generationIv?.heartgoldSoulsilver?.backShiny,
                    frontShiny = this.sprites?.versions?.generationIv?.heartgoldSoulsilver?.frontShiny
                )
            ), generationVii = GenerationVii(
                icons = Icons(
                    frontDefault = this.sprites?.versions?.generationVii?.icons?.frontDefault,
                    frontFemale = this.sprites?.versions?.generationVii?.icons?.frontDefault
                ), ultraSunUltraMoon = UltraSunUltraMoon(
                    frontShinyFemale = this.sprites?.versions?.generationVii?.ultraSunUltraMoon?.frontShinyFemale,
                    frontDefault = this.sprites?.versions?.generationVii?.ultraSunUltraMoon?.frontDefault,
                    frontFemale = this.sprites?.versions?.generationVii?.ultraSunUltraMoon?.frontFemale,
                    frontShiny = this.sprites?.versions?.generationVii?.ultraSunUltraMoon?.frontShiny
                )
            ), generationI = GenerationI(
                yellow = Yellow(
                    frontGray = this.sprites?.versions?.generationI?.yellow?.frontGray,
                    backTransparent = this.sprites?.versions?.generationI?.yellow?.backTransparent,
                    backDefault = this.sprites?.versions?.generationI?.yellow?.backDefault,
                    backGray = this.sprites?.versions?.generationI?.yellow?.backGray,
                    frontDefault = this.sprites?.versions?.generationI?.yellow?.frontDefault,
                    frontTransparent = this.sprites?.versions?.generationI?.yellow?.frontTransparent
                ), redBlue = RedBlue(
                    frontGray = this.sprites?.versions?.generationI?.redBlue?.frontGray,
                    backTransparent = this.sprites?.versions?.generationI?.redBlue?.backTransparent,
                    backDefault = this.sprites?.versions?.generationI?.redBlue?.backDefault,
                    backGray = this.sprites?.versions?.generationI?.redBlue?.backGray,
                    frontDefault = this.sprites?.versions?.generationI?.redBlue?.frontDefault,
                    frontTransparent = this.sprites?.versions?.generationI?.redBlue?.frontTransparent
                )
            ), generationViii = GenerationViii(
                icons = Icons(
                    frontDefault = this.sprites?.versions?.generationVii?.icons?.frontDefault,
                    frontFemale = this.sprites?.versions?.generationVii?.icons?.frontFemale
                )
            ), generationVi = GenerationVi(
                omegarubyAlphasapphire = OmegarubyAlphasapphire(
                    frontShinyFemale = this.sprites?.versions?.generationVi?.omegarubyAlphasapphire?.frontShinyFemale,
                    frontDefault = this.sprites?.versions?.generationVi?.omegarubyAlphasapphire?.frontDefault,
                    frontFemale = this.sprites?.versions?.generationVi?.omegarubyAlphasapphire?.frontFemale,
                    frontShiny = this.sprites?.versions?.generationVi?.omegarubyAlphasapphire?.frontShiny
                ), xY = XY(
                    frontShinyFemale = this.sprites?.versions?.generationVi?.xY?.frontShinyFemale,
                    frontDefault = this.sprites?.versions?.generationVi?.xY?.frontDefault,
                    frontFemale = this.sprites?.versions?.generationVi?.xY?.frontFemale,
                    frontShiny = this.sprites?.versions?.generationVi?.xY?.frontShiny
                )
            )
        ),
        frontFemale = this.sprites?.frontFemale,
        backShiny = this.sprites?.backShiny,
        frontShiny = this.sprites?.frontShiny
    ),
    abilities = this.abilities?.map { abilitiesItemResponse ->
        AbilitiesItem(
            isHidden = abilitiesItemResponse.isHidden, ability = Ability(
                name = abilitiesItemResponse.ability?.name, url = abilitiesItemResponse.ability?.url
            ), slot = abilitiesItemResponse.slot
        )
    },
    gameIndices = this.gameIndices?.map { gameIndicesItemResponse ->
        GameIndicesItem(
            gameIndex = gameIndicesItemResponse.gameIndex, version = Version(
                name = gameIndicesItemResponse.version?.name,
                url = gameIndicesItemResponse.version?.url
            )
        )
    },
    species = Species(name = this.species?.name, url = this.species?.url),
    stats = this.stats?.map { statsItemResponse ->
        StatsItem(
            stat = Stat(
                name = statsItemResponse.stat?.name, url = statsItemResponse.stat?.url
            ), baseStat = statsItemResponse.baseStat, effort = statsItemResponse.effort
        )
    },
    moves = this.moves?.map { movesItemResponse ->
        MovesItem(versionGroupDetails = movesItemResponse.versionGroupDetails?.map { versionGroupDetailsItemResponse ->
            VersionGroupDetailsItem(
                levelLearnedAt = versionGroupDetailsItemResponse.levelLearnedAt,
                versionGroup = VersionGroup(
                    name = versionGroupDetailsItemResponse.versionGroup?.name,
                    url = versionGroupDetailsItemResponse.versionGroup?.url
                ),
                moveLearnMethod = MoveLearnMethod(
                    name = versionGroupDetailsItemResponse.moveLearnMethod?.name,
                    url = versionGroupDetailsItemResponse.moveLearnMethod?.url
                )
            )
        }, move = Move(name = movesItemResponse.move?.name, url = movesItemResponse.move?.url))
    },
    name = this.name,
    id = this.id,
    forms = this.forms?.map { formsItemResponse ->
        FormsItem(name = formsItemResponse.name, url = formsItemResponse.url)
    },
    height = this.height,
    order = this.order
)

fun Pokemon.mapToPokemonEntity(): PokemonEntity = PokemonEntity(
    pokemonId = this.pokemonId,
    pokemonName = this.pokemonName,
    pokemonImage = this.pokemonImage,
    pokemonType = this.pokemonType
)

fun PokemonEntity.mapToPokemon(): Pokemon = Pokemon(
    pokemonId = this.pokemonId,
    pokemonName = this.pokemonName,
    pokemonImage = this.pokemonImage,
    pokemonType = this.pokemonType
)

