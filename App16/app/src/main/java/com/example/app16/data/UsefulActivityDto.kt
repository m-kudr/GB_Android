package com.example.app16.data

import com.example.app16.entity.UsefulActivity

class UsefulActivityDto(
    override val activity: String,
    override val type: String,
    override val participants: Int,
    override val price: Float,
    override val link: String?,
    override val key: String,
    override val accessibility: Float
) : UsefulActivity
