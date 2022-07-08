package com.github.boybeak.map.base.model

interface IIndoorBuilding {
    val levels: List<IIndoorLevel>
    val activeLevelIndex: Int
    val defaultLevelIndex: Int
    val isUnderGround: Boolean
}